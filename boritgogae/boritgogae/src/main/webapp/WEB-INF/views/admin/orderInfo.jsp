<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보릿고개 | 주문 조회</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function() {
    console.log("test");
    let pageNo = ${pageNo};
	let pageNoTotal = ${param.pageNo};
	let pageGo = ${pi.totalPage};

	if (pageNoTotal > pageGo) {
		console.log(pageNo);
		console.log(pageNoTotal);
		location.href = "/admin/orders?pageNo="
				+ pageGo;
	} else if (pageNoTotal < 1) {
		location.href = "/admin/orders?pageNo=1";
	} else {
		console.log(pageNo);
		console.log(pageNoTotal);
		console.log(pageGo);
	}
});
	
	function getDetailOrder(orderNo){
		console.log(orderNo);
		let url ="/admin/orders/detailOrderInfo?orderNo="+orderNo;
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data.order);
				parsingJson(data.order);
			},
			error : function(e) {
				console.log(e);
			}
		});
		
	}
	
	function parsingJson(data){
		
		let output = "";
		let orderNo = 0;
		console.log(data);
		console.log(data[0].memberId);
		$.each(data, function(i, e) {
				orderNo = e.initialOrderDetailNo;
				output += "<div class='col-12'><h4>주문 상세내역</h4><div class='post'><div class='user-block'>";
				output += "<img class='img-circle img-bordered-sm' src='"+ e.originalFile +"' alt='"+ e.prodNo +"'>";
				output += "<span class='username'> <a href='#'>"+ e.prodNo +"</a></span>";
				output += "<span class='description'>배송상태 : "+ e.deliveryStatus +"</span></div>";
				output += "<span class='description'>주문갯수 : "+ e.qty +"</span></div>";
				output += "</div></div>";
				
			});
		$('#'+orderNo).html(output);
		let output2 = "";
		output2 += "<div class='card'><div class='card-header'><h3 class='card-title'><i class='ion ion-clipboard mr-1'></i> 관리자님의 승인이 필요합니다</h3>";
		$.each(data, function(i, e) {
			if(e.deliveryStatus == '교환요청' || e.deliveryStatus == '반품요청' || e.deliveryStatus == '취소' || e.deliveryStatus == '결제완료' || e.deliveryStatus == '배송전'){
				console.log(e);
				output2 += "<div class='card-body'><ul class='todo-list col-12 col-md-12 col-lg-12 order-1 order-md-2' data-widget='todo-list'><li>";
				output2 += "<span class='text'>주문상세번호 : "+ e.orderDetailNo +" ,";
				output2 += "주문갯수 : "+ e.qty +"</span> <small class='badge badge-danger'>";
				output2 += "<i class='far fa-clock'></i> "+ e.deliveryStatus +"</small></li>";
				output2 += "</ul></div>";
			}
		});
		output2 += "</div></div>";
		output2 += "<div class='col-lg-2 col-2'><div class='small-box bg-warning'><div class='inner'><h3>"+ data[0].memberId +"</h3>";
		output2 += "<p>memberInfo</p></div><div class='icon'><i class='fas fa-user-plus'></i></div>";
		output2 += "<a href='/admin/member/detail?memberId="+ data[0].memberId +"' class='small-box-footer'> More info <i class='fas fa-arrow-circle-right'></i></a></div></div>";
		
		
		//console.log(output);
		$('.'+orderNo).html(output2);
	}
	
	function callModal() {
		console.log('이거 작동하긴함?');
		
		$("#modal-xl").show();
	}
	
	function closeModal(){
		$("#modal-xl").hide();
	}
	
