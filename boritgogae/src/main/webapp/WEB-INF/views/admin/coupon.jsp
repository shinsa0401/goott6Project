<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보릿고개 | 쿠폰 관리</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	let couponName = "";
	let beforeCouponName = "";
	$(document).ready(function() {
		$(".closeModal").click(function() {
			$("#modiModal").hide(100);
			$("#statusModal").hide(100);
		});
	});
	
	function createCoupon() {
		let couponName = $("#couponName").val();
		let couponDiscount = $("#couponDiscount").val();
		let couponUseDate = $("#couponUseDate").val();

		let url = "/admin/coupon/create";
		let sendData = JSON.stringify({
			"couponName" : couponName,
			"couponDiscount" : couponDiscount,
			"couponUseDate" : couponUseDate
		});

		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : sendData,
			headers : {
				"content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST" // 구 버전의 웹 브라우저에서 (PUT / DELETE) 방식이 호환이 안되는 버전에서 호환 되도록
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("쿠폰 등록이 완료되었습니다.");
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("쿠폰 등록에 실패하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});

	}

	function deleteCoupon() {
		let url = "/admin/coupon/delete";
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : {
				"couponName" : couponName
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("쿠폰을 삭제하였습니다.");
					$("#modiModal").hide(100);
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("쿠폰 삭제에 실패하였습니다.");
					$("#modiModal").hide(100);
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	function modifyCoupon() {
		let couponName = $("#modiCouponName").val();
		let couponDiscount = $("#modiCouponDiscount").val();
		let couponUseDate = $("#modiCouponUseDate").val();
		let modiCouponName = beforeCouponName;

		let url = "/admin/coupon/modify";
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : {
				"couponName" : couponName,
				"couponDiscount" : couponDiscount,
				"couponUseDate" : couponUseDate,
				"modiCouponName" : modiCouponName
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("쿠폰 수정이 완료되었습니다.");
					$("#modiModal").hide(100);
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("쿠폰 수정에 실패하였습니다.");
					$("#modiModal").hide(100);
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	function modiModal(beforeName, beforeDiscount, beforeUseDate) {
		couponName = beforeName;
		beforeCouponName = beforeName;
		$("#modiModal").show(200);
		$("#modiCouponName").val(beforeName);
		$("#modiCouponDiscount").val(beforeDiscount);
		$("#modiCouponUseDate").val(beforeUseDate);
	}

	function sendCoupon() {
		let sendMemberId = $("#who").val();
		let sendCouponName = $("#what").val();
		let sendCouponWhy = $("#couponWhy").val();

		if (sendMemberId == "회원 전체") {
			sendMemberId = "all";
		}

		let sendData = JSON.stringify({
			"couponName" : sendCouponName,
			"memberId" : sendMemberId,
			"couponWhy" : sendCouponWhy
		});

		let url = "/admin/coupon/sendCoupon";
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : sendData,
			headers : {
				"content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST" // 구 버전의 웹 브라우저에서 (PUT / DELETE) 방식이 호환이 안되는 버전에서 호환 되도록
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("쿠폰 전송이 완료되었습니다.");
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("쿠폰 전송에 실패하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});

	}
</script>
<style type="text/css">
.coupon {
	position: relative;
	top: 7px;
	left: 2px;
}

.table-bordered {
	text-align: center;
}

#coupons :hover {
	cursor: pointer;
}
</style>
</head>

<body class="hold-transition sidebar-mini layout-fixed">

	<jsp:include page="header.jsp"></jsp:include>

	<!-- Site wrapper -->
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/main">Home</a></li>
								<li class="breadcrumb-item active">쿠폰 관리</li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="container-fluid">
					<!--  쿠폰 등록 시작 -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">쿠폰 생성</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<div class="form-group">
								<label for="couponName">쿠폰명</label> <input type="text"
									class="form-control col-sm-4" id="couponName" name="couponName"
									placeholder="쿠폰 이름">
							</div>
							<div class="form-group">
								<label for="couponDiscount">쿠폰 할인율</label>
								<div class="mb-3 row">
									<input type="number" class="form-control col-sm-4"
										name="couponDiscount" id="couponDiscount" placeholder="쿠폰 할인율">
									<div class="col-sm-6">
										<span class="coupon">%</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="couponUseDate">사용 기한</label>
								<div class="mb-3 row">
									<input type="number" class="form-control col-sm-4"
										name="couponUseDate" id="couponUseDate" placeholder="쿠폰 사용 기한">
									<div class="col-sm-6">
										<span class="coupon">일</span>
									</div>
								</div>
							</div>
						</div>
						<!-- /.card-body -->

						<div class="card-footer">
							<button type="button" class="btn btn-primary"
								onclick="createCoupon();">등록</button>
						</div>
					</div>
					<!--  쿠폰 등록 끝 -->

					<!--  쿠폰 전송 시작 -->
					<div class="card card-success">
						<div class="card-header">
							<h3 class="card-title">쿠폰 전송</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<span>전송할 회원</span>
							<div class="mb-3 row">
								<select id="who" class="form-select form-select-sm col-sm-4">
									<option id="all">회원 전체</option>
									<c:forEach items="${members }" var="member">
										<option id="${member.memberId }">${member.memberId }</option>
									</c:forEach>
								</select>
							</div>

							<span>전송할 쿠폰</span>
							<div class="mb-3 row">
								<select id="what" class="form-select form-select-sm col-sm-4">
									<c:forEach items="${couponList }" var="coupon">
										<option id="${coupon.couponName }">${coupon.couponName }</option>
									</c:forEach>
								</select>
							</div>

							<span>전송 사유</span>
							<div class="mb-3 row">
								<input type="text" class="form-control col-sm-4"
									name="couponWhy" id="couponWhy">
							</div>
						</div>
						<!-- /.card-body -->
						<div class="card-footer">
							<button type="button" class="btn btn-success"
								onclick="sendCoupon();">전송</button>
						</div>
					</div>
					<!--  쿠폰 전송 끝 -->

					<!--  쿠폰 목록 시작 -->
					<div class="card card-info">
						<div class="card-header">
							<h3 class="card-title">쿠폰 목록</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">

							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>쿠 폰 명</th>
										<th>할 인 률</th>
										<th>사 용 기 한</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${couponList }" var="coupon">
										<tr id="coupons"
											onclick="modiModal('${coupon.couponName }', '${coupon.couponDiscount * 100 }', '${coupon.couponUseDate }');">
											<td>${coupon.couponName }</td>
											<td>${coupon.couponDiscount * 100 }%</td>
											<td>${coupon.couponUseDate }일</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!--  쿠폰 목록 끝 -->
				</div>

			</section>

		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>


	<div class="modal" id="statusModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="status"></h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal" onclick="location.reload();">확인</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="modiModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="modiTitle">쿠폰 수정</h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-group">
						<label for="modiCouponName">쿠폰명</label> <input type="text"
							class="form-control col-sm-8" id="modiCouponName"
							name="modiCouponName" placeholder="수정할 쿠폰 이름을 입력해주세요">
					</div>
					<div class="form-group">
						<label for="modiCouponDiscount">쿠폰 할인율</label>
						<div class="mb-3 row">
							<input type="number" class="form-control col-sm-8"
								name="couponDiscount" id="modiCouponDiscount"
								placeholder="수정할 쿠폰의 할인율을 입력해주세요">
							<div class="col-sm-2">
								<span class="coupon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="modiCouponUseDate">사용 기한</label>
						<div class="mb-3 row">
							<input type="number" class="form-control col-sm-8"
								name="modiCouponUseDate" id="modiCouponUseDate"
								placeholder="수정할 쿠폰의 사용기한을 입력해주세요">
							<div class="col-sm-2">
								<span class="coupon">일</span>
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal" onclick="modifyCoupon();">쿠폰 수정</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="deleteCoupon();">쿠폰 삭제</button>
				</div>

			</div>
		</div>
	</div>

</body>
</html>