package org.dmfrey.restaurant.menu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;

/**
 * Configuration for Restaurant application @Components such as @Services, @Repositories, and @Controllers.
 * Loads externalized property values required to configure the various application properties.
 * Not much else here, as we rely on @Component scanning in conjunction with @Inject by-type autowiring.
 * 
 * @author Daniel Frey
 */
@Configuration
@ComponentScan( basePackages = "org.dmfrey.restaurant.menu", excludeFilters = { @Filter( Configuration.class ) } )
public class ComponentConfig {
	
	/**
	 * Properties to support the 'embedded' mode of operation.
	 */
	@Configuration
	@Profile( "embedded" )
	@PropertySource( "classpath:org/dmfrey/restaurant/menu/config/embedded.properties" )
	static class Embedded { }

	/**
	 * Properties to support the 'development' mode of operation.
	 */
	@Configuration
	@Profile( "development" )
	@PropertySource( "classpath:org/dmfrey/restaurant/menu/config/development.properties" )
	static class Development { }

	/**
	 * Properties to support the 'production' mode of operation.
	 */
	@Configuration
	@Profile( "production" )
	@PropertySource( "classpath:org/dmfrey/restaurant/menu/config/production.properties" )
	static class Production { }

}