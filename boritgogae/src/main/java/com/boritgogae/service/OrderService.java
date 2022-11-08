package com.boritgogae.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrderDetailDTO;
import com.boritgogae.domain.OrdersVo;

public interface OrderService {
	//장바구니 추가 
	public boolean addCart(CartDTO cart)throws Exception;
	//장바구니 조회
	public List<CartDTO> getCartList(String memberId)throws Exception;
	public List<DetailOrderVo> popularProd() throws Exception;
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	public OrdersVo guestOrderInfo(GuestOrderDTO gdto) throws Exception;
	
	// 관리자 주문조회
	public Map<String, Object> getOrders(int pageNo) throws Exception;
	
	// 관리자 주문 상세조회
	public List<OrderDetailDTO> getDetailOrdersInfo(int orderNo) throws Exception;
	
	// 관리자 오늘 해야할일
	public List<OrderDetailDTO> getAdminTodoList() throws Exception;
	
	// 총주문 카운트
	public int countOrder() throws Exception;
	
	// 관리자 승인주문
	public int adminAllowOrders() throws Exception;
}
