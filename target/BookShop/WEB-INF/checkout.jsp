<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<c:url value="/checkout" var="checkoutUrl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkout</title>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-bottom: -100px;">
			<div class="col-xs-8">
				<h2 class="section-headline">
					<span>User Account</span>
				</h2>
			</div>

		</div>

		<div class="row" style="margin-top: 10px;">
			<form th:action="${checkoutUrl}" method="post">

				<!-- Left Panel -->
				<div class="col-xs-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<button type="submit" class="btn btn-warning btn-block">Place
								your order</button>
							
							<hr />
							<h3>Order Summary</h3>
							<div class="row">
								<div class="col-xs-7 text-left">Total:</div>
								<div class="col-xs-5 text-right">
									$<span ${shoppingCart.grandTotal}"></span>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-7 text-left">
									<h3 style="color: darkred;">
										<strong>Order Total: </strong>
									</h3>
								</div>
								<div class="col-xs-5 text-right">
									<h3>
										<strong style="color: darkred;">$<span>
											${shoppingCart.grandTotal}"
											</span></strong>
									</h3>
								</div>
							</div>
							<div class="panel-footer">Shipping not added.</div>
						</div>
					</div>
				</div>

				<!-- Checkout Info -->
				<div class="col-xs-8">
					<div th:if="${missingRequiredField}">
						<h5 class="alert alert-warning">There are some fields
							missing. Field with * is required.</h5>
					</div>

					<div class="panel-group" id="accordion">

						<!-- 1. Shipping Address -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#shippingInfo"> 1. Shipping Address </a>
								</h4>
							</div>
							<div id="shippingInfo" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table" th:if="${not emptyShippingList}">
										<thead>
											<tr>
												<th>Available Shipping Address</th>
												<th>Operations</th>
											</tr>
										</thead>
										<tbody>
											<tr th:each="userShipping : ${userShippingList}">
												<td
													th:text="${userShipping.uHouseNo}+' '+${userShipping.uStreet}+', '+${userShipping.uArea}
												+', '+${userShipping.aCounty}+${userShipping.aCountry}"></td>
												<td><a
													th:href="@{/setShippingAddress(userShippingId=${userShipping.id})}">use
														this address</a></td>
											</tr>
										</tbody>
									</table>

									<div class="form-group">
										<label for="shippingName">* House Num</label> <input type="text"
											class="form-control" id="HouseNo"
											placeholder="HouseNumber" name="HouseNo"
											th:value="${shippingAddress.houseNo}" />
									</div>

									<div class="form-group">
										<label for="Street">* Street Address</label> <input
											type="text" class="form-control" id="Street"
											placeholder="Street Address" name="Street"
											th:value="${shippingAddress.street}" />
									</div>
									<div class="form-group">
										<input type="text" class="form-control"
											placeholder="Area name="Area"
											th:value="${shippingAddress.area}" />
									</div>

									<div class="row">
										<div class="col-xs-4">
											<div class="form-group">
												<label for="country">* country</label> <input type="text"
													class="form-control" id=country
													placeholder="Shipping country" th:name="country"
													required="required"
													th:value="${shippingAddress.country}" />
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="shippingState">* County</label> <select
													id="shippingState" class="form-control"
													th:name="shippingAddressState"
													th:value="${shippingAddress.county}"
													required="required">
													<option value="" disabled="disabled">Please select
														an option</option>
													<option th:each="state : ${countyList}" th:text="${county}"
														th:selected="(${shippingAddress.county}==${county})"></option>
												</select>
											</div>
										</div>
										
									</div>
									<a data-toggle="collapse" data-parent="#accordion"
										class="btn btn-warning pull-right" href="#paymentInfo">Next</a>
								</div>
							</div>
						</div>

						<!-- Payment Information -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#paymentInfo"> 2. Payment Information </a>
								</h4>
							</div>

							<div id="paymentInfo" class="panel-collapse collapse"
								th:classappend="${classActivePayment}? 'in'">
								<div class="panel-body">
									<table class="table" th:if="${not emptyPaymentList}">
										<thead>
											<tr>
												<th>Available Credit Card</th>
												<th>Operations</th>
											</tr>
										</thead>
										<tbody>
											<tr th:each="userPayment : ${userPaymentList}">
												<td th:text="${userPayment.cardName}"></td>
												<td><a
													th:href="@{/setPaymentMethod(userPaymentId=${userPayment.id})}">use
														this payment</a></td>
											</tr>
										</tbody>
									</table>

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
										<label for="cardCVC">CVC Code</label> <input id="cardCVC"
											type="tel" class="form-control" name="cvc" placeholder="CVC"
											th:name="cvc" th:value="${userPayment.cvc}" />
									</div>
								</div>
							</div>
											</div>
										</div>
										<div class="col-xs-5">
											<div class="form-group">
												<label for="cardCVC">CV Code</label> <input id="cardCVC"
													type="tel" class="form-control" name="cvc"
													placeholder="CVC" th:name="cvc" th:value="${payment.cvc}" />
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="panel panel-default">
							
							<div id="reviewItems" class="panel-collapse collapse">
								<div class="panel-body">
									

									<div class="row">
										<div class="col-xs-8">
											<h4>Books</h4>
										</div>
										<div class="col-xs-2">
											<h4>Price</h4>
										</div>
										<div class="col-xs-2">
											<h4>Qty</h4>
										</div>
									</div>

									<!-- display products in cart -->
									<div class="row" th:each="cartItem : ${cartItemList}">
										<hr />
										<div class="col-xs-2">
											<a th:href="@{/bookDetail(id=${cartItem.book.id})}">

												style="width: 70px;" />
											</a>
										</div>
										<div class="col-xs-6">
											<div style="margin-left: 50px;">
												<a th:href="@{/bookDetail?id=}+${cartItem.book.id}"><h4
														th:text="${cartItem.book.title}"></h4></a>
												<p th:if="${cartItem.book.quantity&gt;10}"
													style="color: green;">In Stock</p>
												<p
													th:if="${cartItem.book.quantity&lt;10 and cartItem.book.quantity&gt;0}"
													style="color: green;">
													Only <span th:text="${cartItem.book.quantity}"></span>
													In Stock
												</p>
												<p th:if="${cartItem.book.quantity==0}"
													style="color: darkred;">Product Unavailable</p>
												<a th:href="@{/shoppingCart/removeItem?id=}+${cartItem.id}">delete</a>
											</div>
										</div>

										<div class="col-xs-2">
											<h5 style="color: #db3208; font-size: large;">
												$<span th:text="${cartItem.book.ourPrice}"
													th:style="${cartItem.book.quantity}==0? 'text-decoration: line-through' : ''"></span>
											</h5>
										</div>

										<div class="col-xs-2">
											<h5 style="font-size: large;" th:text="${cartItem.qty}"></h5>
										</div>

									</div>

									<hr />
									<h4 class="col-xs-12 text-right">
										<strong style="font-size: large;">Order Total (<span
											th:text="${#lists.size(cartItemList)}"></span> items):
										</strong> <span style="color: #db3208; font-szie: large;">$<span
											th:text="${shoppingCart.grandTotal}"></span></span>
									</h4>
									<br /> <br />
									<button type="submit" class="btn btn-warning btn-block">Place
										your order</button>
				
								</div>
							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- end of container -->
</body>
</html>