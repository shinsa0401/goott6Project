<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의게시판</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		viewAskReply();
	});
	
	// 댓글 등록하는 메서드
	function addAskReply() {
		let askBno = ${board.askBno}
		let memberId = $("#memberId").val();
		let contents = $("#replyContent").val();
		let url = "/reply/ask"
		let sendData = JSON.stringify({
			askBno : askBno, memberId : memberId, contents : contents
		}); // json 문자 형식(json 문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#replyContent").val("");
		
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
            		viewAskReply();
            	} else if(data == "fail") {
            		alert("댓글 등록 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });		
	}
	
	// 현재 글의 모든 댓글을 얻어오는 메서드
	function viewAskReply(){
		let bno = ${board.askBno };
		let url = "/reply/ask/" + bno;
		
		$.ajax({
            url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json", // 수신할 데이터
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
            	console.log(data);
				outputReplies(data);
            }, error : function(e) {
				console.log(e);
			}
         });
	}
	
	function outputReplies(data) {
		let output = "<ul class='list-group list-group-flush'>";
		$.each(data, function(i, item) {
			output += "<li class='list-group-item'>";
			output += "<div>";
			output += "<div class='row'>"; // 1
			output += "<div class='col'>"; // 3
			output += "<div class='replyer'>"

			output += item.memberId;
			output +=  "</div>";			
			output += "</div>"; // 3
			output += "<div class='col'></div>";
			output += "<div class='col'></div>";
			output += "<div class='col'>"; // 2
			output += "<div class='writtenDate'>" + calcDate(item.writtenTime) + "</div>";
			output += "</div>"; // 2
			output += "</div>"; // 1
			
			output += "<div class='row'>"; // 1
			output += "<div class='col'>"; // 3
			output += "<div class='replyContents'>";
			for(i=0; i<item.step; i++){
				output += "<img src='../../../resources/img/ask_reply.png' style = 'max-width : 24px';/>";
			}
			output += item.contents;	
			output += "</div>";		
			output += "</div>"; // 3
			output += "<div class='col'></div>";
			output += "<div class='col'></div>";
			output += "<div class='col'>"; // 2
			if(item.isDelete == 'N'){
				output += "<span onclick='modifyReplyModalOpen("+ item.askRno +");'>수정&nbsp&nbsp</span>";
				output += "<span onclick='deleteReplyModalOpen("+ item.askRno +");'>삭제&nbsp&nbsp</span>";
				output += "<span onclick='nestedReplyModalOpen(" +item.ref  +","+item.refOrder  +","+item.step + ");'>답글&nbsp&nbsp</span>";
			}
			
			output += "</div>"; // 2
			output += "</div>"; // 1
			
			output += "</div></li>";
		});
		
		output += "</ul>";
		
		$("#replies").html(output);
	}	
	
	// 댓글을 작성한 날짜를 문자열로 반환
	function calcDate(wd) {
		// 방금전, ㅇㅇ분전, 날짜시간
		let diff = new Date() - wd;
		let diffSecond = diff / 1000; // 초단위 시간차
		if(diffSecond <  60 * 5) return "5분 이내";
		let diffMinutes = diffSecond / 60; // 분단위 시간차
		if(diffMinutes < 60) return Math.floor(diffMinutes) + "분 전";
		return new Date(wd).toLocaleString();
	}
	
	function modifyReplyModalOpen(rno) {
		$("#modifyRno").val(rno);
		$("#modifyReplyModal").show();	
	}
	
	function modifyReplyModalClose() {
		$("#modifyReplyModal").hide();
		$("#modifyReply").val("");
	}
	function nestedReplyModalOpen(ref, refOrder, step) {
		$("#targetRef").val(ref);
		$("#targetRefOrder").val(refOrder);
		$("#targetStep").val(step);
		$("#nestedReplyModal").show();		
	}
	
	function nestedReplyModalClose() {
		$("#nestedReplyModal").hide();
		$("#nestedReply").val("");
	}

	function deleteReplyModalOpen(rno) {
		$("#deleteRno").val(rno);
		$("#deleteReplyModal").show();	
	}
	
	function deleteReplyModalClose() {
		$("#deleteReplyModal").hide();
	}
	// 댓글 수정
	function modifyReply() {
		let rno = $("#modifyRno").val();
		let contents = $("#modifyReply").val();
		$("#modifyReplyModal").hide();
		let url = "/reply/ask/modify"
		let sendData = JSON.stringify({
			askRno : rno, contents : contents
		}); // json 문자 형식(json 문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#modifyReply").val("");
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
            		viewAskReply();
            	} else if(data == "fail") {
            		alert("댓글 수정 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });	
	}
	
	// 대댓글 기능
	function nestedReply() {
		let askBno = ${board.askBno}
		let memberId = "admin";
		let contents = $("#nestedReply").val();
		let ref = $("#targetRef").val();
		let refOrder = $("#targetRefOrder").val();
		let step = $("#targetStep").val();

		$("#nestedReplyModal").hide();
		
		
		let url = "/reply/ask/nested"
		let sendData = JSON.stringify({
			askBno : askBno, memberId : memberId, contents : contents,
			ref : ref, refOrder : refOrder, step : step
		}); // json 문자 형식(json 문자열)으로 바꿔줌
		
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
            		viewAskReply();
            		$("#nestedReply").val("");
            	} else if(data == "fail") {
            		alert("답글 등록 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });		
	}
	

	// 댓글 삭제 기능
	function deleteReply() {
		let askRno = $("#deleteRno").val();
		$("#deleteReplyModal").hide();
		let url = "/reply/ask/delete"
		let sendData = JSON.stringify({
			askRno : askRno
		}); // json 문자 형식(json 문자열)으로 바꿔줌
		
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
            		viewAskReply();
            	} else if(data == "fail") {
            		alert("답글 삭제 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });		
	}
	
	


	// 좋아요 상태 바꾸기
	function likeStatusChange() {
		//let likeStatus = $("#likeStatus").val();	
		let bno = ${board.askBno};
		let url = "/board/ask/likeStatusChange"
		let sendData = JSON.stringify({
			askBno : bno
		}); // json 문자 형식(json 문자열)으로 바꿔줌
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
            	if(data == "cancelSuccess") {
            		changeLikeStatus("cancel");
            	} else if(data == "registerSuccess") {
            		changeLikeStatus("register");
            	}
            	else {
            		alert("좋아요 상태전환 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });		
	}
	
	// 좋아요 상태 바꾸기
	function changeLikeStatus(data){
		console.log(data);
		if(data=="register"){
			console.log("등록");
			$("#likeBox").html("<img src='../../../resources/img/ask_like_2.png' />	");
		}else{
			console.log("해제");
			$("#likeBox").html("<img src='../../../resources/img/ask_like_1.png' />	");
		}
	}
</script>
<style type="text/css">
.inline {
	
}
</style>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<div class="container p-5 my-5 text-white"
			style="background-color: #7FAD39;">
			<a href="/board/ask/list"><h3>문의게시판</h3></a>
		</div>
		<div class="row">
			<div class="col-sm-4 ">
				<div style="float: left;">작성자 : ${board.writer }</div>
			</div>
			<div class="col-sm-4 ">
				<div style="margin: auto;">작성시간 : ${board.writtenDate }</div>
			</div>
			<div class="col-sm-4 ">
				<div style="float: right;">조회수 : ${readCount } &nbsp|&nbsp 추천수
					: ${board.likeCount }</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4 ">
				<div style="float: left;">문의분류 : ${askOption }</div>
			</div>
			<div class="col-sm-4 ">
				<c:choose>
					<c:when test="${not empty board.modifyDate }">
						<div style="margin: auto;">수정시간 : ${board.modifyDate }</div>
					</c:when>
				</c:choose>

			</div>
			<div class="col-sm-4 ">
				<div style="float: right;">댓글보기</div>
			</div>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>
		<div>
			<h3
				style="diplay: block; width: 800px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${board.title }</h3>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>

		<c:set value="../../../resources/askBoard/uploads" var="path"></c:set>
		<div class="mb-3 mt-3">
			<c:if test="${not empty fileList }">
				<label for="attachFiles" class="form-label">첨부파일 (클릭 시 원본
					다운로드)</label>
			</c:if>
			<c:forEach var="file" items="${fileList }">
				<div class="files">
					<a href="${path }${file.originalFile }" download style="">${file.originalFile }</a>
				</div>
			</c:forEach>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>
		<div>
			<h5 id="contentss">${board.contents }</h5>
			<div class="attachImgFiles">
				<c:forEach var="imgFiles" items="${fileList }">
					<c:if test="${imgFiles.thumbnailFile != null }">
						<div class="imgFile">
							<img src="${path }${imgFiles.originalFile }"
								style="max-width: 150px; max-height: 150px" ;  />
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>

		<!-- 좋아요를 구현해보자 -->
		<!-- 특정 아이피가 이 글을 추천했는지 확인해야 함 -->
		<div style="width: 100%; height: 100%; text-align: center"
			onclick="likeStatusChange();" id="likeBox">
			<c:choose>
				<c:when test="${likeCheck == 1}">
					<img src="../../../resources/img/ask_like_2.png" />
				</c:when>
				<c:otherwise>
					<img src="../../../resources/img/ask_like_1.png" />
				</c:otherwise>
			</c:choose>
		</div>


		<!-- 글 수정삭제 등 -->
		<div class="row">
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">
				<div class="btns">
					<button type="button" class="btn btn-primary"
						onclick="location.href='/board/ask/modify?no=${board.askBno}';">글
						수정</button>
					<button type="button" class="btn btn-warning"
						onclick="location.href='/board/ask/remove?no=${board.askBno}';">
						글삭제</button>
					<button type="button" class="btn btn-warning"
						onclick="location.href='/board/ask/answer?no=${board.askBno}';">
						답글달기</button>
					<button type="button" class="btn btn-info"
						onclick="location.href='/board/ask/list';">목록으로</button>
				</div>
			</div>
		</div>


	</div>

	<!-- 여기서부터 리플관련 -->
	<div class="container">
		<div style="height: 100px"></div>
		<label for="comment" style="font-size: 20pt;">Reply</label>

		<div id="replies"></div>


		<div>
			<table class="table table-hover table-danger">
				<c:forEach var="board" items="${askReplyList }">
					<!-- 리플 넣는 장소 -->
				</c:forEach>
			</table>
		</div>


		<div style="font-size: 0; display: flex;">
			<button type="button" class="btn"
				style="vertical-align: top; height: 100px; width: 100px"
				id="memberId" value="test" disabled>작성자</button>
			<textarea class="form-control" rows="3" id="replyContent" name="text"
				style="flex-grow: 1; display: inline-block; vertical-align: top; height: 100px"></textarea>
			<button type="button" class="btn btn-info float-right"
				id="addAskReplyBtn" onclick="addAskReply();"
				style="vertical-align: top; height: 100px; width: 100px">등록</button>
		</div>
	</div>
	<!-- 수정 모달 -->
	<div class="modal" id="modifyReplyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">댓글 수정</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<textarea rows="5" class="form-control" id="modifyReply"></textarea>
					<input type="hidden" id="modifyRno">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						onclick="modifyReply();">수정하기</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="modifyReplyModalClose();">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 넘어갈 값. askBno, memberId, contents, askRno  -->
	<!-- 답글 모달 -->
	<div class="modal" id="nestedReplyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">답글</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<textarea rows="5" class="form-control" id="nestedReply"></textarea>
					<input type="hidden" id="targetRef"> <input type="hidden"
						id="targetRefOrder"> <input type="hidden" id="targetStep">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						onclick="nestedReply();">답글 달기</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="nestedReplyModalClose();">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 넘어갈 값. askBno  -->
	<!-- 댓글 삭제 모달 -->
	<div class="modal" id="deleteReplyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">삭제하시겠습니까?</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<h5 rows="2">삭제한 댓글은 복원할 수 없습니다</h5>
					<input type="hidden" id="deleteRno">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						onclick="deleteReply();">댓글 삭제</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="deleteReplyModalClose();">Close</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>