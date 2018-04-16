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
						<th class="text-center col-md-1">Order No.</th>
						<th class="text-center">Date</th>
						<th class="text-center">Name</th>
						<th class="text-center">Email</th>
						<th class="text-center">Address</th>
						<th class="text-center">Card Number</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${orderDemoList}" var="orderDemo">
						<tr>
							<td>${orderDemo.getId()}</td>
							<td>${orderDemo.getOrderDate()}</td>
							<td>${orderDemo.getUser().getFirstName()}
								${orderDemo.getUser().getLastName()}</td>
							<td>${orderDemo.getUser().getEmail()}</td>
							<td>${orderDemo.getUser().getAddress()}</td>
							<td>${orderDemo.getUser().getCardNumber()}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</div>

