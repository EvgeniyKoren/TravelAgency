<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="title" value="User account" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h2>Hi ${user.login}!</h2>
    <h3>Your personal data:</h3>
    <div>
        <ul>
            <li><span>First name: </span>${user.firstName}</li>
            <li><span>Last name: </span>${user.laststName}</li>
            <li><span>Login: </span>${user.login}</li>
            <c:choose>
                <c:when test="${user.status == true}">
                    <b><c:set var="title" value="You are banned by admin"/></b>
                </c:when>
                <c:otherwise>
                    <b><c:set var="title" value="OK"/></b>
                </c:otherwise>
            </c:choose>
            <li><span>Status: </span>${status}</li>
        </ul>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
