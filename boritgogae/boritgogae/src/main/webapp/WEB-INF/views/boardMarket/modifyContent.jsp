<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.btn btn-success{
		text-align: center;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
		<h3 style="font: bold; text-align: center; text-decoration: underline;" >장터 게시판</h3>
		
	<form action="/boardMarket/modifyContent" method="post" >
	
		<div class="viewcontent" style="padding: 100px;">	
		<div class="mb-3 mt-3">
				<label for="no">번호 :</label> 
					<input type="text"  class="form-control" id="no" value="${board.no }" name="no" readonly> 
			</div>
			<div class="mb-3 mt-3">
				<label for="writer">작성자 :</label> 
					<input type="text"  class="form-control" id="writer" value="${board.writer }" name="writer" readonly > 
			</div>
			
			<div class="mb-3 mt-3">
				<label for="title">제목 :</label> 
					<input type="text"  class="form-control" id="title" value="${board.title }" name="title"> 
			</div>
			
			<div class="mb-3 mt-3">
				<label for="content">판매글을 작성하세요!</label>
					<textarea class="form-control" rows="10" id="content" name="content">${board.content }</textarea>
			</div>
			
			
			
			
			<button type="submit" class="btn btn-success">등록</button>
			<button type="button" class="btn btn-success" onclick="location.href='/boardMarket/listAll';">목록</button>
		</div>
		</form>
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>