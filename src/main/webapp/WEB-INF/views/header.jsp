<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="container container--70">

    <ul class="nav--actions">
        <sec:authorize access="isAuthenticated()">
            <li class="logged-user">

                Witaj ${firstName}

                <ul class="dropdown">
                    <sec:authorize access="hasRole('USER')">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Ustawienia</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="/adminPanel">Panel admina</a></li>
                    </sec:authorize>
                    <li>
                        <form id="logout_form" method="post" action="/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="javascript:{}"
                               onclick="document.getElementById('logout_form').submit(); return false;">Wyloguj</a>
                        </form>
                    </li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
    </ul>


    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
        <li><a href="/#link1" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#link2" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#link3" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <sec:authorize access="hasRole('USER')">
            <li><a href="/donate" class="btn btn--without-border">Przekaż dary</a></li>
        </sec:authorize>
        <li><a href="/#link4" class="btn btn--without-border">Kontakt</a></li>
        <li><p class="btn btn--without-border"><a href="?lang=en">EN</a>|<a href="?lang=pl">PL</a></p></li>
<%--        <li>ten tekst...: <spring:message code="app.title" text="default text" /></li>--%>
        <li>Current Locale : ${pageContext.response.locale}</li>
    </ul>
</nav>