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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".closeModal").click(function() {
			$("#statusModal").hide(100);
		});
	});

	function searchMember() {
		let url = "/admin/member/searchMember";
		let inputString = $("#searchMember").val();
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "json", // 수신할 데이터
			data : {
				"inputString" : inputString
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data.lenth > 0) {
					searchMemberOutput(data);
				} else {
					$("#status").html("검색하신 조건의 회원이 없습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	function searchMemberOutput(data) {
		let output = "";

		$.each(data,function(i, item) {
							let memberJoinDate = new Date(item.joinDate);

							output += '<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column">';
							output += '<div class="card bg-light d-flex flex-fill">';
							output += '<div class="card-header text-muted border-bottom-0"><b>아이디 : </b>'
									+ item.memberId + '</div>';
							output += '<div class="card-body pt-0"><div class="row"><div class="col-7">';
							output += '<h2 class="lead"><b>닉네임 : </b>'
									+ item.nickName
									+ '</h2><p class="text-muted text-sm">';
							output += '<b>이름 : </b>'
									+ item.memberName
									+ '</p><p class="text-muted text-sm"><b>등급 : </b>'
									+ item.grade + '</p>';
							output += '<p class="text-muted text-sm"><b>가입일 : </b>'
									+ memberJoinDate.getFullYear()
									+ '년 '
									+ (memberJoinDate.getMonth() + 1)
									+ '월 '
									+ memberJoinDate.getDate() + '일';
							output += '</p></div><div class="col-5 text-center">';
							output += '<img src="${pageContext.request.contextPath}/resources/' + item.memberImg + '" alt="user-avatar" class="img-circle img-fluid">';
							output += '</div></div></div><div class="card-footer"><div class="text-right"><a href="#" class="btn btn-sm btn-primary">';
							output += '<i class="fas fa-user"></i> 프로필 보기</a></div></div></div></div>';
						});

		$("#members").html(output);
	}

	function viewMemberProfile(memberId) {
		location.href = "/admin/member/detail?memberId=" + memberId;
	}
</script>

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
							<h1>회원 관리</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/main">Home</a></li>
								<li class="breadcrumb-item active">회원 관리</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
				<div class="row">
					<div class="col-md-8 offset-md-2">
						<div class="input-group">
							<input type="search" class="form-control form-control-lg"
								placeholder="검색할 회원을 입력해주세요" id="searchMember">
							<div class="input-group-append">
								<button type="button" class="btn btn-lg btn-default"
									onclick="searchMember();">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</section>


			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card card-solid">
					<div class="card-body pb-0">
						<div class="row" id="members">

							<c:forEach items="${members }" var="member">
								<c:if test="${member.memberId != 'admin' }">
									<div
										class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column">
										<div class="card bg-light d-flex flex-fill">
											<div class="card-header text-muted border-bottom-0">
												<b>아이디 : </b>${member.memberId }
											</div>
											<div class="card-body pt-0">
												<div class="row">
													<div class="col-7">
														<h2 class="lead">
															<b>닉네임 : </b>${member.nickName }</h2>
														<p class="text-muted text-sm">
															<b>이름 : </b>${member.memberName }</p>
														<p class="text-muted text-sm">
															<b>등급 : </b>${member.grade }</p>
														<p class="text-muted text-sm">
															<b>가입일 : </b>
															<fmt:formatDate value="${member.joinDate }"
																pattern="yyyy년 MM월 dd일" />
														</p>

													</div>
													<div class="col-5 text-center">
														<img
															src="${pageContext.request.contextPath}/resources/${member.memberImg }"
															alt="user-avatar" class="img-circle img-fluid">
													</div>
												</div>
											</div>
											<div class="card-footer">
												<div class="text-right">
													<a class="btn btn-sm btn-primary"
														onclick="viewMemberProfile('${member.memberId }');"> <i
														class="fas fa-user"></i> 프로필 보기
													</a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer"></div>
					<!-- /.card-footer -->
				</div>
				<!-- /.card -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


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

		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
