<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="all users settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <h2>Registered users</h2>
    <div>
        <table id="list" border="1">
            <thead>
            <tr>
                <td>№</td>
                <td>first name</td>
                <td>last name</td>
                <td>login</td>
                <td>status</td>
                <td>role</td>
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
        <form action="controller" method="post">
            <input type="hidden" name="command" value="modifyUser"/>
            <input type="text" name="userLogin" placeholder="user login"/>
            <input type="submit" value="change status">
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
