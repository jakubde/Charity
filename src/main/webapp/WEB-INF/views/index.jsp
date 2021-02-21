<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="give.it.to.the.right.hands"/></title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

    <header class="header--main-page">
        <jsp:include page="header.jsp"/>

        <div class="slogan container container--90">
            <div class="slogan--item">
                <h1>
                    <spring:message code="start.helping"/>
                    <br/>
                    <spring:message code="give.unwanted.items.to.good.hands"/>
                </h1>
            </div>
        </div>
    </header>

    <section class="stats">
        <div class="container container--85">
            <div class="stats--item">
                <em>${donationSum}</em>
                <h3><spring:message code="donated.bags"/></h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                    tempora!</p>
            </div>

            <div class="stats--item">
                <em>${donatedInstitutionsSum}</em>
                <h3><spring:message code="supported.organisations"/></h3>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                    quam.</p>
            </div>
        </div>
    </section>

    <section class="steps" id="link1">
        <h2><spring:message code="in.just.four.simple.steps"/></h2>

        <div class="steps--container">
            <div class="steps--item">
                <span class="icon icon--hands"></span>
                <h3><spring:message code="choose.your.items"/></h3>
                <p><spring:message code="clothes.toys.equipment.and.more"/></p>
            </div>
            <div class="steps--item">
                <span class="icon icon--arrow"></span>
                <h3><spring:message code="pack.it.up"/></h3>
                <p><spring:message code="use.the.bin.bags"/></p>
            </div>
            <div class="steps--item">
                <span class="icon icon--glasses"></span>
                <h3><spring:message code="decide.who.you.want.to.help"/></h3>
                <p><spring:message code="choose.a.trusted.location"/></p>
            </div>
            <div class="steps--item">
                <span class="icon icon--courier"></span>
                <h3><spring:message code="book.a.courier"/></h3>
                <p><spring:message code="courier.will.come.at.a.convenient.time"/></p>
            </div>
        </div>

        <a href="<c:url value="/register"/>" class="btn btn--large"><spring:message code="create.an.account"/></a>
    </section>

    <section class="about-us" id="link2">
        <div class="about-us--text">
            <h2><spring:message code="index.about.us"/></h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
                optio esse quisquam illo omnis.</p>
            <img src="<c:url value="/resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
        </div>
        <div class="about-us--image"><img src="<c:url value="/resources/images/about-us.jpg"/>" alt="People in circle"/>
        </div>
    </section>

    <section class="help" id="link3">
        <h2><spring:message code="who.do.we.help"/></h2>

        <!-- SLIDE 1 -->
        <div class="help--slides active" data-id="1">
            <p><spring:message code="in.our.database.you.will.find"/></p>

            <ul class="help--slides-items">
                <li>
                    <c:forEach items="${institutions}" var="institution" varStatus="status">
                        <div class="col">
                            <div class="title">${institution.get(0)}</div>
                            <div class="subtitle">${institution.get(1)}</div>
                        </div>

                        <c:if test="${status.count % 2 == 0 && status.count != institutions.size()}">
                        </li>
                        <li>
                        </c:if>
                    </c:forEach>
                </li>
            </ul>
        </div>

    </section>
    <p id="link4"></p>
    <jsp:include page="footer.jsp"/>

    <script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
