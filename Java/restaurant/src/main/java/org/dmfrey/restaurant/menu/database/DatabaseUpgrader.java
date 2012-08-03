package org.dmfrey.restaurant.menu.database;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.versioned.DatabaseChange;
import org.springframework.jdbc.versioned.DatabaseChangeSet;
import org.springframework.jdbc.versioned.DatabaseVersion;
import org.springframework.jdbc.versioned.GenericDatabaseUpgrader;
import org.springframework.jdbc.versioned.SqlDatabaseChange;

/**
 * Performs migrations against the KnollServices Tax database.
 * 
 * Allows a database migration to be automated as part of integrating a software change that impacts the schema. 
 * 
 * If the Database is at version zero(), the current version of the database will be installed. 
 * If the Database is at a version less than the current version, the database will be upgraded from that version to the current version. 
 * If the Database is already at the current version, no migration will be performed. 
 * 
 * This migration model was adapted from the <a href="http://www.liquibase.org/tutorial-using-oracle">LiquiBase Oracle tutorial</a>.
 * 
 * @author Daniel Frey
 */
public class DatabaseUpgrader {

	private final org.springframework.jdbc.versioned.DatabaseUpgrader upgrader;

	public DatabaseUpgrader( DataSource dataSource, Environment environment ) {
		this.upgrader = createUpgrader( dataSource, environment );
	}

	public void run() {
		upgrader.run();
	}

	// subclassing hooks

	protected void addInstallChanges( DatabaseChangeSet changeSet ) { }

	// internal helpers

	private org.springframework.jdbc.versioned.DatabaseUpgrader createUpgrader( DataSource dataSource, Environment environment ) {
		GenericDatabaseUpgrader upgrader = new GenericDatabaseUpgrader( dataSource );
		if( upgrader.getCurrentDatabaseVersion().equals( DatabaseVersion.zero() ) ) {
			addInstallChangeSet( upgrader, environment );
		} else {
			addUpgradeChangeSets( upgrader );
		}
		return upgrader;
	}

	private void addInstallChangeSet( GenericDatabaseUpgrader upgrader, Environment environment ) {
		DatabaseChangeSet changeSet = new DatabaseChangeSet( DatabaseVersion.valueOf( "1" ) );
		
		if( environment.containsProperty( "database.driverClassName" ) ) {
		
			if( "org.h2.Driver".equals( environment.getProperty( "database.driverClassName" ) ) ) {
				changeSet.add( installScript( "restaurant.sql" ) );
			}
			
			if( "com.mysql.jdbc.Driver".equals( environment.getProperty( "database.driverClassName" ) ) ) {
				changeSet.add( installMysqlScript( "restaurant.sql" ) );
			}
			
		} else {
			changeSet.add( installScript( "restaurant.sql" ) );
		}
		
		addInstallChanges( changeSet );
		upgrader.addChangeSet( changeSet );
	}

	private DatabaseChange installScript( String resource ) {
		return SqlDatabaseChange.inResource( new ClassPathResource( "install/" + resource, DatabaseUpgrader.class ) );
	}

	private DatabaseChange installMysqlScript( String resource ) {
		return SqlDatabaseChange.inResource( new ClassPathResource( "install/mysql/" + resource, DatabaseUpgrader.class ) );
	}

	private void addUpgradeChangeSets( GenericDatabaseUpgrader upgrader ) {
	}

	private DatabaseChange upgradeScript( String resource, Environment environment ) {
		return SqlDatabaseChange.inResource( new ClassPathResource( "upgrade/" + resource, DatabaseUpgrader.class ) );
	}

}