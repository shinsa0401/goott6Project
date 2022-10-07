<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<<<<<< HEAD
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
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>글상세보기</title>
<script>
	
	// 삭제 모달 열기
	function removeModal() {
		$("#pwdCheckText").hide();
		$("#removeModal").show(100);
		
	}
	
	// 모달 닫기
	function modalClose() {
		$("#removeModal").hide(100);
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
	
	
</script>
<style>
	#pwdCheckText {
		font-size: 20px;
		color: red;
		margin-left: 20px;
	}
>>>>>>> sth
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
<<<<<<< HEAD
		<div class="container">
			<h1></h1>
		</div>
	
	
=======
	<div class="container">
	
		<h4>글상세보기</h4>
		
		<div class="board">
			<div class="mb-3 mt-3">
				<label for="writer" class="form-label">번호:</label> 
				<input type="text" class="form-control" id="no" value="${board.no }" readonly>
			</div>
		
			<div class="mb-3 mt-3">
				<label for="writer" class="form-label">작성자:</label> 
				<input type="text" class="form-control" id="writer" name="writer" value="${board.writer }" readonly>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="writer" class="form-label">작성일:</label> 
				<div>
					<fmt:formatDate value="${board.writtenDate }" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				</div>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="readCount" class="form-label">조회수:</label>
				<span id="readCount">${board.readCount }</span>
				<label for="likeCount" class="form-label">좋아요:</label>
				<span id="likeCount">${board.likeCount }</span> 
			</div>
			
			<div class="mb-3 mt-3">
				<label for="attachFiles" class="form-label">첨부파일:</label>
				<c:forEach var="file" items="${fileList }">
					<c:if test="${file.thumbnailFileName == null }">
						<div class="files"><a href="/resources/uploads/${file.originFileName }">${file.originFileName }</a></div>
					</c:if>
				</c:forEach>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">제목:</label>
				<input type="text" class="form-control" id="title" name="title" value="${board.title }">
			</div>

			<div class="mb-3 mt-3">
				<label for="content" class="form-label">내용:</label>
				<div id="content" name="content">
					${board.content }
				</div>
				
				<div class="attachImgFiles">
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
	         <button type="button" class="btn text-white" style="background-color: #7FAD39;"
	            onclick="showReply();">댓글달기</button>
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
		
		
>>>>>>> sth
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
