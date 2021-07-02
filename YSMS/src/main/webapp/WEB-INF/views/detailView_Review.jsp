<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/review.css" type="text/css">
<title>내가 쓴 리뷰</title>
</head>
<script type="text/javascript">
	function modifyReview() {
		document.reviewDetail.submit();
	}
	function deleteReview() {
		var url = "deleteCheck_review.four?rentalNo=" + ${rentalNo } ;
		location.href = url
	}
</script>
<body>
<%
	int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
	session.setAttribute("rentalNo", rentalNo);
%>
	<form name="reviewDetail" action="modify_review.four" method="post">
	<table id="detailReview" align="center">
		<tr>
			<th colspan="2" align="left">내가 쓴 리뷰
			<input type="hidden" name="rentalNo" value="${rentalNo }">
			<input type="hidden" name="reviewScore" value="${detailViewReview.reviewScore }">
			<input type="hidden" name="reviewFilePath" value="${detailViewReview.reviewFilePath }">
			<input type="hidden" name="reviewContent" value="${detailViewReview.reviewContent }"></th>
			<th align="right" style="padding:0px;"><input type="button" value="수정하기" onclick="modifyReview();"></th>
			<th align="right" style="padding:0px;"><input type="button" value="삭제하기" onclick="deleteReview();"></th>
			<th align="right" style="padding:0px;"><input type="button" value="닫기" onclick="window.close();"></th>
			
		</tr>
		<tr>
			<td colspan="3"class="title" align="left">${detailViewReview.reviewPlaceName }</td>
			<td colspan="2"class="title" align="right">
			<c:if test="${detailViewReview.reviewScore == 5 }">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				</c:if>
				<c:if test="${detailViewReview.reviewScore == 4 }">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				</c:if>
				<c:if test="${detailViewReview.reviewScore == 3 }">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				</c:if>
				<c:if test="${detailViewReview.reviewScore == 2 }">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				</c:if>
				<c:if test="${detailViewReview.reviewScore == 1 }">
				<img class="reviewScore" src="reviewPhoto/reviewScore.png">
				</c:if></td>
		</tr>
		<c:if test="${!empty detailViewReview.reviewFilePath }">
		<tr>
			<td class="photo" colspan="5" align="center">
				<div class="review">
				<img class="reviewPhoto" src="reviewPhoto/${detailViewReview.reviewFilePath }">
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="5" class="content" valign="top">
			<pre>${detailViewReview.reviewContent }</pre>
			</td>
		</tr>
		<tr>
			<td colspan="5" class="date">
				${detailViewReview.reviewUpdateDate }
			</td>
		</tr>
	</table>
	</form>

</body>
</html>