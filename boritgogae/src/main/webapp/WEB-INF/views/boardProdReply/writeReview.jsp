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
    
    
<title>?????? ??????</title>
<script>
	$(function () {
		//?????? ????????? ??????
		$("#reviewImg").on("dropenter dragover", function (event) {
			event.preventDefault();
		});
		//?????? ????????????
		$("#reviewImg").on("drop", function(evt) {
			evt.preventDefault();
			let files = evt.originalEvent.dataTransfer.files;
			
			
			
			let formData = new FormData();
			
			formData.append("file", files[0])
			let url = "/prodReply2/addReviewImg/${param.prodNo}";
			
			$.ajax({
		         url : url, // ????????? ???????????? ?????? 
		         type : "post", // ?????? ??????(get, post)
		         dataType : "json", // ???????????? ????????? ??????
		         processData : false, //???????????? ???????????? ????????? ???????????? ?????????
		         contentType : false, // ?????????(application/x-www-form-urlencoded)??? ???????????? ????????? : ??????????????? multipart??? ??????
				 data : formData, // ????????? ?????????
		         success : function(data) { // ????????? ???????????? ??? ???????????? ????????????
		            console.log(data);
		         	showImg(data);
		         }
		      });
		});
		
	});
	
	//?????? ???????????? ????????????
	function showImg(data) {
		let output = "";
		output += "<img src='/resources/uploads/reviewImg"+data.thumbnailName + "' />";
		output += "<img src='/resources/img/delete.png' id='"+data.imgName+"' width=15px; onclick='delImg(this);'>";
		
		$("#reviewImg").append(output);
	}
	
	//?????? ????????? ?????????
	function delImg(obj) {
		let deleteImgName = $(obj).attr("id");
		let url = "/prodReply2/delImg";
		
		$.ajax({
	         url : url, // ????????? ???????????? ?????? 
	         type : "post", // ?????? ??????(get, post)
	         dataType : "text", // ???????????? ????????? ?????? text?????? boardcontroller??? ???????????? responsebody string?????? ?????????
			 data : {"deleteImgName": deleteImgName}, // ????????? ?????????
			 async:false,
	         success : function(data) { // ????????? ???????????? ??? ???????????? ????????????
	            console.log(data);
	        	$(obj).prev().remove(); //?????????????????? ??????
	 	        $(obj).remove(); //???????????? ?????? ??????
	        	 
	         }
	      });
	}

	//?????? ????????? ??????
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
			alert("????????? ????????? ??????????????????");
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
                <form action="/prodReply/addReview" method="post">
                    <div class="row">
                        <div class="col-lg-12 col-md-6">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="checkout__input">
                                        <p>?????????<span>*</span></p>
                                        <input type="text" name="nickName"> <!-- value??? ajax??? ????????? ???????????? ?????? -->
                                        <input type="text" name="userId" id="userId">????????? <!-- value??? ????????? ????????????????????? -->
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>????????????<span>*</span></p>
                                <input type="text" name="prodName"> <!-- value??? ajax??? ????????? ???????????? ?????? -->
                                ??????????????????<input type="hidden" name="prodNo" value="${param.prodNo }" id = "prodNo"> <!-- ?????????????????????????????????? -->
                            </div>
                            <div class="checkout__input">
                                <p>??????<span>*</span></p>
                                <input type="text" placeholder="????????? ???????????????" class="checkout__input__add" id="title" name="title">
                            </div>
                            <div class="checkout__input Img">
                                <p>??????</p>
                                <div id="reviewImg" class="form-control" rows="5" style="height: 100px; resize:none;">????????? ????????? ?????? ????????????</div>
                            </div>
                            <div class="checkout__input">
                                <p>??????<span>*</span></p>
                                <textarea id="reviewContent" name="reviewContent" class="form-control" rows="5" style="height: 200px; resize:none;"></textarea>
                            </div>
                            <div class="checkout__input stars" >
                                <p>??????</p>
                                <div class="product__details__rating star">
                                	<div id="stars">
                                		<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i>
                                    	<i class="fa fa-star"></i></div>
                                    
                                    <div class="starRate"><input type="range" min="1" max="5" step="1" value="5" onclick="starChange();" id="assess" name="assess">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="wrtieReview"><button type="submit" class="site-btn" onclick="return isValid();">?????? ??????</button>
                    <button type="reset" class="site-btn" onclick="location.href='/product/category/detail?prodNo=${param.prodNo}'" style="background-color: orange">??????</button></div>
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
