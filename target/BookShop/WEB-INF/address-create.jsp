<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/include/header.jsp"%>

<c:url value="/address/save" var="createAddressURL" />

<div class="container">
	<div class="row">
		<h4>Shipping details</h4>
		<form:form commandName="address" action="${createAddressURL}" method="post"
			role="form" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-2" for="houseNumber">House
					Number:</label>
				<div class="col-sm-6">
					<form:input path="houseNumber" type="text" id="houseNumber"
						class="form-control" placeholder="Enter house number" />
					<form:errors path="houseNumber" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="street">Street:</label>
				<div class="col-sm-6">
					<form:input path="street" type="text" id="street"
						class="form-control" placeholder="Enter street" />
					<form:errors path="street" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="city">City::</label>
				<div class="col-sm-6">
					<form:input path="city" type="text" id="city" class="form-control"
						placeholder="Enter city" />
					<form:errors path="city" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="country">County::</label>
				<div class="col-sm-6">
					<form:input path="county" type="text" id="county"
						class="form-control" placeholder="Enter county" />
					<form:errors path="county" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<button type="submit" class="btn btn-primary">Save</button>
					<a href="${addressURL}" class="btn btn-danger">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</div>
<%@ include file="/WEB-INF/include/footer.jsp"%>