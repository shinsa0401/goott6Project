package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
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

}
