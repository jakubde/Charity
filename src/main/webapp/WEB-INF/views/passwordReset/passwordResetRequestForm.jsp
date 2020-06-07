<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%--    TODO - i18n--%>
    <title>Zmień hasło</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<section class="login-page">
    <%--    TODO - i18n--%>

    <h2><spring:message code="remind.password"/></h2>
    <form method="post" action="<c:url value="/password-reset/request"/>">
        <div class="form-group inline-form-divs">
            <label for="email"></label>
            <input type="email" id="email" name="userEmail" placeholder="Email" required autofocus/>
        </div>
        <div class="form-group form-group--buttons inline-form-divs">
            <button class="btn" type="submit">Zatwierdź</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>

<jsp:include page="../footer.jsp"/>
</body>
</html>