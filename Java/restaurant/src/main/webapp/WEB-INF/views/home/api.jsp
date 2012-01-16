<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<h2>Restaurant Web Service API</h2>

<article>

	<p>
		This page defines the Web Service Application Programming Interface(API) for the Restaurant web application. 
		<br /><br />
	</p>
	
	<p>
		The end points listed below can be accessed by changing the Content-type Request Header to one of the following:
	</p>
	<ul>
		<li>Content-Type: application/json - Returns a JSON representation of the Restaurant data model</li>
		<li>Content-Type: application/xml - Returns a XML representation of the Restaurant data model</li>
	</ul>
	<p><br /><br /></p>
</article>

<article>
	<header>
		<h3>Web Service Testing Tools</h3>
	</header>	
	
	<p>
		These end points can be tested in a number of ways.  Here are a few of the way in which you can test your web services
		<br /><br />
	</p>
	
	<p>
		cURL - <a href="http://en.wikipedia.org/wiki/CURL">cURL Wikipedia Page</a>
	</p>
	<p>
		Once installed open up a command prompt and enter the following (feel free to change the URL to any of the end points listed below):<br />
		<code>
			curl -i -X GET -H "Content-type: application/json" http://localhost:8080/restaurant/1
		</code>
		<br /><br />
		The above shows the JSON response.  To see the XML response, change the Content-type to application/xml.<br />
		<code>
			curl -i -X GET -H "Content-type: application/xml" http://localhost:8080/restaurant/1
		</code>
		<br /><br />
	</p>

	<p>
		Firebug - <a href="http://getfirebug.com/">Firebug Web Page</a>
	<p>
	<p>
		Firebug is a great tool for Mozilla Firefox for debugging your web pages.  It has native support for displaying and formatting JSON and XML objects in Javascript
		<br /><br />
	</p>
	
	<p>
		Simple REST Client for Google Chrome - Search 'Simple REST Client' in the Google Chrome Web Store 
	</p>
	<p>
		This plugin works much like cURL on the command line, but can be executed in the Google Chrome Web Browser
		<br /><br />
	</p>
	
	<p>
		A word about testing...
	</p>
	<p>
		Google Chrome's Inspector and Firebug for Mozilla Firefox are great developer tools and every Web Developer should know about them and how to use them.  However, you should still test your web applications in Internet Explorer and Safari on the Mac.  IE's developer tools are still somewhat lacking and IE9 is becomming more standards compliant, but it still has its quirks.<br /><br />
		TEST EVERYWHERE!!
		<br /><br />
	</p>
	
</article>
<p><br /><br /></p>

<article>

	<header>
		<h3>Restaurant Web Service API Definition</h3>
	</header>
	<p><br /></p>

	<h5>Restaurant End Points</h5>
	<ul>
		<li>http://HOST:PORT/restaurant - returns a list of all the restaurants defined, menus are not included</li>
		<li>http://HOST:PORT/restaurant/{restaurantId} - returns an individual restaurant, menus are included</li>
	</ul>
	<p><br /><br /></p>
	
	<h5>Menu End Points</h5>
	<ul>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus - returns a list of menus for a restaurant, sections are included</li>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus/{menuId} - returns a menu for a restaurant, sections are included</li>
	</ul>
	<p><br /><br /></p>
	
	<h5>Section End Points</h5>
	<ul>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus/{menuId}/sections - returns a list of sections for a menu, menu items are included</li>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus/{menuId}/sections/{sectionId} - returns a section for a menu, menu items are included</li>
	</ul>
	<p><br /><br /></p>
	
	<h5>Menu Item End Points</h5>
	<ul>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems - returns a list of menuItems for a section</li>
		<li>http://HOST:PORT/restaurant/{restaurantId}/menus/{menuId}/sections/{sectionId}/menuItems/{menuItemId} - returns a menuItem for a section</li>
	</ul>
	<p><br /><br /></p>
	
</article>
