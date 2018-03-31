<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/book/edit" var="editBookUrl" />
<c:url value="/book/delete" var="deleteBookUrl" />

<c:url value="/buy/book" var="buyUrl" />
<c:url value="book/info" var="detailsUrl"/>

<div class="container">

	<h1>List of books</h1>


	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center col-md-1">ISBN</th>
						<th class="text-center">Title</th>
						<th class="text-center">Author</th>
						<th class="text-center">Category</th>
						<th class="text-center">Price</th>
						<th class="text-center">Image</th>
						<security:authorize access="hasRole('ADMIN')">
							<th class="text-center col-md-1">Edit</th>
							<th class="text-center col-md-1">Delete</th>
						</security:authorize>
						<security:authorize access="hasRole('USER')">
							<th class="text-center col-md-1">See Details</th>
							<th class="text-center col-md-1">Buy</th>
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
									<!-- <td class="text-center"><a
										href="${editBookUrl}/${book.isbn}"
										class="btn btn-sm btn-primary">Edit</a></td> -->
									<td class="text-center">
									 <li class=btn-edit><a href="${pageContext.request.contextPath}/book?isbn=${book.isbn}"> Edit </a></li>
	                </td>
									<td class="text-center"><a
										href="${deleteBookUrl}/${book.isbn}"
										class="btn btn-sm btn-danger delete-button">Delete</a></td>
								</security:authorize> <security:authorize access="hasRole('USER')">
									<td class="text-center"><a href="${detailsUrl}/${book.isbn}"
										class="btn btn-sm btn-primary">See Details</a></td>
									<td class="text-center"><a href="${buyUrl}/${book.isbn}"
										class="btn btn-sm btn-primary">Add to Cart</a></td>
								</security:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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