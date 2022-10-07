<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<<<<<< HEAD
<%@ page session="false" %>

<html>
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
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>글전체보기</title>
<script>
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<title>글전체보기</title>
<script>

	function viewBoard(no) {
		location.href = "/board/question/view?no=" + no;
	}
>>>>>>> sth
</script>
<style>
	#boardTitle {
		margin-bottom: 20px;
	}
	
	#btns {
		text-align: right;
	}
	
	#viewAllBoard #viewTable {
		text-align: center;
	}
<<<<<<< HEAD
=======
	
	#headTr {
		background-color: #7FAD39;
		font: 
	}
	
	#titleTr {
		width: 500px;
	}
>>>>>>> sth
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container mt-3">
		<h3 id="boardTitle">질문 게시판</h3>
		
		<div id="viewAllBoard">
			<table id="viewTable" class="table table-hover">
<<<<<<< HEAD
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>좋아요</th>
				</tr>
				<c:forEach var="board" items="${boardLst}">
				<tr>
					<th>${board.no }</th>
					<th>${board.title }</th>
					<th>${board.writer }</th>
					<th>${board.writtenDate }</th>
					<th>${board.readCount }</th>
					<th>${board.likeCount }</th>
=======
				<tr id="headTr" class="text-white">
					<th>번호</th>
					<th id="titleTr">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="board" items="${boardLst}">
				<tr onclick="viewBoard(${board.no});">
					<th>${board.no }</th>
					<th>${board.title }</th>
					<th>${board.writer }</th>
					<th>${board.readCount }</th>
					<th>${board.likeCount }</th>
					<th>${board.writtenDate }</th>
>>>>>>> sth
				</tr>
				</c:forEach>
			</table>
		</div>
			
		
		
		<div id="btns">
			<button type="button" class="btn text-white" style="background-color: #7FAD39;"
<<<<<<< HEAD
				onclick="location.href='/board/write';">새글작성</button>	
=======
				onclick="location.href='/board/question/newWrite';">새글작성</button>	
>>>>>>> sth
			<button type="button" class="btn text-white" style="background-color: #7FAD39;"
				onclick="location.reload();">전체목록</button>
		</div>
		
	</div>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
