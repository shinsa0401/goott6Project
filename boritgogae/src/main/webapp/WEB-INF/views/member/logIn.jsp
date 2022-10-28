<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>로그인</title>
<script>
	let prevPage = "";
	let notLogInPage = "";

	$(function () {
		
		// 로그인 실패횟수에 상관없이 로그인 페이지로 오기전 URL 세팅
		notLogInPage = document.referrer;
		if (notLogInPage.indexOf("/logIn") == -1) {
			// page라는 이름으로 로그인페이지 오기전 url 세션에 저장
			sessionStorage.setItem("page", notLogInPage);
		}
		
		
		formChange(); // 회원/비회원 폼 변경 메서드
		
		
		// logIn.jsp 페이지 로딩시 회원 로그인 폼 기본호출
		$("input:radio[name=member]:input[value='member']").attr("checked", true);
		$(".memberForm").show();
		
		// 페이지 로딩시 쿼리스트링이 guestFail 이면 게스트폼 호출
		if (getParameter("status") == "guestFail") {
			let memberSelect = $("input[name=member]:checked").val();
			
			$("input:radio[name=member]:input[value='guest']").attr("checked", true);
			$(".memberForm").hide();
			$("#guestFailMsgDiv").show();
			$(".guestForm").show();
		} 
		
		
		logInFail(); // 로그인 실패시 실패 메세지 출력
		guestFail(); // 게스트 주문내역 조회 실패시 실패 메세지 출력
		
	});
	
	
	// 로그인 취소시 이전페이지로 가기
	function logInCancel() {
		let back = sessionStorage.getItem("page");
		
		// page 세션 삭제
		sessionStorage.removeItem("page");
		
		location.href = back;
	}
	
	
	// 회원, 비회원 로그인 폼 변경
	function formChange() {
		$("input[name='member']").change(function() {
			let memberSelect = $("input[name=member]:checked").val();
	        
			if (memberSelect == "guest") {
				$(".memberForm").hide();
				$("#guestFailMsgDiv").hide();
				$(".guestForm").show();
				
			} else if (memberSelect == "member") {
				$(".guestForm").hide();
				$("#memberFailMsgDiv").hide();
				$(".memberForm").show();
			}
		});
	}
	
	
	// 회원 로그인 실패시 실패 메세지 출력
	function logInFail() {
		if (getParameter("status") == "fail") {
			$("#memberFailMsgDiv").show();
		} else {
			$("#memberFailMsgDiv").hide();
		}	
	}
	
	
	// 비회원 주문내역 로그인 실패시 실패 메세지 출력
	function guestFail() {
		if (getParameter("status") == "guestFail") {
			$("#guestFailMsgDiv").show();
		} else {
			$("#guestFailMsgDiv").hide();
		}	
	}
	
	
	//URL의 쿼리스트링에서 param 값을 찾아 반환하는 기능을 하는 함수
	//만약 param값이 쿼리스트링이 없다면 -1을 반환
	function getParameter(param) {
		let returnVal = null; // 리턴값을 저장할 변수
		let url = location.href;
		// console.log(url);
		
		let queryString = url.split("?")[1];
		// console.log(queryString);
		
		let tempArr = (queryString||"").split("&");
		// console.log(tempArr);
		
		for (let i in tempArr) {
		    if (tempArr[i].indexOf(param) != -1) { // tempArr[i]번째에 param이 있다면...
		        returnVal = tempArr[i].split("=")[1];
		        return decodeURIComponent(returnVal);
		    } 
		}
		return -1; // 찾을 매개변수가 없다
	}
	
	
	
