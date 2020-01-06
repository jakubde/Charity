<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Lista instytucji</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/adminPanel/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet"
          type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/adminPanel/css/sb-admin-2.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<c:url value="/resources/adminPanel/vendor/datatables/dataTables.bootstrap4.min.css"/>" rel="stylesheet">

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

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Lista instytucji</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Nazwa</th>
                                    <th>Opis</th>
                                    <th>Akcja</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Nazwa</th>
                                    <th>Opis</th>
                                    <th>Akcja</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${institutionDtos}" var="institutionDto">
                                    <tr>
                                        <td>${institutionDto.name}</td>
                                        <td>${institutionDto.description}</td>
                                        <td style="text-align:center; white-space:nowrap"><a href="/institutions/edit/${institutionDto.id}">Edytuj</a>　|　<a href="#" data-href="/institutions/delete/${institutionDto.id}" data-toggle="modal" data-target="#deleteModal">Usuń</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br/>
                        <h6 class="text-right"><small><small>Generated with <a id="dataTablesSite" href="https://datatables.net/">DataTables</a></small></small></h6>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <jsp:useBean id="date" class="java.util.Date" />
                    <span>Copyright &copy; Oddam w dobre ręce <fmt:formatDate value="${date}" pattern="yyyy"/></span>
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

<!-- Delete Modal-->
<jsp:include page="../adminPanelDeleteModal.jsp"/>

<p id="modalToggle" hidden>${dataViolationFlag}</p>

<!-- Data Violation Modal-->
<jsp:include page="dataViolationModal.jsp"/>

<!-- Bootstrap core JavaScript-->
<script src="../../../resources/adminPanel/vendor/jquery/jquery.min.js"></script>
<script src="../../../resources/adminPanel/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../../resources/adminPanel/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../../../resources/adminPanel/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../../../resources/adminPanel/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../../../resources/adminPanel/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="../../../resources/adminPanel/js/demo/datatables-demo.js"></script>
<script src="../../../resources/adminPanel/utils/js/deleteModal.js"></script>
<script src="../../../resources/adminPanel/utils/js/dataViolationModal.js"></script>



</body>
</html>