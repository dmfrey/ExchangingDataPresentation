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
		<p>
			<s:url value="/" var="new_url" />
			<a href="${ new_url }">New</a>
		</p>
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