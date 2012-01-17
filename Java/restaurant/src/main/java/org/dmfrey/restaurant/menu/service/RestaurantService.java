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

	/**
	 * @return
	 */
	List<Restaurant> list();
	
	/**
	 * @param restaurantId
	 * @return
	 */
	Restaurant findById( Long restaurantId );
	
	/**
	 * @param restaurant
	 * @return
	 */
	Restaurant createRestaurant( Restaurant restaurant );
	
	/**
	 * @param restaurant
	 * @return
	 */
	Restaurant updateRestaurant( Restaurant restaurant );
	
	/**
	 * @param restaurantId
	 */
	void deleteRestaurant( Long restaurantId );
	
	/**
	 * @param restaurantId
	 * @return
	 */
	List<Menu> listMenus( Long restaurantId );
	
	/**
	 * @param menuId
	 * @return
	 */
	Menu findMenuById( Long menuId );
	
	/**
	 * @param menu
	 * @param restaurantId
	 * @return
	 */
	Menu createMenu( Menu menu, Long restaurantId );
	
	/**
	 * @param menu
	 * @return
	 */
	Menu updateMenu( Menu menu );
	
	/**
	 * @param menuId
	 * @return
	 */
	List<Section> listSections( Long menuId );
	
	/**
	 * @param menuId
	 */
	void deleteMenu( Long menuId );

	/**
	 * @param sectionId
	 * @return
	 */
	Section findSectionById( Long sectionId );
	
	/**
	 * @param section
	 * @param menuId
	 * @return
	 */
	Section createSection( Section section, Long menuId );
	
	/**
	 * @param section
	 * @return
	 */
	Section updateSection( Section section );
	
	/**
	 * @param sectionId
	 */
	void deleteSection( Long sectionId );

	/**
	 * @param sectionId
	 * @return
	 */
	List<MenuItem> listMenuItems( Long sectionId );
	
	/**
	 * @param menuItemId
	 * @return
	 */
	MenuItem findMenuItemById( Long menuItemId );
	
	/**
	 * @param menuItem
	 * @param sectionId
	 * @return
	 */
	MenuItem createMenuItem( MenuItem menuItem, Long sectionId );
	
	/**
	 * @param menuItem
	 * @return
	 */
	MenuItem updateMenuItem( MenuItem menuItem );
	
	/**
	 * @param menuItemId
	 */
	void deleteMenuItem( Long menuItemId );

}
