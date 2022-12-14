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
<title>Ogani | Template</title>

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
<!-- summernote ?????? -->
<script src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/css/summernote-lite.css">

<script>

$(document).ready(function() {
	viewAllReplies();
});

// ?????? ??????
function addReply() {
	let bno = ${board.bno};
	let content = $("#replyContent").val();
	let memberId = "${board.memberId}";// ?????????????????? ?????? ?????????????????? ?????? ????????? ???????????? ???????????? ????????? ????????????
	let url = "/replys/"+bno;
	let sendData = JSON.stringify({bno : bno, content : content, memberId : memberId});
	// console.log(sendData);
	$("#replyContent").val("");
	
	$.ajax({
		url : url,
		data : sendData,
		dataType : "text", 
		type : "post",
		headers : {// ajax post ????????? dataType ??? json?????? ????????? ??????.. text??? ????????? headers??? ??????
			"content-type" : "application/json", // ?????????????????? ????????? json?????? ?????? ?????? ???????????? json??????
			"X-HTTP-Method-Override" : "POST" // ???????????? ????????????????????? (PUT/DELETE) ????????? ????????? ????????? ???????????? ???????????????
		},
		success : function(data) {
			viewAllReplies();
		},
		error : function(e) { 
			console.log(e);
		}
	});
	
	
}


// ?????? ??????
function viewAllReplies() { 
	let bno = ${board.bno };
	let url = "/replys/" + bno;
	$.ajax({
		url : url, 
		dataType : "json", 
		contentType : 'application/json;charset=utf-8',
		type : "get", 
		success : function(data) {
			console.log(data);
			outputReply(data);
			
		},
		error : function(e) { 
			console.log(e);
		}
	});
}

// data ??????
function outputReply(data) {
	
	let output = "";
	//<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
	
	$.each(data, function(i,item){
		let memberId = "item.memberId";
		output += "<div class='d-flex'><div class='ms-3'><div class='fw-bold'>" + memberId + "</div><div class='flex-shrink-0' onclick='modiReply(" + item.rno + "," + item.memberId + ");'>??????</div><div class='flex-shrink-0' onclick='delReply(" + item.rno + ");'>??????</div>";
		output += item.content + "<div>"+ calc(item.createDate) +"</div></div>";
		output += "<div style='display:none;' id='"+ item.rno +"'>"+ item.memberId +"</div>"
	});
	
	output += "</div>";
	
	$("#replies").html(output);
	
}

// ????????????
function calc(time) {
	
	let diff = new Date() - time;
	let diffSecond = diff / 1000; 
	if (diffSecond < 60*10) return "?????? ???"; 
	let diffMinutes = diffSecond / 60; 
	if (diffMinutes < 60) return Math.floor(diffMinutes) + "??? ???";
	return new Date(time).toLocaleString();
}

// ?????? ????????? 
function sendModi(rno) {
	let bno = ${board.bno};
	let content = $("#modiContent").val();
	let memberId = "${board.memberId}";// ?????????????????? ?????? ?????????????????? ?????? ????????? ???????????? ???????????? ????????? ????????????
	let url = "/replys/"+bno+"/"+rno;
	let sendData = JSON.stringify({bno : bno, content : content, memberId : memberId, rno : rno});
	
	$.ajax({
		url : url,
		data : sendData,
		dataType : "text", 
		type : "post",
		headers : {// ajax post ????????? dataType ??? json?????? ????????? ??????.. text??? ????????? headers??? ??????
			"content-type" : "application/json", // ?????????????????? ????????? json?????? ?????? ?????? ???????????? json??????
			"X-HTTP-Method-Override" : "POST" // ???????????? ????????????????????? (PUT/DELETE) ????????? ????????? ????????? ???????????? ???????????????
		},
		success : function(data) {
			viewAllReplies();
		},
		error : function(e) { 
			console.log(e);
		}
	});
}

// ????????????????????????
function modiReply(rno,memberId){
	let output = "<div class='modal' id='modiModal'><div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title' id='modiMem'>"+"</h4><button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>"
	output += "<div class='modal-body'><input type='text' id='modiContent'></div>";
	output += "<div class='modal-footer'>";
	output += "<button type='button' class='btn btn-danger' onclick='sendModi(rno);'>??????</button><button type='button' class='btn btn-danger' data-bs-dismiss='modal'>Close</button></div></div></div></div>";
	
	$("#modiDiv").html(output);
	$("#modiModal").show();
}

function delReply(rno){
	let bno = ${board.bno };
	console.log(bno);
	let url = "/replys/" + bno+"/" + rno;
	$.ajax({
		url : url, 
		dataType : "json", 
		contentType : 'application/json;charset=utf-8',
		type : "get", 
		success : function(data) {
			viewAllReplies();
			
		},
		error : function(e) { 
			console.log(e);
		}
	});
}

	function modify(rno){
		location.href = "/boardTip/modifyBoard/"+rno;
	}
	
	function reply(bno){
		location.href = "/boardTip/replyBoard/"+bno;	
	}
	
</script>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<h4>${board.categories }????????????</h4>
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>?????????</p>
									<input type="text" value="${board.memberId}" readonly>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>??????</p>
							<input type="text" value="${board.title}" readonly>
						</div>

						<div class="mb-3 mt-3">
							<label for="content" class="form-label">??????:</label>
							<div id="content" name="content">${board.content}</div>
						</div>

						<!-- ??????????????? ???????????? -->
						<div class="mb-3 mt-3">
							<label for="imgFile" class="form-label">?????????????????? ??????:</label>
							<div id="imgFile" name="content">${board.imgFile}</div>
						</div>

						<button type="button" class="btn btn-secondary"
							onclick="modify(${board.bno})">??? ??????</button>
						<button type="button" class="btn btn-secondary"
							onclick="reply(${board.bno})">????????????</button>
						<form action="/boardTip/${board.bno }" method="post">
							<button type="submit" class="btn btn-warning">??? ??????</button>
						</form>
					</div>
					<!-- Comments section-->
					<section class="mb-5">
						<div class="card bg-light">
							<div class="card-body">
								<!-- Comment form-->
								<div>
									<textarea class="form-control" rows="3" id="replyContent"></textarea>
									<button type="button" class="btn btn-primary"
										onclick="addReply();">????????????</button>
								</div>
								<!-- Comment with nested comments-->
								<div class="d-flex mb-4">
									<div id="replies"></div>
								</div>
							</div>
						</div>
					</section>
					
					<div id="modiDiv"></div>

				</div>
			</div>
	</section>


	



	<jsp:include page="../footer.jsp"></jsp:include>


</body>

</html>