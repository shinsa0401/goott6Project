<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAll</title>
<script>
	
	//글 상세 보기
	function viewContent(no) {
		location.href="/boardMarket/viewContent?no="+no;
	}

</script>
</head>
<body>
	
	<jsp:include page="../header.jsp"></jsp:include>
	
	<h3 style="font: bold; text-align: center; text-decoration: underline;" >장터 게시판</h3>
		<div class="container">
		
			<div class="container mt-3">
				
				<p>개인 장터입니다. 개인 간 금전거래에 유의하세요!</p>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${boardList }">
						<tr onclick="viewContent(${board.no });" >
							<td>${board.no }</td>
							<td>${board.title }</td>
							<td>${board.writer }</td>
							<td>${board.writtenDate }</td>
							<td>${board.readCount }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div claa="button" style="text-align: center; ">
			 <button type="submit" class="site-btn" onclick="location.href='/boardMarket/write';">글쓰기</button>
			
		


	</div>
	
		
	
	
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>