<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/footer.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>


<c:url value="/orders?id=" var="viewOrderUrl" />

<div class="container">

	<h1>List of users</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">First Name</th>
						<th class="text-center">Last Name</th>
						<th class="text-center">Email</th>
						<th class="text-center col-md-1">See Orders</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${usersList}" var="user">
						<tr>
							<td>${user.getFirstName()}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>

							<td><a href="${viewOrderUrl}${user.id}"
								class="btn btn-sm btn-primary">See Orders</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</div>

