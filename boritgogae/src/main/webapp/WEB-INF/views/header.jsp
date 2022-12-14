<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unia577d2e5cb3081154c520bafe633fbf69efadb95ca, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Google Font -->
   <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
   
<!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">

<!-- Js Plugins -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
    
<title>header</title>
<script>
	$(document).ready(function() {
		$("#searchWord").keyup(function(e) {
			if (e.keyup == 13) {
				let searchWord = $("#searchWord").val();
				alert(searchWord);
				searchProduct();
			}
		});
	});
	
	function searchProduct() {
		let searchWord = $("#searchWord").val();
	
		location.href = "/product/" + searchWord + "?pageNo=1";
	}
</script>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

	

	
	<!-- Humberger Begin ????????? -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="${contextPath}/order/cart"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
            <div class="header__cart__price">item: <span>$150.00</span></div>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__language">
                
                <div>Korean</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                    <li><a href="#">Korean</a></li>
                    <li><a href="#">English</a></li>
                </ul>
            </div>
            
        </div>
        <!-- ????????? ?????? -->
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="${contextPath}/">Home</a></li>
                <li><a href="${contextPath}/product/productCategory/All?pageNo=1">Shop</a></li>
                <li><a href="${contextPath}/boardFree/list">?????????</a>
                   <ul class="header__menu__dropdown">
                        <li><a href="${contextPath}/boardFree/list">???????????????</a></li>
                        <li><a href="${contextPath}/board/question?pageNo=1">???????????????</a></li>
                        <li><a href="${contextPath}/board/market/listAll">???????????????</a></li>
                        <li><a href="/boardTip/listAll">?????????</a></li>
                    </ul>
                </li>
                <li><a href="/board/notice/list">????????????</a>
               <ul class="header__menu__dropdown">
                  <li><a href="/board/notice/list">????????????</a></li>
                  <li><a href="/board/ask/list">???????????????</a></li>
               </ul>
            </li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-pinterest-p"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                <li>Free Shipping for all Order of $99</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->
    
   
   <!-- Header Section Begin PC?????? -->
    <header class="header">
        
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
			                <li class="active"><a href="${contextPath}/">Home</a></li>
			                <li><a href="${contextPath}/product/productCategory/All?pageNo=1">Shop</a></li>
			                <li><a href="${contextPath}/boardFree/list">?????????</a>
			                	<ul class="header__menu__dropdown">
			                        <li><a href="${contextPath}/boardFree/list">???????????????</a></li>
			                        <li><a href="${contextPath}/board/question?pageNo=1">???????????????</a></li>
			                        <li><a href="${contextPath}/board/market/listAll">???????????????</a></li>
			                        <li><a href="/boardTip/listAll">?????????</a></li>
			                    </ul>
			                </li>
			                <li><a href="/board/notice/list">????????????</a>
								<ul class="header__menu__dropdown">
									<li><a href="/board/notice/list">????????????</a></li>
									<li><a href="/board/ask/list">???????????????</a></li>
								</ul>
							</li>
			            </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="${contextPath}/order/cart"><i class="fa fa-shopping-bag"></i> <span>4</span></a></li>
                        </ul>
                        <c:choose>
                        	<c:when test="${sessionScope.logInMember == null }">
                        	<!-- ???????????? ?????? ????????? ?????? -->
                        		<div class="header__top__right__auth">
                        			<a href="${pageContext.request.contextPath}/member/logIn">
                        				<i class="fa fa-user"></i> ?????????</a>
                        		</div>
                        	</c:when>
                        	<c:when test="${sessionScope.logInMember != null }">
                        	<!-- ???????????? ?????? ?????? -->
                        		<c:if test="${sessionScope.logInMember.isAdmin == 'Y' }">
                        			<div class="header__top__right__auth">
                        			<a href="${pageContext.request.contextPath}/member/logOut">
                        				<i class="fa fa-user"></i> ?????????????????????</a>
                        			</div>
                        		</c:if>
                        		<c:if test="${sessionScope.logInMember.isAdmin == 'N' }">
                        			<div class="header__top__right__auth">
                        			<a href="${pageContext.request.contextPath}/member/logOut">
                        				<i class="fa fa-user"></i> ????????????</a>
                        			</div>
                        			<div class="header__top__right__auth">
                        				<a>${sessionScope.logInMember.nickName } ??? ???????????????</a>
                        			</div>
                        		</c:if>
                        	</c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
    
    
    <!-- Hero Section Begin -->
    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
				<div class="col-lg-3">
					<a href="${contextPath}/"> <img
						src="${contextPath}/resources/img/boritgogae.png" style="height: 150px">
					</a>
				</div>
				<div class="col-lg-9">
				
					<div class="hero__search">
						<c:choose>
							<c:when
								test="${sessionScope.logInMember != null && sessionScope.logInMember.isAdmin != 'Y' }">
								<div class="hero__search__phone">
									<div class="hero__search__phone__text">
										<h5>
											<a href="/member/myPage" style="color: black;">My Page</a>
										</h5>
									</div>
								</div>
							</c:when>
							<c:when
								test="${sessionScope.logInMember != null && sessionScope.logInMember.isAdmin == 'Y' }">
								<div class="hero__search__phone">
									<div class="hero__search__phone__text">
										<h5>
											<a href="/admin/main" style="color: black;">Admin Page</a>
										</h5>
									</div>
								</div>
							</c:when>
						</c:choose>

					</div>

					<div class="mx-auto mt-5 search-bar input-group mb-3">
						<input name="searchWord" id="searchWord" type="text"
							class="form-control rounded-pill" placeholder="What do you need?"
							aria-label="Recipient's username"
							aria-describedby="button-addon2"
							onKeypress="javascript:if(event.keyCode==13) {searchProduct();}">
						<button type="button" id="moveFocus" class="site-btn"
							onclick="searchProduct();">SEARCH</button>
						<div class="input-group-append"></div>
					</div>
					
				</div>
			</div>
        </div>
    </section>
    <!-- Hero Section End -->
</body>
</html>