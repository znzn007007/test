<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
td {
	text-align: center;
	vertical-align: middle;
}

th {
	text-align: center;
	vertical-align: middle;
	color: red;
}

tr:nth-child(odd).book {
	background-color: #76B7BC;
}

tr:nth-child(even).book {
	background-color: #66ffcc;
}

#catalog {
	border: 0px
}
</style>
<script type="text/javascript">
function updateBook(id){
	location.href = "update?id=" + id;
}
function deleteBook(id){
	location.href = "delete?id=" + id;
}
function addCart(id){
	location.href = "/BookStore/cart/add?id=" + id;
}

</script>
</head>
<body>
	<h1 align="center">书籍列表页面</h1>
	<div style="width: 98%; padding-top: 7px; text-align: center;">
		<form action="search" method="get">
			搜索框： <select name="goal">
				<option value="name">书名</option>
				<option value="author">作者</option>
				<option value="isbn">isbn</option>
			</select> <input type="text" id="text" name="keyword" value="${keyword}" /> <input type="submit"
				value="查询" /> <input type="reset" name="清空" value="清空" /> <a href="add">添加书籍</a>
		</form>
		<h4>欢迎${user.username}使用我们网站！</h4>
		<a href="/BookStore/cart/">我的购物车</a> <a href="/BookStore/logout">退出登录</a>
	</div>
	<div>
		<table border="1" align="center">
			<caption>
				<h3>书籍列表</h3>
			</caption>
			<tr>
				<th>书 号</th>
				<th>书 名</th>
				<th>作 者</th>
				<th>isbn</th>
				<th>出版时间</th>
				<th>封 面</th>
				<th>价格</th>
				<th colspan="3">操 作</th>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr class="book">
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.name}" /></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.isbn}" /></td>
					<td><fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd" /></td>
					<td><img src="/upload/${book.cover}"  width="140" height="170"></td>
					<td><c:out value="${book.price}" /></td>
					<td><input type="button" value="修改" onclick="updateBook(${book.id})" /></td>
					<td><input type="button" value="删除" onclick="deleteBook(${book.id})" /></td>
					<td><input type="button" value="添加到购物车" onclick="addCart(${book.id})" /></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/common/pagination.jsp" flush="true" />
	</div>
</body>
</html>