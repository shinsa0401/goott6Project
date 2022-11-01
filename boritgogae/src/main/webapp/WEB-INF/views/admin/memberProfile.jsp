<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>보릿고개 | 회원 프로필</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	let delMemberId = "${member.memberId }";

	$(document).ready(function() {
		$(".closeModal").click(function() {
			$("#statusModal").hide(100);
			$("#deleteMemberModal").hide(100);
		});
	});
	
	function modifyMember() {
		let memberId = $("#memberId").val();
		let memberPwd = $("#memberPwd").val();
		let nickName = $("#nickName").val();
		let memberName = $("#memberName").val();
		let phoneNumber = $("#phoneNumber").val();
		let memberEmail = $("#memberEmail").val();
		let url = "/admin/member/modify";

		let sendData = JSON.stringify({
			"memberId" : memberId,
			"memberPwd" : memberPwd,
			"nickName" : nickName,
			"memberName" : memberName,
			"phoneNumber" : phoneNumber,
			"memberEmail" : memberEmail
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
					$("#status").html("회원 수정이 완료되었습니다.");
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("회원 수정에 실패하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function showDeleteModal() {
		$("#deleteModalStatus").html(delMemberId + " 회원을 삭제하시겠습니까?");
		$("#deleteMemberModal").show(200);
	}
	
	function ModalStatusOk() {
		if(delMemberId =="") {
			location.href="/admin/member";
		} else {
			location.reload();
		}
		
	}
	
	function deleteMember() {
		$("#deleteMemberModal").hide(100);
		let url = "/admin/member/delete";
		
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : {"memberId" : delMemberId},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("회원을 삭제하였습니다.");
					delMemberId = "";
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("회원 삭제에 실패하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
</script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<jsp:include page="header.jsp"></jsp:include>
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
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">회원 상세</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-4">

							<!-- Profile Image -->
							<div class="card card-primary card-outline">
								<div class="card-body box-profile">
									<div class="text-center">
										<img class="profile-user-img img-fluid img-circle"
											src="${pageContext.request.contextPath}/resources/${member.memberImg }"
											alt="User profile picture">
									</div>

									<h5 class="profile-username text-center">닉네임</h5>
									<h3 class="profile-username text-center">${member.nickName }</h3>


									<ul class="list-group list-group-unbordered mb-3">
										<li class="list-group-item"><b>아이디 : </b> <a
											class="float-right">${member.memberId } </a></li>
										<li class="list-group-item"><b>이 름 : </b> <a
											class="float-right"> ${member.memberName } </a></li>
										<li class="list-group-item"><b>가입일 : </b> <a
											class="float-right"> <fmt:formatDate
													value="${member.joinDate }" pattern="yyyy년 MM월 dd일" />
										</a></li>
										<li class="list-group-item"><b>등급 : </b> <a
											class="float-right">${member.grade }</a></li>
										<li class="list-group-item"><b>총 구매액 : </b> <a
											class="float-right"> <fmt:formatNumber type="number"
													maxFractionDigits="3" value="${member.purchaseAmount }" />원
										</a></li>
										<li class="list-group-item"><b>적립 포인트 : </b> <a
											class="float-right"> <fmt:formatNumber type="number"
													maxFractionDigits="3" value="${member.memberPoint }" />포인트
										</a></li>
									</ul>
									<a class="btn btn-danger btn-block" onclick="showDeleteModal();"><b>회원 삭제</b></a>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->

							<!-- About Me Box -->
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">상세 정보</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<strong><i class="fas fa-birthday-cake mr-1"></i> 생년월일</strong>

									<p class="text-muted">
										<fmt:formatDate value="${member.birthDay }"
											pattern="yyyy년 MM월 dd일" />
									</p>

									<hr>

									<strong><i class="fas fa-mobile mr-1"></i> 연락처</strong>

									<p class="text-muted">
										<c:choose>
											<c:when test="${fn:length(member.phoneNumber) == 11}">
												<c:set var="first"
													value="${fn:substring(member.phoneNumber, 0, 3) }" />
												<c:set var="middle"
													value="${fn:substring(member.phoneNumber, 3, 7) }" />
												<c:set var="last"
													value="${fn:substring(member.phoneNumber, 7, 11) }" />
											${first } - ${middle } - ${last }
										</c:when>
											<c:otherwise>
												<c:set var="first"
													value="${fn:substring(member.phoneNumber, 0, 3) }" />
												<c:set var="middle"
													value="${fn:substring(member.phoneNumber, 3, 6) }" />
												<c:set var="last"
													value="${fn:substring(member.phoneNumber, 6, 10) }" />
											${first } - ${middle } - ${last }
										</c:otherwise>
										</c:choose>

									</p>

									<hr>

									<strong><i class="fas  fa-envelope mr-1"></i> 이메일</strong>

									<p class="text-muted">${member.memberEmail }</p>

								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
						<div class="col-md-8">
							<div class="card">
								<div class="card-header p-2">
									<ul class="nav nav-pills">
										<li class="nav-item"><a class="nav-link active"
											href="#coupon" data-toggle="tab">쿠폰</a></li>
										<li class="nav-item"><a class="nav-link" href="#address"
											data-toggle="tab">배송지 주소</a></li>
										<li class="nav-item"><a class="nav-link" href="#settings"
											data-toggle="tab">정보 수정</a></li>
									</ul>
								</div>
								<!-- /.card-header -->

								<div class="card-body">
									<div class="tab-content">
										<div class="active tab-pane" id="coupon">
											<!-- Post -->
											<div class="post">

												<h5 style="margin-left: 4px;">보유 쿠폰 및 사용 유무</h5>

												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>쿠 폰 명</th>
															<th>사 용 날 짜</th>
															<th>발 급 날 짜</th>
															<th>유 효 기 간</th>
															<th>증 정 사 유</th>
															<th>사 용 유 무</th>
														</tr>

													</thead>
													<tbody>
														<c:forEach items="${memberCoupon }" var="coupon">
															<tr id="coupons" style="font-size: 14px;">
																<c:choose>
																	<c:when test="${coupon.couponName != null }">
																		<td>${coupon.couponName }</td>
																	</c:when>
																	<c:otherwise>
																		<td>삭제된 쿠폰입니다.</td>
																	</c:otherwise>
																</c:choose>
																<td><fmt:formatDate value="${coupon.useDate }"
																		pattern="yyyy년 MM월 dd일" /></td>
																<td><fmt:formatDate value="${coupon.issueDate }"
																		pattern="yyyy년 MM월 dd일" /></td>
																<td><fmt:formatDate
																		value="${coupon.expirationDate }"
																		pattern="yyyy년 MM월 dd일" /></td>
																<td>${coupon.couponWhy }</td>
																<td><c:choose>
																		<c:when test="${coupon.orderNo != '0' }">
																			사용
																		</c:when>
																		<c:otherwise>
																			미사용
																		</c:otherwise>
																	</c:choose></td>
															</tr>
														</c:forEach>
													</tbody>

												</table>
											</div>
											<!-- /.post -->
										</div>
										<!-- /.tab-pane -->
										<div class="tab-pane" id="address">
											<h5 style="margin-left: 4px;">배송지</h5>
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th class="col-sm-8">주소</th>
														<th class="col-sm-4">우편번호</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${memberAddressList }"
														var="memberAddress">
														<tr id="addresses" style="font-size: 14px;">
															<td>${memberAddress.address }
																${memberAddress.detailAddress }</td>
															<td>${memberAddress.postCode }</td>
														</tr>
													</c:forEach>
												</tbody>

											</table>
										</div>
										<!-- /.tab-pane -->

										<div class="tab-pane" id="settings">
											<div class="form-group row">
												<label for="memberName" class="col-sm-2 col-form-label">이름</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="memberName"
														name="memberName" value="${member.memberName }">
												</div>
											</div>
											<div class="form-group row">
												<label for="nickName" class="col-sm-2 col-form-label">닉네임</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="nickName"
														name="nickName" value="${member.nickName }">
												</div>
											</div>
											<input type="hidden" class="form-control" id="memberId"
												name="memberId" value="${member.memberId }">
											<div class="form-group row">
												<label for="memberPwd" class="col-sm-2 col-form-label">비밀번호</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="memberPwd"
														name="memberPwd">
												</div>
											</div>
											<div class="form-group row">
												<label for="memberEmail" class="col-sm-2 col-form-label">이메일</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="memberEmail"
														name="memberEmail" value="${member.memberEmail }">
												</div>
											</div>
											<div class="form-group row">
												<label for="phoneNumber" class="col-sm-2 col-form-label">연락처</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="phoneNumber"
														name="phoneNumber" value="${member.phoneNumber }">
												</div>
											</div>
											<div class="form-group row">
												<div class="offset-sm-2 col-sm-10">
													<button type="button" class="btn btn-success"
														onclick="modifyMember();">정보 수정</button>
												</div>
											</div>
										</div>
										<!-- /.tab-pane -->
									</div>
									<!-- /.tab-content -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
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
							data-bs-dismiss="modal" onclick="ModalStatusOk();">확인</button>
					</div>

				</div>
			</div>
		</div>
		
		<div class="modal" id="deleteMemberModal">
			<div class="modal-dialog"> 
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="deleteModalStatus"></h4>
						<button type="button" class="btn-close close closeModal"
							data-bs-dismiss="modal">X</button>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							data-bs-dismiss="modal" onclick="deleteMember();">확인</button>
						<button type="button" class="btn btn-danger closeModal"
							data-bs-dismiss="modal">취소</button>
					</div>

				</div>
			</div>
		</div>
</body>
</html>
