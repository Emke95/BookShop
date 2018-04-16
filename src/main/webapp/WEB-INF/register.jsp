<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<c:url value="/register" var="registerURL"/>
<c:url value="/login" var="loginURL"/>

<div class="container">
    <div class="card card-container">
        <h4> Create new account</h4>
        <form action="${registerURL}" method="post" class="form-signin">
            <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
            <input name="firstName" type="text" id="inputFirstName" class="form-control" placeholder="First name" required>
            <input name="lastName" type="text" id="inputLastName" class="form-control" placeholder="Last name" required>
            <input name="address" type="text" id="inputAddress" class="form-control" placeholder="Shipping Address" required>
            <input name="cardNumber" type="number" id="inputCardNumber" class="form-control" placeholder="Credit Card Number" required>
            <input name="cardCVC" type="number" id="inputCardCVC" class="form-control" placeholder="Credit Card CVC" required>     
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            <input type="password" id="inputRepeatedPassword" class="form-control" placeholder="Repeat password" required>

            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Create</button>
        </form>
        <div class="margin-bottom-10">
            <a href="${loginURL}" class="forgot-password">Back to login</a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>