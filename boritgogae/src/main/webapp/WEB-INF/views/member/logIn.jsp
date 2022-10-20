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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>로그인 페이지</title>
<script>
	
</script>
<style>
	.logInArea {
		height: 400px;
	}
	.row {
		justify-content: center;
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
                    <div class="form-group">
                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력하세요" />
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="memberPwd"  name="memberPwd" placeholder="비밀번호를 입력하세요" />
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group">
                        <div class="main-checkbox">
                            <input type="checkbox" value="None" id="remember" name="remember">
                            <label for="remember"></label>
                        </div>
                        <span class="text">자동 로그인</span>
                        <button type="submit" class="btn btn-default">로그인</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>