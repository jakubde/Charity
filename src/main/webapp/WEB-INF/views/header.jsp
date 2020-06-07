<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- TODO - i18n --%>
<header>
    <nav class="container container--70">

        <ul class="nav--actions">
            <sec:authorize access="isAuthenticated()">
                <li class="logged-user">

                    Witaj ${sessionScope.firstName}

                    <ul class="dropdown">
                        <sec:authorize access="hasRole('USER')">
                            <li><a href="#">Profil</a></li>
                            <li><a href="#">Ustawienia</a></li>
                            <li><a href="#">Moje zbiórki</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a href="<c:url value="/adminPanel"/>">Panel admina</a></li>
                        </sec:authorize>
                        <li>
                            <form id="logout_form" method="post" action="<c:url value="/logout"/>">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="javascript:{}"
                                   onclick="document.getElementById('logout_form').submit(); return false;">Wyloguj</a>
                            </form>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="<c:url value="/login"/>" class="btn btn-small btn--without-border">Zaloguj</a></li>
                <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="<c:url value="/#link1"/>" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="<c:url value="/#link2"/>" class="btn btn--without-border">O nas</a></li>
            <li><a href="<c:url value="/#link3"/>" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <sec:authorize access="hasRole('USER')">
                <li><a href="<c:url value="/donate"/>" class="btn btn--without-border">Przekaż dary</a></li>
            </sec:authorize>
            <li><a href="<c:url value="/#link4"/>" class="btn btn--without-border">Kontakt</a></li>
            <li><a href="?lang=en" class="btn btn--without-border">EN 🇬🇧</a></li>
            <li><a href="?lang=pl" class="btn btn--without-border">PL 🇵🇱</a></li>
        </ul>
    </nav>
</header>