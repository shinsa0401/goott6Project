package com.boritgogae.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.boritgogae.board.ask.domain.UploadAskFile;
import com.boritgogae.board.ask.etc.AskUploadFileProcess;
import com.boritgogae.board.free.domain.FreePageHandler;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;
import com.boritgogae.service.MemberService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrdersVo;

@Controller
@RequestMapping(value="/member/*") // member 요청들 매핑
public class MemberController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Inject
	private MemberService service;

	@RequestMapping(value = "/myPage")
	public String myPage(Model model, HttpServletRequest request) throws Exception {
		return "member/myPage";
	}

	// 회원혜택 (포인트, 쿠폰)을 불러오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/benefit")
	public JSONObject showUserBenefit(Model model) throws Exception {
		System.out.println("컨트롤러 : 회원혜택불러오기 ");
		List<GradesVo> gradeList = service.showGradeBenefit();
		List<CouponVo> couponList = service.showCouponBenefit();
		System.out.println(gradeList.toString());
		System.out.println(couponList.toString());
		JSONObject json = new JSONObject();
		json.put("couponList", couponList);
		json.put("gradeList", gradeList);
		return json;
	}

	// 회원의 포인트 히스토리를 불러오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userPoint")
	public JSONObject showPointHistory(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 포인트 불러오기 ");

		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		int pointNow = service.pointNow(memberId);
		List<PointHistoryVo> pointList = service.showPointHistory(memberId);
		System.out.println("보유포인트" + pointNow);
		System.out.println(pointList.toString());
		JSONObject json = new JSONObject();
		json.put("pointNow", pointNow);
		json.put("pointList", pointList);
		return json;
	}

	// 회원의 보유 쿠폰갯수, 쿠폰 히스토리를 불러오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userCoupon")
	public JSONObject showCouponUsed(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 쿠폰 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		List<CouponUsedVo> couponHaveList = service.showCouponHaveList(memberId);
		List<CouponUsedVo> couponUsedList = service.showCouponUsedList(memberId);

		System.out.println(couponHaveList.toString());
		System.out.println(couponUsedList.toString());

		JSONObject json = new JSONObject();
		json.put("couponHaveList", couponHaveList);
		json.put("couponUsedList", couponUsedList);
		return json;
	}

	// 회원이 쓴 글 목록을 가져오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userBoard")
	public JSONObject showUserBoard(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 유저 작성글 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		List<UserBoardVo> userBoardList = service.showUserBoardList(memberId);

		System.out.println(userBoardList.toString());

		JSONObject json = new JSONObject();
		json.put("userBoardList", userBoardList);
		return json;
	}

	// 회원이 쓴 댓글 목록을 가져오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userReply")
	public JSONObject showUserReply(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 유저 댓글 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		List<UserReplyVo> userReplyList = service.showUserReplyList(memberId);

		System.out.println(userReplyList.toString());

		JSONObject json = new JSONObject();
		json.put("userReplyList", userReplyList);
		return json;
	}

	// 회원이 쓸 수 있는 후기와 쓴 후기 내역을 보여주는 메서드.
	@ResponseBody
	@RequestMapping(value = "/myPage/userReview")
	public JSONObject showUserReview(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 유저 후기 관련 정보들 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		List<ReviewVO> userReviewList = service.showUserReviewList(memberId);

		System.out.println(userReviewList.toString());
		// 상품코드를 통해 상품명을 찾고 콘텐츠에 상품명을 넣어놓는다.
		for (ReviewVO review : userReviewList) {
			review.setContent(convertProdNoToProdName(review.getProdNo()));
		}

		JSONObject json = new JSONObject();
		json.put("userReviewList", userReviewList);
		return json;
	}

	// 상품코드에 맞는 상품명을 반환하는 메서드
	public String convertProdNoToProdName(String prodCode) throws Exception {
		String result = service.convertProdNoToProdName(prodCode);
		return result;
	}

	// 회원정보를 가져오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userInfo")
	public JSONObject showUserInfo(Model model, HttpSession ses, HttpServletRequest request) throws Exception {
		System.out.println("컨트롤러 : 유저 후기 관련 정보들 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		

		MemberVo memberInfo = service.showUserInfo(memberId);
		JSONObject json = new JSONObject();
		json.put("memberInfo", memberInfo);
		Cookie secretInfoCookie = WebUtils.getCookie(request, "secretInfo");
		if(secretInfoCookie != null) {
			json.put("secretInfo", "Y");
		} else {
			json.put("secretInfo", "N");
		}
		return json;
	}

	// 회원의 비밀번호가 맞는지 체크하고, 맞다면 쿠키를 생성해줌.
	@RequestMapping(value = "/myPage/secretInfoCheck")
	public ResponseEntity<String> secretInfoCheck(@RequestParam("pwd") String pwd, Model model, HttpSession ses,
			HttpServletResponse response) {
		System.out.println("컨트롤러 : 비번 맞는지 체크 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;

		try {
			correctCheck = service.checkPwd(memberId, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (correctCheck == 1) {
			try { // 비번 맞았을 시
				System.out.println("맞음");
				Cookie secretInfoCookie = new Cookie("secretInfo", "secretInfoCheck");
				secretInfoCookie.setPath("/");
				secretInfoCookie.setMaxAge(60 * 10); // 쿠키 만료시간 : 10분
				response.addCookie(secretInfoCookie);
				result = new ResponseEntity<String>("correctCheckSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else { // 비번 틀렸을 시
			System.out.println("틀림");
			result = new ResponseEntity<String>("correctCheckFail", HttpStatus.OK);
		}
		
		return result;
	}
	
	
	// 회원의 비밀번호가 맞는지 체크.
	@RequestMapping(value = "/myPage/pwdCheck")
	public ResponseEntity<String> pwdCheck(@RequestParam("pwd") String pwd, Model model, HttpSession ses,
			HttpServletResponse response) {
		System.out.println("컨트롤러 : 비번 맞는지 체크 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;

		try {
			correctCheck = service.checkPwd(memberId, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (correctCheck == 1) {
			try { // 비번 맞았을 시
				System.out.println("맞음");
				result = new ResponseEntity<String>("pwdCheckSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else { // 비번 틀렸을 시
			System.out.println("틀림");
			result = new ResponseEntity<String>("pwdCheckFail", HttpStatus.OK);
		}
		return result;
	}
	
	// 회원이 정보를 변경할 때의 메서드
	@RequestMapping(value = "/myPage/changeInfo")
	public ResponseEntity<String> changeInfo(@RequestParam("target") String target, @RequestParam("value") String value, Model model, HttpSession ses,
			HttpServletResponse response) {
		
		System.out.println("컨트롤러 : 정보변경!");
				
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;
		
		try {
			correctCheck = service.changeInfo(memberId, target, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(correctCheck == 1) {
			try { // 변경이 잘 되었을 시
				System.out.println("변경완료");
				result = new ResponseEntity<String>("infoChangeSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}else{ // 비번 틀렸을 시
			System.out.println("변경실패");
			result = new ResponseEntity<String>("infoChangeFail", HttpStatus.OK);
		}
		return result;
	}	
	
	// 주소지 팝업
	@RequestMapping(value = "/jusoPopup")
	public String juso() {
		return "/member/jusoPopup";
	}
	

	// 회원의 주소지 정보들을 불러오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/showDeliveryInfo")
	public JSONObject showDeliveryInfo(Model model, HttpSession ses) throws Exception {
		System.out.println("컨트롤러 : 주소지 불러오기 ");

		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		List<DeliveryInfoVo> deliveryList = service.showDeliveryInfo(memberId);
		System.out.println(deliveryList.toString());
		JSONObject json = new JSONObject();
		json.put("deliveryList", deliveryList);
		return json;
	}


	// 회원이 주소지를 삭제할 때의 메서드
	@RequestMapping(value = "/myPage/deleteAddr")
	public ResponseEntity<String> deleteAddr(@RequestParam("deliveryInfo") String deliveryInfo, Model model, HttpSession ses,
			HttpServletResponse response) {		
		System.out.println("컨트롤러 : 주소 삭제");
				
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;
		
		try {
			correctCheck = service.deleteAddr(memberId, deliveryInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(correctCheck == 1) {
			try { // 삭제가 잘 되었을 시
				System.out.println("변경완료");
				result = new ResponseEntity<String>("deleteAddrSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}else{ // 삭제가 안되었을 시
			System.out.println("변경실패");
			result = new ResponseEntity<String>("deleteAddrFail", HttpStatus.OK);
		}
		return result;
	}	
	
	// 회원이 주소지를 추가할 때의 메서드
	@RequestMapping(value = "/myPage/addAddr")
	public ResponseEntity<String> addAddr(@RequestParam("address") String address, @RequestParam("detailAddress") String detailAddress, @RequestParam("postCode") String postCode,
			 @RequestParam("recipient") String recipient, @RequestParam("recipientPhoneNumber") String recipientPhoneNumber,Model model, HttpSession ses,
			HttpServletResponse response) {
		
		System.out.println("컨트롤러 : 주소 추가!");
				
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;
		
		try {
			correctCheck = service.addAddr(memberId, address, detailAddress, postCode, recipient, recipientPhoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(correctCheck == 1) {
			try { // 입력이 잘 되었을 시
				System.out.println("입력완료");
				result = new ResponseEntity<String>("addAddrSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}else{ // 비번 틀렸을 시
			System.out.println("입력실패");
			result = new ResponseEntity<String>("addAddrFail", HttpStatus.OK);
		}
		return result;
	}	
	
	// 파일 업로드 메서드
	@RequestMapping(value = "/myPage/uploadProfilImg", method = RequestMethod.POST)
	public @ResponseBody String uploadProfilImg(MultipartFile upfile, HttpServletRequest request) {
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		
		System.out.println("컨트롤러 : 파일 업로드");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());
		
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/members/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);

		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "_" + upfile.getOriginalFilename(); // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로 계산하여 얻어옴 (최종 저장될 경로 = upPath + savePath)
		File originTarget = new File(upPath + savePath, saveFileName);

		
		try {
			System.out.println("업로드 성공");
			FileCopyUtils.copy(upfile.getBytes(), originTarget);
			System.out.println("저장값 =  " + savePath + saveFileName);
			// 기존 이미지값 가져오고, 그 파일을 삭제
			new File(upPath + service.showUserInfo(memberId).getMemberImg()).delete();
			// 새로운 이미지값 업데이트
			service.addMemberImg(memberId, savePath + File.separator + saveFileName);
			return "success";
		} catch (Exception e) {
			System.out.println("업로드 실패");
			return "fail";
		}
	}
	
	
	// 파일 저장 경로 규약
	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR) + ""; //  \2022
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13
		
		System.out.println("calcSavePath : " + upPath + datePath);
		
		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듬
		
		return datePath;
	}

	// 디렉토리 생성
	private static void makeDir(String upPath, String... paths) {
		// String... : 가변인자(배열로 처리됨), String 타입의 매개변수로 가변인자 배열로 받을것임을 컴파일러에게 알려줌.
		// yearPath, monthPath, datePath 값을 paths라는 이름의 배열로 넘겨주게 된다. 
		
		if(new File(upPath + paths[paths.length - 1]).exists()) {
			// 해당 경로가 존재한다... 폴더를 생성할 필요가 없다.
			return;
		}
		
		// 해당 경로의 폴더가 없으므로 폴더를 생성함
		for(String path : paths) {
			File dirPath = new File(upPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir(); // 실제 디렉토리(폴더) 생성
			}
		}
	}
	
	// 회원 이메일 변경 메서드
	@RequestMapping(value = "/myPage/changeMemberEmail")
	public ResponseEntity<String> changeMemberEmail(@RequestParam("emailAddress") String memberEmail, Model model, HttpSession ses,
			HttpServletResponse response) {
		System.out.println("컨트롤러 : 이메일주소 업데이트 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		ResponseEntity<String> result = null;
		String memberId = "test";
		int correctCheck = 0;

		try {
			correctCheck = service.changeMemberEmail(memberId, memberEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (correctCheck == 1) {
			try { // 성공
				System.out.println("맞음");
				result = new ResponseEntity<String>("emailUpdateSuccess", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else { // 실패
			System.out.println("틀림");
			result = new ResponseEntity<String>("emailUpdateFail", HttpStatus.OK);
		}
		return result;
	}
	
	/**
	 * @methodName : mailCheck
	 * @author : 강무진 - 수정 신태호
	 * @date : 2022. 10. 31.
	 * @입력 param : 
	 * @returnType : String
	 * 6자리의 임의의 인증번호 이메일 전송 - 호출한 페이지에 따라 제목과 내용을 다르게 설정 
	 */
	@RequestMapping (value="/mailCheck")
	@ResponseBody
	public String mailCheck(String email, HttpServletRequest request) throws Exception{
		System.out.println("이메일 데이터 전송확인");
		System.out.println("인증 메일 : " + email);
		
		Random random = new Random();
		int checkNum = random.nextInt(888888)+111111; 
		System.out.println("인증번호 : " + checkNum);
		
		//이메일 보내기
		String setFrom = "goott6@naver.com"; // 네이버 아이디
		String toEmail = email;
		String title = "";
		String content = "";
		// 요청한 이전페이지를 판단해서 제목과 내용 달라짐
		String referer = request.getHeader("referer");
		System.out.println(referer);
		
		if (referer.contains("/join")) { // 회원가입 일 때
			System.out.println("join 포함");
			
			title = "test";
			content = "가입해주셔서 감사합니다."+ "<br/><br/>"+"인증 번호는 "+checkNum+" 입니다.<br/>"+
								"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
			
			
		} else if (referer.contains("/find")) { // 아이디찾기, 비밀번호 재설정 일 때
			System.out.println("find 포함");
			
			title = "보릿고개 이메일 인증";
			content = "이용해주셔서 감사합니다." + "<br/><br/>" + "인증 번호는 " + checkNum + " 입니다.<br/>" + "해당 인증번호를 인증번호 입력란에 기입하여 주세요.";
		}
		
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toEmail);
            helper.setSubject(title);
            helper.setText(content,true);
            
            mailSender.send(message);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        String num = Integer.toString(checkNum);
        return num;
	}

	/**
	 * @methodName : logIn
	 * @author : 신태호
	 * @date : 2022. 10. 17.
	 * @입력 param : HttpServletRequest request, HttpSession ses
	 * @returnType : String
	 * 로그인 정보를 입력하기 위한 로그인 페이지 호출
	 */
	@RequestMapping(value = "/logIn")
	public String logIn(HttpServletRequest request, HttpSession ses) {
		System.out.println("로그인 페이지");
		
		String memberId = (String) ses.getAttribute("memberId");
		System.out.println(memberId);
		
		// 아이디찾기에서 세션에 저장된 memberId 삭제
		if (ses.getAttribute("memberId") != null) {
			ses.removeAttribute("memberId");
			ses.invalidate();
		}
		
		// 현재 페이지로 오기전 URL 정보를 referer에 저장
		String referer = request.getHeader("Referer");
		
		// 이전페이지로 돌아가기 위한 Referer 헤더값을 세션의 destination에 저장
		// (로그인 여러회 실패시 이전페이지로 제대로 돌아가기 위함)
	    if (referer != null && !referer.contains("logIn")) { // 로그인페이지 제외
	    	if (!referer.contains("find")) { // + 찾기페이지 제외
	    		ses.setAttribute("destination", referer);
	    	}
	    }
		
		return "member/logIn";
	}
	 
	/**
	 * @methodName : logInPost
	 * @author : 신태호
	 * @throws Exception 
	 * @date : 2022. 10. 20.
	 * @입력 param : dto, model, request, response
	 * @returnType : void
	 * 로그인 정보를 입력하고 난 후 인터셉터핸들러에 의해 로그인 처리
	 */
	@RequestMapping(value = "/logInPost", method = RequestMethod.POST)
	public void logInPost(LogInDTO dto, Model model, HttpSession ses) throws Exception {
		// 인터셉터 preHandle 수행하고 옴
		
		MemberVo logInMember = service.logIn(dto);
		
		if (logInMember == null) { // 로그인 실패 유저
			return;
		}
		
		if (dto.isRemember()) { // 자동 로그인을 체크 했을경우 DB에 세션 정보 저장
			int ms = 1000 * 60 * 60 * 24 * 7;
			long now = System.currentTimeMillis();
			
			String memberId = dto.getMemberId();
			String sessionId = ses.getId();
			Timestamp sessionLimit = new Timestamp(now + ms);
			System.out.println("자동로그인 체크 온");
			service.keepLogIn(memberId, sessionId, sessionLimit);
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("logInMember", logInMember); // model 객체에 바인딩
		
		// 인터셉터 postHandle에 의해 나머지 과정 수행
	}
	
	/**
	 * @methodName : logOut
	 * @author : 신태호
	 * @throws Exception 
	 * @date : 2022. 10. 21.
	 * @입력 param : session, response
	 * @returnType : void
	 */
	@RequestMapping(value = "/logOut")
	public void logOut(HttpSession ses, HttpServletResponse response) throws Exception {
		MemberVo logInMember = (MemberVo) ses.getAttribute("logInMember");
		
		if (ses.getAttribute("logInMember") != null) { // 회원 정보가 있다면
			// DB 로그아웃시간 업데이트
			service.updateLogOutDate(logInMember.getMemberId());
			
			ses.removeAttribute("logInMember"); // 로그인 정보 삭제
			ses.invalidate(); // 세션 만료
			
		}
		
		System.out.println("로그아웃");
		
		response.sendRedirect("/");
	}
	
	/**
	 * @methodName : findId
	 * @author : 신태호
	 * @date : 2022. 10. 26.
	 * @입력 param : 
	 * @returnType : String
	 * 아이디 찾기 페이지 호출
	 */
	@RequestMapping(value = "/findId")
	public String findId() throws Exception {
		System.out.println("컨트롤러 : 아이디 찾기 페이지로 이동");
		return "member/findId";
	}
	
	/**
	 * @methodName : emailAuthCheck
	 * @author : 신태호
	 * @date : 2022. 10. 26.
	 * @입력 param :
	 * @returnType : String
	 * 이메일 인증이 확인되고 나서 이후 단계
	 */
	@RequestMapping(value = "/emailAuthCheck", method = RequestMethod.POST)
	public ResponseEntity<MemberVo> emailAuthCheck(HttpSession ses, @RequestBody MemberVo findMember) throws Exception {
		ResponseEntity<MemberVo> result = null;
		
		System.out.println("컨트롤러 이메일 : " + findMember.getMemberEmail());
		String memberEmail = findMember.getMemberEmail();
		
		// 입력받은 이메일로 아이디 검색
		MemberVo member = service.selectMemberId(memberEmail);
		System.out.println(member.toString());
		
		// 검색결과가 있다면
		if (member != null) {
			// 찾은 아이디 세션에 저장(비밀번호재설정 인증없이 바로 넘기기위함)
			ses.setAttribute("memberId", member.getMemberId());
			System.out.println("아이디 찾기 성공");
			result = new ResponseEntity<>(member, HttpStatus.OK);
		} 
		
		return result;
		
	}
	
	/**
	 * @methodName : findPwd
	 * @author : 신태호
	 * @date : 2022. 10. 26.
	 * @입력 param :
	 * @returnType : String
	 * 비밀번호 재설정 페이지 호출
	 */
	@RequestMapping(value = "/findPwd")
	public String findPwd(HttpSession ses, Model model) throws Exception {
		System.out.println("컨트롤러 : 비밀번호 재설정 페이지로 이동");
		
		String memberId = (String) ses.getAttribute("memberId");
		System.out.println("세션에저장된 아이디 : " + memberId);
		
		if (ses.getAttribute("memberId") != null) {
			System.out.println("세션에 저장된 아이디가 있다");
			model.addAttribute("memberId", memberId); // 뷰단으로 바인딩
		}
		
		return "member/findPwd";
		
	}
	
	/**
	 * @methodName : findPwd
	 * @author : 신태호
	 * @date : 2022. 10. 27.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/idCheck")
	public ResponseEntity<String> idCheck(@RequestParam("memberId")String memberId) throws Exception {
		ResponseEntity<String> result = null;
		
		if (service.checkMemberId(memberId) == 1) { // 회원 아이디가 맞다면
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else { // 검색되는 아이디가 없다면
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return result;
	}
	
	/**
	 * @methodName : idCheck
	 * @author : 신태호
	 * @date : 2022. 10. 27.
	 * @입력 param :
	 * @returnType : ResponseEntity<String>
	 */
	@RequestMapping(value = "/pwdUpdate")
	public ResponseEntity<String> pwdUpdate(@RequestParam("memberId")String memberId, @RequestParam("memberPwd")String memberPwd) throws Exception {
		ResponseEntity<String> result = null;
		
		if (service.updatePwd(memberId, memberPwd) == 1) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return result;
	}
	
	

	@RequestMapping(value = "/joinOk", method = RequestMethod.POST)
	public String joinOk(DeliveryInfoVo dv, MemberVo vo, HttpServletResponse response)  {
		
	
		
		try {
			service.memberjoin(vo, response,dv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	 System.out.println("확인!!!!!!!!!!!!!!!!!!!!!!!!"+vo);
	 System.out.println("확인!!!!!!!!!!!!!!!!!!!!!!!!"+dv);
		return"/member/join";
	}
	 @RequestMapping(value = "/ex")
		public String test() throws Exception {
			
			
			
			 return "/member/ex";
			
		}
	 
	 
	 @RequestMapping(value = "/send")

		public void sendtest(DM dm, @RequestParam("noteContent")String noteContent,@RequestParam("receiverId")String receiverId) throws Exception {
			
				service.insertWriter(dm);
			
			
		}
	
	
	 @RequestMapping(value = "/join")
	public String join( ) {
		 
		 return "/member/join";
		
	}
	
	
	
	@RequestMapping(value = "/like")
	public ModelAndView like(Model m, ProductVo pv ) throws Exception {
		
		 ModelAndView mav = new ModelAndView();
		 
		 mav.setViewName("member/like");
		 
		
		String memberId = "haha";
		List<ProductVo> list = service.selectLike(memberId);
	
        m.addAttribute("list", list);
        
        

   
       
        
		
		System.out.println("확인!!!!!!!");
		
		return mav;
	}
	
	
	
	 @RequestMapping(value = "/DM")
	    public ModelAndView list(Model m, FreeSearchCondition sc, HttpServletRequest request) throws Exception {
		 ModelAndView mav = new ModelAndView();
			
				mav.setViewName("member/DM");

	            int totalCnt = service.getSearchResultCnt(sc);
	            m.addAttribute("totalCnt", totalCnt);

	            FreePageHandler pageHandler = new FreePageHandler(totalCnt, sc);

	            List<DM> list = service.getSearchResultPage(sc);
	            m.addAttribute("list", list);
	            m.addAttribute("ph", pageHandler);
	            System.out.println("list"+list);
	           

	        return mav; // 로그인을 한 상태이면, 게시판 화면으로 이동
	           

			
	    }
	
	 //@RequestParam("tdArr")List[] tdArr
	 @RequestMapping(value = "/delDM")
		public ModelAndView delBoard(@RequestParam("tdArr[]")List<String> tdArr)throws Exception{
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("member/DM");
		System.out.println(tdArr);
		
	
		
		 for(String no : tdArr) {
			 service.sendDel(no);
		 }
		 
		
			return mav;
		}


	@RequestMapping(value = "/dmdetail")
		public ModelAndView boardDetail( Model model,@RequestParam("no") String no)throws Exception{
		 int bno = Integer.parseInt(no);
		 
		 ModelAndView mav = new ModelAndView();
			mav.setViewName("member/dmdetail");
		 
		 Map<String,Object> map = service.detaildm(bno);
		 
		 DM dm = (DM)map.get("dm");
		 
		 model.addAttribute("dm", dm);
		 
		 return mav;
		 
		 
	}

}
