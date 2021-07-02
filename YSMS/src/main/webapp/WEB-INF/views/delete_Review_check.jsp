<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/Compeleted.css" type="text/css">
<title>Delete Review</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
	session.setAttribute("rentalNo", rentalNo);
%>

<body>
<table id="reviewDeleteChk" align="center">
	<form action="review_delete.four" method="post">
	<input type="hidden" name="rentalNo" value="${rentalNo }">
	<tr>
		<td colspan="2" align="center" valign="bottom">정말 삭제하시겠습니까?</td>
	</tr>
	<tr>
		<td align="center" valign="top"><input type="submit" value="YES"></td></form>
		<td align="center" valign="top"><input type="button" value="NO" onclick="history.back();"></td>
	</tr>
</table>
</body>
<script type="text/javascript">
	sessionStorage.removeItem("rentalNo");
</script>
</html>