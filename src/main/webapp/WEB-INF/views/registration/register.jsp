<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="registration.view.account.registration"/></title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/invalid-inputs.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<section class="login-page">
    <h2><spring:message code="registration.view.create.an.account"/></h2>
    <form:form id="register-form" modelAttribute="userDto">
        <div class="form-group">
            <spring:message code="registration.view.first.name" var="registrationViewFirstName"/>
            <form:input path="firstName" placeholder="${registrationViewFirstName}" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                <c:if test = "${firstNameErrorMessage != null}">
                    <span id="firstName.errors" class="invalid-input">${firstNameErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <spring:message code="registration.view.last.name" var="registrationViewLastName"/>
            <form:input path="lastName" placeholder="${registrationViewLastName}" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${lastNameErrorMessage != null}">
                    <span id="lastName.errors" class="invalid-input">${lastNameErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <spring:message code="registration.view.email" var="registrationViewEmail"/>
            <form:input path="email" type="email" placeholder="${registrationViewEmail}" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${emailErrorMessage != null}">
                    <span id="email.errors" class="invalid-input">${emailErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <spring:message code="registration.view.password" var="registrationViewPassword"/>
            <form:input path="password" type="password" placeholder="${registrationViewPassword}" cssErrorClass="invalid-input"/>
            <div class="invalid-input">
                 <c:if test = "${passwordErrorMessage != null}">
                    <span id="password.errors" class="invalid-input">${passwordErrorMessage}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <spring:message code="registration.view.confirm.password" var="registrationViewConfirmPassword"/>
            <form:input path="confirmPassword" type="password" placeholder="${registrationViewConfirmPassword}"
                        cssErrorClass="invalid-input"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border"><spring:message code="registration.view.log.in"/></a>
            <button class="btn" type="submit"><spring:message code="registration.view.create.an.account.button"/></button>
        </div>

        <input type="hidden" name="locale" value="${pageContext.response.locale}"/>

    </form:form>

</section>

<jsp:include page="../footer.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<%-- Client side validation script --%>
<%--<script src="<c:url value="/resources/js/register-form.js"/>"></script>--%>
</body>
</html>

