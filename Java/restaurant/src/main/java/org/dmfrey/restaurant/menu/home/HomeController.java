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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping( value = "api", method = RequestMethod.GET )
	public String api( WebRequest request, Model model ) {
		log.info( "api : enter" );

		log.info( "api : exit" );
		return "api";
	}

	@RequestMapping( value = "display", method = RequestMethod.GET )
	public String display( WebRequest request, Model model ) {
		log.info( "display : enter" );

		log.info( "display : exit" );
		return "display";
	}

	@RequestMapping( method = RequestMethod.GET, produces = { "text/html" } )
	public String restaurants( WebRequest request, Model model ) {
		log.info( "restaurants : enter" );

		model.addAttribute( "restaurants", service.list() );
		model.addAttribute( "restaurant", new Restaurant() );
		
		log.info( "restaurants : exit" );
		return "restaurants";
	}

	@RequestMapping( method = RequestMethod.POST )
	public String newRestaurant( @Valid @ModelAttribute Restaurant restaurant, BindingResult result, Model model ) {
		log.info( "newRestaurant : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newRestaurant : exit, errors exist" );
			
			return "restaurants";
		}
		
		Restaurant created = service.createRestaurant( restaurant );
		
		log.info( "newRestaurant : exit" );
		return "redirect:/" + created.getId();
	}
	
	@RequestMapping( value = "{restaurantId}", method = RequestMethod.PUT )
	public String updateRestaurant( @PathVariable Long restaurantId, @Valid @ModelAttribute Restaurant restaurant, BindingResult result, Model model ) {
		log.info( "updateRestaurant : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateRestaurant : exit, errors exist" );
			
			return "restaurants";
		}
		
		Restaurant updated = service.findById( restaurantId );
		updated.setName( restaurant.getName() );
		
		updated = service.updateRestaurant( restaurant );
		
		log.info( "updateRestaurant : exit" );
		return "redirect:/" + updated.getId();
	}
	
	@RequestMapping( method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody List<Restaurant> restaurantsResource() {
		log.debug( "restaurantsResource : enter" );
		log.debug( "restaurantsResource : exit" );
		return service.list();
	}
	
	@RequestMapping( value = "{restaurantId}", method = RequestMethod.GET, produces = { "text/html" } )
	public String restaurant( @PathVariable Long restaurantId, WebRequest request, Model model ) {
		log.info( "restaurant : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		
		log.info( "restaurant : exit" );
		return "restaurants";
	}

	@RequestMapping( value = "{restaurantId}", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody Restaurant restaurantResource( @PathVariable Long restaurantId ) {
		log.debug( "restaurantResource : enter" );
		log.debug( "restaurantResource : exit" );
		return service.findById( restaurantId );
	}
	
	@RequestMapping( value = "{restaurantId}", method = RequestMethod.DELETE )
	public String deleteRestaurant( @PathVariable Long restaurantId, WebRequest request, Model model ) {
		log.info( "deleteRestaurant : enter" );

		service.deleteRestaurant( restaurantId );
		
		log.info( "deleteRestaurant : exit" );
		return "redirect:/";
	}

	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.GET, produces = { "text/html" } )
	public String menus( @PathVariable Long restaurantId, WebRequest request, Model model ) {
		log.info( "menus : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menus", service.listMenus( restaurantId ) );
		
		Menu menu = new Menu();
		menu.setRestaurant( restaurantId );
		model.addAttribute( "menu", menu );
		
		log.info( "menus : exit" );
		return "menus";
	}

	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody List<Menu> menusResource( @PathVariable Long restaurantId ) {
		log.debug( "menusResource : enter" );
		log.debug( "menusResource : exit" );
		return service.listMenus( restaurantId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.POST )
	public String newMenu( @PathVariable Long restaurantId, @Valid @ModelAttribute Menu menu, BindingResult result ) {
		log.info( "newMenu : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newMenu : exit, errors exist" );
			
			return "menus";
		}
		
		Menu created = service.createMenu( menu );
		
		log.info( "newMenu : exit" );
		return "redirect:/" + restaurantId + "/menus/" + created.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.PUT )
	public String updateMenu( @PathVariable Long restaurantId, @PathVariable Long menuId, @Valid @ModelAttribute Menu menu, BindingResult result ) {
		log.info( "updateMenu : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateMenu : exit, errors exist" );
			
			return "menus";
		}
		
		Menu updated = service.findMenuById( menuId );
		updated.setName( menu.getName() );
		
		updated = service.updateMenu( menu );
		
		log.info( "updateMenu : exit" );
		return "redirect:/" + restaurantId + "/menus/" + updated.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.GET, produces = { "text/html" } )
	public String menu( @PathVariable Long restaurantId, @PathVariable Long menuId, WebRequest request, Model model ) {
		log.info( "menu : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menu", service.findMenuById( menuId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		
		log.info( "menu : exit" );
		return "menus";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody Menu menuResource( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "menuResource : enter" );
		log.debug( "menuResource : exit" );
		return service.findMenuById( menuId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus", method = RequestMethod.DELETE )
	public String deleteMenu( @PathVariable Long restaurantId, @RequestParam Long menuId, WebRequest request, Model model ) {
		log.info( "deleteMenu : enter" );

		service.deleteMenu( menuId );
		
		log.info( "deleteMenu : exit" );
		return "redirect:/" + restaurantId + "/menus";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.GET, produces = { "text/html" } )
	public String sections( @PathVariable Long restaurantId, @PathVariable Long menuId, WebRequest request, Model model ) {
		log.info( "sections : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menu", service.findMenuById( menuId ) );
		model.addAttribute( "sections", service.listSections( menuId ) );
		
		Section section = new Section();
		section.setMenu( menuId );
		model.addAttribute( "section", section );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		
		log.info( "sections : exit" );
		return "sections";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody List<Section> sectionsResource( @PathVariable Long restaurantId, @PathVariable Long menuId ) {
		log.debug( "sectionsResource : enter" );
		log.debug( "sectionsResource : exit" );
		return service.listSections( menuId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.POST )
	public String newSection( @PathVariable Long restaurantId, @PathVariable Long menuId,  @Valid @ModelAttribute Section section, BindingResult result ) {
		log.info( "newSection : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newSection : exit, errors exist" );
			
			return "sections";
		}
		
		Section created = service.createSection( section );
		
		log.info( "newSection : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + created.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.PUT )
	public String updateSection( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @Valid @ModelAttribute Section section, BindingResult result ) {
		log.info( "updateSection : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateSection : exit, errors exist" );
			
			return "sections";
		}
		
		Section updated = service.findSectionById( sectionId );
		updated.setName( section.getName() );
		
		updated = service.updateSection( section );
		
		log.info( "updateSection : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + updated.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.GET, produces = { "text/html" } )
	public String section( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, WebRequest request, Model model ) {
		log.info( "section : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menu", service.findMenuById( menuId ) );
		model.addAttribute( "section", service.findSectionById( sectionId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		
		log.info( "section : exit" );
		return "sections";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody Section sectionResource( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "sectionResource : enter" );
		log.debug( "sectionResource : exit" );
		return service.findSectionById( sectionId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections", method = RequestMethod.DELETE )
	public String deleteSection( @PathVariable Long restaurantId, @PathVariable Long menuId, @RequestParam Long sectionId, WebRequest request, Model model ) {
		log.info( "deleteSection : enter" );

		service.deleteSection( sectionId );
		
		log.info( "deleteSection : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.GET, produces = { "text/html" } )
	public String menuItems( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, WebRequest request, Model model ) {
		log.info( "menuItems : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menu", service.findMenuById( menuId ) );
		model.addAttribute( "section", service.findSectionById( sectionId ) );
		model.addAttribute( "menuItems", service.listMenuItems( sectionId ) );
		
		MenuItem menuItem = new MenuItem();
		menuItem.setSection( sectionId );
		model.addAttribute( "menuItem", menuItem );

		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		
		log.info( "menuItems : exit" );
		return "menuItems";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody List<MenuItem> menuItemsResource( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId ) {
		log.debug( "menuItemsResource : enter" );
		log.debug( "menuItemsResource : exit" );
		return service.listMenuItems( sectionId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.POST )
	public String newMenuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @Valid @ModelAttribute MenuItem menuItem, BindingResult result ) {
		log.info( "newMenuItem : enter" );
		
		if( result.hasErrors() ) {
			log.info( "newMenuItem : exit, errors exist" );
			
			return "menuItems";
		}
		
		MenuItem created = service.createMenuItem( menuItem );
		
		log.info( "newMenuItem : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + sectionId + "/menuItems/" + created.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.PUT )
	public String updateMenuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId, @Valid @ModelAttribute MenuItem menuItem, BindingResult result ) {
		log.info( "updateMenuItem : enter" );
		
		if( result.hasErrors() ) {
			log.info( "updateMenuItem : exit, errors exist" );
			
			return "menuItems";
		}
		
		MenuItem updated = service.findMenuItemById( menuItemId );
		updated.setName( menuItem.getName() );
		updated.setDescription( menuItem.getDescription() );
		updated.setPrice( menuItem.getPrice() );
		
		updated = service.updateMenuItem( menuItem );
		
		log.info( "updateMenuItem : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + sectionId + "/menuItems/" + updated.getId();
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.GET, produces = { "text/html" } )
	public String menuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId, WebRequest request, Model model ) {
		log.info( "menuItem : enter" );

		model.addAttribute( "restaurant", service.findById( restaurantId ) );
		model.addAttribute( "menu", service.findMenuById( menuId ) );
		model.addAttribute( "section", service.findSectionById( sectionId ) );
		model.addAttribute( "menuItem", service.findMenuItemById( menuItemId ) );
		
		model.addAttribute( "restaurantId", restaurantId );
		model.addAttribute( "menuId", menuId );
		model.addAttribute( "sectionId", sectionId );
		model.addAttribute( "menuItemId", menuItemId );
		
		log.info( "menuItem : exit" );
		return "menuItems";
	}

	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId}", method = RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" } )
	public @ResponseBody MenuItem menuItemResource( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @PathVariable Long menuItemId ) {
		log.debug( "menuItemResource : enter" );
		log.debug( "menuItemResource : exit" );
		return service.findMenuItemById( menuItemId );
	}
	
	@RequestMapping( value = "{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems", method = RequestMethod.DELETE )
	public String deleteMenuItem( @PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long sectionId, @RequestParam Long menuItemId, WebRequest request, Model model ) {
		log.info( "deleteMenuItem : enter" );

		service.deleteMenuItem( menuItemId );
		
		log.info( "deleteMenuItem : exit" );
		return "redirect:/" + restaurantId + "/menus/" + menuId + "/sections/" + sectionId + "/menuItems";
	}

}
