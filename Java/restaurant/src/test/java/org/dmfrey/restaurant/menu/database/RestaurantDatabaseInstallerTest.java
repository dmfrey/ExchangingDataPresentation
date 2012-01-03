package org.dmfrey.restaurant.menu.database;

import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class RestaurantDatabaseInstallerTest {

	@Test
	public void runUpgrader() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseType( EmbeddedDatabaseType.H2 );

		EmbeddedDatabase db = factory.getDatabase();

		DatabaseUpgrader installer = new DatabaseUpgrader( db, new StandardEnvironment() );
		installer.run();
		installer.run();
		
		DatabaseUpgrader installer2 = new DatabaseUpgrader( db, new StandardEnvironment() );
		installer2.run();
	}

}
