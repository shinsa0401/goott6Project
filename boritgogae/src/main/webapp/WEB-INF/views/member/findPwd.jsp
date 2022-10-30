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
<title>비밀번호 재설정</title>
<script>
	let checkCode = false;
	let authCheck = false;
	let hiddenSessionId = "";
	let memberId = "";
	
	
	$(function () {
		hiddenSessionId = "${memberId }";
		
		if (hiddenSessionId != "") {
			// 아이디찾기 완료 후 세션에 아이디 저장되었을 때(인증없이 바로 비밀번호재설정)
			$(".pwdUpdate").show();
			$("#memberPwd").focus();
			
		} else {
			// 페이지 로딩시 아이디확인 폼 출력
			$(".idCheckForm").show();
			$("#memberId").focus();
		
			
			// 인증번호 동일성
			$("#alert-success-email").hide();
		    $("#alert-danger-email").hide();
	
		    
		  	// 인증번호 이메일 전송
		    $("#getAuthNumber").on("click",function(e){
				e.preventDefault();
				
				let memberName = $("#memberName").val();
				let memberEmail = $("#memberEmail").val();
				
				if (memberName != "" && memberEmail != "") {
				
					let email = $("#memberEmail").val();
					let checkBox = $(".mailInputBox");
		
					$.ajax({
						type:"GET",
						url : "/member/mailCheck",
						data : {email : email},
						contentType :"text/plain;charset=UTF-8",
						success : function(data){ // 인증번호를 가져옴
		
							alert("인증번호 발송 요청이 완료되었습니다. 인증번호가 오지 않는 경우, 입력한 이름/이메일주소를 확인 후 다시 요청해주세요.");
							
							checkBox.attr("disabled",false); // 인증번호 입력 가능
							checkBox.val(''); // 기존에 값이 있었으면 지워줌
							$("#alert-success-email").hide();
							$("#alert-danger-email").hide();
							checkCode = false;
							code = data; // 인증번호를 변수에 저장
							authCheck = true; // 인증여부
						}
					});
				} else if (memberName == "") {
			  		alert("이름 입력");
			  		$("#memberName").focus();
			  	} else if (memberEmail == "") {
			  		alert("이메일 입력");
			  		$("#memberEmail").focus();
			  	}
			});
		          
		    // 인증코드 입력 시 동일성 확인
			$(".mailInputBox").keyup(function() {
				var inputCode = $(".mailInputBox").val();
				if (inputCode != "" || code != "") {
					if (inputCode == code) {
						$("#alert-success-email").show();
						$("#alert-danger-email").hide();
						$(".mailInputBox").attr("disabled",true); // 인증번호 입력 멈춤
						checkCode = true;
					} else {
						$("#alert-success-email").hide();
						$("#alert-danger-email").show();
						checkCode = false;
					}
				}
			});
		}
		
	});
	
	
	// 아이디 확인
	function idCheck() {
		let memberId = $("#memberId").val();
		let url = "/member/idCheck";
		
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
			data: { "memberId" :memberId },
	        dataType: "text", // 수신 받을 데이터 타입
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
    			if (data == "success") {
    				$(".idCheckForm").hide();
    				$(".authForm").show();
    				
    			} else if (data == "fail") {
    				alert("아이디없음");
    				$("#memberId").focus();
    			}
    		}
	    });
	}
		
		
	// 인증번호 확인 이후 비밀번호 재설정
	function emailAuthCheck() {
		
		let url = "/member/emailAuthCheck";
		let memberName = $("#memberName").val();
		let memberEmail = $("#memberEmail").val();
		let sendData = JSON.stringify({
			memberName : memberName, memberEmail : memberEmail
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		
		if (memberName != "" & memberEmail != "" && authCheck == true && checkCode == true) {
			
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
	    				
	    	        	$(".authForm").hide();
	    	        	$(".pwdUpdate").show();
	        			
	    			}
	    			
		        }
		    });
			
		} else if (memberName == "") {
			alert("이름 입력");
			$("#memberName").focus();
		} else if (memberEmail == "") {
			alert("이메일 입력");
			$("#memberEmail").focus();
		} else if (authCheck == false) {
			alert("인증 여부");
		} else if (checkCode == false) {
			alert("인증번호 틀림");
		}
		
	}
	
	// 비밀번호 재설정
	function pwdUpdate() {
		// 아이디찾기에서 세션에 저장한 아이디
		hiddenSessionId = "${memberId }";
		console.log(hiddenSessionId);
		
		// 세션아이디가 저장되어있다면
		if (hiddenSessionId != "") {
			memberId = hiddenSessionId;
		} else if (hiddenSessionId == "") { // 저장되어있지 않다면
			memberId = $("#memberId").val();	
		}
		
		console.log(memberId);
		
		let memberPwd = $("#newPwd").val();
		let memberPwdCheck = $("#newPwdCheck").val(); 
		let url = "/member/pwdUpdate";
		
		if (memberPwd != "" && memberPwdCheck != "" && memberPwd == memberPwdCheck) {
			
			$.ajax({
		        url: url, // 데이터 송수신될 주소
		        type: "post", // 통신 방식(get, post)
				data: { "memberId" :memberId,
						"memberPwd" :memberPwd},
		        dataType: "text", // 수신 받을 데이터 타입
		        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
		            // data : 성공했을 때 전달되는 데이터
		            console.log(data);
	    			if (data == "success") {
	    				$(".pwdUpdate").hide();
	    				$(".resultForm").show();
	    				
	    			} else if (data == "fail") {
	    				alert("실패");
	    			}
	    		}
		    });	
			
		} else if (memberPwd == "") {
			alert("비밀번호 없음");
			$("#memberPwd").focus();
		} else if (memberPwdCheck == "") {
			alert("비밀번호확인 없음");
			$("#memberPwdCheck").focus();
		} else if (memberPwd != memberPwdCheck) {
			alert("비밀번호와 비밀번호확인이 다름");
			$("#memberPwdCheck").focus();
		}
		
		
	}
	
	
