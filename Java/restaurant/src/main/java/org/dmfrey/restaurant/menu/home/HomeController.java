/**
 * 
 */
package org.dmfrey.restaurant.menu.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * @author dmfrey
 *
 */
@Controller
@RequestMapping( "/" )
public class HomeController {

	private static final Logger log = Logger.getLogger( HomeController.class );
	
	@RequestMapping( method = RequestMethod.GET )
	public String home( WebRequest request, Model model ) {
		log.info( "home : enter" );

		log.info( "home : exit" );
		return "home";
	}

}
