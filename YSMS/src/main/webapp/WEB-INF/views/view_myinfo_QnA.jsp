<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/ysms/resources/css/myinfo.css" type="text/css">
<title>나의 문의 목록 </title>
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
       #myinfoQna { border-collapse:collapse;  width:870px; margin-left:60px;}
       #myinfoQna th { background:#fbedaa url(header_bkg.png) repeat-x scroll center left; padding:10px 10px; text-align:center; }
       #myinfoQna td { background:#FDFAE8 none repeat-x scroll center left; padding:20px 15px; }
       #myinfoQna tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
/*         #myinfoQna div.arrow { background:transparent url(arrows.png) no-repeat scroll 0px -16px; display:block;} 
         #myinfoQna div.up { background-position:0px 0px;} */
         
    .text_line {
		width:150px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;
	}
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
	
	    $("#myinfoQna tr:odd").addClass("odd");
	    $("#myinfoQna tr:not(.odd)").hide(); 
	    $("#myinfoQna tr:first-child").show(); //열머리글 보여주기
	
	    $("#myinfoQna tr.odd").click(function(){
	        $(this).next("tr").toggle();
	        $(this).find(".arrow").toggleClass("up");
	    });
	});
</script>
</head>
<script type="text/javascript">
	function modifyQna(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=230");
	}
	function openNewWindow(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=230");
	}	
</script>
<body>
<%@ include file="myinfoHeader.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<div class="textLeft" style="margin-left:60px; margin-bottom:30px; font-size:25px; font-weight:700;">나의 문의 목록</div>
		<table align="center" id="myinfoQna">
			<tr>
				<th style="width:150px;">공간 이름</th>
				<th>문의 내용</th>
				<th style="width:130px;">문의 일자</th>
				<th style="width:90px;">호스트<br>답변 여부</th>
			</tr>
			
			<c:choose>
			<c:when test="${!empty myInfoQnaList }">
			<c:forEach items="${myInfoQnaList }" var="Dto_QnA">
			<tr>
				<td align="center"><div class="text_line">${Dto_QnA.qnaPlaceName }</div></td>
				<td><div class="text_contentline">${Dto_QnA.qnaContent }</div></td>
				<td align="center">
				<fmt:parseDate var="parseRegDate" value="${Dto_QnA.qnaQ_updateDate }" pattern="yyyy-MM-dd"/>
				<fmt:formatDate var="resultRegDt" value="${parseRegDate}" pattern="yyyy-MM-dd"/>
				${resultRegDt }
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${!empty Dto_QnA.qnaAnswer }">
							<p>Y</p>
						</c:when>
						<c:otherwise>
							<p>N</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<!--Detail  -->
				<td colspan = "2" align="left">
					<p style="font-weight:700;">문의내용</p>
					<pre>${Dto_QnA.qnaContent }</pre>
					<br>
					<p style="font-weight:700;">호스트답변<p>
					<pre>${Dto_QnA.qnaAnswer }</pre>
					<p style="font-size:13px;">${Dto_QnA.qnaA_updateDate}</p>

				</td>
				<td colspan="2" valign="bottom" align="right">
				<a href="javascript:modifyQna('modify_qna?qna_no=${Dto_QnA.qnaNo }')">
				<input type="button" value="수정하기"></a>
				<a href="javascript:openNewWindow('deleteCheck_qna?qna_no=${Dto_QnA.qnaNo }')">
				<input type="button" value="삭제하기"></a>			
				</td>
			</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">등록하신 질문이 없습니다.</td>
				</tr>
			</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="4" align="center" >
					<!--Paging  -->
				<c:forEach items="${myinfoQnaPageList }" var="myinfoQnaPage">
					<a href="myinfo_qna?myinfoQnaPage=${myinfoQnaPage }">${myinfoQnaPage }</a>
				</c:forEach></td>
			</tr> 
		</table>
	</div>
</div>
<div style="margin-bottom:70px;"></div>

<%@ include file="footer.jsp" %>
</body>
</html>