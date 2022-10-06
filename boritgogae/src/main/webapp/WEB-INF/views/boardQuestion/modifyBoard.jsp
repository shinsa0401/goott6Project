<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head> 
<title>글수정</title>
<script>

	function writeCancel(no) {
		location.href='/board/question/view?no=' + no;
	}
</script>
<style>
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h4>글수정하기</h4>
		
		<form action="/board/question/modifySave" method="post">
			<div class="board">
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">번호:</label> 
					<input type="text" class="form-control" id="no" name="no" value="${board.no }" readonly>
				</div>
			
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">작성자:</label> 
					<input type="text" class="form-control" id="writer" name="writer" value="${board.writer }" readonly>
				</div>
				
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">작성일:</label> 
					<div>
						<fmt:formatDate value="${board.writtenDate }" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초" />
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
					<textarea class="form-control" rows="10" id="content" name="content">${board.content }</textarea>
					
					<div class="attachImgFiles">
						<c:forEach var="imgFiles" items="${fileList }">
							<c:if test="${imgFiles.thumbnailFileName != null }">
								<div class="imgFile"><img src="/resources/uploads/${imgFiles.originFileName }"></div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				
				<div class="mb-3 mt-3">
					<label for="pwd" class="form-label">비밀번호:</label> 
					<input type="password" class="form-control" id="pwd" name="pwd">
				</div>
				
			</div>
		
		
		
			<div class="btns" style="text-align: center;">
					<button type="submit" class="btn text-white" style="background-color: #7FAD39;">저장</button>
					<button type="reset" class="btn text-white" style="background-color: #7FAD39;"
						onclick="writeCancel(${board.no});">취소</button>
			</div>
	
		</form>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
