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
	name = "section"
)
public class Section {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Column( name = "name" )
	@NotNull
	@Size( min = 1, max = 128 )
	private String name;

	@Column( name = "menu" )
	private Long menu;
	
	@OneToMany( mappedBy = "section", targetEntity = MenuItem.class, cascade = CascadeType.ALL )
	private List<MenuItem> menuItems;

	/**
	 * 
	 */
	public Section() { }

	/**
	 * @param id
	 * @param name
	 */
	public Section( Long id, String name ) {
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
	 * @return the menu
	 */
	public Long getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu( Long menu ) {
		this.menu = menu;
	}

	/**
	 * @return the menuItems
	 */
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * @param menuItems the menuItems to set
	 */
	public void setMenuItems( List<MenuItem> menuItems ) {
		this.menuItems = menuItems;
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
		
		if( !( obj instanceof Section ) ) {
			return false;
		}
		
		Section other = (Section) obj;
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
		
		builder.append( "Section [" );
		
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
		
		if( menu != null ) {
			builder.append( "menu=" );
			builder.append( menu );
			builder.append( ", " );
		}
		
		if( menuItems != null ) {
			builder.append( "menuItems=" );
			builder.append( menuItems.size() );
		}
		
		builder.append( "]" );
	
		return builder.toString();
	}
	
}
