<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>공지사항</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/notice/js/commonJS.js"></script>
<script>
	
	let pageNo = getParameter("pageNo");
	
	if(pageNo < 1 || pageNo > ${pagingInfo.totalPage }) {
		location.href="/board/notice/list?pageNo=1";
	}

	function viewBoard(bno) {
		location.href="/board/notice/view?no=" + bno;
	}
	
	$(function() {
		for(let i = ${pagingInfo.startNumOfCurPagingBlock }; i <= ${pagingInfo.endNumOfCurPagingBlock }; i++) {
			if(pageNo == document.getElementById(i).innerHTML) {
				$("#" + i).parent('li').addClass("active");
			}
		}
		
	});
</script>
<style>
tr {
	text-align: center;
}

#paging {
	margin-bottom: 20px;
}

.boardList:hover {
	cursor: pointer;
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
					<tr class="boardList" onclick="viewBoard(${board.bno});">
						<td>${board.bno }</td>
						<td>${board.title }</td>
						<td>${board.nickName }</td>
						<td>${fn:substring(board.writtenDate,0,10) }</td>
						<td>${board.readCount }</td>
						<td>${board.likeCount }</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="boardBtn">
			<c:if test="${sessionScope.logInMember.isAdmin == 'Y' }">
				<button class="btn btn-success"
					onclick="location.href='/board/notice/writeBoard'">글 등록</button>
			</c:if>
		</div>

	</div>


	<div id="paging">
		<ul class="pagination justify-content-center">
			<c:if test="${pagingInfo.startNumOfCurPagingBlock != 1 }">
				<li class="page-item"><a class="page-link"
					href="/board/notice/list?pageNo=1"><<</a></li>
				<li class="page-item"><a class="page-link"
					href="/board/notice/list?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }">이전</a></li>
			</c:if>

			<c:choose>
				<c:when
					test="${pagingInfo.endNumOfCurPagingBlock > pagingInfo.totalPage }">
					<c:forEach var="i" begin="${pagingInfo.startNumOfCurPagingBlock }"
						end="${pagingInfo.totalPage }" step="1">
						<li class="page-item"><a class="page-link" id="${i }"
							href="/board/notice/list?pageNo=${i }">${i }</a></li>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<c:forEach var="i" begin="${pagingInfo.startNumOfCurPagingBlock }"
						end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
						<li class="page-item"><a class="page-link" id="${i }"
							href="/board/notice/list?pageNo=${i }">${i }</a></li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<!--  -->
			<c:if
				test="${pagingInfo.endNumOfCurPagingBlock < pagingInfo.totalPage }">
				<li class="page-item"><a class="page-link"
					href="/board/notice/list?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }">다음</a></li>

				<li class="page-item"><a class="page-link"
					href="/board/notice/list?pageNo=${pagingInfo.totalPage }">>></a></li>
			</c:if>

		</ul>
	</div>



	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
