/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.dmfrey.restaurant.menu.database.RestaurantTestDatabaseBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;
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
		
		service = new JdbcRestaurantService( new StandardEnvironment(), jdbcTemplate, new RestaurantMapper() );
	}

	@Test
	public void list() {
		List<Restaurant> restaurants = service.list();
		assertNotNull( restaurants );
		assertFalse( restaurants.isEmpty() );
	}

	@Rule
	public Transactional transactional;
	
}
