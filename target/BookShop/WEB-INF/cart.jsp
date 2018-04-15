<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>
	<div class="container">
		<h2>Your cart</h2>
		<form action='cart/confirmOrder' method="post">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Book</th>
						<th class="text-center">Quantity</th>
						<th class="text-center">Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${cart.contents}">
						<tr>
							<td><c:out value="${book.key.title}" /></td>
							<td><c:out value="${book.value}" /></td>
							<td>$<c:out value="${book.key.price}" />
							</td>
						</tr>
					</c:forEach>

					<tr></tr>
					<tr>
						<td>Total</td>
						<td></td>
						<td>$${cart.totalCost}</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="Confirm Order"></input>
		</form>

	</div>
</body>
</html>