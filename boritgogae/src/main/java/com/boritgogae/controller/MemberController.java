package com.boritgogae.controller;

import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;
import com.boritgogae.service.MemberService;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.service.MemberService;

@Controller
@RequestMapping(value="/member/*") // member 요청들 매핑
public class MemberController {

	@Inject
	private MemberService service;
	
	@RequestMapping(value = "/myPage")
	public String myPage(Model model, HttpServletRequest request) throws Exception {
		return "member/myPage";
	}
	
	// 회원혜택 (포인트, 쿠폰)을 불러오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/benefit")
	public JSONObject showUserBenefit(Model model) throws Exception{
		System.out.println("컨트롤러 : 회원혜택불러오기 ");
		List<GradesVo> gradeList =  service.showGradeBenefit();
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
	public JSONObject showPointHistory(Model model, HttpSession ses) throws Exception{
		System.out.println("컨트롤러 : 포인트 불러오기 ");
		
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		
		int pointNow =  service.pointNow(memberId);
		List<PointHistoryVo> pointList =  service.showPointHistory(memberId);
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
	public JSONObject showCouponUsed(Model model, HttpSession ses) throws Exception{
		System.out.println("컨트롤러 : 쿠폰 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		
		List<CouponUsedVo> couponHaveList =  service.showCouponHaveList(memberId);
		List<CouponUsedVo> couponUsedList =  service.showCouponUsedList(memberId);

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
	public JSONObject showUserBoard(Model model, HttpSession ses) throws Exception{
		System.out.println("컨트롤러 : 유저 작성글 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		
		List<UserBoardVo> userBoardList =  service.showUserBoardList(memberId);

		System.out.println(userBoardList.toString());
		
		JSONObject json = new JSONObject();
		json.put("userBoardList", userBoardList);
		return json;
	}
	
	
	// 회원이 쓴 댓글 목록을 가져오는 메서드
	@ResponseBody
	@RequestMapping(value = "/myPage/userReply")
	public JSONObject showUserReply(Model model, HttpSession ses) throws Exception{
		System.out.println("컨트롤러 : 유저 댓글 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";
		
		List<UserReplyVo> userReplyList =  service.showUserReplyList(memberId);

		System.out.println(userReplyList.toString());
		
		JSONObject json = new JSONObject();
		json.put("userReplyList", userReplyList);
		return json;
	}
	
	
	// 회원이 쓸 수 있는 후기와 쓴 후기 내역을 보여주는 메서드.
	@ResponseBody
	@RequestMapping(value = "/myPage/userReview")
	public JSONObject showUserReview(Model model, HttpSession ses) throws Exception{
		System.out.println("컨트롤러 : 유저 후기 관련 정보들 불러오기 ");
		// 추후 로그인 기능이 생겼을 때 변경해야 함.
		// String memberId = (String)ses.getAttribute("loginMember");
		String memberId = "test";

		List<ReviewVO> userReviewList =  service.showUserReviewList(memberId);
		
		System.out.println(userReviewList.toString());		
		// 상품코드를 통해 상품명을 찾고 콘텐츠에 상품명을 넣어놓는다.
		for (ReviewVO review : userReviewList) {
			review.setContent(convertProdNoToProdName(review.getProdNo()));
		}
		
		JSONObject json = new JSONObject();
		json.put("userReviewList", userReviewList);
		return json;
	}
	
	public String convertProdNoToProdName(String prodCode) throws Exception {
		String result = "";
		result = service.convertProdNoToProdName(prodCode);
		return result;
	}
	
	/*
	 * @methodName : logIn
	 * @author : 신태호
	 * @date : 2022. 10. 20.
	 * @입력 param : request, session
	 * @returnType : String
	 * 로그인 정보를 입력하기 위한 로그인 페이지 호출
	 */
	@RequestMapping(value = "/logIn")
	public String logIn(HttpServletRequest request, HttpSession session) {
		String referer = request.getHeader("Referer");
		
		try {
			URL refererUrl = new URL(referer);
			session.setAttribute("destination", refererUrl.getFile());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("로그인 하기");
		
		return "member/logIn";
	}
	
	 
	/**
	 * @methodName : logInPost
	 * @author : 신태호
	 * @throws Exception 
	 * @date : 2022. 10. 20.
	 * @입력 param : dto, model, request, response
	 * @returnType : String
	 * 
	 */
	@RequestMapping(value = "/logInPost", method = RequestMethod.POST)
	public void logInPost(LogInDTO dto, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(dto.toString() + "로 로그인 하자");
		
		MemberVo logInMember = service.logIn(dto, request);
		System.out.println(logInMember.toString());
		
		HttpSession session = request.getSession();
		
		if (logInMember != null) {
			System.out.println("세션아이디 : " + session.getId());
			session.setAttribute("logInMember", logInMember); // 세션에 바인딩
			
		}
		
//		if (dto.isRemember()) { // 자동 로그인을 체크 했을경우
//			int ms = 1000 * 60 * 60 * 24 * 7;
//			long now = System.currentTimeMillis();
//			
//			String memberId = dto.getMemberId();
//			String sessionId = session.getId();
//			Timestamp sessionLimit = new Timestamp(now + ms);
//			
//			service.keepLogIn(memberId, sessionId, sessionLimit);
//		}
		
		
		model.addAttribute("dto", dto);
		model.addAttribute("logInMember", logInMember); // model 객체에 바인딩
		
		
		
		response.sendRedirect("/");
		
	}
	
	/**
	 * @methodName : logOut
	 * @author : 신태호
	 * @date : 2022. 10. 21.
	 * @입력 param : session, response
	 * @returnType : void
	 */
	@RequestMapping(value = "/logOut")
	public void logOut(HttpSession session, HttpServletResponse response) throws IOException {
		if (session.getAttribute("logInMember") != null) {
			session.removeAttribute("logInMember"); // 로그인 정보 삭제
			session.invalidate(); // 세션 만료
			
		}
		System.out.println("로그아웃");
		response.sendRedirect("/");
	}
}
