<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
   
<title>Insert title here</title>
</head>
 <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
	
	let bno =7;
	let showList = function(bno) {
		
		
		  $.ajax({
              type:'get',       // 요청 메서드
              url: '/reply/list?bno='+bno,  // 요청 URI
           
              success : function(result){
                  $("#replyList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
                 
                 
              },
              error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
          }); 
		
		
	}
	
	$(document).ready(function () {
		
		
		$("#sendBtn").click(function () {
			let replyContent = $("input[name=comment]").val();
			
			
			
			$.ajax({
                type:'POST',       // 요청 메서드
                url: '/reply/writer?bno='+bno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
               
                data : JSON.stringify({bno:bno,replyContent:replyContent} ),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                   
                    alert(result);       // result는 서버가 전송한 데이터
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); //
         
			
		});
		
		$("#replyList").on("click",".delBtn",function () {
			
			let rno = $(this).parent().attr("data-rno");
			let bno = $(this).parent().attr("data-bno");
			
			  $.ajax({
	              type:'DELET',       // 요청 메서드
	              url: '/reply/remove/'+rno+'?bno='+bno,  // 요청 URI
	           
	              success : function(result){
	            	  showList(bno);
	                 
	                 
	              },
	              error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	          }); 
			
			
		});
		
	})
	
	
	let toHtml = function (replyContent) {
		let tmp = "<ul>";
		
		replyContent.forEach(function (replyContent) {
			tmp +='<li data-rno='+replyContent.rno
			tmp +='data-prno=' +replyContent.prno
			tmp += 'data-bno='+ replyContent.bno+'>'
		
			tmp += 'replyContent=<span class="replyContent">' +replyContent.replyer+'</span>'
			tmp += 'replyContent=<span class="replyContent">'+ replyContent.replyContent+'</span>'
			tmp += 'replyWittenDate='+replyContent.replyWittenDate
			tmp += '<button class="delBtn">삭제</button>'
			tmp += '</li>'
		});
		
		return tmp+ "</ul>"
	}
	
	
</script>
<body>

	<jsp:include page="../header.jsp"></jsp:include>


	<div style="padding: 100px">

		<div class="mb-3 mt-3">
			<label for="title" class="form-label">글 제목:</label> <input
				type="title" class="form-control" id="title" name="title"
				value="${board.title }" readonly="readonly">
		</div>

		<div class="mb-3 mt-3">
			<label for="bno" class="form-label">글 번호:</label> <input type="bno"
				class="form-control" id="bno" name="bno" value="${board.bno }"
				readonly="readonly">
		</div>

		<div class="mb-3">
			<label for="writer" class="form-label">글쓴이 :</label> <input
				type="writer" class="form-control" id="writer" name="writer"
				value="${board.writer }" readonly="readonly">
		</div>
		<label for="content" class="form-label" style="text-align: center;">내용</label>
		<div class="mb-3">

			<textarea rows="10" cols="160" readonly="readonly">${board.content}</textarea>
		</div>

	</div>


	comment:<input type="text" name="comment"><br/>

	

<button id="sendBtn" type="button">send</button>
<div id="replyList"></div>







	<div style="text-align: center;">
		<button type="button" class="btn btn-primary">
			<a href="/boardFree/delBoard?bno=${board.bno }" />삭제
		</button>

		<button type="button" class="btn btn-success">
			<a href="/boardFree/modify?bno=${board.bno }" />수정
		</button>

		<jsp:include page="../footer.jsp"></jsp:include>

	</div>


</body>
</html>