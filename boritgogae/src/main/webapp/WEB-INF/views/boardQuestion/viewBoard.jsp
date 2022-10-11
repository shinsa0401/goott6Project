<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>글상세보기</title>
<script>

	$(function () {
		viewAllReply();
		
		
	});
	
	
	// 삭제 모달 열기
	function removeModal() {
		$("#pwdCheckText").hide();
		$("#removeModal").show(100);
	}
	
	// 모달 닫기
	function modalClose() {
		$("#removeModal").hide(100);
		$("#removeReplyModal").hide(100);
	}
	
	// 삭제 모달에서 비밀번호 입력후 삭제
	function removeBoard() {
		
		let no = ${board.no};
		let pwd = $("#pwd").val();
		let url = "/board/question/remove";
		
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
	        dataType: "text", // 수신 받을 데이터 타입
	        data: { "no" : no, "pwd" : pwd },
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            // console.log(data);
	        	if (data == "success") {
	        		$("#pwdCheckText").hide();
	        		location.href="/board/question"; 
	        	} else if (data == "fail") {
	        		$("#pwdCheckText").show();
	        	}
	        	 
	        },
	        error: function (e) {
				console.log(e);
			}
		});
	}
	
	
	
	// 댓글등록
	function addReply() {
		let bno = ${board.no };
		let replyWriter = $("#replyWriter").val();
		let replyContent = $("#replyContent").val();
		let url = "/reply/write";
		let sendData = JSON.stringify({
			bno : bno, replyWriter : replyWriter, replyContent : replyContent
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#replyWriter").val("");
		$("#replyContent").val("");
		
		// REST
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
	        data: sendData,
	        dataType: "text", // 수신 받을 데이터 타입
	        headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
	        			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
	            
	        	if (data == "success") {
	        		viewAllReply();
	        	} else if (data == "fail") {
	        		alert("댓글 등록 실패");
	        	}
	        },
	        error: function (e) {
				console.log(e);
			}
		});

	}
	
	
	// 현재 글의 모든 댓글 출력
	function viewAllReply() {
		let bno = ${board.no}
		let url = "/reply/" + bno;
		
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "get", // 통신 방식(get, post)
	        dataType: "json", // 수신 받을 데이터 타입
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            // console.log(data);
	            outputReply(data); // 댓글 출력
	        },
	        error: function (e) {
				console.log(e);
			}
		});
		
	}
	
	// 댓글 출력하는 메서드
	function outputReply(data) {
		let output = "";
		
		$.each(data, function(i, item) {
			output += "<a class='list-group-item list-group-item-action'>";
			output += "<div>";
			output += "<div class='replyWriter'>" + item.replyWriter + "</div>";
			
			if (data[i].step > 0) {
				output += "<div>";
				output += "<img src='${pageContext.request.contextPath}/resources/img/sth_replyArrow.png' width='30px' />";
				output += "</div>";	
				
				output += "<div>";
				output += "<span id='toggle"+item.rno+"' onclick='showModifyReply(" + item.rno + ")'><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_modify.png'; />수정</span>";
				output += "<span onclick='showRemoveReply(" + item.rno + ")';><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_trash.png' />삭제</span>";
				output += "<span onclick='showReReply(" + item.rno + ")';><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_reply.png' />댓글</span>";
				output += "</div>";
				output += "<div class='replyContent'>" + item.replyContent + "</div>";
				output += "<div class='replyWrittenDate'>" + item.replyWrittenDate + "</div>";
				output += "</div></a>";
			} else {
				output += "<div>";
				output += "<span id='toggle"+item.rno+"' onclick='showModifyReply(" + item.rno + ")'><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_modify.png'; />수정</span>";
				output += "<span onclick='showRemoveReply(" + item.rno + ")';><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_trash.png' />삭제</span>";
				output += "<span onclick='showReReply(" + item.rno + ")';><img class='icons' src='${pageContext.request.contextPath}/resources/img/sth_reply.png' />댓글</span>";
				output += "</div>";
				output += "<div class='replyContent'>" + item.replyContent + "</div>";
				output += "<div class='replyWrittenDate'>" + item.replyWrittenDate + "</div>";
				output += "</div></a>";
				
			}
			
			
			
			// 댓글 수정
			output += "<div class='replyForm' id='replyForm"+item.rno+"'>";
			output += "<a class='list-group-item list-group-item-action'>";
			output += "<div>"
			output += "<input type='text' class='form-control' id='replyWriter"+item.rno+"' value='${sessionScope.loginMember.userId }' />"
			output += "<textarea rows='5' class='form-control' id='replyContent"+item.rno+"'>"+item.replyContent+"</textarea>"
			output += "<button type='button' class='btn btn-info' onclick='modifyReply("+item.rno+");'>작성</button>"
			output += "</div>"
			output += "</a></div>";
			
			// 댓글의 댓글
			output += "<div class='reReplyForm' id='reReplyForm"+item.rno+"'>";
			output += "<a class='list-group-item list-group-item-action'>";
			output += "<div>"
			output += "<input type='text' class='form-control' id='reReplyWriter"+item.rno+"' value='${sessionScope.loginMember.userId }' />"
			output += "<textarea rows='5' class='form-control' id='reReplyContent"+item.rno+"'></textarea>"
			output += "<button type='button' class='btn btn-info' onclick='reReply("+item.rno+");'>작성</button>"
			output += "</div>"
			output += "</a></div>";
		});
		
		output += "</div>";
		
		$("#replies").html(output);
	}
	
	// 댓글 수정하기위한 댓글폼 출력
	function showModifyReply(no) {
		let rno = no;
		
		console.log(rno);
		console.log($("#replyForm" + rno));
		
		if ($("#replyForm" + rno).css("display") == "none") {
			$("#replyForm" + rno).show();
		} else {
			$("#replyForm" + rno).hide();
		}
	}
	
	// 댓글 수정
	function modifyReply(no) {
		
		let rno = no;
		let replyWriter = $("#replyWriter" + rno).val();
		let replyContent = $("#replyContent" + rno).val();
		let url = "/reply/modify";
		let sendData = JSON.stringify({
			rno : rno, replyWriter : replyWriter, replyContent : replyContent
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#replyWriter").val("");
		$("#replyContent").val("");
		
		// REST
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
	        data: sendData,
	        dataType: "text", // 수신 받을 데이터 타입
	        headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
	        			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
	            
	        	if (data == "success") {
	        		viewAllReply();
	        	} else if (data == "fail") {
	        		alert("댓글 수정 실패");
	        	}
	        },
	        error: function (e) {
				console.log(e);
			}
		});
	}
	
	
	// 댓글 삭제 모달
	function showRemoveReply(no) {
		let rno = no;
		console.log(rno)
		$("#removeReplyModal").show(100);
		$("#replyNo").val(rno);
		
	}
	
	
	//댓글 삭제
	function removeReply(rno) {
		let bno = ${board.no}
		console.log(rno);
		let sendData = JSON.stringify({
			rno : rno, bno : bno
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		let url = "/reply/remove";
		
		// REST
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
	        data: sendData,
	        dataType: "text", // 수신 받을 데이터 타입
	     	headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
	        			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
	            
	        	if (data == "success") {
	        		$("#removeReplyModal").hide(100);
	        		viewAllReply();
	        	} else if (data == "fail") {
	        		alert("댓글 삭제 실패");
	        	}
	        },
	        error: function (e) {
				console.log(e);
			}
		});
	}
	
	// 댓글의 댓글을 작성하는 폼 출력
	function showReReply(no) {
		let rno = no;
		
		console.log(rno);
		console.log($("#reReplyForm" + rno));
		
		if ($("#reReplyForm" + rno).css("display") == "none") {
			$("#reReplyForm" + rno).show();
		} else {
			$("#reReplyForm" + rno).hide();
		}
	}
	
	// 댓글의 댓글 작성
	function reReply(no) {
		let rno = no;
		let bno = ${board.no };
		let replyWriter = $("#reReplyWriter"+rno).val();
		let replyContent = $("#reReplyContent"+rno).val();
		
		let url = "/reply/reReply";
		let sendData = JSON.stringify({
			rno : rno, bno : bno, replyWriter : replyWriter, replyContent : replyContent
		}); // JSON문자 형식(JSON문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#replyWriter").val("");
		$("#replyContent").val("");
		
		// REST
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
	        data: sendData,
	        dataType: "text", // 수신 받을 데이터 타입
	        headers : { "content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
	        			"X-HTTP-Method-Override" : "POST" }, // 구 버전의 웹 브라우저에서 (PUT/ DELETE)방식이 호환이 안되는 버전에서 호환 되도록
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
	            
	        	if (data == "success") {
	        		viewAllReply(data);
	        	} else if (data == "fail") {
	        		alert("댓글 등록 실패");
	        	}
	        },
	        error: function (e) {
				console.log(e);
			}
		});

		
	}
	
	
	
	
