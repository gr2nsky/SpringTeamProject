<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/Compeleted.css" type="text/css">
<title>Delete QnA</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	int qna_no = Integer.parseInt(request.getParameter("qna_no"));
	session.setAttribute("qnaNo", qna_no);
%>

<body>
<table id="qnaDeleteChk" align="center">
	<form action="qna_delete.four" method="post">
	<input type="hidden" name="qna_no" value="${qnaNo }">
	<tr>
		<td colspan="2" align="center" valign="bottom">정말 삭제하시겠습니까?</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="YES"></td></form>
		<td align="center"><input type="button" value="NO" onclick="window.close();"></td>
	</tr>
</table>
</body>
<script type="text/javascript">
	sessionStorage.removeItem("qnaNo");
</script>
</html>