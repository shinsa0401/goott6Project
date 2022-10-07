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
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	function modify(bno) {
		location.href = "/boardTip/modifyBoard/" + bno;
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
							<div class="row">
								<div class="col-lg-4 col-md-6"></div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="reset" class="btn btn-danger">취소</button>
				</form>
			</div>
	</section>

	<jsp:include page="../footer.jsp"></jsp:include>


</body>

</html>