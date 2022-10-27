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

<title>제품 상세페이지</title>
<script>

function addCart(cart) {
	let qty =$(".pro-qty");
	let prodNo =${param.prodNo};
	let url = "/order/cart/add";
	
	$.ajax({
		url : url,
		data : { "prodNo" : prodNo ,"qty" : qty},
		type : "post",
		dataType : "json",
		
		success : function (data) {
			console.log(data);
			if(data == "success"){
				location.reload();
			}else if (data !="success"){
				alert(" 실패")
			}
		}
	 });
}


function delReview(reviewNo) {
	let url = "/prodReply/deleteReview";
	
	$.ajax({
         url : url, // 데이터 송수신될 주소 
         type : "post", // 통신 방식(get, post)
         dataType : "text", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
		 data : {"reviewNo": reviewNo}, // 전송할 데이터
         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
        	 console.log(data);
        	 if(data == "delSuccess"){
				location.href="/product/category/detail?prodNo=${param.prodNo}";
	        }else{
	        	alert("리뷰가 정상적으로 삭제되지 않았습니다. 다시 시도해주세요.");
	        }
         }
      });
}



function openReply(reviewNo) {
	if ($("#replyTap"+reviewNo).css("display")==("none")){
		$("#replyTap"+reviewNo).css("display", "block");
	}else{
		$("#replyTap"+reviewNo).css("display", "none");
	}
	
}

function writeReply(reviewNo) {
	output = "";
	output += '<div class="container"><div class="review_box"><form class="row contact_form" action="/prodReply/writeReply" method="post" id="replyWrite" novalidate="novalidate"><input type="hidden" id="reviewNo" name="reviewNo" value="'+reviewNo+'" readonly="readonly"/> <input type="hidden" id="prodNo" name="prodNo" value="${param.prodNo}" readonly="readonly"/> <div class="col-md-5"> <div class="form-group"> writer: <input type="text" class="form-control" id="replyWriter" name="replyWriter" value="'+'abcd'+'" /> </div> </div> <div class="col-md-12"> <div> content : <textarea class="form-control" style="height: 74px;" name="replyContent" id="replyContent" rows="1" placeholder="내용을 입력해주세요." ></textarea> </div> </div> <div class="col-md-12 text-right"> <button type="submit" value="submit" style="font-size : 14px; padding: 10px; border:0; outline:0; margin:3px;" class="primary-btn"  onclick="return replyValid()">등록</button><button type="reset" value="submit" style="font-size: 14px; padding: 10px; border:0; outline:0; margin:3px; background-color:orange;" class="primary-btn" onclick="replyCancel('+reviewNo+')">취소</button> </div> </form> </div> </div>';
	
	$("#replyWrite"+reviewNo).html(output);
}

function replyCancel(reviewNo) {
	$("#replyWrite"+reviewNo).html("");
}

function replyValid() {
	let content = $("#replyContent").val();
	
	if(content == ""){
		alert("댓글 내용을 입력해주세요.");
		return false;
	}else{
		return true;
	}
}

function writeReReply(rno, reviewNo) {
	let output = "";
	output += '<div class="container"><div class="review_box"><form class="row contact_form" action="/prodReply/writeReReply" method="post" id="replyWrite" novalidate="novalidate"><input type="hidden" id="reviewNo" name="reviewNo" value="'+ reviewNo +'" readonly="readonly"/><input type="hidden" id="parentRno" name="parentRno" value="'+rno+'" readonly="readonly"/> <input type="hidden" id="prodNo" name="prodNo" value="${param.prodNo}" readonly="readonly"/> <div class="col-md-5"> <div class="form-group"> writer: <input type="text" class="form-control" id="replyWriter" name="replyWriter" value="'+'abcd'+'" /> </div> </div> <div class="col-md-12"> <div class="form-group"> content : <textarea class="form-control" style="height: 74px;" name="replyContent" id="rereplyContent" rows="1" placeholder="내용을 입력해주세요." ></textarea> </div> </div> <div class="col-md-12 text-right"> <button type="submit" value="submit" class="primary-btn" style="font-size : 14px; padding: 10px; border:0; outline:0; margin:3px;" onclick="return rereplyValid()">등록</button><button type="reset" value="submit" class="primary-btn" style="font-size: 14px; padding: 10px; border:0; outline:0; margin:3px; background-color:orange;" onclick="rereplyCancel('+rno+')">취소</button> </div> </form> </div> </div>';
	
	$("#rereplyWrite"+rno).html(output);
}

