<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="title" value="Admin page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h3>Hi admin,${user.login}</h3>
    <ul>
        <li><a href="controller?command=showTours">Show tours</a></li>
        <li><a href="controller?command=showUsers">Show users</a></li>
    </ul>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
