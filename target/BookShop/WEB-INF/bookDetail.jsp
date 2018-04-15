<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<c:url var="buyUrl" value="/addItem" />


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Information</title>
</head>
<body>
	<div class="container">
		<form:form action="${buyUrl}" method="post">
		<input hidden="hidden" field="*{book.id}" />
				<h3 text="${book.title}">Book Title</h3>
				<div class="row">
					<div class="col-xs-5">
						<h5>
							<strong>Author: </strong><span th:text="${book.author}"></span>
						</h5>

						<p>
							<strong>Category: </strong><span th:text="${book.category}"></span>
						</p>
						<p>
							<strong>ISBN: </strong><span th:text="${book.isbn}"></span>
						</p>

					</div>

					<div class="col-xs-7">
						<div class="panel panel-default"
							style="border-width: 5px; margin-top: 20px;">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-6">
										<h4>
											Our Price: <span style="color: #db3208;">$<span
												th:text="${book.price}"></span></span>
										</h4>
										<span>Qty: </span> <select name="qty">
											<option th:each="qty : ${qtyList}" th:value="${qty}"
												th:text="${qty}"></option>
										</select>
									</div>
									<div class="col-xs-6">
										<h4 th:if="*{book.quantity&gt;10}" style="color: green">In
											Stock</h4>
										<h4
											th:if="*{book.quantity&lt;10 and book.quantity&gt;0}"
											style="color: green">
											Only <span th:text="${book.quantity}"> </span> In Stock
										</h4>
										<h4 th:if="*{book.quantity==0}" style="color: darkred;">Unavailable</h4>
										<button type="submit" class="btn btn-warning"
											style="color: black; border: 1px solid black; padding: 10px 40px 10px 40px;">Add
											to Cart</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form:form>
	</div>
</body>
</html>