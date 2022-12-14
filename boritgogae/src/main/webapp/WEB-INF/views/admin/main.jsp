<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>보릿고개 | 관리자페이지</title>
<script
	src="${pageContext.request.contextPath}/resources/admin/plugins/jquery/jquery.min.js"></script>
<!-- ChartJS -->
<script
	src="${pageContext.request.contextPath}/resources/admin/plugins/chart.js/Chart.min.js"></script>
<script>
	$(document).ready(function() {
		let likeDonutChartCanvas = $('#likeDonutChart').get(0).getContext('2d');
		let likeDonutData = {		
			labels : [ 
				<c:forEach items="${topLikeCountList }" var="topLikeCount">
					'${topLikeCount.prodName}',
				</c:forEach> ],
			datasets : [ {
				data : [ 
					<c:forEach items="${topLikeCountList }" var="topLikeCount">
						'${topLikeCount.likeCount}',
					</c:forEach> ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		};
		let readDonutChartCanvas = $('#readDonutChart').get(0).getContext('2d');
		let readDonutData = {		
			labels : [ 
				<c:forEach items="${topReadCountList }" var="topReadCount">
					'${topReadCount.prodName}',
				</c:forEach> ],
			datasets : [ {
				data : [ 
					<c:forEach items="${topReadCountList }" var="topReadCount">
						'${topReadCount.readCount}',
					</c:forEach> ],
				backgroundColor : [ '#f56954', '#00a65a', '#f39c12', '#00c0ef',
						'#3c8dbc' ],
			} ]
		};
		let donutOptions = {
			maintainAspectRatio : false,
			responsive : true,
		};
		
		new Chart(likeDonutChartCanvas, {
		      type: 'doughnut',
		      data: likeDonutData,
		      options: donutOptions
		    });
		new Chart(readDonutChartCanvas, {
		      type: 'doughnut',
		      data: readDonutData,
		      options: donutOptions
		    });
	});
	
</script>

</head>
<jsp:include page="header.jsp">
	<jsp:param name="member" value="${members }" />
</jsp:include>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">대시보드</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/main">Home</a></li>
								<li class="breadcrumb-item active">대시보드</li>
							</ol>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<div class="col-lg-12 col-12">
							<!-- small box -->
							<div class="small-box bg-secondary">
								<div class="inner">
									<h3>${logInMemberCount } 명</h3>
									<p>일일 접속자 수</p> 
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
							</div>
						</div>

						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-info">
								<div class="inner">
									<h3>${fn:length(getNewOrders)}</h3>
									<p>새로운 주문</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>

						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-success">
								<div class="inner">
									<h3>${fn:length(newMembers)}</h3>
									<p style="font-size: 14 px;">최근 30일간 가입한 회원</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="/admin/member/new" class="small-box-footer">상세 보기 <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>

						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-info">
								<div class="inner">
									<c:set value="0" var="memberLenth" />
									<c:forEach items="${members }" var="member">
										<c:if test="${member.isAdmin == 'N' }">
											<c:set var="memberLenth" value="${memberLenth + 1}" />
										</c:if>
									</c:forEach>
									<h3>${memberLenth}</h3>
									<p>총 회원 수</p>
								</div>
								<div class="icon">
									<i class="ion ion-person"></i>
								</div>
								<a href="/admin/member" class="small-box-footer">상세 보기 <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->

						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>${fn:length(lowestProduct)}</h3>
									<p>재고가 부족한 상품</p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>

					<!-- /.row -->
					<!-- Main row -->
					<div class="row">
						<!-- Left col -->
						<section class="col-lg-6 connectedSortable">
							<!-- Custom tabs (Charts with tabs)-->
							<!-- DONUT CHART -->
							<div class="card card-danger">
								<div class="card-header">
									<h3 class="card-title">조회수가 많은 상품 (상위 5개)</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<canvas id="readDonutChart"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->

						</section>
						<!-- /.Left col -->
						<!-- right col (We are only adding the ID to make the widgets sortable)-->
						<section class="col-lg-6 connectedSortable">
							<div class="card card-danger">
								<div class="card-header">
									<h3 class="card-title">좋아요가 많은 상품 (상위 5개)</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<canvas id="likeDonutChart"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</section>
						<!-- right col -->
					</div>
					<!-- /.row (main row) -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- ./wrapper -->
	<div class="info-box">
		<span class="info-box-icon bg-info"><i class="far fa-envelope"></i></span>

		<div class="info-box-content">
			<span class="info-box-text">Messages</span> <span
				class="info-box-number">1,410</span>
		</div>
		<!-- /.info-box-content -->
	</div>
	<!-- /.info-box -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>