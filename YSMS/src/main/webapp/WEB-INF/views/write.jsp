<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/write.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript">
		let shareableDateNum = 1111111*1;
		let startTime = 0*1;
		
		function dateHandler(checkBox){
			console.log("변경전");
			console.log(shareableDateNum);
			if(checkBox.checked){
				console.log("체크됨");
				shareableDateNum += (checkBox.value*1 );
			}else {
				console.log("체크해제");
				shareableDateNum -= (checkBox.value*1 );
			}
			console.log("변경후");
			console.log(shareableDateNum);
			
			dayLimit.value = shareableDateNum;
		}
		
		function selectedStartTime(input){
			console.log(input);
			startTime = input*1;
		}
		function selectedEdnTime(input){
			console.log(input);
			if (input*1 <= startTime*1){
				alert("종료시간이 시작시간과 같거나 빠릅니다.");
				document.writeForm.endTime.value=0;
			}
		}
	</script>
<body>
	<%@ include file="header.jsp" %>
	<div class="mainBox">
	<div class="contentBox">
	<h3 style="text-align: center;">공간 나눔(호스트) 신청 : 영업정보 입력</h3>
	<table class="table" style="margin-left: auto; margin-right: auto;">
		<form action="write.four" name="writeForm" method="post" enctype="multipart/form-data">
			<tr>
				<th>공간이름</th>
				<td><input type="text" name="title" size="50"></td>
			</tr> 
			<tr>
				<th>시간당 금액(원)</th>
				<td><input type="text" name="price" size="50"></td>
			</tr> 
			<tr>
				<th>시작시간</th>
				<td>
					<select name="startTime" onchange="selectedStartTime(this.value)">
						<option value="0">선택</option>
						<c:forEach var="time" begin="8" end="22" step="1">
							<option value="${time}">${time} : 00</option>
						</c:forEach>
					</select>
				</td>
			</tr> 
			<tr>
				<th>종료시간</th>
				<td>
					<select name="endTime" onchange="selectedEdnTime(this.value)">
						<option value="0">선택</option>
						<c:forEach var="time" begin="8" end="22" step="1">
							<option value="${time}">${time} : 00</option>
						</c:forEach>
					</select>
				</td>
			</tr> 
			<tr>
				<th>예약가능요일</th>
				<td>
					<input type="hidden" id="dayLimit" name="dayLimit" value="" size="50">
					<input type="checkbox" value="1000000" onclick="dateHandler(this)" checked="checked">일
					<input type="checkbox" value="100000" onclick="dateHandler(this)" checked="checked">월
					<input type="checkbox" value="10000" onclick="dateHandler(this)" checked="checked">화
					<input type="checkbox" value="1000" onclick="dateHandler(this)" checked="checked">수
					<input type="checkbox" value="100" onclick="dateHandler(this)" checked="checked">목
					<input type="checkbox" value="10" onclick="dateHandler(this)" checked="checked">금
					<input type="checkbox" value="1" onclick="dateHandler(this)" checked="checked">토
				</td>
			</tr> 
			<tr>
				<th style="vertical-align:middle">내용</th>
				<td><textarea rows="10" cols="50" name="introduce"></textarea></td>
			</tr> 
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="uploadFile" ></td>
			</tr> 
			
		</table>
		
		<table class="table2" style="margin-left: auto; margin-right: auto;">
			<tr><td>
			<input type="hidden" name="user_id" value="${loginedUserID }">
			<input type="hidden" name="postCode" value="${POSTCODE }">
			<input type="hidden" name="capacity" value="${CAPACITY }">
			<input type="hidden" name="category" value="${CATEGORY }">
			</td></tr>
			<tr><td><button type="submit" class="btnChk" >신청하기</button></td></tr>
			<!-- <tr><td><input type="submit" value="신청하기" class="btnChk"></td></tr> -->
		</table>
		<br><br><br><br>
		<br><br><br><br>
		</form>
	
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>