</script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<jsp:include page="header.jsp"></jsp:include>

	<!-- The Modal -->
	<div class="modal" id="modal-xl">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">승인이 필요한 주문들</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						onclick="closeModal();"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!-- /.card-header -->
					<c:forEach var="todo" items="${todo }">
						<div class="card-body">
							<ul class="todo-list" data-widget="todo-list">
								<li><span class="text">주문상세번호 : ${todo.orderDetailNo },
										주문갯수 : ${todo.qty }</span> <small class="badge badge-danger"><i
										class="far fa-clock"></i> ${todo.deliveryStatus }</small></li>
							</ul>
						</div>
					</c:forEach>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="closeModal();">Close</button>
				</div>

			</div>
		</div>
	</div>
	<div class="wrapper">
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>Project Detail</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Project Detail</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
				<!-- new -->
				<div class="row">
					<div class="col-lg-3 col-6">
						<!-- small card -->
						<div class="small-box bg-info">
							<div class="inner">
								<h3>${count }</h3>

								<p>모든 주문 조회</p>
							</div>
							<div class="icon">
								<i class="fas fa-shopping-cart"></i>
							</div>
							<a href="/admin/orders" class="small-box-footer"> More info <i
								class="fas fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>

					<!-- new -->
					<div class="col-lg-3 col-6">
						<!-- small card -->
						<div class="small-box bg-warning">
							<div class="inner">
								<h3>${adminAllowOrders }</h3>

								<p>승인이 필요한 주문들</p>
							</div>
							<div class="icon">
								<i class="fas fa-user-plus"></i>
							</div>
							<div class="small-box-footer" onclick="callModal();">
								More info <i class="fas fa-arrow-circle-right"></i>
							</div>
						</div>
					</div>
					<!-- end -->

					<!-- new -->
					<div class="col-lg-3 col-6">
						<!-- small card -->
						<div class="small-box bg-success">
							<div class="inner">
								<h3>
									53<sup style="font-size: 20px">%</sup>
								</h3>

								<p>이번 달 매출</p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="#" class="small-box-footer"> More info <i
								class="fas fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- new ned -->
				</div>
				<!-- new ned -->


			</section>
			<!-- 주문 리스트 -->
			<c:forEach var="order" items="${orderList }">
				<section class="content">
					<!-- Default box -->
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">주문번호 : ${order.orderNo }&nbsp;&nbsp;&nbsp;&nbsp;주문자
								: ${order.name }&nbsp;&nbsp;&nbsp;&nbsp; phone :
								${order.phoneNumber }</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse" title="Collapse">
									<i class="fas fa-plus"
										onclick="getDetailOrder(${order.orderNo});"></i>
								</button>
								<button type="button" class="btn btn-tool"
									data-card-widget="remove" title="Remove">
									<i class="fas fa-times"></i>
								</button>
							</div>
						</div>
						<div class="card-body" style="display: none;">
							<div class="row">
								<div class="col-12 col-md-12 col-lg-12 order-2 order-md-1">
									<div class="row">
										<div class="col-12 col-sm-4">
											<div class="info-box bg-light">
												<div class="info-box-content">
													<span class="info-box-text text-center text-muted">주문
														번호</span> <span
														class="info-box-number text-center text-muted mb-0">${order.orderNo }</span>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-4">
											<div class="info-box bg-light">
												<div class="info-box-content">
													<span class="info-box-text text-center text-muted">주문자</span>
													<span class="info-box-number text-center text-muted mb-0">${order.name }</span>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-4">
											<div class="info-box bg-light">
												<div class="info-box-content">
													<span class="info-box-text text-center text-muted">주문
														금액</span> <span
														class="info-box-number text-center text-muted mb-0">${order.prodTotalPrice }</span>
												</div>
											</div>
										</div>
									</div>
									<!-- 여기에 파싱해야함 -->
									<div class="row" id="${order.orderNo }"></div>
								</div>
								<div
									class="col-12 col-md-12 col-lg-12 order-1 order-md-2 ${order.orderNo }">
									<!-- to do list -->

									<!-- todolist end -->

									<!-- new -->
									<div class="row">
										<div class="col-lg-2 col-2">
											<!-- small card -->
											<div class="small-box bg-warning">
												<div class="inner">
													<!-- 누적 주문갯수 -->
													<h3>44</h3>
													<p>memberInfo</p>
												</div>
												<div class="icon">
													<i class="fas fa-user-plus"></i>
												</div>
												<!-- 회원의 정보로 ㄱㄱ -->
												<a href="/admin/member/detail?memberId=${order.memberId }"
													class="small-box-footer"> More info <i
													class="fas fa-arrow-circle-right"></i>
												</a>
											</div>
										</div>
									</div>
									<!-- new ned -->

								</div>
							</div>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->

				</section>
				<!-- /.content -->
			</c:forEach>
			<!-- paging -->
			<ul class="pagination">
				<c:if test="${param.pageNo > 1 }">
					<li class="page-item"><a class="page-link" href="/admin/orders?pageNo=${param.pageNo - 1 }">Previous</a></li>
				</c:if>
				
				<c:forEach var="i" begin="${pi.startNumOfCurPagingBlock}"
					end="${pi.endNumOfCurPagingBlock }" step="1">
						<c:if test="${i < pi.totalPage+1 and i == param.pageNo}">
							<li class="page-item active"><a class="page-link" href="/admin/orders?pageNo=${i}">${i }</a></li>
						</c:if>
						<c:if test="${i < pi.totalPage+1 and i != param.pageNo}">
							<li class="page-item"><a class="page-link" href="/admin/orders?pageNo=${i}">${i }</a></li>
						</c:if>
				</c:forEach>
				<c:if test="${param.pageNo < pi.totalPage or param.pageNo==null}">
					<li class="page-item"><a class="page-link" href="/admin/orders?pageNo=${param.pageNo + 1 }">Next</a></li>
				</c:if>
			</ul>

		</div>
		<!-- /.content-wrapper -->



	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>