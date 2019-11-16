<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="all users settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <div class="container text-center bg-light p-0">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <h2 class="text-info m-4"><fmt:message key="users.allUsers"/></h2>
        <div class="container-fluid overflow-auto mb-5" style="max-height: 300px;">
            <table id="list" class="mx-auto table table-striped">
                <thead>
                <tr>
                    <td>&#8470;</td>
                    <td><fmt:message key="signIn.firstName"/></td>
                    <td><fmt:message key="signIn.lastName"/></td>
                    <td><fmt:message key="login.login"/></td>
                    <td><fmt:message key="signIn.status"/></td>
                    <td><fmt:message key="signIn.role"/></td>
                </tr>
                </thead>

                <c:forEach var="item" items="${allUsers}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.firstName}</td>
                        <td>${item.lastName}</td>
                        <td>${item.login}</td>
                        <c:choose>
                            <c:when test="${item.status == true}">
                                <b><c:set var="status" value="banned"/></b>
                            </c:when>
                            <c:otherwise>
                                <b><c:set var="status" value="not banned"/></b>
                            </c:otherwise>
                        </c:choose>
                        <td>${status}</td>
                        <c:choose>
                            <c:when test="${item.roleId == 2}">
                                <b><c:set var="role" value="customer"/></b>
                            </c:when>
                            <c:when test="${item.roleId == 1}">
                                <b><c:set var="role" value="manager"/></b>
                            </c:when>
                            <c:otherwise>
                                <b><c:set var="role" value="admin"/></b>
                            </c:otherwise>
                        </c:choose>
                        <td>${role}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="form_container">
            <h2><fmt:message key="users.change.status"/></h2>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="modifyUser"/>
                <h2><fmt:message key="users.user.login" var="userLogin"/></h2>
                <input type="text" name="userLogin" placeholder="${userLogin}"/>
                <h2><fmt:message key="users.status.batton" var="changeStatus"/></h2>
                <input type="submit" value="${changeStatus}">
            </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>
