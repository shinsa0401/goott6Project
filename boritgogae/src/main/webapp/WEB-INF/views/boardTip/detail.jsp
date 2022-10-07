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
<script>

$(document).ready(function() {
	viewAllReplies();
});

// 댓글 등록
function addReply() {
	let bno = ${board.bno};
	let content = $("#replyContent").val();
	let memberId = "${board.memberId}";// 로그인기능이 아직 구현되지않아 모든 댓글의 작성자를 글쓴이로 하였음 추후수정
	let url = "/reply/"+bno;
	let sendData = JSON.stringify({bno : bno, content : content, memberId : memberId});
	// console.log(sendData);
	$("#replyContent").val("");
	
	$.ajax({
		url : url,
		data : sendData,
		dataType : "text", 
		type : "post",
		headers : {// ajax post 방식은 dataType 이 json일떄 오류가 뜨네.. text로 바꾸고 headers로 명시
			"content-type" : "application/json", // 송신데이터의 타입이 json임을 명시 이떄 문자열이 json으로
			"X-HTTP-Method-Override" : "POST" // 구버전의 웹브라우저에서 (PUT/DELETE) 방식이 호환이 안되는 버전에서 호환되도록
		},
		success : function(data) {
			viewAllReplies();
		},
		error : function(e) { 
			console.log(e);
		}
	});
	
	
}


// 댓글 조회
function viewAllReplies() { 
	let bno = ${board.bno };
	let url = "/reply/" + bno;
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

// data 파싱
function outputReply(data) {
	
	let output = "";
	//<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
	
	$.each(data, function(i,item){
		let memberId = "item.memberId";
		output += "<div class='d-flex'><div class='ms-3'><div class='fw-bold'>" + memberId + "</div><div class='flex-shrink-0' onclick='modiReply(" + item.rno + "," + item.memberId + ");'>수정</div><div class='flex-shrink-0' onclick='delReply(" + item.rno + ");'>삭제</div>";
		output += item.content + "<div>"+ calc(item.createDate) +"</div></div>";
		output += "<div style='display:none;' id='"+ item.rno +"'>"+ item.memberId +"</div>"
	});
	
	output += "</div>";
	
	$("#replies").html(output);
	
}

// 시간계산
function calc(time) {
	
	let diff = new Date() - time;
	let diffSecond = diff / 1000; 
	if (diffSecond < 60*10) return "방금 전"; 
	let diffMinutes = diffSecond / 60; 
	if (diffMinutes < 60) return Math.floor(diffMinutes) + "분 전";
	return new Date(time).toLocaleString();
}

// 댓글수정
function modiReply(rno,memberId){
	//$("#modiModal").show();
	//$("#modiMem").val($("#"+rno).val());
	let output = "<div class='modal' id='modiModal'><div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title' id='modiMem'>"+ memberId +"</h4><button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>"
	output += "<div class='modal-body'><input type='text' id='modiContent'></div>";
	output += "<div class='modal-footer'>";
	output += "<button type='button' class='btn btn-danger' data-bs-dismiss='modal'>Close</button></div></div></div></div>";
	
	$("#modiDiv").html(output);
	$("#modiModal").show();
	/*
	<!-- The Modal -->
		
		
			

				<!-- Modal Header -->
				
					
					
// 경로 el 				

				<!-- Modal body -->
				

				<!-- Modal footer -->
				
					
	*/
}

function delReply(rno){
	let bno = ${board.bno };
	console.log(bno);
	let url = "/reply/" + bno+"/" + rno;
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
		location.href = "/boardTip/modifyBoard/"+bno;
	}
</script>
</head>

<body>
	<jsp:include page="../head.jsp"></jsp:include>

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<h4>${board.categories }에관한글</h4>
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>글쓴이</p>
									<input type="text" value="${board.memberId}" readonly>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>제목</p>
							<input type="text" value="${board.title}" readonly>
						</div>

						<div class="mb-3 mt-3">
							<label for="content" class="form-label">내용:</label>
							<div id="content" name="content">${board.content}</div>
						</div>

						<!-- 이미지파일 넣어야함 -->
						<div class="row">
							<div class="col-lg-4 col-md-6"></div>
						</div>

						<button type="button" class="btn btn-secondary"
							onclick="modify(${board.bno})">글 수정</button>
						<form action="/boardTip/${board.bno }" method="post">
							<button type="submit" class="btn btn-warning">글 삭제</button>
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
										onclick="addReply();">댓글등록</button>
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