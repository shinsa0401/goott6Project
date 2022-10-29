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
<title>보릿고개 | 회원관리</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
let delMemberId = "";

$(document).ready(function() {
	$(".closeModal").click(function() {
		$("#statusModal").hide(100);
		$("#deleteMemberModal").hide(100);
	});
});

function showDeleteModal(memberId) {
	$("#deleteModalStatus").html(memberId + " 회원을 삭제하시겠습니까?");
	delMemberId = memberId;
	$("#deleteMemberModal").show(200);
}

function ModalStatusOk() {
	location.reload();
	
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
												<tr onclick="showDeleteModal('${member.memberId }');">
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
