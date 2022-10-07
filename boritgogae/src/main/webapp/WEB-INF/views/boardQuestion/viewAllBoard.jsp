<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	#headTr {
		background-color: #7FAD39;
		font: 
	}
	
	#titleTr {
		width: 500px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container mt-3">
		<h3 id="boardTitle">질문 게시판</h3>
		
		<div id="viewAllBoard">
			<table id="viewTable" class="table table-hover">
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
				</tr>
				</c:forEach>
			</table>
		</div>
			
		
		
		<div id="btns">
			<button type="button" class="btn text-white" style="background-color: #7FAD39;"
				onclick="location.href='/board/question/newWrite';">새글작성</button>	
			<button type="button" class="btn text-white" style="background-color: #7FAD39;"
				onclick="location.reload();">전체목록</button>
		</div>
		
	</div>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
