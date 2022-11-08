package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.AdminOrdersPagingInfo;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrderDetailDTO;
import com.boritgogae.domain.OrdersVo;

public interface OrderDAO {
	//장바구니 추가 메서드
	public int addCart(CartDTO cart)throws Exception;
	//장바구니 조회 메서드
	public List<CartDTO> getCartList(String memberId)throws Exception;
	
	public List<DetailOrderVo> getPopular() throws Exception;
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	public OrdersVo selectGuestOrderInfo(GuestOrderDTO gdto) throws Exception;
	
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
	
}
