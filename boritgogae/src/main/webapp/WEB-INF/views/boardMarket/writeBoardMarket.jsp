<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeBoardMarket</title>
<script>


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
		
		<div claa="button" style="text-align: center;">
			<button type="submit" class="btn btn-success">등록</button>
			<button type="button" class="btn btn-success" onclick="writeCancle();">취소</button>
		</div>
	</form>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>