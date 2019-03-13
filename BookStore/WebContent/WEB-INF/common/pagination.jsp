<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="catalog" align="center">
	<tr>
		<td>共${page.totalNum}本书，当前第${page.pageNo}页</td>
		<td><a href="${req}">首页</a></td>
		<td><c:if test="${page.pageNo > 1}">
				<a href="${req}?pageNo=${page.pageNo - 1}">上一页</a>
			</c:if> <c:if test="${page.pageNo == 1}">上一页</c:if></td>
		<td><c:if test="${page.pageNo < page.maxPage}">
				<a href="${req}?pageNo=${page.pageNo + 1}">下一页</a>
			</c:if> <c:if test="${page.pageNo == page.maxPage}">下一页</c:if></td>
		<td><a href="${req}?pageNo=${page.maxPage}">尾页</a></td>
	</tr>
</table>
