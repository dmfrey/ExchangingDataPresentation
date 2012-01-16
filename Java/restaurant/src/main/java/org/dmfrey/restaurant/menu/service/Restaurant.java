/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Daniel Frey
 *
 */
public class Restaurant {

	private Long id;
	
	@NotNull
	@Size( min = 1, max = 128 )
	private String name;
	
	private List<Menu> menus;
	
	/**
	 * 
	 */
	public Restaurant() { }
	
	/**
	 * @param id
	 * @param name
	 */
	public Restaurant( Long id, String name ) {
		this.id = id;
		this.name = name;
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
	 * @return the menus
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus( List<Menu> menus ) {
		this.menus = menus;
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
		
		if( !( obj instanceof Restaurant ) ) {
			return false;
		}
		
		Restaurant other = (Restaurant) obj;
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
		
		builder.append( "Restaurant [" );
		
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
		
		if( menus != null ) {
			builder.append( "menus=" );
			builder.append( menus.size() );
		}
		
		builder.append( "]" );
		
		return builder.toString();
	}
	
}
