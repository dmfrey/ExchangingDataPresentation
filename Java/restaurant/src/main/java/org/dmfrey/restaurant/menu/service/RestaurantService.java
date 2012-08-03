/**
 * 
 */
package org.dmfrey.restaurant.menu.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Frey
 *
 */
@Service
public class RestaurantService {

	private static final Logger log = Logger.getLogger( RestaurantService.class );
	
	private RestaurantRepository restaurantRepository;
	private MenuRepository menuRepository;
	private SectionRepository sectionRepository;
	private MenuItemRepository menuItemRepository;
	
	@Inject
	public RestaurantService( RestaurantRepository restaurantRepository, MenuRepository menuRepository, SectionRepository sectionRepository, MenuItemRepository menuItemRepository ) {
		log.trace( "initialize : enter" );
		
		this.restaurantRepository = restaurantRepository;
		this.menuRepository = menuRepository;
		this.sectionRepository = sectionRepository;
		this.menuItemRepository = menuItemRepository;

		log.trace( "initialize : exit" );
	}
	
	/**
	 * @return
	 */
	public List<Restaurant> list() {
		log.debug( "list : enter" );
		log.debug( "list : exit" );
		return restaurantRepository.findAll();
	}
	
	/**
	 * @param restaurantId
	 * @return
	 */
	public Restaurant findById( Long restaurantId ) {
		log.debug( "findById : enter" );

		Restaurant restaurant = restaurantRepository.findOne( restaurantId );
		if( null != restaurant && log.isTraceEnabled() ) {
			log.trace( "findById : restaurant=" + restaurant.toString() );
		}
		
		log.debug( "findById : enter" );
		return restaurant;
	}
	
	/**
	 * @param restaurant
	 * @return
	 */
	@Transactional
	public Restaurant createRestaurant( Restaurant restaurant ) {
		log.debug( "createRestaurant : enter" );

		Restaurant created = restaurantRepository.save( restaurant );
		if( null != created && log.isTraceEnabled() ) {
			log.trace( "createRestaurant : created=" + created.toString() );
		}
		
		log.debug( "createRestaurant : enter" );
		return created;
	}
	
	/**
	 * @param restaurant
	 * @return
	 */
	@Transactional
	public Restaurant updateRestaurant( Restaurant restaurant ) {
		log.debug( "updateRestaurant : enter" );

		Restaurant updated = restaurantRepository.save( restaurant );
		if( null != updated && log.isTraceEnabled() ) {
			log.trace( "updateRestaurant : updated=" + updated.toString() );
		}
		
		log.debug( "updateRestaurant : enter" );
		return updated;
	}
	
	/**
	 * @param restaurantId
	 */
	@Transactional
	public void deleteRestaurant( Long restaurantId ) {
		log.debug( "deleteRestaurant : enter" );

		restaurantRepository.delete( restaurantId );

		log.debug( "deleteRestaurant : exit" );
	}
	
	/**
	 * @param restaurantId
	 * @return
	 */
	public List<Menu> listMenus( Long restaurantId ) {
		return menuRepository.findByRestaurant( restaurantId );
	}
	
	/**
	 * @param menuId
	 * @return
	 */
	public Menu findMenuById( Long menuId ) {
		return menuRepository.findOne( menuId );
	}
	
	/**
	 * @param menu
	 * @param restaurantId
	 * @return
	 */
	@Transactional
	public Menu createMenu( Menu menu ) {
		return menuRepository.save( menu );
	}
	
	/**
	 * @param menu
	 * @return
	 */
	@Transactional
	public Menu updateMenu( Menu menu ) {
		return menuRepository.save( menu );
	}
	
	/**
	 * @param menuId
	 */
	@Transactional
	public void deleteMenu( Long menuId ) {
		menuRepository.delete( menuId );
	}

	/**
	 * @param menuId
	 * @return
	 */
	public List<Section> listSections( Long menuId ) {
		return sectionRepository.findByMenu( menuId );
	}
	
	/**
	 * @param sectionId
	 * @return
	 */
	public Section findSectionById( Long sectionId ) {
		return sectionRepository.findOne( sectionId );
	}
	
	/**
	 * @param section
	 * @param menuId
	 * @return
	 */
	@Transactional
	public Section createSection( Section section ) {
		return sectionRepository.save( section );
	}
	
	/**
	 * @param section
	 * @return
	 */
	@Transactional
	public Section updateSection( Section section ) {
		return sectionRepository.save( section );
	}

	/**
	 * @param sectionId
	 */
	@Transactional
	public void deleteSection( Long sectionId ) {
		sectionRepository.delete( sectionId );
	}

	/**
	 * @param sectionId
	 * @return
	 */
	public List<MenuItem> listMenuItems( Long sectionId ) {
		return menuItemRepository.findBySection( sectionId );
	}
	
	/**
	 * @param menuItemId
	 * @return
	 */
	public MenuItem findMenuItemById( Long menuItemId ) {
		return menuItemRepository.findOne( menuItemId );
	}
	
	/**
	 * @param menuItem
	 * @param sectionId
	 * @return
	 */
	@Transactional
	public MenuItem createMenuItem( MenuItem menuItem ) {
		return menuItemRepository.save( menuItem );
	}
	
	/**
	 * @param menuItem
	 * @return
	 */
	@Transactional
	public MenuItem updateMenuItem( MenuItem menuItem ) {
		return menuItemRepository.save( menuItem );
	}
	
	/**
	 * @param menuItemId
	 */
	@Transactional
	public void deleteMenuItem( Long menuItemId ) {
		menuItemRepository.delete( menuItemId );
	}

}
