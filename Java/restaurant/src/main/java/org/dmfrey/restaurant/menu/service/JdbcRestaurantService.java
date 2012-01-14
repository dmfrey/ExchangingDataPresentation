/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Daniel Frey
 *
 */
@Repository
public class JdbcRestaurantService implements RestaurantService {

	private static final Logger log = Logger.getLogger( JdbcRestaurantService.class );
	
	private final JdbcTemplate jdbcTemplate;

	private final RestaurantMapper mapper;
	
	@Autowired
	public JdbcRestaurantService( JdbcTemplate jdbcTemplate, RestaurantMapper mapper) {
		log.trace( "initialize : enter" );
		
		this.jdbcTemplate = jdbcTemplate;
		this.mapper = mapper;

		log.trace( "initialize : exit" );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#list()
	 */
	@Override
	public List<Restaurant> list() {
		return jdbcTemplate.query( RestaurantMapper.SELECT_RESTAURANT, mapper );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findById(java.lang.Long)
	 */
	@Override
	public Restaurant findById( Long restaurantId ) {
		return jdbcTemplate.queryForObject( RestaurantMapper.SELECT_RESTAURANT + " where id = ?", mapper, restaurantId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createRestaurant(org.dmfrey.restaurant.menu.service.Restaurant)
	 */
	@Override
	public Restaurant createRestaurant( Restaurant restaurant ) {
		jdbcTemplate.update( "insert into restaurant(name) values(?)", restaurant.getName() );
		restaurant.setId( jdbcTemplate.queryForLong( "call identity()" ) );
		return restaurant;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createMenu(org.dmfrey.restaurant.menu.service.Menu, java.lang.Long)
	 */
	@Override
	public Menu createMenu( Menu menu, Long restaurantId ) {
		jdbcTemplate.update( "insert into menu(name, restaurant) values(?,?)", menu.getName(), restaurantId );
		menu.setId( jdbcTemplate.queryForLong( "call identity()" ) );
		return menu;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listMenus(java.lang.Long)
	 */
	@Override
	public List<Menu> listMenus( Long restaurantId ) {
		return jdbcTemplate.query( RestaurantMapper.SELECT_MENU + " where restaurant = ?", mapper.getMenuRowMapper(), restaurantId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findMenuById(java.lang.Long)
	 */
	@Override
	public Menu findMenuById( Long menuId ) {
		return jdbcTemplate.queryForObject( RestaurantMapper.SELECT_MENU + " where id = ?", mapper.getMenuRowMapper(), menuId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateRestaurant(org.dmfrey.restaurant.menu.service.Restaurant)
	 */
	@Override
	public Restaurant updateRestaurant( Restaurant restaurant ) {
		jdbcTemplate.update( "update restaurant set name = ? where id = ?", restaurant.getName(), restaurant.getId() );
		return restaurant;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateMenu(org.dmfrey.restaurant.menu.service.Menu)
	 */
	@Override
	public Menu updateMenu( Menu menu ) {
		jdbcTemplate.update( "update menu set name = ? where id = ?", menu.getName(), menu.getId() );
		return menu;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listSections(java.lang.Long)
	 */
	@Override
	public List<Section> listSections( Long menuId ) {
		return jdbcTemplate.query( RestaurantMapper.SELECT_SECTION + " where menu = ?", mapper.getSectionRowMapper(), menuId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findSectionById(java.lang.Long)
	 */
	@Override
	public Section findSectionById( Long sectionId ) {
		return jdbcTemplate.queryForObject( RestaurantMapper.SELECT_SECTION + " where id = ?", mapper.getSectionRowMapper(), sectionId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createSection(org.dmfrey.restaurant.menu.service.Section, java.lang.Long)
	 */
	@Override
	public Section createSection( Section section, Long menuId ) {
		jdbcTemplate.update( "insert into section(name, menu) values(?,?)", section.getName(), menuId );
		section.setId( jdbcTemplate.queryForLong( "call identity()" ) );
		return section;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateSection(org.dmfrey.restaurant.menu.service.Section)
	 */
	@Override
	public Section updateSection( Section section ) {
		jdbcTemplate.update( "update section set name = ? where id = ?", section.getName(), section.getId() );
		return section;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listMenuItems(java.lang.Long)
	 */
	@Override
	public List<MenuItem> listMenuItems( Long sectionId ) {
		return jdbcTemplate.query( RestaurantMapper.SELECT_MENU_ITEM + " where section = ?", mapper.getMenuItemRowMapper(), sectionId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findMenuItemById(java.lang.Long)
	 */
	@Override
	public MenuItem findMenuItemById( Long menuItemId ) {
		return jdbcTemplate.queryForObject( RestaurantMapper.SELECT_MENU_ITEM + " where id = ?", mapper.getMenuItemRowMapper(), menuItemId );
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createMenuItem(org.dmfrey.restaurant.menu.service.MenuItem, java.lang.Long)
	 */
	@Override
	public MenuItem createMenuItem( MenuItem menuItem, Long sectionId ) {
		jdbcTemplate.update( "insert into menu_item(name, description, price, section) values(?,?,?,?)", menuItem.getName(), menuItem.getDescription(), menuItem.getPrice(), sectionId );
		menuItem.setId( jdbcTemplate.queryForLong( "call identity()" ) );
		return menuItem;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateMenuItem(org.dmfrey.restaurant.menu.service.MenuItem)
	 */
	@Override
	public MenuItem updateMenuItem( MenuItem menuItem ) {
		jdbcTemplate.update( "update menuItem set name = ?, description = ?, price = ? where id = ?", menuItem.getName(), menuItem.getDescription(), menuItem.getPrice(), menuItem.getId() );
		return menuItem;
	}

}
