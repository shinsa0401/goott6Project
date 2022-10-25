<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보릿고개 | 상품 관리</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/notice/js/commonJS.js"></script>
<script type="text/javascript">

	let pageNo = getParameter("pageNo");

	if(pageNo < 1 || pageNo > ${pagingInfo.totalPage }) {
		location.href="/admin/product?pageNo=1";
	}
	
	$(function() {
		for(let i = ${pagingInfo.startNumOfCurPagingBlock }; i <= ${pagingInfo.endNumOfCurPagingBlock }; i++) {
			if(pageNo == document.getElementById(i).innerHTML) {
				$("#" + i).parent('li').addClass("active");
			}
		}
		
	});
	

</script>
<style type="text/css">
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
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-left">
								<li class="breadcrumb-item active" style="margin-left: 8px;">총
									<b style="color: #dc3545;">${prodCnt }</b> 개의 상품이 있습니다.
								</li>
							</ol>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/main">Home</a></li>
								<li class="breadcrumb-item active">상품 관리</li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="container-fluid">

					<!--  상품 목록 시작 -->
					<div class="card card-info">
						<div class="card-header">
							<h3 class="card-title">상품 목록</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">

							<table id="prodTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>상품 코드</th>
										<th>상품 이름</th>
										<th>상품 가격</th>
										<th>상품 재고</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${prodList }" var="product">
										<tr id="coupons" onclick="modiModal('');">
											<td>${product.prodNo }</td>
										<c:forEach items="${prodImgList}" var="prodImg">
											<c:if test="${product.prodNo == prodImg.prodNo}">
												<td><img src="${prodImg.originalFile}" style="max-height: 50px; max-width: 50px;">  ${product.prodName }</td>
											</c:if>
											
										</c:forEach>
												<td><fmt:formatNumber type="number"
														maxFractionDigits="3" value="${product.prodPrice }" /> 원</td>
												<td>${product.prodQuantity }개</td>
											</tr>
										</c:forEach>
									
								</tbody>

							</table>
						</div>
						<!-- /.card-body -->
					</div>

					<div id="paging">
						<ul class="pagination justify-content-center">
							<c:if test="${pagingInfo.startNumOfCurPagingBlock != 1 }">
								<li class="page-item"><a class="page-link"
									href="/admin/product?pageNo=1"><<</a></li>
								<li class="page-item"><a class="page-link"
									href="/admin/product?pageNo=${pagingInfo.startNumOfCurPagingBlock -1 }">이전</a></li>
							</c:if>

							<c:choose>
								<c:when
									test="${pagingInfo.endNumOfCurPagingBlock > pagingInfo.totalPage }">
									<c:forEach var="i"
										begin="${pagingInfo.startNumOfCurPagingBlock }"
										end="${pagingInfo.totalPage }" step="1">
										<li class="page-item"><a class="page-link" id="${i }"
											href="/admin/product?pageNo=${i }">${i }</a></li>
									</c:forEach>
								</c:when>

								<c:otherwise>
									<c:forEach var="i"
										begin="${pagingInfo.startNumOfCurPagingBlock }"
										end="${pagingInfo.endNumOfCurPagingBlock }" step="1">
										<li class="page-item"><a class="page-link" id="${i }"
											href="/admin/product?pageNo=${i }">${i }</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<!--  -->
							<c:if
								test="${pagingInfo.endNumOfCurPagingBlock < pagingInfo.totalPage }">
								<li class="page-item"><a class="page-link"
									href="/admin/product?pageNo=${pagingInfo.endNumOfCurPagingBlock +1 }">다음</a></li>

								<li class="page-item"><a class="page-link"
									href="/admin/product?pageNo=${pagingInfo.totalPage }">>></a></li>
							</c:if>

						</ul>
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

	<div class="modal" id="modiModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="modiTitle">쿠폰 수정</h4>
					<button type="button" class="btn-close close closeModal"
						data-bs-dismiss="modal">X</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-group">
						<label for="modiCouponName">쿠폰명</label> <input type="text"
							class="form-control col-sm-8" id="modiCouponName"
							name="modiCouponName" placeholder="수정할 쿠폰 이름을 입력해주세요">
					</div>
					<div class="form-group">
						<label for="modiCouponDiscount">쿠폰 할인율</label>
						<div class="mb-3 row">
							<input type="number" class="form-control col-sm-8"
								name="couponDiscount" id="modiCouponDiscount"
								placeholder="수정할 쿠폰의 할인율을 입력해주세요">
							<div class="col-sm-2">
								<span class="coupon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="modiCouponUseDate">사용 기한</label>
						<div class="mb-3 row">
							<input type="number" class="form-control col-sm-8"
								name="modiCouponUseDate" id="modiCouponUseDate"
								placeholder="수정할 쿠폰의 사용기한을 입력해주세요">
							<div class="col-sm-2">
								<span class="coupon">일</span>
							</div>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal" onclick="modifyCoupon();">쿠폰 수정</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="deleteCoupon();">쿠폰 삭제</button>
				</div>

			</div>
		</div>
	</div>

</body>
</html>