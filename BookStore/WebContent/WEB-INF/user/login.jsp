<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录页面</title>
<jsp:include page="/WEB-INF/base.jsp"></jsp:include>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h1 align="center">用户登录页面</h1>
	<div>
		<form action="login" method="post" id="loginForm">
			<table border="0" align="center">
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" id="username" /></td>
					<td class="username"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" id="password" /></td>
					<td class="password"></td>
				</tr>
				<tr>
					<td type="text-align:center;"><input type="submit" id="登录"
						value="登录" /> <input type="reset" id="清空" value="清空" /></td>
				</tr>
			</table>
		</form>
		<a href="register">注册</a>
	</div>
</body>
</html>