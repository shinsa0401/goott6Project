package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.persistence.OrderDAO;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Inject
	public OrderDAO dao;
	
	@Override
	public boolean addCart(CartDTO cart) throws Exception {
		boolean result = false;
		
		if(dao.addCart(cart)==1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public List<CartDTO> getCartList(String memberId)throws Exception {
	
		List<CartDTO> cart = dao.getCartList(memberId);
		
		//cartDTO에는 가격정보 없는데,, 가격정보 있는 DTO사용?
		
		return cart;
	
	} 
	
	@Override
	public List<DetailOrderVo> popularProd() throws Exception {
		List<DetailOrderVo> lst = dao.getPopular();
		return lst;
	} 

}
