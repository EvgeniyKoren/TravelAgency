<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
  <c:set var="title" value="Main" scope="page" />
  <%@ include file="/WEB-INF/jspf/head.jspf" %>
  <body>
    <div class="container bg-light p-0">
      <%@ include file="/WEB-INF/jspf/header.jspf"%>
      <h1 class="text-center m-4 "><fmt:message key="index.greeting" /></h1>
      <h2 class="text-center m-3"><fmt:message key="index.wishes" /></h2>
      <ul class="list-unstyled d-flex flex-column align-items-center justify-content-center">
        <li><a href="controller?command=showTours" class="p-3 m-2 btn btn-primary"><fmt:message key="index.look.tours"/></a></li>
        <li><a href="login.jsp" class="p-3 m-2 btn btn-primary"><fmt:message key="index.login.page"/></a></li>
        <li><a href="controller?command=redirect&pageName=signIn" class="p-3 m-2 btn btn-primary"><fmt:message key="index.signin.page"/></a></li>
      </ul>
      <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </div>
  </body>
</html>
