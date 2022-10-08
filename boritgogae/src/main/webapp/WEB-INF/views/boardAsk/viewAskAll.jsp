<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의게시판</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/commonJS.js"></script>
<script type="text/javascript">
	window.onload = function() {
		let pageNo = getParameter("pageNo");

		//	if(pageNo < 1 || pageNo > ${pagingInfo.totalPage }) {
		//		alert(pageNo + "페이지는 존재하지 않습니다!");

		//		location.href="/board/listAll?pageNo=1";
		//	}
	}

	function validate() {
		let result = false;
		let st = document.getElementById("searchType").value;
		let sw = document.getElementById("searchWord").value;

		if (st != "" || sw != "") {
			result = true;
		} else {
			alert("검색대상과 검색어를 바르게 입력하세요!");
		}
	}

	function viewAskBoard(no) {
		location.href = "/board/ask/view?no=" + no;
	}
</script>
<style type="text/css">
.test2 h3 {
	font-weight: bold;
}

.icon {
	width: 19px;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">

		<div class="container p-5 my-5 bg-success text-white test2">
			<h3>문의게시판</h3>
			<p>
				<br></br>비밀글이 아닌 글만 조회할 수 있습니다.
			</p>
			<p>FAQ 혹은 원하는 답변이 없을 시 문의를 남겨주세요.</p>
		</div>

		<div>
			<table class="table table-hover table-danger">
				<thead>
					<tr class="table-success">
						<th>글번호</th>
						<th>분류</th>
						<th>문의명</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>답변여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${askBoardList }">
						<c:choose>
							<c:when test="${board.isFAQ eq 'Y'}">
								<tr class="table-info" onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:when test="${board.answerStatus eq 'Y'}">
								<tr class="table-success"
									onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:when test="${board.writer eq 'admin'}">
								<tr class="table-secondary"
									onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:otherwise>
								<tr onclick="viewAskBoard(${board.askBno})">
							</c:otherwise>
						</c:choose>
						<td>${board.askBno }</td>

						<c:forEach var="askCode" items="${askCodeList }">
							<c:if test="${board.askCode eq askCode.askCode }">
								<td>${askCode.askOption }
							</c:if>
						</c:forEach>

						<c:choose>
							<c:when test="${board.isSecret eq 'Y'}">
								<td><img src="../../../resources/img/lock.png" class="icon">
									비밀글입니다</td>
								<td>-</td>
							</c:when>
							<c:otherwise>
								<td>${board.title }</td>
								<td>${board.writer }</td>
							</c:otherwise>
						</c:choose>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${board.writtenDate }" /></td>
						<td>${board.readCount }</td>
						<td>${board.likeCount }</td>
						<td>${board.answerStatus }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="searchBar">
			<form action="/board/ask" method="get">
				<select name="searchType" id="searchType">
					<option value="">검색옵션</option>
					<option value="writer">글쓴이</option>
					<option value="title">제목</option>
					<option value="contents">본문</option>
				</select> <input type="text" name="searchWord" id="searchWord" />
				<button type="submit" onclick="return validate();">검색</button>
			</form>
		</div>

		<div id="paging">
			<ul class="pagination justify-content-center">
				<c:if test="${pagingInfo.startNumOfCurPagingBlock != 1 }">
					<li class="page-item"><a class="page-link"
						href="/board/ask?pageNo=1"><<</a></li>
					<c:choose>
						<c:when
							test="${param.searchType == '' and param.searchWord == '' }">
							<li class="page-item"><a class="page-link"
								href="/board/ask?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/board/ask?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }
							&searchType=${param.searchType}&searchWord=${param.searchWord}">이전</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>


				<c:choose>
					<c:when
						test="${param.searchType == '' and param.searchWord == '' }">
						<c:choose>
							<c:when
								test="${pagingInfo.endNumOfCurPagingBlock > pagingInfo.totalPage }">
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.totalPage }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask?pageNo=${i }">${i }</a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask?pageNo=${i }">${i }</a></li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<c:choose>
							<c:when
								test="${pagingInfo.endNumOfCurPagingBlock > pagingInfo.totalPage }">
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.totalPage }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
								</c:forEach>
							</c:otherwise>

						</c:choose>
					</c:otherwise>

				</c:choose>

				<c:if
					test="${pagingInfo.endNumOfCurPagingBlock < pagingInfo.totalPage }">
					<c:choose>
						<c:when
							test="${param.searchType == '' and param.searchWord == '' }">
							<li class="page-item"><a class="page-link"
								href="/board/ask?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }">다음</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/board/ask?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }
							&searchType=${param.searchType}&searchWord=${param.searchWord}">다음</a></li>
						</c:otherwise>
					</c:choose>

					<li class="page-item"><a class="page-link"
						href="/board/ask?pageNo=${pagingInfo.totalPage }">>></a></li>
				</c:if>

			</ul>
		</div>

		<div id="btns">
			<button type="button" class="btn btn-info"
				onclick="location.href='/board/ask/register';">문의하기</button>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>