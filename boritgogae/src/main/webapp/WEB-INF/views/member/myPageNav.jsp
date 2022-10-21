<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPageNav</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".dropdown-toggle").dropdown();
	});

	// 회원혜택을 보여주는 메서드.
	function showUserBenefit() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userPointBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		
		$("#userBenefitBox").show();
		let url = "/member/myPage/benefit"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>등급 및 쿠폰혜택</h3>";
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>등급</th><th>누적구매금액</th><th>적립포인트</th></tr></thead><tbody>";
				$.each(data.gradeList, function(i, e){
					if(e.grade != null){
						output += "<tr>";
						output += "<td>"+e.grade+"</td>";
						output += "<td>"+e.gradeValue+"</td>";
						output += "<td>"+e.reservePoint+"</td>";
						output += "</tr>";
					}
				});
				output += "<tr><td></td><td></td><td></td></tr>";
				output += "<tr><th>쿠폰발급조건</th><th>할인율</th><th>사용기한</th></tr>";			
				$.each(data.couponList, function(i, e){
					if(e.couponName != null){
						output += "<tr>";
						output += "<td>"+e.couponName+"</td>";
						output += "<td>"+e.couponDiscount+"</td>";
						output += "<td>"+e.couponUseDate+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userBenefitBox").html(output);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	// 회원의 포인트 내역을 보여주는 메서드.
	function showUserPoint() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		
		// div 보여주기
		$("#userPointBox").show();
		let url = "/member/myPage/userPoint"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
			
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>보유 포인트 : "+ data.pointNow +"</h3>"
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>구분</th><th>일자</th><th>금액</th><th>관련주문번호</th></tr></thead><tbody>";
				$.each(data.pointList, function(i, e){
					if(e.memberId != null){
						output += "<tr>";
						if(e.pointNo ==  '1'){
							output += "<td>회원가입</td>";
						}else if(e.pointNo ==  '2'){
							output += "<td>구매사용</td>";
						}else if(e.pointNo ==  '3'){
							output += "<td>구매적립</td>";
						}else if(e.pointNo ==  '4') {
							output += "<td>이벤트적립</td>";
						}
						let pointSavedate = new Date(e.pointSaveDate);
						let formatPointSavedate = formatDate(pointSavedate);
						output += "<td>"+formatPointSavedate+"</td>";
						output += "<td>"+e.pointHistory +"</td>";
						output += "<td>"+e.orderNo+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userPointBox").html(output);
			},
			
			error : function(e) {
				console.log(e);
			}
		});
	}
	

	// 회원의 쿠폰 내역을 보여주는 메서드.
	function showUserCoupon() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userPointBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		
		// div 보여주기
		$("#userCouponBox").show();
		let url = "/member/myPage/userCoupon"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>보유쿠폰 : " + data.couponHaveList.length + "</h3>"
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>보유쿠폰명</th><th>사용일자</th><th>발급일자</th><th>발급사유</th><th>관련주문번호</th></tr></thead><tbody>";
				$.each(data.couponHaveList, function(i, e){
					if(e.couponName != null){
						output += "<tr>";
						output += "<td>"+e.couponName+"</td>";
						if(e.useDate == null){
							output += "<td>-</td>";
						}else{
							let date = new Date(e.useDate);
							let formatDate = formatDate(date);
							output += "<td>"+formatDate+"</td>";
						}
						let issueDate = new Date(e.issueDate);
						let formatIssueDate = formatDate(issueDate);
						output += "<td>"+formatIssueDate+"</td>";
						output += "<td>"+e.couponWhy+"</td>";
						output += "<td>"+e.orderNo+"</td>";
						output += "</tr>";
					}
				});
				output += "<tr><td></td><td></td><td></td><td></td><td></td></tr>";
				output += "<tr><th>사용쿠폰명</th><th>사용일자</th><th>발급일자</th><th>발급사유</th><th>관련주문번호</th></tr></thead><tbody>";		
				$.each(data.couponUsedList, function(i, e){
					if(e.couponName != null){
						output += "<tr>";
						output += "<td>"+e.couponName+"</td>";
						let useDate = new Date(e.useDate);
						let formatUseDate = formatDate(useDate);
						output += "<td>"+formatUseDate+"</td>";
						let issueDate = new Date(e.issueDate);
						let formatIssueDate = formatDate(issueDate);
						output += "<td>"+formatIssueDate+"</td>";
						output += "<td>"+e.couponWhy+"</td>";
						output += "<td>"+e.orderNo+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userCouponBox").html(output);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	// 회원이 쓴 글 내역을 보여주는 메서드.
	function showUserBoard() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userReplyBox").hide();
		$("#userPointBox").hide();
		
		// div 보여주기
		$("#userBoardBox").show();
		let url = "/member/myPage/userBoard"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>내가 작성한 글</h3>"
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>게시판명</th><th>제목</th><th>작성일</th><th>조회수</th></tr></thead><tbody>";
				$.each(data.userBoardList, function(i, e){
					if(e.writer != null){
						output += "<tr>";
						output += "<td>"+e.board+"</td>";
						output += "<td>"+e.title+"</td>";
						let writtenDate = new Date(e.writtenDate);
						let formatWrittenDate = formatDate(writtenDate);
						output += "<td>"+formatWrittenDate+"</td>";
						output += "<td>"+e.readCount+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userBoardBox").html(output);
			},
			
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	// 회원이 쓴 댓글 내역을 보여주는 메서드.
	function showUserReply() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		
		// div 보여주기
		$("#userReplyBox").show();
		let url = "/member/myPage/userReply"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>내가 작성한 댓글</h3>"
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>게시판명</th><th>내용</th><th>작성일</th></tr></thead><tbody>";
				$.each(data.userReplyList, function(i, e){
					if(e.memberId != null){
						output += "<tr>";
						output += "<td>"+e.board+"</td>";
						output += "<td>"+e.contents+"</td>";
						let writtenTime = new Date(e.writtenTime);
						let formatWrittenTime = formatDate(writtenTime);
						output += "<td>"+formatWrittenTime+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userReplyBox").html(output);
			},
			
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	
	// 회원이 쓸 수 있는 후기와 쓴 후기 내역을 보여주는 메서드.
	function showUserReview() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		
		// div 보여주기
		$("#userReviewBox").show();
		let url = "/member/myPage/userReview"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				output += "<br></br>";
				output += "<h3 style='color:#7FAD39;'>내가 작성한 리뷰</h3>"
				output += "<br></br>";
				output += "<table class='table'>";
				output += "<thead class='table-success'>";
				output += "<table class='table'>";
				output += "<tr><th>주문번호</th><th>상품명</th><th>제목</th><th>작성일</th><th>추천수</th></tr></thead><tbody>";
				$.each(data.userReviewList, function(i, e){
					if(e.writer != null){
						output += "<tr>";
						output += "<td>"+e.orderDetailNo+"</td>";
						output += "<td>"+e.content+"</td>";
						output += "<td>"+e.title+"</td>";
						let writtenDate = new Date(e.writtenDate);
						let formatwrittenDate = formatDate(writtenDate);
						output += "<td>"+formatwrittenDate+"</td>";
						output += "<td>"+e.likeCnt+"</td>";
						output += "</tr>";
					}
				});
				output += "</tbody></table>";
				$("#userReviewBox").html(output);
			},
			
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	
	// 날짜 변환 함수
	function formatDate(date){
		let year = date.getFullYear();
		let month = ('0' + (date.getMonth() + 1)).slice(-2);
		let day = ('0' + date.getDate()).slice(-2);
		let hours = ('0' + date.getHours()).slice(-2); 
		let minutes = ('0' + date.getMinutes()).slice(-2);
		let seconds = ('0' + date.getSeconds()).slice(-2); 
		let timeString = year + '-' + month  + '-' + day + '&nbsp' + hours + ':' + minutes  + ':' + seconds;
		return timeString;
	}
</script>
<style type="text/css">
</style>
</head>
<body>

	<div class="container mt-3" style="height: 1000px;">
		<ul class="nav nav-tabs">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown">내 정보</a>
				<ul class="dropdown-menu">
					<!--  -->
					<li><a class="dropdown-item" href="#">회원정보수정</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserBoard()">내가 쓴 글 보기</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserReply()">내가 쓴 댓글 보기</a></li>
				</ul></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown">쇼핑</a>
				<ul class="dropdown-menu">
					<!--  -->
					<li><a class="dropdown-item" href="#">주문내역</a></li>
					<!-- 완. 무진씨 링크 받기 -->
					<li><a class="dropdown-item" href="#">관심상품</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserReview()">작성후기</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserCoupon()">내 쿠폰</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserPoint()">포인트 내역</a></li>
				</ul></li>
			<!--  -->
			<li class="nav-item"><a class="nav-link"
				href="javascript:showUserBenefit();" style="color: black">회원혜택</a></li>
			</li>
		</ul>
		<div id="contentsBox">
			<div id="userBenefitBox" style="display: none;"></div>
			<div id="userPointBox" style="display: none;"></div>
			<div id="userCouponBox" style="display: none;"></div>
			<div id="userBoardBox" style="display: none;"></div>
			<div id="userReplyBox" style="display: none;"></div>
			<div id="userReviewBox" style="display: none;"></div>
		</div>
	</div>
</body>
</html>