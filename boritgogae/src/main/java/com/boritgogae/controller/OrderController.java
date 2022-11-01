package com.boritgogae.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
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
import com.boritgogae.domain.MemberVo;
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
		System.out.println(order.getMemberId());
		if(order.getMemberId() != null) {
			order.setIsMember("Y");
			order.setMemberId(member.getMemberId());
		}else {
			order.setIsMember("N");
		}
		
		return orderService.getDeliveryOption(order);
	}
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public String placeOrder(OrderDTO order, @RequestParam(value="coupon", required = false) String coupon, OrderSheetDTO orderSheet, Model model) {
		System.out.println("order"+order.toString());
		System.out.println("orderSheet"+orderSheet.toString());
		
		OrderVo currentOrder = orderService.placeOrder(order, coupon, orderSheet);

		
		model.addAttribute("order", currentOrder);
		
		return "order/orderComplete";
	}

	@Inject
	private OrderService service;
	
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

	/**
	 * @methodName : addCart
	 * @author :하수영
	 * @date : 2022. 10. 18.
	 * @입력 param :
	 * @returnType : String (아직)
	 * prodDetail.jsp에서 호출
	 */
	@ResponseBody
	@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
	public String addCart(CartDTO cart ,HttpSession ses,  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MemberVo member = (MemberVo)request.getSession().getAttribute("logInMember");
		System.out.println("찍히니??????????");
		String memberId = member.getMemberId();
		String session = ses.getId();	
		
		System.out.println("????????"+session);
		System.out.println("????????"+memberId);
		
		if(ses.getAttribute("logInMember") != null) { //회원
			cart.setMemberId(memberId);
			service.addCartMem(cart);
			
			return "success";
			
		}else if(memberId == null &&  session == null ) { //비회원이고 쿠키 없음
			// 쿠키 생성
			Cookie cookie = new Cookie("Guest", session);
			// 쿠키 유지 시간 설정(1일)
			cookie.setMaxAge(60*60*24);  
			// 쿠키를 클라이언트로 전송
			response.addCookie(cookie);
			cart.setSessionId(session);
			service.addCartGuest(cart);
			
			return "success";
			
		}else if(memberId ==null && session !=null){	 //비회원이지만 쿠키 있음
			
			// 쿠키 얻어오기
			Cookie[] cookies = request.getCookies();
			String cookieName = "";
			String cookieValue = "";
			if(cookies!=null ){
			    for(int i=0;i<cookies.length;i++){
			        if(cookies[i].getName().equals("Guest")){
			            cookieName = cookies[i].getName();
			            cookieValue = cookies[i].getValue();
			        }
			    }
			}
			cart.setSessionId(session);
			service.addCartGuest(cart);
			
			return "success";
			
		}
		System.out.println("컨틀롤러 멤ㅁ버요청 : "+memberId);
		return "redirect:/order/cart";
	}

	/**
	 * @methodName : getCartList
	 * @author :하수영
	 * @date : 2022. 10. 20.
	 * @입력 param :
	 * @returnType : void 장바구니 목록 보기 (앞으로 회원, 비회원 장바구니로 더 수정해줘야 한다) 장바구니 가격 총합
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCartList(Model model, HttpSession ses, HttpServletRequest request) throws Exception {
		
		MemberVo member = (MemberVo)request.getSession().getAttribute("logInMember");
	
		List<CartDTO> cartList = null;
		Cookie[] cookies = request.getCookies();
		String session = ses.getId();
		
		if(ses.getAttribute("logInMember") != null) {
			cartList = service.selectCartListMem(member.getMemberId());
			System.out.println("여기는 멤버");
			
		} else {
			System.out.println("1");
			for(Cookie cookie : cookies){
			    System.out.println("4");
			    if(cookie.getName().equals("Guest")) {
			    	session = cookie.getValue();
			    	System.out.println("2");
			    	cartList = service.selectCartListGuest(session);
					
				}else {
			    System.out.println("3");
			    return "redirect:/";
			}
		  }
		}
		// 장바구니 가격 총합 구하기
		int totalPrice = 0;
		for (CartDTO cart : cartList) {
			totalPrice += cart.getProdPrice() * cart.getQty();
					
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalPrice);

		}

		return "/order/cart";
		
	}

	/**
	 * @methodName : delCart
	 * @author :하수영
	 * @throws Exception
	 * @date : 2022. 10. 20.
	 * @입력 param :
	 * @returnType : int 장바구니 삭제 하는 메서드(장바구니 상품 옆에 x버튼 누르면 장바구니 내 상품이 삭제 됨)
	 */
	@ResponseBody
	@RequestMapping(value = "/cart/delCart")
	public int delCart(int cartNo) throws Exception {

		System.out.println("컨트롤러" + cartNo);
		service.delCart(cartNo);

		return 1;

	}

	/**
	 * @methodName : qtyCartUpdate
	 * @author :하수영
	 * @date : 2022. 10. 21.
	 * @입력 param :
	 * @returnType : int 장바구니 수량 업데이트 메서드
	 */
	@ResponseBody
	@RequestMapping(value = "/cart/updateCart")
	public int qtyCartUpdate(@RequestParam("cartNo") int cartNo, int qty) throws Exception {
		System.out.println("~~~~~~~" + cartNo + qty);
		service.qtyCartUpdate(cartNo, qty);

		return 1;
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
