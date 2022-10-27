<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>index</title>
<script>
$(document).ready(function() {
	let keyword = $(".current").val();
	miniBoard(keyword);
});

	function miniBoard(keyword){
		
		let url = "";
		
		switch (keyword) {
		case "질문게시판":
			url = "/view/question";
			break;
		case "고개팁":
			url = "/view/tip";
			break;
		case "자유게시판":
			url = "/view/free";
			break;
		case "장터게시판":
			url = "/view/market";
			break;
		default:
			url = "/view/notice";
			break;
		}
		
		$.ajax({
			url : url, 
			dataType : "json", 
			contentType : 'application/json;charset=utf-8',
			type : "get", 
			success : function(data) {
				console.log(data);
				parsingMini(data);
				
			},
			error : function(e) { 
				console.log(e);
			}
		});
		
		
		
	}

	function parsingMini(data){
		
		$("#viewTable").hide();
	}
	
	function viewBoard(bno){
		location.href = "/board/notice/view?no="+bno;
	}
	
	
</script>
<style>
#headTr {
	background-color: #7FAD39;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar">
						<div class="sidebar__item">
							<h4>Department</h4>
							<ul>
								<li><a href="#">병원</a></li>
								<li><a href="/board/notice/list">공지사항</a></li>
								<li><a href="#">자유 게시판</a></li>
								<li><a href="/board/ask/list">문의 게시판</a></li>
								<li><a href="${contextPath}/board/question?pageNo=1">질문
										게시판</a></li>
								<li><a href="">상품 후기</a></li>
								<li><a href="/boardTip/listAll">고개팁</a></li>
								<li><a href="${contextPath}/board/market/listAll">장터 게시판</a></li>
								<li><a href="#">병원리뷰</a></li>
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
								<c:forEach var="prod" items="${prodLst}">
									<div class="col-lg-4">
										<div class="product__discount__item">
											<div class="product__discount__item__pic set-bg"
												data-setbg="${prod.originalFile }">
												<div class="product__discount__percent">-20%</div>
												<ul class="product__item__pic__hover">
													<li><a href="#"><i class="fa fa-heart"></i></a></li>
													<li><a href="#"><i class="fa fa-retweet"></i></a></li>
													<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
												</ul>
											</div>
											<div class="product__discount__item__text">
												<span>[${prod.brand }]</span>
												<h5>
													<a href="#">${prod.prodName }</a>
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
									<span>Show View</span> <select>
										<option value="0" selected>공지사항</option>
										<option value="0">질문게시판</option>
										<option value="0">고개팁</option>
										<option value="0">자유게시판</option>
										<option value="0">장터게시판</option>
									</select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4">
								<div class="filter__found">
									<h6>
										<span>[필독]</span> 공지사항
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
					<!-- 으악 -->
					<table id="viewTable" class="table table-hover">
						<tr id="headTr" class="text-white">
							<th>번호</th>
							<th id="titleTr">제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
						<c:forEach var="board" items="${noticeLst}">
							<tr onclick="viewBoard(${board.bno});">
								<th>${board.bno }</th>
								<th><c:choose>
										<c:when test="${board.likeCount != 0}">
							${board.title } (${board.readCount})
						</c:when>
										<c:otherwise>
							${board.title } 
						</c:otherwise>
									</c:choose></th>
								<th>${board.nickName }</th>
								<th>${board.readCount }</th>
								<!-- <th>${board.likeCount }</th> -->
								<th><fmt:formatDate value="${board.writtenDate }"
										pattern="yyyy-MM-dd HH:mm" /></th>
							</tr>
						</c:forEach>
					</table>
					<!-- 으악 -->
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->




	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
