<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="../../../resources/js/jquery-3.6.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="../../../resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"
	src="../../../resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function addAskReply() {
		let askBno = ${board.askBno}
		let replyer = $("#replyer").val();
		let contents = $("#replyContent").val();
		let url = "/reply/ask"
		let sendData = JSON.stringify({
			askBno : askBno, replyer : replyer, contents : contents
		}); // json 문자 형식(json 문자열)으로 바꿔줌
		
		console.log(sendData);
		$("#replyContent").val("");
		
		$.ajax({
            url : url, // 데이터 송수신될 주소 
			data : sendData, // 송신할 데이터
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			headers : {
				"content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST" // 구 버전의 웹 브라우저에서 (PUT / DELETE) 방식이 호환이 안되는 버전에서 호환 되도록
			},
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
               console.log(data);
            	if(data == "success") {
            		viewAllReplies();
            	} else if(data == "fail") {
            		alert("댓글 등록 실패");
            	}
            }, error : function(e) {
				console.log(e);
			}
         });
		
		
	}

	
</script>
<style type="text/css">
.inline {
	
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
			<label for="attachFiles" class="form-label">첨부파일 (클릭 시 원본
				다운로드)</label>
			<c:forEach var="file" items="${fileList }">
				<div class="files">
					<a href="${path }${file.originalFile }" download style="">${file.originalFile }</a>
				</div>
			</c:forEach>
		</div>
		<div>
			<h3>&nbsp</h3>
		</div>
		<div>
			<h5 id="contentss">${board.contents }</h5>
			<div class="attachImgFiles">
				<c:forEach var="imgFiles" items="${fileList }">
					<c:if test="${imgFiles.thumbnailFile != null }">
						<div class="imgFile">
							<img src="${path }${imgFiles.originalFile }"
								style="max-width: 150px; max-height: 150px" ;  />
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<div class="col"></div>
			<div class="col"></div>
			<div class="col"></div>
			<div class="col">
				<div class="btns">
					<button type="button" class="btn btn-primary"
						onclick="location.href='/board/ask/modify?no=${board.askBno}';">글
						수정</button>
					<button type="button" class="btn btn-warning"
						onclick="location.href='/board/ask/remove?no=${board.askBno}';">글
						삭제</button>
					<button type="button" class="btn btn-info"
						onclick="location.href='/board/ask';">목록으로</button>
				</div>
			</div>
		</div>


	</div>

	<!-- 여기서부터 리플관련 -->
	<div class="container">
		<label for="comment">Reply </label>


		<div>
			<table class="table table-hover table-danger">
				<c:forEach var="board" items="${askReplyList }">
					<!-- 리플 넣는 장소 -->
				</c:forEach>
			</table>
		</div>


		<div style="font-size: 0;">
			<!--  -->
			<button type="button" class="btn"
				style="vertical-align: top; height: 100px" id="replyer" value="test"
				disabled>작성자명</button>
			<textarea class="form-control" rows="3" id="replyContent" name="text"
				style="max-width: 800px; display: inline-block; vertical-align: top; height: 100px"></textarea>
			<button type="button" class="btn btn-info" id="addAskReplyBtn" onclick="addAskReply();"
				style="vertical-align: top; height: 100px">댓글등록</button>
		</div>
	</div>
	<

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>