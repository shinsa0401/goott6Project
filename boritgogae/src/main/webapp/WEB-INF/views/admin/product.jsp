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
		
		$(".closeModal").click(function() {
			$("#statusModal").hide(100);
			$("#deleteProdModal").hide(100);
		});
	});
	
	
	function modiModal(prodImg, product) {
		product = product.split(",");
		let prodNo = product[0].split("=")[1];
		let prodName = product[1].split("=")[1];
		let brand = prodName.split(" ")[0];
		if (brand.indexOf("]") != -1) {
			brand = prodName.split(" ")[0].split("]")[1];
		}
		let prodQuantity = Number(product[2].split("=")[1]).toLocaleString();
		let prodPrice = Number(product[3].split("=")[1]).toLocaleString();
		let readCount = product[4].split("=")[1];
		let likeCount = product[5].split("=")[1];
		let prodPutDate = product[6].split("=")[1];
		
		let prodContent = [];
		
		
		
		<c:forEach var="productContents" items="${prodContentList}" >
			if(prodNo == '${productContents.prodNo}') {
				prodContent.push("${productContents.prodContent}");
			}
		</c:forEach>
		
		console.log(prodContent);
		
		console.log(product);
		console.log(prodNo, prodName, prodQuantity, prodPrice, readCount, likeCount, prodPutDate);
		
		let output = "";
		output += '<div class="modal" id="modal-xl"><div class="modal-dialog modal-xl"><div class="modal-content" style="max-height: 650px;">';
		output += '<div class="modal-header"><h4 class="modal-title">상품 정보</h4>';
		output += '<button type="button" class="close closeModal" onclick="modalClose();">';
		output += '<span aria-hidden="true">&times;</span></button></div>';
		output += '<div class="mx-auto prodInfo">각 정보를 클릭 시 수정할 수 있습니다. (상품코드, 브랜드 제외)</div>';
		output += '<div class="modal-body row" >';
		output += '<div class="col-6"><img src="' + prodImg + '" style="max-height: 400px; max-width: 400px;"/></div>';
		output += '<div class="col-6"><ul class="list-group list-group-flush"><li class="list-group-item">';
		output += '<div class="productDetail prodTitle">상품 코드</div><div class="productDetail dataTag" id="prodNo">' + prodNo + '</div></li>';
		output += '<li class="list-group-item"><div class="productDetail prodTitle">브 랜 드</div>';
		output += '<div class="productDetail dataTag">' + brand + '</div></li>';
		output += '<li class="list-group-item"><div class="productDetail prodTitle">상품 이름</div><div class="productDetail">';
		output += '<span class="dataTag" id="prodName" onclick="return changedTag(this);"> ' + prodName + '</span></div></li>';
		output += '<li class="list-group-item"><div class="productDetail prodTitle">상품 가격</div><div class="productDetail">';
		output += '<span class="dataTag" id="prodPrice" onclick="return changedTagFromNo(this);">' + prodPrice + ' </span><span>원</span></div></li>';
		output += '<li class="list-group-item"><div class="productDetail prodTitle">현재 재고</div><div class="productDetail">';
		output += '<span class="dataTag" id="prodQuantity" onclick="return changedTagFromNo(this);">' + prodQuantity + ' </span><span>개</span></div></li>';
		output += '</ul></div><div class="col-12" id="prodContent">';
		for(let prodCont in prodContent) {
			output += '<img src="' + prodContent[prodCont] + '" style="max-width: 600px;"/>';
		}
		output += '</div></div><div class="modal-footer">';
		output += '<button type="button" class="btn btn-danger" onclick="showDeleteProdModal();">삭제</button>';
		output += '<button type="button" class="btn btn-warning" onclick="modalClose();">닫기</button>';
		output += '<button type="button" class="btn btn-success" onclick="modifyProd();">수정</button>';
		output += '</div></div></div></div>';
		
		$("#modalDiv").html(output);
		modalShow();
		
	}
	
	function showDeleteProdModal() {
		$("#deleteModalStatus").html("상품을 삭제하시겠습니까?");
		$("#deleteProdModal").show(200);
	}
	
	function deleteProd() {
		
		let prodNo = $("#prodNo").html().trim();
		
		let url = "/admin/product/delete";
		
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : {"prodNo" : prodNo},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("상품이 삭제 되었습니다.");
					$("#deleteProdModal").hide(100);
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("상품 삭제가 실패되었습니다.");
					$("#deleteProdModal").hide(100);
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function modifyProd() {
		let prodQuantity = 0;
		let prodPrice = 0;
		let prodName = "";
		let prodNo = $("#prodNo").html().trim();
		if($("#prodQuantity").val() != "") {
			prodQuantity = $("#prodQuantity").val().trim();
		} else {
			prodQuantity = $("#prodQuantity").html().trim();
		}
		
		if($("#prodPrice").val() != "") {
			prodPrice = $("#prodPrice").val().trim().replace(",","");
		} else {
			prodPrice = $("#prodPrice").html().trim().replace(",","");
		}
		
		if($("#prodName").val() != "") {
			prodName = $("#prodName").val().trim();
		} else {
			prodName = $("#prodName").html().trim();
		}
		
		let sendData = JSON.stringify({
			"prodQuantity" : prodQuantity,
			"prodPrice" : prodPrice,
			"prodName" : prodName,
			"prodNo" : prodNo
		});

		let url = "/admin/product/modify";
		
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
					$("#status").html("상품 수정이 완료되었습니다.");
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("상품 수정에 실패하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
		
	}
	
	function ModalStatusOk() {
		location.reload();
	}
	
	function modalShow() {
		$("#modal-xl").show(200);
	}
	
	function modalClose() {
		$("#modal-xl").hide(100);
	}
	
	function changedTag(tags) {
		let prevData = $(tags).html();
		let dataId = $(tags).attr("id");
		$(tags).contents().unwrap().wrap( '<input type="text" class="form-control" id="' + dataId + '" value="'+ prevData +'">' );
	}
	
	function changedTagFromNo(tags) {
		let prevData = $(tags).html().trim().replace(",","");
		let dataId = $(tags).attr("id");
		$(tags).contents().unwrap().wrap( '<input type="number" class="form-control" id="' + dataId + '" value="'+ prevData +'">' );
	}

</script>
<style type="text/css">
.productDetail {
	margin-bottom: 3px;
}

.prodTitle {
	font-size: 16px;
	font-weight: bold;
	color: #6c757d;
}

.prodInfo {
	font-size: 14px;
	color: #6c757d;
}

.modal-body {
	overflow:auto;
}

.modal-body::-webkit-scrollbar {
	position: relative;
}
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
										<c:forEach items="${prodImgList}" var="prodImg">
											<c:if test="${product.prodNo == prodImg.prodNo}">
												<tr id="coupons"
													onclick="modiModal('${prodImg.originalFile}','${product }');">
													<td>${product.prodNo }</td>
													<td><img src="${prodImg.originalFile}"
														style="max-height: 50px; max-width: 50px;">
														${product.prodName }</td>
											</c:if>
										</c:forEach>
										<td><fmt:formatNumber type="number" maxFractionDigits="3"
												value="${product.prodPrice }" /> 원</td>
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

	<div id="modalDiv"></div>
	
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
		
		<div class="modal" id="deleteProdModal">
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
							data-bs-dismiss="modal" onclick="deleteProd();">확인</button>
						<button type="button" class="btn btn-danger closeModal"
							data-bs-dismiss="modal">취소</button>
					</div>

				</div>
			</div>
		</div>
</body>
</html>