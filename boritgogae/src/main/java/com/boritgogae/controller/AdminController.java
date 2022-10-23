package com.boritgogae.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
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
		List<ProductVO> topLikeCountList = service.getTopLikeCount();
		List<OrdersVO> getNewOrders = service.getNewOrder();
		List<ProductVO> topReadCountList = service.getTopReadCount();
		
		model.addAttribute("topReadCountList", topReadCountList);
		model.addAttribute("getNewOrders", getNewOrders);
		model.addAttribute("topLikeCountList", topLikeCountList);
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

	@RequestMapping(value = "/member/searchMember", method = RequestMethod.POST)
	public ResponseEntity<List<MemberVo>> searchMember(@RequestParam("inputString") String inputString, Model model) throws Exception {
		System.out.println(inputString + "회원 검색");
		ResponseEntity<List<MemberVo>> result = null;
		
		List<MemberVo> searchMember = service.searchMember(inputString);
		System.out.println(searchMember);
		
		result = new ResponseEntity<>(searchMember, HttpStatus.OK);
		
		return result;
	}

	@RequestMapping(value = "/member/delMembers")
	public String getDelMember(Model model) throws Exception {
		List<DeleteAccountVo> deleteMember = service.getDelMembers();
		System.out.println(deleteMember);
		model.addAttribute("deleteMember", deleteMember);
		return "/admin/delMember";
	}

	@RequestMapping(value = "/coupon")
	public String getCoupon(Model model) throws Exception {
		List<CouponVo> couponList = service.getCoupon();
		List<MemberVo> members = service.getMembers();

		model.addAttribute("members", members);
		model.addAttribute("couponList", couponList);

		return "/admin/coupon";
	}

	@RequestMapping(value = "/coupon/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCoupon(@RequestBody CouponVo coupon) {
		coupon.setCouponDiscount(coupon.getCouponDiscount() * 0.01);

		ResponseEntity<String> result = null;

		try {
			if (service.createCoupon(coupon)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}

		return result;
	}

	@RequestMapping(value = "/coupon/modify", method = RequestMethod.POST)
	public ResponseEntity<String> moodifyCoupon(@RequestParam("couponName") String couponName,
			@RequestParam("couponDiscount") String couponDiscount,
			@RequestParam("couponUseDate") String couponUseDate,
			@RequestParam("modiCouponName") String modiCouponName) throws Exception {
		
		CouponVo coupon = new CouponVo(couponName, Double.parseDouble(couponDiscount), Integer.parseInt(couponUseDate));
		coupon.setCouponDiscount(coupon.getCouponDiscount() * 0.01);
		
		System.out.println(coupon);
		System.out.println(modiCouponName);
		ResponseEntity<String> result = null;

			if (service.modifyCoupon(coupon, modiCouponName)) {
				System.out.println("수정 완료");
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		return result;
	}

	@RequestMapping(value = "/coupon/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteCoupon(@RequestParam("couponName") String couponName) throws Exception {

		String result = null;

		if (service.delCoupon(couponName)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}
	
	@RequestMapping(value = "coupon/sendCoupon", method = RequestMethod.POST)
	public @ResponseBody String deleteCoupon(@RequestBody CouponUsedVo sendCoupon) throws Exception {

		String result = null;
		
		if(sendCoupon.getMemberId().equals("all")) {
			List<MemberVo> members = service.getMembers();
			for(MemberVo member : members) {
				sendCoupon.setMemberId(member.getMemberId());
				if(!member.getMemberId().equals("admin")) {
					if (service.sendCoupon(sendCoupon)) {
						result = "success";
					} else {
						result = "fail";
					}
				}
			}

		} else {
			if (service.sendCoupon(sendCoupon)) {
				result = "success";
			} else {
				result = "fail";
			}
		}

		return result;
	}
	
	
}
