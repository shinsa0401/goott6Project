package com.boritgogae.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryFeeVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.DetailOrderDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDTO;
import com.boritgogae.domain.OrderProductDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.PointHistoryDTO;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.persistence.MemberDAO;
import com.boritgogae.persistence.OrderDAO;
import com.boritgogae.persistence.ProductDAO;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;

@Service
public class OrderServiceImpl implements OrderService {
   
   @Inject
   private OrderDAO orderDao;
   
   @Inject
   private ProductDAO prodDao;
   
   @Inject
   private MemberDAO memDao;

@Override
public Map<CouponVo, CouponUsedVo> getAvailableCoupon(String memberId) {
	Map<CouponVo, CouponUsedVo> couponMap = new HashMap<CouponVo, CouponUsedVo>();
	
	//couponUsed와 coupon을 가져와서 issueDate와 useDate 비교 후 리턴
	List<CouponUsedVo> couponLst = orderDao.getUsableCoupon(memberId);
	
	Timestamp now = new Timestamp(System.currentTimeMillis());
	
	for(CouponUsedVo vo : couponLst) {
		CouponVo coupon = orderDao.getCouponByCouponName(vo.getCouponName());
		if(vo != null) {
			if(vo.getExpirationDate().compareTo(now) > 0) {
				couponMap.put(coupon, vo);
			}
		}
	}

	return couponMap;
}


@Override
public DeliveryFeeVo getDeliveryOption(OrderDTO order) { 
	List<String> expensive = new ArrayList<String>();
	expensive.add("제주특별자치도");
	expensive.add("울릉군");
	expensive.add("거제시");

	String addr = order.getAddress();
	DeliveryFeeVo result = new DeliveryFeeVo();
	System.out.println(addr);
	
	MemberVo member = memDao.getMemInfo(order.getMemberId());
	
	
	//orders에 isMember세팅해서 오기
	//ismember이 y일때 - isAdmin이 y일때 - 무료
	//ismember가 y일때 - prodTotalPrice가 50000이상일 때 -무료 - 도서산간이면서 5만원 이상일 때 무료
	//address가 도서산간일 때 - 8000원
	//도서산간이 아닐 때 - 3000원
	if (member != null) {
		if(memDao.getMemInfo(order.getMemberId()).getIsAdmin().equals("Y")) {
			result = orderDao.getdeliFee("관리");
		}else if (order.getProdTotalPrice() > 50000) {
			result = orderDao.getdeliFee("무료");
		}else {
			for(String s : expensive) {
				if(addr.contains(s)) {
					result = orderDao.getdeliFee("도서");
					break;
				}else {
					result =orderDao.getdeliFee("기본");
				}
			}
		}
	}
	else {
		for(String s : expensive) {
			if(addr.contains(s)) {
				result = orderDao.getdeliFee("도서");
				break;
			}else {
				result =orderDao.getdeliFee("기본");
			}
		}
	}
	
	
	
	return result;
}

	@Transactional
	@Override
	public OrdersVo placeOrder(OrderDTO order, String couponName, OrderSheetDTO ordersheet) {
		if(order.getIsMember() != null) {
			order.setIsMember("Y");
		}else {
			order.setIsMember("N");
		}
		System.out.println(order.toString());
		
		//트랜잭션 처리 해보기
		//order에 넣기
		int orderRow = orderDao.insertOrder(order);
		System.out.println("주문인서트"+orderRow);
		//마지막으로 insert된 order의 no 불러오기
		int orderNo = orderDao.lastOrderNo();
		
		OrdersVo currentOrder = orderDao.getOrderByOrderNo(orderNo);
		
		//detailorder에 넣기, initialOrderDetailNo 업데이트
		List<OrderProductDTO> orderProds = ordersheet.getOrderProducts();
		
		for(OrderProductDTO prod : orderProds) {
			DetailOrderDTO detailOrder = new DetailOrderDTO(orderNo, prod.getProdNo(), prod.getQty(), prodDao.getProd(prod.getProdNo()).getProdPrice() * prod.getQty(), currentOrder.getMemberId());
			int detailRow = orderDao.insertDetailOrder(detailOrder);
			System.out.println("주문상세인서트" + detailRow);
			int detailNo = orderDao.lastDetailNo();
			orderDao.updateDetailInit(detailNo);
		}
		
		//쿠폰 사용 했다면 쿠폰 사용내역 업데이트
		if(couponName != null) {
			CouponUsedVo coupon = new CouponUsedVo();
			coupon.setCouponName(couponName);
			coupon.setOrderNo(orderNo);
			coupon.setUseDate(currentOrder.getOrderDate());
			coupon.setMemberId(currentOrder.getMemberId());
			orderDao.updateCouponUsed(coupon);
		}
		
		// 포인트 사용, 적립 내역 업데이트(pointWhy불러와서)
		int usePointNo = orderDao.getPointNo("구매사용");
		PointHistoryDTO usedPoint = new PointHistoryDTO(order.getMemberId(), usePointNo, -order.getUsedPoint(), orderNo);
		
		int accumPointNo = orderDao.getPointNo("구매적립");
		PointHistoryDTO accumPoint = new PointHistoryDTO(order.getMemberId(), accumPointNo, order.getAccumPoint(), orderNo);
		
		int useRow = orderDao.insertPointHistory(usedPoint);
		int accumRow = orderDao.insertPointHistory(accumPoint);
		
		//회원 테이블의 포인트 업데이트
		int pointrow = memDao.updateMemberPoint(currentOrder.getMemberId());

		
		return currentOrder;
	}

	@Inject
	public OrderDAO dao;
	
	@Override
	public void addCartMem(CartDTO cart) throws Exception {
		
		dao.addCartMem(cart);
		
	}
	@Override
	public void addCartGuest(CartDTO cart) throws Exception {
		
		dao.addCartGuest(cart);
		
		System.out.println("서비스 상품담김"+cart.toString());
		
	}

	@Override
	public List<CartDTO> selectCartListMem(String memberId)throws Exception {
	
//		List<CartDTO> cart = dao.getCartList(memberId);
		System.out.println("제발 걸려바"+dao.selectCartListMem(memberId).toString());
		
		return dao.selectCartListMem(memberId);
	
	}
	@Override
	public List<CartDTO> selectCartListGuest(String sessionId)throws Exception {
	
//		List<CartDTO> cart = dao.getCartList(sessionId);
		System.out.println("제발 걸려바 게스트"+dao.selectCartListGuest(sessionId).toString());
		
		return dao.selectCartListGuest(sessionId);
	
	}

	@Override
	public void delCart(int cartNo) throws Exception {
		System.out.println("서비스"+cartNo);
		dao.delCart(cartNo);
		
	}

	@Override
	public void qtyCartUpdate(int cartNo, int qty) throws Exception {
		System.out.println(cartNo+ qty);
		dao.qtyCartUpdate(cartNo, qty);
		
	}
	
	@Override
	public List<DetailOrderVo> popularProd() throws Exception {
		List<DetailOrderVo> lst = dao.getPopular();
		return lst;
	}
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	@Override
	public OrdersVo guestOrderInfo(GuestOrderDTO gdto) throws Exception {
		return dao.selectGuestOrderInfo(gdto);
	}

}
