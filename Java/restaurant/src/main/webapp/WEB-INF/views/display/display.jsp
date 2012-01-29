<%@ page session="false" %>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!doctype html>

<html lang="en">

	<head>
		
		<meta charset="utf-8" />
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<title>Restaurant Display</title>
		
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />	
    	<meta name="description" content="Restaurant backend core services." />
    	<meta name="keywords" content="Exchanging Data Podcast Series" />

		<link rel="stylesheet" href="<c:url value="/resources/page.css" />" type="text/css" media="screen" />

	</head>
	
	<body>
	
		<header id="header">
		
			<h1 id="restaurant-name"></h1>
			
			<nav id="menu-nav">
				<ul></ul>
			</nav>
			
		</header>
	
		<div id="content-container">
	
			<div id="content">
				<section id="menus">
					
				</section>
				
				<aside id="happening">
					<header>
						<h4>What is happening on this page?</h4>
						<p>
							JQuery is making AJAX calls to the Restaurant back end web services.
						</p>
						
						<p>
							On initial entry to the page, all the restaurant data are retrieved for restaurant 1.  The restaurant name is set in the header of the page and he menu navigation above rendered from the returned results. The data for menu 1 is also rendered on the page so we are not looking at a blank page.  To load the initial data, a call is made to http://localhost:8080/restaurant/1.
						</p>
						
						<p>
							The menu navigation links above, when clicked are set to return to this page.  However, that action is intercepted and prevented from completing.  When the click event is captured, another AJAX call is made to the back end restaurant web services, but only the data for the corresponding menu is returned.  To get this specific data, a call is made to http://localhost:8080/restaurant/1/menus/{menuId}.  When each link is clicked, the current menu is faded out, the new menu is retrieved and rendered and then faded in.
						</p>
						
						<p>
							The section navigation links above, when clicked are set to return to this page.  However, that action is intercepted and prevented from completing.  When the click event is captured, another AJAX call is made to the back end restaurant web services, but only the data for the corresponding section is returned.  To get this specific data, a call is made to http://localhost:8080/restaurant/1/menus/{menuId}/section/{sectionId}.  When each link is clicked, the current section is faded out, the new section is retrieved and rendered and then faded in.
						</p>
					</header>
				</aside>
				
			</div>
	
			<div id="footer">
				<p><small>&copy; Copyright Your Name Here 2011. All Rights Reserved.</small></p>
			</div>
	
		</div>

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>

		<script id="menuNavTmpl" type="text/x-jquery-tmpl">
			<li>
				<a href="#" data-menu="${id}" class="menu-link">${name}</a>
			</li> 
		</script>
		
		<script id="menuTmpl" type="text/x-jquery-tmpl"> 
    		<article id="menu-${id}" class="menu">
				<header>
					<h2>${name}</h2>
							
					<nav id="section-nav">
						<ul>
							{{each(i, section) sections}}
							<li>
								<a href="#" data-section="${section.id}" class="section-link">${section.name}</a>
							</li>
			        		{{/each}}
						</ul>
					</nav>
				</header>
			</article>
		</script>

		<script id="sectionTmpl" type="text/x-jquery-tmpl"> 
				<div id="section-${id}" class="section">
					<header>
						<h3>${name}</h3>
					</header>

					<ul class="menuItems">
						{{each(i, menuItem) menuItems}}
						<li class="menuItem">
							${ menuItem.name } <span class="price">${ menuItem.price }</span><br />
							<span class="description">${ menuItem.description }</span>
						</li>
						{{/each}}
					</ul>
				</div>
		</script>

		<script>
			$.ajax({
			  url: '/restaurant/1',
			  contentType: 'application/json',
			  dataType: 'json',
			  success: function( data, textStatus, jqXHR ) {
				  $( "#restaurant-name" ).text( data.name );
				  
				  $.each( data.menus, function( i, menu ) {
					  $( '#menuNavTmpl' ).tmpl( menu ).appendTo( '#menu-nav ul' );
				  });
				  
				  var menu = data.menus[0];
				  $( '#menuTmpl' ).tmpl( menu ).appendTo( '#menus' );
				  $( '#sectionTmpl' ).tmpl( menu.sections[0] ).appendTo( '#menus article.menu' );
				  $( 'a.section-link' ).each( function() {
					  $( this ).attr( 'data-menu', menu.id );
				  });
			  },
			  error: function( jqXHR, textStatus, errorThrown ) {
				  console.dir( jqXHR );
				  console.debug( textStatus );
				  console.debug( errorThrown )
			  }
			});

			$( 'a.menu-link' ).live( 'click', function( e ) {
				console.debug( "a.menu-link.click : enter" );
				e.preventDefault();
					
				var menuId = $( this ).attr( 'data-menu' );
				console.debug( menuId );
					
				$( '#menus' ).fadeOut( 'slow', function() {
					$( this ).empty();

					$.ajax({
						  url: '/restaurant/1/menus/' + menuId,
						  contentType: 'application/json',
						  dataType: 'json',
						  success: function( data, textStatus, jqXHR ) {
							  $( '#menuTmpl' ).tmpl( data ).appendTo( '#menus' );
							  $( '#sectionTmpl' ).tmpl( data.sections[0] ).appendTo( '#menus article.menu' );
							  $( 'a.section-link' ).each( function() {
								  $( this ).attr( 'data-menu', data.id );
							  });
						  },
						  error: function( jqXHR, textStatus, errorThrown ) {
							  console.dir( jqXHR );
							  console.debug( textStatus );
							  console.debug( errorThrown )
						  }
					});

					$( this ).fadeIn( 'slow' );

				});
				
				console.debug( "a.menu-link.click : exit" );
			});
			  
			$( 'a.section-link' ).live( 'click', function( e ) {
				console.debug( "a.section-link.click : enter" );
				e.preventDefault();
					
				var menuId = $( this ).attr( 'data-menu' );
				console.debug( menuId );

				var sectionId = $( this ).attr( 'data-section' );
				console.debug( sectionId );
					
				$( '.section' ).fadeOut( 'slow', function() {
					$( '.section' ).remove();

					$.ajax({
						  url: '/restaurant/1/menus/' + menuId + '/sections/' + sectionId,
						  contentType: 'application/json',
						  dataType: 'json',
						  success: function( data, textStatus, jqXHR ) {
							  $( '#sectionTmpl' ).tmpl( data ).appendTo( '#menus article.menu' );
						  },
						  error: function( jqXHR, textStatus, errorThrown ) {
							  console.dir( jqXHR );
							  console.debug( textStatus );
							  console.debug( errorThrown )
						  }
					});

					$( '.section' ).fadeIn( 'slow' );

				});
				
				console.debug( "a.section-link.click : exit" );
			});

		</script>
		
	</body>
	
</html>