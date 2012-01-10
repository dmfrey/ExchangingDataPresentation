/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

/**
 * @author Daniel Frey
 *
 */
@SuppressWarnings( "serial" )
public abstract class RestaurantException extends Exception {

	public RestaurantException( String message ) {
		super( message );
	}

	public RestaurantException( String message, Throwable cause ) {
		super( message, cause );
	}
	
}
