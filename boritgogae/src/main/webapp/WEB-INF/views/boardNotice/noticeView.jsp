<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>공지사항 상세</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script type="text/javascript">
	function showDelModal() {
		$("#deleteModal").show(200);
	}
	function deleteBoard() {
		$("#deleteModal").hide(200);
		$.ajax({
            url : "/board/notice/delete", // 데이터 송수신될 주소 
			data : {"bno" : ${board.bno }}, // 송신할 데이터
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
            	console.log(data);
            	if(data == "success") {
            		$("#delStatusModal").show();
            		$("#delStatus").html("삭제되었습니다.");
           	 	} else if(data == "fail") {
           	 		$("#delStatusModal").show();
            		$("#delStatus").html("삭제 실패했습니다.");
            	}
            }, error : function(request, status, error) {
            	console.log(
						"code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
			}
         });
	}
	

	$(document).ready(function() {
		$(".closeModal").click(function() {
			$("#deleteModal").hide(200);
		});
	});
	
</script>
<style>
#notice {
	margin: 2px;
}
</style>

</head>
<body>

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
					onclick="showDelModal();">글 삭제</button>
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
						data-bs-dismiss="modal" onclick="deleteBoard();">확인</button>
				</div>

			</div>
		</div>
	</div>
	<div class="modal" id="delStatusModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="delStatus"></h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal" onclick="location.href='/board/notice/list'">확인</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>