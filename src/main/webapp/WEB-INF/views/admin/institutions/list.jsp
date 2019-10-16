<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 16.10.19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/list.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../../header.jsp"/>
</header>

<h2>Zaufane instytucje</h2>

<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
        <th style="text-align: center;" colspan="2">Akcja</th>
    </tr>
    <c:forEach items="${institutions}" var="institution">
    <tr>
        <td>${institution.name}</td>
        <td>${institution.description}</td>
        <td><a href="/institutions/add/${institution.id}">Edytuj</a> </td>
        <td><a href="/institutions/delete/${institution.id}">Usuń</a> </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
