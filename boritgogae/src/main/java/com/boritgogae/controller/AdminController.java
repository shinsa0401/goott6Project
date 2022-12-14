package com.boritgogae.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.AdminOrdersPagingInfo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailDTO;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.service.AdminService;
import com.boritgogae.service.OrderService;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController {

	@Inject
	private AdminService service;
	
	@Inject
	private OrderService orderService;

	@RequestMapping(value = "/main")
	public String adminMainPage(Model model) throws Exception {
		System.out.println("관리자 페이지로 이동");
		List<MemberVo> members = service.getMembers();
		List<MemberVo> newMembers = service.getNewMembers();
		List<ProductVo> lowestProduct = service.getLowestProduct();
		List<ProductVo> topLikeCountList = service.getTopLikeCount();
		List<OrdersVo> getNewOrders = service.getNewOrder();
		List<ProductVo> topReadCountList = service.getTopReadCount();
		int getLogInMemberCount = service.getLogInMemberCount();
		
		model.addAttribute("logInMemberCount", getLogInMemberCount);
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
		List<DeleteReasonVo> deleteReasons = service.getDeleteReason();
		
		model.addAttribute("deleteReasons", deleteReasons);
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
	
	@RequestMapping(value = "/member/detail")
	public String viewMemberProfile(@RequestParam("memberId") String memberId, Model model) throws Exception {
		
		MemberVo member = service.getMemberProfile(memberId);
		List<CouponUsedVo> memberCoupon = service.getCouponFromMember(memberId);
		List<DeliveryInfoVo> memberAddressList = service.getMemberAddress(memberId);
		
		model.addAttribute("memberAddressList", memberAddressList);
		model.addAttribute("memberCoupon", memberCoupon);
		model.addAttribute("member", member);
		
		return "/admin/memberProfile";
	}
	
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public @ResponseBody String modifyMember(@RequestBody MemberVo member) throws Exception {
		String result = ""; 
		
		if(service.modifyMemberForAdmin(member)) {
			result = "success";
		}else {
			result = "fail";
		}
		
		
		return result;
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteMember(@RequestParam("memberId") String memberId) throws Exception {
		String result = ""; 
		
		if(service.deleteMember(memberId)) {
			result = "success";
		}else {
			result = "fail";
		}
		
		
		return result;
	}
	
	
	// 관리자 주문조회
	@RequestMapping(value = "/orders")
	public String adminOrderInfo(Model model ,@RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {
		
		if (pageNo < 1) {
			pageNo = 1;
		}
		
		Map<String,Object> map = new HashMap<>();
		
		map = orderService.getOrders(pageNo); 
		
		List<OrdersVo> order = (List<OrdersVo>) map.get("order");
		AdminOrdersPagingInfo pi = (AdminOrdersPagingInfo) map.get("pi");
		
		if (pageNo > pi.getTotalPage()) {
			pageNo = pi.getTotalPage();
		}
		
		List<OrderDetailDTO> todo = orderService.getAdminTodoList();
		int orderCnt = orderService.countOrder();
		int adminAllowOrders = orderService.adminAllowOrders();
		System.out.println(pageNo+"@@@@@@@@");
		model.addAttribute("orderList", order);
		model.addAttribute("todo", todo);
		model.addAttribute("count", orderCnt);
		model.addAttribute("adminAllowOrders", adminAllowOrders);
		model.addAttribute("pi", pi);
		model.addAttribute("pageNo", pageNo);
		
		return "/admin/orderInfo"; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/orders/detailOrderInfo")
	public JSONObject adminDetailOrderInfo(Model model,@RequestParam(value="orderNo") int orderNo) throws Exception {
		List<OrderDetailDTO> order = orderService.getDetailOrdersInfo(orderNo);
		JSONObject json = new JSONObject();
		json.put("order",order);
		System.out.println(json);
		
		return json; 
	}
	
}
