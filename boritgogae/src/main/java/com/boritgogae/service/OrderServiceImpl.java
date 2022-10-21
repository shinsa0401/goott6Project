package com.boritgogae.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderProductDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.persistence.MemberDAO;
import com.boritgogae.persistence.OrderDAO;
import com.boritgogae.persistence.ProductDAO;

@Service
public class OrderServiceImpl implements OrderService {
   
   @Inject
   private OrderDAO orderDao;
   
   @Inject
   private ProductDAO prodDao;
   
   @Inject
   private MemberDAO memDao;

@Override
public List<CouponVo> getAvailableCoupon(String memberId) {
	List<CouponVo> availableCoupon = new ArrayList<>();
	
	//couponUsed와 coupon을 가져와서 issueDate와 useDate 비교 후 리턴
	List<CouponUsedVo> couponLst = orderDao.getUsableCoupon(memberId);
	
	Timestamp now = new Timestamp(System.currentTimeMillis());
	
	for(CouponUsedVo vo : couponLst) {
		CouponVo coupon = orderDao.getCouponByCouponName(vo.getCouponName());
		if(vo != null) {
			if((now.getTime() - vo.getIssueDate().getTime())/1000/60/60/24 < coupon.getCouponUseDate()) {
				coupon.setDueDate((long)vo.getIssueDate().getTime() + (long)coupon.getCouponUseDate() * 1000 * 60 * 60 * 24);
				availableCoupon.add(coupon);
			}
		}
	}
	return availableCoupon;
}


	@Override
	public DeliveryFeeVo getDeliveryOption(OrderDTO order) { 
		List<String> expensive = new ArrayList<String>();
		expensive.add("제주특별자치도");
		expensive.add("울릉군");
		expensive.add("거제시");

		String addr = order.getAddress();
		String detailAddr = order.getDetailAddress();
		DeliveryFeeVo result = new DeliveryFeeVo();
		//ismember이 y일때 - isAdmin이 y일때 - 무료
		//ismember가 y일때 - prodTotalPrice가 50000이상일 때 -무료 - 도서산간이면서 5만원 이상일 때 무료
		//address가 도서산간일 때 - 8000원
		//도서산간이 아닐 때 - 3000원
//		if(order.getIsMember().equals("Y")) {
//			if(memDao.getMemInfo(order.getMemberId()).getIsAdmin().equals("Y") || order.getProdTotalPrice() > 50000) {
//				orderDao.getdeliInfo("무료");
//			}else if(){
//				
//			}else {
//				for(String s : expensive) {
//					if(order.getAddress().contains(s)) {
//						orderDao.getdeliInfo("도서");
//					}
//				}
//			}
//			
//		}
	
		
	
		return null;
	}  
}
