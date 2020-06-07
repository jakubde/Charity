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
    <title>Dodawanie donacji</title>
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
                                <h6 class="m-0 font-weight-bold text-primary">Dodawanie donacji</h6>
                            </div>
                            <div class="card-body">
                                <form:form method="post" cssClass="user" modelAttribute="donationDto"
                                           id="donationAddForm">

                                    <div class="form-group">
                                        <input id="userEmail" name="userEmail"
                                               class="form-control form-control-user typeahead"
                                               placeholder="Użytkownik" autocomplete="off"/>
                                    </div>

                                    <div class="form-group">
                                        <input id="institutionName" name="institutionName"
                                               class="form-control form-control-user typeahead"
                                               placeholder="Instytucja" autocomplete="off"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="statusSelect" class="multiple-select"><b>Status:</b></label>
                                        <select name="statusId" id="statusSelect">
                                            <c:forEach items="${donationStatusMap}" var="status">
                                                <option value="${status.key}">${status.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="categorySelect" class="multiple-select"><b>Kategorie:</b></label>
                                        <select name="categoryIdListAsString" id="categorySelect" multiple="multiple">
                                            <c:forEach items="${categoryMap}" var="category">
                                                <option value="${category.key}">${category.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <form:input type="number" path="quantity"
                                                    cssClass="form-control form-control-user"
                                                    placeholder="Liczba worków"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="city" cssClass="form-control form-control-user"
                                                    placeholder="Miasto"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="street" cssClass="form-control form-control-user"
                                                    placeholder="Ulica i nr domu"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="zipCode" cssClass="form-control form-control-user"
                                                    placeholder="Kod pocztowy"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input type="tel" path="telephoneNumber"
                                                    cssClass="form-control form-control-user"
                                                    placeholder="Nr telefonu"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input type="date" path="pickUpDate"
                                                    cssClass="form-control form-control-user"
                                                    placeholder="Data odbioru"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:input type="time" path="pickUpTime"
                                                    cssClass="form-control form-control-user"
                                                    placeholder="Godzina odbioru"></form:input>
                                    </div>
                                    <div class="form-group">
                                        <form:textarea path="pickUpComment" cssClass="form-control"
                                                       placeholder="Komentarz dot. odbioru"></form:textarea>
                                    </div>
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('donationAddForm').submit();"
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
<!-- Typeahead v4.0.2 -->
<script src="<c:url value="/resources/adminPanel/vendor/typeahead/bootstrap3-typeahead.min.js"/>"></script>
<!-- Setting and calling Typeahead -->
<script userEmailList="${userEmailList}" institutionNameList="${institutionNameListAsString}"
        src="<c:url value="/resources/adminPanel/utils/js/typeahead-set-and-call.js"/>"></script>
<!-- Bootstrap Multiselect v2.0 -->
<script src="<c:url value="/resources/adminPanel/vendor/bootstrap-multiselect/bootstrap-multiselect.js"/>"></script>
<!-- Bootstrap Multiselect call -->
<script type="text/javascript">
    $('#categorySelect').multiselect();
    $('#statusSelect').multiselect();
</script>
<%-- TODO - end --%>

</body>
</html>