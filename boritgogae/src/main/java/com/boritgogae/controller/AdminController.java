package com.boritgogae.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boritgogae.domain.MemberVo;
import com.boritgogae.service.AdminService;

@Controller
@RequestMapping(value= "/admin/*")
public class AdminController {

	@Inject
	private AdminService service;
	
	@RequestMapping(value = "/main")
	public String adminMainPage(Model model) throws Exception {
		System.out.println("관리자 페이지로 이동");
		List<MemberVo> members = service.getMembers();
		
		Long time = Calendar.getInstance().getTimeInMillis();
		Timestamp nowDate = new Timestamp(time);
//		int a = 0;
//		System.out.println(nowDate);
//		for(MemberVo vo : members) {
//			Timestamp memberJoinDate = vo.getJoinDate();
//			a = new Date().compareTo(memberJoinDate);
//		}
//		int b = new Date().compareTo(nowDate);
//		
//		System.out.println(a + ", " + b);
		
		model.addAttribute("nowDate", nowDate);
		model.addAttribute("members", members);
		
		return "/admin/main";
	}
	
	@RequestMapping(value = "/member")
	public String memberManagememt(Model model) throws Exception {
		System.out.println("회원 관리 페이지로 이동");
		List<MemberVo> members = service.getMembers();
		
		model.addAttribute("members", members);
		
		return "/admin/member";
		
	}
}
