<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<s:url value="/${ restaurant.id }" var="back_url" />
<a href="${ back_url }">Back to ${ restaurant.name }</a>

<c:if test="${ empty menus }">
	<s:url value="/${ restaurant.id }/menus" var="menus_back_url" />
	<a href="${ menus_back_url }">Back</a>
</c:if>

<h2>Menus for ${ restaurant.name }</h2>

<c:choose>
	<c:when test="${ empty menu.id }">
		<c:set var="button_label" value="Create" />
		<c:set var="method" value="post" />
	</c:when>
	<c:otherwise>
		<c:set var="button_label" value="Update" />
		<c:set var="method" value="put" />
	</c:otherwise>
</c:choose>

<form:form method="${ method }" modelAttribute="menu">
	<form:hidden path="id" />
	
	<fieldset>
		<legend>Menu Form</legend>
		<c:if test="${ ! empty menu.id }">
		<p>
			<s:url value="/${ restaurant.id }/menus" var="new_url" />
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

<c:if test="${ ! empty menus }">
<h3>Menu List</h3>
<section id="list">

	<header>
		<ul>
			<li></li>
			<li>Name</li>
			<li></li>
		</ul>
	</header>

	<c:forEach items="${ menus }" var="menu">
	<article>
		<div>
			<span class="controls">
				<s:url value="/${ restaurant.id }/menus/${ menu.id }" var="edit_url" />
				<a href="${ edit_url }">Edit</a>
			</span>
			<span>${ menu.name }</span>
			<span>
				<s:url value="/${ restaurant.id }/menus/${ menu.id }/sections" var="add_section_url" />
				<a href="${ add_section_url }">Add/Edit Sections</a>
			</span>
			<span class="rowForm">
				<s:url value="/${ restaurant.id }/menus" var="delete_url" />
				<form method="post" action="${ delete_url }">
					<input type="hidden" name="_method" value="delete" />
					<input type="hidden" name="menuId" value="${ menu.id }" />
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