<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/list.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../../header.jsp"/>
</header>

<h2>Dodawanie instytucji</h2>


<form:form cssClass="mgmt-form" method="post" modelAttribute="institution">
    <div class="mgmt-form">
        <p>Nazwa: <form:input path="name"/></p>

        <p>Opis: <form:textarea path="description"/></p>

        <input type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
