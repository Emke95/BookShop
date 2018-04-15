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

<c:url value="/addItem" var="buyUrl" />
<c:url value="/book/info" var="detailsUrl" />

<div class="container">

	<h1>Profile</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Orders</th>
						<th class="text-center">Billing</th>
						<th class="text-center">Shipping</th>
					</tr>
				</thead>
				<tbody>
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
								
							<!-- Credit Card Information -->
							<hr />
							<div class="form-group">
								<h4>Credit Card Information</h4>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<img src="/image/creditcard.png" class="img-responsive" /><br />
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
												th:name="cardNumber" th:value="${userPayment.cardNumber}" />
											<span class="input-group-addon"><i
												class="fa fa-credit-card" aria-hidden="true"></i></span>
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
					</div>
					</div>
					</div>
					</div>
					</div>

					<!-- Shipping Information -->
					<div class="tab-pane fade" id="tab-4"
						th:classappend="${classActiveShipping}? 'in active'">
						<div class="panel-group">
							<div class="panel panel-default" style="border: none;">
								<div class="panel-body"
									style="background-color: #ededed; margin-top: 20px;">

									<ol class="breadcrumb">
										<li class="breadcrumb-item active"><a
											th:href="@{/listOfShippingAddresses}"
											th:style="${listOfShippingAddresses}? 'color:red'">List
												of Shipping Addresses</a></li>
										<li class="breadcrumb-item active"><a
											th:href="@{/addNewShippingAddress}"
											th:style="${addNewShippingAddress}? 'color:red'">Add(Update)
												Shipping Address</a></li>
									</ol>

									<div th:if="${listOfShippingAddresses}">
										<form th:action="@{/setDefaultShippingAddress}" method="post">
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
														<td><input type="radio"
															name="defaultShippingAddressId"
															th:value="${userShipping.id}"
															th:checked="${userShipping.userShippingDefault}" /><span>default</span></td>
														<td
															th:text="${userShipping.userShippingStreet1}+', '+
														${userShipping.userShippingCity}+', '+${userShipping.userShippingState}"></td>
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

									<div th:if="${addNewShippingAddress}">
										<form th:action="@{/addNewShippingAddress}" method="post">
											<div class="bg-info" th:if="${updateUserShippingInfo}">User
												info updated.</div>

											<input hidden="hidden" name="id"
												th:value="${userShipping.id}" />

											<!-- Shipping Address -->
											<hr />
											<div class="form-group">
												<h4>Shipping Address</h4>
											</div>
											<div class="form-group">
												<label for="shippingName">* Name</label> <input type="text"
													class="form-control" id="shippingName"
													placeholder="Receiver Name" th:name="userShippingName"
													required="required"
													th:value="${userShipping.userShippingName}" />
											</div>
											<div class="form-group">
												<label for="shippingAddress">* Street Address</label> <input
													type="text" class="form-control" id="shippingAddress"
													placeholder="Street Address 1"
													th:name="userShippingStreet1" required="required"
													th:value="${userShipping.userShippingStreet1}" /> <input
													type="text" class="form-control"
													placeholder="Street Address 2" th:name="userShppingStreet2"
													th:value="${userShipping.userShippingStreet2}" />
											</div>

											<div class="row">
												<div class="col-xs-4">
													<div class="form-group">
														<label for="shippingCity">* City</label> <input
															type="text" class="form-control" id="shippingCity"
															placeholder="Shipping City" th:name="userShippingCity"
															required="required"
															th:value="${userShipping.userShippingCity}" />
													</div>
												</div>
												<div class="col-xs-4">
													<div class="form-group">
														<label for="shippingState">* State</label> <select
															id="shippingState" class="form-control"
															th:name="userShippingState"
															th:value="${userShipping.userShippingState}"
															required="required">
															<option value="" disabled="disabled">Please
																select an option</option>
															<option th:each="state : ${stateList}" th:text="${state}"
																th:selected="(${userShipping.userShippingState}==${state})"></option>
														</select>
													</div>
												</div>
												<div class="col-xs-4">
													<div class="form-group">
														<label for="shippingZipcode">* Zipcode</label> <input
															type="text" class="form-control" id="shippingZipcode"
															placeholder="Shipping Zipcode"
															th:name="userShippingZipcode" required="required"
															th:value="${userShipping.userShippingZipcode}" />
													</div>
												</div>
											</div>

											<hr />
											<button type="submit" class="btn btn-primary btn-lg">Save
												All</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/include/footer.jsp"%>