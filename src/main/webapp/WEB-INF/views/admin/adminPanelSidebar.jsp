<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center"
       href="<c:url value="/"/>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-hand-holding-heart"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Oddam w dobre ręce</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="/adminPanel">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Zarządzanie
    </div>

    <!-- Nav Item - Institutions Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseInstitutions"
           aria-expanded="true" aria-controls="collapseInstitutions">
            <i class="fas fa-fw fa-clinic-medical"></i>
            <span>Instytucje</span>
        </a>
        <div id="collapseInstitutions" class="collapse" aria-labelledby="headingInstitutions" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/institutions"/>">Lista
                    instytucji</a>
                <a class="collapse-item" href="<c:url value="/institutions/add"/>">Dodaj
                    instytucję</a>
                <a class="collapse-item" href="<c:url value="/institutions/edit"/>">Edytuj/Usuń
                    instytucję</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Categories Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCategories"
           aria-expanded="true" aria-controls="collapseCategories">
            <i class="fas fa-fw fa-grip-horizontal"></i>
            <span>Kategorie</span>
        </a>
        <div id="collapseCategories" class="collapse" aria-labelledby="headingCategories"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-color.html"/>">Lista
                    kategorii</a>
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-border.html"/>">Dodaj
                    kategorię</a>
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-animation.html"/>">Edytuj/Usuń
                    kategorię</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Donations Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDonations"
           aria-expanded="true" aria-controls="collapseDonations">
            <i class="fas fa-hand-holding-heart fa-grip-horizontal"></i>
            <span>Donacje</span>
        </a>
        <div id="collapseDonations" class="collapse" aria-labelledby="headingDonations"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-color.html"/>">Lista
                    donacji</a>
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-border.html"/>">Dodaj
                    donację</a>
                <a class="collapse-item" href="<c:url value="/resources/adminPanel/utilities-animation.html"/>">Edytuj/Usuń
                    donację</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Users Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsers"
           aria-expanded="true" aria-controls="collapseUsers">
            <i class="fas fa-users fa-grip-horizontal"></i>
            <span>Użytkownicy</span>
        </a>
        <div id="collapseUsers" class="collapse" aria-labelledby="headingUsers" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/users"/>">Lista
                    użytkowników</a>
                <a class="collapse-item" href="<c:url value="/users/add"/>">Dodaj
                    użytkownika</a>
                <a class="collapse-item" href="<c:url value="/users/edit"/>">Edytuj/Usuń
                    użytkownika</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Admins Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAdmins"
           aria-expanded="true" aria-controls="collapseAdmins">
            <i class="fas fa-users-cog fa-grip-horizontal"></i>
            <span>Administratorzy</span>
        </a>
        <div id="collapseAdmins" class="collapse" aria-labelledby="headingAdmins"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/admins"/>">Lista
                    adminów</a>
                <a class="collapse-item" href="<c:url value="/admins/add"/>">Dodaj
                    admina</a>
                <a class="collapse-item" href="<c:url value="/admins/edit"/>">Edytuj/Usuń
                    admina</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->