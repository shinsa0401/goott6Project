<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">


</script>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	
	<h2 style="text-align: center;">글작성</h2>
	
	<form action="/boardFree/create" method="post"style ="padding: 100px;">
		<div class="mb-3 mt-3">
			<label for="title" class="form-label">글 제목:</label> <input
				type="title" class="form-control" id="title" name="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">글쓴이 :</label> <input
				type="writer" class="form-control" id="writer" name="writer">
		</div>


		<div class="mb-3">

			<label for="content">내용 :</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>

		<button type="button" class="btn btn-primary">취소</button>
		<button type="submit" class="btn btn-success">완료</button>

	</form>
	
	
	


	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>