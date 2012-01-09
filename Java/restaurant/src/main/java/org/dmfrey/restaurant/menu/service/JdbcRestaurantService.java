/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author Daniel Frey
 *
 */
@Repository
public class JdbcRestaurantService implements RestaurantService {

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#list()
	 */
	@Override
	public List<Restaurant> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findById(java.lang.Long)
	 */
	@Override
	public Restaurant findById( Long restaurantId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createRestaurant(org.dmfrey.restaurant.menu.service.Restaurant)
	 */
	@Override
	public Restaurant createRestaurant( Restaurant restaurant ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createMenu(org.dmfrey.restaurant.menu.service.Menu, java.lang.Long)
	 */
	@Override
	public Menu createMenu( Menu menu, Long restaurantId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listMenus(java.lang.Long)
	 */
	@Override
	public List<Menu> listMenus( Long restaurantId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findMenuById(java.lang.Long)
	 */
	@Override
	public Menu findMenuById( Long menuId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateRestaurant(org.dmfrey.restaurant.menu.service.Restaurant)
	 */
	@Override
	public Restaurant updateRestaurant( Restaurant restaurant ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateMenu(org.dmfrey.restaurant.menu.service.Menu)
	 */
	@Override
	public Menu updateMenu( Menu menu ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listSections(java.lang.Long)
	 */
	@Override
	public List<Section> listSections( Long menuId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findSectionById(java.lang.Long)
	 */
	@Override
	public Section findSectionById( Long sectionId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createSection(org.dmfrey.restaurant.menu.service.Section, java.lang.Long)
	 */
	@Override
	public Section createSection( Section section, Long menuId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateSection(org.dmfrey.restaurant.menu.service.Section)
	 */
	@Override
	public Section updateSection( Section section ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#listMenuItems(java.lang.Long)
	 */
	@Override
	public List<MenuItem> listMenuItems( Long sectionId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#findMenuItemById(java.lang.Long)
	 */
	@Override
	public MenuItem findMenuItemById( Long menuItemId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#createMenuItem(org.dmfrey.restaurant.menu.service.MenuItem, java.lang.Long)
	 */
	@Override
	public MenuItem createMenuItem( MenuItem menuItem, Long sectionId ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dmfrey.restaurant.menu.service.RestaurantService#updateMenuItem(org.dmfrey.restaurant.menu.service.MenuItem)
	 */
	@Override
	public MenuItem updateMenuItem( MenuItem menuItem ) {
		// TODO Auto-generated method stub
		return null;
	}

}
