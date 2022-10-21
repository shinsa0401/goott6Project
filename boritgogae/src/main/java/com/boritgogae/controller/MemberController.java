package com.boritgogae.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	@Inject
	private MemberService service;
	
	/**
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
