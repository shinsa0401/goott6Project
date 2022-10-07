<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>  
<title>글쓰기</title>
<script>

	function writeCancel() {
		location.href='/board/question';
	}
</script>
<style>
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h1>질문 게시판</h1>
		
		<form action="/board/question/write" method="post">
			<div class="board">
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">작성자:</label> 
					<input type="text" class="form-control" id="writer" name="writer" readonly">
				</div>
	
				<div class="mb-3">
					<label for="pwd" class="form-label">비밀번호:</label> 
					<input type="password" class="form-control" id="pwd" name="pwd">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="title" class="form-label">제목:</label>
					<input type="text" class="form-control" id="title" name="title">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="content" class="form-label">내용:</label>
					<textarea class="form-control" rows="10" id="content" name="content"></textarea>
				</div>
	
				<div class="mb-3 mt-3">
					<button type="button" class="btn btn-info" onclick="openArea();">파일 업로드</button>
					<div class="fileDrop">
						<div class="fileContent">이 영역에 업로드 할 파일을 드래그 드롭 하세요</div>
					</div>
					
					<div class="upFileList"></div>
					
				</div>
				
				<div class="btns" style="text-align: center;">
					<button type="submit" class="btn text-white" style="background-color: #7FAD39;">저장</button>
					<button type="reset" class="btn text-white" style="background-color: #7FAD39;"
						onclick="writeCancel();">취소</button>
				</div>
			</div>	
		</form>
		
	</div>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
