<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- TODO - i18n --%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Zaloguj się</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form method="post" action="<c:url value="/login"/>">
        <div class="form-group">
            <label for="username"></label>
            <input type="text" id="username" name="username" placeholder="Email" required autofocus/>
        </div>

        <div class="form-group">
            <label for="password"></label>
            <input type="password" id="password" name="password" placeholder="Hasło" required/>
            <a href="<c:url value="/password-reset/request"/>"
               class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>