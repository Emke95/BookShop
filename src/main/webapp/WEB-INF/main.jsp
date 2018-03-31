<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/main.css">
<script>
	var text;

	$(document).ready(function() {

		$("#search").keyup(function() {
			text = $(this).val();
			if (text.length > 0) {
				$.ajax({
					type : "GET",
					data : "search=" + text,
					url : "search",
					datatype : "text",
					success : function(data) {
						$("#searchArea").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert(xhr.status);
						alert(thrownError);
					}
				});
			} else {
				$("#searchArea").html("");
			}
			return false;
		});
	});
</script>
<li>
	<div class="pull-left">
		<form class="form-inline" role="form">
			<div class="form-group">
				<input type="text" style="width: 200px;" class="form-control"
					id="search" placeholder="Search Everyone">
			</div>
			<button type="submit" class="btn btn-theme">Search</button>
		</form>
	</div> <!-- /form-panel -->
</li>

				<div class="row">
<div id="searchArea" class="col-lg-9 main-chart">
	<div class="container">

		<h1>Welcome to our Bookstore</h1>

	</div>
	</div>
	</div>

	<%@ include file="/WEB-INF/include/footer.jsp"%>