<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/footer.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<c:url value="/cart/add" var="buyUrl" />
<div class="container">

	<table class="bookshow">
		<tr>
			<td>Title:</td>
			<td>${book.title}</td>
			<td rowspan="9" class="imagecolumn"><a
				href="<c:url value="/books/book/${book.id}"/>"><img
					src="${pageContext.request.contextPath}/bookCover?isbn=${book.isbn}"
					width="270" height="425"></a></td>
		</tr>
		<tr>
			<td>Author:</td>
			<td>${book.author}</td>
		</tr>
		<tr>
			<td>Isbn:</td>
			<td>${book.isbn}</td>
		</tr>
		<tr>
			<td>Price:</td>
			<td>$${book.price}</td>
		</tr>
		
	</table>
	<table>
	<security:authorize access="hasRole('USER')">

			<td class="text-center"><a href="${buyUrl}/${book.isbn}"
				class="btn btn-sm btn-primary">Add to Cart</a></td>
			<td class="text-center"><c:choose>
					<c:when test="${book.quantity > 1}"> In stock.
												</c:when>
					<c:otherwise>
												Unavailable</c:otherwise>
				</c:choose></td>
		</security:authorize>
		</table>
</div>


