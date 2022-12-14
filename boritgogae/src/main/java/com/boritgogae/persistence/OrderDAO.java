package com.boritgogae.persistence;

import java.util.List;

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
	
	/**
	 * @methodName : insertOrder 주문 인서트
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int insertOrder(OrderDTO order);
	
	/**
	 * @methodName : lastOrderNo 마지막으로 인서트된 주문번호 가져옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int lastOrderNo();
	
	/**
	 * @methodName : insertDetailOrder 디테일 오더 인서트
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int insertDetailOrder(DetailOrderDTO detailOrder);
	
	/**
	 * @methodName : lastDetailNo 마지막으로 인서트된 디테일오더 번호 가져옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int lastDetailNo();
	
	/**
	 * @methodName : updateDetailInit 최초주문상세번호 업데이트
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int updateDetailInit(int lastDetailNo);
	
	/**
	 * @methodName : getPointNo 포인트 pk가져옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int getPointNo(String pointWhy);
	
	/**
	 * @methodName : insertPointHistory 포인트내역에 인서트
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int insertPointHistory(PointHistoryDTO pointHistory);
	
	/**
	 * @methodName : updateCouponUsed 쿠폰사용내역 업데이트
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : int
	 */
	public int updateCouponUsed(CouponUsedVo couponUsedVo);
	
	/**
	 * @methodName : getOrderByOrderNo 주문번호로 주문건 가져옴
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : 
	 * @returnType : OrderVo
	 */
	public OrdersVo getOrderByOrderNo(int orderNo);
	


	//장바구니 추가 메서드
	public void addCartMem(CartDTO cart)throws Exception;
	public void addCartGuest(CartDTO cart)throws Exception;
	//장바구니 조회 메서드
	public List<CartDTO> selectCartListMem(String memberId)throws Exception;
	public List<CartDTO> selectCartListGuest(String sessionId)throws Exception;
	//장바구니 삭제 메서드
	public void delCart(int cartNo)throws Exception;
	//장바구니 수량 업데이트 메서드
	public void qtyCartUpdate(int cartNo, int qty)throws Exception;
	
	public List<DetailOrderVo> getPopular() throws Exception;

	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	public OrdersVo selectGuestOrderInfo(GuestOrderDTO gdto) throws Exception;

	// 주문비밀번호 찾기위해 주문건 검색하는 메서드
	public OrdersVo findGuestPwdSelectOrder(OrdersVo order) throws Exception;

	// 주문번호로 비회원 주문비밀번호를 임시비밀번호로 업데이트
	public int updateGuestPwd(int orderNo, String tempPwd) throws Exception;
	
	
	// 관리자 주문조회용
	public List<OrdersVo> getOrders(AdminOrdersPagingInfo pi) throws Exception;
	
	// 관리자 주문상세조회용
	public List<OrderDetailDTO> getDetailOrderInfo(int orderNo) throws Exception;
	
	// 오늘 처리해야할 관리자의 일
	public List<OrderDetailDTO> getAdminTodoList() throws Exception;
	
	// 주문 카운트
	public int countOrder() throws Exception;
	
	// 관리자승인주문
	public int adminAllowOrders() throws Exception;
	
	//멤버아이디로 주문내역 가져옴
	public List<OrdersVo> getOrdersByMemberId(String memberId) throws Exception;
	
	//멤버아이디로 주문상세내역 가져옴
	public List<DetailOrderVo> getDetailOrderByMemberId(String memberId) throws Exception;

}
