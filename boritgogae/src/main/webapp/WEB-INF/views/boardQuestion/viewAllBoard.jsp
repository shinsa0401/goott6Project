<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<title>글전체보기</title>
<script>



	// 글 상세페이지
	function viewBoard(no) {
		location.href = "/board/question/view?no=" + no;
	}
	
	// 검색 유효성 검사
	function validate() {
		let result = false;
		let st = document.getElementById("searchType").value;
		let sw = document.getElementById("searchWord").value;
		
		if (st != "" || sw != "") {
			result = true;
		} else {
			alert("검색대상과 검색어를 바르게 입력하세요!");
			// 모달창 띄워서 처리
			location.href = "/board/question?pageNo=1";
			
		}
	}
	
	
	// 제목에 댓글개수 출력
	function replyCount(bno) {
		let url = "/reply/count"
		$.ajax({
	        url: url, // 데이터 송수신될 주소
	        type: "post", // 통신 방식(get, post)
			data: { "bno" : bno },
	        dataType: "text", // 수신 받을 데이터 타입
	        success: function (data) { // 통신이 성공했을 때 호출되는 callback함수
	            // data : 성공했을 때 전달되는 데이터
	            console.log(data);
	            
	        },
	        error: function (e) {
				console.log(e);
			}
		});
	}
