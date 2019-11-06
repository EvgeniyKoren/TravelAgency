<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 30.10.2019
  Time: 00:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <c:set var="title" value="Main" scope="page" />
  <%@ include file="/WEB-INF/jspf/head.jspf" %>
  <body>
<%--    <%@ include file="/WEB-INF/jspf/header.jspf"%>--%>
    <h1>Welcome to my Travel Agency</h1>
    <ul>
      <li><a href="controller?command=showTours">Show tours</a></li>
      <li><a href="login.jsp">Login page</a></li>
      <li><a href="controller?command=redirect&pageName=signIn">Sign in page</a></li>
    </ul>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
  </body>
</html>
