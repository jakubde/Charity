<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="date" class="java.util.Date"/>

<footer>
    <div class="contact">
        <h2><spring:message code="contact.us"/></h2>
        <h3><spring:message code="contact.form"/></h3>
        <form class="form--contact">
            <div class="form-group form-group--50">
                <label>
                    <input type="text" name="name" placeholder="<spring:message code="footer.first.name"/>"/>
                </label>
            </div>
            <div class="form-group form-group--50">
                <label>
                    <input type="text" name="surname" placeholder="<spring:message code="footer.last.name"/>"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    <textarea name="message" placeholder="<spring:message code="footer.message"/>" rows="1"></textarea>
                </label>
            </div>
            <button class="btn" type="submit"><spring:message code="footer.send"/></button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/></span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small">
                <img src="<c:url value="/resources/images/icon-facebook.svg"/>" alt="icon-facebook"/>
            </a>
            <a href="#" class="btn btn--small">
                <img src="<c:url value="/resources/images/icon-instagram.svg"/>" alt="icon-instagram"/>
            </a>
        </div>
    </div>
</footer>