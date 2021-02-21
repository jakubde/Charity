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
        <title>Zmień status donacji</title>
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
                            <div class="col-7">

                                <!-- Custom Text Color Utilities -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Zmień status donacji</h6>
                                    </div>
                                    <div class="card-body">
                                        <dl class="row">
                                            <dt class="col-sm-3">Email użytkownika:</dt>
                                            <dd class="col-sm-9"><a
                                                    href="/users/profile/${donationDto.userId}">${userEmail}</a></dd>

                                            <dt class="col-sm-3">Kategorie:</dt>
                                            <dd class="col-sm-9">
                                                <ul>
                                                    <c:forEach items="${donationDto.categoryIdList}" var="categoryId">
                                                        <li>${categoryMap.get(categoryId)}</li>
                                                        </c:forEach>
                                                </ul>
                                            </dd>

                                            <dt class="col-sm-3">Instytucja:</dt>
                                            <dd class="col-sm-9">${institutionMap.get(donationDto.institutionId)}</dd>

                                            <dt class="col-sm-3">Liczba worków:</dt>
                                            <dd class="col-sm-9">${donationDto.quantity}</dd>

                                            <dt class="col-sm-3">Miasto:</dt>
                                            <dd class="col-sm-9">${donationDto.city}</dd>

                                            <dt class="col-sm-3">Ulica i numer domu:</dt>
                                            <dd class="col-sm-9">${donationDto.street}</dd>

                                            <dt class="col-sm-3">Kod pocztowy:</dt>
                                            <dd class="col-sm-9">${donationDto.zipCode}</dd>

                                            <dt class="col-sm-3">Nr telefonu:</dt>
                                            <dd class="col-sm-9">${donationDto.telephoneNumber}</dd>

                                            <dt class="col-sm-3">Data odbioru:</dt>
                                            <dd class="col-sm-9">${donationDto.pickUpDate}</dd>

                                            <dt class="col-sm-3">Godzina odbioru:</dt>
                                            <dd class="col-sm-9">${donationDto.pickUpTime}</dd>

                                            <dt class="col-sm-3">Komentarz:</dt>
                                            <dd class="col-sm-9">${donationDto.pickUpComment}</dd>

                                            <dt class="col-sm-3"></dt>
                                            <dd class="col-sm-9"><br></dd>

                                            <dt class="col-sm-3">Status:</dt>
                                            <form class="user" id="changeDonationStatusForm" method="post"
                                                  action="/donations/status/${donationDto.id}">
                                                <select name="donationStatusId" class="form-control">
                                                    <c:forEach items="${donationStatusMap}" var="donationStatus">
                                                        <c:choose>
                                                            <c:when test="${donationStatusMap.containsKey(donationDto.donationStatusId)}">
                                                                <option selected
                                                                        value="${donationStatus.key}">${donationStatus.value}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${donationStatus.key}">${donationStatus.value}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            </form>
                                        </dl>

                                        <a href="javascript:{}"
                                           onclick="document.getElementById('changeDonationStatusForm').submit();"
                                           class="btn btn-primary btn--small active float-right mr-1">
                                            Zatwierdź
                                        </a>
                                        <a href="/donations/edit" class="btn btn-light btn--small active float-right mr-1"
                                           role="button"
                                           aria-pressed="true">Wróć do donacji</a>

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
        <%-- TODO - end --%>
    </body>
</html>