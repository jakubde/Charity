<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<header>
    <nav class="container container--70">

        <ul class="nav--actions">
            <sec:authorize access="isAuthenticated()">
                <li class="logged-user">

                    <spring:message code="header.hello"/> ${sessionScope.firstName}

                    <ul class="dropdown">
                        <sec:authorize access="hasRole('USER')">
                            <li><a href="#"><spring:message code="header.profile"/></a></li>
                            <li><a href="#"><spring:message code="header.settings"/></a></li>
                            <li><a href="#"><spring:message code="header.my.donations"/></a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a href="<c:url value="/adminPanel"/>"><spring:message code="header.admin.panel"/></a></li>
                        </sec:authorize>
                        <li>
                            <form id="logout_form" method="post" action="<c:url value="/logout"/>">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="javascript:{}"
                                   onclick="document.getElementById('logout_form').submit(); return false;"><spring:message code="header.log.out"/></a>
                            </form>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="<c:url value="/login"/>" class="btn btn-small btn--without-border"><spring:message code="log.in"/></a></li>
                <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted"><spring:message code="create.an.account"/></a></li>
            </sec:authorize>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active"><spring:message code="start"/></a></li>
            <li><a href="<c:url value="/#link1"/>" class="btn btn--without-border"><spring:message code="what.is.it.all.about"/></a></li>
            <li><a href="<c:url value="/#link2"/>" class="btn btn--without-border"><spring:message code="header.about.us"/></a></li>
            <li><a href="<c:url value="/#link3"/>" class="btn btn--without-border"><spring:message code="foundations.and.organisations"/></a></li>
                <sec:authorize access="hasRole('USER')">
                    <li><a href="<c:url value="/donate"/>" class="btn btn--without-border"><spring:message code="make.a.donation"/></a></li>
                </sec:authorize>
            <li><a href="<c:url value="/#link4"/>" class="btn btn--without-border"><spring:message code="contact"/></a></li>
            <li><a href="?lang=en" class="btn btn--without-border">EN 🇬🇧</a></li>
            <li><a href="?lang=pl" class="btn btn--without-border">PL 🇵🇱</a></li>
        </ul>
    </nav>
</header>