package com.boritgogae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= "/admin/*")
public class adminController {

	
	
	@RequestMapping(value = "/main")
	public String adminMainPage() throws Exception {
		System.out.println("관리자 페이지로 이동");
		
		return "/admin/index";
	}
}
