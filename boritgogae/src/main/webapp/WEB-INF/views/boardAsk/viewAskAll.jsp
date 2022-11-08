<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의게시판</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		let pageNo = request.getParameter("pageNo");
			//	if(pageNo < 1 || pageNo > ${pagingInfo.totalPage }) {
			//		alert(pageNo + "페이지는 존재하지 않습니다!");
	
			//		location.href="/board/listAll?pageNo=1";
			//	}
	}

	// 검색시 유효성 검사
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
.icon {
	width: 19px;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<!-- 게시판 배너 -->
		<div class="container p-5 my-5 text-white"
			style="background-color: #7FAD39;">
			<h3 style="font-weight: bold;">문의게시판</h3>
			<p>
				<br></br>비밀글이 아닌 글만 조회할 수 있습니다.
			</p>
			<p>FAQ조회 혹은 원하는 답변이 없을 시 문의를 남겨주세요.</p>
		</div>
		<!-- 글 목록 테이블 -->
		<div>
			<table class="table table-hover table-secondary"
				style="table-layout: fixed;">
				<colgroup>
					<col width="9%">
					<col width="12%">
					<col width="30%">
					<col width="9%">
					<col width="13%">	
					<col width="9%">
					<col width="9%">
					<col width="9%">
				</colgroup>
				<thead>
					<tr class="table-success">
						<th>글번호</th>
						<th>분류</th>
						<th>문의명</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>상태</th>
					</tr>
				</thead>
				<!-- 실제 글 목록 테이블 -->
				
				<!-- 여기는 FAQ 상위 3개 -->
				<tbody>
					<c:forEach var="board" items="${FAQList }">
						<tr class="table-info" onclick="viewAskBoard(${board.askBno})" >
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.askBno }</td>

						<c:forEach var="askCode" items="${askCodeList }">
							<c:if test="${board.askCode eq askCode.askCode }">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${askCode.askOption }</td>
							</c:if>
						</c:forEach>

						<c:choose>
							<c:when test="${board.isSecret eq 'Y'}">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><c:forEach
										var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach><img src="../../../resources/img/ask_lock.png" class="icon">
									비밀글입니다</td>
								<td></td>
							</c:when>

							<c:when test="${board.isDelete eq 'Y'}">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><c:forEach
										var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach><img src="../../../resources/img/ask_delete.png" class="icon">
									삭제된 글입니다.</td>
								<td></td>
							</c:when>
							<c:otherwise>
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">
									<c:forEach var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach> ${board.title }
								</td>
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.writer }</td>
							</c:otherwise>
						</c:choose>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><fmt:formatDate
								pattern="yyyy-MM-dd" value="${board.writtenDate }" /></td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.readCount }</td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.likeCount }</td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">
							<c:choose>
								<c:when test="${board.isFAQ eq 'Y'}">
										FAQ
								</c:when>
								<c:when test="${board.answerStatus eq 'Y'}">
										답변완료
								</c:when>
								<c:when test="${board.answerStatus eq 'A'}">
										답글
								</c:when>
								<c:when test="${board.answerStatus eq '-'}">
										-
								</c:when>
								<c:when test="${board.answerStatus eq 'N'}">
										답변대기
								</c:when>
							</c:choose>
						</td>
					</c:forEach>
					<tr style="height:15px;"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<!-- 여기서부터가 진짜 작성글들 -->					
					<c:forEach var="board" items="${askBoardList }">
						<c:choose>
							<c:when test="${board.isFAQ eq 'Y'}">
								<tr class="table-info" onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:when test="${board.answerStatus eq 'Y'}">
								<tr class="table-success"
									onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:when test="${board.answerStatus eq '-'}">
								<tr class="table-secondary"
									onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:when test="${board.answerStatus eq 'A'}">
								<tr class="table-secondary"
									onclick="viewAskBoard(${board.askBno})">
							</c:when>
							<c:otherwise>
								<tr  class="table-danger" onclick="viewAskBoard(${board.askBno})">
							</c:otherwise>
						</c:choose>
						
						
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.askBno }</td>

						<c:forEach var="askCode" items="${askCodeList }">
							<c:if test="${board.askCode eq askCode.askCode }">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${askCode.askOption }</td>
							</c:if>
						</c:forEach>

						<c:choose>
							<c:when test="${board.isSecret eq 'Y'}">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><c:forEach
										var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach><img src="../../../resources/img/ask_lock.png" class="icon">
									비밀글입니다</td>
								<td></td>
							</c:when>

							<c:when test="${board.isDelete eq 'Y'}">
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><c:forEach
										var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach><img src="../../../resources/img/ask_delete.png" class="icon">
									삭제된 글입니다.</td>
								<td></td>
							</c:when>
							<c:otherwise>
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">
									<c:forEach var="step" begin="1" end="${board.step }">
										<img src="../../../resources/img/ask_reply.png"
											style="max-width: 24px" ;/>
									</c:forEach> ${board.title }
								</td>
								<td
									style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.writer }</td>
							</c:otherwise>
						</c:choose>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;"><fmt:formatDate
								pattern="yyyy-MM-dd" value="${board.writtenDate }" /></td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.readCount }</td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">${board.likeCount }</td>
						<td
							style="overflow: hidden; text-overflow: ellipsis; word-break: break-all; white-space: nowrap;">
							<c:choose>
								<c:when test="${board.isFAQ eq 'Y'}">
										FAQ
								</c:when>
								<c:when test="${board.answerStatus eq 'Y'}">
										답변완료
								</c:when>
								<c:when test="${board.answerStatus eq 'A'}">
										답글
								</c:when>
								<c:when test="${board.answerStatus eq '-'}">
										-
								</c:when>
								<c:when test="${board.answerStatus eq 'N'}">
										답변대기
								</c:when>
							</c:choose>
						</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>

		<!-- 하단바 목록 -->
		<div class="row">
			<div class="col-sm-10">
				<div id="searchBar">
					<form action="/board/ask/list" method="get">
						<select name="searchType" id="searchType" class="form-select" >
							<option value="">검색옵션</option>
							<option value="writer">글쓴이</option>
							<option value="title">제목</option>
							<option value="contents">본문</option>
						</select>
						 <input type="text" name="searchWord" id="searchWord" style="height:42px;"/>
						<button type="submit" onclick="return validate();"style="height:42px;">검색</button>
					</form>

				</div>
			</div>
			<div class="col-sm-2">
				<div id="btns">
					<button type="button" class="btn btn-info float-right" 
						onclick="location.href='/board/ask/register';">문의하기</button>
				</div>

			</div>
		</div>


		<!-- 페이징 목록 -->
		<div id="paging">
			<ul class="pagination justify-content-center">
				<c:if test="${pagingInfo.startNumOfCurPagingBlock != 1 }">
					<li class="page-item"><a class="page-link"
						href="/board/ask/list?pageNo=1"><<</a></li>
					<c:choose>
						<c:when
							test="${param.searchType == '' and param.searchWord == '' }">
							<li class="page-item"><a class="page-link"
								href="/board/ask/list?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/board/ask/list?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }
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
										href="/board/ask/list?pageNo=${i }">${i }</a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask/list?pageNo=${i }">${i }</a></li>
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
										href="/board/ask/list?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="i"
									begin="${pagingInfo.startNumOfCurPagingBlock }"
									end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
									<li class="page-item"><a class="page-link"
										href="/board/ask/list?pageNo=${i }&searchType=${param.searchType}&searchWord=${param.searchWord}">${i }</a></li>
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
								href="/board/ask/list?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }">다음</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/board/ask/list?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }
							&searchType=${param.searchType}&searchWord=${param.searchWord}">다음</a></li>
						</c:otherwise>
					</c:choose>

					<li class="page-item"><a class="page-link"
						href="/board/ask/list?pageNo=${pagingInfo.totalPage }">>></a></li>
				</c:if>

			</ul>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>