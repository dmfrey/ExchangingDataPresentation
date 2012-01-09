/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

/**
 * @author Daniel Frey
 *
 */
public interface RestaurantService {

	List<Restaurant> list();
	
	Restaurant findById( Long restaurantId );
	
	Restaurant createRestaurant( Restaurant restaurant );
	
	Restaurant updateRestaurant( Restaurant restaurant );
	
	List<Menu> listMenus( Long restaurantId );
	
	Menu findMenuById( Long menuId );
	
	Menu createMenu( Menu menu, Long restaurantId );
	
	Menu updateMenu( Menu menu );
	
	List<Section> listSections( Long menuId );
	
	Section findSectionById( Long sectionId );
	
	Section createSection( Section section, Long menuId );
	
	Section updateSection( Section section );
	
	List<MenuItem> listMenuItems( Long sectionId );
	
	MenuItem findMenuItemById( Long menuItemId );
	
	MenuItem createMenuItem( MenuItem menuItem, Long sectionId );
	
	MenuItem updateMenuItem( MenuItem menuItem );
	
}
