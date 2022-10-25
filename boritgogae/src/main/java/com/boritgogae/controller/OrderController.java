package com.boritgogae.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.service.OrderService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {
	
	
	@Inject
	private OrderService service;
	
	/**
	 * @methodName : addCart
	 * @author : 
	 * @date : 2022. 10. 18.
	 * @입력 param :
	 * @returnType : String(int로 바꿀거임)
	 */
	
	@RequestMapping(value = "/cartList")
	public String addCart(CartDTO cart )throws Exception {
		
		service.addCart(cart);
		return "order/cartList";
	}
	
	@GetMapping("/cartList/{memberId}")
	public String cartListGet(@PathVariable("memberId")String memberId, Model model) throws Exception {
		
		model.addAttribute("cartInfo",service.getCartList(memberId));
		
		return "/cartList";
	}
	
	/**
	 * @methodName : guestLogIn
	 * @author : 신태호
	 * @date : 2022. 10. 24.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/detailGuest")
	public String guestLogIn(GuestOrderDTO gdto, Model model, HttpServletResponse response) throws Exception {
		
		OrdersVo guestOrderInfo = service.guestOrderInfo(gdto);
		
		if (guestOrderInfo == null) { // 주문내역이 없다
			return "redirect:/member/logIn?status=guestFail";
		}
		
		model.addAttribute("guestOrderInfo", guestOrderInfo); // 비회원주문정보 바인딩
		
//		response.sendRedirect(destination);
		return "/order/detailGuest";
	}
}
