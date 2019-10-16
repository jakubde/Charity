<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<nav class="container container--70">

            <ul class="nav--actions">
                <sec:authorize access="isAuthenticated()">
                <li class="logged-user">

                    Witaj ${firstName}

                    <ul class="dropdown">
                        <li><a href="#">Profil</a></li>
                        <li><a href="#">Ustawienia</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li>
                            <form id="logout_form" method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="javascript:{}" onclick="document.getElementById('logout_form').submit(); return false;">Wyloguj</a>
                            </form>
                        </li>
                    </ul>
                </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="#" class="btn btn--small btn--highlighted">Załóż konto</a></li>
                </sec:authorize>
            </ul>



            <ul>
                <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
                <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
                <li><a href="#" class="btn btn--without-border">O nas</a></li>
                <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
                <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
            </ul>
        </nav>

</body>
</html>