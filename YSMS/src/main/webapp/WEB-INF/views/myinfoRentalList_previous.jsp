<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/ysms/resources/css/myinfoRentalList.css" type="text/css">
<html>
<title>My Rental List : Previous Reservation </title>
</head>

<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	.table_outter{
		width: 970px;
		padding: 10px;
/*   		border: 1px solid #f0f0f0;  */
		font-size: 18px;
	}
 	.table_inner{
		width: 950px;
		padding: 15px;
		font-size: 18px;
	} 
	th{
		padding: 0px;
		margin-botton: 10px;
	}
	td{
		font-size:15px;
		padding: 5px;
	}
	.td_title{
		text-align: left;
		font-size:15px;
		padding: 7px;
		font-weight:700;
		width:70px;
	}
	.td_content{
		text-align: left;
		font-size:15px;
		padding: 7px;
		width: 150px;
		
	}
	
	hr{
		border-top: 1px solid #f0f0f0;
	}
	.button_rental{
		margin: 3px;
		padding: 8px;
		display: inline-block;
		width: 65px;
		
		font-size: 12px;
 		color: #fff; 
		
		text-align: center;
		text-decoration: none;
		background-color: #91D8FA;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_cancel{
		margin: 3px;
		padding: 8px;
		display: inline-block;
		width: 65px;
		
		font-size: 12px;
 		color: #fff; 
		
		text-align: center;
		text-decoration: none;
		background-color: #FFCD28;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_myReview{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		width: 135px;
		
		font-size: 13px;
		color: #6e6e6e;
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #dcdcdc;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_writeReview{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		width: 135px;
		
		font-size: 13px;
		color: #fff;
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #ace2f9;
		
		border: none;
		border-radius: 10px;
	}
	
	
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}
</style>
<script type="text/javascript">
	function openReview(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=500,height=700");
	}
</script>
<body>
<%@ include file="myinfoHeader.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<div class="textLeft" style="margin-bottom:20px;">
			<a href="myinfo_rental_scheduled">????????? ??????</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>?????? ??????</strong>
		</div>
		<hr>
		<table class="table_outter">
		<c:choose>
			<c:when test="${!empty myinfoRentalPreviousList }">
			<c:forEach items="${myinfoRentalPreviousList }" var="Dto_Rental" varStatus="status">
			<!-- ???????????? ???  -->
			<tr><td>
			<hr>
				<table class="table_inner">
					<tr>
						<th rowspan="4"><div class="rentalList">
							<img class="rentalListPhoto" src="${pageContext.request.contextPath }/resources/${Dto_Rental.rentalPhoto }">
							</div></th>
						<th colspan="3" align="left">${Dto_Rental.rentalTitle }</th>
						<th align="right">
							<c:choose>
								<c:when test="${empty Dto_Rental.rentalCancellationDate }">
									<button class="button_rental" disabled="disabled"> ???????????? </button><button class="button_rental" disabled="disabled"> ???????????? </button>					
								</c:when>
								<c:otherwise>
									<button class="button_cancel" disabled="disabled"> ???????????? </button><button class="button_cancel" disabled="disabled"> ???????????? </button>
								</c:otherwise>
							</c:choose>
						</th>
					</tr>
					<tr>
						<td class="td_title">???????????? :</td>
						<td class="td_content">
						<fmt:parseDate var="parseRegDate" value="${Dto_Rental.checkInDate }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="resultRegDt" value="${parseRegDate}" pattern="yyyy-MM-dd"/>
						${resultRegDt }
						</td>
						<td class="td_title">???????????? :</td>
						<td class="td_content">
						<fmt:parseDate var="parseRegDate" value="${Dto_Rental.rentalDate }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="resultRegDt" value="${parseRegDate}" pattern="yyyy-MM-dd"/>
						${resultRegDt }
						</td>
					</tr>
					<tr>
						<td class="td_title">???????????? :</td>
						<td class="td_content">${Dto_Rental.rentalStartTime }??? ~ ${Dto_Rental.rentalEndTime }???</td>
						<td class="td_title">???????????? :</td>
						<td class="td_content">${Dto_Rental.rentalNo }</td>
					</tr>
					<tr>
						<td class="td_title">??? ?????? :</td>
						<td class="td_content">&#8361; <fmt:formatNumber value="${Dto_Rental.rentalPrice }" pattern="#,###"/></td>
			 			<c:choose>
							<c:when test="${!empty Dto_Rental.rentalCancellationDate }">
							<td class="td_title">???????????? :</td>
							<td class="td_content">
							<fmt:parseDate var="parseRegDate" value="${Dto_Rental.rentalCancellationDate }" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="resultRegDt" value="${parseRegDate}" pattern="yyyy-MM-dd"/>
							${resultRegDt }
							</td>
							</c:when>
							<c:when test="${empty Dto_Rental.reviewSubmitDate }">
							<td colspan="2" align="right">
								<a href="javascript:openReview('write_review?rentalNo=${Dto_Rental.rentalNo }')">
								<button class="button_writeReview">????????????</button></a>
								<div style="padding-left:100px; padding-top:5px; text-align:center; font-size:9px; color:#6e6e6e">?????? ?????? ????????? ????????????????? <br> ????????? ????????? ???????????????.</div>
							</td>
							</c:when>				
							<c:otherwise>
							<td colspan="2" align="right"><a href="javascript:openReview('detail_review?rentalNo=${Dto_Rental.rentalNo }')">
							<button class="button_myReview">?????? ??? ?????? ??????</button></a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</td></tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">?????? ????????? ????????????.</td>
				</tr>
			</c:otherwise>
		</c:choose>
			<tr><td align="center">
			<hr><br><br>
				<c:forEach items="${rentalPreviousPageList }" var="rentalPreviousPage">
				<a href="myinfo_rental_previous?rentalPreviousPage=${rentalPreviousPage }">${rentalPreviousPage }</a>
				</c:forEach>
			</td></tr>
		</table>
	</div>
</div>
	<%@ include file="footer.jsp" %>
</body>
</html>