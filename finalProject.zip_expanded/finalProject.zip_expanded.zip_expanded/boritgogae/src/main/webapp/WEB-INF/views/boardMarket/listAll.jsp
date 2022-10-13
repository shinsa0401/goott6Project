<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAll</title>
<script>


</script>
</head>
<body>
	
	<jsp:include page="../header.jsp"></jsp:include>
	
	
		<div class="container">
		<form action="/boardMarket/wirte" method="post">
			<div class="container mt-3">
				<h2>장터 게시판</h2>
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
						<tr>
							<td>John</td>
							<td>Doe</td>
							<td>Doe</td>
							<td>Doe</td>
							<td>john@example.com</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</form>


	</div>
	
		
	
	
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>