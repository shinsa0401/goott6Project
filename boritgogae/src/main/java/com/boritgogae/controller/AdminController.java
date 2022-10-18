package com.boritgogae.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.service.AdminService;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController {

    @Inject
    private AdminService service;

    @RequestMapping(value = "/main")
    public String adminMainPage(Model model) throws Exception {
        System.out.println("관리자 페이지로 이동");
        List<MemberVo> members = service.getMembers();

        Long time = Calendar.getInstance().getTimeInMillis();
        Timestamp nowDate = new Timestamp(time);
        List<MemberVo> newMembers = service.getNewMembers();
        List<ProductVO> lowestProduct = service.getLowestProduct();

        model.addAttribute("lowestProduct", lowestProduct);
        model.addAttribute("newMembers", newMembers);
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

    @RequestMapping(value = "/member/new")
    public String newMemberManagememt(Model model) throws Exception {
        System.out.println("회원 관리 페이지로 이동");
        List<MemberVo> members = service.getNewMembers();

        model.addAttribute("members", members);

        return "/admin/newMember";
    }
    
    @RequestMapping(value = "/member/searchMember")
    public String searchMember(HttpServletRequest request, String inputString, Model model) throws Exception {
        System.out.println("회원 검색");
        String getUrl = request.getRemoteAddr();
        
        return getUrl;
    }
}
