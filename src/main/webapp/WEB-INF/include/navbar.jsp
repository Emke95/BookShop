<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:url value="/users" var="usersURL" />
<c:url value="/profile" var="profileUrl" />
<c:url value="/register" var="createUserURL" />
<c:url value="/logout" var="logoutURL" />
<c:url value="/searchBook" var="searchURL" />
<c:url value="/searchAuth" var="searchAuthURL" />
<c:url value="/books" var="booksUrl" />
<c:url value="/book" var="createBookUrl" />

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

				<sec:authorize access="hasRole('USER')">

					<li><a href="<c:url value="/cart"/>"> Cart[<c:forEach
								var="book" items="${cart.contents}"></c:forEach>]

					</a></li>

				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-center">s
				<li>
					<form class="navbar-form" action="${searchURL}" method="post">
						<div class="form-group">
							<input type="text" name="keyword" class="form-control"
								placeholder="Book title" />
						</div>
						<button type="submit" class="btn btn-default">Search</button>
					</form>
				</li>
				<li>
					<form class="navbar-form" action="${searchAuthURL}" method="post">
						<div class="form-group">
							<input type="text" name="keyword" class="form-control"
								placeholder="Author" />
						</div>
						<button type="submit" class="btn btn-default">Search</button>
					</form>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <sec:authentication
							property="principal.username" /> <span class="caret"></span></a>
					<ul class="dropdown-menu">
					<sec:authorize access="hasRole('USER')">
						<li><a href="${profileUrl}">My Profile</a></li>					
						<li role="separator" class="divider"></li>
						</sec:authorize>
						<li><a href="${logoutURL}">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>