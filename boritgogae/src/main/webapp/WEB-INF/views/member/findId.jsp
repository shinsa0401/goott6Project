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
	let checkCode = false;
	let authCheck = false;

	$(function () {
		// 페이지 로딩시 이메일 인증폼 출력
		$(".authForm").show();
		
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
						$("#authNumber").focus();
					}
				});
				
			} else if (memberName == "") {
		  		alert("이름을 입력하세요");
		  		$("#memberName").focus();
		  	} else if (memberEmail == "") {
		  		alert("이메일을 입력하세요");
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
	    
	});
	
	// 인증번호 확인이후 아이디찾기
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
						// timestamp 날짜변환
	    				let joinDate = new Date(data.joinDate);
	    				let year = joinDate.getFullYear();
						let month = joinDate.getMonth() + 1;
						let date = joinDate.getDate();
						
	    				$("#resultName").html(data.memberName + " 님의 정보와 일치하는");
	    				$("#resultId").html(data.memberId);
	    				$("#resultJoinDate").html(year + "." + month + "." + date + " 가입");
	    				
	    				// JSON 데이터는 Object 형식이라 String으로 변환
	    				let memberId = JSON.stringify(data.memberId);
	    				
	    				let output = "";
	    				output += "<button class='btn btn-success' onclick='logIn();'>로그인 하러가기</button>&nbsp;";
	    				output += "<button class='btn btn-primary' onclick='pwdUpdate();'>비밀번호 재설정</button>";
	    				$(".btns").html(output);
	    				
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
			
		} else if (memberName == "") {
			alert("이름을 입력하세요");
			$("#memberName").focus();
		} else if (memberEmail == "") {
			alert("이메일을 입력하세요");
			$("#memberEmail").focus();
		} else if (authCheck == false) {
			alert("이메일 인증을 해주세요");
		} else if (checkCode == false) {
			alert("인증번호가 틀렸습니다");
		}
		
	}
	
	// 로그인 하러가기 버튼
	function logIn() {
		location.href='${contextPath}/member/logIn';
	}
	
	
	// 비밀번호 재설정 버튼
	function pwdUpdate() {
		// 비밀번호재설정 페이지 이동
		location.href='${contextPath}/member/findPwd';
	}
	
	
</script>
<style>

	#outer {
		width: 100%;
		margin: auto;
	}
	
	.mailInputBox {
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
	
	.label {
		padding-top: 20px;
		padding-bottom: 10px;
	}
	
	.authForm {
		width: 457px;
		margin:0 auto;
	}
	
	.alert {
		top: -22px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div id="container">
		<div id="outer">
		<div class="heading">아이디 찾기</div>
		
		<!-- 1차 인증 -->
		<div class="authForm" style="display: none">
			<div class="col-lg">
				<div class="label">이름</div>
				<div class="checkout__input">
					<input type="text" class="inputBox form-control" id="memberName" placeholder="이름을 입력하세요" style="color:black" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<div class="col-lg">
				<div class="label">이메일</div>
				<div class="checkout__input">
					<input type="email" class="inputBox form-control" id="memberEmail" placeholder="이메일을 입력하세요" style="color:black" />
					<div class="valid-feedback">Valid.</div>
    				<div class="invalid-feedback">Please fill out this field.</div>
				</div>
			</div>
			
			<!-- 캡챠자리 -->
			
			<div class="col-lg">
				<div class="label">이메일 인증</div>
				<div class="checkout__input input-group">
					<input type="text" class="mailInputBox form-control" id="authNumber" placeholder="인증번호 입력" disabled="disabled" />
					<span class="input-group-btn">
						<button id="getAuthNumber" class="btn btn-secondary" type="button">인증번호 전송</button>
		      		</span>
				</div>
				<!-- 인증번호 확인 -->
         		<div class="alert alert-success" id="alert-success-email">인증번호가 일치합니다.
         		</div>
         		<div class="alert alert-danger" id="alert-danger-email">인증번호가 일치하지 않습니다.
         		</div>
			</div>
			
			<div class="col-lg">
				<div class="checkout__input d-grid">
					<button id="authCheck" class="btn btn-primary btn-block" onclick="emailAuthCheck();">아이디 찾기</button>
				</div>
			</div>
		</div>
		
		
		<!-- 2차 아이디찾기 결과 -->
		<div class="resultForm" style="display: none">
			<div id="resultName"></div>
			<div>아이디는 <span id="resultId"></span> 입니다.</div>
			<div id="resultJoinDate"></div>
			
	    	<div class="btns">
	    		
			</div>
		</div>
	
		</div>
    </div>
    
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>