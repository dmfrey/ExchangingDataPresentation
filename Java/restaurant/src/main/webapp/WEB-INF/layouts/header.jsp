<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h1>
	<a title="Restaurant" href="<c:url value="/" />">Restaurant</a>
</h1>

<nav>
	<ul>
		<li>
			<s:url value="/display" var="display_url" />
			<a href="${ display_url }">JQuery Display</a>
		</li>
		<li>
			<s:url value="/api" var="api_url" />
			<a href="${ api_url }">API</a>
		</li>
	</ul>
</nav>