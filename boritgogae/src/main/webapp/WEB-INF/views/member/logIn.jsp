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
<title>로그인 페이지</title>
<script>
	$(function () {
		logInFail();
	});
	
	
	
	// 로그인 실패시 실패 메세지 출력
	function logInFail() {
		if (getParameter("status") == "fail") {
			$("#failMsgDiv").show();
			
			
		} else {
			$("#failMsgDiv").hide();
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
		height: 550px;
	}
	
	.row {
		justify-content: center;
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
    
    #failMsgDiv {
    	display: none;
    }
    
    .failMsg {
    	text-align: left;
    	font-size: 15px;
    }
    
    #failMsg1 {
    	color: blue;
    }
    #failMsg2 {
    	color: red;
    }
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6 logInArea">
                <form class="form-horizontal" action="${contextPath }/member/logInPost" method="post">
                    <span class="heading">Log In</span>
                    <div id="failMsgDiv" class="form-group">
                    	<p class="failMsg">죄송합니다. 로그인에 실패했습니다.</p>
                    	<p class="failMsg">
                    		<span id="failMsg1">아이디와 비밀번호</span>를 
                    		<span id="failMsg2">확인</span>하고 다시 로그인해주세요.
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
	                        <button type="reset" class="btn btn-cancel" onclick="location.href='${contextPath}/';">취소</button>
	                    </div>
                    </div>
                    
                    <div class="form-group">
                    	<div class="addLink">
                    		<a class="find" href="#">아이디찾기</a> │ 
                    		<a class="find" href="#">비밀번호찾기</a> │ 
                    		<a class="signUp" href="#">회원가입</a>
                    	</div>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
    

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>