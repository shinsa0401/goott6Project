package com.boritgogae.persistence;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DetailOrderDTO;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderVo;
import com.boritgogae.domain.PointHistoryDTO;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;

@Repository
public class OrderDAOImpl implements OrderDAO {
   
   @Inject
   private SqlSession ses;
   
   private static String ns = "com.boritgogae.orderMapper";

   @Override
   public CouponVo getCouponByCouponName(String couponName) {
	   
	   return ses.selectOne(ns+".getCouponByCouponName", couponName);
   }

   @Override
   public List<CouponUsedVo> getUsableCoupon(String memberId) {
	   
	   return ses.selectList(ns+".getUsableCoupon", memberId);
   }

   @Override
   public DeliveryFeeVo getdeliFee(String deliveryOption) {
	
	   return ses.selectOne(ns+".getDeliveryFeeVo", deliveryOption);
   }

   @Override
   public int insertOrder(OrderDTO order) {

	   return ses.insert(ns+".insertOrder", order);
   }

   @Override
   public int lastOrderNo() {
	   
	   return ses.selectOne(ns+".getLastOrderNo");
   }

   @Override
   public int insertDetailOrder(DetailOrderDTO detailOrder) {
	
	   return ses.insert(ns+".insertDetailOrder", detailOrder);
   }

   @Override
   public int lastDetailNo() {
	
	   return ses.selectOne(ns+".getLastDetailNo");
   }

   @Override
   public int updateDetailInit(int lastDetailNo) {
	   
	   return ses.update(ns+".updateDetailInit", lastDetailNo);
   }

   @Override
   public int getPointNo(String pointWhy) {
	
	   return ses.selectOne(ns+".getPointNo", pointWhy);
	}

   @Override
   public int insertPointHistory(PointHistoryDTO pointHistory) {
	   
	   return ses.insert(ns+".insertPointHistory", pointHistory);
   }

   @Override
   public int updateCouponUsed(CouponUsedVo couponUsedVo) {
	   
	   return ses.update(ns+".updateCouponUsed", couponUsedVo);
   }

   @Override
   public OrderVo getOrderByOrderNo(int orderNo) {
	   
	   return ses.selectOne(ns+".getOrderByOrderNo", orderNo);
   }

	
	@Override
	public int addCart(CartDTO cart) throws Exception {
		
		return ses.insert(ns+".addCart");
	}

	@Override
	public List<CartDTO> getCartList(String memberId) throws Exception {
		
		return ses.selectList(ns+".selectCartList");
	}
	
	@Override
	public List<DetailOrderVo> getPopular() throws Exception {
	
		return ses.selectList(ns+".popularList");
	}
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	@Override
	public OrdersVo selectGuestOrderInfo(GuestOrderDTO gdto) throws Exception {
		System.out.println("DAO : 비회원 주문내역 조회");
		return ses.selectOne(ns + ".selectGuestOrderInfo", gdto);
	}
}
