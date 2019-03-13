<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
<script type="text/javascript" src=""></script>
<title>欢迎页面</title>
</head>
<body>
	<h3>欢迎 ${user.username}使用我们网站！</h3>
	
	<a href="logout">退出登录</a>
	<a href="book/shelf">书架</a>
</body>
</html>