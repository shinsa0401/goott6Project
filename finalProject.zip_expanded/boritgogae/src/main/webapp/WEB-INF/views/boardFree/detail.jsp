<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
   
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css"  rel="stylesheet" >
<script type="text/javascript" src="../resources/js/bootstrap.bundle.min.js"></script>



 <style>
* {
    border : 0;
    padding : 0;
}



#commentList {
    width : 50%;
    margin : auto;
}

.comment-content {
    overflow-wrap: break-word;
}

.comment-bottom {
    font-size:9pt;
    color : rgb(97,97,97);
    padding: 8px 0 8px 0;
}

.comment-bottom > a {
    color : rgb(97,97,97);
    text-decoration: none;
    margin : 0 6px 0 0;
}

.comment-area {
    padding : 0 0 0 46px;
}

.commenter {
    font-size:12pt;
    font-weight:bold;
}

.commenter-writebox {
    padding : 15px 20px 20px 20px;
}

.comment-img {
    font-size:36px;
    position: absolute;
}

.comment-item {
    position:relative;
}

.up_date {
    margin : 0 8px 0 0;
}

#comment-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
}

textarea {
    display: block;
    width: 100%;
    min-height: 17px;
    padding: 0 20px;
    border: 0;
    outline: 0;
    font-size: 13px;
    resize: none;
    box-sizing: border-box;
    background: transparent;
    overflow-wrap: break-word;
    overflow-x: hidden;
    overflow-y: auto;
}

#comment-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

.btn {
    font-size:10pt;
    color : black;
    background-color: #eff0f2;
    text-decoration: none;
    padding : 9px 10px 9px 10px;
    border-radius: 5px;
    float : right;
}

#sendBtn, #btn-write-reply { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-reply { 
    background-color: #eff0f2;
    margin-right : 10px;
}

#btn-write-modify { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-modify { 
    margin-right : 10px;
}

#reply-writebox {
    display : none;
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#reply-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

#modify-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#modify-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}



.paging {
    color: black;
    width: 100%;
    text-align: center;
}

.page {
    color: black;
    text-decoration: none;
    padding: 6px;
    margin-right: 10px;
}

.paging-active {
    background-color: rgb(216, 216, 216);
    border-radius: 5px;
    color: rgb(24, 24, 24);
}

.paging-container {
    width:100%;
    height: 70px;
    margin-top: 50px;
    margin : auto;
}
    </style>





