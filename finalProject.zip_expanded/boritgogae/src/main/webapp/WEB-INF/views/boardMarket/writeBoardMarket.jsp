<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeBoardMarket</title>
<script>
	
</script>
</head>
<body>

	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
	<jsp:include page="../header.jsp"></jsp:include>

	<form action="/action_page.php">
		<div class="mb-3 mt-3">
			<label for="price">판매 가격</label> <input type="text"   class="form-control" placeholder="판매 상품명을 입력하세요"> 
				<label for="price">판매 가격</label> 
					<input type="text" class="form-control">
			<label for="comment">판매글을 작성하세요!</label>
			<textarea class="form-control" rows="5" id="comment" name="text"></textarea>
		</div>
		<button type="submit" class="btn btn-success">등록</button>
	</form>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>