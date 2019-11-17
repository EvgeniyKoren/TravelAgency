<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="Sign in" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <div class="container">
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="form_container bg-light p-0">
            <h3 class="pt-4 text-center text-info"><fmt:message key="signIn.info"/></h3>
            <form id="signIn_form" action="controller" method="post"
                  class="rounded p-5 bg-secondary my-5 mx-auto d-flex flex-column justify-content-center align-items-center w-50">

                <input type="hidden" name="command" value="signIn"/>
                <fieldset class="mb-3">
                    <fmt:message key="signIn.firstName" var="firstName"/>
                    <legend class="text-center text-white">${firstName}</legend>
                    <input type="text" name="firsName" placeholder="${firstName}"/><br>
                </fieldset>
                <fieldset class="mb-3">
                    <fmt:message key="signIn.lastName" var="lastName"/>
                    <legend class="text-center text-white">${lastName}</legend>
                    <input type="text" name="lastName" placeholder="${lastName}"/><br>
                </fieldset>
                <fieldset class="mb-3">
                    <fmt:message key="login.login" var="login"/>
                    <legend class="text-center text-white">${login}</legend>
                    <input type="text" name="login" placeholder="${login}"/><br/>
                </fieldset>
                <fieldset class="mb-5">
                    <fmt:message key="login.password" var="password"/>
                    <legend class="text-center text-white">${password}</legend>
                    <input type="password" name="password" placeholder="${password}"/>
                </fieldset>
                <fmt:message key="signIn.signIn" var="signIn"/>
                <input type="submit" value="${signIn}" class="btn btn-outline-light" />
            </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    </div>
</body>
</html>