</script>
<style>
	.icons {
		padding: 3px;
		width: 30px;
		
	}
	
	#pwdCheckText {
		font-size: 20px;
		color: red;
		margin-left: 20px;
	}
	
	#btns {
		text-align: right;
		margin-right: 10px;
	}
	
	.body {
		display: table-cell;
		padding: 11px;
		vertical-align: middle;
	}
	
	div .no {
		display: none;
	}
	
	.line1 {
		height: 60px;
		border-top: 1px solid;
		border-bottom: 1px solid;
		background-color: #FCFCFC;
		
	}
	
	
	#title {
		width: 1000px;
		font-size: 25px;
	}
	
	#writtenDate {
		width: 170px;
		font-size: 15px;
	}
	
	#writer {
		float: left;
		width: 600px;
		font-size: 20px;
	}
	
	#count {
		
		width: 270px;
		font-size: 20px;
	}
	
	#content {
		
		width: 100%;
	}
	
	
	#replyDiv {
		
		
	}
	
	#replyContent {
		
	}
	
	.replyForm {
		display: none;
	}
	
	.reReplyForm {
		display: none;
	}
=======
<%@ page session="false" %>

<html>
<head>
<meta charset="UTF-8">
	<meta name="description" content="Ogani Template">
	<meta name="keywords" content="Ogani, unica, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">

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
    
