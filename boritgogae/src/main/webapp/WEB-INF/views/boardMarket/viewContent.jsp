<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"
	src="../resources/js/bootstrap.bundle.min.js"></script>
<script>

	
	$(document).ready(function () {
		viewAllReply();
	
		
	});
	
	function showReply() {
		$("#replyDiv").show();
	}
	
	function modalClose() {
		$("#modifyReply").hide(); //댓글 수정 중 닫기(모달 창)
		
	}
	
	//댓글 등록
	function replyWrite() {
		let bno = ${board.no };
		let replyer = $("#writer").val();  //로그인 한 회원인지 검사 해야함
		let replyContent = $("#replyContent").val();
		let url = "/reply";
		let sendData = JSON.stringify({bno : bno, replyer : replyer, replyContent : replyContent}); 
		
		console.log("replyContent찾기"+replyContent);
		console.log("bno찾기"+bno);
		console.log(sendData);
		
		
		//댓글이 공백일 경우 저장되지 않는다
		if(replyContent==""){
			alert("댓글을 입력하세요");
		
		}else{
		
		 $("#replyContent").val("");
		 $("#replyDiv").hide();
		 
		 $.ajax({
			url : url,
			data : sendData,
			type : "post",
			dataType : "text",
			headers : {
				"content-type" : "application/json",  // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST"  
			},
			success : function (data) {
				console.log(data);
				
				if(data == "success"){
					viewAllReply();
				}else if (data =="fail"){
					alert("댓글 등록에 실패했습니다.")
				}
			}, 
			error : function(e) {
				console.log(e);
				alert("댓글 등록에 실패했습니다."+e);
			}
			
		 });
	}
	}
	
	// 현재 글의 모든 댓글을 얻어오는 메서드
	function viewAllReply() {
		let bno = ${board.no };
		let url = "/reply/"+bno;
		
		console.log(url);
		$.ajax({
			url : url,
			type : "get",
			dataType : "json",
			success : function (data) {
				printAllReply(data);
				console.log(data);
			}, error : function (e) {
				console.log(e);
				
			}
		});
	}
	
	
	
	//댓글 출력 메서드
	 function printAllReply(data) {
		let print = "<div class='list-group'>";
		
		
		
		$.each(data, function (i, item) {
			print += "";
			print += "<div class='writtenDate' id='replyWrittenDate'>"+calcDate(item.replyWrittenDate)
			print += "<span class='icons'>";
			print += "<img src='/resources/img/modi.png' onclick='modifyReply(" + item.rno + ")'; />";
			print += "<img src='/resources/img/del.png'  onclick='delReply(" + item.rno + ")'; />";
			print += "</div></span>";
			print += "<div class='replyer'>"+item.replyer+"</div>"
			print += "<div class='replyContent'>"+item.replyContent+"</div>"
			
			
			
		});
		print += " </div>";
		
		$("#replies").html(print);
	}
	
	// 댓글을 작성한 날짜를 문자열로 반환
		function calcDate(wd) {
			// 방금전, oo분전, 날짜시간
			let diff = new Date() - wd;
			let diffSecond = diff / 1000;   // 초단위 시간차
			if (diffSecond < 60 * 10) return "방금 전";
			let diffMinutes = diffSecond / 60;  // 분단위 시간차
			if (diffMinutes < 60) return Math.floor(diffMinutes) + "분 전";
			return new Date(wd).toLocaleString();
		}
	
		//댓글 수정 누르면 수정 모달창 띄우기
		function modifyReply(rno) {
			$("#modiRno").val(rno);
			$("#modifyReply").show(); 
			
			
		}
		
		//댓글 수정 모달
		function modiReply() {
			let rno = $("#modiRno").val();
			
			let replyContent = $("textarea[name=replyContent]" ).val();
			let url = "/reply/modiReply/"+rno;
			let sendData = JSON.stringify({ "rno":rno,  "replyContent" : replyContent});

			$(".modal-content").hide(); //모달창의 수정 버튼 누르면..
			
			$("#replyContent").val();
			console.log("댓글 수정"+rno);
			console.log("댓글 수정"+sendData);
			
			$.ajax({
				url : url,
				data : sendData,
				type : "post",
				dataType : "text",
				headers : {
					"content-type" : "application/json",  // 송신되는 데이터의 타입이 json임을 알림
					"X-HTTP-Method-Override" : "POST"  
				},
				success : function (data) {
					console.log(data);
					
					if(data == "success"){
						viewAllReply();
					}else if (data =="fail"){
						alert("댓글 수정 실패")
					}
				}, 
				error : function(e) {
					console.log(e);
					alert("댓글 수정 실패했습니다."+e);
				}
			});
		}
	
		// 댓글 삭제
		function delReply( rno) {
			let bno = ${board.no };
			
			let replyer = $("#writer").val();  //아직
			let replyContent = $("#replyContent").val();
			let url = "/reply/delReply";
			let sendData = JSON.stringify({bno : bno, rno:rno}); 
			
			console.log("삭제 rno찾기"+rno);
			console.log(sendData);
			 
			 $.ajax({
				url : url,
				data : sendData,
				type : "post",
				dataType : "text",
				headers : {
					"content-type" : "application/json",  // 송신되는 데이터의 타입이 json임을 알림
					"X-HTTP-Method-Override" : "POST"  
				},
				success : function (data) {
					console.log(data);
					
					if(data == "success"){
						viewAllReply();
					}else if (data =="fail"){
						alert("댓글 삭제 실패")
					}
				}, 
				error : function(e) {
					console.log(e);
					alert("댓글 삭제 실패했습니다."+e);
				}
				
			 });
		}
		
			
