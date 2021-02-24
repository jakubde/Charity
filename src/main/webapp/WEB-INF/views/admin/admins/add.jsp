<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><spring:message code="adding.an.admin"/></title>
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
                                        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="adding.an.admin"/></h6>
                                    </div>
                                    <div class="card-body">
                                        <form:form method="post" cssClass="user" modelAttribute="admin" id="adminAddForm">
                                            <div class="form-group">
                                                <form:label path="email"><b><spring:message code="admins.add.email"/></b></form:label>
                                                <spring:message code="admins.add.email" var="adminsAddEmail"/>
                                                <form:input path="email" type="email" class="form-control form-control-user"
                                                            placeholder="${adminsAddEmail}"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="password"><b><spring:message code="admins.add.password"/></b></form:label>
                                                <spring:message code="admins.add.password" var="adminsAddPassword"/>
                                                <form:input path="password" type="password"
                                                            class="form-control form-control-user" placeholder="${adminsAddPassword}"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="firstName"><b><spring:message code="admins.add.first.name"/></b></form:label>
                                                <spring:message code="admins.add.first.name" var="adminsAddFirstName"/>
                                                <form:input path="firstName" type="text" class="form-control form-control-user"
                                                            placeholder="${adminsAddFirstName}"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="lastName"><b><spring:message code="admins.add.last.name"/></b></form:label>
                                                <spring:message code="admins.add.last.name" var="adminsAddLastName"/>
                                                <form:input path="lastName" type="text" class="form-control form-control-user"
                                                            placeholder="${adminsAddLastName}"/>
                                            </div>
                                            <hr>
                                            <a href="javascript:{}" onclick="document.getElementById('adminAddForm').submit();"
                                               class="btn btn-primary btn-user btn-block">
                                                <spring:message code="admins.add.confirm"/>
                                            </a>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <%-- TODO - end --%>
    </body>
</html>
