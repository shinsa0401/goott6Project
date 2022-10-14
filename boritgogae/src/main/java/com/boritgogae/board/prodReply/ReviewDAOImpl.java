package com.boritgogae.board.prodReply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReviewDTO;

public class ReviewDAOImpl implements ReviewDAO {
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardProdReplyMapper";

//	@Override
//	public int insertReview(ReviewDTO dto) {
//		
//		return 0;
//	}

	@Override
	public List<OrdersVO> getOrder(String userId, String prodNo) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("prodNo", prodNo);

		List<OrdersVO> orderLst = ses.selectList(".getOrders", map);
		
		return orderLst;
	}
	
}
