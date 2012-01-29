package org.dmfrey.restaurant.menu.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.dmfrey.restaurant.menu.database.DatabaseUpgrader;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.versioned.DatabaseChangeSet;
import org.springframework.jdbc.versioned.SqlDatabaseChange;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Restaurant RDBMS access configuration.
 * 
 * A RDBMS provides the system of record for transactional data in the
 * Tax application. 
 * We use {@link JdbcTemplate} to access that data. 
 * We use compile-time-woven AspectJ-advice around {@link Transactional} methods to
 * apply transaction management. 
 * 
 * In "embedded mode", we use an embedded database to ease setup of a developer testing environment. 
 * In "development mode" and "production mode", we connect to a db2400 database via a connection pool.
 * 
 * @author Daniel Frey
 */
@Configuration
@EnableTransactionManagement( mode = AdviceMode.ASPECTJ )
public class DataConfig {

	private static final Logger log = Logger.getLogger( DataConfig.class );
	
	@Inject
	private DataSource dataSource;

	/**
	 * Allows repositories to access RDBMS data using the JDBC API.
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate( dataSource );
	}

	/**
	 * Allows transactions to be managed against the RDBMS using the JDBC API.
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager( dataSource );
	}

	/**
	 * Embedded Data configuration.
	 * 
	 */
	@Configuration
	@Profile( "embedded" )
	static class Embedded {

		@Inject
		private Environment environment;
		
		@Bean( destroyMethod = "shutdown" )
		public DataSource dataSource() {
			log.debug( "dataSource : enter" );

			EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
			factory.setDatabaseName( "restaurant" );
			factory.setDatabaseType( EmbeddedDatabaseType.H2 );

			log.debug( "dataSource : exit" );
			return populateDatabase( factory.getDatabase() );
		}

		// internal helpers

		private EmbeddedDatabase populateDatabase( EmbeddedDatabase database ) {
			log.trace( "populateDatabase : enter" );

			new DatabaseUpgrader( database, environment ) {
				protected void addInstallChanges( DatabaseChangeSet changeSet ) {
					changeSet.add( SqlDatabaseChange.inResource( new ClassPathResource( "test-data-actual.sql", getClass() ) ) );
				}
			}.run();
			
			log.trace( "populateDatabase : exit" );
			return database;
		}

	}

	/**
	 * Development Data configuration.
	 * 
	 */
	@Configuration
	@Profile( "development" )
	static class Development {

		@Inject
		private Environment environment;

		@Bean( destroyMethod = "close" )
		public DataSource dataSource() {
			log.debug( "dataSource : enter" );

			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName( environment.getProperty( "database.driverClassName" ) );
			dataSource.setUrl( environment.getProperty( "database.url" ) );
			dataSource.setUsername( environment.getProperty( "database.username" ) );
			dataSource.setPassword( environment.getProperty( "database.password" ) );
			
			log.debug( "dataSource : exit" );
			return populateDatabase( dataSource );
		}

		// internal helpers

		private BasicDataSource populateDatabase( BasicDataSource database ) {
			log.trace( "populateDatabase : enter" );

			new DatabaseUpgrader( database, environment ) {
				protected void addInstallChanges( DatabaseChangeSet changeSet ) {
					log.trace( "addInstallChanges : enter" );
					
					changeSet.add( SqlDatabaseChange.inResource( new ClassPathResource( "test-data.sql", getClass() ) ) );

					log.trace( "addInstallChanges : exit" );
				}
			}.run();
			
			log.trace( "populateDatabase : enter" );
			return database;
		}

	}

	/**
	 * Production Data configuration.
	 * 
	 */
	@Configuration
	@Profile( "production" )
	static class Production {

		@Inject
		private Environment environment;

		@Bean( destroyMethod = "close" )
		public DataSource dataSource() {
			log.debug( "dataSource : enter" );

			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName( environment.getProperty( "database.driverClassName" ) );
			dataSource.setUrl( environment.getProperty( "database.url" ) );
			dataSource.setUsername( environment.getProperty( "database.username" ) );
			dataSource.setPassword( environment.getProperty( "database.password" ) );
			
			new DatabaseUpgrader( dataSource, environment ).run();

			log.debug( "dataSource : exit" );
			return dataSource;
		}

	}

}
