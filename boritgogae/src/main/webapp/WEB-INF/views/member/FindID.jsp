<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://www.google.com/recaptcha/api.js?render=6Ld11bIiAAAAAKrv3Cqs8YDoEZFLmjLOVyCZHYo-"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>아이디 찾기</title>
<script>

	$(function () {
		
		$(".authForm").show();
		
	});
	
	function emailAuthCheck() {
		// 아이디 찾기
		let url = "/member/emailAuthCheck";
		let memberName = $("#memberName").val();
		let memberEmail = $("#memberEmail").val();
		let sendData = JSON.stringify({
			memberName : memberName, memberEmail : memberEmail
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		console.log(memberName);
		console.log(memberEmail);
		
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
			data: sendData,
	        dataType: "json", // 수신 받을 데이터 타입
	        headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
    			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
    			if (data != null) {
					// timestamp 날짜변환
    				let joinDate = new Date(data.joinDate);
    				let year = joinDate.getFullYear();
					let month = joinDate.getMonth() + 1;
					let date = joinDate.getDate();
					
    				$("#resultName").html(data.memberName + " 님의 정보와 일치하는");
    				$("#resultId").html("아이디는 " + data.memberId + " 입니다.");
    				$("#resultJoinDate").html(year + "." + month + "." + date + " 가입");
    				
    	        	$(".authForm").hide();
        			$(".resultForm").show();
    			}
    			
	        },
	        error : function(request, status, error) {
	            console.log("code:" + request.status + "\n"
	                  + "message:" + request.responseText + "\n"
	                  + "error:" + error);
	        }
	    });
	}
	
	
	
</script>
<style>
	#container div {
		width: 80%;
		margin: auto;
	}
	
	.inputBox {
		margin-bottom: 20px;
	}
	.btn {
		height: 46px;
	}
	
	#authCheck {
		margin-top: 20px;
		margin-bottom: 50px;
	}
	
	.heading {
		text-align: center;
		font-size: 30px;
		padding: 30px;
	}
	
	.resultForm div{
		text-align: center;
		font-size: 25px;
	}
	
	.btns {
		padding: 50px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div id="container">
	
		<div class="heading">아이디 찾기</div>
		
		<!-- 1차 인증 -->
		<div class="authForm" style="display: none">
			<div class="col-lg-6">
				<div class="checkout__input">
					<p>이름<span>*</span></p>
					<input type="text" class="inputBox form-control" id="memberName" placeholder="이름을 입력하세요" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="checkout__input">
					<p>이메일<span>*</span></p>
					<input type="email" class="inputBox form-control" id="memberEmail" placeholder="이메일을 입력하세요" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<!-- 캡챠자리 -->
			
			<div class="col-lg-6">
				<div class="checkout__input input-group">
					<input type="text" class="inputBox form-control" id="authNumber" placeholder="인증번호 입력" />
					<span class="input-group-btn">
						<button id="getAuthNumber" class="btn btn-secondary" type="button">인증번호 받기</button>
		      		</span>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="checkout__input d-grid">
					<button id="authCheck" class="btn btn-primary btn-block" onclick="emailAuthCheck();">아이디 찾기</button>
				</div>
			</div>
			
		</div>
		
		
		<!-- 2차 아이디찾기 결과 -->
		<div class="resultForm" style="display: none">
			<div id="resultName"></div>
			<div id="resultId"></div>
			<div id="resultJoinDate"></div>
	    	<div class="btns">
	    		<button class="btn btn-success" onclick="location.href='${contextPath}/member/logIn';">로그인 하러가기</button>
				<button class="btn btn-primary" onclick="location.href='${contextPath}/member/findPwd';">비밀번호 재설정</button>
			</div>
		</div>
		
    </div>
    
	
	<!--
    
    <hidden 인증번호 적는 곳>

    <button1>인증번호 받기</button>

    <button>1이후 아이디찾기</button>

    <에러> 받기버튼 ajax 로 회원정보 select
    입력한 정보와 일치하는 회원정보가 없습니다. 
    이름/이메일 주소를 확인해주세요.

    <받기되면>
    인증번호 발송 요청이 완료되었습니다.
    인증번호가 오지 않는 경우, 입력한 이름/이메일주소를 
    확인 후 다시 요청해주세요.
    
    <아이디찾기 버튼누른후>
    신태호님의 정보와 일치하는 아이디 목록입니다.
    shinsa0402
    2009.8.12 가입

    로그인하러가기 버튼(logIn 호출) | 비밀번호 재설정 버튼
-->
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>