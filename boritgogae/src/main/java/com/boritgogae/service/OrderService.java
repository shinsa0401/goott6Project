package com.boritgogae.service;

import java.util.List;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;

public interface OrderService {
	//장바구니 추가 
	public boolean addCart(CartDTO cart)throws Exception;
	//장바구니 조회
	public List<CartDTO> getCartList(String memberId)throws Exception;
	public List<DetailOrderVo> popularProd() throws Exception;
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	public OrdersVo guestOrderInfo(GuestOrderDTO gdto) throws Exception;
}
