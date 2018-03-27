<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ include file="/WEB-INF/include/footer.jsp"%>


<c:url value="/users/delete" var="deleteUserURL"/>
<c:url value="/users/edit" var="editUserURL"/>

<div class="container">

    <h1>List of users</h1>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th class="text-center col-md-1">Id</th>
                    <th class="text-center">First Name</th>
                    <th class="text-center">Last Name</th>
                    <th class="text-center">Email</th>
                    <th class="text-center col-md-1">Edit</th>
                    <th class="text-center col-md-1">Delete</th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${usersList}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td> <a href="${editUserURL}/${user.userId}" class="btn btn-primary">Edit</a> </td>
                        <td> <a href="${deleteUserURL}/${user.userId}" class="btn btn-danger delete-btn">Delete</a> </td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</div>

