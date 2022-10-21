package com.boritgogae.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderProductDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProductVO;
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
	
	//주문할 상품의 상품번호를 임시저장(더했다 뺄 수 있음)
	List<OrderProductDTO> orderProds = new ArrayList<>();
	
	/**
	 * @methodName : orderSheet(주문페이지)
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : OrderSheetDTO, Model, request
	 * @returnType : String
	 **/
	@RequestMapping(value = "/orderSheet", method = RequestMethod.POST)
	public void orderSheet(OrderSheetDTO orderSheet, Model model, HttpServletRequest req) {
		String memberId = (String) req.getSession().getAttribute("memberId");
		// 로그인 세션에 어떻게 저장하는지 보고 수정하기
		memberId = "naver";
		
		List<OrderProductDTO> orders = orderSheet.getOrderProducts();
		for(OrderProductDTO prods : orders) {
			orderProds.add(prods);
		}
		List<DeliveryInfoVo> addrs = memService.getMemAddrs(memberId);
		
		//가져올 데이터
		List<CouponVo> coupon = orderService.getAvailableCoupon(memberId);
		MemberVo member = memService.getMemberInfo(memberId);
		List<ProductVO> products = prodService.getProducts(orderSheet);
		
		model.addAttribute("receivedProducts", orders);
		model.addAttribute("member", member);
		model.addAttribute("orders", products);
		model.addAttribute("coupons", coupon);
		model.addAttribute("addrs", addrs);
		
	}
	
	/**
	 * @methodName : deleteProductFromOrder(컨트롤러의 리스트에서 상품 지움)
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : prodNo
	 * @returnType : ResponseEntity<String>
	 **/
	@RequestMapping(value = "/deleteProdFromOrder", method = RequestMethod.POST)
	public ResponseEntity<String> deleteProductFromOrder(String prodNo){
		ResponseEntity<String> result = new ResponseEntity<>("delFail", HttpStatus.BAD_REQUEST);
		for(OrderProductDTO prods : orderProds) {
			if(prods.getProdNo().equals(prodNo)) {
				result = new ResponseEntity<String>("delSuccess", HttpStatus.OK);
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/jusoPopup")
	public String jusoPopup() {
		return "/order/jusoPopup";
	}
	
	@RequestMapping(value = "/getDeliveryOption", method = RequestMethod.POST)
	public ResponseEntity<String> getDeliveryOption(@RequestBody DeliveryInfoVo addr){
		ResponseEntity<String> result = null;
		
//		orderService.getDeliveryOption(addr);
		
		return null;
	}
}
