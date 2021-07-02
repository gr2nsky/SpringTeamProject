<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/QnA.css" type="text/css">
<title>문의하기 : 너의 공간 나의 공간 Your space My space</title>
</head>
<script type="text/javascript">
	
	function checkQnaContent(){
		
		if(document.writeQna.qnaContent.value.trim() == ""){
			alert("질문을 입력해주세요.");
			document.writeQna.qnaContent.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.writeQna.submit();
			return true;
		}
	}
</script>
<body>
<%
	int place_no = Integer.parseInt(request.getParameter("place_no"));
	session.setAttribute("place_no", place_no);
%>

	<form name="writeQna" action="qna_write.four" method="post">
	
		<input type="hidden" name="place_no" value="${place_no }">
	<table id = "writeQna" align="center">
		<tr>		
			<th align="left">문의 내용</th>
			<th align="right">
			<input type="reset" value="지우기">
			<input type="button" value="등록" onclick="checkQnaContent();">
			</th>
		</tr>
		<tr>
			<td colspan="2" align="center"><textarea name="qnaContent" placeholder="문의 내용을 입력 해 주세요."></textarea></td>
		</tr>			
	</table>
	</form>
</body>
</html>