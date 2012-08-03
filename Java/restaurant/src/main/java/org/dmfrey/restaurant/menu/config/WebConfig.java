package org.dmfrey.restaurant.menu.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dmfrey.restaurant.menu.home.DateTimeZoneHandlerInterceptor;
import org.dmfrey.restaurant.menu.service.Menu;
import org.dmfrey.restaurant.menu.service.MenuItem;
import org.dmfrey.restaurant.menu.service.Restaurant;
import org.dmfrey.restaurant.menu.service.Section;
import org.joda.time.DateTimeZone;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.webmvc.RepositoryRestMvcConfiguration;
import org.springframework.format.datetime.joda.JodaTimeContextHolder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

/**
 * Spring MVC Configuration.
 * 
 * Implements {@link WebMvcConfigurer}, which provides convenient callbacks that allow us to customize aspects of the Spring Web MVC framework.
 * These callbacks allow us to register custom interceptors, message converters, argument resovlers, a validator, resource handling, and other things.
 * 
 * @author Daniel Frey
 * 
 * @see WebMvcConfigurer
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Inject
	private Environment environment;

	// implementing WebMvcConfigurer

	public void addInterceptors( InterceptorRegistry registry ) {
		registry.addInterceptor( new DateTimeZoneHandlerInterceptor() );
	}

	public void addArgumentResolvers( List<HandlerMethodArgumentResolver> argumentResolvers ) {
		argumentResolvers.add( new DateTimeZoneHandlerMethodArgumentResolver() );
	}

	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry.addResourceHandler( "/resources/**" ).addResourceLocations( "/resources/" );
	}
	
	public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
		Map<String, Class<?>> aliases = new HashMap<String, Class<?>>();
		aliases.put( "restaurant", Restaurant.class );
		aliases.put( "menu", Menu.class );
		aliases.put( "section", Section.class );
		aliases.put( "menuItem", MenuItem.class );
		
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setSupportedClasses( new Class[] { List.class, Restaurant.class, Menu.class, Section.class, MenuItem.class } );
		marshaller.getXStream().processAnnotations( new Class[] { Restaurant.class, Menu.class, Section.class, MenuItem.class } );

//		marshaller.getXStream().addDefaultImplementation( org.hibernate.collection.PersistentBag.class, java.util.List.class );
		marshaller.getXStream().addDefaultImplementation( org.hibernate.mapping.List.class, java.util.List.class );
		marshaller.getXStream().addDefaultImplementation( org.hibernate.mapping.Map.class, java.util.Map.class );
		marshaller.getXStream().addDefaultImplementation( org.hibernate.mapping.Set.class, java.util.Set.class );
		try {
			marshaller.setAliases( aliases );
		} catch( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		converters.add( new MappingJacksonHttpMessageConverter() );
		converters.add( new MarshallingHttpMessageConverter( marshaller, (Unmarshaller) marshaller ) );
	}
	
	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename( "/WEB-INF/messages/validation" );
		
		if( environment.acceptsProfiles( "development" ) ) {
			messageSource.setCacheSeconds( 0 );
		}
		
		factory.setValidationMessageSource( messageSource );
		
		return factory;
	}

	// additional webmvc-related beans

	@Bean
	public RepositoryRestMvcConfiguration repositoryRestMvcConfiguration() {
		return new RepositoryRestMvcConfiguration();
	}
	
	/**
	 * ViewResolver configuration required to work with Tiles2-based views.
	 */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass( TilesView.class );
		
		return viewResolver;
	}

	/**
	 * Configures Tiles at application startup.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions( new String[] {
			"/WEB-INF/layouts/tiles.xml",
			"/WEB-INF/views/**/tiles.xml"				
		});
		configurer.setCheckRefresh( true );
		
		return configurer;
	}
	
	/**
	 * Messages to support internationalization/localization.
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename( "/WEB-INF/messages/messages" );
		
		if( environment.acceptsProfiles( "development" ) ) {
			messageSource.setCacheSeconds( 0 );
		}
		
		return messageSource;
	}

	/**
	 * Supports FileUploads.
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize( 500000 );

		return multipartResolver;
	}
	
	// custom argument resolver inner classes

	private static class DateTimeZoneHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

		public boolean supportsParameter( MethodParameter parameter ) {
			return DateTimeZone.class.isAssignableFrom( parameter.getParameterType() );
		}

		public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory ) throws Exception {
			return JodaTimeContextHolder.getJodaTimeContext().getTimeZone();
		}
		
	}
	
}