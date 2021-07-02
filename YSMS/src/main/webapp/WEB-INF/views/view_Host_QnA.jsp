<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfo.css" type="text/css">
<meta charset="UTF-8">
<title>${hostQnaShareTitle }의 문의 목록 </title>
<style>
        #hostQna { border-collapse:collapse;  width:870px; margin-left:60px;}
       #hostQna th { background:#fbedaa url(header_bkg.png) repeat-x scroll center left; padding:10px 10px; text-align:center; }
       #hostQna td { background:#FDFAE8 none repeat-x scroll center left; padding:20px 15px; }
       #hostQna tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
	
	    .text_contentline {
		width:420px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;
	}
	input[type="button"]{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		
		font-size: 12px;
/* 		font-weight:700; */
		
		text-align: center;
		text-decoration: none;
		background-color: #fff;
/* 		background-color: #ace2f9; */
		
		border: 1px solid #f0f0f0;
		border-radius: 10px;
	}
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
		$(document).ready(function(){
	
	    $("#hostQna tr:odd").addClass("odd");
	    $("#hostQna tr:not(.odd)").hide(); 
	    $("#hostQna tr:first-child").show(); //열머리글 보여주기
	
	    $("#hostQna tr.odd").click(function(){
	        $(this).next("tr").toggle();
	        $(this).find(".arrow").toggleClass("up");
	    });
	});
</script>
</head>
<script type="text/javascript">

	function modifyQna(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=430");
	}

	function openNewWindow(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=230");
	}
	
</script>
<body>
<%@ include file="header.jsp" %>

	<div class="mainBox">
	<div class="contentBox">
		<h3 style="text-align: center;">${hostQnaShareTitle }의 문의 목록</h3>
		<table class="table" id="hostQna" align="center">
			<tr>
				<th style="width:150px;">게스트</th>
				<th>문의 내용</th>
				<th style="width:130px;">문의 일자</th>
				<th style="width:90px;">답변 여부</th>
			</tr>
			
			<c:choose>
			<c:when test="${!empty hostQnaList }">
			<c:forEach items="${hostQnaList }" var="hostQnaDto">
			<tr>
				<td>${hostQnaDto.qnaSender }</td>
				<td><div class="text_contentline">${hostQnaDto.qnaContent }</div></td>
				<td align="center">${hostQnaDto.qnaQ_updateDate }</td>
				<td align="center">${hostQnaDto.qnaYesNo }</td>
			</tr>
			<tr>
				<!--Detail  -->
				<td colspan = "2" align="left" style="margin-left:10px;">
					<p style="font-weight:700;">문의내용</p>
					<pre>${hostQnaDto.qnaContent }</pre>
					<br>
					<p style="font-weight:700;">호스트답변</p>
					<pre>${hostQnaDto.qnaAnswer }</pre>
					<p>${hostQnaDto.qnaA_updateDate}</p>
				</td>
				<td colspan="2" valign="bottom" align="right">
				<c:choose>
				<c:when test="${hostQnaDto.qnaYesNo == 'N' }">
					<a href="javascript:modifyQna('host_write_qna.four?qna_no=${hostQnaDto.qnaNo }')">
					<input type="button" value="답변작성하기"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:modifyQna('host_modify_qna.four?qna_no=${hostQnaDto.qnaNo }')">
					<input type="button" value="수정하기"></a>
					<a href="javascript:openNewWindow('host_deleteCheck_qna.four?qna_no=${hostQnaDto.qnaNo }')">
					<input type="button" value="삭제하기"></a>	
				</c:otherwise>
				</c:choose>
				</td>
			</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" align="center">등록된 질문이 없습니다.</td>
				</tr>
			</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="4" align="center">
					<!--Paging  -->
				<c:forEach items="${hostQnaPageList }" var="hostQnaPage">
					<a href="host_qna.four?place_no=${placeNo }&hostQnaPage=${hostQnaPage }">${hostQnaPage }</a>
				</c:forEach></td>
			</tr> 
		</table>
	</div>
</div>
<div style="margin-bottom:70px;"></div>
<%@ include file="footer.jsp" %>
</body>
</html>