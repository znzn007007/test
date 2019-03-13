<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/WEB-INF/base.jsp" flush="true" />
</head>
<body>
	<h1 align="center">书籍修改页面</h1>
	<div>
		<form action="update" method="post" enctype="multipart/form-data"
			id="bookForm">
			<table border="0" align="center">
				<tr>
					<td>书名：</td>
					<td><input type="text" name="name" id="name"
						value="${book.name}" /></td>
					<td class="name"></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="author" id="author"
						value="${book.author}" /></td>
					<td class="author"></td>
				</tr>
				<tr>
					<td>isbn：</td>
					<td><input type="text" name="isbn" id="isbn"
						value="${book.isbn}" /></td>
					<td class="isbn"></td>
				</tr>
				<tr>
					<td>价格：</td>
					<td><input type="text" name="price" id="price"
						value="${book.price}" /></td>
					<td class="price"></td>
				</tr>
				<tr>
					<td>出版日期：</td>
					<td><input type="date" name="publishDate" id="publishDate"
						value="<fmt:formatDate value='${book.publishDate}' pattern='yyyy-MM-dd'/>"></input></td>
					<td class="publishDate"></td>
				</tr>
				<tr>
					<td>简介：</td>
					<td><input type="textarea" name="introduction"
						id="introduction" value="${book.introduction}" /></td>
					<td class="introduction"></td>
				</tr>
				<tr>
					<td>封面：</td>
					<td><input type="file" name="coverImg" id="cover"
						value="${book.cover}" /></td>
					<td class="cover"></td>
				</tr>
				<tr>
					<td type="text-align:center;"><input type="hidden" name="id"
						value="${book.id}" /> <input type="submit" id="提交" value="提交" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>