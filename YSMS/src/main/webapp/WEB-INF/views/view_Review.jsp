<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/ysms/resources/css/spaceDetailView_QnaReview.css" type="text/css">
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	table{
		width: 700px;
		padding: 15px;
/* 		table-layout: fixed; */
		border: 1px solid #f0f0f0;
	}
	th{
		padding: 10px;
	}	
	td{
		margin: 0;
		padding: 15px;
/*  		border: 1px solid #f0f0f0;   */
	}
	.photo{
		width: 170px;
	}
	
	textarea{
		width: 100%;
		height: 80px;
		padding: 10px;
		box-sizing: border-box;
		border: none;
		font-size: 15px;
		resize: none;
		background-color: #fff;
	}

	
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}
	
</style>
</head>
<body>
<div class=mainBox>
	<div class="contentBox">
	<table align="center">
		<c:choose>
		<c:when test="${!empty reviewList }">
		<c:forEach items="${reviewList }" var="Dto_Review">
		<tr>
			<th colspan="2" align="left">
			<span style="padding:5px; font-size:15pt; font-weight:700;">이용 후기</span>
			<div class="underline" style="margin-top:10px; margin-left:5px;"></div>
			</th>
<%-- 			<th align="right">${reviewDto.reviewScore }</th> --%>
			<th align="right">
				<c:if test="${Dto_Review.reviewScore == 5 }">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
				</c:if>
				<c:if test="${Dto_Review.reviewScore == 4 }">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
				</c:if>
				<c:if test="${Dto_Review.reviewScore == 3 }">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
				</c:if>
				<c:if test="${Dto_Review.reviewScore == 2 }">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
				</c:if>
				<c:if test="${Dto_Review.reviewScore == 1 }">
					<img class="reviewScore" src="${pageContext.request.contextPath }/resources/reviewPhoto/reviewScore.PNG">
				</c:if>
			</th>
		</tr>
		<tr>
			<td  class="photo" rowspan="3" valign="top" align="center">
			<div class="user">
				<img class="userProfile" src="userPhoto/${Dto_Review.userFilePath }">
			</div>
			</td>
			<td align="left" style="font-weight:700;">${Dto_Review.rentalUser_id }</td>
			<td align="right"><span style="font-size:13px;">${Dto_Review.reviewUpdateDate }</span></td>
		</tr>
				<c:if test="${!empty Dto_Review.reviewFilePath }">
		<tr>
			<td colspan="3" align="center">
				<div class="review">
					<img class="reviewPhoto" src="${pageContext.request.contextPath }/resources/${Dto_Review.reviewFilePath }">
				</div>
			</td>
		</tr>
				</c:if>
		<tr>
			<td colspan="3">
			<textarea readonly="readonly" disabled="disabled">${Dto_Review.reviewContent }</textarea></td>
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="3"><textarea readonly="readonly" disabled="disabled">등록된 후기가 없습니다.</textarea></td>
			</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="3" align="center">
				<!--Paging  -->
			<c:forEach items="${reviewPageList }" var="reviewPage">
				<a href="review?place_no=${placeNo }&reviewPage=${reviewPage }">${reviewPage }</a>
			</c:forEach></td>
		</tr>
	</table>
	</div>
</div>

</body>
</html>