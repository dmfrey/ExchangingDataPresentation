/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

/**
 * @author Daniel Frey
 *
 */
public class Menu {

	private Long id;
	private String name;
	private List<Section> sectons;

	public Menu() { }

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
	 * @return the sectons
	 */
	public List<Section> getSectons() {
		return sectons;
	}

	/**
	 * @param sectons the sectons to set
	 */
	public void setSectons( List<Section> sectons ) {
		this.sectons = sectons;
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
		
		if( !( obj instanceof Menu ) ) {
			return false;
		}
		
		Menu other = (Menu) obj;
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
		
		builder.append( "Menu [" );
		
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
		
		if( sectons != null ) {
			builder.append( "sectons=" );
			builder.append( sectons );
		}
		
		builder.append( "]" );
	
		return builder.toString();
	}
	
}
