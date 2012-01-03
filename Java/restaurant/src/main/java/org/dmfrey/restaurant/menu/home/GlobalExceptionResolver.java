package org.dmfrey.restaurant.menu.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * Handles Exceptions that are not handled at the Application Controller level.
 * Applies the global exception handling policy for the application.
 * An AccountNotConnectedException results in a 412.
 * An EmptyResultDataAccessException results in a 404.
 * Delegates to {@link ResponseStatusExceptionResolver} as a first fallback.
 * Delegates to {@link DefaultHandlerExceptionResolver} as a final fallback.
 * 
 * @author Daniel Frey
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger( GlobalExceptionResolver.class );
	
	private final ResponseStatusExceptionResolver annotatedExceptionHandler = new ResponseStatusExceptionResolver();
	
	private final DefaultHandlerExceptionResolver defaultExceptionHandler = new DefaultHandlerExceptionResolver();
		
	public ModelAndView resolveException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {
		try {
			if( ex instanceof EmptyResultDataAccessException ) {
				return handleEmptyResultDataAccessException( (EmptyResultDataAccessException) ex, request, response, handler );
			}
		} catch( Exception e ) {
			logger.warn( "Handling of [" + ex.getClass().getName() + "] resulted in Exception", ex );
			return null;
		}
		
		ModelAndView result = annotatedExceptionHandler.resolveException( request, response, handler, ex );
		if( result != null ) {
			return result;
		}
		
		return defaultExceptionHandler.resolveException( request, response, handler, ex );
	}

	// internal helpers
	
	private ModelAndView handleEmptyResultDataAccessException( EmptyResultDataAccessException ex, HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		response.sendError( HttpServletResponse.SC_NOT_FOUND );

		return new ModelAndView();
	}

}