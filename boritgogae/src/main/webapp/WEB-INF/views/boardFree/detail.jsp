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
              type:'get',       // ?????? ?????????
              url: '/reply/list?bno='+bno,  // ?????? URI
           
              success : function(result){
                  $("#replyList").html(toHtml(result));    // ??????????????? ????????? ???????????? ????????? ??????
                 
                 
              },
              error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
          }); 
		
		
	}
	
	$(document).ready(function () {
		showList(bno);
		
		$("#replyList").on("click",".replyBtn",function () {
			//replyForm?????????
			$("#replyForm").appendTo($(this).parent());
			
			// ????????? ????????? ?????? ????????????
			$("#replyForm").css("display","block");
			
			
		});
		
		$("#wrtRepBtn").click(function () {
			let replyContent = $("textarea[name=comment]").val();
			let prno = $("#replyForm").parent().attr("data-prno");
			
			
			
			$.ajax({
                type:'POST',       // ?????? ?????????
                url: '/reply/writerreply?bno'+bno,  // ?????? URI
                headers : { "content-type": "application/json"}, // ?????? ??????
                data : JSON.stringify({prno:prno, bno:bno, replyContent:replyContent} ),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
            }); //
            
            
            $("#replyForm").css("display","none")
            $("textarea[name=comment]").val('')
            $("#replyForm").appendTo("body");
            
		});
         
			
		
		
		
		
		
		

		
		
		$("#modBtn").click(function () {
			let rno = $(this).attr("data-rno");
			let replyContent = $("textarea[name=modContent]").val();
			
			
			
			$.ajax({
                type:'PATCH',       // ?????? ?????????
                url: '/reply/modify/'+rno,  // ?????? URI
                headers : { "content-type": "application/json"}, // ?????? ??????
                data : JSON.stringify({rno:rno,replyContent:replyContent} ),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
            }); //
            
            
      
            
            
         
			
		});
		
		
		
		
		
		
		
		
		$("#sendBtn").click(function () {
			let replyContent = $("textarea[name=comment]").val();
			
			
			
			$.ajax({
                type:'POST',       // ?????? ?????????
                url: '/reply/writerreply?bno'+bno,  // ?????? URI
                headers : { "content-type": "application/json"}, // ?????? ??????
                data : JSON.stringify({bno:bno,replyContent:replyContent} ),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
                success : function(result){
                  
                    showList(bno);
                },
                error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
            }); //
         
			
		});
		
		
		
		$("#replyList").on("click",".modBtn",function () {
			let rno = $(this).parent().attr("data-rno")
			console.log(rno);
			$("#modalWin").modal("show");
			let replyContent =  $(".replyContent").text();   /* $("span.replyContent",$(this).parent()).text(); */
			
			console.log(replyContent);
			$("textarea[name=modContent]").val(replyContent);
			
			$("#modBtn").attr("data-rno",rno);
			
			
		});
		
		
		
	$("#replyList").on("click",".delBtn",function () {
				let rno = $(this).parent().attr("data-rno")
				console.log(rno);
				let bno = $(this).parent().attr("data-bno")
				console.log(bno);
				  $.ajax({
		              type:'get',       // ?????? ?????????
		              url: '/reply/remove/'+rno+'?bno='+bno,  // ?????? URI
		           
		              success : function(result){
		            	
		            	  showList(bno);
		                 
		                 
		              },
		              error   : function(e){ 
		            	  console.log(e);
		            	  alert("error",e) } // ????????? ???????????? ???, ????????? ??????
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
			tmp += '???'
			tmp += '<div class="comment-area">'
			tmp += ' <div class="commenter"> replyer =  <span class="replyer">  ' + replyContent.replyer +'</span></div>'+ Date(replyContent.replyWittenDate)
			
			
			tmp += '<div> replyContent = <span class= "replyContent"> '+ replyContent.replyContent+'</span></div>'
			tmp += ' <div data-rno= '+replyContent.rno 
			
			tmp += ' data-prno= ' +replyContent.prno
			tmp += ' data-bno= '+ replyContent.bno +  '>'
		
			tmp += '<button class="delBtn" type="button">??????</button>'
			tmp += '<button class="modBtn" type="button">??????</button>'
			tmp += '<button class="replyBtn" type="button">??????</button> </div> </div> '
			tmp += '</li>'
		})
		
		return tmp+ "</ul>";
		
		
	}
	
	
</script>
<body>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	

	<jsp:include page="../header.jsp"></jsp:include>


	<div style="padding: 100px">
			<table class="table">
			<thead>
				<tr>
					<th>????????? : "${board.bno }"</th>
				</tr>
				<tr>
				<th >?????? : ${board.title }</th>
				</tr>
				<tr>
				<th>????????? : ${board.writer }" </th>
				</tr>
				
			</thead>
			</table>

	
		<div class="mb-3 mt-3">
            <label for="attachFile" class="form-label">????????????:</label>
            <c:forEach var="file" items="${fileList }">
               
                  <div class="files">
                     <img src="${contextPath}/resources/uploads${file.attachFile }">
                    
                  </div>
               
            </c:forEach>
         </div>
         
		<label for="content" class="form-label" style="text-align: center;">??????</label>
		<div class="mb-3">

			<textarea rows="10" cols="160" readonly="readonly">${board.content}</textarea>
		</div>
		
		</div>


	
	<!-- comment:
	<input type="text" name="comment">
	<br /> -->
	
	
	 <div id="comment-writebox" class="my-4 rounded-lg border border-gray-200 p-4 sm:my-10 sm:p-6" >
            <div class="commenter commenter-writebox"></div>
            <div class="comment-writebox-content">
                <textarea name="comment" id="comment" cols="30" rows="3" placeholder="????????? ???????????????"></textarea>
            </div>
            <div id="comment-writebox-bottom">
                <div class="register-box">
                    <a href="#" class="btn" id="sendBtn">??????</a>
                </div>
            </div>
        </div>
        
        <div id="replyForm" style="display: none;">
	<input type="text"  name="replyComment">
	<button id="wrtRepBtn" type="button">??????</button>
	</div>
        


<div id="replyList"></div>






 <div id="modalWin" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="close" id="closeModify" >&times;</span>
            <p>
            <h2> | ?????? ??????</h2>
            <div id="modify-writebox">
                <div class="commenter commenter-writebox"></div>
                <div class="modify-writebox-content">
                    <textarea name="modContent" id="" cols="30" rows="5" placeholder="????????? ???????????????"></textarea>
                </div>
                <div id="modify-writebox-bottom">
                    <div class="register-box">
                        <a href="/boardFree/detail?bno=${board.bno }" class="btn" id="modBtn">??????</a>
                    </div>
                </div>
            </div>
            </p>
        </div>
    </div>

	<!-- <button id="sendBtn" type="button">send</button> -->
	<!-- <button id="modBtn" type="button">??????</button> -->
	
	

	






	<div style="text-align: center;">
		<button type="button" class="site-btn" onclick="location.href = '/boardFree/delBoard?bno=${board.bno }'">
			??????
		</button>

		<button type="button" class="site-btn"onclick="location.href = '/boardFree/modify?bno=${board.bno }'">
			??????
		</button>
		<button type="button" class="site-btn"onclick="location.href = '/boardFree/list'">
			??????
		</button>

		<jsp:include page="../footer.jsp"></jsp:include>

	</div>


</body>
</html>