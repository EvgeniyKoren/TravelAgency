<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="Admin page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h3><fmt:message key="admin.greeting" />,${user.login}</h3>
    <ul>
        <li><a href="controller?command=showTours"><fmt:message key="admin.tours" /></a></li>
        <li><a href="controller?command=showUsers"><fmt:message key="admin.users" /></a></li>
        <li><a href="controller?command=showOrders"><fmt:message key="admin.orders" /></a></li>
    </ul>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
