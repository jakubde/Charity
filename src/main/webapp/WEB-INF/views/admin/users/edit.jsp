<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edycja użytkownika</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/adminPanel/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/adminPanel/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../adminPanelSidebar.jsp"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="../adminPanelTopbar.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Content Row -->
                <div class="row justify-content-md-center">

                    <!-- First Column -->
                    <div class="col-6">

                        <!-- Custom Text Color Utilities -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Edycja użytkownika</h6>
                            </div>
                            <div class="card-body">
                                <form:form method="post" cssClass="user" modelAttribute="userDto" id="userForm">
                                    <div class="form-group">
                                        <form:input path="firstName" type="text" class="form-control form-control-user" id="exampleInputFirstName" placeholder="Imię"/>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="lastName" type="text" class="form-control form-control-user" id="exampleInputLastName" placeholder="Nazwisko"/>
                                    </div>
                                    Enabled:
                                    <div class="form-group text-center">
                                        <form:select path="enabled" cssClass="custom-select">
                                            <form:option value="true"/>
                                            <form:option value="false"/>
                                        </form:select>
                                    </div>
                                    <a href="javascript:{}" onclick="document.getElementById('userForm').submit();" class="btn btn-primary btn-user btn-block">
                                        Zatwierdź
                                    </a>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <jsp:useBean id="date" class="java.util.Date"/>
                        <span>Copyright &copy; Oddam w dobre ręce <fmt:formatDate value="${date}"
                                                                                  pattern="yyyy"/></span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <jsp:include page="../adminPanelLogoutModal.jsp"/>

    <!-- Bootstrap core JavaScript-->
    <script src="../../resources/adminPanel/vendor/jquery/jquery.min.js"></script>
    <script src="../../resources/adminPanel/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../../resources/adminPanel/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../../resources/adminPanel/js/sb-admin-2.min.js"></script>

</body>
</html>