<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script type="text/javascript">


$("#sendBtn").click(function () {
	let receiverId = $("#receiverId").val();
	let noteContent = $("#noteContent").val();
	
	
	
	$.ajax({
        type:'get',       // 요청 메서드
        url: '/member/send',  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        data :{"noteContent":noteContent,"receiverId":receiverId} ,  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          
          
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); //
 
	
});



</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container mt-3">
 
  
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
    쪽지보내
  </button>
</div>

<div class="modal" id="myModal">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header" id="receiverId">
        <label for="receiverId" class="form-label">받는 사람:</label> <input
				 class="form-control" id="receiverId" name="receiverId">
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="noteContent">
       <label for="noteContent">내용 :</label>
			<textarea class="form-control" rows="5" id="noteContent" name="noteContent" ></textarea>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
       <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="sendBtn">보내기</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>











<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>