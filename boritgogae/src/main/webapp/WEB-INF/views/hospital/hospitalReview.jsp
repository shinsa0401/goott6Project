<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootswatch: Journal -->
<link rel="stylesheet" href="../5/journal/bootstrap.css">
<link rel="stylesheet"
	href="../_vendor/bootstrap-icons/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="../_vendor/prismjs/themes/prism-okaidia.css">
<link rel="stylesheet" href="../_assets/css/custom.min.css">

<title>병원 찾기</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="#"><img src="img/logo.png" alt=""></a>
		</div>
		<div class="humberger__menu__cart">
			<ul>
				<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
				<li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
			</ul>
			<div class="header__cart__price">
				item: <span>$150.00</span>
			</div>
		</div>
		<div class="humberger__menu__widget">
			<div class="header__top__right__language">
				<img src="img/language.png" alt="">
				<div>English</div>
				<span class="arrow_carrot-down"></span>
				<ul>
					<li><a href="#">Spanis</a></li>
					<li><a href="#">English</a></li>
				</ul>
			</div>
		</div>

		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a href="./index.html">Home</a></li>
				<li><a href="./shop-grid.html">Shop</a></li>
				<li><a href="../hospitalList.jsp">병원찾기</a></li>
				<li><a href="./contact.html">게시판</a>
					<ul class="header__menu__dropdown">
						<li><a href="./shop-details.html">자유게시판</a></li>
						<li><a href="./shoping-cart.html">질문게시판</a></li>
						<li><a href="./checkout.html">장터게시판</a></li>
						<li><a href="./blog-details.html">고개팁</a></li>
					</ul></li>
				<li><a href="./contact.html">고객센터</a>
					<ul class="header__menu__dropdown">
						<li><a href="./shop-details.html">공지사항</a></li>
						<li><a href="./shoping-cart.html">문의게시판</a></li>
					</ul></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
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

	<!-- Header Section Begin -->
	<header class="header">

		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.html"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="./index.html">Home</a></li>
							<li><a href="./shop-grid.html">Shop</a></li>
							<li><a href="../hospitalList.jsp">병원</a></li>
							<li><a href="./contact.html">게시판</a>
								<ul class="header__menu__dropdown">
									<li><a href="./shop-details.html">자유게시판</a></li>
									<li><a href="./shoping-cart.html">질문게시판</a></li>
									<li><a href="./checkout.html">장터게시판</a></li>
									<li><a href="./blog-details.html">고개팁</a></li>
								</ul></li>
							<li><a href="./contact.html">고객센터</a>
								<ul class="header__menu__dropdown">
									<li><a href="./shop-details.html">공지사항</a></li>
									<li><a href="./shoping-cart.html">문의게시판</a></li>
								</ul></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
							<li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
						</ul>
						<div class="header__top__right__auth">
							<a href="#"><i class="fa fa-user"></i> Login</a>
						</div>
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
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>All departments</span>
						</div>
						<ul>
							<!-- 상단 카테고리 드롭다운 -->
							<li><a href="#">wgty</a></li>
							<li><a href="#">Vegetables</a></li>
							<li><a href="#">Fruit & Nut Gifts</a></li>
							<li><a href="#">Fresh Berries</a></li>
							<li><a href="#">Ocean Foods</a></li>
							<li><a href="#">Butter & Eggs</a></li>
							<li><a href="#">Fastfood</a></li>
							<li><a href="#">Fresh Onion</a></li>
							<li><a href="#">Papayaya & Crisps</a></li>
							<li><a href="#">Oatmeal</a></li>
							<li><a href="#">Fresh Bananas</a></li>
						</ul>
					</div>
					<div class="blog__sidebar__item">
						<br />
						<h4>Categories</h4>
						<ul>
							<li><a href="#">강아지</a></li>
							<li><a href="#">고양이</a></li>
							<li><a href="#">주차가능</a></li>
							<li><a href="#">별점순</a></li>
							<li><a href="#">리뷰게시판</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form action="#">
								<div class="hero__search__categories">
									All Categories <span class="arrow_carrot-down"></span>
								</div>
								<input type="text" placeholder="What do yo u need?">
								<button type="submit" class="site-btn">SEARCH</button>
							</form>
						</div>
						<div class="hero__search__phone">
							<div class="hero__search__phone__text">
								<h5>My Page</h5>
							</div>
						</div>
					</div>
					<div class="blog__sidebar__search">
						<form action="#">
							<input type="text" placeholder="Search...">
							<button type="submit">
								<span class="icon_search"></span>
							</button>
						</form>
					</div>

					<div class="container">
						<div class="col-lg-8 col-md-7">
							<div class="row">
								<div class="page-header">
									<h2 id="tables" style="vertical-align: inherit;">병원 리뷰 게시판</h2>
								</div>
								<table class="table table-hover">
									<tbody>
										<tr class="table-success">
											<th scope="row">No</th>
											<td>글쓴이</td>
											<td>제목</td>
											<td>작성일</td>
											<td>조회수</td>
											<td>좋아요</td>
										</tr>
										</tr>
										<tr class="table-light">
											<th scope="row">1</th>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
										</tr>
										</tr>
										<tr class="table-light">
											<th scope="row">2</th>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
										</tr>
										</tr>
										<tr class="table-light">
											<th scope="row">3</th>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
											<td>Column content</td>
										</tr>
									</tbody>
								</table>
								<div class="col-lg-12">
									<div class="product__pagination blog__pagination">
										<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a
											href="#"><i class="fa fa-long-arrow-right"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Breadcrumb Section End -->

	<!-- Blog Section Begin -->
	<section class="blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5">
					<div class="blog__sidebar">

						<div class="blog__sidebar__item">
							<h4>Recent News</h4>
							<div class="blog__sidebar__recent">
								<a href="#" class="blog__sidebar__recent__item">
									<div class="blog__sidebar__recent__item__pic"></div>
									<div class="blog__sidebar__recent__item__text">
										<h6>
											09 Kinds Of Vegetables<br /> Protect The Liver
										</h6>
										<span>MAR 05, 2019</span>
									</div>
								</a> <a href="#" class="blog__sidebar__recent__item">
									<div class="blog__sidebar__recent__item__pic"></div>
									<div class="blog__sidebar__recent__item__text">
										<h6>
											Tips You To Balance<br /> Nutrition Meal Day
										</h6>
										<span>MAR 05, 2019</span>
									</div>
								</a> <a href="#" class="blog__sidebar__recent__item">
									<div class="blog__sidebar__recent__item__pic"></div>
									<div class="blog__sidebar__recent__item__text">
										<h6>
											4 Principles Help You Lose <br />Weight With Vegetables
										</h6>
										<span>MAR 05, 2019</span>
									</div>
								</a>
							</div>
						</div>
						<div class="blog__sidebar__item">
							<h4>Search By</h4>
							<div class="blog__sidebar__item__tags">
								<a href="#">Apple</a> <a href="#">Beauty</a> <a href="#">Vegetables</a>
								<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a href="#">Lifestyle</a>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Blog Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.html"><img src="img/logo.png" alt=""></a>
						</div>
						<ul>
							<li>Address: 60-49 Road 11378 New York</li>
							<li>Phone: +65 11.188.888</li>
							<li>Email: hello@colorlib.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
					<div class="footer__widget">
						<h6>Useful Links</h6>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">About Our Shop</a></li>
							<li><a href="#">Secure Shopping</a></li>
							<li><a href="#">Delivery infomation</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Our Sitemap</a></li>
						</ul>
						<ul>
							<li><a href="#">Who We Are</a></li>
							<li><a href="#">Our Services</a></li>
							<li><a href="#">Projects</a></li>
							<li><a href="#">Contact</a></li>
							<li><a href="#">Innovation</a></li>
							<li><a href="#">Testimonials</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>Join Our Newsletter Now</h6>
						<p>Get E-mail updates about our latest shop and special
							offers.</p>
						<form action="#">
							<input type="text" placeholder="Enter your mail">
							<button type="submit" class="site-btn">Subscribe</button>
						</form>
						<div class="footer__widget__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-pinterest"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer__copyright">
						<div class="footer__copyright__text">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
																																					document
																																							.write(new Date()
																																									.getFullYear());
																																				</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
						<div class="footer__copyright__payment">
							<img src="img/payment-item.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>

</body>


</html>