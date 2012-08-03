/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Daniel Frey
 *
 */
@Entity
@Table( 
	name = "menu_item"
)
public class MenuItem {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Column( name = "name" )
	@NotNull
	@Size( min = 1, max = 128 )
	private String name;

	@Column( name = "description" )
	@Size( max = 512 )
	private String description;
	
	@Column( name = "price" )
	private Double price;
	
	@Column( name = "section" )
	private Long section;
	
	/**
	 * 
	 */
	public MenuItem() { }

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 */
	public MenuItem( Long id, String name, String description, Double price ) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice( Double price ) {
		this.price = price;
	}

	/**
	 * @return the section
	 */
	public Long getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection( Long section ) {
		this.section = section;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		
		if( this == obj ) {
			return true;
		}
		
		if( obj == null ) {
			return false;
		}
		
		if( !( obj instanceof MenuItem ) ) {
			return false;
		}
		
		MenuItem other = (MenuItem) obj;
		if( id == null ) {
			if( other.id != null ) {
				return false;
			}
		} else if( !id.equals( other.id ) ) {
			return false;
		}
	
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append( "MenuItem [" );
		
		if( id != null ) {
			builder.append( "id=" );
			builder.append( id );
			builder.append( ", " );
		}
		
		if( name != null ) {
			builder.append( "name=" );
			builder.append( name );
			builder.append( ", " );
		}
		
		if( description != null ) {
			builder.append( "description=" );
			builder.append( description );
			builder.append( ", " );
		}
		
		if( price != null ) {
			builder.append( "price=" );
			builder.append( price );
			builder.append( ", " );
		}
		
		if( section != null ) {
			builder.append( "section=" );
			builder.append( section );
		}
		
		builder.append( "]" );
		
		return builder.toString();
	}
	
}
