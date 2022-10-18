package com.boritgogae.board.prodReply.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReplyDTO;
import com.boritgogae.board.prodReply.domain.ReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewDTO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.Paging;
import com.boritgogae.board.prodReply.etc.UploadImg;


public interface ReviewDAO {
	
	//리뷰쓰는 메서드
	public int insertReview(ReviewDTO dto) throws Exception;
	
	//아이디와 상품id로 주문 불러오는 메서드
	public List<OrdersVO> getOrder(String userId, String prodNo) throws Exception;
	
	//마지막 리뷰번호 불러오는 메서드
	public int getLastReviewNo() throws Exception;
	
	//리뷰 사진 저장하는 메서드
	public int saveReviewImg(UploadImg img) throws Exception;
	
	//제품번호에 따라 리뷰 불러오는 메서드
	public List<ReviewVO> getReviews(String prodNo, Paging page) throws Exception;
	
	//제품번호에 따라 리뷰이미지 불러오는 메서드
	public List<UploadImg> getReviewImgs(int reviewNo) throws Exception;
	
	//리뷰번호로 리뷰 지우는 메서드
	public int deleteReview(int reviewNo) throws Exception;
	
	//리뷰번호로 리뷰 불러오는 메서드
	public ReviewVO getReviewByRno(int reviewNo) throws Exception;
	
	//리뷰번호로 리뷰 이미지 불러오는 메서드
	public List<UploadImg> getReviewImgByRno(int reviewNo) throws Exception;
	
	//리뷰 수정하는 메서드
	public int modifyReview(ReviewVO vo) throws Exception;
	
	//상품번호에 따른 리뷰 갯수 가져오는 메서드
	public int getReviewCntByProdNo(String prodNo) throws Exception;
	
	//댓글 저장하는 메서드
	public int writeReply(ReplyDTO dto) throws Exception;
	
	//상품번호의 댓글 불러오는 메서드
	public List<ReplyVo> getReplies(String prodNo) throws Exception;
	
	//댓글번호로 댓글 삭제하는 메서드
	public int deleteReply(int rno) throws Exception;
	
	//리뷰번호로 리뷰이미지 삭제하는 메서드
	public int deleteReviewImg(int reviewNo) throws Exception;
	
	//댓글의 마지막 pk 얻어오는 메서드
	public int getLstRno() throws Exception;
	
	//댓글 ref번호 업데이트하는 메서드
	public int updateRef(int rno) throws Exception;
	
	//댓글번호로 댓글 가져오는 메서드
	public ReplyVo getReply(int rno) throws Exception;
	
	//댓글의 step과 refOrder업데이트하는 메서드
	public int updateStepRO(ReplyVo parentVo, int rno) throws Exception;
	
	//대댓글의 ref업데이트하는 메서드
	public int updateReRef(int rno, int parentRef) throws Exception;
}
