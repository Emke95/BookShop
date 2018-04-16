<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/book/edit" var="editBookUrl" />
<c:url value="/book/delete" var="deleteBookUrl" />
<c:url value="/books" var="booksURL" />
<c:url value="/cart/add" var="buyUrl" />
<c:url value="/books" var="detailsUrl" />
<c:url value="/searchByCategory?category=Fiction" var="FictionUrl" />
<c:url value="/searchByCategory?category=NonFiction" var="NonFictionUrl" />
<c:url value="/searchByCategory?category=Fantasy" var="FantasyUrl" />
<c:url value="/searchByCategory?category=Mystery" var="MysteryUrl" />
<c:url value="/searchByCategory?category=Romance" var="RomanceUrl" />
<c:url value="/searchByCategory?category=Spiritual" var="SpiritualUrl" />
<c:url value="/titleAsc" var="AscURL" />
<c:url value="/titleDesc" var="DescURL" />
<c:url value="/authAsc" var="AuAscURL" />
<c:url value="/authDesc" var="AuDescURL" />
<c:url value="/isbnAsc" var="IsbnAscURL" />
<c:url value="/isbnDesc" var="IsbnDescURL" />
<c:url value="/priceAsc" var="PriceAscURL" />
<c:url value="/priceDesc" var="PriceDescURL" />

<div class="container">

	<h1>List of books</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center"><a href="${FictionUrl}">Fiction
								Books</a></th>
						<th class="text-center"><a href="${NonFictionUrl}">Non
								Fiction Books</a></th>
						<th class="text-center"><a href="${MysteryUrl}">Mystery
								Books</a></th>
						<th class="text-center"><a href="${RomanceUrl}">Romance
								Books</a></th>
						<th class="text-center"><a href="${FantasyUrl}">Fantasy
								Books</a></th>
						<th class="text-center"><a href="${SpiritualUrl}">Spiritual
								Books</a></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th class="text-center "><a href="${IsbnAscURL}">v</a> Isbn
							<a href="${IsbnDescURL}">^</a></th>
						<th class="text-center"><a href="${AscURL}">v</a> Title <a
							href="${DescURL}">^</a></th>
						<th class="text-center"><a href="${AuAscURL}">v</a> Author
							<a href="${AuDescURL}">^</a></th>
						<th class="text-center">Category</th>
						<th class="text-center"><a href="${PriceAscURL}">v</a>
							Price <a href="${PriceDescURL}">^</a></th>
						<th class="text-center">Image</th>
						<security:authorize access="hasRole('ADMIN')">
							<th class="text-center col-md-1">Quantity</th>
							<th class="text-center col-md-1">Edit</th>
							<th class="text-center col-md-1">Delete</th>
						</security:authorize>
						<security:authorize access="hasRole('USER')">
							<th class="text-center col-md-1">Details</th>
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
							<td>$${book.price}</td>
							<td><img class="book-image"
								src="${pageContext.request.contextPath}/bookCover?isbn=${book.isbn}" />

								<security:authorize access="hasRole('ADMIN')">
									<td>${book.quantity}</td>
									<td class="text-center"><a
										href="${editBookUrl}/${book.isbn}"
										class="btn btn-sm btn-primary"> Edit </a></td>
									<td class="text-center"><a
										href="${pageContext.request.contextPath}/bookDelete?isbn=${book.isbn}"
										class="btn btn-sm btn-danger delete-button">Delete</a></td>
								</security:authorize> <security:authorize access="hasRole('USER')">
									<td class="text-center">
									<a href="${detailsUrl}/${book.id}"
										class="btn btn-sm btn-primary">See details</a></td>
									</td>
										<td class="text-center"><a href="${buyUrl}/${book.isbn}"
										class="btn btn-sm btn-primary">Add to Cart</a></td>
										<td class="text-center"><c:choose>
											<c:when test="${book.quantity > 1}"> In stock.
												</c:when>
											<c:otherwise>
												Unavailable</c:otherwise>
										</c:choose></td>
								</security:authorize>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<a href="${booksURL}">All Books</a>
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