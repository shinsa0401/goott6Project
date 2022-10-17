<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="Ogani Template">
	<meta name="keywords" content="Ogani, unica, creative, html">
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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    
    
<title>리뷰 수정</title>
<script>

	$(function () {
		//파일 이벤트 방지
		$("#reviewImg").on("dropenter dragover", function (event) {
			event.preventDefault();
		});
		//파일 들어오면
		$("#reviewImg").on("drop", function(evt) {
			evt.preventDefault();
			let files = evt.originalEvent.dataTransfer.files;
			
			let prodNo = $("#prodNo").val();
			console.log(prodNo);
			
			let formData = new FormData();
			
			formData.append("file", files[0])
			let url = "/prodReply2/addReviewImg/"+prodNo;
			
			$.ajax({
		         url : url, // 데이터 송수신될 주소 
		         type : "post", // 통신 방식(get, post)
		         dataType : "json", // 수신받을 데이터 타입
		         processData : false, //전송하는 데이터를 텍스트 변환하지 않는다
		         contentType : false, // 기본값(application/x-www-form-urlencoded)를 사용하지 않는다 : 폼태그에서 multipart를 지정
				 data : formData, // 전송할 데이터
		         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
		            console.log(data);
		         	showImg(data);
		         }
		      });
		});
		
		//별 초기 설정
		starChange();
		
		
	});
	
	//사진 파싱하여 보여주기
	function showImg(data) {
		let output = "";
		output += "<img src='/resources/uploads/reviewImg"+data.thumbnailName + "' />";
		output += "<img src='/resources/img/delete.png' id='"+data.imgName+"' width=15px; onclick='delImg(this);'>";
		
		$("#reviewImg").append(output);
	}
	
	//사진 지우는 메서드
	function delImg(obj) {
		let deleteImgName = $(obj).attr("id");
		let url = "/prodReply2/deleteImgFromLst";
		
		$.ajax({
	         url : url, // 데이터 송수신될 주소 
	         type : "post", // 통신 방식(get, post)
	         dataType : "text", // 수신받을 데이터 타입 text라서 boardcontroller의 메서드도 responsebody string으로 리턴함
			 data : {"deleteImgName": deleteImgName}, // 전송할 데이터
			 async:false,
	         success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
	            console.log(data);
	        	$(obj).prev().remove(); //파일화면에서 지움
	 	        $(obj).remove(); //마이너스 버튼 지움
	        	 
	         }
	      });
	}

	//별점 채우는 함수
	function starChange() {
		let coloredStar = $("#assess").val();
		
		for(let i =0 ; i < coloredStar; i++){
			$("#stars i").eq(i).css("color","orange");
			for(let j = coloredStar; j<=5;j++){
				$("#stars i").eq(j).css("color","gray");
			}
		}
	}
	
	function isValid() {
		let returnVal = false;
		
		let userId = $("#userId").val();
		let title = $("#title").val();
		let content = $("#reviewContent").val();
		
		if (userId != "" && title != "" && content != ""){
			returnVal = true;
		}else{
			alert("제목과 내용을 입력해주세요");
		}
		
		return returnVal;
	}
	
	
</script>
<style>
.star{
	color: gray;
	position: relative;
	font-size: 50px
}
.starRate{
	opacity: 0;
	position: absolute;
	width: 270px;
	height: 75px;
}
#stars{
	
	position: absolute;
}
.wrtieReview{
	margin: 70px;
}

</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
                <form action="/prodReply2/modifyReview" method="post">
                    <div class="row">
                        <div class="col-lg-12 col-md-6">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="checkout__input">
                                        <p>닉네임<span>*</span></p>
                                        <input type="text" name="nickName" /> <!-- value에 ajax로 데이터 가져와서 넣기 -->
                                        <input type="hidden" name="userId" value="${review.writer }" readonly="readonly" id="userId" />아이디 <!-- value에 세션의 유저아이디넣기 -->
                                        <input type="hidden" name="reviewNo" value="${review.reviewNo }" readonly="readonly" />
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>구매상품<span>*</span></p>
                                <input type="text" name="prodName"> <!-- value에 ajax로 데이터 가져와서 넣기 -->
                                구매상품번호<input type="hidden" name="prodNo" value="${review.prodNo }" id="prodNo"> <!-- 쿼리스트링에서가져오기? -->
                            </div>
                            <div class="checkout__input">
                                <p>제목<span>*</span></p>
                                <input type="text" placeholder="제목을 입력하세요" class="checkout__input__add" id="title" name="title" value="${review.title }">
                            </div>
                            <div class="checkout__input">
                                <p>사진</p>
                                <div id="reviewImg" class="form-control" rows="5" style="height: 100px; resize:none;">사진을 드래그 드랍 해주세요
                                	<c:if test="${not empty imgs }">
                                		<c:forEach var="img" items="${imgs}">
                                			<img src='/resources/uploads/reviewImg${img.thumbnailName }' />
                                			<img src='/resources/img/delete.png' id='${img.imgName }' width=15px; onclick='delImg(this);'>
                                		</c:forEach>
                                	
                                	</c:if>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>내용<span>*</span></p>
                                <textarea id="reviewContent" name="content" class="form-control" rows="5" style="height: 200px; resize:none;">${review.content }</textarea>
                            </div>
                            <div class="checkout__input stars" >
                                <p>별점</p>
                                <div class="product__details__rating star">
                                	<div id="stars">
                                		<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i></div>
                                    
                                    <div class="starRate"><input type="range" min="1" max="5" step="1" value="5" onclick="starChange();" id="assess" name="assess" value="${review.assess }" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="wrtieReview"><button type="submit" class="site-btn" onclick="return isValid();">리뷰 수정</button>
                    <button type="reset" class="site-btn" onclick="location.href='/product/category/detail?prodNo=${param.prodNo}'" style="background-color: orange" >취소</button></div>
                </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->

   
    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
	
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
