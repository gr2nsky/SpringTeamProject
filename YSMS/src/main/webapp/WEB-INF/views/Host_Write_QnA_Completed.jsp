<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/Compeleted.css" type="text/css">
<title>Write QnA Answer Completed!</title>
</head>
<script type="text/javascript">
	function pclose() {
		opener.location.reload();
		window.close();
	}
</script>
<body>
<table id="qnaCompeleted" align="center">
	<tr>
		<td align="center" valign="bottom">답변이 등록되었습니다!</td>
	</tr>
	<tr>
		<td align="center"><input type="button" value="닫기" onclick="pclose();"></td>
	</tr>
</table>
</body>
</html>