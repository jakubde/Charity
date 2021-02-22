<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <%--    TODO - i18n--%>
        <title><spring:message code="confirm.password.change"/></title>
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/invalid-inputs.css"/>">
    </head>
    <body>
        <jsp:include page="../header.jsp"/>

        <div class="centering space-bottom-top">
            <%-- TODO - i18n --%>
            <h2><spring:message code="confirm.password.change"/></h2>

            <form:form modelAttribute="passwordResetDto" method="post" action="/password-reset/confirm">
                <div class="row">
                    <div class="column-center">
                        <div class="column">
                            <div class="column-padding">
                                <spring:bind path="password">
                                    <div class="form-group">
                                        <form:label path="password" for="pass">
                                            <spring:message code="confirm.new.password" var="newPasswordPlaceholder" />
                                            <form:password path="password" id="pass" placeholder="${newPasswordPlaceholder}"
                                                cssClass="wide-input" cssErrorClass="wide-input invalid-input"/>
                                                <form:errors path="password"/>
                                        </form:label>
                                    </div>
                                </spring:bind>

                                <spring:bind path="confirmPassword">
                                    <div class="form-group">
                                        <form:label path="confirmPassword" for="confirmPass">
                                            <spring:message code="confirm.confirm.password" var="confirmPasswordPlaceholder" />
                                            <form:password path="confirmPassword" id="confirmPass" placeholder="${confirmPasswordPlaceholder}"
                                                cssClass="wide-input" cssErrorClass="wide-input invalid-input"/>
                                                <form:errors path="confirmPassword"/>
                                        </form:label>
                                    </div>
                                </spring:bind>

                                <input type="hidden" name="token" value="${token}">
                            </div>
                        </div>
                        <div class="column">
                            <div class="column-padding">
                                <div class="centering form-group">
                                    <button class="btn" type="submit"><spring:message code="confirm.change.password"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>

            <div class="space-bottom-top">
                <h1><a class="border-padded-a" href="<c:url value="/"/>"><spring:message code="back.to.home.page"/></a></h1>
            </div>
        </div>

        <jsp:include page="../footer.jsp"/>
    </body>
</html>