package com.boritgogae.service;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.OrderVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;

@Service
public interface OrderService {
	/**
	 * @methodName : getCouponByMemId
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : memberId
	 * @returnType : CouponVo
	 **/
	public Map<CouponVo, CouponUsedVo> getAvailableCoupon(String memberId);
	
	/**
	 * @methodName : getDeliveryOption
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : DeliveryInfoVo(memberId, addr, detailAddress)
	 * @returnType : DeliveryFeeVo
	 **/
	public DeliveryFeeVo getDeliveryOption(OrderDTO order);
	
	public OrderVo placeOrder(OrderDTO order, String couponName, OrderSheetDTO ordersheet);


	//장바구니 추가 
	public boolean addCart(CartDTO cart)throws Exception;
	//장바구니 조회
	public List<CartDTO> getCartList(String memberId)throws Exception;
	public List<DetailOrderVo> popularProd() throws Exception;
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	public OrdersVo guestOrderInfo(GuestOrderDTO gdto) throws Exception;
	
	/**
	 * @methodName : getorders(멤버가 있을 때 멤버아이디로 주문이력 불러오는 메서드)
	 * @author : kjy
	 * @date : 2022. 10. 27.
	 * @입력 param : 
	 * @returnType : List<OrderVo>
	 */
	public List<OrderVo> getordersByMemberId(String memberId) throws Exception;
	
	public OrderVo getorderByOrderNo(int orderNo) throws Exception;

}
