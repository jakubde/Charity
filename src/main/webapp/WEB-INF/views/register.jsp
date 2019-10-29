<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 12.10.19
  Time: 05:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form id="register-form" modelAttribute="user" onsubmit="return passwordValidation()">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" />
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" />
        </div>
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email" />
            <c:if test="${error.equals('userAlreadyExists')}">
                <p style="color: red" >Użytkownik o adresie ${email} już isnieje. Wpisz inny email.</p>
            </c:if>
        </div>
        <div class="form-group">
            <form:input path="password" id="pass1" type="password" placeholder="Hasło" /><form:errors path="password"/>
            <span id='message1'></span>
        </div>
        <div class="form-group">
            <input type="password" id="pass2" placeholder="Powtórz hasło" />
            <span id='message'></span>
        </div>
        
        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>

        <input type="hidden" name="locale" value="${pageContext.response.locale}"

    </form:form>

</section>

<jsp:include page="footer.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../resources/js/register-form.js"></script>
</body>
</html>
