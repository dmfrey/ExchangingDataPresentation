/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.dmfrey.restaurant.menu.database.RestaurantTestDatabaseBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.transaction.Transactional;

/**
 * @author Daniel Frey
 *
 */
public class JdbcRestaurantServiceTest {

	private final JdbcRestaurantService service;

	public JdbcRestaurantServiceTest() {
		EmbeddedDatabase db = new RestaurantTestDatabaseBuilder().restaurant().testData( getClass() ).getDatabase();
		
		transactional = new Transactional( db );
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate( db );
		
		service = new JdbcRestaurantService( jdbcTemplate, new RestaurantMapper() );
	}

	@Test
	public void list() {
		List<Restaurant> restaurants = service.list();
		assertNotNull( restaurants );
		assertFalse( restaurants.isEmpty() );
	}

	@Test
	public void testFindById() {
		Restaurant restaurant = service.findById( 1L );
		assertEquals( new Long( 1 ), restaurant.getId() );
		assertEquals( "restaurant 1", restaurant.getName() );
	}
	
	@Test( expected = EmptyResultDataAccessException.class )
	public void testFindByIdNullId() {
		service.findById( null );
	}

	@Test( expected = EmptyResultDataAccessException.class )
	public void testFindByIdNotFound() {
		service.findById( -1L );
	}

	@Test
	public void testCreateRestaurant() {
		Restaurant restaurant = new Restaurant( null, "Test Restaurant" );
		service.createRestaurant( restaurant );
		assertNotNull( restaurant.getId() );
		assertEquals( "Test Restaurant", restaurant.getName() );
	}
	
	@Test
	public void testCreateMenu() {
		Restaurant restaurant = service.findById( 1L );
		Menu menu = new Menu( null, "Test Menu" );
		service.createMenu( menu, restaurant.getId() );
		assertNotNull( menu.getId() );
		assertEquals( "Test Menu", menu.getName() );
	}
	
	@Test
	public void testListMenus() {
		Restaurant restaurant = service.findById( 1L );
		List<Menu> menus = service.listMenus( restaurant.getId() );
		assertNotNull( menus );
		assertFalse( menus.isEmpty() );
	}
	
	@Test
	public void testFindMenuById() {
		Menu menu = service.findMenuById( 1L );
		assertNotNull( menu );
		assertEquals( new Long( 1 ), menu.getId() );
		assertEquals( "menu 1", menu.getName() );
	}
	
	@Test
	public void testUpdateRestaurant() {
		Restaurant restaurant = new Restaurant( null, "Test Restaurant" );
		service.createRestaurant( restaurant );
		assertNotNull( restaurant.getId() );
		assertEquals( "Test Restaurant", restaurant.getName() );

		restaurant.setName( "Test Restaurant, updated" );
		Restaurant updated = service.updateRestaurant( restaurant );
		assertNotNull( updated.getId() );
		assertEquals( "Test Restaurant, updated", updated.getName() );
	}
	
	@Test
	public void testListSections() {
		Menu menu = service.findMenuById( 1L );
		List<Section> sections = service.listSections( menu.getId() );
		assertNotNull( sections );
		assertFalse( sections.isEmpty() );
	}
	
	@Test
	public void testFindSectionById() {
		Section section = service.findSectionById( 1L );
		assertNotNull( section );
		assertEquals( new Long( 1 ), section.getId() );
		assertEquals( "section 1", section.getName() );
	}
	
	@Test
	public void testCreateSection() {
		Menu menu = service.findMenuById( 1L );
		Section section = service.createSection( new Section( null, "Test Section" ), menu.getId() );
		assertNotNull( section.getId() );
		assertEquals( "Test Section", section.getName() );
	}
	
	@Test
	public void testUpdateSection() {
		Menu menu = service.findMenuById( 1L );
		Section section = service.createSection( new Section( null, "Test Section" ), menu.getId() );
		assertNotNull( section.getId() );
		assertEquals( "Test Section", section.getName() );
		
		section.setName( "Test Section, updated" );		
		Section updated = service.updateSection( section );
		assertNotNull( updated.getId() );
		assertEquals( "Test Section, updated", updated.getName() );
	}
	
	@Test
	public void testListMenuItems() {
		Section section = service.findSectionById( 1L );
		List<MenuItem> menuItems = service.listMenuItems( section.getId() );
		assertNotNull( menuItems );
		assertFalse( menuItems.isEmpty() );
	}
	
	@Test
	public void testFindMenuItemById() {
		MenuItem menuItem = service.findMenuItemById( 1L );
		assertNotNull( menuItem );
		assertEquals( new Long( 1 ), menuItem.getId() );
		assertEquals( "section 1, menu item 1", menuItem.getName() );
		assertEquals( "description of section 1, menu item 1", menuItem.getDescription() );
		assertEquals( new Double( 10.00 ), menuItem.getPrice() );
	}
	
	@Test
	public void testCreateMenuItem() {
		Section section = service.findSectionById( 1L );
		MenuItem menuItem = service.createMenuItem( new MenuItem( null, "Test Menu Item", "Description of Test Menu Item", 1.00 ), section.getId() );
		assertNotNull( menuItem.getId() );
		assertEquals( "Test Menu Item", menuItem.getName() );
		assertEquals( "Description of Test Menu Item", menuItem.getDescription() );
		assertEquals( new Double( 1.00 ), menuItem.getPrice() );
	}
	
	@Test
	public void testUpdateMenuItem() {
		Section section = service.findSectionById( 1L );
		MenuItem menuItem = service.createMenuItem( new MenuItem( null, "Test Menu Item", "Description of Test Menu Item", 1.00 ), section.getId() );
		assertNotNull( menuItem.getId() );
		assertEquals( "Test Menu Item", menuItem.getName() );
		assertEquals( "Description of Test Menu Item", menuItem.getDescription() );
		assertEquals( new Double( 1.00 ), menuItem.getPrice() );
		
		menuItem.setName( "Test Menu Item, updated" );
		menuItem.setDescription( "Description of Test Menu Item, updated" );
		menuItem.setPrice( 2.00 );
		MenuItem updated = service.updateMenuItem( menuItem );
		assertNotNull( updated.getId() );
		assertEquals( "Test Menu Item, updated", updated.getName() );
		assertEquals( "Description of Test Menu Item, updated", updated.getDescription() );
		assertEquals( new Double( 2.00 ), updated.getPrice() );
	}
	
	@Rule
	public Transactional transactional;
	
}
