<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/QnA.css" type="text/css">
<title>Modify QnA</title>
</head>
<script type="text/javascript">
	
	function checkQnaContent(){
		
		if(document.modifyQna.qnaContent.value.trim() == ""){
			alert("내용을 입력해주세요.");
			document.modifyQna.qnaContent.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.modifyQna.submit();
			return true;
		}
	}
</script>
<body>


	<form name="modifyQna" action="qna_modify.four" method="post">
		<input type="hidden" name="qna_no" value="${qnaDetail.qnaNo }">
	<table id="writeQna" align="center">
		<tr>		
			<th align="left">문의 내용</th>
			<th align="right">
			<input type="reset" value="되돌리기">
			<input type="button" value="수정" onclick="checkQnaContent();"></th>
		</tr>	
		<tr>
			<td colspan="2"><textarea name="qnaContent">${qnaDetail.qnaContent }</textarea></td>
		</tr>			
	</table>
	</form>
</body>
</html>