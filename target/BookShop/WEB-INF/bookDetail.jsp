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
			<form: hidden path="{book.id}" />

		</form:form>
	</div>
</body>
</html>