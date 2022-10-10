<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 답글</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		// on() : 동적으로 생성된 태그에 대하여 이벤트 등록, 여러개의 이벤트를 한꺼번에 태그에 등록할 때 사용
		$(".fileDrop").on("dropenter dragover", function(event) {
			event.preventDefault(); // 이벤트 무효화
		});

		$(".fileDrop").on("drop", function(evt) {
			// 이벤트가 전파되어 드롭된 파일이 웹브라우저에서 열리는 것을 방지
			evt.preventDefault();

			let files = evt.originalEvent.dataTransfer.files;
			console.log(files);

			let formData = new FormData(); // form 태그 객체
			formData.append("upfile", files[0]); // form 객체 파일에 파일 첨부
			formData.append("test", "test"); // form 객체 파일에 파일 첨부
			console.log(formData);

			let url = "/board/ask/uploadFile";
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "post", // 통신 방식(get, post)
				dataType : "json", // 수신받을 데이터 타입
				processData : false, // 전송하는 데이터를 텍스트 변환하지 않는다.
				contentType : false,
				// 기본값(application/x-www-form-urlencoded)을 사용하지 않는다.
				async : false,
				data : formData,
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					showFileList(data);
				}
			});

		});

	});

	function showFileList(data) {
		let output = "";
		if (data.image) { // 이미지 파일이면
			output += "<img src='/resources/askBoard/uploads" + data.thumbnailFileName + "'style = 'max-width : 48px; max-height : 48px' />";
		} else {
			output += "<img src='../../../resources/img/commonFile.png' style = 'max-width : 48px'/>";
		}

		output += "<img src='../../../resources/img/close.png' width='20px' id='"
				+ data.savedOriginImageFileName + "' class='closeBtn' ";
		output += "onclick='delFile(this)' />";

		$(".upfileList").append(output);
	}

	function delFile(obj) {
		let deleteFileName = $(obj).attr("id");
		let url = "/board/ask/delFile";
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 통신 방식(get, post)
			dataType : "text", // 수신받을 데이터 타입
			data : {
				"deleteFileName" : deleteFileName
			}, // 전송할 데이터
			async : false,
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$(obj).prev().remove(); //  썸네일 파일 화면에서 지움
					$(obj).remove(); // close 버튼 지움
					deleteFileName = null;
				}

			},
			error : function(request, status, error) {
				console.log("code: " + request.status)
				console.log("message: " + request.responseText)
				console.log("error: " + error);
			}
		});
	}

	function writeCancel() {
		let url = "/board/ask/writeCancel";
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 통신 방식(get, post)
			dataType : "text", // 수신받을 데이터 타입
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);

				if (data == "success") {
					location.href = "/board/ask/list"; // 게시판 전체 목록 페이지로 이동
				}

			}
		});
	}
</script>
<style type="text/css">
.closeBtn {
	
}

.fileDrop {
	width: 100%;
	height: 150px;
	border: groove;
}

.fileDrop .fileContent {
	padding: 20px;
	text-align: center;
	margin: 10px auto;
}

.upfileList {
	padding: 5px;
	float: left;
}

.upfileList img, .upfileList a {
	margin-left: 10px;
	position: relative;
}

.btns {
	clear: left;
	margin-top: 15px;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<div class="container p-5 my-5 bg-success text-white test2">
			<h3>${askBno }번글 답변하기</h3>
		</div>
		<form action="/board/ask/answerCreate" method="post">
			<div class="mb-3 mt-3">
				<textarea class="form-control" id="ref"
					name="ref" style="display:none;">${askBno }</textarea>
				<label for="title" class="form-label">제목 : </label> <input
					type="text" class="form-control" id="title" name="title">
			</div>

			<div class="mb-3 mt-3">
				<label for="title" class="form-label">카테고리 : </label>
				<div>
					<select class="form-select" id="askCode" name="askCode">
						<c:forEach var="ask" items="${askCodeList }">
							<option value="${ask.askCode }">${ask.askOption }</option>
						</c:forEach>
					</select>
				</div>
			</div>


			<div class="mb-3 mt-3">
				<label for="contents" class="form-label">내용 : </label>
				<textarea class="form-control" rows="15" id="contents"
					name="contents"></textarea>

			</div>
			<div class="mb-3 mt-3">
				<label for="contents" class="form-label">첨부파일 : </label>
				<div class="fileDrop">
					<div class="fileContent">업로드 할 파일을 드래그 앤 드롭 해주세요!</div>
					<div class="upfileList"></div>
				</div>
			</div>

			<div class="btns" style="text-align: right;">
				<button type="submit" class="btn btn-primary">저장</button>
				<button type="reset" class="btn btn-danger" onclick="writeCancel();">취소</button>
			</div>
		</form>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>