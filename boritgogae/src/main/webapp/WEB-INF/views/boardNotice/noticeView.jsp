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
	let sendByRno = 0;

	$(document).ready(function() {
		$('#summernote').summernote({
			height : 100, // 에디터 높이
			minHeight : null, // 최소 높이
			maxHeight : null, // 최대 높이
			focus : false, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			toolbar: [
				['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				['color', ['forecolor','color']]
			],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
		});	
		
		$("#summernote").on("summernote.enter", function(we, e) {
		     $(this).summernote("pasteHTML", "<br><br>");
		     e.preventDefault();
		});
		
		getReply();

	});
	
	// 댓글 가져오기
	function getReply() {
		let bno = ${board.bno };
		let url = "/board/notice/replyList/" + bno;
		
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
		sendByRno = deleteFromRno;
		console.log(sendByRno);
		$("#deleteReplyModal").show(200);
	}
	
	function deleteReplyBoard() {
		$("#deleteReplyModal").hide(200);
		let sendRno = sendByRno;
		console.log(sendRno);
		$.ajax({
            url : "/board/notice/replyDelete", // 데이터 송수신될 주소 
			data : {"rno" : sendRno}, // 송신할 데이터
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
	
	function readReply(data) {
		let output = "";
		output += '<ul class="list-group list-group-flush reply-list-group">';
		$.each(data, function(i, item) {
			output += "<li class='list-group-item'>";
			if(item.step > 0) {
					output += "<div style='position:relative; left :" + 30 * item.step + "px;'>";
			} else {
				output += "<div>";
			}
			output += "<div class='replyer'>" + item.nickName + "</div>";
			if(item.step > 0) {
				output += "<p class='replyIcon' style='position:relative; right :" + 30 * item.step + "px;'>";
			} else {
				output += "<p class='replyIcon'>";
			}
			
			output += "<span id='deleteIcon' style='text-decoration: underline;' onclick='delReply(" + item.rno + ")';>";
			output += "삭제<img src='../../resources/notice/icon/delete_icon.png' class='icon' /></span>&nbsp;&nbsp;&nbsp;";
			output += "<span id='modifyIcon' style='text-decoration: underline;'";
			output += " onclick='modiReply(" + item.rno + ",\"" + item.content.trim() + "\",\"" + item.memberId + "\")';>";
			output += "수정<img src='../../resources/notice/icon/modify_icon.png' class='icon' /></span>&nbsp;&nbsp;&nbsp;";
			output += "<span id='replyRepl' style='text-decoration: underline;' onclick='replyRepl(" + item.rno + ", \"" + item.ref + "\", \"" + item.step + "\", \"" + item.refOrder + "\")';>";
			output += "답글달기</span>";
			output += "</p>";
			output += "<div class='replyContents' style='margin:2px;'>" + item.content + "</div>";
			output += "<div class='writtenDate' style='font-size:14px; position:relative; top:8px;'>" + calcDate(item.writtenDate) + "</div>";
			output += "</div></li><div id='modiRegister" + item.rno + "'></div>";
			
			
		});
		
		output += '</ul>';
		
		
		
		$("#replyList").html(output);
	}
	
	// 댓글의 답글
	function replyRepl(replRno, ref, step, refOrder) {
		
		let output = "";
		
		output += '<input type="text" id="replMemberId" name="memberId">';
		output += "<textarea style='width:70%' rows='5' class='form-control replyReplContent' name='content'></textarea>";
		output += "<div><button type='button' class='btn btn-success' onclick='addReplyrepl(" + replRno + ", \"" + ref + "\", \"" + step + "\", \"" + refOrder + "\")';>등록</button></div>";

		$("#modiRegister" + replRno).html(output);
	}
	
	function addReplyrepl(replRno, ref, step, refOrder) {
		let bno = ${board.bno };
		let rno = replRno;
		let memberId = $("#replMemberId").val();
		let content = $(".replyReplContent").val();
		content = content.replace(/<(\/?)p>/gi,"");
		
		let url = "/board/notice/replyRegister";
		let sendData = JSON.stringify({
			"bno" : bno, "rno" : rno, "memberId" : memberId, "content" : content, "ref" : ref, "step" : step, "refOrder" : refOrder
		});
		
		console.log(sendData);
		
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
	
	// 댓글 등록
	function addReply() {
		let bno = ${board.bno };
		let memberId = $("#memberId").val();
		let content = $(".content").val();
		content = content.replace(/<(\/?)p>/gi,"");
		
		let url = "/board/notice/replyRegister";
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
	
	function modiReply(rno, content, memberId) {
		content = content.replace("<br>", "\r\n");
		let output = "";
		output += "<textarea style='width:70%' rows='5' class='form-control replyContent' name='content'>" + content + "</textarea>";
		output += '<div><button type="button" class="btn btn-success" onclick="modiReplySubmit(' + rno + ',\'' + content.replace("\r\n", "<br>").trim() + '\',\'' + memberId + '\')";>수정</button></div>';

		$("#modiRegister" + rno).html(output);
	}
	
	function modiReplySubmit(rno, content, memberId) {
		console.log(rno, content, memberId);
		content = $(".replycontent").val();
		
		let url = "/board/notice/replyModify";
		let sendData = JSON.stringify({"rno" : rno, "content" : content, "memberId" : memberId});
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
            		$("#delReplyStatusModal").show(200);
            		$("#delReplyStatus").html("수정이 완료되었습니다.");
            		
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
		location.href="/board/notice/modify?bno=" + ${board.bno};
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
	margin-top : 80px;
	overflow: auto;
}

.replyIcon {
	position: relative;
	float: right;
	bottom: 26px;
	
}
#deleteIcon {
	cursor: pointer;
}

#modifyIcon {
	cursor: pointer;
}

#replyRepl {
	cursor: pointer;
}

.replyContents {
	position: relative;
	left:30px;
	max-width:60%;
	white-space:normal;
	word-break: break-all;
	word-wrap: break-word;
}

.reply-list-group {
	max-width:80%;
}

.likeBtn {
	width : 50px;
	margin-bottom: 10px;
	position: absolute;
	left: 50%;

}

.container {
	margin-bottom: 30px;
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
						src="${pageContext.request.contextPath}/resources/notice/icon/view_icon.png"
						class="icon"> ${board.readCount } &nbsp; <img
						src="${pageContext.request.contextPath}/resources/notice/icon/like_icon.png"
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
		<div class="likeBtnDiv">
			<c:choose>
				<c:when test="">
					<button type="button" class="btn btn-success likeBtn"
					style="background-color: #7fad39; color: white; border-color: #7fad39;"
					onclick=""><img src="${pageContext.request.contextPath}/resources/notice/icon/full_like_icon.png"></button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-success likeBtn"
						style="background-color: #7fad39; color: white; border-color: #7fad39;"
						onclick=""><img src="${pageContext.request.contextPath}/resources/notice/icon/empty_like_icon.png"></button>
				</c:otherwise>
			
			</c:choose>
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