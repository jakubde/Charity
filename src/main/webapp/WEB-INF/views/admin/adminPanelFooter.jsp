<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="date" class="java.util.Date"/>

<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; <spring:message code="footer.put.it.in.good.hands"/> <fmt:formatDate value="${date}" pattern="yyyy"/></span>
        </div>
    </div>
</footer>