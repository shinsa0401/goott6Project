<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	type="text/css">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Js Plugins -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>



  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  
<script type="text/javascript">

	//서브토탈을 구하여 화면에 출력하는 메서드
	function calSubtotal(obj, price) {
		$(obj).siblings("span").html($(obj).val() * price);
		
		let totalSubTotal = "";
		let subTotalLst = [];
		
		subTotalLst.push($(".orderProducts span").html());
		for(let i =0 ; i < subTotalLst.length; i++){
			totalSubTotal = eval(totalSubTotal + subTotalLst[i]);
		}
		
		$("#subTotal").html(totalSubTotal);
		$("#inputSubTotal").val(totalSubTotal);
	}

	//컨트롤러의 주문리스트에서 상품을 삭제하는 메서드
	function deleteProds(prodNo) {
		
		let url = "/order/deleteProdFromOrder";
		$.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
	         dataType : "text", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
			 data : {"prodNo": prodNo}, // 전송할 데이터
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	        	 console.log(data);
	        	 if(data == "delFail"){
		        	alert("상품이 정상적으로 삭제되지 않았습니다. 다시 시도해주세요.");
		        }else{
		        	$("#"+prodNo).html("");
		        }
	         }
	      });
	}
	
	//선택한 주소에 표시 남기는 메서드
	function selectAddr(addrNo) {
		let color = "";
		color= $("#addr"+addrNo).css("background-color");

		if(color == "rgb(127, 173, 57)"){
			$(".addrLst").css("background-color","white");
			$(".addrLst").css("color","black");
		}else{
			$(".addrLst").css("background-color","white");
			$(".addrLst").css("color","black");
			$("#addr"+addrNo).css("background-color","rgb(127, 173, 57)");
			$("#addr"+addrNo).css("color","white");
		}
	}
	
	//선택한 주소 input에 넣는 메서드
	function addrCheck() {
		let color = "";

		$(".addrLst").each(function() {
			color = $(this).css("background-color");
			if(color == "rgb(127, 173, 57)"){
				$("#address").attr("value", $(this).find("td").eq(0).html());
				$("#detailAddress").attr("value", $(this).find("td").eq(1).html());
				$("#name").attr("value", $(this).find("td").eq(2).html());
				$("#phoneNumber").attr("value", $(this).find("td").eq(3).html());
				$("#postCode").attr("value", $(this).find("td").eq(4).html());
				alert("!");
				deliveryOption();
				return false;
			}else{
				$("#address").attr("value", "");
				$("#detailAddress").attr("value", "");
				$("#postCode").attr("value", "");
			}
		});
	}
	

	//선택한 쿠폰에 표시 남기는 메서드
	function selectCoupon(coupon) {
		let color = "";
		color= $("#"+coupon).css("background-color");
		if(color == "rgb(127, 173, 57)"){
			$(".couponLst").css("background-color","white");
			$(".couponLst").css("color","black");
		}else{
			$(".couponLst").css("background-color","white");
			$(".couponLst").css("color","black");
			$("#"+coupon).css("background-color","rgb(127, 173, 57)");
			$("#"+coupon).css("color","white");
		}
	}
	
	//선택한 쿠폰을 input태그에 넣는 메서드
	function couponCheck() {

		let color = "";

		$(".couponLst").each(function() {
			let id = $(this).attr("id");
			color = $(this).css("background-color");
			if(color == "rgb(127, 173, 57)"){
				$("#coupon").attr("value", $(this).attr("id"));
				$("#couponSelect").html(""+$(this).attr("id")+"(" +$(this).find("td").eq(1).html()+")");
				return false;
			}else{
				$("#coupon").attr("value", "");
				$("#couponSelect").html("");
			}
		});

	}
	
	
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		let pop = window.open("/order/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$("#address").val(roadAddrPart1);
		$("#detailAddress").val(addrDetail);
		$("#postCode").val(zipNo);
}
	
	function deliveryOption() {
		let url = "/order/getDeliveryOption";
		
		let memberId = $("#memberId").val();
		let address = $("#address").val();
		let detailAddress = $("#detailAddress").val();
		
		let deliveryVo = {"memberId": memberId, "address":address, "detailAddress" : detailAddress};
		
		$.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
			 contentType : "application/json",
	         dataType : "text", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
			 data : JSON.stringify(deliveryVo), // 전송할 데이터
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	        	 console.log(data);
	        	 
	         }
	      });
	}

</script>

