package com.boritgogae.board.prodReply;

import java.util.List;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReviewDTO;

public interface ReviewService {

	// 리뷰 추가하는 메서드
	boolean addReview(ReviewDTO dto);

	//주문자와 상품번호로 주문 가져오는 메서드
//	List<OrdersVO> getOrder(String userId, String prodNo);
		
}
