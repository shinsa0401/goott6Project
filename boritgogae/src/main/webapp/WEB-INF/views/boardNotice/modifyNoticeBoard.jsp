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

<title>공지사항 글 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<link
		href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script>
	$(document).ready(function() {
		//여기 아래 부분
		$('#summernote').summernote({
			height : 300, // 에디터 높이
			minHeight : null, // 최소 높이
			maxHeight : null, // 최대 높이
			focus : false, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			toolbar: [
				// [groupName, [list of button]]
				['fontname', ['fontname']],
				['fontsize', ['fontsize']],
				['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				['color', ['forecolor','color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			callbacks: {
				onImageUpload : function(files){
					imgUpload(files[0],this);
				}
			}
		});
		console.log('${board.content}');
		$('#summernote').summernote('code', '${board.content}');
	});
	
	function modiBoard(form) {
		let content = form.content.value;
		let memberId = form.memberId.value;
		let title = form.title.value;
		console.log(content, memberId, title);
		
		content = content.replace(/<(\/?)p>/gi,"\r\n");
		form.content.value = content;
		
		form.submit();
	}

</script>
<style>
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
		<h1>공지사항 글 수정</h1>

		<div class="checkout__form">
			<h4>No. ${board.bno }</h4>
			<form action="/board/notice/modify" method="post">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="checkout__input">
							<input type="hidden" name="bno" value="${board.bno }">
						</div>
						<div class="checkout__input">
							<p>제목</p>
							<input type="text" name="title" value="${board.title }">
						</div>
						<div class="checkout__input">
							<p>작성자</p>
							<input type="text" name="memberId" value="${board.memberId }">
						</div>
						<div class="checkout__input">
							<p>내용</p>
							<textarea id="summernote" name="content" id="content"></textarea>
						</div>

					</div>

				</div>

				<p><button type="button" class="btn btn-success" onclick="modiBoard(this.form)">수정</button></p>
			</form>

		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>


</body>
</html>
