package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.CartDTO;

public interface OrderDAO {
	//장바구니 추가 메서드
	public int addCart(CartDTO cart)throws Exception;
	//장바구니 조회 메서드
	public List<CartDTO> getCartList(String memberId)throws Exception;
}
