package com.boritgogae.board.prodReply;

import java.util.List;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReviewDTO;

public interface ReviewDAO {
	
	//리뷰쓰는 메서드
//	public int insertReview(ReviewDTO dto);
	
	//아이디와 상품id로 주문 불러오는 메서드
	public List<OrdersVO> getOrder(String userId, String prodNo);
	
}
