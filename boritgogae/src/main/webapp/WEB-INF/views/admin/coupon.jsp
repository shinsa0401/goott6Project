<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
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
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">쿠폰 등록</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<div class="form-group">
								<label for="couponName">쿠폰명</label> <input type="text"
									class="form-control col-sm-4" id="couponName" name="couponName"
									placeholder="생성할 쿠폰 이름을 입력해주세요">
							</div>
							<div class="form-group">
								<label for="couponDiscount">쿠폰 할인율</label>
								<div class="mb-3 row">
									<input type="number" class="form-control col-sm-4"
										name="couponDiscount" id="couponDiscount"
										placeholder="생성할 쿠폰의 할인율을 입력해주세요">
									<div class="col-sm-6">
										<span class="coupon">%</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="couponUseDate">사용 기한</label>
								<div class="mb-3 row">
									<input type="number" class="form-control col-sm-4"
										name="couponUseDate" id="couponUseDate"
										placeholder="생성할 쿠폰의 할인율을 입력해주세요">
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
					
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">쿠폰 목록</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
						
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>쿠 폰 명</th>
										<th>할 인 률</th>
										<th>사 용 기 한</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${couponList }" var="coupon">
										<tr>
											<td>${coupon.couponName }</td>
											
											<td>${coupon.couponDiscount * 100 } %</td>
											<td>${coupon.couponUseDate } 일</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
						</div>
						<!-- /.card-body -->
					</div>
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
</body>
</html>