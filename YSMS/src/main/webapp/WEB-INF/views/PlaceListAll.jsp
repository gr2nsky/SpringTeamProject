<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- 숫자 포맷 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<link href="css/PlaceSearchPage.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<meta charset="UTF-8">
</head>
<body>
<div class="mainBox">
	<div class="contentBox">
		<table id="shareList" align="center">
			<c:forEach  items="${shareList }" var="Dto_SearchPlace"> <!-- items 잊지 말 것 -->
			<tr>
				<td class="photo" rowspan="2" align="right"><div class="shareList">
				<a href="detail.four?no=${Dto_SearchPlace.no }">
				<img class="shareListPhoto" src="${Dto_SearchPlace.filePath }"/></a></div></td>	
				<th width="200">공간유형</th>		
				<td width="200">${Dto_SearchPlace.category }</td>
				<th width="200">이름</th>
				<td width="200"><a href="detail.four?no=${Dto_SearchPlace.no }">${Dto_SearchPlace.title }</a></td>	
			</tr>
			
			<!-- <tr>
				<td colspan="4"></td>
			</tr> -->
			<tr>
				<th >주소</th>		
				<td >${Dto_SearchPlace.address1 } ${Dto_SearchPlace.address2 }</td> 
				<th>가격<br>(원/시간)</th>
				<td><fmt:formatNumber value="${Dto_SearchPlace.price }" pattern="##,###"/></td>	
		
			</tr>	
			<tr>
				<td colspan="4"></td>
			</tr>
			
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<!-- 페이징 부분 -->
					<c:forEach items="${shareListpage }" var="sharePage">
						<a href="SearchPlacePage.four?sharePage=${sharePage }">${sharePage } </a>
					</c:forEach>
				</td>
					</tr>
		</table>
	</div>
</div>
</body>
</html>