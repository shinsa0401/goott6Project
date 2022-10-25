package com.boritgogae.persistence;

import java.sql.Timestamp;
import java.util.List;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DetailOrderDTO;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderVo;
import com.boritgogae.domain.PointHistoryDTO;
import com.boritgogae.domain.ProductVO;

public interface OrderDAO {
	/**
	 * @methodName : getCouponByCouponName
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : couponName
	 * @returnType : CouponVo
	 **/
	public CouponVo getCouponByCouponName(String couponName);
	
	/**
	 * @methodName : getUsableCoupon : 사용가능한 쿠폰 리스트 얻어옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : memberId
	 * @returnType : List<CouponUsedVo>
	 **/
	public List<CouponUsedVo> getUsableCoupon(String memberId);
	
	/**
	 * @methodName : getdeliInfo 배송비vo를 얻어옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 주소
	 * @returnType : DeliveryFeeVo
	 **/
	public DeliveryFeeVo getdeliFee(String deliveryOption);
	
	public int insertOrder(OrderDTO order);
	
	public int lastOrderNo();
	
	public int insertDetailOrder(DetailOrderDTO detailOrder);
	
	public int lastDetailNo();
	
	public int updateDetailInit(int lastDetailNo);
	
	public int getPointNo(String pointWhy);
	
	public int insertPointHistory(PointHistoryDTO pointHistory);
	
	public int updateCouponUsed(CouponUsedVo couponUsedVo);
	
	public OrderVo getOrderByOrderNo(int orderNo);
	

}
