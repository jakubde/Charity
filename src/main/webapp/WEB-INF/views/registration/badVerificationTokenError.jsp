<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="bad.token.invalid.link"/></title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="centering space-bottom-top">
    <h2><spring:message code="invalid.registration.link.please.register"/></h2>
    <h1><a href="<c:url value="/register"/>"><spring:message code="bad.token.go.to.registration"/></a></h1>
    <br>
    <h1><a href="<c:url value="/"/>"><spring:message code="bad.token.return.to.home.page"/></a></h1>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
