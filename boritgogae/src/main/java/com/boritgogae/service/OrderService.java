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
import com.boritgogae.domain.ProductVO;

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
}