</script>
<style>

	#container div {
		width: 90%;
		margin: auto;
	}
	
	.inputBox {
		margin-bottom: 20px;
	}
	
	.btn {
		height: 46px;
	}
	
	#idCheck, #authCheck, #pwdUpdate {
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
	
	.label {
		padding-top: 20px;
		padding-bottom: 10px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div id="container">
		<input type="hidden" id="hiddenSessionId" value="${memberId }" />
		
		<div class="heading">비밀번호 재설정</div>
		
		<!-- 1차 아이디확인 -->
		<div class="idCheckForm" style="display: none">
			<div class="col-lg-6">
				<div class="checkout__input">
					<input type="text" class="inputBox form-control" id="memberId" placeholder="아이디를 입력하세요" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="checkout__input d-grid">
					<button id="idCheck" class="btn btn-primary btn-block" onclick="idCheck();">아이디 확인</button>
				</div>
			</div>
		</div>
		
		
		
		<!-- 2차 인증 -->
		<div class="authForm" style="display: none">
			<div class="col-lg-6">
				<div class="label">이름</div>
				<div class="checkout__input">
					<input type="text" class="inputBox form-control" id="memberName" placeholder="이름을 입력하세요" style="color:black" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="label">이메일</div>
				<div class="checkout__input">
					<input type="email" class="inputBox form-control" id="memberEmail" placeholder="이메일을 입력하세요" style="color:black" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<!-- 캡챠자리 -->
			
			<div class="col-lg-6">
				<div class="label">이메일 인증</div>
				<div class="checkout__input input-group">
					<input type="text" class="mailInputBox form-control" id="authNumber" placeholder="인증번호 입력" disabled="disabled" />
					<span class="input-group-btn">
						<button id="getAuthNumber" class="btn btn-secondary" type="button">인증번호 전송</button>
		      		</span>
				</div>
				<!-- 인증번호 확인 -->
         		<div class="alert alert-success" id="alert-success-email">
         			인증번호가 일치합니다.
         		</div>
         		<div class="alert alert-danger" id="alert-danger-email">
         			인증번호가 일치하지 않습니다.
         		</div>
			</div>
			
			<div class="col-lg-6">
				<div class="checkout__input d-grid">
					<button id="authCheck" class="btn btn-primary btn-block" onclick="emailAuthCheck();">비밀번호 재설정</button>
				</div>
			</div>
		</div>
	
	
		<!-- 3차 비밀번호 재설정 -->
		<div class="pwdUpdate" style="display: none">
			<div class="col-lg-6">
				<div class="checkout__input">
					<input type="password" class="inputBox form-control" id="newPwd" placeholder="새 비밀번호를 입력하세요 (8~16)" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
				<div class="checkout__input">
					<input type="password" class="inputBox form-control" id="newPwdCheck" placeholder="새 비밀번호 확인" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="comment">
					<p>8~16자의 영문자, 숫자, 특수문자 조합</p>
					<p>아이디, 생일, 전화번호 등 개인정보 사용불가</p>
				</div>
				
				<div class="checkout__input d-grid">
					<button id="pwdUpdate" class="btn btn-primary btn-block" onclick="pwdUpdate();">확인</button>
				</div>
			</div>
		</div>
		
		
		<!-- 4차 비밀번호 재설정 결과 -->
		<div class="resultForm" style="display: none">
			<div class="comment">
				비밀번호 재설정 완료 되었습니다.
			</div>
			
	    	<div class="btns">
	    		<button class="btn btn-success" onclick="location.href='${contextPath}/member/logIn';">로그인 하러가기</button>
			</div>
		</div>
			
	</div>
	
	
	<!-- 
아이디 찾기 | 비밀번호 재설정

아이디 확인 후 비밀번호를 재설정 할 수 있습니다.
<input type="text" placeholder="아이디" />
<button>확인</button>
------------------------------------------

    아이디 출력

    <input type="text" placeholder="이름" /> 이름을 입력해주세요

    <input type="email" placeholder="이메일" /> 이메일 주소를 정확히 입력해주세요

    캡챠자리

    <hidden 인증번호 적는 곳>

    <button1>인증번호 받기</button>

    <button>1이후 비번 재설정</button>

    <에러> 받기버튼 ajax 로 회원정보 select
    입력한 정보와 일치하는 회원정보가 없습니다. 
    이름/이메일 주소를 확인해주세요.

    <받기되면>
    인증번호 발송 요청이 완료되었습니다.
    인증번호가 오지 않는 경우, 입력한 이름/이메일주소를 
    확인 후 다시 요청해주세요.

    <비밀번호 재설정 버튼 누른후>
    shinsa0402님 새 비밀번호를 입력해 주세요.
    <input type="password" placeholder="새 비밀번호 (8~15)" /> 비밀번호가 적합하지 않습니다. (비밀번호표시)
    <input type="password" placeholder="새 비밀번호 확인" /> 비밀번호가 서로 일치하지 않습니다. (비밀번호표시)

    일치하지않으면 확인버튼 안눌림
    

    8~15자의 영문 대/소문자, 숫자, 특수문자 중 2개 이상 조합
    사용가능 특수문자: ! “ $ % & ‘ () * + , - . / : ; < > = ? @ # [] ₩ ^ _`{} ~ |
    동일하거나 연속된 4자리 이상의 숫자/영문 반복 사용불가
    아이디, 생일, 전화번호 등 개인정보 사용불가
 -->
 
 	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>