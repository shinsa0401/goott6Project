<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAll</title>
<script>

	//글 상세 보기
	function viewContent(no) {
		location.href="/boardMarket/viewContent?no="+no;
	}
	
	//검색어
	function valiDate() {
		let result = false;
		
		let sWord = document.getElementById("searchWord").value;
		let sType = document.getElementById("searchType").value;
		
		if(sWord != "" && sType !=""){
			result = true;
		}else{
			alert("검색결과가 없습니다");
		}
	}
	
</script>

</head>
<body>
	
	<jsp:include page="../header.jsp"></jsp:include>
	
	<h3 style="font: bold; text-align: center; text-decoration: underline;" >장터 게시판</h3>
		<div class="container">
		
			<div class="container mt-3">
				
				<p>개인 장터입니다. 개인 간 금전거래에 유의하세요!</p>
		
		<!-- 검색 버튼 -->		
		<div id="search">
			<form action="/boardMarket/listAll" method="get">
				<select name="searchType" id="searchType">
					<option value="">검색</option>
					<option value="writer">글쓴이</option>
					<option value="title">제목</option>
					<option value="content">본문</option>
				</select>
				<input type="text" name="searchWord" id="searchWord"/>
				<button type="submit" onclick="return valiDate();">검색</button>
			</form>
		</div>	
		<!-- ------------ -->	
		<!-- 글 목록 -->
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${boardList }">
						<tr onclick="viewContent(${board.no });" >
							<td>${board.no }</td>
							<td>${board.title }</td>
							<td>${board.writer }</td>
							<td>${board.writtenDate }</td>
							<td>${board.readCount }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</div>
			
			<div class="button" style="text-align: center; ">
			 	<button type="submit" class="site-btn" onclick="location.href='/boardMarket/write';">글쓰기</button>
			
			</div>
			
			
			<div id="paging" >
				 <ul class="pagination" >
				 
				 	<c:if test="${param.pageNo > 1 }">
				    	<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=1">--</a></li>
				    	
				    	<c:choose>
				    		
				    		<c:when test="${param.searchType=='' and param.searchWord=='' }">
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${param.pageNo-1 }">Previous</a></li>
				    		</c:when>
				    		<c:otherwise>
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${param.pageNo-1 }&searchType=${param.searchType}&searchWord=${param.searchWord}">Previous</a></li>
				    		</c:otherwise>
				    		
				    	</c:choose>
				    </c:if>
				    
				    <c:forEach var="i" begin="${paging.startNumOfCurPagingBlock }" end="${paging.endNumOfCurPagingBlock }" step="1" >
				    
				    	<c:choose>
	
				    		<c:when test="${param.searchType=='' and param.searchWord=='' }">
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${i }">${i }</a></li>
				    		</c:when>
				    		<c:otherwise>
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
				    		</c:otherwise>
				    		
				    	</c:choose>
				    </c:forEach>
				    
				    <c:if test="${param.pageNo < paging.totalPage }">
				    	<c:choose>
				    		
				    		<c:when test="${param.searchType=='' and param.searchWord=='' }">
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${param.pageNo+1 }">next</a></li>
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${paging.totalPage }">>></a></li>
				    		</c:when>
				    		<c:otherwise>
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${param.pageNo+1 }&searchType=${param.searchType}&searchWord=${param.searchWord}">next</a></li>
				    			<li class="page-item"><a class="page-link" href="/boardMarket/listAll?pageNo=${paging.totalPage }&searchType=${param.searchType}&searchWord=${param.searchWord}">>></a></li>
				    		
				    		</c:otherwise>
				    	</c:choose>
				    </c:if>
				  
				 </ul> 
			 </div>
				
					
	
	
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>