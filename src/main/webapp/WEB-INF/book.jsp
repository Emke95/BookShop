<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<c:url var="createBookUrl" value="/book" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Book</title>

</head>
<div id="wrapper">
	<div class="custom" id="page-title">
		<h4 align="center">Create new book</h4>
	</div>
	<div id="main">
		<c:if test="${not empty errorMessage }">
			<div class="error-message">${errorMessage}</div>
		</c:if>
		<div class="contact-left">
			<form:form commandName="book" action="${createBookUrl}"
				modelAttribute="bookForm" method="POST"
				enctype="multipart/form-data" id="formMail">
				<table align="center">
					<tr>
						<td class="custom">ISBN:</td>
						<td><c:if test="${not empty bookForm.isbn}">
								<form:input path="isbn" style="color:red;" />

							</c:if> <c:if test="${empty bookForm.isbn}">
								<form:input path="isbn" />
								<form:hidden path="newBook" />

							</c:if></td>
						<td><form:errors path="isbn" class="error-message" /></td>

					</tr>

					<tr>
						<td class="custom">Title:</td>
						<td><form:input path="title" /></td>
						<td><form:errors path="title" class="error-message" /></td>
					</tr>
					<tr>
						<td class="custom">Author:</td>
						<td><form:input path="author" /></td>
						<td><form:errors path="author" class="error-message" /></td>
					</tr>
					<tr>
						<td class="custom">Category:</td>
						<td><form:input path="category" /></td>
						<td><form:errors path="category" class="error-message" /></td>
					</tr>
					<tr>
						<td class="custom">Image:</td>
						<td><img id="prodImage"
							src="${pageContext.request.contextPath}/bookCover?isbn=${bookForm.isbn}"
							width="130" /></td>
						<td></td>
					</tr>
					<tr>
						<td class="custom" id="imgUpload">Upload:</td>
						<td><form:input id="fileInput" type="file" path="fileData" /></td>
						<td><form:errors path="fileData" class="error-message" /></td>
					</tr>
					<tr>
						<td class="custom">Price:</td>
						<td><form:input path="price" /></td>
						<td><form:errors path="price" class="error-message" /></td>
					</tr>

					<tr>
						<td class="custom">Quantity:</td>
						<td><form:input path="quantity" /></td>
						<td><form:errors path="quantity" class="error-message" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Save" id="send" /></td>
					</tr>

				</table>

			</form:form>

		</div>

	</div>

</div>
</html>