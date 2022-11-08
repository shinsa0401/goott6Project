package com.boritgogae.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.AdminOrdersPagingInfo;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrderDetailDTO;
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

	// 관리자 주문 조회
	@Override
	public List<OrdersVo> getOrders(AdminOrdersPagingInfo pi) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("StartNum", pi.getStartNum());
		map.put("PostPerPage", pi.getPostPerPage());
		
		return ses.selectList(ns+".getOrders",map);
	}

	// 관리자 주문 상세 조회
	@Override
	public List<OrderDetailDTO> getDetailOrderInfo(int orderNo) throws Exception {
		System.out.println(orderNo+"이거 들어왔나?");//성공
		System.out.println(ses.selectList(ns+".getOrdersDetailInfo", orderNo)+"여기는 DAO여");
		return ses.selectList(ns+".getOrdersDetailInfo", orderNo);
	}

	// 관리자가 오늘 해야할일
	@Override
	public List<OrderDetailDTO> getAdminTodoList() throws Exception {

		return ses.selectList(ns+".AdminTodoList");
	}

	@Override
	public int countOrder() throws Exception {

		return ses.selectOne(ns+".countOrder");
	}

	@Override
	public int adminAllowOrders() throws Exception {

		return ses.selectOne(ns+".adminAllowOrders");
	}


}
