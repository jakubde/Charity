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
    <link rel="stylesheet" href="<c:url value="/resources/css/miscellaneous.css"/>">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div class="centering lowerdistance">
    <p>Wygasł link powierdzający rejestrację. Zarejestruj konto ponownie, aby otrzymać nowy link.</p>
    <h1><a href="/register">Przejdź do rejestracji</a><br></h1>
    <h1><a href="/">Wróć na stronę główną</a></h1>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
