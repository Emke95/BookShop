<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/footer.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<div class="container">

	<h1>List of Orders</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Date</th>
						<th class="text-center">User Id</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${ordersList}" var="order">
						<tr>
							<td>${order.getOrderId()}</td>
							<td>${order.user.getFirstName()}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</div>

