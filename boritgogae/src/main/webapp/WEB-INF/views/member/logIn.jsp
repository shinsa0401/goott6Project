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
			$("#memberId").focus();
		} else {
			$("#memberFailMsgDiv").hide();
		}	
	}
	
	
	// 비회원 주문내역 로그인 실패시 실패 메세지 출력
	function guestFail() {
		if (getParameter("status") == "guestFail") {
			$("#guestFailMsgDiv").show();
			$("#name").focus();
		} else {
			$("#guestFailMsgDiv").hide();
		}	
	}
	
	// 회원 로그인 유효성 검사
	function isValidMember() {
		let isValid = false;
		let flag = false;
		
		flag = idCheck();
		if (!flag) {
			return false;
		}
		flag = pwdCheck();
		if (!flag) {
			return false;
		}
		
		if (flag) {
            isValid = true;
        }
		
		return isValid;
	}
	
	// 회원 아이디 유효성 검사
	function idCheck() {
		let isValid = false;
        let memberId = $("#memberId").val();
        // let idExp = /^[a-z]+[a-z0-9]{5,11}$/g;
        
        if (memberId == "") {
            $("#hiddenIdMsg").html("아이디는 필수 항목입니다");
            $("#hiddenIdMsg").show();
            $("#memberId").focus();
        } 
        /* else if (!idExp.test(memberId)) {
        	$("#hiddenIdMsg").html("");
			// 아이디를 정규식으로 체크해보기
        	$("#hiddenIdMsg").html("아이디는 6~12자 영문자, 숫자만 입력 가능합니다");
        	$("#memberId").focus();
        }  */
        else {
        	$("#hiddenIdMsg").hide();
			isValid = true;
        }
        return isValid;
	}
	
	// 회원 비밀번호 유효성 검사
	function pwdCheck() {
		let isValid = false;
        let memberPwd = $("#memberPwd").val();
        // let pwdExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
        
        
        if (memberPwd == "") {
            $("#hiddenPwdMsg").html("비밀번호는 필수 항목입니다");
            $("#hiddenPwdMsg").show();
            $("#memberPwd").focus();
        } 
        /* else if (!pwdExp.test(memberPwd)) {
        	$("#hiddenPwdMsg").html("");
        	$("#hiddenPwdMsg").html("비밀번호는 8~20자 영문자, 숫자, 특수문자 조합입니다");
        	$("#memberPwd").focus();
        }  */
        
        else {
        	$("#hiddenPwdMsg").hide();
			isValid = true;
        }
        return isValid;
	}
	
	
	// 비회원 주문내역조회 유효성 검사
	function isValidGuest() {
		let isValid = false;
		let flag = false;
		
		flag = guestNameCheck();
		if (!flag) {
			return false;
		}
		flag = guestPhoneCheck();
		if (!flag) {
			return false;
		}
		flag = guestPwdCheck();
		if (!flag) {
			return false;
		}
		
		if (flag) {
            isValid = true;
        }
		
		return isValid;
	}
	
	// 게스트 주문자명 유효성 검사
	function guestNameCheck() {
		let isValid = false;
        let name = $("#name").val();
        // let nameExp = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+{6}$/;
        
        
        if (name == "") {
            $("#hiddenGuestNameMsg").html("주문자 명은 필수 항목입니다");
            $("#hiddenGuestNameMsg").show();
            $("#name").focus();
        } 
        /* else if (!nameExp.test(name)) {
        	$("#hiddenGuestNameMsg").html("");
        	$("#hiddenGuestNameMsg").html("주문자 명은 특수문자 제외 6자까지 입력 가능합니다");
        	$("#name").focus();
        }  */
        
        else {
        	$("#hiddenGuestNameMsg").hide();
			isValid = true;
        }
        return isValid;
	}
	
	// 게스트 휴대폰 번호 유효성 검사
	function guestPhoneCheck() {
		let isValid = false;
        let phoneNumber = $("#phoneNumber").val();
        let phoneNumberExp = /^[0-9]{11,11}/g;
        
        
        if (phoneNumber == "") {
            $("#hiddenGuestPhoneMsg").html("휴대폰 번호는 필수 항목입니다");
            $("#hiddenGuestPhoneMsg").show();
            $("#memberPwd").focus();
        } else if (!phoneNumberExp.test(phoneNumber)) {
        	$("#hiddenGuestPhoneMsg").html("");
        	$("#hiddenGuestPhoneMsg").html("휴대폰 번호는 '-' 없이 숫자만 11자 입니다");
        	$("#phoneNumber").focus();
        } else {
        	$("#hiddenGuestPhoneMsg").hide();
			isValid = true;
        }
        return isValid;
	}
	
	// 게스트 비밀번호 유효성 검사
	function guestPwdCheck() {
		let isValid = false;
        let guestPwd = $("#guestPwd").val();
        // let guestPwdExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
        
        
        if (guestPwd == "") {
            $("#hiddenGuestPwdMsg").html("비밀번호는 필수 항목입니다");
            $("#hiddenGuestPwdMsg").show();
            $("#guestPwd").focus();
        } 
        /* else if (!guestPwdExp.test(guestPwd)) {
        	$("#hiddenGuestPwdMsg").html("");
        	$("#hiddenGuestPwdMsg").html("비밀번호는 8~16자 영문자, 숫자, 특수문자 조합입니다");
        	$("#guestPwd").focus();
        }  */
        
        else {
        	$("#hiddenGuestPwdMsg").hide();
			isValid = true;
        }
        return isValid;
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
	
	
	// 비호원 주문비밀번호찾기 모달 닫기
	function modalClose() {
		$("#guestPwdModal").hide();
	}
	
	// 비회원 주문비밀번호찾기 모달 열기
	function showGuestPwdModal() {
		$("#guestPwdModal").show();
	}
	
	// 비회원 주문비밀번호 찾기
	function guestPwdAuth() {
		
		let name = $("#orderName").val();
		let guestEmail = $("#guestEmail").val();
		let orderNo = $("#orderNo").val();
		
		let url = "${contextPath}/order/findOrderPwd";
		console.log(url);
		
		let sendData = JSON.stringify({
			name : name, guestEmail : guestEmail, orderNo : orderNo
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		if (name != "" & guestEmail != "" && orderNo != "") {
			
			$.ajax({
		        url: url, // 데이터 송수신될 주소
		        type: "post", // 통신 방식(get, post)
				data: sendData,
		        dataType: "text", // 수신 받을 데이터 타입
		        headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
	    			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
		        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
		            // data : 성공했을 때 전달되는 데이터
		            console.log(data);
	    			if (data == "success") {
	    				alert("임시비밀번호가 전송 되었습니다");
	    				$("#guestPwdModal").hide();
	    			} else if (data == "fail") {
	    				alert("검색된 주문이 없습니다. 주문자명, 이메일, 주문번호를 확인하고 다시 입력해주세요.");
	    			}
	    		}
		    });
			
		} else if (name == "") {
			alert("주문자 명을 입력하세요");
			$("#orderName").focus();
		} else if (guestEmail == "") {
			alert("이메일 주소를 입력하세요");
			$("#guestEmail").focus();
		} else if (orderNo == "") {
			alert("주문 번호를 입력하세요");
			$("#orderNo").focus();
		}
		
	}
	
</script>
<style>
	.logInArea {
		height: 670px;
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
    #guestTransBtn {
    	margin: auto;
    }
    .form-label {
    	margin-top: 15px;
    }
    
    .modal {
    	position: fixed;
    	top:0; left: 0; bottom: 0; right: 0;
    	background: rgba(0, 0, 0, 0.8);
    	z-index: 1000;
    }
    .modal-content {
    	position: absolute;
    	z-index: 1;
    }
    .hiddenMsg {
    	margin-top: 10px;
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
                        <div id="hiddenIdMsg" class="hiddenMsg" style="color: red; text-align: left; margin-left: 20px;"></div>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="memberPwd"  name="memberPwd" placeholder="비밀번호를 입력하세요" />
                        <i class="fa fa-lock"></i>
                        <div id="hiddenPwdMsg" class="hiddenMsg" style="color: red; text-align: left; margin-left: 20px;"></div>
                        
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
                    		<button type="submit" class="btn btn-login" onclick="return isValidMember();">로그인</button>
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
                        <div id="hiddenGuestNameMsg" style="color: red; text-align: left; margin-left: 20px;"></div>
                    </div>
                    
                    <div class="form-group">
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="휴대폰 번호를 '-' 없이 입력하세요" />
                        <i class="fa fa-phone"></i>
                        <div id="hiddenGuestPhoneMsg" style="color: red; text-align: left; margin-left: 20px;"></div>
                    </div>
                    
                    <div class="form-group">
                        <input type="password" class="form-control" id="guestPwd"  name="guestPwd" placeholder="비밀번호를 입력하세요" />
                        <i class="fa fa-lock"></i>
                        <div id="hiddenGuestPwdMsg" style="color: red; text-align: left; margin-left: 20px;"></div>
                    </div>
                    
                    <div class="form-group">
                    	<div class="btns">
                    		<button type="submit" class="btn btn-login" onclick="return isValidGuest();">주문내역보기</button>
	                        <button type="reset" class="btn btn-cancel" onclick="location.href='${contextPath}/';">취소</button>
	                    </div>
                    </div>
                    
                    <div class="form-group">
                    	<div class="addLink">
                    		<a id="guestFind" onclick="showGuestPwdModal();" style="cursor:pointer;">주문 비밀번호 찾기</a>
                    	</div>
                    </div>
                    
                </form>
                </div>
            </div>
        </div>
    </div>
    
    
    
    <!-- 주문비밀번호찾기 모달 -->
    <div class="modal" id="guestPwdModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
		
		    <!-- Modal Header -->
		    <div class="modal-header">
		    	<h4 class="modal-title">주문비밀번호 찾기</h4>
		    	<button type="button" class="btn-close" data-bs-dismiss="modal" onclick="modalClose();"></button>
		    </div>
		
		    <!-- Modal body -->
		    <div class="modal-body mb-3">
		    	
	    		<div>
	    			<label for="orderName" class="form-label">주문자 명</label>
	    			<input type="text" class="form-control" id="orderName" placeholder="주문자 명을 입력하세요">
	    		</div>
	    		
	    		<div>
	    			<label for="guestEmail" class="form-label">이메일 주소</label>
	    			<input type="text" class="form-control" id="guestEmail" placeholder="이메일을 입력하세요">
	    		</div>
	    		
	    		<div>
	    			<label for="orderNo" class="form-label">주문 번호</label>
	    			<input type="text" class="form-control" id="orderNo" placeholder="주문확인 이메일에서 확인 가능">
	    		</div>
		    </div>
		    
		      
		
		    <!-- Modal footer -->
		    <div class="modal-footer">
		    	<div>
		    		주문번호는 주문시 이메일로 발송됩니다. <br />
		    		이메일 주소로 임시 비밀번호를 보내 드립니다.
		    	</div>
		    	
		    	<div id="guestTransBtn">
			    	<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="guestPwdAuth();">전송받기</button>
			    </div>
		    </div>
		
		    </div>
		</div>
	</div>
    

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>