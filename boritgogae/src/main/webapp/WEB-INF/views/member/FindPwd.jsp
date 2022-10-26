<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://www.google.com/recaptcha/api.js?render=6Ld11bIiAAAAAKrv3Cqs8YDoEZFLmjLOVyCZHYo-"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>비밀번호 재설정</title>
<script>

	$(function () {
		
		$(".idCheckForm").show();
		
	});
	
</script>
<style>
	.container div {
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
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
	
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
					<button id="idCheck" class="btn btn-primary btn-block">아이디 확인</button>
				</div>
			</div>
		</div>
		
		
		
		<!-- 2차 인증 -->
		<div class="authForm" style="display: none">
			<div class="authForm">
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
					<button id="authCheck" class="btn btn-primary btn-block">인증번호 확인</button>
				</div>
			</div>
			
		</div>
		
		</div>
	
	
		<!-- 3차 비밀번호 재설정 -->
		<div class="pwdReset" style="display: none">
			<div class="col-lg-6">
				<div class="checkout__input">
					<input type="password" class="inputBox form-control" id="newPwd" placeholder="새 비밀번호를 입력하세요" />
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
				<p>8~15자의 영문 대/소문자</p>
				<p>아이디, 생일, 전화번호 등 개인정보 사용불가</p>
				<div class="checkout__input d-grid">
					<button id="pwdUpdate" class="btn btn-primary btn-block">비밀번호 재설정</button>
				</div>
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