function rereplyCancel(rno) {
	$("#rereplyWrite"+rno).html("");
}

function rereplyValid() {
	let content = $("#rereplyContent").val();
	
	if(content == ""){
		alert("댓글 내용을 입력해주세요.");
		return false;
	}else{
		return true;
	}
}

function delReply(rno) {
let url = "/prodReply/deleteReply";
	
	$.ajax({
         url : url, // 데이터 송수신될 주소 
         type : "get", // 통신 방식(get, post)
         dataType : "text", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
		 data : {"rno": rno}, // 전송할 데이터
         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
        	 console.log(data);
        	 if(data == "replyDelSuccess"){
				location.href="/product/category/detail?prodNo=${param.prodNo}";
	        }else{
	        	alert("댓글이 정상적으로 삭제되지 않았습니다. 다시 시도해주세요.");
	        }
         }
      });
}


$(function () {
	
	if (getParameter("pageNo") != -1){
		$("#Description").attr("class", "nav-link");
		$("#Information").attr("class", "nav-link");
		$("#Reviews").attr("class", "nav-link active");
		
		$("#tabs-1").attr("class", "tab-pane");
		$("#tabs-2").attr("class", "tab-pane");
		$("#review").attr("class", "tab-pane active");
	} 
	
	
});

function getParameter(param) {
    let returnVal = null;  //리턴값을 저장할 변수
    console.log(param);
    let url = location.href;
    console.log(url);

    let querystring = url.split("?")[1];
    console.log(querystring);

    let tempArr = querystring.split("&");
    console.log(tempArr);

    for (let i in tempArr) {
        if (tempArr[i].indexOf(param) != -1) { //tempArr배열에 i번째에 param이 있다면
            returnVal = tempArr[i].split("=")[1];
            return decodeURIComponent(returnVal);
        }
    }
    return -1; //찾을 매개변수가 없음
}




