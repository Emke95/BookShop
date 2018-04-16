<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/book/edit" var="editBookUrl" />
<c:url value="/book/delete" var="deleteBookUrl" />
<c:url value="/addNewCreditCard" var="addCredCardUrl" />
<c:url value="/addNewShippingAddress" var="addAddressUrl" />
<c:url value="/setDefaultShippingAddress"
	var="setDefaultShippingAddressUrl" />

<c:url value="/addItem" var="buyUrl" />
<c:url value="/book/info" var="detailsUrl" />

<div class="container">

	<h1>Profile</h1>

	<div class="row">
		<div class="col-md-12">
			<form action="${addCredCardUrl}" method="post">
				<div class="bg-info" th:if="${updateUserPaymentInfo}">User
					info updated.</div>

				<input hidden="hidden" name="id" th:value="${userPayment.id}" />

				<div class="form-group">
					<h5>* Give a name for your card:</h5>
					<input type="text" class="form-control" id="cardName"
						placeholder="Card Name" th:name="cardName" required="required"
						th:value="${userPayment.cardName}" />
				</div>
				<hr />
				<div class="form-group">
					<h4>Credit Card Information</h4>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="cardType">* Select Card Type:</label> <select
								class="form-control" id="cardType" th:name="type"
								th:value="${userPayment.type}">
								<option value="visa">Visa</option>
								<option value="mastercard">Mastercard</option>
								<option value="discover">Discover</option>
								<option value="amex">American Express</option>
							</select>
						</div>
						<div class="form-group">
							<label for="cardHolder">* Card Holder Name:</label> <input
								type="text" class="form-control" id="cardHolder"
								required="required" placeHolder="Card Holder Name"
								th:name="holderName" th:value="${userPayment.holderName}" />
						</div>
						<div class="form-group">
							<label for="cardNumber">* Card Number:</label>
							<div class="input-group">
								<input type="tel" class="form-control" id="cardNumber"
									required="required" placeHolder="Valid Card Number"
									th:name="cardNumber" th:value="${userPayment.cardNumber}" /> <span
									class="input-group-addon"><i class="fa fa-credit-card"
									aria-hidden="true"></i></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-7">
						<div class="form-group">
							<label>* Expiration Date</label>
							<div class="row">
								<div class="col-xs-6">
									<select class="form-control" name="expiryMonth"
										required="required" th:value="${userPayment.expiryMonth}">
										<option disabled="disabled">-- Month --</option>
										<option value="01">Jan (01)</option>
										<option value="02">Feb (02)</option>
										<option value="03">Mar (03)</option>
										<option value="04">Apr (04)</option>
										<option value="05">May (05)</option>
										<option value="06">June (06)</option>
										<option value="07">July (07)</option>
										<option value="08">Aug (08)</option>
										<option value="09">Sep (09)</option>
										<option value="10">Oct (10)</option>
										<option value="11">Nov (11)</option>
										<option value="12">Dec (12)</option>
									</select>
								</div>
								<div class="col-xs-6">
									<select class="form-control" name="expiryYear"
										th:value="${userPayment.expiryYear}">
										<option disabled="disabled">-- Year --</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="19">2019</option>
										<option value="20">2020</option>
										<option value="21">2021</option>
										<option value="22">2022</option>
										<option value="23">2023</option>
										<option value="23">2024</option>
										<option value="23">2025</option>
										<option value="23">2026</option>
										<option value="23">2027</option>
										<option value="23">2028</option>
										<option value="23">2029</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-5">
						<div class="form-group">
							<label for="cardCVC">CV Code</label> <input id="cardCVC"
								type="tel" class="form-control" name="cvc" placeholder="CVC"
								th:name="cvc" th:value="${userPayment.cvc}" />
						</div>
					</div>
				</div>
				<hr />
				<button type="submit" class="btn btn-primary btn-lg">Save
					All</button>
			</form>


			<!-- Shipping Information -->
			<form action="${addAddressUrl}" method="post">
				<div th:if="${listOfShippingAddresses}">
					<form action="${setDefaultShippingAddressUrl}" method="post">
						<table class="table">
							<thead>
								<tr>
									<th>Default</th>
									<th>Shipping Address</th>
									<th>Operations</th>
								</tr>
							</thead>
							<tbody>
								<tr th:each="userShipping : ${userShippingList}">
									<td><input type="radio" name="defaultShippingAddressId"
										th:value="${userShipping.id}"
										th:checked="${userShipping.userShippingDefault}" /><span>default</span></td>
									<td
										th:text="${userShipping.uStreet}+', '+
														${userShipping.uArea}+', '+${userShipping.aCounty}+
														${userShipping.aCountry}"></td>
									<td><a
										th:href="@{/updateUserShipping(id=${userShipping.id})}"><i
											class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a
										th:href="@{/removeUserShipping(id=${userShipping.id})}"><i
											class="fa fa-times"></i></a></td>
								</tr>
							</tbody>
						</table>
						<button class="btn btn-primary" type="submit">Save</button>
					</form>
				</div>

				<div class="bg-info" th:if="${updateUserShippingInfo}">User
					info updated.</div>

				<input hidden="hidden" name="id" th:value="${userShipping.id}" />

				<!-- Shipping Address -->
				<hr />
				<div class="form-group">
					<h4>Shipping Address</h4>
				</div>
				<div class="form-group">
					<label for="shippingName">* House Num</label> <input type="text"
						class="form-control" id="HouseNo" placeholder="HouseNumber"
						name="HouseNo" th:value="${shippingAddress.houseNo}" />
				</div>

				<div class="form-group">
					<label for="Street">* Street Address</label> <input type="text"
						class="form-control" id="Street" placeholder="Street Address"
						name="Street" th:value="${shippingAddress.street}" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Area name="
						Area"
											th:value="${shippingAddress.area}" />
				</div>

				<div class="row">
					<div class="col-xs-4">
						<div class="form-group">
							<label for="country">* country</label> <input type="text"
								class="form-control" id=country placeholder="Shipping country"
								th:name="country" required="required"
								th:value="${shippingAddress.country}" />
						</div>
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="shippingState">* County</label> <select
								id="shippingState" class="form-control"
								th:name="shippingAddressState"
								th:value="${shippingAddress.county}" required="required">
								<option value="" disabled="disabled">Please select an
									option</option>
								<option th:each="state : ${countyList}" th:text="${county}"
									th:selected="(${shippingAddress.county}==${county})"></option>
							</select>
						</div>
					</div>
				</div>
		</div>
		</form>
		</form>
		<hr />
		<button type="submit" class="btn btn-primary btn-lg">Save All</button>
	</div>
</div>
</div>


<%@ include file="/WEB-INF/include/footer.jsp"%>