<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/book/edit" var="editBookUrl" />
<c:url value="/book/delete" var="deleteBookUrl" />

<c:url value="/buy/book" var="buyUrl" />

<div class="container">

	<h1>List of books</h1>

	<c:forEach items="${bookList}" var="book">
		<div class="book-box">
			<ul>
				<li><a class="btn-details" href="${pageContext.request.contextPath}/bookInfo?isbn=${book.isbn}"><span><b>
				SeeDetails</b></span></a> 
				<img class="book-image" src="${pageContext.request.contextPath}/bookCover?isbn=${book.isbn}" />

				</li>

				<li class="book">${book.title}</li>
				<li class="book">${book.author}</li>
				<li class="book" >${book.category}</li>
				<li class="book"><fmt:formatNumber value="${book.price}" type="currency" /></li>

				<security:authorize access="hasRole('USER')">
					<li class="btn-add"><a href="${pageContext.request.contextPath}/buyBook?isbn=${book.isbn}">Add
							to cart</a></li>
				</security:authorize>

				<security:authorize access="hasRole('ADMIN')">
					<li class=btn-edit><a
						href="${pageContext.request.contextPath}/book?isbn=${book.isbn}">
							Edit </a></li>
					<li class="btn-delete"><a
						href="${pageContext.request.contextPath}/bookDelete?isbn=${book.isbn}">
							Delete </a></li>
				</security:authorize>

			</ul>


		</div>
	</c:forEach>

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
						<th class="text-center col-md-1">Edit</th>
						<th class="text-center col-md-1">Delete</th>
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
								src="${pageContext.request.contextPath}/bookImage?isbn=${book.isbn}" />
							<td class="text-center"><a
								href="${editBookUrl}/${book.isbn}"
								class="btn btn-sm btn-primary">Edit</a></td>
							<td class="text-center"><a
								href="${deleteBookUrl}/${book.isbn}"
								class="btn btn-sm btn-danger delete-button">Delete</a></td>
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