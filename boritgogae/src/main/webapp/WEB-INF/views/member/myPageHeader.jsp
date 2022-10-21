<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPageHeader</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	
</script>
<style>
</style>
</head>
<body>
	<div class="container p-5 my-5 text-white"
		style="background-color: #7FAD39; padding: 20px;">
		<div style="display: block;">
			<a style="font-size: 22px">마이페이지</a><a
						style="font-size: 8px">&nbsp;&nbsp;회원정보수정</a>
			<p>&nbsp;</p>
		</div>
		<div class="row row-cols-2">
			<div class="col-2" style="float: left">
				<div class="container-fluid">
					<img src="../../../resources/img/myPage/myPage_person.png"
						style="width: 100px; display: inline-block;" class="rounded-pill">
				</div>
			</div>
			<div class="col-4">
				<div>
					<a style="font-size: 36px">닉네임</a><a style="font-size: 10px">&nbsp;&nbsp;가입일
						2012.12.12</a>
				</div>
				<div>
					<a style="font-size: 20px">&nbsp;&nbsp;등급</a><a
						style="font-size: 10px">&nbsp;&nbsp;다음 등급까지 210점 남았습니다.</a>
				</div>
			</div>
			<div class="col-2">
				<div>
					<img src="../../../resources/img/myPage/myPage_point.png"
						style="width: 25px; display: inline-block;" >
				</div>
				<div>
					<a style="font-size: 14px">포인트</a>
				</div>
				<div>
					<a style="font-size: 26px">2500</a>
				</div>
			</div>
						<div class="col-2">
				<div>
					<img src="../../../resources/img/myPage/myPage_coupon.png"
						style="width: 25px; display: inline-block;">
				</div>
				<div>
					<a style="font-size: 14px">쿠폰</a>
				</div>
				<div>
					<a style="font-size: 26px">4</a>
				</div>
			</div>
						<div class="col-2">
				<div>
					<img src="../../../resources/img/myPage/myPage_review.png"
						style="width: 25px; display: inline-block;">
				</div>
				<div>
					<a style="font-size: 14px">후기</a>
				</div>
				<div>
					<a style="font-size: 26px">2</a>
				</div>
			</div>
		</div>

	</div>
</body>
</html>