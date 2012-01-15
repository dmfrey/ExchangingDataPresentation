/**
 * 
 */
package org.dmfrey.restaurant.menu.home;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.dmfrey.restaurant.menu.service.Menu;
import org.dmfrey.restaurant.menu.service.MenuItem;
import org.dmfrey.restaurant.menu.service.Restaurant;
import org.dmfrey.restaurant.menu.service.RestaurantService;
import org.dmfrey.restaurant.menu.service.Section;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * @author dmfrey
 *
 */
@Controller
@RequestMapping( "/" )
public class HomeController {

	private static final Logger log = Logger.getLogger( HomeController.class );
	
	private RestaurantService service;
	
	@Inject
	public HomeController( RestaurantService service ) {
		this.service = service;
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public String restaurants( WebRequest request, Model model ) {
		log.info( "restaurants : enter" );

		model.addAttribute( "restaurants", service.list() );
		model.addAttribute( "restaurant", new Restaurant() );
		
		log.info( "restaurants : exit" );
		return "restaurants";
	}

	@RequestMapping( method = RequestMethod.POST )
	public String newRestaurant( @Valid @ModelAttribute Restaurant restaurant, BindingResult result ) {
		log.info( "newRestaurant : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newRestaurant : exit, errors exist" );
			
			return "restaurants";
		}
		
		service.createRestaurant( restaurant );
		
		log.info( "newRestaurant : exit" );
		return "redirect:/";
	}
	
	@RequestMapping( method = RequestMethod.PUT )
	public String updateRestaurant( @Valid @ModelAttribute Restaurant restaurant, BindingResult result ) {
		log.info( "updateRestaurant : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateRestaurant : exit, errors exist" );
			
			return "restaurants";
		}
		
		service.updateRestaurant( restaurant );
		
