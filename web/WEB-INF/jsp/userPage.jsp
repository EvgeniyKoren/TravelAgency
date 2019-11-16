<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="User account" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <div class="container text-left bg-light p-0">
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <h2 class="text-center"><fmt:message key="user.greeting"/>, ${user.login}!</h2>
        <h3 class="text-center"><fmt:message key="user.info"/>:</h3>
        <div id="person" class="d-flex w-50 justify-content-between align-items-center mx-auto bg-secondary text-light p-4">
            <ul class="list-unstyled d-flex flex-column align-items-left justify-content-center">
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
            <div class="d-flex justify-content-center align-items-center" style="width: 130px;height: 150px;border:1px solid green;border-radius: 15px;">
                <img src="../static/IMG_20191013_175643.jpg" style="width:100%;">
            </div>
        </div>
        <div>
            <a href="controller?command=showTours"><fmt:message key="index.look.tours"/></a><br>
            <c:if test="${userRole.name == 'manager'}">
                <a href="controller?command=showOrders"><fmt:message key="user.orders"/></a>
            </c:if>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </div>
</body>
</html>
