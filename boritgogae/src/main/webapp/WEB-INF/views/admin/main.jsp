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
<!-- Bootstrap 4 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script
	src="${pageContext.request.contextPath}/resources/admin/plugins/chart.js/Chart.min.js"></script>
<!-- AdminLTE App -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/adminlte.min.js"></script>
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
<jsp:include page="header.jsp"></jsp:include>

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
						<section class="col-lg-7 connectedSortable">
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

							<!-- TO DO List -->
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">
										<i class="ion ion-clipboard mr-1"></i> To Do List
									</h3>

									<div class="card-tools">
										<ul class="pagination pagination-sm">
											<li class="page-item"><a href="#" class="page-link">&laquo;</a></li>
											<li class="page-item"><a href="#" class="page-link">1</a></li>
											<li class="page-item"><a href="#" class="page-link">2</a></li>
											<li class="page-item"><a href="#" class="page-link">3</a></li>
											<li class="page-item"><a href="#" class="page-link">&raquo;</a></li>
										</ul>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<ul class="todo-list" data-widget="todo-list">
										<li>
											<!-- drag handle --> <span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span> <!-- checkbox -->
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo1" id="todoCheck1">
												<label for="todoCheck1"></label>
											</div> <!-- todo text --> <span class="text">Design a nice
												theme</span> <!-- Emphasis label --> <small
											class="badge badge-danger"><i class="far fa-clock"></i>
												2 mins</small> <!-- General tools such as edit or delete-->
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div>
										</li>
										<li><span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo2" id="todoCheck2"
													checked> <label for="todoCheck2"></label>
											</div> <span class="text">Make the theme responsive</span> <small
											class="badge badge-info"><i class="far fa-clock"></i>
												4 hours</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo3" id="todoCheck3">
												<label for="todoCheck3"></label>
											</div> <span class="text">Let theme shine like a star</span> <small
											class="badge badge-warning"><i class="far fa-clock"></i>
												1 day</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo4" id="todoCheck4">
												<label for="todoCheck4"></label>
											</div> <span class="text">Let theme shine like a star</span> <small
											class="badge badge-success"><i class="far fa-clock"></i>
												3 days</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo5" id="todoCheck5">
												<label for="todoCheck5"></label>
											</div> <span class="text">Check your messages and
												notifications</span> <small class="badge badge-primary"><i
												class="far fa-clock"></i> 1 week</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i
												class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo6" id="todoCheck6">
												<label for="todoCheck6"></label>
											</div> <span class="text">Let theme shine like a star</span> <small
											class="badge badge-secondary"><i class="far fa-clock"></i>
												1 month</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
									</ul>
								</div>
								<!-- /.card-body -->
								<div class="card-footer clearfix">
									<button type="button" class="btn btn-primary float-right">
										<i class="fas fa-plus"></i> Add item
									</button>
								</div>
							</div>
							<!-- /.card -->
						</section>
						<!-- /.Left col -->
						<!-- right col (We are only adding the ID to make the widgets sortable)-->
						<section class="col-lg-5 connectedSortable">

							<!-- Map card -->
							<div class="card bg-gradient-primary" style="display: none;">
								<div class="card-header border-0">
									<h3 class="card-title">
										<i class="fas fa-map-marker-alt mr-1"></i> Visitors
									</h3>
									<!-- card tools -->
									<div class="card-tools">
										<button type="button" class="btn btn-primary btn-sm daterange"
											title="Date range">
											<i class="far fa-calendar-alt"></i>
										</button>
										<button type="button" class="btn btn-primary btn-sm"
											data-card-widget="collapse" title="Collapse">
											<i class="fas fa-minus"></i>
										</button>
									</div>
									<!-- /.card-tools -->
								</div>
								<div class="card-body">
									<div id="world-map" style="height: 250px; width: 100%;"></div>
								</div>
								<!-- /.card-body-->
								<div class="card-footer bg-transparent">
									<div class="row">
										<div class="col-4 text-center">
											<div id="sparkline-1"></div>
											<div class="text-white">Visitors</div>
										</div>
										<!-- ./col -->
										<div class="col-4 text-center">
											<div id="sparkline-2"></div>
											<div class="text-white">Online</div>
										</div>
										<!-- ./col -->
										<div class="col-4 text-center">
											<div id="sparkline-3"></div>
											<div class="text-white">Sales</div>
										</div>
										<!-- ./col -->
									</div>
									<!-- /.row -->
								</div>
							</div>
							<!-- /.card -->

							

							<!-- Calendar -->
							<div class="card bg-gradient-success">
								<div class="card-header border-0">

									<h3 class="card-title">
										<i class="far fa-calendar-alt"></i> Calendar
									</h3>
									<!-- tools card -->
									<div class="card-tools">
										<!-- button with a dropdown -->
										<div class="btn-group">
											<button type="button"
												class="btn btn-success btn-sm dropdown-toggle"
												data-toggle="dropdown" data-offset="-52">
												<i class="fas fa-bars"></i>
											</button>
											<div class="dropdown-menu" role="menu">
												<a href="#" class="dropdown-item">Add new event</a> <a
													href="#" class="dropdown-item">Clear events</a>
												<div class="dropdown-divider"></div>
												<a href="#" class="dropdown-item">View calendar</a>
											</div>
										</div>
										<button type="button" class="btn btn-success btn-sm"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-success btn-sm"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
									<!-- /. tools -->
								</div>
								<!-- /.card-header -->
								<div class="card-body pt-0">
									<!--The calendar -->
									<div id="calendar" style="width: 100%"></div>
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

	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>