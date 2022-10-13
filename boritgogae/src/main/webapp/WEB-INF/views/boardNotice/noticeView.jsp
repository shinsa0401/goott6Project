<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
<script type="text/javascript">

	function deleteBoard() {
		$("#deleteModal").show(200);
	}

	
	
	$(function() {
		$(".closeModal").click(function() {
			$("#deleteModal").hide(200);
		});
	});
	
</script>
<style>
#notice {
	margin: 2px;
}

.btn-group :hover {
	background-color: #aaa;
	border-color: #aaa;
}
</style>
</head>
<body>
	${board }

	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><h4 id="notice">공지사항</h4></li>
			<li class="list-group-item"><h5>${board.title }</h5></li>
			<li class="list-group-item">${board.nickName }</li>
			<li class="list-group-item">${board.content }</li>
		</ul>

		<div class="justify-content-center">
			<div class="btn-group">
				<button type="button" class="btn btn-success"
					style="background-color: #7fad39; color: white; border-color: #7fad39;"
					onclick="location.href='/board/notice/list'">목록으로</button>
				<button type="button" class="btn btn-success"
					style="background-color: #7fad39; color: white; border-color: #7fad39;"
					onclick="deleteBoard();">글 삭제</button>
				<button type="button" class="btn btn-success"
					style="background-color: #7fad39; color: white; border-color: #7fad39;"
					onclick="">글 수정</button>
			</div>
		</div>

		<!-- 댓글 -->


	</div>



	<jsp:include page="../footer.jsp"></jsp:include>

	<!-- The Modal -->
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">댓글을 삭제하시겠습니까?</h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger closeModal" 
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal"
						onclick="location.href='/board/notice/delete'">확인</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>