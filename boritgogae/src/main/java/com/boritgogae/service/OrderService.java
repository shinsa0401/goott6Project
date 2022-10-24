package com.boritgogae.service;

import java.util.List;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;

public interface OrderService {
	//장바구니 추가 
	public boolean addCart(CartDTO cart)throws Exception;
	//장바구니 조회
	public List<CartDTO> getCartList(String memberId)throws Exception;
	public List<DetailOrderVo> popularProd() throws Exception;
}
