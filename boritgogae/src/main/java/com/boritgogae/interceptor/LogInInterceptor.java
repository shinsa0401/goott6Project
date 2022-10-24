package com.boritgogae.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;

/**
 * @author 신태호
 *LogInInterceptor : 실제 로그인 하는 클래스
 */
public class LogInInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Log In Interceptor : 이전 로그인 세션 삭제");
		
		HttpSession ses = request.getSession();
		
		if (ses.getAttribute("logInMember") != null) { // 로그인 한 기록이 있다면
			ses.removeAttribute("logInMember"); // 이전에 로그인 기록을 삭제
			ses.invalidate(); // 세션 만료
		}
		
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String destination = "";
		
		ModelMap map = modelAndView.getModelMap();
		MemberVo logInMember = (MemberVo) map.get("logInMember");
		
		if (logInMember != null) { // 로그인 성공시
			LogInDTO dto = (LogInDTO) map.get("dto");
			if (dto.isRemember()) { // 자동로그인 체크를 한 유저라면
				
				// AutoLogInCookie라는 쿠키의 이름으로 세션 아이디를 저장 
				Cookie AutoLogInCookie = new Cookie("AutoLogInCookie", ses.getId());
				
				AutoLogInCookie.setPath("/"); // "/"하위 경로에 쿠키 전송
				AutoLogInCookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 만료일 7일
				response.addCookie(AutoLogInCookie); // 쿠키 저장
			}
			
			System.out.println(logInMember.toString() + "의 정보를 세션에 남김");
			ses.setAttribute("logInMember", logInMember); // 세션에 바인딩

			// "destination" = 로그인을 요청하기전의 URL의 정보
			if (ses.getAttribute("destination") != null) {
				
				destination = (String) ses.getAttribute("destination");
				
			} else if (ses.getAttribute("destination") == null) {
				
				destination = "/"; // 홈, /, index.jsp
			}
			
			System.out.println("로그인 성공");
			
		} else { // 로그인 실패시
			destination = "/member/logIn?status=fail";
			System.out.println("로그인 실패");
		}
		
		response.sendRedirect(destination); // destination 페이지로 이동
	}

	
}
