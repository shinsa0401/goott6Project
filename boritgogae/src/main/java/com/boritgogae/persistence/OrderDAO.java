package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;

public interface OrderDAO {
	//장바구니 추가 메서드
	public int addCart(CartDTO cart)throws Exception;
	//장바구니 조회 메서드
	public List<CartDTO> getCartList(String memberId)throws Exception;
	
	public List<DetailOrderVo> getPopular() throws Exception;
}