</script>
<style>
.product__details__rating i {
	color: orange;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<div class="product__details__pic__item">
							<c:forEach var="img" items="${prodImg}">
								<img class="product__details__pic__item--large"
									src="${img.originalFile }" alt="">
							</c:forEach>

						</div>
						<div class="product__details__pic__slider owl-carousel">
							<c:forEach var="img" items="${prodImg}">
								<img class="product__details__pic__item--large"
									src="${img.originalFile }" alt="">
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product__details__text">
						<h3>${product.prodName }</h3>
						<div class="product__details__rating">
							<c:forEach var="review" items="${reviews }">
								<c:set var="totalAssess" value="${totalAssess + review.assess }" />
							</c:forEach>
							<c:set var="avgAssess" value="${totalAssess / product.reviewCount }" />
							<c:forEach var="i" begin="1" end="${avgAssess }" step="1">
								<i class="fa fa-star"></i>
							</c:forEach>
							<c:forEach begin="${avgAssess }" end="4" step="1">
								<i class="fa fa-star" style="color: gray;"></i>
							</c:forEach>

							<span>&nbsp &nbsp ${product.reviewCount } &nbsp reviews</span>
						</div>
						<div class="product__details__price">${product.prodPrice }₩</div>
						<p>Mauris blandit aliquet elit, eget tincidunt nibh pulvinar
							a. Vestibulum ac diam sit amet quam vehicula elementum sed sit
							amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet
							quam vehicula elementum sed sit amet dui. Proin eget tortor
							risus.</p>
						<form action="${pageContext.request.contextPath}/order/cart/add" method="post">
							<div class="product__details__quantity">
							<input type="hidden" value="${param.prodNo }" name="orderProducts[0].prodNo" />
								<div class="quantity">
									<div class="pro-qty">
										<input type="text" value="1" pattern="[0-9]+" name="orderProducts[0].qty">
									</div>
								</div>
							</div>
							<button onclick="addcart();"  class="primary-btn"  style="border:0; outline:0;">TO CART</button> 
							<a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
						</form>
						
						<ul>
							<li><b>Availability</b> <span>${product.prodQuantity }</span></li>
							<li><b>Shipping</b> <span>3000₩ <samp>5만원 이상 구매시 무료배송</samp></span></li>
								<c:choose>
									<c:when test="${options.materialSubOption != '성격없음'}">
										<li><b>Main Material</b> <span>${options.materialSubOption}</span></li>
									</c:when>
									<c:when test="${options.sizeSubOption != '성격없음'}">
										<li><b>Size</b> <span>${options.sizeSubOption}</span></li>
									</c:when>
									<c:when test="${options.colorSubOption != '성격없음'}">
										<li><b>Color</b> <span>${options.colorSubOption}</span></li>
									</c:when>
									<c:when test="${options.weightSubOption != '성격없음'}">
										<li><b>Weight</b> <span>${options.weightSubOption}</span></li>
									</c:when>
								</c:choose>
						</ul>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="product__details__tab">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								id="Description" data-toggle="tab" href="#tabs-1" role="tab"
								aria-selected="true">Description</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								id="Reviews" href="#review" role="tab" aria-selected="false">Reviews
									<span>(${product.reviewCount })</span>
							</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tabs-1" role="tabpanel">
								<div class="product__details__tab__desc">
									<img src="${prodContent.prodContent }" style="display: block; margin: auto;">
								</div>
							</div>
							<div class="tab-pane" id="review" role="tabpanel">
								<section class="blog-details spad">
									<div class="product__details__tab__desc">
										<div class="row">
											<div class="col-lg-4 col-md-5 order-md-1 order-2">
												<div class="blog__sidebar">
													<div class="blog__sidebar__search">
														<a href="/prodReply/writeReview?prodNo=${param.prodNo }"
															class="primary-btn">add review</a>
													</div>
													<div class="blog__sidebar__item">
														<h4>stars</h4>
														<ul>
															<li>
																<div class="product__details__rating">
																	5 &nbsp; <i class="fa fa-star"></i> <i
																		class="fa fa-star" ></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star" ></i> 
																		<c:set var="cnt" value="0" />
																		<c:forEach var="i" items="${reviews}">
																			<c:if test="${i.assess == 5 }">
																				<c:set var="cnt" value="${cnt+1 }" />
																			</c:if>							
																		</c:forEach> 
																		<span>(${cnt } reviews)</span>
																</div>
															</li>
															<li>
																<div class="product__details__rating">
																	4 &nbsp; <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <c:set var="cnt" value="0" />
																		<c:forEach var="i" items="${reviews}">
																			<c:if test="${i.assess == 4 }">
																				<c:set var="cnt" value="${cnt+1 }" />
																			</c:if>							
																		</c:forEach> 
																		<span>(${cnt } reviews)</span>
																</div>
															</li>
															<li>
																<div class="product__details__rating">
																	3 &nbsp; <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <c:set var="cnt" value="0" />
																		<c:forEach var="i" items="${reviews}">
																			<c:if test="${i.assess == 3 }">
																				<c:set var="cnt" value="${cnt+1 }" />
																			</c:if>							
																		</c:forEach> 
																		<span>(${cnt } reviews)</span>
																</div>
															</li>
															<li>
																<div class="product__details__rating">
																	2 &nbsp; <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <c:set var="cnt" value="0" />
																		<c:forEach var="i" items="${reviews}">
																			<c:if test="${i.assess == 2 }">
																				<c:set var="cnt" value="${cnt+1 }" />
																			</c:if>							
																		</c:forEach> 
																		<span>(${cnt } reviews)</span>
																</div>
															</li>
															<li>
																<div class="product__details__rating">
																	1 &nbsp; <i class="fa fa-star"></i> <c:set var="cnt" value="0" />
																		<c:forEach var="i" items="${reviews}">
																			<c:if test="${i.assess == 1 }">
																				<c:set var="cnt" value="${cnt+1 }" />
																			</c:if>							
																		</c:forEach> 
																		<span>(${cnt } reviews)</span>
																</div>
															</li>
														</ul>
													</div>
													<div class="blog__sidebar__item">
														<h4>Recent News</h4>
														<div class="blog__sidebar__recent">
															<a href="#" class="blog__sidebar__recent__item">
																<div class="blog__sidebar__recent__item__pic">
																	<img src="img/blog/sidebar/sr-1.jpg" alt="">
																</div>
																<div class="blog__sidebar__recent__item__text">
																	<h6>
																		09 Kinds Of Vegetables<br /> Protect The Liver
																	</h6>
																	<span>MAR 05, 2019</span>
																</div>
															</a> <a href="#" class="blog__sidebar__recent__item">
																<div class="blog__sidebar__recent__item__pic">
																	<img src="img/blog/sidebar/sr-2.jpg" alt="">
																</div>
																<div class="blog__sidebar__recent__item__text">
																	<h6>
																		Tips You To Balance<br /> Nutrition Meal Day
																	</h6>
																	<span>MAR 05, 2019</span>
																</div>
															</a> <a href="#" class="blog__sidebar__recent__item">
																<div class="blog__sidebar__recent__item__pic">
																	<img src="img/blog/sidebar/sr-3.jpg" alt="">
																</div>
																<div class="blog__sidebar__recent__item__text">
																	<h6>
																		4 Principles Help You Lose <br />Weight With
																		Vegetables
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
															<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a
																href="#">Lifestyle</a>
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-8 col-md-7 order-md-1 order-1">
												<!--여기부터 후기 공간-->
												<div class="blog__details__text">
													<!-- 슬라이드 넣기 -->

													<c:forEach var="review" items="${reviews }">
														<div class="product__details__pic__slider owl-carousel">
															<c:forEach var="reviewImg" items="${reviewImg }">
																<c:set var="cnt" value="0" />
																<c:if test="${review.reviewNo == reviewImg.reviewNo }">
																	<c:set var="cnt" value="${cnt+1}" />
																	<c:forEach var="i" begin="1" end="${cnt }">
																		<img
																			src="/resources/uploads/reviewImg/${reviewImg.imgName }"
																			alt="">
																	</c:forEach>
																</c:if>
															</c:forEach>
														</div>
														<h3>${review.title }</h3>
														<div>
															<span> <c:if test="${review.assess != null}">
																	<c:forEach var="i" begin="1" end="${review.assess }">
																		<i class="fa fa-star" style="color: orange"></i>
																	</c:forEach>
																	<c:forEach var="i" begin="${review.assess }" end="4">
																		<i class="fa fa-star" style="color: gray"></i>
																	</c:forEach>

																</c:if>
															</span> <span>&nbsp;&nbsp;&nbsp;${review.likeCnt }명이 좋아함</span>
														</div>
														<p>${review.content }</p>
														<div class="blog__details__content drawLine">
															<div class="row">
																<div class="col-lg-6">
																	<div class="blog__details__author">
																		<div class="blog__details__author__pic">
																			<!--리뷰작성자 사진-->
																			<img src="img/blog/details/details-author.jpg" alt="">
																		</div>
																		<div class="blog__details__author__text">
																			<h6>${review.writer }</h6>
																		</div>
																	</div>
																</div>
																<div class="col-lg-6">
																	<div class="blog__details__widget">
																		<div class="blog__details__social">
																			<i class="fa fa-envelope"></i> <img alt=""
																				src="/resources/img/deleteReview.png"
																				onclick="delReview(${review.reviewNo});"
																				style="height: 20px; margin: auto;" /> <img alt=""
																				src="/resources/img/edit.png"
																				onclick="location.href='/prodReply2/editeReview/${review.reviewNo}/${param.prodNo }';"
																				style="height: 20px; margin: auto;" /> <img
																				src="/resources/img/reply.png"
																				onclick="openReply(${review.reviewNo})"
																				style="height: 20px; margin: auto;" /> <img
																				src="/resources/img/pencil.png"
																				onclick="writeReply(${review.reviewNo });"
																				style="height: 20px; margin: auto;" />
																			<div>
																				<fmt:formatDate value="${review.writtenDate }"
																					pattern="yyyy-MM-dd a hh:mm" />
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div id="replyWrite${review.reviewNo}"></div>
															<!-- 댓글창 -->
															<div class="drawLine" style="display: none;"
																id="replyTap${review.reviewNo }">
																<c:forEach var="reply" items="${replies }">
																	<c:if test="${review.reviewNo == reply.reviewNo}">
																			<div class="row" id="reply${reply.rno }">
																				<div class="col-lg-4">
																					<div class="blog__details__author">
																						<div class="blog__details__author__text">
																							<h6>
																								<c:if test="${reply.step > 0 }">
																									<c:forEach var="i" begin="1"
																										end="${reply.step}">
																										<img alt="" src="/resources/img/rereply.png" style="height: 20px; margin: auto;" />
																									</c:forEach>
																								</c:if>
																								${reply.replyWriter }
																							</h6>
																						</div>
																					</div>
																				</div>
																				<div class="col-lg-8">
																					<div class="blog__details__widget">
																						${reply.replyContent }</div>
																					<div>
																						<fmt:formatDate value="${reply.replyWrittenDate }"
																							pattern="yyyy-MM-dd a hh:mm" />
																					</div>
																					<div class="blog__details__social text-right">
																						<img alt="" src="/resources/img/deleteReview.png"
																							onclick="delReply(${reply.rno});"
																							style="height: 20px; margin: auto;" /> <img
																							src="/resources/img/pencil.png"
																							onclick="writeReReply(${reply.rno}, ${review.reviewNo });"
																							style="height: 20px; margin: auto;" />
																					</div>
																				</div>
																			</div>
																			<div id="rereplyWrite${reply.rno}"></div>
																	</c:if>
																</c:forEach>

															</div>

														</div>
														<hr>
													</c:forEach>
												</div>
											</div>

										</div>
									</div>
									<div class="product__pagination">
										<c:if test="${page.startNumOfCurPagingBlock > 1 }">
											<a
												href="/product/category/detail?prodNo=${param.prodNo }&pageNo=1"><<</a>
											<a
												href="/product/category/detail?prodNo=${param.prodNo }&pageNo=${page.startNumOfCurPagingBlock -1}"><-</a>
										</c:if>
										<c:forEach var="i" begin="${page.startNumOfCurPagingBlock }"
											end="${page.endNumOfCurPagingBlock }" varStatus="status">
											<c:if test="${status.current <= page.totalPage}">
												<a
													href="/product/category/detail?prodNo=${param.prodNo }&pageNo=${i }">${i}</a>
											</c:if>

										</c:forEach>
										<c:if test="${page.endNumOfCurPagingBlock < page.totalPage }">
											<a
												href="/product/category/detail?prodNo=${param.prodNo }&pageNo=${page.endNumOfCurPagingBlock +1}">-></a>
											<a
												href="/product/category/detail?prodNo=${param.prodNo }&pageNo=${page.totalPage}">>></a>
										</c:if>

									</div>
								</section>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Details Section End -->

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
