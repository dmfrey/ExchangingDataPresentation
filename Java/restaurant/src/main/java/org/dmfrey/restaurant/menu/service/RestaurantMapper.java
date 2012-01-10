/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Frey
 *
 */
@Component
public class RestaurantMapper implements RowMapper<Restaurant> {

	public static final String SELECT_RESTAURANT = 
			"select id, name from restaurant";
	
	public static final String SELECT_MENU = 
			"select id, name, restaurant from menu";
	
	public static final String SELECT_SECTION = 
			"select id, name, menu from section";
	
	public static final String SELECT_MENU_ITEM = 
			"select id, name, description, price, section from menu_item";
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Restaurant mapRow( ResultSet rs, int rowNum ) throws SQLException {
		return new Restaurant( rs.getLong( "id" ), rs.getString( "name" ) );
	}

	/**
	 * @return
	 */
	public RowMapper<Menu> getMenuRowMapper() {
		return menuRowMapper;
	}
	
	/**
	 * @return
	 */
	public RowMapper<Section> getSectionRowMapper() {
		return sectionRowMapper;
	}
	
	/**
	 * @return
	 */
	public RowMapper<MenuItem> getMenuItemRowMapper() {
		return menuItemRowMapper;
	}
	
	// internal helpers
	
	private RowMapper<Menu> menuRowMapper = new RowMapper<Menu>() {

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public Menu mapRow( ResultSet rs, int rowNum ) throws SQLException {
			return new Menu( rs.getLong( "id" ), rs.getString( "name" ) );
		}
		
	};

	private RowMapper<Section> sectionRowMapper = new RowMapper<Section>() {

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public Section mapRow( ResultSet rs, int rowNum ) throws SQLException {
			return new Section( rs.getLong( "id" ), rs.getString( "name" ) );
		}
		
	};

	private RowMapper<MenuItem> menuItemRowMapper = new RowMapper<MenuItem>() {

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public MenuItem mapRow( ResultSet rs, int rowNum ) throws SQLException {
			return new MenuItem( rs.getLong( "id" ), rs.getString( "name" ), rs.getString( "description" ), rs.getDouble( "price" ) );
		}
		
	};

}
