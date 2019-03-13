<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
<script type="text/javascript">
function updateCart(id, lineNum) {
	var count = $("#"+lineNum).val();
	location.href = "update?id=" + id + "&count=" + count;
}
function deleteCart(id) {
	location.href = "delete?id=" + id;
}

</script>
</head>
<body>
	<h1 align="center">购物车列表页面</h1>
	<div style="width: 98%; padding-top: 7px; text-align: center;">
		<a href="/BookStore/book/shelf">书架</a> <a href="/BookStore/logout">退出登录</a>
	</div>
	<div>
		<table border="1" align="center">
			<caption>
				<h3>${user.username}的购物车</h3>
			</caption>
			<tr>
				<th>书 名</th>
				<th>数 量</th>
				<th>价 格</th>
				<th colspan="3">操 作</th>
			</tr>
			<c:set var="lineNum" value="0"></c:set>
			<c:forEach var="item" items="${cart.items}">
				<tr align="cneter" class="cart">
					<c:set var="lineNum" value="${lineNum + 1}"></c:set>
					<td><c:out value="${item.book.name}" /></td>
					<td><input type="number" id="${lineNum}" value="${item.count}" /></td>
					<td><c:out value="${item.book.price * item.count}" /></td>
					<td><input type="button" value="修改"
						onclick="updateCart(${item.book.id},${lineNum})" /></td>
					<td><input type="button" value="删除"
						onclick="deleteCart(${item.book.id})" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>