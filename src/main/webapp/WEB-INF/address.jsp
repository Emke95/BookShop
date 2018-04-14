<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/include/header.jsp"%>

<c:url value="/address" var="createAddressURL" />

<div class="container">
	<div class="row">
		<h4>Shipping details</h4>
		<form:form commandName="address" action="${createAddressURL}" modelAttribute="addressForm" method="PUT"
			role="form" class="form-horizontal" id="formMail">
			 <input type="hidden" value="${addressId}" name="id"/>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="houseNumber">House
					Number:</label>
				<div class="col-sm-6">
					<form:input path="houseNo" type="text" id="houseNo"
						class="form-control" placeholder="Enter house number" />
					<form:errors path="houseNo" cssStyle="color: red" />
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
				<label class="control-label col-sm-2" for="area">Area::</label>
				<div class="col-sm-6">
					<form:input path="area" type="text" id="area" class="form-control"
						placeholder="Enter Area" />
					<form:errors path="area" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="county">County::</label>
				<div class="col-sm-6">
					<form:input path="county" type="text" id="county"
						class="form-control" placeholder="Enter county" />
					<form:errors path="county" cssStyle="color: red" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="country">Country::</label>
				<div class="col-sm-6">
					<form:input path="country" type="text" id="country"
						class="form-control" placeholder="Enter country" />
					<form:errors path="country" cssStyle="color: red" />
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