<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/contentsView.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>공지 사항</title>
</head>
<%@ include file="header.jsp" %>
<div class="mainBox">
<div class="contentBox">
<body>
	<h3>공지사항</h3>
	<table class="table">
			<tr>
				<th>No. </th>
				<td><input type="text" name="no" readonly="readonly" value="${announceContent_view.no }"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="100" readonly="readonly" value="${announceContent_view.title}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="50" cols="100" name="content" readonly="readonly" style="height:500px;">${announceContent_view.content}</textarea></td>
			</tr>
			
	</table>
	<table class="table2">
		<tr>		
			<td align="right">
			<a href = "announce_user.four"><button type="button" class="btnGoList" >목록보기</button></a>
			</td>
		</tr>
	</table>
</div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>