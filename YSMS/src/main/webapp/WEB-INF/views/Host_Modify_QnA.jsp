<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/QnA.css" type="text/css">
<title>Modify QnA Answer</title>
</head>
<script type="text/javascript">
	
	function checkQnaContent(){
		
		if(document.hostAnswerQna.qnaAnswer.value.trim() == ""){
			alert("답변을 입력해주세요.");
			document.hostAnswerQna.qnaAnswer.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.hostAnswerQna.submit();
			return true;
		}
	}
</script>
<body>
	<form name="hostAnswerQna" action="host_qna_modify.four" method="post">
		<input type="hidden" name="qna_no" value="${qnaDetail.qnaNo }">
	<table id="hostWriteQna" align="center">
		<tr>		
			<th align="left">문의 내용</th>
			<th align="right">
			<input type="reset" value="되돌리기">
			<input type="button" value="수정" onclick="checkQnaContent();"></th>
		</tr>
		<tr>
			<td colspan="2"><textarea readonly="readonly" disabled="disabled">${qnaDetail.qnaContent }</textarea></td>
		</tr>
		<tr>		
			<th colspan="2" align="left" style="border-top:1px solid #dcdcdc">답변 작성하기</th>
		</tr>
		<tr>
			<td colspan="2"><textarea name="qnaAnswer">${qnaDetail.qnaAnswer }</textarea></td>
		</tr>			
	</table>
	</form>
</body>
</html>