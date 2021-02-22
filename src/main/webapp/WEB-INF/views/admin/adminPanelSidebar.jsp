<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center"
       href="<c:url value="/"/>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-hand-holding-heart"></i>
        </div>
        <div class="sidebar-brand-text mx-3"><spring:message code="put.it.in.good.hands"/></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="<c:url value="/adminPanel"/>">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        <spring:message code="admin.panel.management"/>
    </div>

    <!-- Nav Item - Institutions Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseInstitutions"
           aria-expanded="true" aria-controls="collapseInstitutions">
            <i class="fas fa-fw fa-clinic-medical"></i>
            <span><spring:message code="admin.panel.institutions"/></span>
        </a>
        <div id="collapseInstitutions" class="collapse" aria-labelledby="headingInstitutions"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/institutions"/>">
                    <spring:message code="list.of.institutions"/>
                </a>
                <a class="collapse-item" href="<c:url value="/institutions/add"/>">
                    <spring:message code="add.institution"/>
                </a>
                <a class="collapse-item" href="<c:url value="/institutions/edit"/>">
                    <spring:message code="edit.delete.institution"/>
                </a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Categories Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCategories"
           aria-expanded="true" aria-controls="collapseCategories">
            <i class="fas fa-fw fa-grip-horizontal"></i>
            <span><spring:message code="admin.panel.categories"/></span>
        </a>
        <div id="collapseCategories" class="collapse" aria-labelledby="headingCategories"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/categories"/>">
                    <spring:message code="list.of.categories"/>
                </a>
                <a class="collapse-item" href="<c:url value="/categories/add"/>">
                    <spring:message code="add.category"/>
                </a>
                <a class="collapse-item" href="<c:url value="/categories/edit"/>">
                    <spring:message code="edit.delete.category"/>
                </a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Donations Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDonations"
           aria-expanded="true" aria-controls="collapseDonations">
            <i class="fas fa-hand-holding-heart fa-grip-horizontal"></i>
            <span><spring:message code="admin.panel.donations"/></span>
        </a>
        <div id="collapseDonations" class="collapse" aria-labelledby="headingDonations"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/donations/"/>">
                    <spring:message code="list.of.donations"/>
                </a>
                <a class="collapse-item" href="<c:url value="/donations/add"/>">
                    <spring:message code="add.donation"/>
                </a>
                <a class="collapse-item" href="<c:url value="/donations/edit"/>">
                    <spring:message code="edit.delete.donation"/>
                </a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Donation Statuses Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDonationStatuses"
           aria-expanded="true" aria-controls="collapseDonationStatuses">
            <i class="fas fa-shipping-fast fa-grip-horizontal"></i>
            <span><spring:message code="admin.panel.donation.statuses"/></span>
        </a>
        <div id="collapseDonationStatuses" class="collapse" aria-labelledby="headingUsers"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/donationStatus"/>">
                    <spring:message code="list.of.statuses"/>
                </a>
                <a class="collapse-item" href="<c:url value="/donationStatus/add"/>">
                    <spring:message code="add.status"/>
                </a>
                <a class="collapse-item" href="<c:url value="/donationStatus/edit"/>">
                    <spring:message code="edit.delete.status"/>
                </a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Users Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsers"
           aria-expanded="true" aria-controls="collapseUsers">
            <i class="fas fa-users fa-grip-horizontal"></i>
            <span><spring:message code="admin.panel.users"/></span>
        </a>
        <div id="collapseUsers" class="collapse" aria-labelledby="headingUsers" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/users"/>">
                    <spring:message code="list.of.users"/>
                </a>
                <a class="collapse-item" href="<c:url value="/users/add"/>">
                    <spring:message code="add.user"/>
                </a>
                <a class="collapse-item" href="<c:url value="/users/edit"/>">
                    <spring:message code="edit.delete.user"/>
                </a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Admins Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAdmins"
           aria-expanded="true" aria-controls="collapseAdmins">
            <i class="fas fa-users-cog fa-grip-horizontal"></i>
            <span><spring:message code="admin.panel.administrators"/></span>
        </a>
        <div id="collapseAdmins" class="collapse" aria-labelledby="headingAdmins"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="<c:url value="/admins"/>">
                    <spring:message code="list.of.administrators"/>
                </a>
                <a class="collapse-item" href="<c:url value="/admins/add"/>">
                    <spring:message code="add.admin"/>
                </a>
                <a class="collapse-item" href="<c:url value="/admins/edit"/>">
                    <spring:message code="edit.delete.admin"/>
                </a>
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