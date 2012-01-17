<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<c:if test="${ empty restaurants }">
	<s:url value="/" var="back_url" />
	<a href="${ back_url }">Back</a>
</c:if>

<h2>Restaurants</h2>

<c:choose>
	<c:when test="${ empty restaurant.id }">
		<c:set var="button_label" value="Create" />
		<c:set var="method" value="post" />
	</c:when>
	<c:otherwise>
		<c:set var="button_label" value="Update" />
		<c:set var="method" value="put" />
	</c:otherwise>
</c:choose>

<form:form method="${ method }" modelAttribute="restaurant">
	<form:hidden path="id" />
	
	<fieldset>
		<legend>Restaurant Form</legend>
		<c:if test="${ ! empty restaurant.id }">
		<p>
			<s:url value="/" var="new_url" />
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
	</fieldset>
		
	<p>
		<button type="submit">
			${ button_label }
		</button>
	</p>

	<c:if test="${ ! empty restaurant.id }">
	<section>

		<header>
			<h2>Menus</h2>
		</header>
		
		<c:forEach items="${ restaurant.menus }" var="menu">
		<article>
			<header>
				<h3>
					<s:url value="/${ restaurant.id }/menus/${ menu.id }" var="edit_menu_url" />
					${ menu.name } <span><a href="${ edit_menu_url }">(edit)</a></span>
				</h3>
			</header>
		
			<c:forEach items="${ menu.sections }" var="section">
			<div>
				<header>
					<h5>
						<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }" var="edit_section_url" />
						${ section.name } <span><a href="${ edit_section_url }">(edit)</a></span>
					</h5>
				</header>
				
				<ul>
					<c:forEach items="${ section.menuItems }" var="menuItem">
					<li>
						<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems/${ menuItem.id }" var="edit_menuItem_url" />
						<a href="${ edit_menuItem_url }">${ menuItem.name }</a>
						<span>${ menuItem.description }</span>
						<span>${ menuItem.price }</span>
					</li>
					</c:forEach>
				</ul>
				
				<footer>
					<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems" var="add_menuItem_url" />
					<a href="${ add_menuItem_url }">Add/Edit Menu Items</a>
				</footer>
			</div>
			</c:forEach>
		
			<footer>
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="add_section_url" />
				<a href="${ add_section_url }">Add/Edit Sections</a>
			</footer>
		</article>
		</c:forEach>

		<footer>
			<s:url value="/${ restaurant.id }/menus/" var="add_menu_url" />
			<a href="${ add_menu_url }">Add/Edit Menus</a>
		</footer>

	</section>
	</c:if>
	
</form:form>

<c:if test="${ ! empty restaurants }">
<h3>Restaurant List</h3>
<section id="list">

	<header>
		<ul>
			<li></li>
			<li>Name</li>
			<li></li>
		</ul>
	</header>

	<c:forEach items="${ restaurants }" var="restaurant">
	<article>
		<div>
			<span class="controls">
				<s:url value="/${ restaurant.id }" var="edit_url" />
				<a href="${ edit_url }">Edit</a>
			</span>
			<span>${ restaurant.name }</span>
			<span class="rowForm">
				<s:url value="/${ restaurant.id }" var="delete_url" />
				<form:form method="DELETE" modelAttribute="restaurant" action="${ delete_url }">
					<p>
						<button type="submit">
							Delete
						</button>
					</p>
				</form:form>
			</span>
		</div>
	</article>
	</c:forEach>

</section>
</c:if>