package org.dmfrey.restaurant.menu.database;

import org.junit.Ignore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Ignore
public class RestaurantTestDatabaseBuilder {

	private ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

	public RestaurantTestDatabaseBuilder restaurant() {
		populator.addScript( new ClassPathResource( "install/restaurant.sql", DatabaseUpgrader.class ) );
		return this;
	}

	public RestaurantTestDatabaseBuilder testData( Class<?> testClass ) {
		populator.addScript( new ClassPathResource( testClass.getSimpleName() + ".sql", testClass ) );
		return this;
	}

	public RestaurantTestDatabaseBuilder testData( String script ) {
		populator.addScript( new ClassPathResource( script ) );
		return this;
	}

	public RestaurantTestDatabaseBuilder testData( String script, Class<?> relativeTo ) {
		populator.addScript( new ClassPathResource( script, relativeTo ) );
		return this;
	}

	public EmbeddedDatabase getDatabase() {
		EmbeddedDatabaseFactory databaseFactory = new EmbeddedDatabaseFactory();
		databaseFactory.setDatabaseName( "restaurant" );
		databaseFactory.setDatabaseType( EmbeddedDatabaseType.H2 );
		databaseFactory.setDatabasePopulator( populator );
		return databaseFactory.getDatabase();
	}

}