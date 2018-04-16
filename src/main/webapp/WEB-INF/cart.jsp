<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<c:url value="/cart/remove" var="deleteItemUrl"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>
	<c:url value="/confirmOrder" var="confirmUrl" />
	<div class="container">
		<h2>Your cart</h2>
		<form action="${confirmUrl}" method="post">
			${cartMessage}
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
					<th class="text-center col-md-1">Cover</th>
						<th class="text-center">Book</th>
						<th class="text-center">Quantity</th>
						<th class="text-center">Price</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${cart.contents}">
						<tr>
						<td><img height="100px" width="75px" src="${pageContext.request.contextPath}/bookCover?isbn=${book.key.isbn}"/></td>
							<td><c:out value="${book.key.title}" /></td>
							<td><c:out value="${book.value}" /></td>
							<td>$<c:out value="${book.key.price}" />
							</td>
							<td class="text-center"><a
								href="${deleteItemUrl}/${book.key.isbn}"
								class="btn btn-sm btn-danger delete-button">Delete</a></td>
						</tr>
					</c:forEach>

					<tr></tr>
					<tr>
						<td>Total</td>
						<td></td>
						<td></td>
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