</script>
<style>
	.logInArea {
		height: 650px;
	}
	.row {
		justify-content: center;
	}
	#memberSelect {
		margin-top: 30px;
		text-align: center;
	}
	.btns {
		float: right;
		margin-top: 5px;
	}
	.addLink {
		width: 400px;
		margin-top: 120px;
		position: absolute;
		top: 100%;
    	left: 50%;
    	transform: translate(-50%, -100%);
    }
    .failMsg {
    	text-align: left;
    	font-size: 15px;
    }
    #memberFailMsg1 {
    	color: blue;
    }
    #memberFailMsg2 {
    	color: red;
    }
    .guestForm {
    	display: none;
    }
    .guestFailMsg {
    	color: red;
    }
    #guestFind {
    	color: blue;
    }
    #guestFind:hover {
    	color: blue;
    }
    
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	
    <div class="container">
    	<div class="row">
    	    <div class="col-md-offset-3 col-md-6 logInArea">
    	    
    	    	<div class="form-check" id="memberSelect">
    	    		<label class="form-check-label" for="member">
  						<input type="radio" class="form-check-input" class="radioBtn" name="member" value="member">
  						<spna>회원</spna>
  					</label>
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<label class="form-check-label" for="guest">
  						<input type="radio" class="form-check-input" id="guestRadio" class="radioBtn" name="member" value="guest">
  						<spna>비회원</spna>
  					</label>
				</div>
				
    	    	<div class="memberForm" style="display:none">
                <form class="form-horizontal" action="${contextPath }/member/logInPost" method="post">
                    <span class="heading">Log In</span>
                    <div id="memberFailMsgDiv" class="form-group" style="display:none">
                    	<p class="failMsg">죄송합니다. 로그인에 실패했습니다.</p>
                    	<p class="failMsg">
                    		<span id="memberFailMsg1">아이디와 비밀번호</span>를 
                    		<span id="memberFailMsg2">확인</span>하고 다시 로그인해주세요.
                    	</p>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력하세요" autofocus/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="memberPwd"  name="memberPwd" placeholder="비밀번호를 입력하세요" />
                        <i class="fa fa-lock"></i>
                        
                    </div>
                    <div class="form-group">
                        <div class="main-checkbox">
                            <input type="checkbox" id="remember" name="remember">
                            <label for="remember"></label>
                        </div>
                        <div class="text">자동 로그인</div>
                    </div>
                    <div class="form-group">
                    	<div class="btns">
                    		<button type="submit" class="btn btn-login">로그인</button>
	                        <button type="reset" class="btn btn-cancel" onclick="logInCancel();">취소</button>
	                    </div>
                    </div>
                    
                    <div class="form-group">
                    	<div class="addLink">
                    		<a class="find" href="${contextPath }/member/findId">아이디찾기</a> │ 
                    		<a class="find" href="${contextPath }/member/findPwd">비밀번호 재설정</a> │ 
                    		<a class="signUp" href="${contextPath }/member/join">회원가입</a>
                    	</div>
                    </div>
                    
                </form>
                </div>
                
                
                <div class="guestForm" style="display:none">
                <form class="form-horizontal" action="${contextPath }/order/detailGuest" method="post">
                    <span class="heading">Guest</span>
                    <div class="form-group" id="guestFailMsgDiv" style="display:none">
                    	<p class="failMsg">
                    		<span class="guestFailMsg">주문 시 입력한 주문자 명과 휴대폰 번호,</span>
                    	</p>
                    	<p class="failMsg">
                    		<span class="guestFailMsg">주문 비밀번호를 정확히 입력해주세요.</span>
                    	</p>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="주문자명을 입력하세요" autofocus />
                        <i class="fa fa-user"></i>
                    </div>
                    
                    <div class="form-group">
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="휴대폰 번호를 입력하세요" />
                        <i class="fa fa-phone"></i>
                    </div>
                    
                    <div class="form-group">
                        <input type="password" class="form-control" id="guestPwd"  name="guestPwd" placeholder="비밀번호를 입력하세요" />
                        <i class="fa fa-lock"></i>
                        
                    </div>
                    
                    <div class="form-group">
                    	<div class="btns">
                    		<button type="submit" class="btn btn-login">주문내역보기</button>
	                        <button type="reset" class="btn btn-cancel" onclick="location.href='${contextPath}/';">취소</button>
	                    </div>
                    </div>
                    
                    <div class="form-group">
                    	<div class="addLink">
                    		<a id="guestFind" href="#">주문 비밀번호 찾기</a>
                    	</div>
                    </div>
                    
                </form>
                </div>
            </div>
        </div>
    </div>
    

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>