<title>글상세보기</title>
<script>
</script>
<style>
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
<<<<<<< HEAD
	<div class="container">
		
		<div class="board">
			<div class="mb-3 mt-3 no">
				${board.no }
			</div>
			
			<div class="mb-3 mt-3 line1">
				<div id="title" class="body">${board.title }</div>
				<div id="writtenDate" class="body">
					<fmt:formatDate value="${board.writtenDate }" 
						pattern="yyyy-MM-dd HH:mm" />
				</div>
			</div>
			
			<div class="mb-3 mt-3 line2">
				<div id="writer" class="body">작성자 : ${board.writer }</div>
				<div id="count">
					<span id="readCount" class="body">조회수 ${board.readCount }</span>
					<span id="likeCount" class="body ">추천 ${board.likeCount }</span>
					<span id="replyCount" class="body ">댓글 ${board.replyCount }</span>
				</div>
				
			</div>
			
			<div class="mb-3 mt-3 line3">
				<span id="content" class="body">${board.content }</span>
			</div>
			
			
			
			<div class="mb-3 mt-3 line4">
				<label for="attachFiles" class="form-label">첨부파일:</label>
				<c:forEach var="file" items="${fileList }">
					<c:if test="${file.thumbnailFileName == null }">
						<div class="files"><a href="/resources/uploads/${file.originFileName }">${file.originFileName }</a></div>
					</c:if>
				</c:forEach>
			
			
				<div class="attachImgFiles line4">
					<c:forEach var="imgFiles" items="${fileList }">
						<c:if test="${imgFiles.thumbnailFileName != null }">
							<div class="imgFile"><img src="/resources/uploads/${imgFiles.originFileName }"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	
	
		<div id="btns">
	         <button type="button" class="btn text-white" style="background-color: #7FAD39;"
	            onclick="location.href='/board/question/modify?no=${board.no}';">수정</button>
	         <button type="button" class="btn text-white" style="background-color: #7FAD39;"
	            onclick="removeModal();">삭제</button>
	         <button type="button" class="btn text-white" style="background-color: #7FAD39;"
	            onclick="location.href='/board/question';">전체목록</button>
	    </div>
		
		<div id="replies">
		
		</div>
	
		<!-- 댓글 -->
		<div id="replyDiv">
			<br />
			<label for="replyWrite" class="form-label">댓글쓰기</label>
			<input type="text" class="form-control" id="replyWriter" value="${sessionScope.loginMember.userId }" />
			<textarea rows="5" class="form-control" id="replyContent"></textarea>
			<button type="button" class="btn btn-primary"
	            onclick="addReply();">댓글 등록</button>
		</div>
	</div>
	
		<!-- The Modal -->
		<div class="modal" id="removeModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">글을 삭제하시겠습니까?</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" onclick="modalClose();"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body mb-3">
		      	<input type="password" class="form-control" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요...">
		      </div>
		      <div id="pwdCheckText">
		      	비밀번호를 다시 확인해주세요!
		      </div>
		      
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="removeBoard();">삭제</button>
		        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="modalClose();">취소</button>
		      </div>
		
		    </div>
		  </div>
		</div>
		
		
		
		<!-- The Modal 댓글 -->
		<div class="modal" id="removeReplyModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">댓글을 삭제하시겠습니까?</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" onclick="modalClose();"></button>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<input type="hidden"  />
		      	<button type="button" id="replyNo" class="btn btn-primary" data-bs-dismiss="modal" onclick="removeReply(this.value);">삭제</button>
		        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="modalClose();">취소</button>
		      </div>
		
		    </div>
		  </div>
		</div>
		
		
=======
		<div class="container">
			<h1></h1>
		</div>
	
	
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
