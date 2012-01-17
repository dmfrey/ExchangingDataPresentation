<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<s:url value="/${ restaurant.id }" var="back_url" />
<a href="${ back_url }">Back</a>

<h2>Menu Item</h2>
