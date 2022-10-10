<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

	


	


	function detail(bno) {
		location.href ="/boardFree/detail?bno="+bno;
		
	}
</script>



<body>
	<jsp:include page="../header.jsp"></jsp:include>






	<div class="container">
		<h2>게시판 전체 페이지</h2>



		<table class="table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제 목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList }">
					<tr onclick="detail(${board.bno});">
						<td>${board.bno }</td>
						<td>${board.title }</td>
						<td>${board.writer }</td>
						<td>${board.createDate }</td>
						<td>${board.readCount }</td>

					</tr>


				</c:forEach>
			</tbody>
		</table>

	</div>

	<div style="text-align: center;">
		<form action="/boardFree/list">
			<select name="searchType" id="searchType">
				<option value="">-----선택------</option>
				<option value="writer">글쓴이</option>			 
				<option value="title" >제목</option>
				<option value="content">본문</option>
			</select> <input type="text" name="searchWord" id="searchWord" />
			<button type="submit" value="검색">검색</button>
		</form>
	</div>





	<div class="button" style="text-align: center;">
		<button type="submit" class="site-btn"
			onclick="location.href='/boardFree/writer';">글쓰기</button>

	</div>
	<div style="text-align: center;">
		<c:if test="${page.showPrev }">
		
			<a href="<c:url value='list?page=${page.beginPage-2}&pageSize=${page.pageSize }'/>">이전</a>
		</c:if>
		<c:forEach var="i" begin="${page.beginPage }" end="${page.endPage }">
			<a href="<c:url value='list?page=${i}&pageSize=${page.pageSize }'/>">${i}</a>
		</c:forEach>
		<c:if test="${page.showNext }">
			<a href="<c:url value='list?page=${page.endPage+1}&pageSize=${page.pageSize }'/>">다음</a>
		</c:if>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>