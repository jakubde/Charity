<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
  </head>
  <body>
    <header>
      <jsp:include page="header.jsp"/>
    </header>

    <section class="login-page">
      <h2>Zaloguj się</h2>
        <form method="post" action="/login">
        <div class="form-group">
<%--          <input type="text" id="username" name="username" class="form-control" placeholder="Email" required autofocus>--%>
          <label for="username"></label>
          <input type="text" id="username" name="username" placeholder="Email" required autofocus/>
        </div>
        <div class="form-group">
          <label for="password"></label>
          <input type="password" id="password" name="password" placeholder="Hasło" required/>
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
          <a href="/register" class="btn btn--without-border">Załóż konto</a>
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </section>

    <jsp:include page="footer.jsp"/>

  </body>
</html>

<%--post na strone glowną--%>
<%--zrobic ifa czy jest parametr error--%>
<%--testy zaczac od repozytorium--%>