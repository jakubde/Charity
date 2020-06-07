<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%--    TODO - i18n--%>
    <title>Link został wysłany</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="centering space-bottom-top">
    <%-- TODO - i18n --%>
    <h1>Na adres <b>${email}</b> został wysłany link resetujący hasło. Link jest ważny przez 24 godziny.</h1>
    <br>
    <h1><a class="border-padded-a" href="<c:url value="/"/>">Powrót do strony głównej</a></h1>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>