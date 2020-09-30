<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- TODO - i18n --%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Rejestracja konta</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/invalid-inputs.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>


<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form id="register-form" modelAttribute="userDto">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                <c:if test = "${firstNameErrorMessage != null}">
                    <span id="firstName.errors" class="invalid-input">${firstNameErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${lastNameErrorMessage != null}">
                    <span id="lastName.errors" class="invalid-input">${lastNameErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${emailErrorMessage != null}">
                    <span id="email.errors" class="invalid-input">${emailErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${passwordErrorMessage != null}">
                    <span id="password.errors" class="invalid-input">${passwordErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <form:input path="confirmPassword" type="password" placeholder="Powtórz hasło"
                        cssErrorClass="invalid-input"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>

        <input type="hidden" name="locale" value="${pageContext.response.locale}"

    </form:form>

</section>

<jsp:include page="../footer.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<%-- Client side validation script --%>
<%--<script src="<c:url value="/resources/js/register-form.js"/>"></script>--%>
</body>
</html>