<title>주문페이지</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<h4>Billing Details</h4>
				<form action="#">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											이름<span>*</span>
										</p>
										<input type="text" id="name" name="name" value="${member.memberName }">
										<input type="hidden" id="memberId" name="memberId" value="${member.memberId }">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											전화번호<span>*</span>
										</p>
										<!-- 형식 통일하기 -->
										<input type="text" id="phoneNumber" name="phoneNumber" value="${member.phoneNumber }">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											주소<span>*</span>
										</p>
										<input type="text" placeholder="주소" class="checkout__input__add" readonly="readonly" class="col-lg-6" id="address" name="address"/>
									</div>
								</div>
								<div class="col-lg-6">
									<div style="position: absolute; top: 35%;">
										<input type="button" onClick="goPopup();" value="주소 검색하기" class="site-btn" style="" />
										<input type="button" value="내 주소 목록" class="site-btn" style="background-color: #96AD73;" data-toggle="modal" data-target="#addressModal" />
									</div>
										
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											상세주소<span>*</span>
										</p>
											<!-- 형식 통일하기 -->
										<input type="text" placeholder="건물이름/동호수" id="detailAddress" name="detailAddress" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											우편번호<span>*</span>
										</p>
											<!-- 형식 통일하기 -->
										<input type="text" placeholder="우편번호" id="postCode" name="postCode" />
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									비회원 이메일<span>*</span>
								</p>
								<input type="text" placeholder="이메일 주소를 입력해주세요">
							</div>
						<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											비회원 비밀번호<span>*</span>
										</p>
											<!-- 형식 통일하기 -->
										<input type="password"  id="guestPwd" name="guestPwd" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											비밀번호 확인<span>*</span>
										</p>
											<!-- 형식 통일하기 -->
										<input type="password" id="pwdCheck" name="pwdCheck" />
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									요청 사항
								</p>
								<input type="text"
									placeholder="요청사항을 입력해주세요">
							</div>
						</div>
						<!-- your order 창 -->
						<div class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								<div class="checkout__order__products">
									Products <span>Total</span>
								</div>
								<form>
									<ul>
										<c:set var="subTotal" value="0" />
										<c:forEach var="product" items="${orders }" varStatus="status">
											<li id="${product.prodNo}" class="orderProducts">
												<img src="/resources/img/delete.png" style="height: 20px; margin: auto;"  onclick="deleteProds('${product.prodNo}')" />${product.prodName }&nbsp 
												<input type="hidden" value="${product.prodNo }" />
												<input type="number" value="${receivedProducts[status.index].qty }" style="width: 43px; height: 30px; color: '#6f6f6f';" class="qty" onchange="calSubtotal(this, ${product.prodPrice });" min="1" />
												<span>${product.prodPrice * receivedProducts[status.index].qty}</span>
												<c:set var="subTotal" value = "${subTotal + product.prodPrice * receivedProducts[status.index].qty} " />
											</li>
										</c:forEach>
									</ul>
									<div class="checkout__order__subtotal">
										Subtotal <span id="subTotal">${subTotal}</span>
										<input type="hidden" value="${subTotal }" id="inputSubTotal"/>
									</div>
									<div class="checkout__order__total" style=" position: relative;">
										Coupons
										<img alt="" src="${pageContext.request.contextPath}/resources/img/coupon.png" style="position: absolute; right: 150px; bottom: 7px;" data-toggle="modal" data-target="#couponModal" id="couponImg"/>
										<span id="couponSelect" style="position: absolute;  right: 10px;"></span>
										<input type="hidden" id="coupon" name="coupon" value=""/>
									</div>
									<div class="checkout__order__total">
										Ship <span id="ship">3000(기본)</span>
									</div>
									<div class="checkout__order__total">
										Total <span></span>
									</div>

									<div class="checkout__input__checkbox">
										<label for="paypal"> Paypal <input type="checkbox"
										id="paypal"> <span class="checkmark"></span>
										</label>
									</div>
									<button type="submit" class="site-btn">PLACE ORDER</button>
								</form>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- Modal: modalCart -->
<div class="modal fade" id="couponModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <!--Header-->
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Your cart</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <!--Body-->
      <div class="modal-body">

        <table class="table table-hover">
          <thead>
            <tr>
              <th>쿠폰</th>
              <th>할인율</th>
              <th>만료기한</th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach var="coupon" items="${coupons }">
          		<tr onclick="selectCoupon('${coupon.couponName}')" id="${coupon.couponName }" class="couponLst">
              		<td>${coupon.couponName }</td>
              		<td><fmt:formatNumber value="${coupon.couponDiscount}" type="percent" /> </td>
              		<td><fmt:formatDate value="${coupon.dueDate }" pattern="yyyy-MM-dd a hh:mm" /></td>
            	</tr>
          	</c:forEach>
          </tbody>
        </table>

      </div>
      <!--Footer-->
      <div class="modal-footer">
        <button type="button" class="site-btn" data-dismiss="modal" style="background-color: orange;" onclick="selectCoupon()">Close</button>
        <button class="site-btn" onclick="couponCheck()" data-dismiss="modal" >Checkout</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal: modalCart -->

<!-- Modal: modalCart -->
<div class="modal fade" id="addressModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <!--Header-->
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Your cart</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <!--Body-->
      <div class="modal-body">

        <table class="table table-hover">
          <thead>
            <tr>
              <th>주소</th>
              <th>상세주소</th>
              <th>수령자</th>
              <th>수령자 전화번호</th>
              <th>우편번호</th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach var="addr" items="${addrs }">
          		<tr onclick="selectAddr(${addr.deliveryInfo })" id="addr${addr.deliveryInfo }" class="addrLst">
              		<td>${addr.address }</td>
              		<td>${addr.detailAddress }</td>
              		<td>${addr.recipient }</td>
              		<td>${addr.recipientPhoneNumber }</td>
              		<td>${addr.postCode }</td>
            	</tr>
          	</c:forEach>
          </tbody>
        </table>

      </div>
      <!--Footer-->
      <div class="modal-footer">
        <button type="button" class="site-btn" data-dismiss="modal" style="background-color: orange;" onclick="selectAddr();">Close</button>
        <button class="site-btn" data-dismiss="modal" onclick="addrCheck()">Checkout</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal: modalCart -->
	</section>
	<!-- Checkout Section End -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>