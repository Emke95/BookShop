<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<div class="container">

	<h1>Welcome to our Bookstore</h1>

	<form action="search" method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td><label for="title">: </label></td><td><input type="text" name="title" value="" /></td>
		</tr>
		<tr>
			<td><label for="author">: </label></td><td><input type="text" name="author" value="" />
		</tr>
		<tr>
			<td><input type="reset"  /></td><td><input type="submit"/>Search</td>
		</tr>
	</table>
</form>
</div>
</div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>