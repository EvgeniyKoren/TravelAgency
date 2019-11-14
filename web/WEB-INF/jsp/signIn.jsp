<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>
<c:set var="title" value="Sign in" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<p>Enter your data and press sign in...</p>
<div id="form_container">
    <form id="signIn_form" action="controller" method="post">

        <input type="hidden" name="command" value="signIn"/>
        <fieldset >
            <legend>First name</legend>
            <input type="text" name="firstName" placeholder="First name"/><br>
        </fieldset><br/>
        <fieldset >
            <legend>Last name</legend>
            <input type="text" name="lastName" placeholder="Last name"/><br>
        </fieldset><br/>
        <fieldset >
            <legend>Login</legend>
            <input type="text" name="login" placeholder="login"/><br/>
        </fieldset><br/>
        <fieldset>
            <legend>Password</legend>
            <input type="password" name="password"/>
        </fieldset><br/>

        <input type="submit" value="Sign in">
    </form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
