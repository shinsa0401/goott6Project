<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPageNav</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<link
	rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script type="text/javascript">
	var changeInfoCode = 0;
	var uploadFileQty = 0;
	let exchangeBoxArr = [];
	let formData = new FormData();
	let orderCancleOrderNo = 0;
	
	
	$(document).ready(
			function() {
				$("#alert-success-email").hide();
				$("#alert-danger-email").hide();
				showUserInfo();
				$(".dropdown-toggle").dropdown();

				$('.pwdHideDiv i').on(
						'click',
						function() {
							$('input').toggleClass('active');
							if ($('input').hasClass('active')) {
								$(this).attr('class', "fa fa-eye-slash fa-lg")
										.prev('input').attr('type', "text");
							} else {
								$(this).attr('class', "fa fa-eye fa-lg").prev(
										'input').attr('type', 'password');
							}
						});

				$('.filebox .upload-hidden').on('change',function() {
							if (window.FileReader) {
								var filename = $(this)[0].files[0].name;
								if (!validFileType(filename)) {
									notificationModalOpen("허용하지 않는 확장자 파일입니다.");
									return false;
								} else {
									if (!validFileSize($(this)[0].files[0])) {
										notificationModalOpen("파일 사이즈가 10MB를 초과합니다.");
										return false;
									} else {
										if (!validFileNameSize(filename)) {
											notificationModalOpen("파일명이 30자를 초과합니다.");
											return false;
										}
									}
								}
							} else {
								var filename = $(this).val().split('/').pop()
										.split('\\').pop();
							}
							$("#previewImg").css("display","flex");
							$(this).prev().val(filename); //input upload-name 에 파일명 설정해주기
							readImage($(this)[0]); //미리보기
							console.log($(this));
							console.log($(this)[0].files[0]);
							formData.delete("upfile");
							formData.append("upfile", $(this)[0].files[0]);							
						});
				
				//인증번호 이메일 전송
				$("#mail_check_button").on("click",function(e){
					e.preventDefault();
					var email = $("input[name='memberEmail']").val();
					var checkBox = $(".mail_check_input");
					
					$.ajax({
						type:"GET",
						url : "/member/mailCheck",
						data : {email : email},
						contentType :"text/plain;charset=UTF-8",
						success : function(data){ //인증번호를 가져옴
							checkBox.attr("disabled",false); //인증번호 입력 가능
							checkBox.val(''); // 기존에 값이 있었으면 지워줌
							$("#alert-success-email").hide();
							$("#alert-danger-email").hide();
							checkCode = false;
							code = data; // 인증번호를 변수에 저장
							emailAddress = email;
						}
					});
				});
						
				//인증코드 입력 시 동일성 확인
				$(".mail_check_input").keyup(function() {
					var inputCode = $(".mail_check_input").val();
					if (inputCode != "" || code != "") {
						if (inputCode == code) {
							$("#alert-success-email").show();
							$("#alert-danger-email").hide();
							$(".mail_check_input").attr("disabled",true); //인증번호 입력 멈춤
							changeMemberEmail(emailAddress)
							checkCode = true;
						} else {
							$("#alert-success-email").hide();
							$("#alert-danger-email").show();
							checkCode = false;
						}
					}
				});


			});
	
	function changeMemberEmail(emailAddress){
		
		// ajax를 통해 값을 확인
		let url = "/member/myPage/changeMemberEmail"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			data : {
				"emailAddress" : emailAddress
			}, // 전송할 데이터
			dataType : "text", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "emailUpdateSuccess") {
					emailChangeModalClose();
					showUserInfo();				
					notificationModalOpen("이메일이 변경되었습니다.");		
				} else if (data == "emailUpdateFail") {
					notificationModalOpen("정보 변경에 실패했습니다.");
				} else {
					notificationModalOpen("비밀번호 체크 실패");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

		
	// 프로필이미지 올리는 메서드
	function uploadProfilImg(){
		if($(".upload-name").val()=="선택된 파일 없음"){
			notificationModalOpen("파일등록 후 올릴 수 있습니다.");
		} else{
			let url = "/member/myPage/uploadProfilImg";
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "post", // 통신 방식(get, post)
				dataType : "text", // 수신받을 데이터 타입
				processData : false, // 전송하는 데이터를 텍스트 변환하지 않는다.
				contentType : false,
				// 기본값(application/x-www-form-urlencoded)을 사용하지 않는다.
				async : false,
				data : formData,
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					notificationModalOpen("대표이미지가 변경되었습니다");
				},
				error : function(request, status, error) {
					console.log("code: " + request.status)
					console.log("message: " + request.responseText)
					console.log("error: " + error);
				}
			});
		}
	}
	
	// 파일타입 확인하는 메서드
	function validFileType(filename) {
		const fileTypes = [ "png", "jpg", "jpeg" ];
		return fileTypes.indexOf(filename.substring(
				filename.lastIndexOf(".") + 1, filename.length).toLowerCase()) >= 0;
	}

	// 파일 사이즈 확인하는 메서드
	function validFileSize(file) {
		if (file.size > 10000000) { //10MB
			return false;
		} else {
			return true;
		}
	}
	
	// 파일 이름 길이 확인하는 메서드
	function validFileNameSize(filename) {
		if (filename.length > 30) { //30자
			return false;
		} else {
			return true;
		}
	}

	//이미지 띄우기
	function readImage(input) {
		if (input.files && input.files[0]) {
			const reader = new FileReader();
			reader.onload = function(e) {
				const previewImage = document.getElementById("previewImg");
				previewImage.src = e.target.result;
			}
			// reader가 이미지 읽도록 하기
			reader.readAsDataURL(input.files[0]);
		}
	}

	//이미지 원본 팝업 띄우기
	function popImage(url) {
		var img = new Image();
		img.src = url;
		var img_width = img.width;
		var win_width = img.width + 25;
		var img_height = img.height;
		var win = img.height + 30;
		var popup = window.open('', '_blank', 'width=' + img_width
				+ ', height=' + img_height + ', menubars=no, scrollbars=auto');
		popup.document
				.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
	}

	//opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다.
	//("팝업 API 호출 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";
	function goPopup() {
		//경로는 시스템에 맞게 수정하여 사용
		//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 //호출하게 됩니다.
		var pop = window.open("/member/jusoPopup", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
		//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
		//모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
		//실제 주소검색 URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
	}
	function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail,
			roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,
			detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn,
			buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$("#address").val(roadFullAddr);
		$("#detailAddress").val(addrDetail);
		$("#postCode").val(zipNo);
	}

	// 회원혜택을 보여주는 메서드.
	function showUserBenefit() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userPointBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userOrdersBox").hide();

		$("#userBenefitBox").show();
		let url = "/member/myPage/benefit"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>등급 및 쿠폰혜택</h3>";
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>등급</th><th>누적구매금액</th><th>적립포인트</th></tr></thead><tbody>";
						$.each(data.gradeList, function(i, e) {
							if (e.grade != null) {
								output += "<tr>";
								output += "<td>" + e.grade + "</td>";
								output += "<td>" + e.gradeValue + "</td>";
								output += "<td>" + e.reservePoint + "</td>";
								output += "</tr>";
							}
						});
						output += "<tr><td></td><td></td><td></td></tr>";
						output += "<tr><th>쿠폰발급조건</th><th>할인율</th><th>사용기한</th></tr>";
						$.each(data.couponList, function(i, e) {
							if (e.couponName != null) {
								output += "<tr>";
								output += "<td>" + e.couponName + "</td>";
								output += "<td>" + e.couponDiscount + "</td>";
								output += "<td>" + e.couponUseDate + "</td>";
								output += "</tr>";
							}
						});
						output += "</tbody></table>";
						$("#userBenefitBox").html(output);
					},
					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원의 포인트 내역을 보여주는 메서드.
	function showUserPoint() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userPointBox").show();
		let url = "/member/myPage/userPoint"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);

						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>보유 포인트 : "
								+ data.pointNow + "</h3>"
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>구분</th><th>일자</th><th>금액</th><th>관련주문번호</th></tr></thead><tbody>";
						$
								.each(
										data.pointList,
										function(i, e) {
											if (e.memberId != null) {
												output += "<tr>";
												if (e.pointNo == '1') {
													output += "<td>회원가입</td>";
												} else if (e.pointNo == '2') {
													output += "<td>구매사용</td>";
												} else if (e.pointNo == '3') {
													output += "<td>구매적립</td>";
												} else if (e.pointNo == '4') {
													output += "<td>이벤트적립</td>";
												}else if (e.pointNo == '5') {
													output += "<td>주문취소/교환환불</td>";
												}
												let pointSavedate = new Date(
														e.pointSaveDate);
												let formatPointSavedate = formatDate(pointSavedate);
												output += "<td>"
														+ formatPointSavedate
														+ "</td>";
												output += "<td>"
														+ e.pointHistory
														+ "</td>";
												output += "<td>" + e.orderNo
														+ "</td>";
												output += "</tr>";
											}
										});
						output += "</tbody></table>";
						$("#userPointBox").html(output);
					},

					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원의 쿠폰 내역을 보여주는 메서드.
	function showUserCoupon() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userBenefitBox").hide();
		$("#userPointBox").hide();
		$("#userReviewBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userCouponBox").show();
		let url = "/member/myPage/userCoupon"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>보유쿠폰 : "
								+ data.couponHaveList.length + "</h3>"
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>보유쿠폰명</th><th>사용일자</th><th>발급일자</th><th>발급사유</th><th>관련주문번호</th></tr></thead><tbody>";
						$.each(data.couponHaveList, function(i, e) {
							if (e.couponName != null) {
								output += "<tr>";
								output += "<td>" + e.couponName + "</td>";
								if (e.useDate == null) {
									output += "<td>-</td>";
								} else {
									let date = new Date(e.useDate);
									let formatDate = formatDate(date);
									output += "<td>" + formatDate + "</td>";
								}
								let issueDate = new Date(e.issueDate);
								let formatIssueDate = formatDate(issueDate);
								output += "<td>" + formatIssueDate + "</td>";
								output += "<td>" + e.couponWhy + "</td>";
								output += "<td>" + e.orderNo + "</td>";
								output += "</tr>";
							}
						});
						output += "<tr><td></td><td></td><td></td><td></td><td></td></tr>";
						output += "<tr><th>사용쿠폰명</th><th>사용일자</th><th>발급일자</th><th>발급사유</th><th>관련주문번호</th></tr></thead><tbody>";
						$.each(data.couponUsedList, function(i, e) {
							if (e.couponName != null) {
								output += "<tr>";
								output += "<td>" + e.couponName + "</td>";
								let useDate = new Date(e.useDate);
								let formatUseDate = formatDate(useDate);
								output += "<td>" + formatUseDate + "</td>";
								let issueDate = new Date(e.issueDate);
								let formatIssueDate = formatDate(issueDate);
								output += "<td>" + formatIssueDate + "</td>";
								output += "<td>" + e.couponWhy + "</td>";
								output += "<td>" + e.orderNo + "</td>";
								output += "</tr>";
							}
						});
						output += "</tbody></table>";
						$("#userCouponBox").html(output);
					},
					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원이 쓴 글 내역을 보여주는 메서드.
	function showUserBoard() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userReplyBox").hide();
		$("#userPointBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userBoardBox").show();
		let url = "/member/myPage/userBoard"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>내가 작성한 글</h3>"
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>게시판명</th><th>제목</th><th>작성일</th><th>조회수</th></tr></thead><tbody>";
						$
								.each(
										data.userBoardList,
										function(i, e) {
											if (e.writer != null) {
												output += "<tr>";
												output += "<td>" + e.board
														+ "</td>";
												output += "<td>" + e.title
														+ "</td>";
												let writtenDate = new Date(
														e.writtenDate);
												let formatWrittenDate = formatDate(writtenDate);
												output += "<td>"
														+ formatWrittenDate
														+ "</td>";
												output += "<td>" + e.readCount
														+ "</td>";
												output += "</tr>";
											}
										});
						output += "</tbody></table>";
						$("#userBoardBox").html(output);
					},

					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원이 쓴 댓글 내역을 보여주는 메서드.
	function showUserReply() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userReviewBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userReplyBox").show();
		let url = "/member/myPage/userReply"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>내가 작성한 댓글</h3>"
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>게시판명</th><th>내용</th><th>작성일</th></tr></thead><tbody>";
						$.each(data.userReplyList,function(i, e) {
							if (e.memberId != null) {
								output += "<tr>";
								output += "<td>" + e.board
										+ "</td>";
								output += "<td>" + e.contents
										+ "</td>";
								let writtenTime = new Date(
										e.writtenTime);
								let formatWrittenTime = formatDate(writtenTime);
								output += "<td>"
										+ formatWrittenTime
										+ "</td>";
								output += "</tr>";
							}
						});
						output += "</tbody></table>";
						$("#userReplyBox").html(output);
					},

					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원이 쓸 수 있는 후기와 쓴 후기 내역을 보여주는 메서드.
	function showUserReview() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userInfoBox").hide();
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userReviewBox").show();
		let url = "/member/myPage/userReview"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						let output = "";
						output += "<br></br>";
						output += "<h3 style='color:#7FAD39;'>내가 작성한 리뷰</h3>"
						output += "<br></br>";
						output += "<table class='table'>";
						output += "<thead class='table-success'>";
						output += "<table class='table'>";
						output += "<tr><th>주문번호</th><th>상품명</th><th>제목</th><th>작성일</th><th>추천수</th></tr></thead><tbody>";
						$
								.each(
										data.userReviewList,
										function(i, e) {
											if (e.writer != null) {
												output += "<tr>";
												output += "<td>"
														+ e.orderDetailNo
														+ "</td>";
												output += "<td>" + e.content
														+ "</td>";
												output += "<td>" + e.title
														+ "</td>";
												let writtenDate = new Date(
														e.writtenDate);
												let formatwrittenDate = formatDate(writtenDate);
												output += "<td>"
														+ formatwrittenDate
														+ "</td>";
												output += "<td>" + e.likeCnt
														+ "</td>";
												output += "</tr>";
											}
										});
						output += "</tbody></table>";
						$("#userReviewBox").html(output);
					},

					error : function(e) {
						console.log(e);
					}
				});
	}

	// 회원정보 수정을 위해 회원정보를 가져오는 메서드.
	function showUserInfo() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userReviewBox").hide();
		$("#userOrdersBox").hide();

		// div 보여주기
		$("#userInfoBox").show();
		let url = "/member/myPage/userInfo"
		$
				.ajax({
					url : url, // 데이터 송수신될 주소 
					type : "get", // 전송 방식
					dataType : "json",
					success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
						console.log(data);
						if (data.secretInfo == "N") {
							data.memberInfo.memberName = secretCode(
									data.memberInfo.memberName, 1, 2);
							data.memberInfo.phoneNumber = secretCode(
									data.memberInfo.phoneNumber, 3, 7);
							data.memberInfo.memberEmail = secretCode(
									data.memberInfo.memberEmail, 2, 5);
						}
						let output = "";
						output += "<div class='container mt-3'>";
						output += "<h2 style='color:#7FAD39;'>회원정보</h2>";
						if (data.secretInfo == "N") {
							output += "<p><a href='javascript:secretInfoModalOpen()' style='color: black; text-decoration-line: none;'>가려진 정보 보기</a></p>";
						}
						output += "<table class='table table-borderless'>";
						output += "<tr>";
						output += "<th>이미지</th>";
						if(data.memberInfo.memberImg == "img/noImg.jpg"){
							output += "<td><img src='../../../resources/img/myPage/myPage_person.png'style='height : 100px; width: 100px; display: inline-block;' class='rounded-pill'></td>";
						} else {
							output += "<td><img src='../../../resources/members/uploads" + data.memberInfo.memberImg +"'style='height : 100px; width: 100px; display: inline-block;' class='rounded-pill'></td>";							
						}
						output += "<td><button type='button' class='btn btn-light' onclick='imgModalOpen();'>이미지 변경</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>아이디</th>";
						output += "<td>" + data.memberInfo.memberId + "</td>";
						output += "<td><button type='button' class='btn btn-light' style='visibility:hidden;'>높이 조정용</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>비밀번호</th>";
						output += "<td>********</td>";
						output += "<td><button type='button' class='btn btn-light' onclick='changeInfo(1)'>비밀번호 변경</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>이름(실명)</th>";
						output += "<td>" + data.memberInfo.memberName + "</td>";
						output += "<td><button type='button' class='btn btn-light' style='visibility:hidden;'>높이 조정용</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>가입일자</th>";
						let joinDate = new Date(data.memberInfo.joinDate);
						let formatJoinDate = formatDate2(joinDate);
						output += "<td>" + formatJoinDate + "</td>";
						output += "<td><button type='button' class='btn btn-light' style='visibility:hidden;'>높이 조정용</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>생년월일</th>";
						let birthDay = new Date(data.memberInfo.birthDay);
						let formatBirthDay = formatDate2(birthDay);
						if (data.secretInfo == "N") {
							formatBirthDay = secretCode(formatBirthDay, 2, 10);
						}
						output += "<td>" + formatBirthDay + "</td>";
						output += "<td><button type='button' class='btn btn-light' style='visibility:hidden;'>높이 조정용</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>닉네임</th>";
						output += "<td>" + data.memberInfo.nickName + "</td>";
						output += "<td><button type='button' class='btn btn-light' onclick='changeInfo(2)'>닉네임 변경</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>이메일</th>";
						output += "<td>" + data.memberInfo.memberEmail
								+ "</td>";
						output += "<td><button type='button' class='btn btn-light' onclick='emailChangeModalOpen()'>이메일 변경</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>전화번호</th>";
						output += "<td>" + data.memberInfo.phoneNumber
								+ "</td>";
						output += "<td><button type='button' class='btn btn-light' onclick='changeInfo(3)'>전화번호 변경</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th>배송지</th>";
						output += "<td></td>";
						output += "<td><button type='button' class='btn btn-light' onclick='changeInfo(4)'>배송지 확인/수정</button></td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th></th>";
						output += "<td></td>";
						output += "<td>&nbsp;</td>";
						output += "</tr>";
						output += "<tr>";
						output += "<th></th>";
						output += "<td></td>";
						output += "<td><button type='button' class='btn btn-danger' onclick='changeInfo(5);'>회원탈퇴</button></td>";
						output += "</tr>";
						output += "</table>";
						output += "</div>";
						$("#userInfoBox").html(output);
					},

					error : function(e) {
						console.log(e);
					}
				});
	}

	
	
	
	// 회원의 구매내역을 가져오는 메서드. (각오합시다)
	function showOrders() {
		// 여기서 다른 DIV들 숨겨야 한다.
		$("#userBenefitBox").hide();
		$("#userCouponBox").hide();
		$("#userPointBox").hide();
		$("#userBoardBox").hide();
		$("#userReplyBox").hide();
		$("#userReviewBox").hide();
		$("#userInfoBox").hide();

		// div 보여주기
		$("#userOrdersBox").show();
		let url = "/member/myPage/orders"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				$.each(data, function(i, e){
					console.log("e");
					console.log(e); // 76, 77
						$.each(e, function(i2, e2){
							let deliveryStatusPaymentCompleted = true;
							let deliveryStatusDeliveryCompleted = true;
							let deliveryStatusOrderCancle = true;
							let deliveryStatusPurchaseConfirm = true;
							let deliveryStatusExchange = false; 
							
							$.each(e2.TotalOrderListVo, function(i3, e3){
								if(e3.deliveryStatus != "결제완료"){
									deliveryStatusPaymentCompleted = false;
								}
								if(e3.deliveryStatus != "배송완료"){
									deliveryStatusDeliveryCompleted = false;
								}
								if(e3.deliveryStatus != "취소" || e3.deliveryStatus != "교환요청" || e3.deliveryStatus != "반품요청"  ){
									deliveryStatusOrderCancle = false;
								}
								if(e3.deliveryStatus != "구매확정"){
									deliveryStatusPurchaseConfirm = false;
								}
								if(e3.deliveryStatus == "반품요청" || e3.deliveryStatus == "교환요청" ){
									deliveryStatusExchange = true;
								}
							});
							console.log("e2");
							console.log(e2);
							output += "<div><table class='table' style='table-layout: fixed;'>";
							output += "<colgroup><col width='8%'><col width='14%'><col width='27%'><col width='12%'><col width='15%'><col width='10%'><col width='14%'></colgroup>";
							output += "<thead><tr class='table-success'><th>주문번호</th><th>상품사진</th><th>상품명</th><th>주문일자</th><th>주문금액(수량)</th><th>주문상태</th><th>교환/반품 체크</th></tr></thead>";
							output += "<tbody>";
							var flag = false;
							$.each(e2.TotalOrderListVo, function(i3, e3){
								console.log("e3");
								console.log(e3);
								
								output += "<tr class='table-Light'>";
								if(flag == false){
									output += "<td rowspan='"+e2.TotalOrderListVo.length+"'align=center>" + e2.orders.orderNo +"</td>";
									flag = true;
								}
								output += "<td><img src='"+e3.originalFile+"' style='width:100px; height:100px;' id='prodImg"+e3.orderDetailNo+"'></td>";
								output += "<td id='prodName"+e3.orderDetailNo+"'>" + e3.prodName +"</td>";							
								let orderDate = new Date(e2.orders.orderDate);
								let formatOrderDate = formatDate2(orderDate);
								output += "<td>" + formatOrderDate + "</td>";
								
								output += "<td id='prodPrice"+e3.orderDetailNo+"'>" + e3.prodSubTotalPrice+ "(" + e3.qty+ ")"  +"</td>";
								output += "<td>" + e3.deliveryStatus +"</td>";
								// 배송완료 상품 + 교환반품 없을시 + 교환완료+반품완료 체크박스 오픈.
								if(deliveryStatusDeliveryCompleted == true && deliveryStatusExchange != true){ 
									output += "<td align='center' valign='middle'><input type='checkbox' class='form-check-input' name='checkBox"+e2.orders.orderNo+"' value='" + e3.orderDetailNo + "'></td>";
								}else{
									output += "<td></td>";
								}
								
								output += "</tr>";
							});
							
							if(deliveryStatusOrderCancle == false){
								output += "<tr class = 'table-secondary'><td></td><td>상품총액 : "+ e2.orders.prodTotalPrice + "</td><td colspan='2'>" + "쿠폰사용 : - "; 
								if(e2.usedCoupon == null){
									output += 0;
								} else if(e2.usedCoupon.couponName == "생일쿠폰"){
									output += "<span id='usedCoupon"+e2.orders.orderNo+"' value = '0.1'>" + e2.orders.prodTotalPrice * 0.1 + "</span>";
								} else if(e2.usedCoupon.couponName == "이벤트쿠폰A"){
									output += "<span id='usedCoupon"+e2.orders.orderNo+"' value = '0.05'>" + e2.orders.prodTotalPrice * 0.05 + "</span>";
								} else if(e2.usedCoupon.couponName == "이벤트쿠폰B"){
									output += "<span id='usedCoupon"+e2.orders.orderNo+"' value = '0.1'>" + e2.orders.prodTotalPrice * 0.1 + "</span>";
								} else{
									output += 0;
								}
								
								output += "&nbsp;&nbsp;&nbsp;&nbsp;포인트사용 : - "+ e2.orders.usedPoint + "&nbsp;&nbsp;&nbsp;&nbsp;배송비 : + ";
								if(e2.orders.deliveryOption == "반품"){
									output += "6000"
								}else if(e2.orders.deliveryOption == "관리"){
									output += "3000"
								}else if(e2.orders.deliveryOption == "무료"){
									output += "3000"
								}else if(e2.orders.deliveryOption == "도서"){
									output += "8000"
								}else{
									output += "3000"
								}
								output += "</td><td>구매액 : "+ e2.orders.totalPrice + "</td><td colspan='2'>";
								// 구매확정이라면
								if(deliveryStatusPurchaseConfirm == true){
									output += "<button type='button'class='btn btn-info btn-sm'>리뷰쓰기</button>";									
								} else if(deliveryStatusPaymentCompleted == true){
									output += "<button type='button' class='btn btn-danger btn-sm' onclick='orderCancleModalOpen("+ e2.orders.orderNo +")'>주문취소</button>&nbsp;";
								} else if(deliveryStatusDeliveryCompleted == true){
									output += "<button type='button' class='btn btn-primary btn-sm' onclick='orderRtnOrExModalOpen("+ e2.orders.orderNo +")'>교환/반품</button>&nbsp;";
									output += "<button type='button'class='btn btn-success btn-sm' onclick='orderPurchaseConfirm("+ e2.orders.orderNo +")'>구매확정</button>";
								} else if(deliveryStatusExchange == true){
									output += "<span>교환 및 반품이 진행중인 거래입니다.</span>";
									output += "<button type='button'class='btn btn-success btn-sm' onclick='orderRtnOrExConfirm("+ e2.orders.orderNo +")'>교환 및 반품 승인</button>";
								}
								output += "</td>";
								output += "</tr>";
							}
							
							output += "</tbody>";
							output += "</table></div>";
						});
				});

				$("#userOrdersBox").html(output);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	
	

	// 날짜 변환 함수
	function formatDate(date) {
		let year = date.getFullYear();
		let month = ('0' + (date.getMonth() + 1)).slice(-2);
		let day = ('0' + date.getDate()).slice(-2);
		let hours = ('0' + date.getHours()).slice(-2);
		let minutes = ('0' + date.getMinutes()).slice(-2);
		let seconds = ('0' + date.getSeconds()).slice(-2);
		let timeString = year + '-' + month + '-' + day + '&nbsp' + hours + ':'
				+ minutes + ':' + seconds;
		return timeString;
	}

	// 날짜 변환 함수2
	function formatDate2(date) {
		let year = date.getFullYear();
		let month = ('0' + (date.getMonth() + 1)).slice(-2);
		let day = ('0' + date.getDate()).slice(-2);
		let timeString = year + '-' + month + '-' + day;
		return timeString;
	}

	// 가려진 정보 쿠키 모달 열기
	function secretInfoModalOpen() {
		$("#secretInfoModal").css('display', 'flex');
		//$("#secretInfoModalContent").text(contents);
	}

	// 가려진 정보 쿠키 모달 닫기
	function secretInfoModalClose() {
		$("#secretInfoModal").css('display', 'none');
		$("#secretInfoValidateContent").text("");
		$("#secretInfoModalContentPassword").val("");
	}

	// 주문취소 모달 열기
	function orderCancleModalOpen(orderNo) {
		$("#orderCancleModal").css('display', 'flex');
		orderCancleOrderNo = orderNo;
		console.log("주문취소요청번호 : " + orderNo);
	}

	// 주문취소 모달 닫기
	function orderCancleModalClose() {
		$("#orderCancleModal").css('display', 'none');
	}	
	
	// 이메일 추가 모달 열기
	function emailChangeModalOpen() {
		$("#emailChangeModal").css('display', 'flex');
	}	

	// 이메일 추가 모달 닫기
	function emailChangeModalClose() {
		$("#emailChangeModal").css('display', 'none');
		$("#alert-success-email").hide();
		$("#alert-danger-email").hide();	
		$("#memberEmail").text("");
		$(".mail_check_input").text("");	
		$("#memberEmail").val('');
		$(".mail_check_input").val('');
	}
	
	
	// 주소지 추가 모달 열기
	function addAddrModalOpen() {
		$("#addAddrModal").css('display', 'flex');
	}

	// 주소지 추가 모달 닫기
	function addAddrModalClose() {
		$("#addAddrModal").css('display', 'none');
		$("#address").val('');
		$("#detailAddress").val('');
		$("#postCode").val('');
		$("#recipient").val('');
		$("#recipientPhoneNumber").val('');
		$("#addAddrModalWarning").text("");
	}

	// 이미지 모달 열기
	function imgModalOpen() {
		$("#imgModal").css('display', 'flex');
	}

	// 이미지 추가 모달 닫기
	function imgModalClose() {
		$("#imgModal").css('display', 'none');
	}

	// 정보암호화
	function secretCode(code, firstPlace, lastPlace) {
		let ch = '*';
		let repeat = lastPlace - firstPlace;
		let str = code.substring(0, firstPlace);
		for (let i = 0; i < repeat; i++) {
			str += ch;
		}
		str += code.substring(lastPlace);
		return str;
	}

	// 가려진 정보 모달 유효성검사
	function secretInfoValidate() {
		// 유효성 검사
		let pwd = "";
		let isValid = false;
		if ($("#secretInfoModalContentPassword").val() == "") {
			$("#secretInfoValidateContent").text("비밀번호를 입력해주세요");
		} else if ($("#secretInfoModalContentPassword").val().length > 20) {
			$("#secretInfoValidateContent").text("20자 미만으로 입력해주세요");
		} else if ($("#secretInfoModalContentPassword").val().search(/\s/) != -1) {
			$("#secretInfoValidateContent").text("공백없이 입력해주세요");
		} else {
			isValid = true;
			pwd = $("#secretInfoModalContentPassword").val();
			console.log(pwd);
		}
		// 유효성 검사를 통과한다면		
		if (isValid == true) {
			// ajax를 통해 값을 확인
			let url = "/member/myPage/secretInfoCheck"
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "get", // 전송 방식
				data : {
					"pwd" : pwd
				}, // 전송할 데이터
				dataType : "text", // 수신할 데이터
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					if (data == "correctCheckSuccess") {
						secretInfoModalClose();
						showUserInfo();
					} else if (data == "correctCheckFail") {
						$("#secretInfoValidateContent").text("비밀번호가 틀렸습니다.");
					} else {
						notificationModalOpen("비밀번호 체크 실패");
					}
				},
				error : function(e) {
					console.log(e);
				}
			});

		}
		return isValid;
	}

	// 주문취소 메서드
	function orderCancle() {	
		// ajax를 통해 주문취소번호를 보냄.
		let url = "/member/myPage/orderCancle"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			data : {
				"orderCancleOrderNo" : orderCancleOrderNo
			}, // 전송할 데이터
			dataType : "text", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "orderCancleSuccess") {
					notificationModalOpen("주문이 취소되었습니다.");
					orderCancleModalClose();
					showOrders();
				} else if (data == "orderCancleFail") {
					notificationModalOpen("주문취소 실패");
				} else {
					notificationModalOpen("주문취소 실패");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	

	// 구매확정 메서드
	function orderPurchaseConfirm(orderNo) {	
		// ajax를 통해 주문취소번호를 보냄.
		let url = "/member/myPage/orderPurchaseConfirm"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			data : {
				"orderNo" : orderNo
			}, // 전송할 데이터
			dataType : "text", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "orderPurchaseConfirmSuccess") {
					notificationModalOpen("구매확정 되었습니다.");
				} else if (data == "orderPurchaseConfirmFail") {
					notificationModalOpen("구매확정 실패");
				} else {
					notificationModalOpen("구매확정 실패");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	
	// 회원정보 변경시 쓰는 공용 메서드 (비밀번호 확인용 모달 열기)
	function changeInfo(info) {
		changeInfoCode = info;
		$("#pwdCheckModal").css('display', 'flex');
	}

	// 비밀번호 확인용 모달 닫기
	function pwdCheckModalClose() {
		$("#pwdCheckModal").css('display', 'none');
		$("#pwdCheckModalWarning").text("");
		$("#pwdCheckModalPassword").val("");
	}

	// 유효성 검사 후 주소값 인서트하기
	function addAddr() {
		// 정규식
		let recipientCheck = /^[가-힣]{2,4}$/;
		let recipientPhoneNumberCheck = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		let addressCheck = /^.{1,100}$/;
		if (!recipientCheck.test($("#recipient").val())) {
			$("#addAddrModalWarning").text("이름을 10자 이내로 입력해 주십시오.");
		} else if (!recipientPhoneNumberCheck.test($("#recipientPhoneNumber")
				.val())) { // 전화번호 규약이 틀렸을 시
			$("#addAddrModalWarning").text("전화번호를 제대로 입력해 주십시오.");
		} else if (!addressCheck.test($("#address").val())) {
			$("#addAddrModalWarning").text("주소검색을 통해 주소를 입력해주세요");
		} else {
			let url = "/member/myPage/addAddr"
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "get", // 전송 방식
				data : {
					"address" : $("#address").val(),
					"detailAddress" : $("#detailAddress").val(),
					"postCode" : $("#postCode").val(),
					"recipient" : $("#recipient").val(),
					"recipientPhoneNumber" : $("#recipientPhoneNumber").val()
				}, // 전송할 데이터
				dataType : "text", // 수신할 데이터
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					if (data == "addAddrSuccess") {
						addAddrModalClose();
						showDeliveryInfo();
					} else if (data == "addAddrFail") {
						notificationModalOpen("주소 추가 실패");
					} else {
						notificationModalOpen("주소 추가 실패");
					}
				},
				error : function(e) {
					console.log(e);
				}
			});

		}
	}

	// 비밀번호가 맞는지 확인하는 메서드(비밀번호 쓰고 확인 눌렀을 시 작동)
	function pwdCheckModalValidate() {
		// 유효성 검사
		let pwd = "";
		let pwdValid = false;
		if ($("#pwdCheckModalPassword").val() == "") {
			$("#pwdCheckModalWarning").text("비밀번호를 입력해주세요");
		} else if ($("#pwdCheckModalPassword").val().length > 20) {
			$("#pwdCheckModalWarning").text("20자 미만으로 입력해주세요");
		} else if ($("#pwdCheckModalPassword").val().search(/\s/) != -1) {
			$("#pwdCheckModalWarning").text("공백없이 입력해주세요");
		} else {
			pwd = $("#pwdCheckModalPassword").val();
			console.log(pwd);
			// 유효성 검사를 통과한다면	ajax를 통해 값을 확인	
			let url = "/member/myPage/pwdCheck"
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "get", // 전송 방식
				data : {
					"pwd" : pwd
				}, // 전송할 데이터
				dataType : "text", // 수신할 데이터
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					if (data == "pwdCheckSuccess") {
						pwdCheckModalClose();
						pwdValid = true;
						changeInfoInput();

					} else if (data == "pwdCheckFail") {
						$("#pwdCheckModalWarning").text("비밀번호가 틀렸습니다.");
					} else {
						notificationModalOpen("비밀번호 체크 실패");
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
		return pwdValid;
	}

	// 정보변경중 비밀번호 확인이 되었기 때문에, 변경용 모달을 띄워준다.
	function changeInfoInput() {
		if (changeInfoCode == 1) { // 비번변경모달
			$("#pwdChangeModal").css('display', 'flex');
		} else if (changeInfoCode == 2 || changeInfoCode == 3) { // 비번변경모달이 아닐때
			$("#infoChangeModal").css('display', 'flex');
		} else if (changeInfoCode == 4){ // 주소 확인모달일때
			showDeliveryInfo();
		} else if (changeInfoCode == 5){ // 회원탈퇴 모달일때
			membershipWithdrawalModalOpen();
		}
	}
	
	// 회원탈퇴용 모달 열기
	function membershipWithdrawalModalOpen(){
		$("#membershipWithdrawalModal").css('display', 'flex');
	}
	
	// 회원탈퇴용 모달 닫기
	function membershipWithdrawalModalClose(){
		$("#membershipWithdrawalModal").css('display', 'none');
		$("#membershipWithdrawalCode").val(8);
		$("#membershipWithdrawalContents").val("");
	}
	
	// 회원탈퇴용 모달 열기
	function membershipWithdrawalModal2Open(){
		$("#membershipWithdrawalModal2").css('display', 'flex');
	}
	
	// 회원탈퇴용 모달 닫기
	function membershipWithdrawalModal2Close(){
		$("#membershipWithdrawalModal2").css('display', 'none');
	}

	// 회원탈퇴 메서드
	function membershipWithdrawal(){
		// 유효성 검사
		let membershipWithdrawalCode = $("#membershipWithdrawalCode").val();
		let membershipWithdrawalContents = $("#membershipWithdrawalContents").val();
		if(membershipWithdrawalContents == null){
			membershipWithdrawalContents = "사유없음";
		}

			let url = "/member/myPage/membershipWithdrawal"
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "get", // 전송 방식
				data : {
					"code" : membershipWithdrawalCode,
					"contents" : membershipWithdrawalContents
				}, // 전송할 데이터
				dataType : "text", // 수신할 데이터
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					if (data == "membershipWithdrawalSuccess"){
						notificationModalOpen("회원탈퇴가 완료되었습니다.");
					} else if (data == "membershipWithdrawalFail") {
						notificationModalOpen("회원탈퇴 실패");
					} else {
						notificationModalOpen("회원탈퇴 실패");
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
	}

	// 비밀번호 변경용 모달 닫기
	function pwdChangeModalClose() {
		$("#pwdChangeModal").css('display', 'none');
		$("#pwdChangeModalWarning").text("");
		$("#pwdChangeModalPassword").val("");
	}

	// 정보 변경용 모달 닫기
	function infoChangeModalClose() {
		$("#infoChangeModal").css('display', 'none');
		$("#infoChangeModalWarning").text("");
		$("#infoChangeModalContent").val("");
	}

	// 주소보기 모달 닫기
	function addressModalClose() {
		$("#addressModal").css('display', 'none');
	}

	// 새로운 비밀번호 제출 (changeInfoInputModalOpen)
	function submitPassword() {
		// 유효성 검사
		let newPwd = "";
		let pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
		if (!pwdCheck.test($("#pwdChangeModalPassword").val())) { // 비번 규약이 틀렸을 시
			console.log($("#pwdChangeModalPassword").val());
			$("#pwdChangeModalWarning").text(
					"비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
		} else { // 비번 규약에 맞았을 시
			newPwd = $("#pwdChangeModalPassword").val();
			console.log(newPwd);
			changeInfoAjax("memberPwd", newPwd);
		}
	}

	// 새로운 정보 제출 (submitInfo)
	function submitInfo() {
		// 유효성 검사
		let newInfo = "";
		if (changeInfoCode == 2) { // 닉네임 변경
			let infoCheck = /^.{1,20}$/;
			if (!infoCheck.test($("#infoChangeModalContent").val())) { // 닉네임 규약이 틀렸을 시
				console.log($("#infoChangeModalContent").val());
				$("#infoChangeModalWarning").text("닉네임은 20자 이내로 입력할 수 있습니다.");
			} else { // 닉네임 규약이 맞았을 시
				newInfo = $("#infoChangeModalContent").val();
				console.log(newInfo);
				changeInfoAjax("nickName", newInfo);
			}
		} else if (changeInfoCode == 3) { // 전화번호 변경
			let infoCheck = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
			if (!infoCheck.test($("#infoChangeModalContent").val())) { // 전화번호 규약이 틀렸을 시
				console.log($("#infoChangeModalContent").val());
				$("#infoChangeModalWarning").text("전화번호는 숫자만 입력할 수 있습니다.");
			} else { // 전화번호 규약이 맞았을 시
				newInfo = $("#infoChangeModalContent").val();
				console.log(newInfo);
				changeInfoAjax("phoneNumber", newInfo);
			}
		}
	}

	// 어떤 정보를 변경할지, 어떻게 변경할지 보냄.
	function changeInfoAjax(target, value) {
		// 유효성 검사를 통과한다면	ajax를 통해 값을 확인	
		let url = "/member/myPage/changeInfo"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			data : {
				"target" : target,
				"value" : value
			}, // 전송할 데이터
			dataType : "text", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "infoChangeSuccess") { // 정보변경에 성공했을 시
					pwdChangeModalClose();
					infoChangeModalClose();
					showUserInfo();
					notificationModalOpen("정보가 변경되었습니다.");
				} else if (data == "infoChangeFail") { // 정보변경에 실패했을 시
					pwdChangeModalClose();
					infoChangeModalClose();
					showUserInfo();
					notificationModalOpen("정보변경에 실패했습니다.");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	// 배송지 정보를 가져온다.(ajax를 통해서)
	function showDeliveryInfo() {
		// 모달창 열어줘야 함
		let url = "/member/myPage/showDeliveryInfo"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "json",
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				let output = "";
				$.each(data.deliveryList, function(i, e) {
					if (e.deliveryInfo != null) {
						output += "<tr onclick='deleteAddrModalOpen("
								+ e.deliveryInfo + ");'>";
						output += "<td>" + e.address + "</td>";
						output += "<td>" + e.detailAddress + "</td>";
						output += "<td>" + e.recipient + "</td>";
						output += "<td>" + e.recipientPhoneNumber + "</td>";
						output += "<td>" + e.postCode + "</td>";
						output += "</tr>";
					}
				});
				$("#deliveryContents").html(output);
				$("#addressModal").css('display', 'flex');
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	// 저장주소를 정말 삭제할것인지 한번 더 묻는 모달창
	function deleteAddrModalOpen(deliveryInfo) {
		$("#deleteDeliveryInfoNumber").val(deliveryInfo);
		$("#deleteAddrModal").css('display', 'flex');
	}

	// 저장주소 삭제용 모달 닫기
	function deleteAddrModalClose() {
		$("#deleteAddrModal").css('display', 'none');
	}

	// 교환환불용 모달 열기
	function orderRtnOrExModalOpen(orderNo){
		exchangeBoxArr.splice(0, exchangeBoxArr.length);
		let checkBoxName = "checkBox"+orderNo;
		let checkedLength = document.getElementsByName(checkBoxName).length;
		let checkBoxArr = [];
		for(let i = 0 ; i<checkedLength ; i++){
            if (document.getElementsByName(checkBoxName)[i].checked == true) {
                checkBoxArr.push(document.getElementsByName(checkBoxName)[i].value);
                exchangeBoxArr.push(document.getElementsByName(checkBoxName)[i].value);
            }
		}
		if(checkBoxArr != 0){
			console.log(checkBoxArr);
			
			let output = "";
			
			$.each(checkBoxArr, function(i, e){
				output += "<div><table class='table' style='table-layout: fixed;'>";
				output += "<colgroup><col width='30%'><col width='40%'><col width='30%'></colgroup>";
				output += "<thead><tr class='table-success'><th>상품사진</th><th>상품명</th><th>주문금액(수량)</th></tr></thead>";
				output += "<tbody>";
				
				output += "<tr class='table-Light'>";
				
				let checkBoxProdImg = "prodImg" + e; // prodName108
				let checkBoxProdImgText = document.getElementById(checkBoxProdImg).src;
				console.log(checkBoxProdImgText);
				output += "<td><img src='" + checkBoxProdImgText +"' style='width:40px; height:40px;'></td>";
				
				let checkBoxProdName = "prodName" + e; // prodName108
				let checkBoxProdNameText = document.getElementById(checkBoxProdName).innerHTML;
				output += "<td>";
				output += checkBoxProdNameText;
				output += "</td>";							

				let checkBoxProdPrice = "prodPrice" + e; // prodName108
				let checkBoxProdPriceText = document.getElementById(checkBoxProdPrice).innerHTML;
				output += "<td>"+ checkBoxProdPriceText +"</td>";
				output += "</tr>";
				output += "</tbody>";
				output += "</table></div>";
				output += "<label for='comment'>구분:</label><select class='form-select' id ='sort" + e + "'><option>교환</option><option>반품</option></select>";
				output += "<p></p>"
				output += "<label for='comment'>사유:</label><select class='form-select' id = 'why" + e + "'><option>단순변심</option><option>사이즈가 맞지 않음</option><option>원하는 색상과 다름</option><option>상품이 파손되어 도착</option><option>주문한 상품과 다름</option></select>";
				output += "<p></p>"
				output += "<label for='comment'>상세사유:</label><textarea class='form-control' rows='3' id='comment" + e +"' name='text'></textarea>"
				output += "<p></p>"
			});
			
			
			$("#orderRtnOrExModalContents").html(output);
			
			$("#orderRtnOrExModal").css('display', 'flex');
		} else{
			notificationModalOpen("교환/환불을 원하시는 상품을 체크해주세요.");
		}
	}	

	// 교환환불용 모달 닫기
	function orderRtnOrExModalClose() {
		$("#orderRtnOrExModal").css('display', 'none');
	}	
	
	
	// 저장주소를 삭제하는 메서드
	function deleteAddr(deliveryInfo) {
		let url = "/member/myPage/deleteAddr"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			data : {
				"deliveryInfo" : deliveryInfo
			}, // 전송할 데이터
			dataType : "text", // 수신할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "deleteAddrSuccess") { // 정보변경에 성공했을 시
					// 다시 정보를 가져와서 띄워준다. 확인필요함.
					showDeliveryInfo();
					deleteAddrModalClose();
				} else if (data == "deleteAddrFail") { // 정보변경에 실패했을 시
					notificationModalOpen("주소삭제에 실패했습니다.");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	// 알림용 모달 열기
	function notificationModalOpen(msg) {
		document.getElementById("notificationModal").style.display = "block";
		document.getElementById("notificationModalContent").innerHTML = msg;
	}
	
	// 알림용 모달 닫기
	function notificationModalClose() {
		if(document.getElementById("notificationModalContent").innerHTML == "대표이미지가 변경되었습니다"){
			location.href = "/member/myPage";
		}else if(document.getElementById("notificationModalContent").innerHTML == "회원탈퇴가 완료되었습니다."){
			location.href = "/";
		}else if(document.getElementById("notificationModalContent").innerHTML == "구매확정 되었습니다."){
			showOrders();
		}else if(document.getElementById("notificationModalContent").innerHTML == "쿠폰과 포인트의 경우, 남은 주문건에 대해 자동적용됩니다."){
			orderRtnOrExModalClose();
			orderRtnOrEx();
		}else if(document.getElementById("notificationModalContent").innerHTML == "교환 및 반품접수가 완료되었습니다"){
			showOrders();
		}
		
		
		document.getElementById("notificationModal").style.display = "none";
	}

	// 교환반품 요청 넣기
	function orderRtnOrEx(){
		console.log(exchangeBoxArr);
		var exchangeArray = new Array();
		
		$.each(exchangeBoxArr, function(i, e){
			var data = new Object();
			
			data.orderDetailNo = e;
			
			let exchangeSort = "sort" + e; // sort108
			let exchangeSortSelected = document.getElementById(exchangeSort);
			if(exchangeSortSelected.options[exchangeSortSelected.selectedIndex].value == "교환"){
				data.exchangeType = 'E';
			} else if(exchangeSortSelected.options[exchangeSortSelected.selectedIndex].value == "반품"){
				data.exchangeType = 'R';
			}
			
			
			let exchangeWhy = "why" + e; // exchangeWhy108
			let exchangeWhySelected = document.getElementById(exchangeWhy);
			if(exchangeWhySelected.options[exchangeWhySelected.selectedIndex].value == "단순변심"){
				data.reasonNo = 1;
			}else if(exchangeWhySelected.options[exchangeWhySelected.selectedIndex].value == "사이즈가 맞지 않음"){
				data.reasonNo = 2;
			}else if(exchangeWhySelected.options[exchangeWhySelected.selectedIndex].value == "원하는 색상이 아님"){
				data.reasonNo = 3;
			}else if(exchangeWhySelected.options[exchangeWhySelected.selectedIndex].value == "상품이 파손되어 옴"){
				data.reasonNo = 4;
			}else if(exchangeWhySelected.options[exchangeWhySelected.selectedIndex].value == "주문한 상품과 다름"){
				data.reasonNo = 5;
			}else{
				data.reasonNo = 1;				
			}
			
			let exchangeComment = "comment" + e; // comment108
			let exchangeCommentText = document.getElementById(exchangeComment).value;
			data.reasonContent = exchangeCommentText;
			
			exchangeArray.push(data);
					
		});
		console.log(exchangeArray);
		
		// 이 데이터를 보냄.
		var jsonData = JSON.stringify(exchangeArray);
		console.log(jsonData);
		
		let url = "/member/myPage/orderRtnOrEx"
			$.ajax({
				url : url, // 데이터 송수신될 주소 
				type : "post", // 전송 방식
				contentType: 'application/json',
				data : jsonData, // 전송할 데이터
				dataType : "text", // 수신할 데이터
				success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
					console.log(data);
					if (data == "orderRtnOrExSuccess") { // 정보변경에 성공했을 시
						notificationModalOpen("교환 및 반품접수가 완료되었습니다");
					} else if (data == "orderRtnOrExFail") { // 정보변경에 실패했을 시
						notificationModalOpen("교환반품에 실패했습니다");
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
	}
	
	
	
	// 추가기능
	// 교환 및 반품 승인하기
	function orderRtnOrExConfirm(orderNo){
		console.log(orderNo);
		let url = "/member/myPage/orderRtnOrExConfirm"
		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "get", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : {
				"orderNo" : orderNo
			}, // 전송할 데이터
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "orderRtnOrExConfirmSuccess") { // 정보변경에 성공했을 시
					notificationModalOpen("교환 및 반품 승인");
					showOrders();
				} else if (data == "orderRtnOrExConfirmFail") { // 정보변경에 실패했을 시
					notificationModalOpen("교환 및 반품 승인 실패");
					showOrders();
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
</script>
<style type="text/css">
div.pwdHideDiv {
	position: relative;
	padding: 20px;
}

div.pwdHideDiv input {
	width: 300px;
	height: 30px;
	border: 0;
	text-indent: 10px;
}

div.pwdHideDiv i {
	position: absolute;
	left: 75%;
	top: 27px;
}

@font-face {
	font-family: 'GangwonEdu_OTFBoldA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

html {
	font-family: 'GangwonEdu_OTFBoldA'
}

.uploadImg {
	margin: 10px;
	margin-top: 10px;
}

img#previewImg {
	cursor: pointer;
	max-height: 200px;
	max-width: 100%;
	display: block;
}

.filebox {
	display: inline-block;
	margin: 10px;
}

.filebox label {
	display: inline-block;
	padding: 4px 8px;
	margin-left: 5px;
	color: #999;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	border-radius: .25em;
	cursor: pointer;
	color: #fff;
	background-color: #895fc0;
	border: 1px solid #683d8b;
}

.filebox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.filebox .upload-name {
	display: inline-block;
	padding: 0.3em 0.4em;
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}


</style>
</head>
<body>

	<div class="container mt-3">
		<ul class="nav nav-tabs">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown">내 정보</a>
				<ul class="dropdown-menu">
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserInfo()">회원정보수정</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserBoard()">내가
							쓴 글 보기</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserReply()">내가
							쓴 댓글 보기</a></li>
				</ul></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown">쇼핑</a>
				<ul class="dropdown-menu">
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showOrders()">주문내역</a></li>
					<!-- 무진씨 링크 받기 -->
					<li><a class="dropdown-item" href="#">관심상품</a></li>
					<!--  -->
					<li><a class="dropdown-item"
						href="javascript:showUserReview()">작성후기</a></li>
					<!--  -->
					<li><a class="dropdown-item"
						href="javascript:showUserCoupon()">내 쿠폰</a></li>
					<!--  -->
					<li><a class="dropdown-item" href="javascript:showUserPoint()">포인트
							내역</a></li>
				</ul></li>
			<!--  -->
			<li class="nav-item"><a class="nav-link"
				href="javascript:showUserBenefit();" style="color: black">회원혜택</a></li>
			</li>
		</ul>
		<div id="contentsBox">
			<div id="userInfoBox" style="display: none;"></div>
			<div id="userBenefitBox" style="display: none;"></div>
			<div id="userPointBox" style="display: none;"></div>
			<div id="userCouponBox" style="display: none;"></div>
			<div id="userBoardBox" style="display: none;"></div>
			<div id="userReplyBox" style="display: none;"></div>
			<div id="userReviewBox" style="display: none;"></div>
			<div id="userOrdersBox" style="display: none;"></div>
		</div>
	</div>


	<!-- 가려진 정보를 보기 위한 비밀번호 확인용 모달 -->
	<!-- The Modal -->
	<div class="modal" id="secretInfoModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div id="secretInfoModalContent" class="modal-body">비밀번호를
					입력해주세요</div>
				<div class="pwdHideDiv">
					<input type="password" class="form-control"
						id="secretInfoModalContentPassword" placeholder="비밀번호 입력 : "><i
						class="fa fa-eye fa-lg"></i>
				</div>
				<div id="secretInfoValidateContent" class="modal-body"
					style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="secretInfoValidate();">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="secretInfoModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 비밀번호 확인용 모달 -->
	<!-- The Modal -->
	<div class="modal" id="pwdCheckModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">비밀번호를 입력해주세요</div>
				<div class="pwdHideDiv">
					<input type="password" class="form-control"
						id="pwdCheckModalPassword" placeholder="비밀번호 입력 : "><i
						class="fa fa-eye fa-lg"></i>
				</div>
				<div id="pwdCheckModalWarning" class="modal-body"
					style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="pwdCheckModalValidate();">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="pwdCheckModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 비밀번호 변경용 모달 -->
	<!-- The Modal -->
	<div class="modal" id="pwdChangeModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">새로운 비밀번호를 입력해주세요</div>
				<div class="pwdHideDiv">
					<input type="password" class="form-control"
						id="pwdChangeModalPassword" placeholder="비밀번호 입력 : "><i
						class="fa fa-eye fa-lg"></i>
				</div>
				<div id="pwdChangeModalWarning" class="modal-body"
					style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="submitPassword();">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="pwdChangeModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 일반적인 정보 변경용 모달 -->
	<!-- The Modal -->
	<div class="modal" id="infoChangeModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">새로운 정보를 입력해주세요</div>
				<div class="pwdHideDiv">
					<input type="text" class="form-control" id="infoChangeModalContent"
						placeholder="정보 입력 : ">
				</div>
				<div id="infoChangeModalWarning" class="modal-body"
					style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="submitInfo(changeInfoCode);">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="infoChangeModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>




	<!-- 주소지 정보 모달 -->
	<div class="modal" id="addressModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">배송지 정보</h4>
					<a style="font-size: 8px">&nbsp;&nbsp;(주소를 클릭하면 삭제됩니다.)</a>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>주소</th>
								<th>상세주소</th>
								<th>수령자</th>
								<th>수령자 전화번호</th>
								<th>우편번호</th>
							</tr>
						</thead>
						<tbody id="deliveryContents">
						</tbody>
					</table>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						data-bs-dismiss="modal" onclick="addAddrModalOpen();">주소추가</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="addressModalClose();">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 삭제 여부 묻는 모달 -->
	<div class="modal" id="deleteAddrModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">경고</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">정말 삭제하시겠습니까? 삭제시 되돌릴 수 없습니다.</div>
				<div id="deleteDeliveryInfoNumber"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal"
						onclick="deleteAddr($('#deleteDeliveryInfoNumber').val());">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="deleteAddrModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>



	<!-- 주소 추가용 모달 -->
	<div class="modal" id="addAddrModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">배송지 추가</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">새로운 배송지를 입력해주세요</div>
				<div class="pwdHideDiv">

					<input type="text" class="form-control" id="address"
						placeholder="주소 : " readonly style="width: 500px"> <br></br>
					<input type="text" class="form-control" id="detailAddress"
						placeholder="상세주소 : " readonly style="width: 500px"> <br></br>
					<input type="text" class="form-control" id="postCode"
						placeholder="우편번호 : " readonly style="width: 500px"> <br></br>
					<input type="text" class="form-control" id="recipient"
						placeholder="수령자 : " style="width: 500px"> <br></br> <input
						type="text" class="form-control" id="recipientPhoneNumber"
						placeholder="수령자 전화번호 : " style="width: 500px">
				</div>
				<div id="addAddrModalWarning" class="modal-body" style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						data-bs-dismiss="modal" onclick="goPopup()">주소검색</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="addAddr()">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="addAddrModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>



	<!-- 이미지 파일 넣는 모달박스 -->
	<div class="modal" id="imgModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">내 이미지 업로드</h4>
					<br></br> <a style="font-size: 8px">&nbsp;&nbsp;(100px * 100px
						사이즈로 리사이즈됩니다.)</a>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="uploadImg">
						<img id="previewImg" onclick="popImage(this.src)"
							style="height: 48px; width: 48px; display: none">
						<div class="filebox">
							<input class="upload-name" value="선택된 파일 없음" disabled="disabled">
							<input type="file" id="file1" class="upload-hidden" name="file1">
							<label for="file1">이미지 첨부</label>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="uploadProfilImg();">올리기</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="imgModalClose();">취소</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 이메일 인증용 모달 -->
	<div class="modal" id="emailChangeModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">이메일 인증</h4>
				</div>


				<!-- Modal body -->
				<div class="modal-body"></div>
				<div class="form-group">
					<label for="memberEmail">이메일 인증</label> <input type="text"
						name="memberEmail" id="memberEmail" class="form-control"
						placeholder="Enter email">
				</div>
				<div class="form-inline mb-3">
					<div class="mail_check_input_box" id="mail_check_input_box_false">
						<input type="text" class="mail_check_input form-control col-8"
							disabled="disabled">
						<button id="mail_check_button"
							class="btn btn-outline-primary btn-sm">인증번호 전송</button>
					</div>
				</div>
				<!— 인증번호 확인 —>
				<div class="alert alert-success" id="alert-success-email">인증번호가
					일치합니다.</div>
				<div class="alert alert-danger" id="alert-danger-email">인증번호가
					일치하지 않습니다.</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="emailChangeModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>



	<!-- 교환 반품 모달 -->
	<div class="modal" id="orderRtnOrExModal">
		<div class="modal-dialog modal-80size">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">교환 및 반품 신청</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<div id="orderRtnOrExModalContents"></div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="notificationModalOpen('쿠폰과 포인트의 경우, 남은 주문건에 대해 자동적용됩니다.');">교환/환불요청</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="orderRtnOrExModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>





	<!-- 회원탈퇴 모달 -->
	<div class="modal" id="membershipWithdrawalModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">한번 더 생각해주세요!</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<a style="font-size: 8px; color: red;">회원탈퇴시 정보를 되돌릴 수 없습니다.</a> <select
						class="form-select mt-3" id="membershipWithdrawalCode">
						<option value="1">더 좋은 사이트 찾음</option>
						<option value="2">불친절한 서비스</option>
						<option value="3">이용빈도 낮음</option>
						<option value="4">개인정보 누출 우려</option>
						<option value="5">사이트 편의성 부족</option>
						<option value="6">상품의 다양성 및 품질 불만</option>
						<option value="7">배송 및 교환 환불 불만</option>
						<option value="8" selected>기타 사유</option>
					</select> <br></br>
					<textarea rows="5" class="form-control"
						id="membershipWithdrawalContents"
						placeholder="사유를 입력해주시면 운영에 도움이 됩니다."
						style="width: 400px; height: 300px;"></textarea>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal"
						onclick="membershipWithdrawalModal2Open();">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal"
						onclick="membershipWithdrawalModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 회원탈퇴 한번 더 모달 -->
	<div class="modal" id="membershipWithdrawalModal2">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원탈퇴</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">정말 회원탈퇴 하시겠습니까??</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="membershipWithdrawal();">탈퇴하기</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal"
						onclick="membershipWithdrawalModal2Close();">취소</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 주문취소 시 알림모달 -->
	<div class="modal" id="orderCancleModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">주문취소</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body">정말 주문을 취소하시겠습니까? 취소 후 재주문 시에는 다시 주문작성을
					해야합니다.</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="orderCancle();">주문취소하기</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="orderCancleModalClose();">돌아가기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 알림용 모달 -->
	<div class="modal" id="notificationModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div id="notificationModalContent" class="modal-body"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="notificationModalClose();">확인</button>
				</div>

			</div>
		</div>
	</div>

	<!-- The Modal -->
	<div class="modal" id="infoChangeModal">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">알림</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">새로운 정보를 입력해주세요</div>
				<div class="pwdHideDiv">
					<input type="text" class="form-control" id="infoChangeModalContent"
						placeholder="정보 입력 : ">
				</div>
				<div id="infoChangeModalWarning" class="modal-body"
					style="color: red;"></div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="submitInfo(changeInfoCode);">확인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal" onclick="infoChangeModalClose();">닫기</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>