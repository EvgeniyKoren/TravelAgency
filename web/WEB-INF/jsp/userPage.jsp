<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="User account" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h2><fmt:message key="user.greeting"/>, ${user.login}!</h2>
    <h3><fmt:message key="user.info"/>:</h3>
    <div id="person">
        <ul>
            <li><span><fmt:message key="user.firstName"/>: </span>${user.firstName}</li>
            <li><span><fmt:message key="user.lastName"/>: </span>${user.lastName}</li>
            <li><span><fmt:message key="user.login"/>: </span>${user.login}</li>
            <c:choose>
                <c:when test="${user.status == true}">
                    <b><c:set var="status" value="You are banned by admin"/></b>
                </c:when>
                <c:otherwise>
                    <b><c:set var="status" value="OK"/></b>
                </c:otherwise>
            </c:choose>
            <li><span><fmt:message key="user.status"/>: </span>${status}</li>
        </ul>
    </div>
    <div>
        <a href="controller?command=showTours"><fmt:message key="index.look.tours"/></a><br>
        <c:if test="${userRole.name == 'manager'}">
            <a href="controller?command=showOrders"><fmt:message key="user.orders"/></a>
        </c:if>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
