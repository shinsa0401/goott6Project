<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
<style type="text/css">
.inline {
	display: inline;
	margin: auto;
	color: black;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<div class="container p-5 my-5 bg-success text-white test2">
			<a href="/board/ask"><h3>문의게시판</h3></a>
		</div>
		<div class="row">
			<div class="col-sm-4 ">
				<div style="float: left;">작성자 : ${board.writer }</div>
			</div>
			<div class="col-sm-4 ">
				<div style="margin: auto;">작성시간 : ${board.writtenDate }</div>
			</div>
			<div class="col-sm-4 ">
				<div style="float: right;">조회수 : ${board.readCount }
					&nbsp|&nbsp 추천수 : ${board.likeCount }</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4 ">
				<div style="float: left;">문의분류 : ${askOption }</div>
			</div>
			<div class="col-sm-4 "></div>
			<div class="col-sm-4 ">
				<div style="float: right;">댓글보기</div>
			</div>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>
		<div>
			<h3>${board.title }</h3>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>

		<c:set value="../../../resources/askBoard/uploads" var="path"></c:set>
		<div class="mb-3 mt-3">
			<label for="attachFiles" class="form-label">첨부파일 (클릭 시 원본 다운로드)</label>
			<c:forEach var="file" items="${fileList }">
				<div class="files">
					<a href="${path }${file.originalFile }" download style = "" >${file.originalFile }</a>
				</div>
			</c:forEach>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>
		<div>
			<h5>${board.contents }</h5>
			<div class="attachImgFiles">
				<c:forEach var="imgFiles" items="${fileList }">
					<c:if test="${imgFiles.thumbnailFile != null }">
						<div class="imgFile">
							<img src="${path }${imgFiles.originalFile }" style="max-width: 150px; max-height : 150px";  />
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>