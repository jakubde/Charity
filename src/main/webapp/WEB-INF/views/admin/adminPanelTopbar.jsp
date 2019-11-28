<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>



    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">

        <li class="nav-item dropdown no-arrow"><a class="nav-link" href="?lang=en" role="button"><span class="mr-2 d-none d-lg-inline text-gray-600 small">EN 🇬🇧</span></a></li>
        <%--                    <li class="nav-item dropdown no-arrow"><a class="nav-link" href="?lang=en"><span class="mr-2 d-none d-lg-inline text-gray-600 small">|</span></a></li>--%>
        <li class="nav-item dropdown no-arrow"><a class="nav-link" href="?lang=pl"><span class="mr-2 d-none d-lg-inline text-gray-600 small">PL 🇵🇱</span></a></li>

        <div class="topbar-divider d-none d-sm-block"></div>

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${firstName}</span>
                <span class="fa-stack fa-2x">
                                <i class="fas fa-circle fa-stack-1x fa-lg"></i>
                                <i class="fas fa-user fa-stack-1x fa-xs fa-inverse"></i>
                            </span>
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profil
                </a>
                <a class="dropdown-item" href="/logout" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Wyloguj
                </a>
            </div>
        </li>

    </ul>

</nav>
<!-- End of Topbar -->