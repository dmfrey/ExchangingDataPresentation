<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<h2>Tax Test Page</h2>

<form:form method="post" modelAttribute="transaction">
	
	<fieldset>
		<legend>Transaction Form</legend>
		
		<form:errors path="*" cssClass="error">
			<div>
				Errors Exist
			</div>
		</form:errors>
		
		<form:label path="sender">Sender <form:errors path="sender" cssClass="error" /></form:label>
		<form:select path="sender">
			<form:options items="${ senders }" itemValue="id" itemLabel="name" />
		</form:select>
		<form:label path="addressOne">Address <form:errors path="addressOne" cssClass="error" /></form:label>
		<form:input path="addressOne" maxlength="128" />
		<form:label path="city">City <form:errors path="city" cssClass="error" /></form:label>
		<form:input path="city" maxlength="128" />
		<form:label path="state">State <form:errors path="state" cssClass="error" /></form:label>
		<form:input path="state" maxlength="2" />
		<form:label path="zip">Zip <form:errors path="zip" cssClass="error" /></form:label>
		<form:input path="zip" maxlength="10" />
		<form:label path="country">Country <form:errors path="country" cssClass="error" /></form:label>
		<form:input path="country" maxlength="128" />
	</fieldset>
		
	<fieldset>
		<legend>Shipping Total</legend>
		<form:label path="shipping">Total <form:errors path="shipping" cssClass="error" /></form:label>
		<form:input path="shipping" />
	</fieldset>

	<fieldset>
		<legend>Order Total</legend>
		<form:label path="total">Total <form:errors path="total" cssClass="error" /></form:label>
		<form:input path="total" />
	</fieldset>

	<fieldset>
		<legend>Line Items</legend>
		<c:forEach items="${ transaction.items }" var="item" varStatus="loop">

			<form:label path="items[${ loop.index }].quantity">Quantity <form:errors path="items[${ loop.index }].quantity" cssClass="error" /></form:label>
			<form:input path="items[${ loop.index }].quantity" />
		
			<form:label path="items[${ loop.index }].listPrice">List Price <form:errors path="items[${ loop.index }].listPrice" cssClass="error" /></form:label>
			<form:input path="items[${ loop.index }].listPrice" />

		</c:forEach>
	</fieldset>

	<p>
		<button type="submit">
			Calculate Tax
		</button>
	</p>

</form:form>

<c:if test="${ ! empty response }">
	<h3>Tax Response</h3>
	${ response }
</c:if>