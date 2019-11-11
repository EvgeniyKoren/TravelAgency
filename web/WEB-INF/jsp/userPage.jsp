<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<c:set var="title" value="User account" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
<%--    <h1>USER PAGE, MOTHER FUCKER!</h1>--%>
    <h2>Hi, ${user.login}!</h2>
    <h3>Your personal data:</h3>
    <div id="person">
        <ul>
            <li><span>First name: </span>${user.firstName}</li>
            <li><span>Last name: </span>${user.lastName}</li>
            <li><span>Login: </span>${user.login}</li>
            <c:choose>
                <c:when test="${user.status == true}">
                    <b><c:set var="status" value="You are banned by admin"/></b>
                </c:when>
                <c:otherwise>
                    <b><c:set var="status" value="OK"/></b>
                </c:otherwise>
            </c:choose>
            <li><span>Status: </span>${status}</li>
        </ul>
    </div>
    <div>
        <a href="controller?command=showTours">Show tours</a><br>
        <c:if test="${userRole.name == 'manager'}">
            <a href="controller?command=showOrders">Show orders</a>
        </c:if>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
