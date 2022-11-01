<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="../resources/css/bootstrap.min.css"  rel="stylesheet" >
<script type="text/javascript" src="../resources/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</head>
<script type="text/javascript">

$(function() {
	
	
	
});
	
	//장바구니에서 담은 상품을 클릭하면 상품의 상세페이지로 이동
	//아직
	function prodDetail(prodNo) {
		location.href = "/product/category/detail?prodNo="+prodNo; //상세페이지 경로 확인 후 바꿔야함
		alert("!");
	}
		
	function qtyCartUpdate( cartNo) {
		let qty = $(".quantity_input").val();
		
		 $.ajax({
			url : "/order/cart/updateCart",
			data : { "cartNo" : cartNo ,"qty" : qty},
			type : "get",
			dataType : "text",
			success : function (data) {
				console.log(data);
				
				if(data == 1){
					location.reload();
				}else if (data !=1){
					alert("수정 실패")
				}
			}
		 });
	}
	
	function delCart(cartNo) {
		console.log(cartNo);
		
		 $.ajax({
			url : "/order/cart/delCart",
			data : {"cartNo" : cartNo},
			type : "get",
			dataType : "text",
			success : function (data) {
				console.log(data);
				
				if(data == 1){
					location.reload();
				}else if (data !=1){
					alert("삭제 실패")
				}
			}
		 });
	}
</script>
<style>



</style>
<body>

	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg">
    
    <button type="button" onclick="addCart();">zz</button>
    
    
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Shopping Cart</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                	<th>상품 </th>
                                    <th class="shoping__product">상품/옵션 정보</th>
                                    <th>상품가격</th>
                                    <th>수량</th>
                                    <th>합계금액</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cart" items="${cartList }">
                                <tr >
                                	<td class="shoping__cart__img">
                                	<!--  <img src="${cart.prodImg}"/> 
                                	<a href = "/order/cart/add">zzz </a>	 -->
                                	
                                	</td>
                                    <td class="shoping__cart__item" onclick="prodDetail(${cart.prodNo});">
                                      ${cart.prodName}
                                    </td>
                                    <td class="shoping__cart__price">
                                    	<fmt:formatNumber value="${cart.prodPrice}" pattern="#,### 원" />
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" value=" ${cart.qty }" class="quantity_input">
                                            </div>
                                            <a class="quantity_modify_btn" onclick="qtyCartUpdate(${cart.cartNo });" >변경</a>
                                        </div>
                                    </td>
                                    <td class="shoping__cart__total">
                                    <fmt:formatNumber value="${cart.qty * cart.prodPrice}" pattern="#,### 원" />
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <a class="delete-cart_btn" onclick="delCart(${cart.cartNo});">X</a>
                                    </td>
                                </tr>
                               
                           
                           </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="/product/category/detail" class="primary-btn cart-btn">쇼핑 계속하기</a>
                        <a href="${contextPath}/order/cart" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            장바구니 업데이트</a>
                    </div>
                </div>
              
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>장바구니 총합계</h5>
                        <ul>
                            <li>결제 예정금액
                            	<span> ${totalPrice }</span>
                            </li>
                        </ul>
                  	 <!-- form태그로 여기도,,, 아직 안했음
                        <form name="orderProducts[0].prodNo" action="${pageContext.request.contextPath}/order/orderSheet" method="post ">
	                        <button type="submit" class="primary-btn">전체상품 구매</a>
                        </form>
                     -->
                    </div>
                </div>
            </div>
        
	</div>
    </section>
    <!-- Shoping Cart Section End -->
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>