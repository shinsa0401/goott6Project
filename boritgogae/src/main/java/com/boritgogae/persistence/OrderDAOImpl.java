package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrdersVo;
@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.orderMapper";
	
	@Override
	public int addCart(CartDTO cart) throws Exception {
		
		return ses.insert(ns+".addCart");
	}

	@Override
	public List<CartDTO> getCartList(String memberId) throws Exception {
		
		return ses.selectList(ns+".selectCartList");
	}
	
	@Override
	public List<DetailOrderVo> getPopular() throws Exception {
	
		return ses.selectList(ns+".popularList");
	}
	
	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	@Override
	public OrdersVo selectGuestOrderInfo(GuestOrderDTO gdto) throws Exception {
		System.out.println("DAO : 비회원 주문내역 조회");
		return ses.selectOne(ns + ".selectGuestOrderInfo", gdto);
	}

}
