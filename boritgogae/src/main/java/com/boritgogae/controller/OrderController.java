package com.boritgogae.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradeVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderProductDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.OrderVo;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.service.MemberService;
import com.boritgogae.service.OrderService;
import com.boritgogae.service.ProductService;

@RequestMapping(value = "/order/*")
@Controller
public class OrderController {
	
	@Inject
	private MemberService memService;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private ProductService prodService;
	
	
	/**
	 * @methodName : orderSheet(주문페이지)
	 * @author : kjy
	 * @throws Exception 
	 * @date : 2022. 10. 19.
	 * @입력 param : OrderSheetDTO, Model, request
	 * @returnType : String
	 **/
	@RequestMapping(value = "/orderSheet", method = RequestMethod.POST)
	public void orderSheet(OrderSheetDTO orderSheet, Model model, HttpServletRequest request) throws Exception {
		
		MemberVo member = (MemberVo) request.getSession().getAttribute("logInMember");
		
		List<OrderProductDTO> orders = orderSheet.getOrderProducts();

		//가져올 데이터
		if(member != null) {
			List<DeliveryInfoVo> addrs = memService.getMemAddrs(member.getMemberId());
			Map<CouponVo, CouponUsedVo> coupon = orderService.getAvailableCoupon(member.getMemberId());
			GradeVo grade = memService.getGrade(member.getMemberId());
			model.addAttribute("coupons", coupon);
			model.addAttribute("addrs", addrs);
			model.addAttribute("grade", grade);
			model.addAttribute("member", member);
		}

		List<ProductVo> products = prodService.getProducts(orderSheet);
		
		model.addAttribute("receivedProducts", orders);
		model.addAttribute("orders", products);
	}
	

	
	@RequestMapping(value = "/jusoPopup")
	public String jusoPopup() {
		return "/order/jusoPopup";
	}
	
	/**
	 * @methodName : getDeliveryOption 배송비 옵션 가져오기
	 * @author : kjy
	 * @date : 2022. 10. 22.
	 * @입력 param : OrderDTO
	 * @returnType : JSONObject
	 **/
	@RequestMapping(value = "/getDeliveryOption", method = RequestMethod.POST)
	public @ResponseBody DeliveryFeeVo getDeliveryOption(@RequestBody OrderDTO order, HttpServletRequest request){
		MemberVo member = (MemberVo) request.getSession().getAttribute("logInMember");

		if(order.getMemberId() != null) {
			order.setIsMember("Y");
			order.setMemberId(member.getMemberId());
		}else {
			order.setIsMember("N");
		}
		
		return orderService.getDeliveryOption(order);
	}
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public void placeOrder(OrderDTO order, @RequestParam(value="coupon", required = false) String coupon, OrderSheetDTO orderSheet, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		MemberVo member = (MemberVo) request.getSession().getAttribute("logInMember");

		if(order.getMemberId() != null) {
			order.setIsMember("Y");
			order.setMemberId(member.getMemberId());
		}else {
			order.setIsMember("N");
		}

		OrderVo currentOrder = orderService.placeOrder(order, coupon, orderSheet);
		
		response.sendRedirect("redirect:/?orderNo=" + currentOrder.getOrderNo());
	}

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
	@RequestMapping(value = "/detailOrder")
	public String detailOrder(@RequestParam(value="orderNo") int orderNo, Model model, HttpServletRequest request) throws Exception {
		
		MemberVo member = (MemberVo) request.getSession().getAttribute("logInMember");
		
		List<OrderVo> orders = new ArrayList<OrderVo>();
		
		if (member.getMemberId() != null) {
			orders = orderService.getordersByMemberId(member.getMemberId());
			System.out.println(orders.toString());
		}else {
			orders.add(orderService.getorderByOrderNo(orderNo));
			System.out.println(orders.toString());
		}
		
		model.addAttribute("orders", orders);	
		
		return "/order/detailOrder";
	}
}
