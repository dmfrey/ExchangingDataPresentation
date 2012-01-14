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
	
	@Rule
	public Transactional transactional;
	
}
