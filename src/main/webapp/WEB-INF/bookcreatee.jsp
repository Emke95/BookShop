<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<c:url var="createBookUrl" value="/book/save" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Book</title>
</head>
<body>
	<form:form id="book" modelAttribute="book" action="${createBookUrl}"
		method="post">
		<table align="center">
			<tr>
				<td><form:label path="isbn">Isbn</form:label></td>
				<td><form:input path="isbn" name="isbn" id="isbn" /></td>
			</tr>
			<tr>
				<td><form:label path="title">Title</form:label></td>
				<td><form:input path="title" name="title" id="title" /></td>
			</tr>
			<tr>
				<td><form:label path="author">Author:</form:label></td>
				<td><form:input path="author" name="author" id="author" /></td>
			</tr>
			<tr>
				<td><form:label path="category">Category:</form:label></td>
				<td><form:input path="category" name="category" id="category" />
				</td>
			</tr>
			<tr>
				<td class="custom" id="imgUpload">Upload:</td>
				<td><form:input id="fileInput" type="file" path="fileData" /></td>
				<td><form:errors path="fileData" class="error-message" /></td>
			</tr>
			<tr>
				<td><form:label path="price">Price:</form:label></td>
				<td><form:input path="price" name="price" id="price" /></td>
			</tr>
			<tr>
				<td><form:label path="quantity">Quantity:</form:label></td>
				<td><form:input path="quantity" name="quantity" id="quantity" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Save" id="send" /></td>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>