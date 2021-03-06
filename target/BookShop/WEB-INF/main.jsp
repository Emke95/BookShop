<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<c:url value="/orders" var="ordersUrl" />

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<div class="container">

	<h1>Welcome to our Bookstore</h1>
	<sec:authorize access="hasRole('ADMIN')">
		<a href="${ordersUrl}">View Orders</a>
	</sec:authorize>

</div>
</div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>