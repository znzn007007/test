<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册页面</title>
<jsp:include page="/WEB-INF/base.jsp"></jsp:include>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h1 align="center">用户注册页面</h1>
	<div>
		<form id="registerForm" action="register" method="post">
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
					<td>确认密码：</td>
					<td><input type="password" name="password2" id="password2" /></td>
					<td class="password2"></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="email" id="email" /></td>
					<td class="email"></td>
				</tr>
				<tr>
					<td>手机号：</td>
					<td><input type="text" name="tel" id="tel" /></td>
					<td class="tel"></td>
				</tr>
				<tr>
					<td type="text-align:center;"><input type="submit" value="注册" />
						<input type="reset" id="清空" value="清空" /></td>
				</tr>
			</table>
		</form>
		<a href="login">登录</a>
	</div>
</body>
</html>