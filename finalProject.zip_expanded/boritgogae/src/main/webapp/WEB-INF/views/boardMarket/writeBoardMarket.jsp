<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeBoardMarket</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css"  rel="stylesheet" >
<script type="text/javascript" src="../resources/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	
	//글 작성 중 취소했을 경우
	function writeCancle() {
		
		let url = "/boardMarket/writeCancle";
		
		$.ajax({
			url : url,
			type : "post",
			dataType : "text",
			success : function (data) {
				console.log(data);
				if(data=="success"){
					location.href="/boardMarket/listAll";
				}	
			}
			
		});
	}
	
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
		      
		      let url = "/boardMarket/uploadFile";
		      
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
		         },
		         error : function(e) {
						console.log(e);
						
					}
		      });
		      
		   });
		});
	
	function showFileList(data) {
		let output = "";
		if(data.image){  //이미지 파일이면
			output += "<img src='/resources/uploads"+data.thumbnailFileName+"'/>";
			
		}else{
			output += "<a href='/resources/uploads"+data.notImageFileName+"'/>"+data.notImageFileName+"</a>"
		}
		
		output +="<img src ='/resources/img/minus.png' id='"+ data.savedOriginImageFileName 
				+"' width='20px' class'minusBtn'" + " onclick='delFile(this);'/>";
		
		$(".upfileList").append(output);
		
	} 
	
	function delFile(obj) {
		let deleteFileName = $(obj).attr("id");
		
		let url = "/boardMarket/delFile";
		$.ajax({
            url : url, // 데이터 송수신될 주소 
            type : "post", // 통신 방식(get, post)
            dataType : "text", // 수신받을 데이터 타입
            data : {"deleteFileName" : deleteFileName}, // 전송할 데이터
            success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
               console.log(data);
            
            if(data == "success"){
	            $(obj).prev().remove(); //파일 화면에서 지움
	            $(obj).remove(); //마이너스 버튼 지움
            }
             
            }
         });
	}
	
	
	function openArea() {
		$(".fileDrop").show();
	}
</script>
<style>
	.writeForm{
		padding: 100px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<h3 style="font: bold; text-align: center; text-decoration: underline;" >장터 게시판</h3>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	
	<form action="/boardMarket/write" method="post" class="writeForm">
	
		<div class="mb-3 mt-3">
			<label for="writer">작성자 :</label> 
				<input type="text"  class="form-control" id="writer"  name="writer" > 
		</div>
		
		<div class="mb-3 mt-3">
			<label for="title">제목 :</label> 
				<input type="text"  class="form-control" id="title" name="title" placeholder="판매 상품명을 입력하세요"> 
		</div>
		
		<div class="mb-3 mt-3">
			<label for="content">판매글을 작성하세요!</label>
				<textarea class="form-control" rows="10" id="content" name="content"></textarea>
		</div>
		
		<div class="mb-3 mt-3">
				<button type="button" class="btn btn-info" onclick="openArea();">파일
					업로드</button>
				<div class="fileDrop">
					<div class="fileContent"style="height: 100px;">이 영역에 업로드 할 파일을 드래그 드롭 해 주세요</div>
				</div>
				
				<div class="upfileList"></div>
			
		</div>
		
		<div claa="button" style="text-align: center;">
			<button type="submit" class="btn btn-success">등록</button>
			<button type="button" class="btn btn-success" onclick="writeCancle();">취소</button>
		</div>
	</form>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>