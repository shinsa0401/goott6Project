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
<title>보릿고개 | 회원관리</title>

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
						<div class="col-sm-6">
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/main">Home</a></li>
								<li class="breadcrumb-item active">회원 관리</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- Main content -->
				<section class="content">
					<div class="container-fluid">
						<div class="row">

							<div class="col-12">
								<div class="card card-danger">
									<div class="card-header">
										<h3 class="card-title">탈퇴한 회원 목록</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>아이디</th>
													<th>탈퇴 사유</th>
													<th>탈퇴 날짜</th>
													<th>탈퇴 코드</th>
												</tr>
												
											</thead>
											<tbody>
											<c:forEach items="${deleteMember }" var="member">
												<tr>
													<td>${member.memberId }</td>
													<td>${member.deleteWhyMemo }</td>
													<td>${member.deleteDate }</td>
													<td>${member.deleteCode }</td>
												</tr>
											</c:forEach>
											</tbody>
											
										</table>
									</div>
									<!-- /.card-body -->
								</div>
								<!-- /.card -->
							</div>
							
							<div class="col-12">
								<div class="card card-info">
									<div class="card-header">
										<h3 class="card-title">탈퇴 사유 목록</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>탈퇴 코드</th>
													<th>탈퇴 사유</th>
												</tr>
												
											</thead>
											<tbody>
											<c:forEach items="${deleteReasons }" var="deleteReason">
												<tr>
													<td>${deleteReason.deleteCode }</td>
													<td>${deleteReason.deleteWhy }</td>
												</tr>
											</c:forEach>
											</tbody>
											
										</table>
									</div>
									<!-- /.card-body -->
								</div>
								<!-- /.card -->
							</div>
						</div>
						<!-- /.row -->
					</div>
					<!-- /.container-fluid -->
				</section>
				<!-- /.content -->
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
