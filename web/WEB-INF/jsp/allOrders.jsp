<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="All orders" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <div class="container text-center bg-light p-0">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <c:if test="${not empty allOrders}">
            <h2 class="text-info m-4"><fmt:message key="orders.table"/></h2>
            <div class="container-fluid overflow-auto mb-5" style="max-height: 300px;">
                <table id="list" class="mx-auto table table-striped">
                    <thead>
                    <tr>
                        <td>&#8470;</td>
                        <td><fmt:message key="orders.userId"/></td>
                        <td><fmt:message key="orders.tourId"/></td>
                        <td><fmt:message key="orders.isHandled"/></td>
                    </tr>
                    </thead>

                    <c:forEach var="item" items="${allOrders}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.userId}</td>
                            <td>${item.tourId}</td>
                            <c:choose>
                                <c:when test="${item.handled == true}">
                                    <%--                                <b><c:set var="isHandled" value="Yes"/></b>--%>
                                    <td><fmt:message key="orders.yes"/></td>
                                </c:when>
                                <c:otherwise>
                                    <%--                                <b><c:set var="isHandled" value="No"/></b>--%>
                                    <td><fmt:message key="orders.no"/></td>
                                </c:otherwise>
                            </c:choose>
                                <%--                        <td>${isHandled}</td>--%>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
        <c:if test="${empty allOrders}">
            <p><fmt:message key="orders.empty"/></p>
        </c:if>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>
