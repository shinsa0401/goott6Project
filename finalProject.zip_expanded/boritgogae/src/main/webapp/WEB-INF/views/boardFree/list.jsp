<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

	


	


	function detail(bno) {
		location.href ="/boardFree/detail?bno="+bno;
		
	}
</script>

<body>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
  <h2>게시판 전체 페이지</h2>
  <table class="table table-bordered">

      <tr>
        <th>글번호</th>
        <th>제 목</th>
        <th>글쓴이</th>
        <th>작성일</th>
        <th>좋아요</th>
      </tr>
      
      <c:forEach var="board" items="${boardList }">
      	<tr onclick="detail(${board.bno});">
      		<td>${board.bno }</td>
      		<td>${board.title }</td>
      		<td>${board.writer }</td>
      		<td>${board.createDate }</td>
      		<td>${board.likeCount }</td>
      		
      	</tr> 
      
      
      </c:forEach>
      
      
     
   
    
  
  </table>
   
</div>
<button type="button" class="btn btn-success" id="writerBoard"><a href="/boardFree/writer">글작성</button>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>