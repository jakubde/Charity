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
        <title><spring:message code="form.make.a.donation"/></title>
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/wickedpicker@0.4.3/stylesheets/wickedpicker.css" integrity="sha256-YmXb+aiiMytRNxu8hYYMXvKFgL+6eh/+tD/V66YV8qw=" crossorigin="anonymous">
    </head>
    <body>
        <header class="header--form-page">
            <jsp:include page="header.jsp"/>

            <div class="slogan container container--90">
                <div class="slogan--item">
                    <h1>
                        <spring:message code="donate.things.you.no.longer.want"/><br/>
                        <span class="uppercase"><spring:message code="to.those.in.need"/></span>
                    </h1>

                    <div class="slogan--steps">
                        <div class="slogan--steps-title"><spring:message code="it.only.takes.four.simple.steps"/></div>
                        <ul class="slogan--steps-boxes">
                            <li>
                                <div><em>1</em><span><spring:message code="form.choose.your.items"/></span></div>
                            </li>
                            <li>
                                <div><em>2</em><span><spring:message code="bag.them"/></span></div>
                            </li>
                            <li>
                                <div><em>3</em><span><spring:message code="choose.a.foundation"/></span></div>
                            </li>
                            <li>
                                <div><em>4</em><span><spring:message code="order.a.courier"/></span></div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>

        <section class="form--steps">
            <div class="form--steps-instructions">
                <div class="form--steps-container">
                    <h3><spring:message code="form.important"/></h3>
                    <p data-step="1" class="active">
                        <spring:message code="fill.in.the.details"/>
                    </p>
                    <p data-step="2">
                        <spring:message code="fill.in.the.details"/>
                    </p>
                    <p data-step="3">
                        <spring:message code="select.one.where.your.parcel.will.go"/>
                    </p>
                    <p data-step="4">
                        <spring:message code="enter.the.address.and.date"/>
                    </p>
                </div>
            </div>

            <div class="form--steps-container">
                <div class="form--steps-counter"><spring:message code="form.step"/><span>1</span>/4</div>

                <form:form method="post" modelAttribute="donation">

                    <!-- STEP 1: class .active is switching steps -->
                    <div data-step="1" class="active">
                        <h3><spring:message code="select.what.you.want.to.donate"/></h3>

                        <c:forEach items="${categories}" var="category">
                            <div class="form-group form-group--checkbox">
                                <label>
                                    <form:checkbox path="categoryIdList" cssClass="auxiliary" value="${category.id}"/>
                                    <c:choose>
                                        <c:when test="${language.equals('pl')}">
                                            <span class="description"> ${category.name} </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="description"> ${category.nameEng} </span>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn next-step"><spring:message code="form.next"/></button>
                        </div>
                    </div>

                    <!-- STEP 2 -->
                    <div data-step="2">
                        <h3><spring:message code="enter.the.number.of.bags"/></h3>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="number.of.bags"/>
                                <form:input path="quantity" type="number" step="1" min="1"/>
                            </label>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step"><spring:message code="form.previous"/></button>
                            <button type="button" class="btn next-step"><spring:message code="form.next"/></button>
                        </div>
                    </div>

                    <!-- STEP 3 -->
                    <div data-step="3">
                        <h3><spring:message code="choose.an.organisation"/></h3>

                        <c:forEach items="${institutions}" var="institution">
                            <div class="form-group form-group--checkbox">
                                <label>
                                    <form:radiobutton path="institutionId" value="${institution.id}"/>
                                    <span class="checkbox radio"></span>
                                    <span class="description">
                                        <c:choose>
                                            <c:when test="${language.equals('pl')}">
                                                <div class="title">${institution.name}</div>
                                                <div class="subtitle">${institution.description}</div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="title">${institution.nameEng}</div>
                                                <div class="subtitle">${institution.descriptionEng}</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                </label>
                            </div>
                        </c:forEach>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step"><spring:message code="form.previous"/></button>
                            <button type="button" class="btn next-step"><spring:message code="form.next"/></button>
                        </div>
                    </div>

                    <!-- STEP 4 -->
                    <div data-step="4">
                        <h3><spring:message code="provide.the.address"/></h3>

                        <div class="form-section form-section--columns">
                            <div class="form-section--column">
                                <h4><spring:message code="collection.address"/></h4>
                                <div class="form-group form-group--inline">
                                    <label> <spring:message code="form.street"/>
                                        <form:input path="street"/>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label> <spring:message code="form.city"/>
                                        <form:input path="city"/>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        <spring:message code="form.postcode"/>
                                        <form:input path="zipCode"/>
                                    </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        <spring:message code="form.telephone.number"/>
                                        <form:input path="telephoneNumber" type="phone"/>
                                    </label>
                                </div>
                            </div>

                            <div class="form-section--column">
                                <h4><spring:message code="form.collection.date"/></h4>
                                <div class="form-group form-group--inline">
                                    <label> <spring:message code="form.date"/>
                                        <form:input path="pickUpDate" type="date"/>
                                    </label>
                                </div>

                                <c:choose>
                                    <c:when test="${language.equals('pl')}">
                                        <div class="form-group form-group--inline">
                                            <label> <spring:message code="form.time"/>
                                                <input id="pickTime" name="pickTime" type="text" class="timepicker-pl"/>
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group form-group--inline">
                                            <label> <spring:message code="form.time"/>
                                                <input id="pickTime" name="pickTime" type="text" class="timepicker-eng"/>
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="form-group form-group--inline">
                                    <label>
                                        <spring:message code="notes.for.courier"/>
                                        <form:textarea path="pickUpComment" rows="5"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step"><spring:message code="form.previous"/></button>
                            <button type="button" class="btn next-step"><spring:message code="form.next"/></button>
                        </div>
                    </div>

                    <!-- STEP 5 -->
                    <div data-step="5">
                        <h3><spring:message code="summary.of.your.donation"/></h3>

                        <div class="summary">
                            <div class="form-section">
                                <h4><spring:message code="you.donate"/></h4>
                                <ul>
                                    <li>
                                        <span class="icon icon-bag"></span>
                                        <span class="summary--text"><spring:message code="form.bags"/>&nbsp</span>
                                        <span class="summary--text">&nbsp&nbsp&nbsp<spring:message code="form.categories"/>&nbsp</span>
                                    </li>

                                    <li>
                                        <span class="icon icon-hand"></span>
                                        <span class="summary--text"><spring:message code="form.for"/>&nbsp</span>
                                    </li>
                                </ul>
                            </div>

                            <div class="form-section form-section--columns">
                                <div class="form-section--column">
                                    <h4><spring:message code="collection.address.summary"/></h4>
                                    <ul>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                    </ul>
                                </div>

                                <div class="form-section--column">
                                    <h4><spring:message code="form.collection.date.summary"/></h4>
                                    <ul>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step"><spring:message code="form.previous"/></button>
                            <button type="submit" class="btn"><spring:message code="form.submit"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>

        <%-- jQuery and local fallback--%>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
        <script>
            if (typeof jQuery == 'undefined') {
                document.write(unescape("%3Cscript src='<c:url value="/resources/adminPanel/vendor/jquery/jquery.min.js"/>'%3E%3C/script%3E"));
            }
        </script>

        <script src="<c:url value="/resources/js/app.js"/>"></script>
        <%-- Wickedpicker v0.4.3--%>
        <script src="https://cdn.jsdelivr.net/npm/wickedpicker@0.4.3/dist/wickedpicker.min.js" integrity="sha256-J6UQjzPUxbvA3vvAasHb1Jez5TKdzWTw0hPCizX+jCY=" crossorigin="anonymous"></script>
        <%-- Wickedpicker setup file--%>
        <script src="<c:url value="/resources/adminPanel/utils/js/wickedpicker-setup.js"/>"></script>
        <script type="text/javascript">
            $('.timepicker-eng').wickedpicker();
            $('.timepicker-pl').wickedpicker();
        </script>
    </body>
</html>