<script type="text/javascript">
	
	let bno =${board.bno };
	
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
		showList(bno);
		
		$("#replyList").on("click",".replyBtn",function () {
			//replyForm옮기기
			$("#replyForm").appendTo($(this).parent());
			
			// 답글을 입력할 폼을 보여주기
			$("#replyForm").css("display","block");
			
			
		});
		
		$("#wrtRepBtn").click(function () {
			let replyContent = $("textarea[name=replyComment]").val();
			let prno = $("#replyForm").parent().attr("data-prno");
			
			
			
			$.ajax({
                type:'POST',       // 요청 메서드
                url: '/reply/writerreply?bno'+bno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({prno:prno, bno:bno, replyContent:replyContent} ),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); //
            
            
            $("#replyForm").css("display","none")
            $("textarea[name=replyComment]").val('')
            $("#replyForm").appendTo("body");
            
		});
         
			
		
		
		
		
		
		
		
		
		
		
		$("#modBtn").click(function () {
			let rno = $(this).attr("data-rno");
			let replyContent = $("textarea[name=comment]").val();
			
			
			
			$.ajax({
                type:'PATCH',       // 요청 메서드
                url: '/reply/modify/'+rno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({rno:rno,replyContent:replyContent} ),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); //
         
			
		});
		
		
		
		
		
		
		
		
		$("#sendBtn").click(function () {
			let replyContent = $("textarea[name=comment]").val();
			
			
			
			$.ajax({
                type:'POST',       // 요청 메서드
                url: '/reply/writerreply?bno'+bno,  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data : JSON.stringify({bno:bno,replyContent:replyContent} ),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); //
         
			
		});
		
		
		
		$("#replyList").on("click",".modBtn",function () {
			let rno = $(this).parent().attr("data-rno")
			console.log(rno);
			
			let replyContent = $("span.replyContent",$(this).parent()).text();
			
			$("textarea[name=comment]").val(replyContent);
			
			$("#modBtn").attr("data-rno",rno);
		});
		
		
		
	$("#replyList").on("click",".delBtn",function () {
				let rno = $(this).parent().attr("data-rno")
				console.log(rno);
				let bno = $(this).parent().attr("data-bno")
				console.log(bno);
				  $.ajax({
		              type:'get',       // 요청 메서드
		              url: '/reply/remove/'+rno+'?bno='+bno,  // 요청 URI
		           
		              success : function(result){
		            	
		            	  showList(bno);
		                 
		                 
		              },
		              error   : function(e){ 
		            	  console.log(e);
		            	  alert("error",e) } // 에러가 발생했을 때, 호출될 함수
		          }); 
				
				
			});
			
	})
	
	
	let toHtml = function (replyContent) {
		
		
		let tmp = "<ul>";
		
		replyContent.forEach(function (replyContent) {
			tmp += '<li class="comment-item" li data-rno= '+replyContent.rno
			tmp += ' data-prno= ' +replyContent.prno
			tmp += ' data-bno= '+ replyContent.bno+'>'
			tmp += ' <span class="comment-img">'
			tmp += ' <i class="fa fa-user-circle" aria-hidden="true"></i>'
			tmp += ' </span>'
			if(replyContent.rno!=replyContent.prno)
			tmp += 'ㄴ'
			tmp += '<div class="comment-area">'
			tmp += ' <div class="commenter"> replyer =  <span class="replyer">  ' + replyContent.replyer +'</span></div>'+ Date(replyContent.replyWittenDate)
			
			
			tmp += '<div> replyContent = <span class= "replyContent"> '+ replyContent.replyContent+'</span></div>'
			tmp += ' <div data-rno= '+replyContent.rno 
			
			tmp += ' data-prno= ' +replyContent.prno
			tmp += ' data-bno= '+ replyContent.bno + '>'
		
			tmp += '<button class="delBtn" type="button">삭제</button>'
			tmp += '<button class="modBtn" type="button">수정</button>'
			tmp += '<button class="replyBtn" type="button">답글</button> </div> </div> '
			tmp += '</li>'
		})
		
		return tmp+ "</ul>";
		
		
	}
	
	
</script>
<body>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	

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
		
		<div class="mb-3 mt-3">
            <label for="attachFile" class="form-label">첨부파일:</label>
            <c:forEach var="file" items="${fileList }">
               
                  <div class="files">
                     <img src="${contextPath }/resources/uploads${file.attachFile }">
                  </div>
               
            </c:forEach>
         </div>
         
		<label for="content" class="form-label" style="text-align: center;">내용</label>
		<div class="mb-3">

			<textarea rows="10" cols="160" readonly="readonly">${board.content}</textarea>
		</div>
		
		</div>


	
	<!-- comment:
	<input type="text" name="comment">
	<br /> -->
	
	
	 <div id="comment-writebox">
            <div class="commenter commenter-writebox">${id}</div>
            <div class="comment-writebox-content">
                <textarea name="comment" id="comment" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
            </div>
            <div id="comment-writebox-bottom">
                <div class="register-box">
                    <a href="#" class="btn" id="sendBtn">등록</a>
                </div>
            </div>
        </div>
        
        
        


<div id="replyList"></div>
	<!-- <button id="sendBtn" type="button">send</button> -->
	<!-- <button id="modBtn" type="button">수정</button>
	
	<div id="replyForm" style="display: none;">
	<input type="text"  name="replyComment">
	<button id="wrtRepBtn" type="button">등록</button>
	</div>
 -->
	






	<div style="text-align: center;">
		<button type="button" class="site-btn" onclick="location.href = '/boardFree/delBoard?bno=${board.bno }'">
			삭제
		</button>

		<button type="button" class="site-btn"onclick="location.href = '/boardFree/modify?bno=${board.bno }'">
			수정
		</button>

		<jsp:include page="../footer.jsp"></jsp:include>

	</div>


</body>
</html>