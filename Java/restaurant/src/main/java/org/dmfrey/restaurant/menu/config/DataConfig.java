package org.dmfrey.restaurant.menu.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.dmfrey.restaurant.menu.database.DatabaseUpgrader;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.versioned.DatabaseChangeSet;
import org.springframework.jdbc.versioned.SqlDatabaseChange;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
@ImportResource( "classpath:META-INF/applicationContext-jpa.xml" )
@EnableTransactionManagement( mode = AdviceMode.ASPECTJ )
public class DataConfig {

	private static final Logger log = Logger.getLogger( DataConfig.class );
	
	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;

	/**
	 * Allows repositories to access RDBMS data using the JDBC API.
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate( dataSource );
	}

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource( dataSource );
        emf.setPersistenceProviderClass( HibernatePersistence.class );
        
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter() {
            {
            	setDatabase( Database.H2 );
            	setDatabasePlatform( environment.getProperty( "hibernate.dialect" ) );
            	setShowSql( new Boolean( environment.getProperty( "hibernate.jpa.showSql" ) ) );
				setGenerateDdl( new Boolean( environment.getProperty( "hibernate.jpa.generateDDL" ) ) );
            }
        };
        emf.setJpaVendorAdapter( jpaVendorAdapter );
         
        Properties jpaProperties = new Properties();
//        jpaProperties.put( "hibernate.hbm2ddl.auto", environment.getProperty( "hibernate.hbm2ddl.auto" ) );
        jpaProperties.put( "hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider" );
        jpaProperties.put( "hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy" );
        emf.setJpaProperties( jpaProperties );
        
        emf.setPackagesToScan( 
            "org.dmfrey.restaurant.menu.service"
        );
                
        return emf;
    }

	/**
	 * Allows transactions to be managed against the RDBMS using the JDBC API.
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
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
