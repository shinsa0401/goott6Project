package com.boritgogae.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;

@Repository
public interface MemberDAO {

	// 등급혜택을 가져오는 메서드
	List<GradesVo> showGradeBenefit() throws Exception;

	// 쿠폰혜택을 가져오는 메서드
	List<CouponVo> showCouponBenefit() throws Exception;

	// 유저가 보유한 포인트를 가져오는 메서드
	int pointNow(String memberId) throws Exception;

	// 유저의 포인트 내역을 가져오는 메서드
	List<PointHistoryVo> showPointHistory(String memberId) throws Exception;

	// 유저의 쿠폰 보유 내역을 가져오는 메서드
	List<CouponUsedVo> showCouponHaveList(String memberId) throws Exception;

	// 유저의 쿠폰 사용 내역을 가져오는 메서드
	List<CouponUsedVo> showCouponUsedList(String memberId) throws Exception;
	
	// 유저의 작성글을 가져오는 메서드
	List<UserBoardVo> showUserBoardList(String memberId) throws Exception;

	// 유저의 작성 댓글을 가져오는 메서드
	List<UserReplyVo> showUserReplyList(String memberId) throws Exception;

	// 유저가 쓴 리뷰 리스트를 가져오는 메서드
	List<ReviewVO> showUserReviewList(String memberId) throws Exception;

	// 유저가 리뷰를 쓰지 않은 구매확정 리스트를 가져오는 메서드
	List<OrderDetailVO> userAbleReviewList(String memberId) throws Exception;

	// 상품코드에 맞는 상품명을 반환해준다.
	String convertProdNoToProdName(String prodCode) throws Exception;

}
