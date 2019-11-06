<%--===========================================================================
Page directive. Sets the content type and encoding.
Encoding of the response: UTF-8
Encoding of this JSP page: UTF-8
===========================================================================--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--===========================================================================
JSTL core tag library.
===========================================================================--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--===========================================================================
JSTL functions tag library.
===========================================================================--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>CORRECT</h2>
    <h2>${allUsers}</h2>
    <table id="list">
        <thead>
        <tr>
            <td>№</td>
            <td>First name</td>
            <td>Last name</td>
            <td>Login</td>
            <td>Password</td>
            <td>Status</td>
            <td>role</td>
        </tr>
        </thead>

        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${allUsers}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>${item.firstName}</td>
                <td>${item.lastName}</td>
                <td>${item.login}</td>
                <td>${item.password}</td>
                <td>${item.status}</td>
                <td>${item.roleId}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
