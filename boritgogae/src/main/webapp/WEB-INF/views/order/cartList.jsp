<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<script type="text/javascript">

	
	

</script>
<style>



</style>
<body>

	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
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
                                    <th class="shoping__product">상품/옵션 정보</th>
                                    <th>상품가격</th>
                                    <th>수량</th>
                                    <th>합계금액</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr onclick="cartList();">
                                    <td class="shoping__cart__item">
                                        <img src="img/cart/cart-1.jpg" alt="">
                                        <h5>Vegetable’s Package</h5>
                                    </td>
                                    <td class="shoping__cart__price">
                                        $55.00
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" value="1">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="shoping__cart__total">
                                        $110.00
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <span class="icon_close"></span>
                                    </td>
                                </tr>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="/product/category/detail?prodNo=a" class="primary-btn cart-btn">쇼핑 계속하기</a>
                        <a href="${contextPath}/order/cartList" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            장바구니 업데이트</a>
                    </div>
                </div>
              
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>장바구니 총합계</h5>
                        <ul>
                            
                            <li>결제 예정금액 <span>$454.98</span></li>
                        </ul>
                  	 <!-- form태그로 여기도,,, 아직 안했음 -->
                        <form name="" action="/order/.." method="post">
                       <!--name="orderProducts[0].prodNo"  -->  
	                        <button type="submit" class="primary-btn">전체상품 구매</a>
                        </form>
                    
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>