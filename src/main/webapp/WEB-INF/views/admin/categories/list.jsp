<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- TODO i18n --%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista kategorii</title>
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
    <!-- DataTables styles -->
    <link href="<c:url value="/resources/adminPanel/vendor/datatables/dataTables.bootstrap4.min.css"/>"
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
                    <div class="col-7">

                        <!-- DataTables -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Lista kategorii</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>Nazwa</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>Nazwa</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach items="${categoryDtoList}" var="categoryDto">
                                            <tr>
                                                <td>${categoryDto.name}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <br/>
                                <h6 class="text-right">
                                    <small>
                                        <small>
                                            Generated with <a href="https://datatables.net/">DataTables</a>
                                        </small>
                                    </small>
                                </h6>
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
<!-- DataTables v1.10.19 -->
<script src="<c:url value="/resources/adminPanel/vendor/datatables/jquery.dataTables.min.js"/>"></script>
<!-- DataTables integration for Bootstrap 4 -->
<script src="<c:url value="/resources/adminPanel/vendor/datatables/dataTables.bootstrap4.min.js"/>"></script>
<!-- Call the DataTables jQuery plugin -->
<script src="<c:url value="/resources/adminPanel/js/charts-and-tables/datatables-call.js"/>"></script>
<%-- TODO - end --%>
</body>
</html>
