/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Daniel Frey
 *
 */
@Entity
@Table( 
	name = "menu"
)
public class Menu {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( name = "name" )
	@NotNull
	@Size( min = 1, max = 128 )
	private String name;
	
	@Column( name = "restaurant" )
	private Long restaurant;
	
	@OneToMany( mappedBy = "menu", targetEntity = Section.class, cascade = CascadeType.ALL )
	private List<Section> sections;

	/**
	 * 
	 */
	public Menu() { }

	/**
	 * @param id
	 * @param name
	 */
	public Menu( Long id, String name ) {
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
	 * @return the restaurant
	 */
	public Long getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant( Long restaurant ) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the sections
	 */
	public List<Section> getSections() {
		return sections;
	}

	/**
	 * @param sections the sections to set
	 */
	public void setSections( List<Section> sections ) {
		this.sections = sections;
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
		
		if( restaurant != null ) {
			builder.append( "restaurant=" );
			builder.append( restaurant );
			builder.append( ", " );
		}
		
		if( sections != null ) {
			builder.append( "sections=" );
			builder.append( sections.size() );
		}
		
		builder.append( "]" );
	
		return builder.toString();
	}
	
}
