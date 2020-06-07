<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="date" class="java.util.Date"/>

<%-- TODO i18n --%>
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Oddam w dobre ręce <fmt:formatDate value="${date}" pattern="yyyy"/></span>
        </div>
    </div>
</footer>