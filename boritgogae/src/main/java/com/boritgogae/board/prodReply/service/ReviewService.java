package com.boritgogae.board.prodReply.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReplyDTO;
import com.boritgogae.board.prodReply.domain.ReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewDTO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.UploadImg;


public interface ReviewService {

	// 리뷰 추가하는 메서드
	public boolean addReview(ReviewDTO dto) throws Exception;

	//주문자와 상품번호로 주문 가져오는 메서드
//	List<OrdersVO> getOrder(String userId, String prodNo);
	
	//리뷰 이미지 저장하는 메서드
	public boolean saveReviewImg(int reviewNo, List<UploadImg> imgLst) throws Exception;
	
	//마지막에 insert한 reviewNo얻어오는 메서드
	public int getLastReviewNo() throws Exception;
	
	//제품 상세페이지의 리뷰와 페이징정보 불러오는 메서드
	public Map<String, Object> getReviews(String prodNo, int pageNo) throws Exception;
	
	//제품 리뷰의 이미지 불러오는 메서드
	public List<UploadImg> getReviewImgs(int reviewNo) throws Exception;
	
	//리뷰 지우는 메서드
	public boolean deleteReview(int reviewNo) throws Exception;
	
	//수정페이지에 넣을 리뷰와 이미지 가져오는 메서드 <- 리뷰 가져오는메서드, 이미지 가져오는 메서드로 나누기?
	public Map<String, Object> getReviewByRno(int reviewNo) throws Exception;
	
	//리뷰 이미지 서버에서 삭제하는 메서드<= rest컨트롤러에서 빼내오기?
	public boolean deleteReviewImg(int reviewNo) throws Exception;
	
	//리뷰 수정하는 메서드
	public boolean modifyReview(ReviewVO vo) throws Exception;
	
	//댓글 저장하는 메서드
	public boolean writeReply(ReplyDTO dto) throws Exception;
	
	//상품 번호에 따른 댓글 불러오는 메서드
	public List<ReplyVo> getReplies(String prodNo) throws Exception;
	
	//댓글번호로 댓글 삭제하는 메서드
	public boolean deleteReply(int rno) throws Exception;
	
	//대댓글 저장하는 메서드
	public boolean writeReReply(ReplyDTO reply) throws Exception;
}