		log.info( "updateRestaurant : exit" );
		return "redirect:/";
	}
	
	@RequestMapping( method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody List<Restaurant> restaurantsJson() {
		log.debug( "restaurantsJson : enter" );
		log.debug( "restaurantsJson : exit" );
		return service.list();
	}
	
	@RequestMapping( method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody List<Restaurant> restaurantsXml() {
		log.debug( "restaurantsXml : enter" );
		log.debug( "restaurantsXml : exit" );
		return service.list();
	}

	@RequestMapping( value = "{restaurantId}", method = RequestMethod.GET )
	public String restaurant( @PathVariable Long restaurantId, WebRequest request, Model model ) {
		log.info( "restaurant : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		
		log.info( "restaurant : exit" );
		return "restaurant";
	}

	@RequestMapping( value = "{restaurantId}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody Restaurant restaurantJson( @PathVariable Long restaurantId ) {
		log.debug( "restaurantJson : enter" );
		log.debug( "restaurantJson : exit" );
		return service.findById( restaurantId );
	}
	
	@RequestMapping( value = "{restaurantId}", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody Restaurant restaurantXml( @PathVariable Long restaurantId ) {
		log.debug( "restaurantXml : enter" );
		log.debug( "restaurantXml : exit" );
		return service.findById( restaurantId );
	}

	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.GET )
	public String menus( @PathVariable Long restaurantId, WebRequest request, Model model ) {
		log.info( "menus : enter" );

		model.addAttribute( "menus", service.listMenus( restaurantId ) );
		model.addAttribute( "menu", new Menu() );
		
		log.info( "menus : exit" );
		return "menus";
	}

	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody List<Menu> menusJson( @PathVariable Long restaurantId ) {
		log.debug( "menusJson : enter" );
		log.debug( "menusJson : exit" );
		return service.listMenus( restaurantId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody List<Menu> menusXml( @PathVariable Long restaurantId ) {
		log.debug( "menusXml : enter" );
		log.debug( "menusXml : exit" );
		return service.listMenus( restaurantId );
	}

	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.POST )
	public String newMenu( @PathVariable Long restaurantId, @Valid @ModelAttribute Menu menu, BindingResult result ) {
		log.info( "newMenu : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newMenu : exit, errors exist" );
			
			return "menus";
		}
		
		service.createMenu( menu, restaurantId );
		
		log.info( "newMenu : exit" );
		return "redirect:/" + restaurantId + "/menus";
	}
	
	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.PUT )
	public String updateMenu( @PathVariable Long restaurantId, @Valid @ModelAttribute Menu menu, BindingResult result ) {
		log.info( "updateMenu : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateMenu : exit, errors exist" );
			
			return "menus";
		}
		
		service.updateMenu( menu );
		
		log.info( "updateMenu : exit" );
		return "redirect:/" + restaurantId + "/menus";
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.GET )
	public String menu( @PathVariable Long restaurantId, @PathVariable Long menuId, WebRequest request, Model model ) {
		log.info( "menu : enter" );

		model.addAttribute( "menu", service.findMenuById( menuId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		
		log.info( "menu : exit" );
		return "menu";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody Menu menuJson( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "menuJson : enter" );
		log.debug( "menuJson : exit" );
		return service.findMenuById( menuId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody Menu menuXml( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "menuXml : enter" );
		log.debug( "menuXml : exit" );
		return service.findMenuById( menuId );
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.GET )
	public String sections( @PathVariable Long restaurantId, @PathVariable Long menuId, WebRequest request, Model model ) {
		log.info( "sections : enter" );

		model.addAttribute( "sections", service.listSections( menuId ) );
		model.addAttribute( "section", new Section() );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		
		log.info( "sections : exit" );
		return "sections";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody List<Section> sectionsJson( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "sectionsJson : enter" );
		log.debug( "sectionsJson : exit" );
		return service.listSections( menuId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody List<Section> sectionsXml( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "sectionsXml : enter" );
		log.debug( "sectionsXml : exit" );
		return service.listSections( menuId );
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.POST )
	public String newSection( @PathVariable Long restaurantId, @PathVariable Long menuId,  @Valid @ModelAttribute Section section, BindingResult result ) {
		log.info( "newSection : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newSection : exit, errors exist" );
			
			return "sections";
		}
		
		service.createSection( section, menuId );
		
		log.info( "newSection : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections";
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.PUT )
	public String updateSection( @PathVariable Long restaurantId, @PathVariable Long menuId,  @Valid @ModelAttribute Section section, BindingResult result ) {
		log.info( "updateSection : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateSection : exit, errors exist" );
			
			return "sections";
		}
		
		service.updateSection( section );
		
		log.info( "updateSection : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections";
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.GET )
	public String section( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, WebRequest request, Model model ) {
		log.info( "section : enter" );

		model.addAttribute( "section", service.findSectionById( sectionId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		
		log.info( "section : exit" );
		return "section";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody Section sectionJson( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "sectionJson : enter" );
		log.debug( "sectionJson : exit" );
		return service.findSectionById( sectionId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody Section sectionXml( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "sectionXml : enter" );
		log.debug( "sectionXml : exit" );
		return service.findSectionById( sectionId );
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.GET )
	public String menuItems( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, WebRequest request, Model model ) {
		log.info( "menuItems : enter" );

		model.addAttribute( "menuItems", service.listMenuItems( sectionId ) );
		model.addAttribute( "menuItem", new MenuItem() );

		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		
		log.info( "menuItems : exit" );
		return "menuItems";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody List<MenuItem> menuItemsJson( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "menuItemsJson : enter" );
		log.debug( "menuItemsJson : exit" );
		return service.listMenuItems( sectionId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody List<MenuItem> menuItemsXml( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "menuItemsXml : enter" );
		log.debug( "menuItemsXml : exit" );
		return service.listMenuItems( sectionId );
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.POST )
	public String newMenuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @Valid @ModelAttribute MenuItem menuItem, BindingResult result ) {
		log.info( "newMenuItem : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newMenuItem : exit, errors exist" );
			
			return "menuItems";
		}
		
		service.createMenuItem( menuItem, sectionId );
		
		log.info( "newMenuItem : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + sectionId + "/menuItems";
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.PUT )
	public String updateMenuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @Valid @ModelAttribute MenuItem menuItem, BindingResult result ) {
		log.info( "updateMenuItem : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateMenuItem : exit, errors exist" );
			
			return "menuItems";
		}
		
		service.updateMenuItem( menuItem );
		
		log.info( "updateMenuItem : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + sectionId + "/menuItems";
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.GET )
	public String menuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId, WebRequest request, Model model ) {
		log.info( "menuItem : enter" );

		model.addAttribute( "menuItem", service.findMenuItemById( menuItemId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		model.addAttribute( "menuItem", menuItemId );
		
		log.info( "menuItem : exit" );
		return "menuItem";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
	public @ResponseBody MenuItem menuItemJson( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId ) {
		log.debug( "menuItemJson : enter" );
		log.debug( "menuItemJson : exit" );
		return service.findMenuItemById( menuItemId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.GET, consumes = { "application/xml", "text/xml" }, produces = { "application/xml", "text/xml" } )
	public @ResponseBody MenuItem menuItemXml( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId ) {
		log.debug( "menuItemXml : enter" );
		log.debug( "menuItemXml : exit" );
		return service.findMenuItemById( menuItemId );
	}

}
