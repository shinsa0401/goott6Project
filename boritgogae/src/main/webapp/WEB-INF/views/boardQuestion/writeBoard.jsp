<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<!-- 서머노트부분 -->
<script src="${pageContext.request.contextPath}/resources/summernote/summernote-lite2.js"></script>
<script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/summernote-lite.css">
<!--  -->

<title>글쓰기</title>
<script>

	// 이 페이지에서 선언한 제이쿼리를 사용하겠다
	let newJquery = $.noConflict(true);

	// 이 페이지에 선언된 제이쿼리로 접근  == $(function() {});
	newJquery(function() {
		
		// 서머노트
		newJquery('#summernote').summernote({
			height: 300,                 // 에디터 높이
			minHeight: null,             // 최소 높이
			maxHeight: null,             // 최대 높이
			focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			lang: "ko-KR",					// 한글 설정
			placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
			
			toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor', 'color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['link','picture','video']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
			
				
		});
		
		
		
		// on() : 동적으로 생성된 태그에 대하여 이벤트 등록, 여러개의 이벤트를 한꺼번에 태그에 등록할 때 사용
		newJquery(".fileDrop").on("dropenter dragover", function(event) {
			event.preventDefault(); // 이벤트 무효화
		});
		
		newJquery(".fileDrop").on("drop", function(evt) {
			evt.preventDefault(); // 이벤트가 전파되어 드롭된 파일이 웹브라우저에서 열리는 것을 방지
			
			let files = evt.originalEvent.dataTransfer.files;
			console.log(files);
			
			let formData = new FormData(); // form 태그 객체 (form태그와 똑같다)
			formData.append("upFiles", files[0]); // form객체에 파일 첨부
			
			let url = "/board/question/uploadFile";
			
			newJquery.ajax({
		         url : url, // 데이터 송수신될 주소 
		         type : "post", // 통신 방식(get, post)
				 data : formData, // 전송할 데이터
				 dataType : "json", // 수신받을 데이터 타입
		         processData : false, // 전송하는 데이터를 텍스트 변환하지 않는다
		         contentType : false, // 기본값(application/x-www-form-urlencoded)을 사용하지 않는다.
		         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
		            console.log(data);
		            
		         	showFileList(data);
		         }
		    });
			
		});
		
		
		
	});
	
	// 작성중인 글 취소
	function writeCancel() {
		let url = "/board/question/writeCancel";
		
		newJquery.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
			 dataType : "text", // 수신받을 데이터 타입
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	            console.log(data);
	            
	         	if (data == "success") {
	         		// (첨부된 이미지리스트 삭제후 전체 글목록 이동)
	         		location.href = "/board/question?pageNo=1";
	         	}
	         	
	         }
	    });
		
	}
	
	// 업로드공간에 올라간 파일 지움
	function deleteFile(obj) {
		let deleteFileName = newJquery(obj).attr("id");
		let url = "/board/question/deleteFile";
		
		newJquery.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
			 data : {"deleteFileName": deleteFileName }, // 전송할 데이터
			 dataType : "text", // 수신받을 데이터 타입
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	            console.log(data);
	            
	         	if (data == "success") {
	         		alert("삭제");
	         		newJquery(obj).prev().remove(); // 파일 화면에서 지움
	         		newJquery(obj).remove(); // 삭제이미지 삭제		
	         	}
	         	
	         }
	    });
	}
	
	// 업로드 한 파일 출력
	function showFileList(data) {
		let output = "";
		
		if (data.image) { // 이미지 파일이면
			output += "<img src='/resources/uploads" + data.thumbnailFileName + "' />";
		} else {
			output += "<a href='/resources/uploads" +data.notImageFileName + "'>" + data.notImageFileName + "</a>";
		}
		
		output += "<img src='/resources/img/sth_trash.png' width='50px' id='" + data.savedOriginImageFileName + "' class='minusBtn' onclick='deleteFile(this);' />";
		newJquery(".upFileList").append(output);
	}
	
	
	// 첨부파일 공간 열고 닫기
	function openArea() {
		if ($("#fileDrop").css("display") == "block") {
			$("#fileDrop").hide();
		} else {
			$("#fileDrop").show();
		}
	}
</script>
<style>
	.fileDrop {
		width: 100%;
		height: 300px;
		border: 1px dotted blue;
		display: none;
	}
	
	.fileDrop .fileContent {
		padding: 20px;
		text-align: center;
		margin: 10px auto;
	}
	
	.upFileList {
		padding: 5px;
		float: left;
	}
	
	.upFileList img, .upFileList a {
		margin-left: 10px;
		position: relative;
	}
	
	.btns {
		clear: left;
		margin-top: 15px;
	}
	
	.minusBtn {
		position: absolute;
		right: 10px;
		bottom: 50px;
	}

	
	#save {
		text-align: right;	
	}
	
	#cancel {
		text-align: left;
	}
	
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h3>질문 게시판</h3>
		
		<form action="/board/question/writeSave" method="post">
			<div class="board">
				<div class="mb-3 mt-3">
					<label for="writer" class="form-label">작성자</label> 
					<input type="text" class="form-control" id="writer" name="writer" readonly">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="title" class="form-label">제목</label>
					<input type="text" class="form-control" id="title" name="title">
				</div>
	
				<div class="mb-3 mt-3">
					<label for="content" class="form-label">내용</label>
					<div>
						<textarea id="summernote" name="content"></textarea> 
					</div>
					
				</div>
	
				
				<button type="button" class="btn btn-info" onclick="openArea();">파일 업로드</button>
				<div class="mb-3 mt-3">
					<div class="fileDrop" id="fileDrop">
						<div class="fileContent">이 영역에 업로드 할 파일을 드래그 드롭 하세요</div>
						<div class="upFileList"></div>
					</div>
				</div>
				
				<div class="mb-3 mt-3">
					<label for="pwd" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" id="pwd" name="pwd">
				</div>
				
				<div class="btns" style="text-align: center;">
					<button type="submit" id="save" class="btn text-white" style="background-color: #7FAD39;">등록</button>
					<button type="reset" id="cancel" class="btn btn-secondary"
						onclick="writeCancel();">돌아가기</button>
				</div>
			</div>	
		</form>
		
	</div>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
