<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/ysms/resources/css/Compeleted.css" type="text/css">
<title>Modify Completed!</title>
</head>
<script type="text/javascript">
	function pclose() {
		opener.location.reload();
		window.close();
	}
</script>
<body>
<table id="reviewCompeleted" align="center">
	<tr>
		<td align="center" valign="bottom">리뷰가 수정되었습니다!</td>
	</tr>
	<tr>
		<td align="center" valign="top"><input type="button" value="닫기" onclick="pclose();"></td>
	</tr>
</table>
</body>
</html>