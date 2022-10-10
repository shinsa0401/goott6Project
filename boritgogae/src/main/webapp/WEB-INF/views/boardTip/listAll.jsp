<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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

<script>
	$(function() {
		
	});
	function goDetail(bno){
		
		location.href = "/boardTip/"+bno;
	}
</script>
<title>boritgogae</title>
</head>
<body>
	<jsp:include page="../head.jsp"></jsp:include>
	<div class="container mt-3">
		<div class="hero__text">
			<span>여러분들의 가족을 위한</span>
			<h2>팁 게시판</h2>
			<p>We will solve your problems</p>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>등록일</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${BoardLst}">
					<tr onclick="goDetail(${board.bno});">
						<td>${board.bno }</td>
						<td>[${board.categories}]${board.title }</td>
						<td>${board.memberId }</td>
						<td>${board.createDate }</td>
						<td>${board.readCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="button" class="btn btn-success"
			onclick="location.href='/boardTip/writeBoard';">글등록</button>
	</div>

	<div id="paging">
		<ul class="pagination">
			<c:if test="${param.pageNo > 1 }">
				<li class="page-item"><a class="page-link"
					href="/boardTip?pageNo=${param.pageNo - 1 }">Previous</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${pagingInfo.startNumOfCurPagingBlock}"
				end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
			<c:choose>
				<c:when test="${param.pageNo == i} }">
					<li class="page-item active"><a class="page-link" href="/boardTip?pageNo=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/boardTip?pageNo=${i}">${i}</a></li>
				</c:otherwise>
			</c:choose>
				
			</c:forEach>
			<c:if test="${param.pageNo < pagingInfo.totalPage}">
				<li class="page-item"><a class="page-link" href="/boardTip?pageNo=${param.pageNo + 1 }">Next</a></li>
			</c:if>
		</ul>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>