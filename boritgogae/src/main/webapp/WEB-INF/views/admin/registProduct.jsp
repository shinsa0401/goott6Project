<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script type="text/javascript">
	
	let majorClass = "";
	let middleClass = "";
	let subClass = "";
	let brand = "";
	let brandName = "";
	let randNo = Math.floor(Math.random() * 99) + 1;
	if (randNo < 10) {
		randNo = String("0" + randNo);
	}
	let mainIngredient = "";
	let size = "";
	let color = "";
	let weight = "";
	let etc = "E00";
	let prodNo = "";
	let savedProdCont = "";

	let prodImgFiles = null;

	$(function() {
		
		$(".closeModal").click(function() {
			$("#statusModal").hide(100);
			$("#deleteMemberModal").hide(100);
		});

		$('#summernote').summernote(
				{
					height : 300, // 에디터 높이
					minHeight : null, // 최소 높이
					maxHeight : null, // 최대 높이
					focus : false, // 에디터 로딩후 포커스를 맞출지 여부
					lang : "ko-KR", // 한글 설정
					toolbar : [
							// [groupName, [list of button]]
							[ 'fontname', [ 'fontname' ] ],
							[ 'fontsize', [ 'fontsize' ] ],
							[
									'style',
									[ 'bold', 'italic', 'underline',
											'strikethrough', 'clear' ] ],
							[ 'color', [ 'forecolor', 'color' ] ],
							[ 'insert', [ 'picture', 'link' ] ] ],
					fontNames : [ '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체' ],
					fontSizes : [ '8', '9', '10', '11', '12', '14', '16', '18',
							'20', '22', '24', '28', '30', '36', '50', '72' ],
					callbacks : {
						onImageUpload : function(files) {
							uploadFile(files[0], this);
						},
						onPaste : function(e) {
							let clipboardData = e.originalEvent.clipboardData;
							if (clipboardData && clipboardData.items
									&& clipboardData.items.length) {
								let item = clipboardData.items[0];
								if (item.kind === 'file'
										&& item.type.indexOf('image/') !== -1) {
									e.preventDefault();
								}
							}
						}
					}

				});

		$("#summernote").on("summernote.enter", function(we, e) {
			$(this).summernote("pasteHTML", "<br><br>");
			e.preventDefault();
		});

		// 대분류 선택 시 중분류 출력
		$("select[id=majorClassSel]").change(function() {
			let output = "";
			output += '<option value="--">옵션을 선택해주세요</option><option value="F">FOOD</option><option value="L">LIFE</option><option value="T">PLAY</option>';
			if ($(this).val() == "D") {
				output += '<option value="C">TOILET</option><option value="B">BEAUTY</option>';
			} else if ($(this).val() == "C") {
				output += '<option value="C">LITTER</option><option value="B">HEALTHY</option>';
			}
			$("#subClassSel").html('<option value="--"> 중분류 옵션을 선택해 주세요</option>');
			$("#middleClassSel").html(output);
			majorClass = $(this).val();
		});

		// 중분류 선택 시 소분류 출력
		$("select[id=middleClassSel]")
				.change(
						function() {
							let output = "";
							output += '<option value="--">옵션을 선택해주세요</option>';
							if ($(this).val() == "F") {
								output += '<option value="1">사료</option><option value="2">간식</option>';
							} else if ($(this).val() == "L") {
								output += '<option value="1">하우스/방석/철장</option><option value="2">이동장/이동가방</option>';
								output += '<option value="3">식기</option><option value="4">줄/패션용품</option>';
							} else if ($(this).val() == "T"
									&& $("#majorClassSel").val() == "C") {
								output += '<option value="1">놀이용품</option><option value="2">스크래쳐/캣타워</option>';
							} else if ($(this).val() == "T"
									&& $("#majorClassSel").val() == "D") {
								output += '<option value="1">놀이용품</option>';
							} else if ($(this).val() == "C"
									&& $("#majorClassSel").val() == "C") {
								output += '<option value="1">모래</option><option value="2">화장실</option><option value="3">화장실용품</option>';
							} else if ($(this).val() == "C"
									&& $("#majorClassSel").val() == "D") {
								output += '<option value="2">화장실</option><option value="3">화장실용품</option>';
							} else if ($(this).val() == "B") {
								output += '<option value="1">목욕용품</option><option value="2">미용용품</option><option value="3">의약</option>';
							}

							output += '<option value="0">기타</option>';
							$("#subClassSel").html(output);
							middleClass = $(this).val();
						});
		// 소분류 변경시 변수에 value 대입
		$("select[id=subClassSel]").change(function() {
			subClass = $(this).val();
		});

		// 브랜드 변경시 변수에 value 대입
		$("select[id=brand]").change(function() {
			brand = $(this).val();
			brandName = $("#brand option:selected").text();
		});

		// 용량 옵션 변경
		weight = $("select[id=weight]").val();
		$("select[id=weight]").change(function() {
			weight = $("select[id=weight]").val();
		});

		// 주재료 옵션 변경
		mainIngredient = $("select[id=mainIngredient]").val();
		$("select[id=mainIngredient]").change(function() {
			mainIngredient = $("select[id=mainIngredient]").val();
		});

		// 크기 옵션 변경
		size = $("select[id=size]").val();
		$("select[id=size]").change(function() {
			size = $("select[id=size]").val();
		});

		// 색상 옵션 변경
		color = $("select[id=color]").val();
		$("select[id=color]").change(function() {
			color = $("select[id=color]").val();
		});

		$('#prodImg')
				.on(
						'change',
						function() {
							ext = $(this).val().split('.').pop().toLowerCase(); //확장자
							prodImgFiles = $('#prodImg').prop("files")[0];
							console.log(prodImgFiles);
							let prodImgFile = URL.createObjectURL(prodImgFiles);
							$('#prodImgPreviewDiv')
									.html(
											"<img id='prodImgPreview' style='max-width:300px;max-height:170px;'>");
							$('#prodImgPreview').attr('src', prodImgFile);
							$('#prodImgPreviewDiv').slideDown();
						});
		
		function uploadFile(file, editor) {
			prodNo = (majorClass + middleClass + subClass + brand + randNo + weight
					+ mainIngredient + size + color + etc);
			console.log(prodNo);
			data = new FormData();
			if(prodNo != "") {
				data.append("prodContent", file);
				data.append("prodNo", prodNo);
				$.ajax({
					url : "/admin/product/register/prodContent",
					data : data,
					type : "POST",
					contentType : false,
					processData : false,
					async: false,
					success : function(data) {
						console.log(data);
						
						let output = "";
						if (data.image) { // 이미지 파일이면
							savedProdCont = data.savedOriginImageFileName;
							output += "/resources/uploads" + data.savedOriginImageFileName;
						} else {
							$("#status").html("이미지 파일인지 확인해주세요!");
							$("#statusModal").show(200);
						}
						
		            	//항상 업로드된 파일의 url이 있어야 한다.
						$(editor).summernote('insertImage', output);
					}
				});
			} else if (prodNo.length != 22) {
				$("#status").html("상품 정보를 먼저 입력해주세요!");
				$("#statusModal").show(200);
			} else {
				$("#status").html("상품 정보를 먼저 입력해주세요!");
				$("#statusModal").show(200);
			}
			
		}
	});

	function register(form) {
		prodNo = (majorClass + middleClass + subClass + brand + randNo + weight
				+ mainIngredient + size + color + etc);
		let prodName = $("#prodName").val();
		let prodQuantity = $("#prodQuantity").val();
		let prodPrice = $("#prodPrice").val();
		let prodImg = prodImgFiles;
		let prodBrandName = brandName;
		let prodContent = form.prodContent.value;
		prodContent = prodContent.replace(/<(\/?)p>/gi,"");
		form.prodContent.value = prodContent;
		
		let sendImgdata = new FormData();
		sendImgdata.append("prodImg", prodImg);
		sendImgdata.append("prodNo", prodNo);
		sendImgdata.append("savedProdCont", savedProdCont);
		
		console.log(prodNo + ", " + prodName + ", " + prodQuantity + ", " + prodPrice + ", " + prodImg + ", " + prodBrandName + ", " + prodContent);
		
		let sendData = JSON.stringify({
			"prodNo" : prodNo,
			"prodName" : prodName,
			"prodQuantity" : prodQuantity,
			"prodPrice" : prodPrice,
			"brand" : prodBrandName,
		});
		
		let url = "/admin/product/register/insert";

		$.ajax({
			url : url, // 데이터 송수신될 주소 
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : sendData,
			headers : {
				"content-type" : "application/json", // 송신되는 데이터의 타입이 json임을 알림
				"X-HTTP-Method-Override" : "POST" // 구 버전의 웹 브라우저에서 (PUT / DELETE) 방식이 호환이 안되는 버전에서 호환 되도록
			},
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					registerProdImg(sendImgdata);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function registerProdImg(sendImgdata) {
		let url = "/admin/product/register/prodImg";

		$.ajax({
			url : url, // 데이터 송수신될 주소
			type : "post", // 전송 방식
			dataType : "text", // 수신할 데이터
			data : sendImgdata,
			processData: false,
			contentType: false,
			success : function(data) { // 통신이 성공했을 때 호출되는 콜백함수
				console.log(data);
				if (data == "success") {
					$("#status").html("상품 등록에 성공하였습니다.");
					$("#statusModal").show(200);
				} else if (data == "fail") {
					$("#status").html("상품 등록에 성공하였습니다.");
					$("#statusModal").show(200);
				}

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	
</script>
</head>

<body class="hold-transition sidebar-mini layout-fixed">

	<jsp:include page="header.jsp"></jsp:include>

	<script
		src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/summernote/css/summernote-lite.css">

	<!-- Site wrapper -->
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- general form elements -->
			<div class="card card-primary">
				<div class="card-header">
					<h3 class="card-title">상품 등록</h3>
				</div>
				<!-- /.card-header -->
				<form>
					<div class="card-body row">
						<div class="col-6">
							<div class="form-group">
								<label for="prodName">상품명</label> <input type="text"
									class="form-control form-control-border border-width-2"
									id="prodName">
							</div>
							<div class="form-group">
								<label for="brand">브랜드 </label> <select
									class="custom-select form-control-border" id="brand">
									<option value="--">브랜드를 선택해주세요</option>
									<option value="4B">4B</option>
									<option value="AA">아크내추럴스</option>
									<option value="AB">아보덤</option>
									<option value="AC">아카나</option>
									<option value="AD">알파도</option>
									<option value="AE">에이프로</option>
									<option value="AI">AION</option>
									<option value="AJ">아침애수제</option>
									<option value="AK">아키카</option>
									<option value="AL">에이미러브즈펫</option>
									<option value="AM">알모네이쳐</option>
									<option value="AN">ANF</option>
									<option value="AP">아페토</option>
									<option value="AR">앱스랑</option>
									<option value="AS">아메리칸솔루션</option>
									<option value="AT">애니클랏</option>
									<option value="BA">블랙펄</option>
									<option value="BB">버박</option>
									<option value="BC">벅시캣</option>
									<option value="BD">뽀떼</option>
									<option value="BM">보니모노</option>
									<option value="BP">비포락토펫</option>
									<option value="BQ">베토퀴놀</option>
									<option value="BR">보레알</option>
									<option value="BS">블루스톤</option>
									<option value="BU">부들부들</option>
									<option value="BW">블랙우드</option>
									<option value="BY">블루베이</option>
									<option value="CA">캐츠랑</option>
									<option value="CB">캣마루</option>
									<option value="CC">코코스</option>
									<option value="CD">커들스</option>
									<option value="CE">카사바랑캣츠</option>
									<option value="CF">시저</option>
									<option value="CG">콩고</option>
									<option value="CH">카리에스</option>
									<option value="CI">캣잇</option>
									<option value="CJ">캣만두</option>
									<option value="CK">CM코리아</option>
									<option value="CL">카니러브</option>
									<option value="CM">CM</option>
									<option value="CN">캐니대</option>
									<option value="CO">쿠쿳</option>
									<option value="CP">케어펫</option>
									<option value="CQ">카네토라</option>
									<option value="CR">케어앤맘</option>
									<option value="CS">커티스</option>
									<option value="CT">캐티맨</option>
									<option value="CU">클린업</option>
									<option value="CV">클레버메이트</option>
									<option value="CW">캣파우</option>
									<option value="CX">츄츄</option>
									<option value="DA">두부랑캣츠</option>
									<option value="DB">다비스(테라코트)</option>
									<option value="DC">도트캣</option>
									<option value="DD">다다</option>
									<option value="DE">덴탈후레쉬</option>
									<option value="DF">도기프렌드</option>
									<option value="DG">두부콩콩</option>
									<option value="DH">드림펫</option>
									<option value="DJ">닥터조인트</option>
									<option value="DK">다이스키</option>
									<option value="DM">도기맨</option>
									<option value="DP">데코퐁</option>
									<option value="DR">두부랑친구</option>
									<option value="DS">델리스틱</option>
									<option value="DT">닥터티슈</option>
									<option value="DU">더스트몬</option>
									<option value="DW">드링크웰</option>
									<option value="DZ">또자</option>
									<option value="EB">어스본</option>
									<option value="EC">에코펜스</option>
									<option value="EL">에코펠라인</option>
									<option value="EP">에티펫</option>
									<option value="ES">에버샌드</option>
									<option value="EV">에버크린</option>
									<option value="EZ">이지클린</option>
									<option value="FA">플레인캣토리</option>
									<option value="FB">포비</option>
									<option value="FC">퓨리나캣차우</option>
									<option value="FD">토우</option>
									<option value="FD">필리대</option>
									<option value="FL">프레쉬앤크린</option>
									<option value="FN">퓨어네이쳐</option>
									<option value="FP">포포즈</option>
									<option value="FQ">FD트릿</option>
									<option value="FR">프롬</option>
									<option value="FS">퍼시터</option>
									<option value="FT">프락티</option>
									<option value="GA">가또블랑코</option>
									<option value="GB">가필드</option>
									<option value="GB">젠틀바바</option>
									<option value="GD">게더</option>
									<option value="GF">굿프랜드</option>
									<option value="GM">그루머스</option>
									<option value="GN">그리니스</option>
									<option value="GO">GO!</option>
									<option value="GT">그린트리</option>
									<option value="GU">구디</option>
									<option value="HD">헬로도기</option>
									<option value="HG">하겐</option>
									<option value="HI">하이펫</option>
									<option value="HJ">홍사장</option>
									<option value="HL">헤일로</option>
									<option value="HM">헬로망치</option>
									<option value="HO">어니스트</option>
									<option value="HP">호산펫</option>
									<option value="HR">하림</option>
									<option value="HS">호산</option>
									<option value="HW">후새</option>
									<option value="ID">아이펫드림</option>
									<option value="IG">아이앤지메딕스</option>
									<option value="IK">이즈칸</option>
									<option value="IN">이나바</option>
									<option value="IR">아이리스</option>
									<option value="IS">아인솝</option>
									<option value="IU">인네이처</option>
									<option value="IZ">인트라젠</option>
									<option value="JD">젠틀다이제스트</option>
									<option value="JF">조이풀</option>
									<option value="JJ">쥬쥬베</option>
									<option value="JM">정글몬스터</option>
									<option value="JP">짐펫</option>
									<option value="KB">키티바이오</option>
									<option value="KC">켄코케어</option>
									<option value="KD">쿠디</option>
									<option value="KM">클레이몽드</option>
									<option value="KO">견옥고</option>
									<option value="LB">라비벳</option>
									<option value="LC">리첼</option>
									<option value="LR">리터로봇</option>
									<option value="MA">미어펫</option>
									<option value="MB">마이베프</option>
									<option value="MC">M&C</option>
									<option value="MD">모더나</option>
									<option value="ME">메디코펫</option>
									<option value="MF">모닝푸드</option>
									<option value="MG">미아모아</option>
									<option value="MH">모노모</option>
									<option value="MI">미스터리</option>
									<option value="ML">멍랩</option>
									<option value="MM">미유믹스</option>
									<option value="MN">마루칸</option>
									<option value="MP">엠펫</option>
									<option value="MR">미오크리미</option>
									<option value="MS">묘생</option>
									<option value="MU">메디록스</option>
									<option value="MV">마이뷰</option>
									<option value="MY">마야</option>
									<option value="MZ">무아지경</option>
									<option value="NA">니앙볼</option>
									<option value="NB">내추럴발란스</option>
									<option value="NC">냥코케어</option>
									<option value="ND">네오클린</option>
									<option value="NE">뉴캣슬</option>
									<option value="NF">네츄럴코어</option>
									<option value="NG">나인케어</option>
									<option value="NH">네코이찌</option>
									<option value="NI">NIANG</option>
									<option value="NJ">네이쳐스</option>
									<option value="NK">네코</option>
									<option value="NL">뉴트리언스</option>
									<option value="NM">네꼬모리</option>
									<option value="NN">네추라너리쉬</option>
									<option value="NO">나우위</option>
									<option value="NP">네이처펫</option>
									<option value="NQ">너를위한디저트</option>
									<option value="NR">뉴트리나</option>
									<option value="NS">네코세카이</option>
									<option value="NT">뉴트로</option>
									<option value="NU">뉴트리플랜</option>
									<option value="NV">내추럴캣푸드</option>
									<option value="NW">나우</option>
									<option value="NX">내추럴키티</option>
									<option value="NZ">뉴트리플러스젠</option>
									<option value="OC">오더캅</option>
									<option value="OG">옥시그린</option>
									<option value="OJ">오리젠</option>
									<option value="OR">오라틴</option>
									<option value="OS">오스테크</option>
									<option value="PA">펄펙트아치</option>
									<option value="PB">퓨어비타</option>
									<option value="PC">펫케어</option>
									<option value="PD">펫데이즈</option>
									<option value="PE">펫아미고</option>
									<option value="PF">프로덴플라그</option>
									<option value="PG">펫긱</option>
									<option value="PH">푸치스</option>
									<option value="PI">파이오니아</option>
									<option value="PJ">프로이젠</option>
									<option value="PK">펫킷</option>
									<option value="PL">펫라이프</option>
									<option value="PM">펫메이트</option>
									<option value="PN">피콜로카네</option>
									<option value="PO">폴라리스</option>
									<option value="PP">펫파운드</option>
									<option value="PQ">피노파인</option>
									<option value="PR">푸르미</option>
									<option value="PS">펫스테이션</option>
									<option value="PT">펫톡스</option>
									<option value="PU">Pilou</option>
									<option value="PV">프로베스트</option>
									<option value="PW">펫커머</option>
									<option value="PX">펫플러스미</option>
									<option value="PY">페토이</option>
									<option value="PZ">포우즈</option>
									<option value="QA">페슬러</option>
									<option value="QB">퓨라엑스</option>
									<option value="QC">프리미요</option>
									<option value="QD">펫더맨</option>
									<option value="QE">필라인그리니즈</option>
									<option value="QF">프리미엄캣토리</option>
									<option value="QG">프리미엄로얄</option>
									<option value="QH">펫모닝</option>
									<option value="QI">팬시피스트</option>
									<option value="RB">럭셔리발란스</option>
									<option value="RC">로얄캐닌</option>
									<option value="RD">럭셔리도그</option>
									<option value="RH">로한</option>
									<option value="RW">리얼와이오밍</option>
									<option value="RY">로얄캣</option>
									<option value="SB">쏘아베</option>
									<option value="SD">샌드마스터</option>
									<option value="SH">신한</option>
									<option value="SI">쉬바</option>
									<option value="SK">쉐드킬러</option>
									<option value="SM">서플메이트</option>
									<option value="SP">수퍼펫</option>
									<option value="SS">실키식스</option>
									<option value="ST">샤뜨</option>
									<option value="SU">수퍼펫</option>
									<option value="SV">SAVIC</option>
									<option value="SY">순수</option>
									<option value="SZ">사조</option>
									<option value="TA">템테이션</option>
									<option value="TC">더캣츠</option>
									<option value="TD">토모다찌</option>
									<option value="TE">태비탑퍼</option>
									<option value="TF">더프랜드</option>
									<option value="TJ">더자연</option>
									<option value="TM">톰캣</option>
									<option value="TP">터치펫</option>
									<option value="TT">타이니테일</option>
									<option value="UC">유니참</option>
									<option value="VA">비타크래프트</option>
									<option value="VP">벤토피아</option>
									<option value="WA">완피</option>
									<option value="WB">위시본</option>
									<option value="WC">위스카스</option>
									<option value="WN">웰니스</option>
									<option value="WP">위드펫</option>
									<option value="WS">백사장</option>
									<option value="WT">웰츠</option>
									<option value="WZ">와그작</option>
									<option value="XM">엑스마일</option>
									<option value="YB">유비타</option>
									<option value="YD">유덤</option>
									<option value="YM">야미야미</option>
									<option value="YP">유펫</option>
									<option value="YS">양키샌드</option>
								</select>
							</div>
							<div class="form-group">
								<label for="prodQuantity">상품 수량 </label> <input type="number"
									class="form-control form-control-border" id="prodQuantity">
							</div>
							<div class="form-group">
								<label for="prodPrice">상품 가격 </label> <input type="number"
									class="form-control form-control-border" id="prodPrice">
							</div>
							<div class="form-group">
								<label for="prodImg">상품 이미지 </label> <input type="file"
									class="form-control form-control-border" id="prodImg">
							</div>

							<div id="prodImgPreviewDiv"></div>

						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="majorClassSel">대분류 </label> <select
									class="custom-select form-control-border" id="majorClassSel">
									<option value="--">옵션을 선택해주세요</option>
									<option value="D">강아지</option>
									<option value="C">고양이</option>
								</select>
							</div>
							<div class="form-group">
								<label for="middleClassSel">중분류 </label> <select
									class="custom-select form-control-border" id="middleClassSel">
									<option value="--">대분류 옵션을 선택해 주세요</option>
								</select>
							</div>

							<div class="form-group">
								<label for="subClassSel">소분류 </label> <select
									class="custom-select form-control-border" id="subClassSel">
									<option value="--">대분류 옵션을 선택해 주세요</option>
								</select>
							</div>
							<div class="form-group">
								<label for="weight">용량 </label> <select
									class="custom-select form-control-border" id="weight">
									<option value="W00">해당 없음</option>
									<option value="W01">~ 1KG</option>
									<option value="W02">1KG ~ 5KG</option>
									<option value="W03">5KG ~ 10KG</option>
									<option value="W04">10KG 이상</option>
								</select>
							</div>
							<div class="form-group">
								<label for="mainIngredient">주재료 </label> <select
									class="custom-select form-control-border" id="mainIngredient">
									<option value="M00">해당 없음</option>
									<option value="M01">조류</option>
									<option value="M02">어류</option>
									<option value="M03">육류</option>
									<option value="M04">야채류</option>
								</select>
							</div>
							<div class="form-group">
								<label for="size">크기 </label> <select
									class="custom-select form-control-border" id="size">
									<option value="S00">해당 없음</option>
									<option value="S01">S</option>
									<option value="S02">M</option>
									<option value="S03">L</option>
								</select>
							</div>
							<div class="form-group">
								<label for="color">색상 </label> <select
									class="custom-select form-control-border" id="color">
									<option value="C00">해당 없음</option>
									<option value="C01">빨간색</option>
									<option value="C02">주황색</option>
									<option value="C03">노란색</option>
									<option value="C04">초록색</option>
									<option value="C05">파란색</option>
									<option value="C06">남색</option>
									<option value="C07">보라색</option>
									<option value="C08">검정색</option>
									<option value="C09">흰색</option>
									<option value="C10">분홍색</option>
								</select>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group">
								<div class="text-center">
									<label for="prodContent">상품 상세 설명 </label>
								</div>
								<textarea id="summernote" name="prodContent" id="prodContent"></textarea>
							</div>
						</div>
					</div>

					<!-- /.card-body -->
					<button type="button" class="btn btn-primary btn-block"
						onclick="register(this.form);">신규 등록</button>
				</form>
			</div>
			<!-- /.card -->

		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>


		<div class="modal" id="statusModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="status"></h4>
						<button type="button" class="btn-close close closeModal"
							data-bs-dismiss="modal">X</button>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							data-bs-dismiss="modal" onclick="location.reload();">확인</button>
					</div>

				</div>
			</div>
		</div>
</body>

</html>