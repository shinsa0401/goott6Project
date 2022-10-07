<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>

<!-- 서머노트부분 -->
<script src="${pageContext.request.contextPath}/resources/summernote/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/summernote-lite.css">
<!--  -->

<title>글쓰기</title>
<script>

	$(function () {
		
		// 서머노트
		$('#summernote').summernote({
			height: 300,                 // 에디터 높이
			minHeight: null,             // 최소 높이
			maxHeight: null,             // 최대 높이
			focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			lang: "ko-KR",					// 한글 설정
			placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
			
				
		});
		
		
		
	});
	
	// 서머노트에 text 쓰기
	$('#summernote').summernote('insertText', 써머노트에 쓸 텍스트);
	// 서머노트 쓰기 비활성화
	$('#summernote').summernote('disable');
	// 서머노트 쓰기 활성화
	$('#summernote').summernote('enable');
	// 서머노트 리셋
	$('#summernote').summernote('reset');
	// 마지막으로 한 행동 취소 ( 뒤로가기 )
	$('#summernote').summernote('undo');
	// 앞으로가기
	$('#summernote').summernote('redo');

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
		
		<div>
			<textarea id="summernote" name="editordata"></textarea> 
		</div>
		
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
