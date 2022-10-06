<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<link
		href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<script type="text/javascript">
	let rno = 0;

	$(document).ready(function() {
		$('#summernote').summernote({
			height : 100, // 에디터 높이
			minHeight : null, // 최소 높이
			maxHeight : null, // 최대 높이
			focus : false, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			toolbar: [
				['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				['color', ['forecolor','color']],
		    	['insert',['picture']]
			],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			callbacks: {
				onImageUpload : function(files){
					imgUpload(files[0],this);
				}
			}
		});	
		
		getReply();

	});
	
	// 댓글 가져오기
	function getReply() {
		let bno = ${board.bno };
		let url = "/board/notice/replylist/" + bno;
		
		$.ajax({
            url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json", // 수신할 데이터
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
            	console.log(data);
				readReply(data);
            
            }, error : function(e) {
				console.log(e);
			}
         });
	}
	
	function readReply(data) {
		let output = "";
		output += '<ul class="list-group list-group-flush">';
		$.each(data, function(i, item) {
			output += "<li class='list-group-item'>";
			output += "<div>";
			output += "<div class='replyer'>" + item.nickName + "</div>";
			output += "<div class='icons'>";
			//output += "<img src='../resources/img/modify.png' class='icon replyIcon' onclick='modifyReply(" + item.rno+ ")'; />";
			output += "<p class='replyIcon' style='text-decoration: underline;' onclick='delReply(" + item.rno + ")';>";
			output += "삭제<img src='../../resources/img/delete_icon.png' class='icon' /></p>";
			output += "</div>";
			output += "<div class='replyContents' style='margin:2px;'>&nbsp;&nbsp;&nbsp;&nbsp;" + item.content + "</div>";
			output += "<div class='writtenDate' style='font-size:14px; position:relative; top:8px;'>" + calcDate(item.writtenDate) + "</div>";
			output += "</div></li>";
		});
		
		
		output += '<li class="list-group-item"></li>';
		output += '<ul>';
		
		
		
		$("#replyList").html(output);
	}
	
	function calcDate(wd) {
		// 방금전, ㅇㅇ분전, 날짜시간
		let diff = new Date() - wd;
		let diffSecond = diff / 1000; // 초단위 시간차
		if(diffSecond <  60 * 5) return "방금 전";
		let diffMinutes = diffSecond / 60; // 분단위 시간차
		if(diffMinutes < 60) return Math.floor(diffMinutes) + "분 전";
		let diffHours = diffMinutes / 60;
		if(diffHours < 24) return Math.floor(diffHours) + "시간 전";
		return new Date(wd).toLocaleString();
	}
	
	function delReply(deleteFromRno) {
		rno = deleteFromRno;
		$("#deleteReplyModal").show(200);
	}
	
	function deleteReplyBoard() {
		$("#deleteReplyModal").hide(200);
		$.ajax({
            url : "/board/notice/replyDelete", // 데이터 송수신될 주소 
			data : {"rno" : rno}, // 송신할 데이터
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
            	console.log(data);
            	if(data == "success") {
            		$("#delReplyStatusModal").show(200);
            		$("#delReplyStatus").html("삭제되었습니다.");
           	 	}
            }, error : function(request, status, error) {
            	console.log(
						"code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
			}
         });
	}
	
	// 댓글 등록
	function addReply() {
		let bno = ${board.bno };
		let memberId = $("#memberId").val();
		let content = $(".content").val();
		content = content.replace(/<(\/?)p>/gi,"\r\n");
		
		let url = "/board/notice/replyregister";
		let sendData = JSON.stringify({
			"bno" : bno, "memberId" : memberId, "content" : content
		});
		
		console.log(sendData);
		$("#memberId").val("");
		$("#content").val("");
		
		$.ajax({
            url : url, // 데이터 송수신될 주소 
			data : sendData, // 송신할 데이터
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			headers : {
				"content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST" // 구 버전의 웹 브라우저에서 (PUT / DELETE) 방식이 호환이 안되는 버전에서 호환 되도록
			},
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
               console.log(data);
            	if(data == "success") {
            		location.reload();
            	} else if(data == "fail") {
            		
            	}
            
            }, error : function(e) {
				console.log(e);
			}
         });
	}
	
	

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
            		$("#delStatusModal").show(200);
            		$("#delStatus").html("삭제되었습니다.");
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
			$("#deleteReplyModal").hide(200);
		});
	});
	
	function modify() {
		location.href="/board/notice/bnoToModify?bno=" + ${board.bno};
	}
	
</script>
<style>
#notice {
	margin: 2px;
}

#rightStatus {
	float: right;
}

#date {
	font-size: 14px;
}

.icon {
	width: 16px;
}

#title {
	position: relative;
	top: 8px;
}

.justify-content-center {
	margin: 10px;
}

#addReplyBtn {
	margin: 20px;
	float: right;
}

#replys {
	overflow: auto;
}

.replyIcon {
	float: right;
}
</style>

</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- summer note -->

	<script
		src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/summernote/css/summernote-lite.css">


	<div class="container">
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><span id="rightStatus">
					<div id="date">
						<fmt:formatDate value="${board.writtenDate }"
							pattern="yyyy-MM-dd HH:mm" />
					</div> &nbsp; <span id="icons"><img
						src="${pageContext.request.contextPath}/resources/img/view_icon.png"
						class="icon"> ${board.readCount } &nbsp; <img
						src="${pageContext.request.contextPath}/resources/img/like_icon.png"
						class="icon"> ${board.likeCount } </span>
			</span>
				<h3 id="title">
					<b>${board.title }</b>
				</h3></li>
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
					onclick="modify();">글 수정</button>
			</div>
		</div>

		<!-- 댓글 -->
		<div id="replys">
			<div>
				<input type="text" id="memberId" name="memberId">
			</div>
			<!-- 댓글 작성 부분 -->
			<textarea id="summernote" class="content" name="content"></textarea>

			<div>
				<button type="button" class="btn btn-success" id="addReplyBtn"
					style="background-color: #7fad39; color: white; border-color: #7fad39;"
					onclick="addReply();">댓글 등록</button>
			</div>
		</div>
		
		<!-- 댓글 목록 -->
			<div id="replyList"></div>
	</div>



	<jsp:include page="../footer.jsp"></jsp:include>

	<!-- The Modal -->
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">글을 삭제하시겠습니까?</h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger closeModal"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal"  onclick="deleteBoard();">확인</button>
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
						data-bs-dismiss="modal"
						onclick="location.href='/board/notice/list'">확인</button>
				</div>

			</div>
		</div>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="deleteReplyModal">
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
						data-bs-dismiss="modal"  onclick="deleteReplyBoard();">확인</button>
				</div>

			</div>
		</div>
	</div>
	<div class="modal" id="delReplyStatusModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="delReplyStatus"></h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal"
						onclick="location.reload();">확인</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>