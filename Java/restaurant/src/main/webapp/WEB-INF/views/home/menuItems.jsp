<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<s:url value="/${ restaurant.id }" var="back_url" />
<a href="${ back_url }">Back to ${ restaurant.name }</a>

<s:url value="/${ restaurant.id }/menus" var="menus_back_url" />
<a href="${ menus_back_url }">Back to ${ menu.name }</a>

<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="sections_back_url" />
<a href="${ sections_back_url }">Back to ${ section.name }</a>

<c:if test="${ empty menuItems }">
	<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems" var="menuItems_back_url" />
	<a href="${ menuItems_back_url }">Back</a>
</c:if>

<h2>Menu Items</h2>

<c:choose>
	<c:when test="${ empty menuItem.id }">
		<c:set var="button_label" value="Create" />
		<c:set var="method" value="post" />
	</c:when>
	<c:otherwise>
		<c:set var="button_label" value="Update" />
		<c:set var="method" value="put" />
	</c:otherwise>
</c:choose>

<form:form method="${ method }" modelAttribute="menuItem">
	<form:hidden path="id" />
	<form:hidden path="section" />
	
	<fieldset>
		<legend>Section Form</legend>
		<c:if test="${ ! empty menuItem.id }">
		<p>
			<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems" var="new_url" />
			<a href="${ new_url }">New</a>
		</p>
		</c:if>
		
		<form:errors path="*" cssClass="error">
			<div>
				Errors Exist
			</div>
		</form:errors>
		
		<form:label path="name">Name <form:errors path="name" cssClass="error" /></form:label>
		<form:input path="name" maxlength="128" />
		<form:label path="description">Description <form:errors path="description" cssClass="error" /></form:label>
		<form:input path="description" maxlength="512" />
		<form:label path="price">Price <form:errors path="price" cssClass="error" /></form:label>
		<form:input path="price" />
	</fieldset>
		
	<p>
		<button type="submit">
			${ button_label }
		</button>
	</p>

</form:form>

<c:if test="${ ! empty menuItems }">
<h3>Menu Item List</h3>
<section id="list">

	<header>
		<ul>
			<li></li>
			<li>Name</li>
			<li>Description</li>
			<li>Price</li>
			<li></li>
		</ul>
	</header>

	<c:forEach items="${ menuItems }" var="menuItem">
	<article>
		<div>
			<span class="controls">
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems/${ menuItem.id }" var="edit_url" />
				<a href="${ edit_url }">Edit</a>
			</span>
			<span>${ menuItem.name }</span>
			<span>${ menuItem.description }</span>
			<span>${ menuItem.price }</span>
			<span class="rowForm">
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems" var="delete_url" />
				<form method="post" action="${ delete_url }">
					<input type="hidden" name="_method" value="delete" />
					<input type="hidden" name="menuItemId" value="${ menuItem.id }" />
					<p>
						<button type="submit">
							Delete
						</button>
					</p>
				</form>
			</span>
		</div>
	</article>
	</c:forEach>

</section>
</c:if>