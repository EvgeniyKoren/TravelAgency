<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
	<div class="container bg-light p-0">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<div id="form_container">
			<form id="login_form" action="controller" method="post"
				  class="rounded p-5 bg-secondary my-5 mx-auto d-flex flex-column justify-content-center align-items-center w-50">

				<input type="hidden" name="command" value="login"/>

				<fieldset class="mb-3">
					<legend  class="text-center text-white"><fmt:message key="login.login" /></legend>
					<input name="login"/><br/>
				</fieldset>
				<fieldset class="mb-5">
					<legend  class="text-center text-white"><fmt:message key="login.password" /></legend>
					<input type="password" name="password"/>
				</fieldset>
				<fmt:message key="login.button" var="login"/>
				<input type="submit" value="${login}" class="btn btn-outline-light">
			</form>
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>