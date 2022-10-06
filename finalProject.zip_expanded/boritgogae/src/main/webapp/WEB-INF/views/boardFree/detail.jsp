<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <jsp:include page="../header.jsp"></jsp:include>
  
    

      
      <div class="mb-3 mt-3">
			<label for="title" class="form-label">글 제목:</label> <input
				type="title" class="form-control" id="title"
				name="title" value="${board.title }">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">글쓴이 :</label> <input
				type="writer" class="form-control" id="writer"
				 name="writer" value="${board.writer }">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">내용 :</label>
				<textarea rows="30" cols="200">${board.content}</textarea>
		</div>
     
     <button type="button" class="btn btn-success" ><a href="/boardFree/modify?bno=${board.bno }"/>수정</button>
 
      
      <jsp:include page="../footer.jsp"></jsp:include>
   
    
  

</body>
</html>