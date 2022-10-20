package com.boritgogae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boritgogae.domain.LogInDTO;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	/**
	 * @methodName : logIn
	 * @author : 신태호
	 * @date : 2022. 10. 20.
	 * @입력 param :
	 * @returnType : String
	 * 로그인 정보를 입력하기 위한 폼 호출
	 */
	@RequestMapping(value = "/logIn")
	public String logIn() {
		System.out.println("로그인 하기");
		
		return "member/logIn";
	}
	
	 
	/**
	 * @methodName : logInPost
	 * @author : 신태호
	 * @date : 2022. 10. 20.
	 * @입력 param : LoginDTO
	 * @returnType : String
	 * 로그인 정보를 입력한 뒤
	 */
	@RequestMapping(value = "/logInPost", method = RequestMethod.POST)
	public String logInPost(LogInDTO dto) {
		
		System.out.println(dto.toString());
		System.out.println("로그인 성공");
		
		return "/";
	}
}
