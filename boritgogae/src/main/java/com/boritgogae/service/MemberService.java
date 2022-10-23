package com.boritgogae.service;

import java.util.List;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;


public interface MemberService {

	// 로그인을 처리하는 메서드
	public MemberVo logIn(LogInDTO dto, HttpServletRequest request) throws Exception;

	// 자동로그인을 체크했을 경우 로그인 유지를 위한 sessionId, sessionLimit 업데이트
	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit) throws Exception;
	
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
