/**
 * 
 */
package org.springframework.http.converter.js;

import java.io.IOException;
import java.nio.charset.Charset;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

/**
 * @author Daniel Frey
 *
 */
public class MappingJacksonJavascriptHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean prefixJson = false;


	public MappingJacksonJavascriptHttpMessageConverter() {
		super( new MediaType( "application", "javascript", DEFAULT_CHARSET ) );
	}

	public void setObjectMapper( ObjectMapper objectMapper ) {
		Assert.notNull( objectMapper, "ObjectMapper must not be null" );
		this.objectMapper = objectMapper;
	}

	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	public void setPrefixJson( boolean prefixJson ) {
		this.prefixJson = prefixJson;
	}

	@Override
	public boolean canRead( Class<?> clazz, MediaType mediaType ) {
		JavaType javaType = getJavaType( clazz );
		return ( this.objectMapper.canDeserialize( javaType ) && canRead( mediaType ) );
	}

	@Override
	public boolean canWrite( Class<?> clazz, MediaType mediaType ) {
		return ( this.objectMapper.canSerialize( clazz ) && canWrite( mediaType ) );
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports( Class<?> clazz ) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class, org.springframework.http.HttpInputMessage)
	 */
	@Override
	protected Object readInternal( Class<? extends Object> clazz, HttpInputMessage inputMessage ) throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType( clazz );
		try {
			return this.objectMapper.readValue( inputMessage.getBody(), javaType );
		} catch( JsonProcessingException ex ) {
			throw new HttpMessageNotReadableException( "Could not read JSON: " + ex.getMessage(), ex );
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
	 */
	@Override
	protected void writeInternal( Object t, HttpOutputMessage outputMessage ) throws IOException, HttpMessageNotWritableException {
		JsonEncoding encoding = getJsonEncoding( outputMessage.getHeaders().getContentType() );
		JsonGenerator jsonGenerator = this.objectMapper.getJsonFactory().createJsonGenerator( outputMessage.getBody(), encoding );
		
		try {
			if( this.prefixJson ) {
				jsonGenerator.writeRaw( "{} && " );
			}
			this.objectMapper.writeValue( jsonGenerator, t );
		} catch( JsonProcessingException ex ) {
			throw new HttpMessageNotWritableException(" Could not write JSON: " + ex.getMessage(), ex );
		}
	}

	@SuppressWarnings( "deprecation" )
	protected JavaType getJavaType( Class<?> clazz ) {
		return TypeFactory.type( clazz );
	}

	protected JsonEncoding getJsonEncoding( MediaType contentType ) {
		if( contentType != null && contentType.getCharSet() != null ) {
			Charset charset = contentType.getCharSet();
			for( JsonEncoding encoding : JsonEncoding.values() ) {
				if( charset.name().equals( encoding.getJavaName() ) ) {
					return encoding;
				}
			}
		}
		
		return JsonEncoding.UTF8;
	}

}
