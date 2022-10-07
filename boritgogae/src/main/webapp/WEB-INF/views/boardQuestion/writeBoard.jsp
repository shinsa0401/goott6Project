<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<<<<<<< HEAD
<head>
<meta charset="UTF-8">
	<meta name="description" content="Ogani Template">
	<meta name="keywords" content="Ogani, unica, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

<!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">

<!-- Js Plugins -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
<title>글쓰기</title>
<script>
=======
<head>  
<title>글쓰기</title>
<script>

	function writeCancel() {
		location.href='/board/question';
	}
>>>>>>> sth
</script>
<style>
</style>
</head>
<body>
<<<<<<< HEAD
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div class="container">
			<h1>글쓰기</h1>
		</div>
=======
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h1>질문 게시판</h1>
		
		<form action="/board/question/write" method="post">
			<div class="board">
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">작성자:</label> 
					<input type="text" class="form-control" id="writer" name="writer" readonly">
				</div>
	
				<div class="mb-3">
					<label for="pwd" class="form-label">비밀번호:</label> 
					<input type="password" class="form-control" id="pwd" name="pwd">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="title" class="form-label">제목:</label>
					<input type="text" class="form-control" id="title" name="title">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="content" class="form-label">내용:</label>
					<textarea class="form-control" rows="10" id="content" name="content"></textarea>
				</div>
	
				<div class="mb-3 mt-3">
					<button type="button" class="btn btn-info" onclick="openArea();">파일 업로드</button>
					<div class="fileDrop">
						<div class="fileContent">이 영역에 업로드 할 파일을 드래그 드롭 하세요</div>
					</div>
					
					<div class="upFileList"></div>
					
				</div>
				
				<div class="btns" style="text-align: center;">
					<button type="submit" class="btn text-white" style="background-color: #7FAD39;">저장</button>
					<button type="reset" class="btn text-white" style="background-color: #7FAD39;"
						onclick="writeCancel();">취소</button>
				</div>
			</div>	
		</form>
		
	</div>
>>>>>>> sth
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
