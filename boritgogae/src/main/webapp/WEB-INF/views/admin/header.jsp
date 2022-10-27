<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/summernote/summernote-bs4.min.css">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake"
				src="${pageContext.request.contextPath}/resources/admin/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				${pageContext.request.servletPath}
				<li class="nav-item d-none d-sm-inline-block"><a
					href="/admin/main" class="nav-link">Home</a></li>
				<c:choose>
					<c:when
						test="${pageContext.request.servletPath == '/WEB-INF/views/admin/member.jsp'}">
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/member" class="nav-link">회원 목록</a></li>
					</c:when>
					<c:when
						test="${pageContext.request.servletPath == '/WEB-INF/views/admin/newMember.jsp'}">
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/member" class="nav-link">회원 목록</a></li>
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/member/new" class="nav-link">신규 가입 회원</a></li>
					</c:when>
					<c:when
						test="${pageContext.request.servletPath == '/WEB-INF/views/admin/delMember.jsp' }">
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/member" class="nav-link">회원 목록</a></li>
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/member/delMember" class="nav-link">탈퇴한 회원</a></li>
					</c:when>
					<c:when
						test="${pageContext.request.servletPath == '/WEB-INF/views/admin/coupon.jsp' }">
						<li class="nav-item d-none d-sm-inline-block"><a
							href="/admin/coupon" class="nav-link">쿠폰관리</a></li>
					</c:when>
				</c:choose>
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Messages Dropdown Menu -->
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-comments"></i>
						<span class="badge badge-danger navbar-badge">3</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img
									src="${pageContext.request.contextPath}/resources/admin/dist/img/user1-128x128.jpg"
									alt="User Avatar" class="img-size-50 mr-3 img-circle">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Brad Diesel <span class="float-right text-sm text-danger"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">Call me whenever you can...</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img
									src="${pageContext.request.contextPath}/resources/admin/dist/img/user8-128x128.jpg"
									alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										John Pierce <span class="float-right text-sm text-muted"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">I got your message bro</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img
									src="${pageContext.request.contextPath}/resources/admin/dist/img/user3-128x128.jpg"
									alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Nora Silvester <span class="float-right text-sm text-warning"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">The subject goes here</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Messages</a>
					</div></li>
				<!-- Notifications Dropdown Menu -->
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
						class="badge badge-warning navbar-badge">15</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-item dropdown-header">15
							Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-envelope mr-2"></i> 4 new messages <span
							class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-users mr-2"></i> 8 friend requests <span
							class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
							3 new reports <span class="float-right text-muted text-sm">2
								days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Notifications</a>
					</div></li>
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>
		<!-- /.navbar -->

		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="/" class="brand-link"> <img
				src="${pageContext.request.contextPath}/resources/admin/dist/img/AdminLTELogo.png"
				alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
				style="opacity: .8"> <span
				class="brand-text font-weight-light">보릿고개</span>
			</a>

			<!-- ì¢ì¸¡ ì¬ì´ëë° -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img
							src="${pageContext.request.contextPath}/resources/admin/dist/img/user2-160x160.jpg"
							class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block">님, 환영합니다!</a>
					</div>
				</div>

				<!-- SidebarSearch Form -->
				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search"
							placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
                 with font-awesome or any other icon font library -->
						<li class="nav-item"><a href="/admin/main"
							class="nav-link active">
								<p>대시보드</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-shopping-cart"></i>
								<p>상품 관리</p> <i class="right fas fa-angle-left"></i>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/layout/top-nav.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>상품 리스트</p> <!-- 상품 리스트, 상품 수정, 상품 삭제, 재고 관리 -->
								</a></li>
								<li class="nav-item"><a
									href="pages/layout/top-nav-sidebar.html" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>상품 등록</p>
								</a></li>
								<li class="nav-item"><a href="${contextPath }/admin/orders"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>주문 조회</p> <!-- 주문 조회, 주문 수정 -->
								</a></li>
								<li class="nav-item"><a
									href="pages/layout/fixed-sidebar.html" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>배송 조회</p> <!-- 배송 등록 -->
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-users"></i>
								<p>
									사용자 관리 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/admin/member"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>회원 목록</p>
								</a></li>
								<li class="nav-item"><a href="/admin/member/new"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>신규 가입 회원</p>
								</a></li>
								<li class="nav-item"><a href="/admin/member/delMembers"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>탈퇴 회원 목록</p>
								</a></li>
								<li class="nav-item"><a href="/admin/coupon"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>쿠폰 관리</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-chart-pie"></i>
								<p>
									통계 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/charts/chartjs.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>사용자 통계</p>
								</a></li>
								<li class="nav-item"><a href="pages/charts/flot.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>상품 통계</p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-table"></i>
								<p>
									컨텐츠 관리 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/tables/simple.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>게시판</p>
								</a></li>
								<li class="nav-item"><a href="pages/tables/data.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>1:1 문의</p>
								</a></li>
								<li class="nav-item"><a href="pages/tables/jsgrid.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p></p>
								</a></li>
							</ul></li>
						<li class="nav-item"><a href="pages/calendar.html"
							class="nav-link"> <i class="nav-icon far fa-calendar-alt"></i>
								<p>일정 관리</p>
						</a></li>
					</ul>

				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>


	</div>


	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/jquery/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<!-- Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- ChartJS -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/chart.js/Chart.min.js"></script>
	<!-- Sparkline -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/sparklines/sparkline.js"></script>
	<!-- JQVMap -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/jqvmap/jquery.vmap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<!-- jQuery Knob Chart -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/moment/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Summernote -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/dist/js/adminlte.js"></script>
	<!-- AdminLTE for demo purposes -->
	<!--<script src="${pageContext.request.contextPath}/resources/admin/dist/js/demo.js"></script> -->
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!--<script src="${pageContext.request.contextPath}/resources/admin/dist/js/pages/dashboard.js"></script> -->
</body>

</html>