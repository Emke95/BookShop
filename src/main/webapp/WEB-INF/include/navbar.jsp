<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:url value="/users" var="usersURL" />
<c:url value="/address" var="createAddressUrl" />
<c:url value="/register" var="createUserURL" />
<c:url value="/logout" var="logoutURL" />
<c:url value="/search" var="searchURL" />
<c:url value="/books" var="booksUrl" />
<c:url value="/book" var="createBookUrl" />
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
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"></a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<sec:authorize access="hasRole('ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Users <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${createUserURL}">New...</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="${usersURL}">Show all</a></li>
						</ul></li>
				</sec:authorize>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Books <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<sec:authorize access="hasRole('ADMIN')">
							<li><a href="${createBookUrl}">New...</a></li>
						</sec:authorize>
						<li role="separator" class="divider"></li>
						<li><a href="${booksUrl}">Show all</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-center">
				<li>
					<form class="navbar-form navbar-left" method="get"
						action="${searchURL}">
						<div class="form-group" role="search">
							<input type="text" name="searchTerm" class="form-control"
								placeholder="Book title">
						</div>
						<button type="submit" class="btn btn-default">Search</button>
					</form>
				</li>


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
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <sec:authentication
							property="principal.username" /> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${createAddressUrl}">Add address</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="${logoutURL}">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>