<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/loginstyle.css" />" type="text/css">
</head>
<body onload='document.loginForm.username.focus();'>

	<div id="login-box">

		<h2 class="text">Login</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='j_spring_security_check'/>" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<c:if test="${empty loginUpdate}">
					<tr>
						<td></td>
						<td>Remember Me: <input type="checkbox" name="remember-me" /></td>
					</tr>
				</c:if>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						class="buttonStyleLogin" value="Log in" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>