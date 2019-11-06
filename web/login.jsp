<%--<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>--%>
<%--<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<h1>here is a login page</h1>
<div id="form_container">
	<form id="login_form" action="controller" method="post">

		<input type="hidden" name="command" value="login"/>

		<fieldset >
			<legend>Login</legend>
			<input name="login"/><br/>
		</fieldset><br/>
		<fieldset>
			<legend>Password</legend>
			<input type="password" name="password"/>
		</fieldset><br/>

		<input type="submit" value="Login">
	</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>