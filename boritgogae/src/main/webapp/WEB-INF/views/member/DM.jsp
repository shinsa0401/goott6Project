<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	
	
	<script type="text/javascript">
	
	
	

	$(document).ready(function() {
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
			else $("input[name=chk]").prop("checked", false);
		});

		$("input[name=chk]").click(function() {
			var total = $("input[name=chk]").length;
			var checked = $("input[name=chk]:checked").length;

			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
		
		
		
		
		
		
		
		
$("#delBtn").click(function(){ 
	
	
			let no = 0;
			let tdArr = new Array();
			//let checkbox = $("input[id=dmlist]:checked");
			$("input[name=chk]:checked").each(function () {
				no = $(this).attr("id");
				console.log(no);
				tdArr.push(no);
				
				
			});
			console.log(tdArr);
			
			$.ajax({
                type:'get',       // 요청 메서드
                url: '/member/delDM',  // 요청 URI
                headers : { "content-type": "application/json"}, // 요청 헤더
                data :{"tdArr":tdArr},
                // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success : function(result){
                	location.href ="/member/DM";
                  
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); 
			
			
		});

		
	});
	
	
	function dmdetail(no) {
		location.href ="/member/dmdetail?no="+no;
		
		
	}
	
	</script>
	
	
	<style type="text/css">
	.nice-select search-option {
	position: relative;
	display: block;
	width: 100%;
	height: 35px;
	padding-left: 10px;
	box-sizing: border-box;
	color: #009f47;
	font-size: 12px;
	text-align: left;
	line-height: 35px;
	text-decoration: none;
}

.search-input {
	width: 214px;
	height: 35px;
	padding-left: 13px;
	padding-right: 0;
	border: none;
	border-radius: 0;
	font-size: 12px;
	color: #979797;
	line-height: 37px;
	-webkit-appearance: none;
}
site-btn.{
position: relative;
    float: left;
    width: 36px;
    height: 37px;
    margin-left: -1px;
    border: 1px solid #239e36;
    border-left-width: 0;
    border-radius: 0;
    background-color: #28a93a;}
ul {
	list-style: none;
}
	
	</style>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>


<div class="container">
		<h2 style="text-align: center;">쪽지함</h2>

		<div>
			<div class="board-container">
				<div class="search-container">
					<form action="<c:url value="/member/DM"/>" class="search-form"
						method="get">
						<select class="search-option" name="option">
						
							<option value="A" ${option=='A' ? "selected" : ""}>모든 메세지</option>
							<option value="T" ${option=='T' ? "selected" : ""}>내 용</option>
							<option value="W" ${option=='W' ? "selected" : ""}>보낸사람</option>
						</select> <input type="text" name="keyword" class="search-input"
							type="text" value="${param.keyword}" placeholder="검색어를 입력해주세요">
						<input type="submit" class="site-btn" value="검색">
					</form>

				</div>
			</div>
		</div>

<button type="button" class="site-btn pull-right" id="delBtn"  >

삭제</button>





<table  class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					
					<th>보낸사람</th>
					<th>내 용</th>
					<th>날 짜</th>
					<th>
						<input type="checkbox" id="cbx_chkAll" />
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dm" items="${list }">
					<tr id="dmlist" onclick="dmdetail(${dm.no});" >
				
						<td>${dm.senderId }</td>
						<td>${dm.noteContent }</td>
						<td>${dm.sendDate }</td>
						<td>
							<input type="checkbox" name="chk" id="${dm.no }">
						</td>

					</tr>


				</c:forEach>
			</tbody>
		</table>
		
		
		
		</div>
		
		
		<div class="paging-container" style="text-align: center;">
		<div id="paging">

			<c:if test="${totalCnt==null || totalCnt==0}">
				<div>게시물이 없습니다.</div>
			</c:if>
			<c:if test="${totalCnt!=null && totalCnt!=0}">
				<c:if test="${ph.showPrev}">
					<a href="<c:url value="/member/DM?page=${ph.beginPage-1}"/>">&lt;</a>
				</c:if>

				<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
					<a href="<c:url value="/member/DM?page=${i}" />">${i}</a>
				</c:forEach>

				<c:if test="${ph.showNext}">
					<a href="<c:url value="/member/DM?page=${ph.endPage+1}"/>">&gt;</a>
				</c:if>
			</c:if>
		</div>
	</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>