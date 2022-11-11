<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boritgogae</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						//viewAllProdImg();
						let pageNo = ${pageNo};
						let pageNoTotal = ${param.pageNo};
						let pageGo = ${pi.totalPage};

						if (pageNoTotal > pageGo) {
							console.log(pageNo);
							console.log(pageNoTotal);
							location.href = "/product/productCategory/${category }?pageNo="
									+ pageGo;
						} else if (pageNoTotal < 1) {
							location.href = "/product/productCategory/${category }?pageNo=1";
						} else {
							console.log(pageNo);
							console.log(pageNoTotal);
							console.log(pageGo);
						}
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
							<h4>category</h4>
							<ul>
								<li><a href="/product/productCategory/CF?pageNo=1">고양이
										FOOD</a></li>
								<li><a href="/product/productCategory/CC?pageNo=1">고양이
										LITTER</a></li>
								<li><a href="/product/productCategory/CL?pageNo=1">고양이
										LIFE</a></li>
								<li><a href="/product/productCategory/CB?pageNo=1">고양이
										HEALTHY</a></li>
								<li><a href="/product/productCategory/CT?pageNo=1">고양이
										PLAY</a></li>
								<li><a href="/product/productCategory/DF?pageNo=1">강아지
										FOOD</a></li>
								<li><a href="/product/productCategory/DC?pageNo=1">강아지
										TOILET</a></li>
								<li><a href="/product/productCategory/DL?pageNo=1">강아지
										LIFE</a></li>
								<li><a href="/product/productCategory/DB?pageNo=1">강아지
										BEAUTY</a></li>
								<li><a href="/product/productCategory/DT?pageNo=1">강아지
										PLAY</a></li>
								<li><a href="/product/productCategory/All?pageNo=1">강아지,
										고양이</a></li>
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
								<h4>최근 등록 상품</h4>
								<div class="latest-product__slider owl-carousel">
									<c:forEach var="last" items="${lastProd }">
										<div class="latest-prdouct__slider__item">
											<a href="product/category/detail?prodNo=${last.prodNo }"
												class="latest-product__item">
												<div class="latest-product__item__pic">
													<img src="${last.originalFile }">
												</div>
												<div class="latest-product__item__text">
													<h6>
														[<b>${last.brand }</b>]
													</h6>
													<h6>${last.prodName }</h6>
													<span>${last.prodPrice }원</span>
												</div>
											</a>
										</div>
									</c:forEach>
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
								<c:forEach var="prod" items="${popular}">
									<div class="col-lg-4">
										<div class="product__discount__item">
											<c:if test="${prod.prodQuantity != 0}">
												<div class="product__discount__item__pic set-bg"
													data-setbg="${prod.originalFile }"
													onclick="javascript:location.href='/product/category/detail?prodNo=${prod.prodNo }';">
													<div class="product__discount__percent">-20%</div>
													<ul class="product__item__pic__hover">
														<li><a href="#"><i class="fa fa-heart"></i></a></li>
														<li><a href="#"><i class="fa fa-retweet"></i></a></li>
														<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
													</ul>
												</div>
											</c:if>
											<c:if test="${prod.prodQuantity == 0}">
												<img src="../resources/img/soldout.jpg"
													style="height: 270px"
													onclick="javascript:location.href='/product/category/detail?prodNo=${prod.prodNo }';" />
											</c:if>

											<div class="product__discount__item__text">
												<span>[${prod.brand }]</span>
												<h5>
													<a href="/product/category/detail?prodNo=${prod.prodNo }">${prod.prodName }</a>
												</h5>
												<div class="product__item__price">
													${prod.prodPrice } <span></span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
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
										<span>${pi.totalPostCnt }</span> Products found
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
							<div class="col-lg-4 col-md-6 col-sm-6 allProduct">
								<div class="product__item">
									<div class="product__item__pic set-bg">
										<c:if test="${prod.prodQuantity != 0}">
											<img src="${prod.originalFile }"
												onclick="javascript:location.href='/product/category/detail?prodNo=${prod.prodNo }';" />
										</c:if>
										<c:if test="${prod.prodQuantity == 0}">
											<img src="../resources/img/soldout.jpg"
												onclick="javascript:location.href='/product/category/detail?prodNo=${prod.prodNo }';" />
										</c:if>

										<ul class="product__item__pic__hover">
											<li><i class="fa fa-heart"></i></li>
											<li><i class="fa fa-retweet"></i></li>
											<li><i class="fa fa-shopping-cart"></i></li>
										</ul>
									</div>
									<div class="product__item__text">
										<h6>
											[<b>${prod.brand }</b>]
										</h6>
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
						<!-- dd -->
						<c:choose>
							<c:when
								test="${category == 'CF' || category == 'CC'|| category == 'CL'||category == 'CB'||category == 'CT'||category == 'DF'||category == 'DC'||category == 'DL'||category == 'DB'||category == 'DT'||category == 'All' }">
								<c:if test="${param.pageNo > 1 }">
									<a
										href="/product/productCategory/${category }?pageNo=${param.pageNo - 1 }">
										<i class="fa fa-long-arrow-left"> </i>
									</a>
								</c:if>

								<c:forEach var="i" begin="${pi.startNumOfCurPagingBlock}"
									end="${pi.endNumOfCurPagingBlock }" step="1">
									<c:if test="${i < pi.totalPage+1 }">
										<a href="/product/productCategory/${category }?pageNo=${i}">${i}</a>
									</c:if>

								</c:forEach>
								<c:if
									test="${param.pageNo < pi.totalPage or param.pageNo==null}">
									<a
										href="/product/productCategory/${category }?pageNo=${param.pageNo + 1 }">
										<i class="fa fa-long-arrow-right"> </i>
									</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${param.pageNo > 1 }">
									<a href="/product/${category }?pageNo=${param.pageNo - 1 }">
										<i class="fa fa-long-arrow-left"> </i>
									</a>
								</c:if>

								<c:forEach var="i" begin="${pi.startNumOfCurPagingBlock}"
									end="${pi.endNumOfCurPagingBlock }" step="1">
									<c:if test="${i < pi.totalPage+1 }">
										<a href="/product/${category }?pageNo=${i}">${i}</a>
									</c:if>

								</c:forEach>
								<c:if
									test="${param.pageNo < pi.totalPage or param.pageNo==null}">
									<a href="/product/${category }?pageNo=${param.pageNo + 1 }">
										<i class="fa fa-long-arrow-right"> </i>
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
	</section>

	<!-- Product Section End -->

	<jsp:include page="../footer.jsp"></jsp:include>



</body>

</html>