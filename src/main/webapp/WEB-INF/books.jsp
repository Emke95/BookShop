<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/book/edit" var="editBookUrl" />
<c:url value="/book/delete" var="deleteBookUrl" />

<c:url value="/addItem?id=" var="buyUrl" />
<c:url value="/bookDetail?id=" var="detailsUrl" />

<html lang="en" xmlns:th="http://www.w3.org/1000/xhtml">
<div class="container">

	<h1>List of books</h1>

	<div class="row">
		<div class="col-md-12">

			<form:form action="${buyUrl}&${book.id}qty=1" method="post">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th class="text-center">ISBN</th>
							<th class="text-center">Title</th>
							<th class="text-center">Author</th>
							<th class="text-center">Category</th>
							<th class="text-center">Price</th>
							<th class="text-center">Image</th>
							<security:authorize access="hasRole('ADMIN')">
								<th class="text-center col-md-1">Quantity</th>
								<th class="text-center col-md-1">Edit</th>
								<th class="text-center col-md-1">Delete</th>
							</security:authorize>
							<security:authorize access="hasRole('USER')">
								<th class="text-center col-md-1">Quantity</th>
								<th class="text-center col-md-1">See Details</th>
								<th class="text-center col-md-1">Buy</th>
								<th class="text-center col-md-1">Available</th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${bookList}">
							<tr>

								<td>${book.isbn}</td>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${book.category}</td>
								<td>${book.price}</td>
								<td><img class="book-image"
									src="${pageContext.request.contextPath}/bookCover?isbn=${book.isbn}" />
									<security:authorize access="hasRole('ADMIN')">
										<td>${book.quantity}</td>
										<td class="text-center"><a
											href="${editBookUrl}/${book.isbn}"
											class="btn btn-sm btn-primary"> Edit </a></td>
										<td class="text-center"><a
											href="${pageContext.request.contextPath}/book/delete?isbn=${book.isbn}"
											class="btn btn-sm btn-danger delete-button">Delete</a></td>
									</security:authorize> <security:authorize access="hasRole('USER')">
										<td><select name="qty">
												<option "qty : ${qtyList}" value="${qty}" text="${qty}"
													selected="qty">1</option>
										</select></td>
										<td class="text-center"><a
											href="${detailsUrl}/${book.id}"
											class="btn btn-sm btn-primary">See Details</a></td>
										<td class="text-center"><a
											href="${buyUrl}${book.id}&qty=1"
											class="btn btn-sm btn-primary">Add to Cart</a></td>
										<td><c:choose>
												<c:when test="${book.quantity > 1}"> In stock.
												</c:when>
												<c:otherwise>
												Unavailable</c:otherwise>
											</c:choose>
									</security:authorize>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</form:form>
		</div>
	</div>
</div>
<script>
	$(function() {
		$('.delete-button').on('click', function(event) {
			console.log(event);
			event.preventDefault();
			var url = event.target.href;
			$.post(url).done(function() {
				location.reload();
			});
		});
	});
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>