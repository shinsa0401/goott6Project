package com.boritgogae.persistence;

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
import com.boritgogae.domain.OrderDetailDTO;
import com.boritgogae.domain.PointHistoryDTO;
import com.boritgogae.domain.AdminOrdersPagingInfo;
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
   public OrdersVo getOrderByOrderNo(int orderNo) {
	   
	   return ses.selectOne(ns+".getOrderByOrderNo", orderNo);
   }
   //????????????
   @Override
	public void addCartMem(CartDTO cart) throws Exception {
		
		 ses.insert(ns+".addCartMem");
	}
	@Override
	public void addCartGuest(CartDTO cart) throws Exception {
		
		 ses.insert(ns+".addCartGuest");
	}

	@Override
	public List<CartDTO> selectCartListMem(String memberId) throws Exception {
//		System.out.println(memberId);
		return ses.selectList(ns+".selectCartListMem", memberId);
	}
	
	@Override
	public List<CartDTO> selectCartListGuest(String sessionId) throws Exception {
//		System.out.println(memberId);
		return ses.selectList(ns+".selectCartListGuest", sessionId);
	}
   
	// ????????? ????????? ?????? ?????? ???????????? ???????????? ?????????
	@Override
	public OrdersVo selectGuestOrderInfo(GuestOrderDTO gdto) throws Exception {
		System.out.println("DAO : ????????? ???????????? ??????");
		return ses.selectOne(ns + ".selectGuestOrderInfo", gdto);
	}
	
	// ?????????????????? ???????????? ????????? ???????????? ?????????
	@Override
	public OrdersVo findGuestPwdSelectOrder(OrdersVo order) throws Exception {
		System.out.println("DAO : ????????? ????????????????????????");
		return ses.selectOne(ns + ".findGuestPwdSelectOrder", order);
	}
	
	// ??????????????? ????????? ????????????????????? ????????????????????? ????????????
	@Override
	public int updateGuestPwd(int orderNo, String tempPwd) throws Exception {
		System.out.println("DAO : ????????? ?????????????????? ????????????");
		System.out.println("DAO : " + orderNo + ", " + tempPwd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("tempPwd", tempPwd);
		return ses.update(ns + ".updateGuestPwd", map);
	}
	
	
	
	@Override
	public void delCart(int cartNo) throws Exception {
		System.out.println("??????"+cartNo);
		 ses.delete(ns+".delCartProd",cartNo);
		
	}

	@Override
	public void qtyCartUpdate(int cartNo, int qty) throws Exception {
		System.out.println("????????????????????/");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cartNo", cartNo);
		map.put("qty", qty);
		
		ses.update(ns+".qtyCartUpdate",map );
		
	}
	@Override
	public List<DetailOrderVo> getPopular() throws Exception {
	
		return ses.selectList(ns+".popularList");
	}

	
	// ????????? ?????? ??????
	@Override
	public List<OrdersVo> getOrders(AdminOrdersPagingInfo pi) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("StartNum", pi.getStartNum());
		map.put("PostPerPage", pi.getPostPerPage());
		
		return ses.selectList(ns+".getOrders",map);
	}

	// ????????? ?????? ?????? ??????
	@Override
	public List<OrderDetailDTO> getDetailOrderInfo(int orderNo) throws Exception {
		System.out.println(orderNo+"?????? ?????????????");//??????
		System.out.println(ses.selectList(ns+".getOrdersDetailInfo", orderNo)+"????????? DAO???");
		return ses.selectList(ns+".getOrdersDetailInfo", orderNo);
	}

	// ???????????? ?????? ????????????
	@Override
	public List<OrderDetailDTO> getAdminTodoList() throws Exception {

		return ses.selectList(ns+".AdminTodoList");
	}

	@Override
	public int countOrder() throws Exception {

		return ses.selectOne(ns+".countOrder");
	}

	@Override
	public int adminAllowOrders() throws Exception {

		return ses.selectOne(ns+".adminAllowOrders");
	}
	
	@Override
	public List<OrdersVo> getOrdersByMemberId(String memberId) throws Exception {
		
		return ses.selectList(ns+".getOrdersByMemberId", memberId);
	}

	@Override
	public List<DetailOrderVo> getDetailOrderByMemberId(String memberId) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectList(ns+".getDetailOrderByMemberId", memberId);
	}
	


}
