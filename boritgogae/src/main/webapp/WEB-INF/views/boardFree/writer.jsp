<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css"  rel="stylesheet" >
<script type="text/javascript" src="../resources/js/bootstrap.bundle.min.js"></script>


<script>

$(function(){
   // on() : 동적으로 생성된 태그에 대하여 이벤트 등록, 여러개의 이벤트를 한꺼번에 태그에 등록할때 사용
   $(".fileDrop").on("dropenter dragover", function(event){
      event.preventDefault(); // 이벤트 무효화
   });
   
   $(".fileDrop").on("drop", function(evt){
      evt.preventDefault(); // 이벤트가 전파되어 드롭된 파일이 웹브라우저에서 열리는 것을 방지
      
      let files = evt.originalEvent.dataTransfer.files;
      console.log(files);
      
      let formData = new FormData(); // form 태그 객체
      formData.append("upfile", files[0]); // form객체에 파일 첨부
      
      let url = "/boardFree/uploadFile";
      
      $.ajax({
         url : url, // 데이터 송수신될 주소 
         type : "post", // 통신 방식(get, post)
         dataType : "json", // 수신받을 데이터 타입
         processData : false,  // 전송하는 데이터를 텍스트 변환하지 않는다
         contentType : false,   // 기본값 (application/x-www-form-urlencoded)을 사용하지 않는다.
         data : formData,   // 전송할 데이터
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
      output += "<img src='/resources/uploads" + data.savedOriginImageFileName + "'  />";
   } else {
      output += "<a href='/resources/uploads" + data.notImageFileName + "'>" + data.notImageFileName + "</a>";
   }
   
   output += "<img src='/resources/img/minus.png' id='" + data.savedOriginImageFileName + "' width='20px' class='minusBtn'"
    + " onclick='delFile(this);' />";
   
   $(".upfileList").append(output);
}

function delFile(obj) {
   let deleteFileName = $(obj).attr("id");

   console.log(deleteFileName);
   let url = "/boardFree/delFile";
   $.ajax({
      url : url, // 데이터 송수신될 주소 
      type : "post", // 통신 방식(get, post)
      dataType : "text", // 수신받을 데이터 타입
      data : {"deleteFileName" : deleteFileName},   // 전송할 데이터
      success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
         console.log(data);
      
         if (data == "success") {
            $(obj).prev().remove(); // 파일 화면에서 지움
            $(obj).remove(); // 마이너스 버튼 지움
         }

      }
   });
   
}

function writeCancel() {
   let url = "/boardFree/writeCancel";
   $.ajax({
      url : url, // 데이터 송수신될 주소 
      type : "post", // 통신 방식(get, post)
      dataType : "text", // 수신받을 데이터 타입
      success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
         console.log(data);
      
         if (data == "success") {
            location.href = "/boardFree/listAll"; // 게시판 전체 목록 페이지로 이동
         }

      }
   });
} 

function openArea() {
   $(".fileDrop").show();
}






</script>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	
	<h2 style="text-align: center;">글작성</h2>
	
	<form action="/boardFree/create" method="post"style ="padding: 100px;">
		<div class="mb-3 mt-3">
			<label for="title" class="form-label">글 제목:</label> <input
				 class="form-control" id="title" name="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">글쓴이 :</label> <input
				 class="form-control" id="writer" name="writer">
		</div>
	
			<label for="content">내용 :</label>
			<textarea class="form-control" rows="5" id="content" name="content" ></textarea>
			
			
		    <div class="mb-3 mt-3">
            <button type="button" class="btn btn-info"  onclick="openArea();">파일 업로드</button>
            <div class="fileDrop">
               <div class="fileContent" style="height:100px;  ">이 영역에 업로드 할 파일을 드래그 드롭 해 주세요~!</div>
            </div>
            
            <div class="upfileList"></div>
            

         </div>

	<div  style="text-align: right;">
		<button type="button" class="site-btn" onclick="location.href = '${contextPath}/boardFree/list'" >취소</button>
		<button type="submit" class="site-btn">완료</button>
	</div>
	
	</form>
	
	
	


	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>