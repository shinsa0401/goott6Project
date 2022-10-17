<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>공지사항</title>
<script>
	function viewBoard(bno) {
		location.href="/board/notice/view?no=" + bno;
	}
</script>
<style>
	tr {
		text-align: center;
	}
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
						<td>${fn:substring(board.writtenDate,0,10) }</td>
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
