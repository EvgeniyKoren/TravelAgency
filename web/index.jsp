<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
  <c:set var="title" value="Main" scope="page" />
  <%@ include file="/WEB-INF/jspf/head.jspf" %>
  <body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h1 class="text-center">Welcome to my Travel Agency</h1>
    <h2 class="text-info"><fmt:message key="tours.welcome" /></h2>
    <ul class="list-unstyled">
      <li class="p-3"><a href="controller?command=showTours" class="text-danger">Show tours</a></li>
      <li class="p-3"><a href="login.jsp" class="text-danger">Login page</a></li>
      <li class="p-3"><a href="controller?command=redirect&pageName=signIn" class="text-danger">Sign in page</a></li>
    </ul>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
  </body>
</html>
