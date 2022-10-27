<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보릿고개 | 주문 조회</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<jsp:include page="header.jsp"></jsp:include>
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
								<h3>150</h3>

								<p>모든 주문 조회</p>
							</div>
							<div class="icon">
								<i class="fas fa-shopping-cart"></i>
							</div>
							<a href="#" class="small-box-footer"> More info <i
								class="fas fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>

					<!-- new -->
					<div class="col-lg-3 col-6">
						<!-- small card -->
						<div class="small-box bg-warning">
							<div class="inner">
								<h3>44</h3>

								<p>승인이 필요한 주문들</p>
							</div>
							<div class="icon">
								<i class="fas fa-user-plus"></i>
							</div>
							<a href="#" class="small-box-footer"> More info <i
								class="fas fa-arrow-circle-right"></i>
							</a>
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

			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">주문번호 :</h3>

						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
							<button type="button" class="btn btn-tool"
								data-card-widget="remove" title="Remove">
								<i class="fas fa-times"></i>
							</button>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
								<div class="row">
									<div class="col-12 col-sm-4">
										<div class="info-box bg-light">
											<div class="info-box-content">
												<span class="info-box-text text-center text-muted">주문
													번호</span> <span
													class="info-box-number text-center text-muted mb-0">31</span>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-4">
										<div class="info-box bg-light">
											<div class="info-box-content">
												<span class="info-box-text text-center text-muted">주문자</span>
												<span class="info-box-number text-center text-muted mb-0">2000</span>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-4">
										<div class="info-box bg-light">
											<div class="info-box-content">
												<span class="info-box-text text-center text-muted">현재
													상태</span> <span
													class="info-box-number text-center text-muted mb-0">배송완료</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<h4>주문 상세내역</h4>
										<div class="post">
											<div class="user-block">
												<img class="img-circle img-bordered-sm"
													src="../../dist/img/user1-128x128.jpg" alt="user image">
												<span class="username"> <a href="#">Jonathan
														Burke Jr.</a>
												</span> <span class="description">Shared publicly - 7:45 PM
													today</span>
											</div>
											<!-- /.user-block -->
											<p>Lorem ipsum represents a long-held tradition for
												designers, typographers and the like. Some people hate it
												and argue for its demise, but others ignore.</p>

											<p>
												<a href="#" class="link-black text-sm"><i
													class="fas fa-link mr-1"></i> Demo File 1 v2</a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="col-12 col-md-12 col-lg-4 order-1 order-md-2">
								<!-- to do list -->
								<div class="card">
									<div class="card-header">
										<h3 class="card-title">
											<i class="ion ion-clipboard mr-1"></i> 관리자님의 승인이 필요합니다
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
													<input type="checkbox" value="" name="todo1"
														id="todoCheck1"> <label for="todoCheck1"></label>
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
													<input type="checkbox" value="" name="todo2"
														id="todoCheck2" checked> <label for="todoCheck2"></label>
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
													<input type="checkbox" value="" name="todo3"
														id="todoCheck3"> <label for="todoCheck3"></label>
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
													<input type="checkbox" value="" name="todo4"
														id="todoCheck4"> <label for="todoCheck4"></label>
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
													<input type="checkbox" value="" name="todo5"
														id="todoCheck5"> <label for="todoCheck5"></label>
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
													<input type="checkbox" value="" name="todo6"
														id="todoCheck6"> <label for="todoCheck6"></label>
												</div> <span class="text">Let theme shine like a star</span> <small
												class="badge badge-secondary"><i
													class="far fa-clock"></i> 1 month</small>
												<div class="tools">
													<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
												</div></li>
										</ul>
									</div>
								</div>
								<!-- todolist end -->

								<!-- new -->
								<div class="row">
									<div class="col-lg-3 col-6">
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
											<a href="/admin/member/detail?memberId=test123433" class="small-box-footer"> More info <i
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
		</div>
		<!-- /.content-wrapper -->

		<!-- TodoList -->

	</div>
	</div>
	</div>
	</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>