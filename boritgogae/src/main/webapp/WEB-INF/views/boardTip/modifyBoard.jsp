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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#summernote')
								.summernote(
										{
											height : 300, // 에디터 높이
											minHeight : null, // 최소 높이
											maxHeight : null, // 최대 높이
											focus : false, // 에디터 로딩후 포커스를 맞출지 여부
											lang : "ko-KR", // 한글 설정
											toolbar : [
													// [groupName, [list of button]]
													[ 'fontname',
															[ 'fontname' ] ],
													[ 'fontsize',
															[ 'fontsize' ] ],
													[
															'style',
															[
																	'bold',
																	'italic',
																	'underline',
																	'strikethrough',
																	'clear' ] ],
													[
															'color',
															[ 'forecolor',
																	'color' ] ],
													[ 'table', [ 'table' ] ],
													[
															'para',
															[ 'ul', 'ol',
																	'paragraph' ] ],
													[ 'height', [ 'height' ] ],
													[
															'insert',
															[ 'link',
																	'picture',
																	'video' ] ],
													[
															'view',
															[ 'fullscreen',
																	'codeview',
																	'help' ] ] ],
											fontNames : [ 'Arial',
													'Arial Black',
													'Comic Sans MS',
													'Courier New', '맑은 고딕',
													'궁서', '굴림체', '굴림', '돋움체',
													'바탕체' ],
											fontSizes : [ '8', '9', '10', '11',
													'12', '14', '16', '18',
													'20', '22', '24', '28',
													'30', '36', '50', '72' ],
											callbacks : {
												onImageUpload : function(files) {
													uploadFile(files[0], this);
												},
												onPaste : function(e) {
													let clipboardData = e.originalEvent.clipboardData;
													if (clipboardData
															&& clipboardData.items
															&& clipboardData.items.length) {
														let item = clipboardData.items[0];
														if (item.kind === 'file'
																&& item.type
																		.indexOf('image/') !== -1) {
															e.preventDefault();
														}
													}
												}
											}

										});
					});

	$("#summernote").on("summernote.enter", function(we, e) {
		$(this).summernote("pasteHTML", "<br><br>");
		e.preventDefault();
	});

	function uploadFile(file, editor) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			url : "/boardTip/uploadFile",
			data : data,
			type : "POST",
			contentType : false,
			processData : false,
			async : false,
			success : function(data) {
				let output = "";
				if (data.image) {
					output += "/resources/uploads"
							+ data.savedOriginImageFileName;
				} else {
					output += "/resources/uploads" + data.notImageFileName;
				}
				$(editor).summernote('insertImage', output);
			}
		});
	}

	function modify(data) {
		let content = data.content.value;
		let memberId = data.memberId.value;
		let title = data.title.value;
		let pwd = data.pwd.value;
		let categories = data.categories.value;
		let imgFile = data.imgFile.value;
		imgFile = imgFile.replace(/<(\/?)p>/gi, "\r\n");
		data.imgFile.value = imgFile;
		data.submit();
	}
	
	function delImg() {
		$("#oriImgFile").hide();
	}
</script>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- summernote 설정 -->
	<script
		src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/summernote/css/summernote-lite.css">

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<h4>${board.categories }에관한글</h4>
				<form action="/boardTip/modifyBoard/${board.bno }" method="post">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>글쓴이</p>
										<input type="text" id="memberId" name="memberId"
											value="${board.memberId }" readonly>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>비밀번호</p>
										<input type="text" id="pwd" name="pwd">
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>제목</p>
								<c:set var="name" value="${board.categories}"></c:set>
								<c:choose>
									<c:when test="${ name == '강아지' }">
										<select name="categories" id="categories">
											<option value="강아지" selected>강아지</option>
											<option value="고양이">고양이</option>
										</select>
									</c:when>
									<c:otherwise>
										<select name="categories" id="categories">
											<option value="강아지">강아지</option>
											<option value="고양이" selected>고양이</option>
										</select>
									</c:otherwise>
								</c:choose>

								<input type="text" id="title" name="title"
									placeholder="${board.title }">
							</div>
							<div class="mb-3 mt-3">
								<label for="content" class="form-label">내용:</label>
								<textarea id="content" name="content" rows="10" cols="100"
									placeholder="${board.content }"></textarea>
							</div>

							<!-- 이미지파일 넣어야함 -->
							<div class="mb-3 mt-3">
								<label for="oriImgFile" class="form-label">올린이미지와 파일:</label>
								<div id="oriImgFile"  onclick="delImg()">${board.imgFile}</div>
							</div>
							<div class="row">
								<div class="col-lg-4 col-md-6">
									<textarea id="summernote" name="imgFile"></textarea>
								</div>
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-primary"
						onclick="modify(this.form);">수정</button>
					<button type="reset" class="btn btn-danger">취소</button>
				</form>
			</div>
	</section>

	<jsp:include page="../footer.jsp"></jsp:include>


</body>

</html>