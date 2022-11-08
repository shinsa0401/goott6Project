package com.boritgogae.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.boritgogae.domain.MemberVo;
import com.boritgogae.service.MemberService;

/**
 * @author 신태호
 * 특정 경로(로그인 권한이 필요할 때)에 접근 할 때, 현재 사용자가 로그인 한 사용자 인지 체크
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private MemberService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession ses = request.getSession();
		
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		String destination = uri + queryString;
		// 로그인 페이지로 이동하기 전에 세션에 저장
		ses.setAttribute("destination", destination);
		
		if (ses.getAttribute("logInMember") == null) { // 로그인 하지 않았다
			// AutoLogInCookie이름의 쿠키를 찾아줌
			Cookie AutoLogInCookie = WebUtils.getCookie(request, "AutoLogInCookie");
			
			if (AutoLogInCookie != null) { // AutoLogInCookie 쿠키가 존재한다면
				MemberVo logInMember = service.checkAutoLogIn(AutoLogInCookie.getValue());
				if (logInMember != null) {
					// 세션에 로그인 정보를 남김
					ses.setAttribute("logInMember", logInMember);
					return true; // 컨트롤러 단으로 돌아감
				}
			}
			response.sendRedirect("/member/logIn"); // 로그인 페이지로 이동
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
