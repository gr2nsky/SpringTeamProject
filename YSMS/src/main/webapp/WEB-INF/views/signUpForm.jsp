<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/ysms/resources/css/signUpForm.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	/* *{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	} */
</style>

</head>
<%
	Date date = new Date();
	int year = date.getYear() + 1900;
	pageContext.setAttribute("year", year);
%>
<script type="text/javascript">
	function validationCheck(){
		var form = document.signUpForm;
		//name check
		var namePattern = /^[가-힣]{2,6}$/;
		var name = form.name.value;
		//phoneNo check
 		var phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
		var phone = form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value;
		
		//생년월일 체크
		var date = new Date();
		var realMonth = date.getMonth()+1;
		var realToDay = date.getDate();
		
		var inputedYear = form.year.value;
		var inputedMonth = form.month.value;
		var inputedDay = form.day.value;
		

		if(form.idDupleChecked.value == "false") {
			alert("아이디 중복확인을 해 주세요.");
			return false;
		}
		if(form.pwCheck.value == "false") {
			alert("비밀번호 확인을 해 주세요.");
			return false;
		}	
		if(form.authPass.value == "false") {
			alert("이메일 인증을 진행해 주세요.");
			return false;
		}	
		
 		if(!check(namePattern, name, "유효하지 않은 이름입니다. 2글자이상의 한글만 입력 가능합니다.")){
			return false;
		}
		if(!check(phonePattern, phone, "유효하지 않은 핸드폰 번호입니다.")) {
			return false;
		} 

		if(inputedYear== 0 || inputedMonth == 0 || inputedDay == 0) {
			alert("생년월일을 선택해 주세요.");
			return false;
		}
		
		if(inputedMonth > realMonth) {
			alert("올바른 생년월일을 선택해 주세요.");
			return false;
		}
		
		if(inputedMonth >= realMonth && inputedDay >= realToDay){
			alert("올바른 생년월일을 선택해 주세요.");
			return false;
			}
		
		
		if (sessionStorage.getItem("pwEqualCheck") == "null"){
			alert("비밀번호 일치확인을 해 주세요.");
			return false;
		}
		
		alert("회원가입이 성공적으로 완료되었습니다.");
		form.submit(); 
	}
	function idValCheck(){
		var form = document.signUpForm;
		var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/;
		var id = form.id.value;
		if(!check(idPattern, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하세요")) {
			return false;
		}
	}
	
	function check(pattern, taget, message) {
		if(pattern.test(taget)) {
	    	return true;
	    }
	    alert(message);
	    taget.focus();
	}
	
	function idDupleCheck(){
		idValCheck();
		var url = "confirmID?id=" + document.signUpForm.id.value;
		open(url, "confirm",
				"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}
	
	function pwEqualCheck(){
		var form = document.signUpForm;
		var passwordPattern = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var pw1 = form.pw1.value;
		var pw2 = form.pw2.value;
		
		if(!check(passwordPattern, pw1, "비밀번호는 8~15자리의 영문, 숫자, 특수문자로 조합해야 합니다.")) {
			return false;
		}
		
		if (pw1 != pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			sessionStorage.removeItem("pwEqualCheck");
			document.getElementById('inputPwForm1').readOnly = false;
			document.getElementById('inputPwForm2').readOnly = false;
			return false;
		}
		sessionStorage.setItem("pwEqualCheck", "true");
		document.getElementById('inputPwForm1').readOnly = true;
		document.getElementById('inputPwForm2').readOnly = true;
		form.pwCheck.value = true;
		alert("사용가능한 비밀번호입니다.");
	}
	
	function emailValCheck(){
		var form = document.signUpForm;
		var emailPattern= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var email = form.email.value;
		if(!check(emailPattern, email, "유효하지 않은 이메일 주소입니다.")) {
			return false;
		}
	}
	
	function emailAuthentication(){
		emailValCheck();
		var url = "confirmEmail?email=" + document.signUpForm.email.value;
		open(url, "confirm",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
		document.signUpForm.authCodeCheckBtn.disabled = false;
		document.signUpForm.inputAuthCode.disabled = false;
	}
	
	function authCodeCheck(){
		var url = "authCodeCheck?inputedCode=" + document.signUpForm.authCode.value;
		open(url, "confirm",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}

	
</script>
<body>
<%@ include file="header.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<h1> 회원가입 </h1>
		<form name="signUpForm" action="signUpInput" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<table class="table" style="margin-left: auto; margin-right: auto;">
			<tr>
				<th>아이디</th>
				<td colspan="2"><input type="text" name="id" id="inputIdForm"  maxlength="12">
				<button onclick="idDupleCheck()" type="button" class="btnChk">중복확인</button>
				<input type="hidden" name="idDupleChecked" id="idDupleChecked" value="false">
				</td>
				<!-- <td><input type="hidden" name="idDupleChecked" id="idDupleChecked" value="false"></td> -->
			</tr>
			<tr>
				<th rowspan="2">비밀번호</th>
				<td rowspan="2"> <input type="password" name="pw1" id="inputPwForm1" maxlength="15">
				<button onclick="pwEqualCheck()" type="button" class="btnChk">비밀번호  확인</button>
				<br>
				<input type="password" name="pw2" id="inputPwForm2"  maxlength="15"><br>
				<input type="hidden" name="pwCheck" id="pwCheck" value="false"></td>
			</tr>			
			<tr>
				<td></td>
				
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>사진 등록</th>
				<td colspan="2"><input type="file" name="uploadPhoto" class="file-input" ></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td colspan="2">
					<select name="phone1">
						<option value="010" selected="selected"> 010
						<option value="011"> 011
						<option value="018"> 018
					</select>
					<input type="text" name="phone2" size="4" maxlength="4">
					<input type="text" name="phone3" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" id="inputEmailForm"  maxlength="30">
				<button onclick="emailAuthentication()" id="eamilAuthBtn" type="button" class="btnChk">인증 메일 보내기</button></td>
			</tr>
			<tr>
				<th rowspan="2"><a>인증번호 입력</a></th>
				<td colspan="2"><input type="text" name="authCode" id="inputAuthCode"  maxlength="10" disabled="disabled">
				<button onclick="authCodeCheck()" id="authCodeCheckBtn" type="button" disabled="disabled" class="btnChk">인증</button>
				<input type="hidden" name="authPass" id="authPass" value="false">
				</td>
				<!-- <td><input type="hidden" name="authPass" id="authPass" value="false"></td> -->
			</tr>		
			<tr>
				<td colspan="2"><a class="a">* 이메일이 도착하는데 1~2분정도 소요될 수 있습니다.</a><br></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td colspan="2">
					<select name="year">
					    <option value="0">선택</option>
					    <c:set var="year" value="${year}"/>
					    <c:forEach var="i" begin="0" end="100">
						    <c:set var="yearOption" value="${year - i}" />
						    <option value="${yearOption}">${yearOption}</option>
					    </c:forEach>
					</select>년
					<select name="month">
						<option value="0">선택</option>
						<c:forEach var="month" begin="1" end="12">
						    <option value="${month}">${month}</option>
						</c:forEach>
					</select>월
					<select name="day">
						<option value="0">선택</option>
						<c:forEach var="day" begin="1" end="31">
						    <option value="${day}">${day}</option>
						</c:forEach>
					</select>일
				</td>
			</tr>		
		</table>
		<br>
		<table class="table2" style="margin-left: auto; margin-right: auto;">
			<tr>
				<td align="right"><input type="button" value="가입하기" onclick="validationCheck()" class="button"></td>
			</tr>
		</table>
			<br>
		</form>
	</div>
</div>
	

<%@ include file="footer.jsp" %>
	
</body>
</html>