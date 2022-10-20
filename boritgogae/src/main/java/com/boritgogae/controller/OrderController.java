package com.boritgogae.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.domain.CartDTO;
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
}
