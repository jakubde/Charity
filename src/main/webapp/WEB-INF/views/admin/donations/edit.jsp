<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- TODO i18n --%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Edycja donacji</title>
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/favicon.png"/>"/>

        <%-- TODO - CDNs with local fallbacks --%>
        <!-- Font Awesome Free 5.10.2 -->
        <link href="<c:url value="/resources/adminPanel/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
              type="text/css">
        <!-- Google Fonts - Nunito -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">
        <!-- SB Admin 2 v4.0.7 styles -->
        <link href="<c:url value="/resources/adminPanel/css/sb-admin-2.min.css"/>" rel="stylesheet">
        <!-- Bootstrap Multiselect v2.0 styles -->
        <link href="<c:url value="/resources/adminPanel/vendor/bootstrap-multiselect/bootstrap-multiselect.css"/>"
              rel="stylesheet">
        <!-- Bootstrap Multiselect additional styles -->
        <link href="<c:url value="/resources/adminPanel/vendor/bootstrap-multiselect/additional-styles.css"/>"
              rel="stylesheet">

        <%-- TODO - end --%>
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="../adminPanelSidebar.jsp"/>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <jsp:include page="../adminPanelTopbar.jsp"/>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Content Row -->
                        <div class="row justify-content-md-center">

                            <!-- First Column -->
                            <div class="col-5">

                                <!-- Custom Text Color Utilities -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Edycja donacji</h6>
                                    </div>
                                    <div class="card-body">
                                        <form:form method="post" cssClass="user" modelAttribute="donationDto"
                                                   id="donationEditForm">

                                            <div class="form-group">
                                                <label for="userEmail"><b>Użytkownik</b></label>
                                                <input id="userEmail" name="userEmail"
                                                       class="form-control form-control-user"
                                                       placeholder="Użytkownik"
                                                       value="${userEmail}"/>
                                            </div>

                                            <div class="form-group">
                                                <label for="institutionSelect" class="multiple-select"><b>Instytucja</b></label>
                                                <select name="institutionId" id="institutionSelect">
                                                    <c:forEach items="${institutionMap}" var="institution">
                                                        <c:choose>
                                                            <c:when test="${language.equals('pl')}">
                                                                <c:choose>
                                                                    <c:when test="${donationDto.institutionId.equals(institution.key)}">
                                                                        <option selected value="${institution.key}">${institution.value.get(0)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${institution.key}">${institution.value.get(0)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${donationDto.institutionId.equals(institution.key)}">
                                                                        <option selected value="${institution.key}">${institution.value.get(1)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${institution.key}">${institution.value.get(1)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label for="statusSelect" class="multiple-select"><b>Status</b></label>
                                                <select name="statusId" id="statusSelect">
                                                    <c:forEach items="${donationStatusMap}" var="status">
                                                        <c:choose>
                                                            <c:when test="${language.equals('pl')}">
                                                                <c:choose>
                                                                    <c:when test="${donationDto.donationStatusId.equals(status.key)}">
                                                                        <option selected value="${status.key}">${status.value.get(0)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${status.key}">${status.value.get(0)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${donationDto.donationStatusId.equals(status.key)}">
                                                                        <option selected value="${status.key}">${status.value.get(1)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${status.key}">${status.value.get(1)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label for="categorySelect" class="multiple-select"><b>Kategorie</b></label>
                                                <select name="categoryIdListAsString" id="categorySelect" multiple="multiple">
                                                    <c:forEach items="${categoryMap}" var="category">
                                                        <c:choose>
                                                            <c:when test="${language.equals('pl')}">

                                                                <c:choose>
                                                                    <c:when test="${donationDto.categoryIdList.contains(category.key)}">
                                                                        <option selected
                                                                                value="${category.key}">${category.value.get(0)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${category.key}">${category.value.get(0)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </c:when>
                                                            <c:otherwise>

                                                                <c:choose>
                                                                    <c:when test="${donationDto.categoryIdList.contains(category.key)}">
                                                                        <option selected
                                                                                value="${category.key}">${category.value.get(1)}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${category.key}">${category.value.get(1)}</option>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </c:otherwise>
                                                        </c:choose>

                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <form:label path="quantity"><b>Liczba worków</b></form:label>
                                                <form:input type="number" path="quantity"
                                                            cssClass="form-control form-control-user"
                                                            placeholder="Liczba worków"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="city"><b>Miasto</b></form:label>
                                                <form:input path="city" cssClass="form-control form-control-user"
                                                            placeholder="Miasto"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="street"><b>Ulica i nr domu</b></form:label>
                                                <form:input path="street" cssClass="form-control form-control-user"
                                                            placeholder="Ulica i nr domu"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="zipCode"><b>Kod pocztowy</b></form:label>
                                                <form:input path="zipCode" cssClass="form-control form-control-user"
                                                            placeholder="Kod pocztowy"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="telephoneNumber"><b>Nr telefonu</b></form:label>
                                                <form:input type="tel" path="telephoneNumber"
                                                            cssClass="form-control form-control-user"
                                                            placeholder="Nr telefonu"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="pickUpDate"><b>Data odbioru</b></form:label>
                                                <form:input type="date" path="pickUpDate"
                                                            cssClass="form-control form-control-user"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="pickUpTime"><b>Godzina odbioru</b></form:label>
                                                <form:input type="time" path="pickUpTime"
                                                            cssClass="form-control form-control-user"></form:input>
                                                </div>
                                                <div class="form-group">
                                                <form:label path="pickUpComment"><b>Komentarz dot. odbioru</b></form:label>
                                                <form:textarea path="pickUpComment" cssClass="form-control"
                                                               placeholder="Komentarz dot. odbioru"></form:textarea>
                                                </div>
                                                <a href="javascript:{}"
                                                   onclick="document.getElementById('donationEditForm').submit();"
                                                   class="btn btn-primary btn-user btn-block">
                                                    Zatwierdź
                                                </a>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <jsp:include page="../adminPanelFooter.jsp"/>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button -->
        <jsp:include page="../adminPanelScrollToTopButton.jsp"/>

        <!-- Logout Modal -->
        <jsp:include page="../adminPanelLogoutModal.jsp"/>

        <%-- TODO - CDNs with local fallbacks --%>
        <!-- jQuery v3.4.1 -->
        <script src="<c:url value="/resources/adminPanel/vendor/jquery/jquery.min.js"/>"></script>
        <!-- Bootstrap v4.3.1 -->
        <script src="<c:url value="/resources/adminPanel/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
        <!-- jQuery Easing v1.4.1 -->
        <script src="<c:url value="/resources/adminPanel/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
        <!-- SB Admin 2 v4.0.7 -->
        <script src="<c:url value="/resources/adminPanel/js/sb-admin-2.min.js"/>"></script>
        <!-- Bootstrap Multiselect v2.0 -->
        <script src="<c:url value="/resources/adminPanel/vendor/bootstrap-multiselect/bootstrap-multiselect.js"/>"></script>
        <!-- Bootstrap Multiselect call -->
        <script type="text/javascript">
                                                       $('#categorySelect').multiselect();
                                                       $('#statusSelect').multiselect();
                                                       $('#institutionSelect').multiselect();
        </script>
        <%-- TODO - end --%>

    </body>
</html>