</script>
<style>
	
	.container {
		text-align: center;
		position: relative;
		
	}
	
	#paging {
		display: inline-block;
		font-weight: bold;
	}

	#searchBar {
	 	position: relative;
		width: 370px;
		margin: 10px;
		float: right;
	}
	
	#btns {
		text-align: right;
		margin-right: 10px;
		margin-top: 50px;
	}
	
	#searchType {
		width: 400px;
	}

	#boardTitle {
		text-align: left;
	}
	
	#viewAllBoard #viewTable {
		text-align: center;
	}
	
	#headTr {
		background-color: #7FAD39;
	}
	
	#titleTr {
		width: 500px;
	}
	
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container mt-3">
		<h2 id="boardTitle">질문 게시판</h2>
		
		
		<div id="searchBar">
			<form action="/board/question" method="get">
				<select name="searchType" id="searchType">
					<option class="option" value=""> 검색대상 선택 </option>
					<option class="option" value="writer">작성자</option>
					<option class="option" value="title">제목</option>
					<option class="option" value="content">본문</option>
				</select> 
				<input type="text" name="searchWord" id="searchWord" />
				<button type="submit" onclick="return validate();">검색</button>
			</form>
		</div>
		
		
		<div id="viewAllBoard">
			<table id="viewTable" class="table table-hover">
				<tr id="headTr" class="text-white">
					<th>번호</th>
					<th id="titleTr">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<!-- <th>좋아요</th> -->
					<th>작성일</th>
				</tr>
				<c:forEach var="board" items="${boardLst}">
				<tr onclick="viewBoard(${board.no});">
					<th>${board.no }</th>
					<th>
					<c:choose>
						<c:when test="${board.replyCount != 0}">
							${board.title } (${board.replyCount})
						</c:when>
						<c:otherwise>
							${board.title } 
						</c:otherwise>
					</c:choose>
					</th>
					<th>${board.writer }</th>
					<th>${board.readCount }</th>
					<!-- <th>${board.likeCount }</th> -->
					<th>
						<fmt:formatDate value="${board.writtenDate }" 
						pattern="yyyy-MM-dd HH:mm" />
					</th>
				</tr>
				</c:forEach>
			</table>
		</div>
		
		
		<div id="btns">
			<button type="button" class="btn text-white" style="background-color: #7FAD39;"
				onclick="location.href='/board/question/write';">새글작성</button>
			<button type="button" class="btn btn-info"
				onclick="location.href='/board/question?pageNo=1';">처음으로</button>
		</div>
		
		<div id="paging">
			<ul class="pagination">
				<c:if test="${param.pageNo == null || param.pageNo >= 1}">
					<c:choose>
						<c:when
							test="${param.searchType == '' or param.searchWord == '' }">
							<li class="page-item" >
								<c:if test="${param.pageNo > 1}">
									<a class="page-link" style="color: #336600;" href="/board/question?pageNo=1">맨앞</a>
								</c:if>
								<c:if test="${param.pageNo <= 1}">
									<a class="page-link" style="color: grey;">맨앞</a>
								</c:if>
								<c:if test="${param.pageNo == null}">
									<a class="page-link" style="color: grey;">맨앞</a>
								</c:if>
							</li>
							
							<li class="page-item">
								<c:if test="${param.pageNo > 1}">
									<a class="page-link" style="color: #336600;" href="/board/question?pageNo=${param.pageNo - 1}">이전</a>
								</c:if>
								<c:if test="${param.pageNo <= 1}">
									<a class="page-link" style="color: grey;">이전</a>
								</c:if>	
								<c:if test="${param.pageNo == null}">
									<a class="page-link" style="color: grey;">이전</a>
								</c:if>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item" >
								<c:if test="${param.pageNo > 1}">
									<a class="page-link" style="color: #336600;" href="/board/question?pageNo=1&searchType=${param.searchType}&searchWord=${param.searchWord}">맨앞</a>
								</c:if>
								<c:if test="${param.pageNo <= 1}">
									<a class="page-link" style="color: grey;">맨앞</a>
								</c:if>
								<c:if test="${param.pageNo == null}">
									<a class="page-link" style="color: grey;">맨앞</a>
								</c:if>
							</li>

							<li class="page-item">
								<c:if test="${param.pageNo > 1}">
									<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${param.pageNo - 1}&searchType=${param.searchType}&searchWord=${param.searchWord}">이전</a>
								</c:if>
								<c:if test="${param.pageNo <= 1}">
									<a class="page-link" style="color: grey;">이전</a>
								</c:if>
								<c:if test="${param.pageNo == null}">
									<a class="page-link" style="color: grey;">이전</a>
								</c:if>
							</li>
						</c:otherwise>
					</c:choose>
				</c:if>

				<!-- 번호 부분 -->
				<c:forEach var="i" begin="${pagingInfo.startNumOfCurPagingBlock}"
					end="${pagingInfo.endNumOfCurPagingBlock}" step="1"> 
					<c:choose>
						<c:when
							test="${param.searchType == '' and param.searchWord == ''}">
							<c:if test="${i < pagingInfo.totalPage + 1}">
								<li class="page-item"><a class="page-link number active"
								 style="color: #336600;" href="/board/question?pageNo=${i }">${i }</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
						
							
							<li class="page-item">
								<a class="page-link active" style="color: #336600;"
								href="/board/question?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
							
							
						</c:otherwise>
					</c:choose>
				</c:forEach>


				
					<c:choose>
						<c:when test="${param.searchType == '' or param.searchWord == '' }">
							<li class="page-item">
								<c:if test="${param.pageNo < pagingInfo.totalPage }">
									<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${param.pageNo + 1}">다음</a>
								</c:if>
								<c:if test="${param.pageNo >= pagingInfo.totalPage }">
									<a class="page-link" style="color: grey;">다음</a>
								</c:if>	
								<c:if test="${param.pageNo == null }">
									<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${param.pageNo + 1}">다음</a>
								</c:if>	
							</li>

							<li class="page-item">
								<c:if test="${param.pageNo < pagingInfo.totalPage }">
									<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${pagingInfo.totalPage }">맨끝</a>
								</c:if>
								<c:if test="${param.pageNo >= pagingInfo.totalPage }">
									<a class="page-link" style="color: grey;">맨끝</a>
								</c:if>
								<c:if test="${param.pageNo == null }">
									<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${pagingInfo.totalPage }">맨끝</a>
								</c:if>	
							</li>
						</c:when>

						<c:otherwise>
							<li class="page-item">
							
							<a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${param.pageNo + 1}&searchType=${param.searchType}&searchWord=${param.searchWord}">다음</a></li>

							<li class="page-item"><a class="page-link" style="color: #336600;"
								href="/board/question?pageNo=${pagingInfo.totalPage }&searchType=${param.searchType}&searchWord=${param.searchWord}">맨끝</a></li>
						</c:otherwise>
					</c:choose>
				

			</ul>
		</div>
		
	</div>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
