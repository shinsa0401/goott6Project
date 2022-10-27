<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
	



<!-- Js Plugins -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>


<script type="text/javascript">

$(function() {
	calSubtotal();
	
	document.addEventListener('keydown', function(event) {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		  };
		}, true);
	
	
});

	function calProdtotal(obj, price) {
		$(obj).siblings("span").html($(obj).val() * price);
		
		calSubtotal();
		deliveryOption();
	}

	//서브토탈을 구하여 화면에 출력하는 메서드
	function calSubtotal() {
		//li 클래스 orderProducts, 각 상품x수량 = li밑의 span에 있음
		
		let totalSubTotal = 0;
		
		//서브토탈 계산
		$(".orderProducts").each(function() {
			totalSubTotal += parseInt($(this).find("span").html())* 1.0;
		});
		
		let couponPercent = 0;
		let usePoint = 0;
		
		if("${member.memberId}" != ""){
			couponPercent = Number($("#couponPercent").html().replace("%","")) * 0.01;
			usePoint = parseInt($("#usePoint").val()*1.0);
		}
			
		//쿠폰 적용+포인트 적용
		totalSubTotal = totalSubTotal * (1-couponPercent) - usePoint;
		
		$("#subTotal").html(totalSubTotal);
		$("#inputSubTotal").val(totalSubTotal);
		
		if("${member.memberId}" != ""){
			calcAccum(totalSubTotal);
		}
		calcTotal(totalSubTotal);
	}

	//상품을 삭제하는 메서드
	function deleteProds(prodNo) {
		$("#"+prodNo).html("");
			        
		calSubtotal();
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
				$("#couponPercent").html($(this).find("td").eq(1).html());
				calSubtotal();
				return false;
			}else{
				$("#coupon").attr("value", "");
				$("#couponSelect").html("");
				$("#couponPercent").html(0);
			}
		});
		calSubtotal();
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
		
		deliveryOption();
}
	
	function deliveryOption() {
		let url = "/order/getDeliveryOption";
		
		let address = $("#address").val();
		let detailAddress = $("#detailAddress").val();
		let prodTotalPrice = $("#inputSubTotal").val();
		
		let OrderDTO = {"address" : address, "detailAddress" : detailAddress, "prodTotalPrice" : prodTotalPrice, "memberId": "${member.memberId}"};
		
		$.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
			 contentType : "application/json",
	         dataType : "json", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
			 data : JSON.stringify(OrderDTO), // 전송할 데이터
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	        	 console.log(data);
	        	 if(data != null){
	        		 
	        		 $("#ship").html(data.deliveryFee+"("+data.deliveryOption+")");
		        	 $("#shipInput").val(data.deliveryOption);
		        	 $("#shipFee").html(data.deliveryFee);
		        	 calSubtotal();
	        	 }else{
	        		 $("#ship").html("3000(기본)");
	        		 $("#shipInput").val("기본");
	        		 $("#shipFee").html(3000);
	        		 calSubtotal();
	        	 }
	        	 
	         }
	      });
		
		
	}
	
	//적립예정 포인트 구하는 메서드
	function calcAccum(subTotal) {
		let reservePercent = 0;
		
		if ("${member.memberId}" != ""){
			reservePercent = $("#reservePercent").val();
			let accum = Math.floor(subTotal * reservePercent);
			
			$("#accum").html(accum);
			$("#accumPoint").val(accum);
		}



	}
	
	//총 금액 구해서 출력하는 메서드
	function calcTotal(subTotal) {
		let totalPrice = subTotal+ parseInt($("#shipFee").html());
		
		$("#total").html(totalPrice);
		$("#inputTotal").val(totalPrice);
	}
	
	//유효성 검사
	function isValid() {
		function nameCheck() {
			let nameVal = false;
			let name = $("#name").val();
			if (name != ""){
				nameVal = true;
				$("#nameCheck").html("");
			}else{
				$("#nameCheck").html("*");
			}
			return nameVal;
		}
		
		function phoneNumberCheck() {
			let phoneNumberVal = false;
			let phoneNumber = $("#phoneNumber").val();
			numberExp = /(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
			if (numberExp.test(phoneNumber)){
				phoneNumberVal = true;
				$("#phoneNumberCheck").html("");
			}else{
				$("#phoneNumberCheck").html("*");
			}
			return phoneNumberVal;
		}
		
		function addrCheck() {
			let addressVal = false;
			let address = $("#address").val();
			if(address != ""){
				addressVal = true;
				$("#addrCheck").html("");
			}else{
				$("#addrCheck").html("*");
			}
			return addressVal;
		}
		
		function detailAddrCheck() {
			let detailAddress = $("#detailAddress").val();
			let detailAddressVal = false;
			
			if(detailAddress != ""){
				detailAddressVal = true;
				$("#detailAddrCheck").html("");
			}else{
				$("#detailAddrCheck").html("*");
			}
			return detailAddressVal;
		}
		
		function postCodeCheck() {
			let postCode = $("#postCode").val();
			let postCodeVal = false;
			let postCodeExp = /\d{5}/;
			
			if(postCodeExp.test(postCode)){
				postCodeVal = true;
				$("#postCodeCheck").html("");
			}else{
				$("#postCodeCheck").html("*");
			}
			return postCodeVal;
		}
		
		function guestMailCheck() {
			let guestEmailVal = false;
			let guestEmail = $("#guestEmail").val();
			let emailExp = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
			
			if(emailExp.test(guestEmail)){
				guestEmailVal = true;
				$("#guestMailCheck").html("");
			}else{
				$("#guestMailCheck").html("*");
			}
			return guestEmailVal;
		}
		
		function guestPwdCheck() {
			let guestPwdVal = false;
			
			let guestPwd = $("#guestPwd").val();
			let pwdCheck = $("#pwdCheck").val();
			let pwdExp = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
			
			if(pwdExp.test(guestPwd) && guestPwd == pwdCheck){
				guestPwdVal = true;
				$("#guestPwdCheck").html("");
			}else{
				$("#guestPwdCheck").html("*");
			}
			return guestPwdVal;
		}
		
		function prodCheck() {
			let prodNum = $(".product").length;
			let prodNumVal = false;
			
			if(prodNum > 0){
				prodNumVal = true;
			}else{
				alert("주문할 상품이 없습니다. 이전 페이지로 이동합니다.");
				history.back();
			}
			return prodNumVal;
		}
		
		let result = false;
		
		if ("${member.memberId}" != "" ){
			if(nameCheck() && phoneNumberCheck() && addrCheck() && detailAddrCheck() && postCodeCheck()){
				result = true;
			}
		}else{
			if(nameCheck() && phoneNumberCheck() && addrCheck() && detailAddrCheck() && postCodeCheck() && guestMailCheck() && guestPwdCheck() && prodCheck()){
				result = true;
			}
		}
		console.log(result);
		return result;
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
				<form action="/order/placeOrder" method="post">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											이름<span id="nameCheck"></span>
										</p>
										<input type="text" id="name" name="name"
											value="${member.memberName }"> <input type="hidden"
											id="memberId" name="memberId" value="${member.memberId }">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											전화번호<span id="phoneNumberCheck"></span>
										</p>
										<input type="text" id="phoneNumber" name="phoneNumber"
											value="${member.phoneNumber }" placeholder="숫자만 입력해주세요" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											주소<span id="addrCheck"></span>
										</p>
										<input type="text" placeholder="주소"
											class="checkout__input__add" readonly="readonly"
											class="col-lg-6" id="address" name="address" />
									</div>
								</div>
								<div class="col-lg-6">
									<div style="position: absolute; top: 35%;">
										<input type="button" onClick="goPopup();" value="주소 검색하기"
											class="site-btn" />
										<c:if test="${not empty member.memberId}">
											<input type="button" value="내 주소 목록" class="site-btn"
												style="background-color: #96AD73;" data-bs-toggle="modal"
												data-bs-target="#addressModal" />
										</c:if>

									</div>

								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											상세주소<span id="detailAddrCheck"></span>
										</p>
										<!-- 형식 통일하기 -->
										<input type="text" placeholder="건물이름/동호수" id="detailAddress"
											name="detailAddress" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											우편번호<span id="postCodeCheck"></span>
										</p>
										<!-- 형식 통일하기 -->
										<input type="text" placeholder="우편번호" id="postCode"
											name="postCode" />
									</div>
								</div>
							</div>
							<c:if test="${empty member.memberId}">
								<div class="checkout__input">
									<p>
										비회원 이메일<span id="guestMailCheck"></span>
									</p>
									<input type="text" placeholder="이메일 주소를 입력해주세요"
										name="guestEmail" id="guestEmail">
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="checkout__input">
											<p>
												비회원 비밀번호<span id="guestPwdCheck"></span>
											</p>
											<input type="password" id="guestPwd" name="guestPwd"
												placeholder="비밀번호는 8자이상으로, 영문자와 숫자를 포함하여 입력해주세요" />
										</div>
									</div>
									<div class="col-lg-6">
										<div class="checkout__input">
											<p>비밀번호 확인</p>
											<input type="password" id="pwdCheck" />
										</div>
									</div>
								</div>
							</c:if>

							<div class="checkout__input">
								<p>요청 사항</p>
								<input type="text" placeholder="요청사항을 입력해주세요" name="memo" />
							</div>
						</div>
						<!-- your order 창 -->
						<div class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								<div class="checkout__order__products">
									Products <span>Total</span>
								</div>

								<ul>

									<c:forEach var="product" items="${orders }" varStatus="status">
										<li id="${product.prodNo}" class="orderProducts"><img
											src="/resources/img/delete.png"
											style="height: 20px; margin: auto;"
											onclick="deleteProds('${product.prodNo}')" />${product.prodName }&nbsp
											<input type="hidden" value="${product.prodNo }"
											name="orderProducts[0].prodNo" class="product" /> <input
											type="number" value="${receivedProducts[status.index].qty }"
											style="width: 43px; height: 30px; color: '#6f6f6f';"
											class="qty"
											onchange="calProdtotal(this, ${product.prodPrice });" min="1"
											name="orderProducts[0].qty" /> <span>${product.prodPrice * receivedProducts[status.index].qty}</span>
										</li>
									</c:forEach>
								</ul>
								<div class="checkout__order__subtotal">
									Subtotal <span id="subTotal"></span> <input type="hidden"
										value="" id="inputSubTotal" name="prodTotalPrice" />
								</div>

								<c:if test="${not empty member.memberId}">
									<div class="checkout__order__total" style="position: relative;">
										쿠폰<img src="${pageContext.request.contextPath}/resources/img/coupon.png" id="couponImg" data-bs-toggle="modal" data-bs-target="#couponModal" />
										<span id="couponSelect"></span> <input
											type="hidden" id="coupon" name="coupon" value="" />
										<div style="display: none;" id="couponPercent"></div>
									</div>
									<div class="checkout__order__products">포인트</div>
									<ul>
										<li>사용가능 포인트 &nbsp; : &nbsp; <span>${member.memberPoint }</span>
										</li>
										<li><input type="number" value="0"
											style="width: 100px; height: 30px; color: '#6f6f6f';"
											id="usePoint" name="usedPoint" min="0"
											max="${logInMember.memberPoint }" onchange="calSubtotal();" />
											&nbsp;&nbsp;사용하기</li>
										<li>적립 예정 포인트 &nbsp; : &nbsp; <span id="accum"></span> <input
											type="hidden" value="0" min="0" name="accumPoint"
											id="accumPoint" />
											<input type="hidden" id="reservePercent" value="${grade.reservePoint}" />
										</li>
									</ul>
								</c:if>

								<div class="checkout__order__total">
									Ship <span id="ship">3000(기본)</span> <input type="hidden"
										id="shipInput" name="deliveryOption" value="기본" />
									<div style="display: none;" id="shipFee">3000</div>
								</div>
								<div class="checkout__order__total">
									Total <span id="total"></span> <input type="hidden"
										id="inputTotal" name="totalPrice" value="" />
								</div>

								<div class="checkout__input__checkbox">
									<label for="paypal"> Paypal <input type="checkbox"
										id="paypal"> <span class="checkmark"></span>
									</label>
								</div>
								<button type="submit" class="site-btn"
									onclick="return isValid();">PLACE ORDER</button>

							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		
			<!-- Modal: modalCart -->
			<div class="modal fade" id="couponModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<!--Header-->
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">내 쿠폰</h4>
							<button type="button" class="close" data-bs-dismiss="modal"
								aria-label="Close">
								<span aria-bs-hidden="true">×</span>
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
										<tr onclick="selectCoupon('${coupon.key.couponName}')"
											id="${coupon.key.couponName }" class="couponLst">
											<td>${coupon.key.couponName }</td>
											<td><fmt:formatNumber
													value="${coupon.key.couponDiscount}" type="percent" /></td>
											<td><fmt:formatDate
													value="${coupon.value.expirationDate }"
													pattern="yyyy-MM-dd a hh:mm" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<button type="button" class="site-btn" data-bs-dismiss="modal"
								style="background-color: orange;" onclick="selectCoupon()">닫기</button>
							<button class="site-btn" onclick="couponCheck()"
								data-bs-dismiss="modal">결정</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal: modalCart -->
		
			<!-- Modal: modalCart -->
			<div class="modal fade" id="addressModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<!--Header-->
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">내 주소</h4>
							<button type="button" class="close" data-bs-dismiss="modal"
								aria-bs-label="Close">
								<span aria-bs-hidden="true">×</span>
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
										<tr onclick="selectAddr(${addr.deliveryInfo })"
											id="addr${addr.deliveryInfo }" class="addrLst">
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
							<button type="button" class="site-btn" data-bs-dismiss="modal"
								style="background-color: orange;" onclick="selectAddr();">Close</button>
							<button class="site-btn" data-bs-dismiss="modal"
								onclick="addrCheck()">Checkout</button>
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