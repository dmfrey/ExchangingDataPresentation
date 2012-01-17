<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<s:url value="/${ restaurant.id }" var="back_url" />
<a href="${ back_url }">Back to ${ restaurant.name }</a>

<s:url value="/${ restaurant.id }/menus" var="menus_back_url" />
<a href="${ menus_back_url }">Back to ${ menu.name }</a>

<c:if test="${ empty sections }">
	<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="sections_back_url" />
	<a href="${ sections_back_url }">Back</a>
</c:if>

<h2>Sections</h2>

<c:choose>
	<c:when test="${ empty section.id }">
		<c:set var="button_label" value="Create" />
		<c:set var="method" value="post" />
	</c:when>
	<c:otherwise>
		<c:set var="button_label" value="Update" />
		<c:set var="method" value="put" />
	</c:otherwise>
</c:choose>

<form:form method="${ method }" modelAttribute="section">
	<form:hidden path="id" />
	
	<fieldset>
		<legend>Section Form</legend>
		<c:if test="${ ! empty section.id }">
		<p>
			<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="new_url" />
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

</form:form>

<c:if test="${ ! empty sections }">
<h3>Section List</h3>
<section id="list">

	<header>
		<ul>
			<li></li>
			<li>Name</li>
			<li></li>
		</ul>
	</header>

	<c:forEach items="${ sections }" var="section">
	<article>
		<div>
			<span class="controls">
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }" var="edit_url" />
				<a href="${ edit_url }">Edit</a>
			</span>
			<span>${ section.name }</span>
			<span>
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections/${ section.id }/menuItems" var="add_menuItems_url" />
				<a href="${ add_menuItems_url }">Add/Edit Menu Items</a>
			</span>
			<span class="rowForm">
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="delete_url" />
				<form method="post" action="${ delete_url }">
					<input type="hidden" name="_method" value="delete" />
					<input type="hidden" name="sectionId" value="${ section.id }" />
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