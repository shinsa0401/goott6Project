<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">
<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css">

<!-- Js Plugins -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<title>공지사항</title>
<script>
</script>
<style>
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h1>공지.jsp</h1>
		<div id="allBoard">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제 목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>

					</tr>
				</thead>
				<c:forEach var="board" items="${list }">
					<tr onclick="viewBoard(${board.bno});">
						<td>${board.bno }</td>
						<td>${board.title }</td>
						<td>${board.memberId }</td>
						<td>${board.writtenDate }</td>
						<td>${board.readCount }</td>
						<td>${board.likeCount }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div id="boardBtn">
			<button class="btn btn-success" onclick="location.href='/board/notice/writeBoard'">글 등록</button>
		</div>
		
	</div>



	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