</script>
<style>
	button .site-btns{
		text-align: center;
		
	}
	
	
	ul{
	 list-style: none;
	 margin-top: 50px;
	 padding-left: 0;
	
	}
	 #replyWrittenDate{
		font-size: 15px;
		padding-top: 20px;
		
	}
	.icons {
	margin-right: 4px;
}

.icons img {
	width: 18px;
}
	
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
		<h3 style="font: bold; text-align: center; text-decoration: underline;" >장터 게시판</h3>
		
		<div class="viewcontent" style="padding: 100px;">	
			<div class="mb-3 mt-3">
				<label for="writer">작성자 :</label> 
					<input type="text"  class="form-control" id="writer" value="${board.writer }" readonly> 
			</div>
			
			<div class="mb-3 mt-3">
				<label for="title">제목 :</label> 
					<input type="text"  class="form-control" id="title" value="${board.title }"readonly> 
			</div>
			
				<div class="mb-3 mt-3">
				<label for="attachFiles" class="form-label">첨부파일:</label>
				<c:forEach var="file" items="${fileList }">
					<c:if test="${file.thumbnailFileName == null }">
						<div class="files">
							<a href="${contextPath }/resources/uploads/${file.originFileName }">${file.originFileName }</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="content"></label>
					<label for="content" class="form-control" id="content" name="content" readonly>${board.content }</label>
					
					<div class="attachImgFiles">
					<c:forEach var="imgFiles" items="${fileList }">
						<c:if test="${imgFiles.thumbnailFileName != null }">
							<div class="imgFile">
								<img src="${contextPath }/resources/uploads${imgFiles.originFileName }" />
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
		
			
			
			
			<a href="/boardMarket/modifyContent?no=${board.no }" role="button" class="site-btn">수정</a>
			<!-- <button type="button" class="btn btn-success"><a href="/boardMarket/modifyContent?no=${board.no }">수정</a></button>
			 	 <button type="button" class="btn btn-success"><a href="/boardMarket/delContent?no=${board.no }">삭제</a></button>
			 -->
			<button type="button" class="site-btn" onclick="location.href='/boardMarket/listAll';">목록</button>
			<a href="/boardMarket/delContent?no=${board.no }" role="button" class="site-btn">삭제</a>
			<button type="button" class="site-btn" onclick="showReply();">댓글달기</button>
			
		
		
		<!-- 댓글 -->
		<div id="replyDiv" style="display: none">
			<label for="replyContent" class="form-label">댓글 작성</label> <input
				type="text" class="form-control" placeholder="여기는 작성자 아이디 들어옵니다">
			<input
				type="text" rows="5" class="form-control" id="replyContent" >
			
			<button type="button" class="site-btn" style="padding: 8px 8px 8px;" onclick="replyWrite();">
				등록</button>
		</div>

		<div id="replies"></div>
		
	</div>
	
		<!-- The Modal -->
	<div class="modal" id="modifyReply">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">댓글 수정</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"  onclick="modalClose();"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					
					<textarea rows="5" class="form-control" id="replyContent" name="replyContent"></textarea>
					<input type="hidden" id="modiRno" >
					
					

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal"  onclick="modiReply();">수정</button>
				</div>

			</div>
		</div>
	</div>
		
		
		
		<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>