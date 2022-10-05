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
<script src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/css/summernote-lite.css">

<title>공지사항 글 작성</title>
<script>
	
</script>
<style>
</style>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>공지사항 글 작성</h1>

		<div class="checkout__form">
			<h4>Billing Details</h4>
			<form action="/board/notice/register" method="post">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="checkout__input">
							<p>제목</p>
							<input type="text" name="title">
						</div>
						<div class="checkout__input">
							작성자 <input type="text" name="memberId">
						</div>
						<div class="checkout__input">
							<p>내용</p>
							<div id="summernote" name="content">Hello Summernote</div>
						</div>
						
						<div class="checkout__input">
							<p>파일 업로드</p>
							
						</div>

					</div>

				</div>
				
				<button type="submit" class="btn btn-success">등록</button>
			</form>
			

		</div>
	</div>



	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
