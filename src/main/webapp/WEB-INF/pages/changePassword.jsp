<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Смена пароля</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Смена пароля</h1>

	<div id="login-box">

		<h3>Веедите старый и новый пароли</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/user/change_password.html' />"
			method='POST'>

			<table>
				<tr>
					<td>Старый пароль:</td>
					<td><input type='password' name='oldPassword'></td>
				</tr>
				<tr>
					<td>Новый пароль:</td>
					<td><input type='password' name='newPassword' /></td>
				</tr>

				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>

			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

	</div>

</body>
</html>