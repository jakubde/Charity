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
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<p>Dziękujemy, na adres <b>${email}</b> został wysłany link aktywujący konto. Link jest ważny przez 24 godziny.</p>
<a href="/">Powrót do strony głównej</a>


<jsp:include page="footer.jsp"/>
</body>
</html>
