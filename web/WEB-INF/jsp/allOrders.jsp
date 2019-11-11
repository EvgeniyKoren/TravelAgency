<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<c:set var="title" value="All orders" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div>
        <table id="list" border="1">
            <thead>
            <tr>
                <td>№</td>
                <td>User id</td>
                <td>Tour id</td>
                <td>Is handled</td>
            </tr>
            </thead>

            <c:forEach var="item" items="${allOrders}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.userId}</td>
                    <td>${item.tourId}</td>
                    <c:choose>
                        <c:when test="${item.handled == true}">
                            <b><c:set var="isHandled" value="Yes"/></b>
                        </c:when>
                        <c:otherwise>
                            <b><c:set var="isHandled" value="No"/></b>
                        </c:otherwise>
                    </c:choose>
                    <td>${isHandled}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
