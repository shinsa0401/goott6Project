<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		//viewAllProdImg();
	});
	
	
</script>
</head>

<body>

	<jsp:include page="../header.jsp"></jsp:include>
	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar">
						<div class="sidebar__item">
							<h4>Department</h4>
							<ul>
								<li><a href="#">Fresh Meat</a></li>
								<li><a href="#">Vegetables</a></li>
								<li><a href="#">Fruit & Nut Gifts</a></li>
								<li><a href="#">Fresh Berries</a></li>
								<li><a href="#">Ocean Foods</a></li>
								<li><a href="#">Butter & Eggs</a></li>
								<li><a href="#">Fastfood</a></li>
								<li><a href="#">Fresh Onion</a></li>
								<li><a href="#">Papayaya & Crisps</a></li>
								<li><a href="#">Oatmeal</a></li>
							</ul>
						</div>
						<div class="sidebar__item">
							<h4>Price</h4>
							<div class="price-range-wrap">
								<div
									class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
									data-min="10" data-max="540">
									<div class="ui-slider-range ui-corner-all ui-widget-header"></div>
									<span tabindex="0"
										class="ui-slider-handle ui-corner-all ui-state-default"></span>
									<span tabindex="0"
										class="ui-slider-handle ui-corner-all ui-state-default"></span>
								</div>
								<div class="range-slider">
									<div class="price-input">
										<input type="text" id="minamount"> <input type="text"
											id="maxamount">
									</div>
								</div>
							</div>
						</div>
						<div class="sidebar__item sidebar__item__color--option">
							<h4>Colors</h4>
							<div class="sidebar__item__color sidebar__item__color--white">
								<label for="white"> White <input type="radio" id="white">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--gray">
								<label for="gray"> Gray <input type="radio" id="gray">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--red">
								<label for="red"> Red <input type="radio" id="red">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--black">
								<label for="black"> Black <input type="radio" id="black">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--blue">
								<label for="blue"> Blue <input type="radio" id="blue">
								</label>
							</div>
							<div class="sidebar__item__color sidebar__item__color--green">
								<label for="green"> Green <input type="radio" id="green">
								</label>
							</div>
						</div>
						<div class="sidebar__item">
							<h4>Popular Size</h4>
							<div class="sidebar__item__size">
								<label for="large"> Large <input type="radio" id="large">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="medium"> Medium <input type="radio"
									id="medium">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="small"> Small <input type="radio" id="small">
								</label>
							</div>
							<div class="sidebar__item__size">
								<label for="tiny"> Tiny <input type="radio" id="tiny">
								</label>
							</div>
						</div>
						<div class="sidebar__item">
							<div class="latest-product__text">
								<h4>최신 상품</h4>
								<div class="latest-product__slider owl-carousel">
									<div class="latest-prdouct__slider__item">
										<a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-1.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a> <a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-2.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a> <a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-3.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a>
									</div>
									<div class="latest-prdouct__slider__item">
										<a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-1.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a> <a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-2.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a> <a href="#" class="latest-product__item">
											<div class="latest-product__item__pic">
												<img src="img/latest-product/lp-3.jpg" alt="">
											</div>
											<div class="latest-product__item__text">
												<h6>Crab Pool Security</h6>
												<span>$30.00</span>
											</div>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-7">
					<div class="product__discount">
						<div class="section-title product__discount__title">
							<h2>인기 상품</h2>
						</div>
						<div class="row">
							<div class="product__discount__slider owl-carousel">
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-1.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><i class="fa fa-heart"></i></li>
												<li><i class="fa fa-retweet"></i></li>
												<li><i class="fa fa-shopping-cart"></i></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Dried Fruit</span>
											<h5>
												<a href="#">Raisin’n’nuts</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-2.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><a href="#"><i class="fa fa-heart"></i></a></li>
												<li><a href="#"><i class="fa fa-retweet"></i></a></li>
												<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Vegetables</span>
											<h5>
												<a href="#">Vegetables’package</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-3.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><a href="#"><i class="fa fa-heart"></i></a></li>
												<li><a href="#"><i class="fa fa-retweet"></i></a></li>
												<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Dried Fruit</span>
											<h5>
												<a href="#">Mixed Fruitss</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-4.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><a href="#"><i class="fa fa-heart"></i></a></li>
												<li><a href="#"><i class="fa fa-retweet"></i></a></li>
												<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Dried Fruit</span>
											<h5>
												<a href="#">Raisin’n’nuts</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-5.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><a href="#"><i class="fa fa-heart"></i></a></li>
												<li><a href="#"><i class="fa fa-retweet"></i></a></li>
												<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Dried Fruit</span>
											<h5>
												<a href="#">Raisin’n’nuts</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="img/product/discount/pd-6.jpg">
											<div class="product__discount__percent">-20%</div>
											<ul class="product__item__pic__hover">
												<li><a href="#"><i class="fa fa-heart"></i></a></li>
												<li><a href="#"><i class="fa fa-retweet"></i></a></li>
												<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>Dried Fruit</span>
											<h5>
												<a href="#">Raisin’n’nuts</a>
											</h5>
											<div class="product__item__price">
												$30.00 <span>$36.00</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="filter__item">
						<div class="row">
							<div class="col-lg-4 col-md-5">
								<div class="filter__sort">
									<span>Sort By</span> <select>
										<option value="0">Default</option>
										<option value="0">Default</option>
									</select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4">
								<div class="filter__found">
									<h6>
										<span>${total }</span> Products found
									</h6>
								</div>
							</div>
							<div class="col-lg-4 col-md-3">
								<div class="filter__option">
									<span class="icon_grid-2x2"></span> <span class="icon_ul"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<c:forEach var="prod" items="${prodLst }">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item">
									<div class="product__item__pic set-bg" >
										<img src="${prod.originalFile }" onclick="javascript:location.href='/product/category/detail?prodNo=${prod.prodNo }';"/>
										<ul class="product__item__pic__hover">
											<li><i class="fa fa-heart"></i></li>
											<li><i class="fa fa-retweet"></i></li>
											<li><i class="fa fa-shopping-cart"></i></li>
										</ul>
									</div>
									<div class="product__item__text">
										<h6>
											<a href="/product/category/detail?prodNo=${prod.prodNo }">${prod.prodName }</a>
										</h6>
										<h5>${prod.prodPrice }원</h5>
										<h6>[재고] : ${prod.prodQuantity }</h6>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
					<div class="product__pagination" style="">
						<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#"><i
							class="fa fa-long-arrow-right"></i></a>
					</div>
				</div>
			</div>
	</section>

	<!-- Product Section End -->

	<jsp:include page="../footer.jsp"></jsp:include>



</body>

</html>