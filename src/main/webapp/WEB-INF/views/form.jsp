<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- TODO - i18n --%>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <title>Przekaż dary</title>
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
                        Oddaj rzeczy, których już nie chcesz<br/>
                        <span class="uppercase">potrzebującym</span>
                    </h1>

                    <div class="slogan--steps">
                        <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                        <ul class="slogan--steps-boxes">
                            <li>
                                <div><em>1</em><span>Wybierz rzeczy</span></div>
                            </li>
                            <li>
                                <div><em>2</em><span>Spakuj je w worki</span></div>
                            </li>
                            <li>
                                <div><em>3</em><span>Wybierz fundację</span></div>
                            </li>
                            <li>
                                <div><em>4</em><span>Zamów kuriera</span></div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>

        <section class="form--steps">
            <div class="form--steps-instructions">
                <div class="form--steps-container">
                    <h3>Ważne!</h3>
                    <p data-step="1" class="active">
                        Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                        wiedzieć komu najlepiej je przekazać.
                    </p>
                    <p data-step="2">
                        Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                        wiedzieć komu najlepiej je przekazać.
                    </p>
                    <p data-step="3">
                        Wybierz jedną, do
                        której trafi Twoja przesyłka.
                    </p>
                    <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
                </div>
            </div>

            <div class="form--steps-container">
                <div class="form--steps-counter">Krok <span>1</span>/4</div>

                <form:form method="post" modelAttribute="donation">

                    <!-- STEP 1: class .active is switching steps -->
                    <div data-step="1" class="active">
                        <h3>Zaznacz co chcesz oddać:</h3>

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
                            <button type="button" class="btn next-step">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 2 -->
                    <div data-step="2">
                        <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                        <div class="form-group form-group--inline">
                            <label>
                                Liczba 60l worków:
                                <form:input path="quantity" type="number" step="1" min="1"/>
                            </label>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 3 -->
                    <div data-step="3">
                        <h3>Wybierz organizację, której chcesz pomóc:</h3>

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
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 4 -->
                    <div data-step="4">
                        <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                        <div class="form-section form-section--columns">
                            <div class="form-section--column">
                                <h4>Adres odbioru</h4>
                                <div class="form-group form-group--inline">
                                    <label> Ulica
                                        <form:input path="street"/>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label> Miasto
                                        <form:input path="city"/>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Kod pocztowy
                                        <form:input path="zipCode"/>
                                    </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Numer telefonu
                                        <form:input path="telephoneNumber" type="phone"/>
                                    </label>
                                </div>
                            </div>

                            <div class="form-section--column">
                                <h4>Termin odbioru</h4>
                                <div class="form-group form-group--inline">
                                    <label> Data
                                        <form:input path="pickUpDate" type="date"/>
                                    </label>
                                </div>

                                <c:choose>
                                    <c:when test="${language.equals('pl')}">
                                        <div class="form-group form-group--inline">
                                            <label> Godzina
                                                <input id="pickTime" name="pickTime" type="text" class="timepicker-pl"/>
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group form-group--inline">
                                            <label> Godzina
                                                <input id="pickTime" name="pickTime" type="text" class="timepicker-eng"/>
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Uwagi dla kuriera
                                        <form:textarea path="pickUpComment" rows="5"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 5 -->
                    <div data-step="5">
                        <h3>Podsumowanie Twojej darowizny</h3>

                        <div class="summary">
                            <div class="form-section">
                                <h4>Oddajesz:</h4>
                                <ul>
                                    <li>
                                        <span class="icon icon-bag"></span>
                                        <span class="summary--text">Worków:&nbsp</span>
                                        <span class="summary--text">&nbsp&nbsp&nbspO kategoriach:&nbsp</span>
                                    </li>

                                    <li>
                                        <span class="icon icon-hand"></span>
                                        <span class="summary--text">Dla:&nbsp</span>
                                    </li>
                                </ul>
                            </div>

                            <div class="form-section form-section--columns">
                                <div class="form-section--column">
                                    <h4>Adres odbioru:</h4>
                                    <ul>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                    </ul>
                                </div>

                                <div class="form-section--column">
                                    <h4>Termin odbioru:</h4>
                                    <ul>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="submit" class="btn">Potwierdzam</button>
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