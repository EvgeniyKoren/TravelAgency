<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="Sign in" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>

    <div id="form_container">
        <h3><fmt:message key="signIn.info"/></h3>
        <form id="signIn_form" action="controller" method="post">

            <input type="hidden" name="command" value="signIn"/>
            <fieldset >
                <fmt:message key="signIn.firstName" var="firstName"/>
                <legend>${firstName}</legend>
                <input type="text" name="firsName" placeholder="${firstName}"/><br>
            </fieldset><br/>
            <fieldset >
                <fmt:message key="signIn.lastName" var="lastName"/>
                <legend>${lastName}</legend>
                <input type="text" name="lastName" placeholder="${lastName}"/><br>
            </fieldset><br/>
            <fieldset >
                <fmt:message key="login.login" var="login"/>
                <legend>${login}</legend>
                <input type="text" name="login" placeholder="${login}"/><br/>
            </fieldset><br/>
            <fieldset>
                <fmt:message key="login.password" var="password"/>
                <legend>${password}</legend>
                <input type="password" name="password" placeholder="${password}"/>
            </fieldset><br/>
            <fmt:message key="signIn.signIn" var="signIn"/>
            <input type="submit" value="${signIn}"/>
        </form>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>