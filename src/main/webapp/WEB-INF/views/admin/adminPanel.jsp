<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- TODO - i18n --%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dashboard</title>
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
    <%-- TODO - end--%>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="adminPanelSidebar.jsp"/>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="adminPanelTopbar.jsp"/>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                </div>

                <!-- Content Row -->
                <div class="row">
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Przekazane dary
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${donationSum}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-boxes fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Wspierane
                                            organizacje
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${donatedInstitutionsSum}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-list-ol fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Zarejestrowanych użytkowników
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${numberOfUsers}</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-users fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Donacje
                                            dziennie
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${donationsPerDay}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar-check fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <div class="col-xl-8 col-lg-7">
                        <!-- Area Chart -->
                        <div class="card shadow mb-4">

                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Suma łączna donacji</h6>
                            </div>

                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="myAreaChart"></canvas>
                                </div>
                            </div>
                        </div>

                        <!-- Bar Chart -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Donacje w ostatnich 12 miesiącach</h6>
                            </div>
                            <div class="card-body">
                                <div class="chart-bar">
                                    <div class="chartjs-size-monitor">
                                        <div class="chartjs-size-monitor-expand">
                                            <div class=""></div>
                                        </div>
                                        <div class="chartjs-size-monitor-shrink">
                                            <div class=""></div>
                                        </div>
                                    </div>
                                    <canvas id="myBarChart" width="1039" height="320" class="chartjs-render-monitor"
                                            style="display: block; width: 1039px; height: 320px;"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Donut Chart -->
                    <div class="col-xl-4 col-lg-5">
                        <div class="card shadow mb-4">

                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Rozkład donacji</h6>
                            </div>

                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-pie pt-4 pb-2">
                                    <canvas id="myPieChart"></canvas>
                                </div>
                                <div class="mt-4 text-center small">
                                    <p class="mr-2">
                                        <i class="fas fa-circle text-primary"></i> ${pieChartLabels.get(0)}
                                    </p>
                                    <p class="mr-2">
                                        <i class="fas fa-circle text-info"></i> ${pieChartLabels.get(1)}
                                    </p>
                                    <p class="mr-2">
                                        <i class="fas fa-circle text-success"></i> ${pieChartLabels.get(2)}
                                    </p>
                                    <p class="mr-2">
                                        <i class="fas fa-circle text-danger"></i> ${pieChartLabels.get(3)}
                                    </p>
                                    <p class="mr-2">
                                        <i class="fas fa-circle text-warning"></i> ${pieChartLabels.get(4)}
                                    </p>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <jsp:include page="adminPanelFooter.jsp"/>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button -->
<jsp:include page="adminPanelScrollToTopButton.jsp"/>

<!-- Logout Modal -->
<jsp:include page="adminPanelLogoutModal.jsp"/>

<%-- TODO - CDNs with local fallbacks --%>
<!-- jQuery v3.4.1 -->
<script src="<c:url value="/resources/adminPanel/vendor/jquery/jquery.min.js"/>"></script>
<!-- Bootstrap v4.3.1 -->
<script src="<c:url value="/resources/adminPanel/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- jQuery Easing v1.4.1 -->
<script src="<c:url value="/resources/adminPanel/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<!-- SB Admin 2 v4.0.7 -->
<script src="<c:url value="/resources/adminPanel/js/sb-admin-2.min.js"/>"></script>
<!-- Chart.js v2.8.0 -->
<script src="<c:url value="/resources/adminPanel/vendor/chart.js/Chart.min.js"/>"></script>
<!-- Donut Chart js -->
<script pieChartValues="${pieChartValues}" pieChartLabels="${pieChartLabels}"
        src="<c:url value="/resources/adminPanel/js/charts-and-tables/chart-pie.js"/>"></script>
<!-- JS i18n String array-->
<script type="text/javascript">
    const languageStrings = new Array();
    languageStrings['javascript.bags'] = "<spring:message code='javascript.bags' javaScriptEscape='true' />";
    languageStrings['javascript.donations'] = "<spring:message code='javascript.donations' javaScriptEscape='true' />";
</script>
<!-- Area Chart js -->
<script donationsSumsInLastTwelveMonths="${donationsSumsInLastTwelveMonths}" chartLabels="${chartLabels}"
        src="<c:url value="/resources/adminPanel/js/charts-and-tables/chart-area.js"/>"></script>
<!-- Bar Chart js -->
<script donationsInLastTwelveMonths="${donationsInLastTwelveMonths}"
        src="<c:url value="/resources/adminPanel/js/charts-and-tables/chart-bar.js"/>"></script>
<%-- TODO - end --%>
</body>
</html>