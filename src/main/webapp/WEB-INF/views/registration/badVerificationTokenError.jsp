<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- TODO - i18n --%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Nieprawidłowy link</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="centering space-bottom-top">
    <h2>Nieprawidłowy link potwierdzający rejestrację. Zarejestruj konto ponownie, aby otrzymać nowy link.</h2>
    <h1><a href="<c:url value="/register"/>">Przejdź do rejestracji</a></h1>
    <br>
    <h1><a href="<c:url value="/"/>">Wróć na stronę główną</a></h1>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
