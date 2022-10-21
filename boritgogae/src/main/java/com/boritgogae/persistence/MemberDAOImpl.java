package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
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
public class MemberDAOImpl implements MemberDAO {

	// Mapper NameSpace 처리하기
	private static String ns = "com.boritgogae.memberMapper";
	
	// sqlSession 객체 주입
	@Inject
	private SqlSession ses;	

	// 등급혜택을 가져오는 메서드
	@Override
	public List<GradesVo> showGradeBenefit() throws Exception {
		System.out.println("DAO : 등급혜택불러오기 ");
		return ses.selectList(ns+".showGradeBenefit");
	}

	// 쿠폰혜택을 가져오는 메서드
	@Override
	public List<CouponVo> showCouponBenefit() throws Exception {
		System.out.println("DAO : 쿠폰혜택불러오기 ");
		return ses.selectList(ns+".showCouponBenefit");
	}

	// 유저가 보유한 포인트를 가져오는 메서드
	@Override
	public int pointNow(String memberId) throws Exception{
		System.out.println("DAO : 포인트 불러오기 ");
		return ses.selectOne(ns+".pointNow", memberId);
	}

	// 유저의 포인트 내역을 가져오는 메서드
	@Override
	public List<PointHistoryVo> showPointHistory(String memberId) throws Exception {
		System.out.println("DAO : 포인트 내역 불러오기 ");
		return ses.selectList(ns+".showPointHistory", memberId);
	}
	
	// 유저의 쿠폰 보유 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponHaveList(String memberId) throws Exception {
		System.out.println("DAO : 쿠폰 보유 내역 불러오기 ");
		return ses.selectList(ns+".showCouponHaveList", memberId);
	}

	// 유저의 쿠폰 사용 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponUsedList(String memberId) throws Exception {
		System.out.println("DAO : 쿠폰 사용 내역 불러오기 ");
		return ses.selectList(ns+".showCouponUsedList", memberId);
	}

	// 유저의 작성글을 가져오는 메서드
	@Override
	public List<UserBoardVo> showUserBoardList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성글 내역 불러오기 ");
		return ses.selectList(ns+".showUserBoardList", memberId);
	}
	
	// 유저의 작성 댓글을 가져오는 메서드
	@Override
	public List<UserReplyVo> showUserReplyList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성 댓글 내역 불러오기 ");
		return ses.selectList(ns+".showUserReplyList", memberId);
	}

	// 유저가 쓴 리뷰 리스트를 가져오는 메서드
	@Override
	public List<ReviewVO> showUserReviewList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성 리뷰 리스트 불러오기 ");
		return ses.selectList(ns+".showUserReviewList", memberId);
	}

	// 유저가 리뷰를 쓰지 않은 구매확정 리스트를 가져오는 메서드
	@Override
	public List<OrderDetailVO> userAbleReviewList(String memberId) throws Exception {
		System.out.println("DAO : 유저 리뷰를 쓰지 않은 구매확정 리스트 불러오기 ");
		return ses.selectList(ns+".userAbleReviewList", memberId);
	}


	// 상품코드에 맞는 상품명을 반환해준다.
	@Override
	public String convertProdNoToProdName(String prodCode) throws Exception {
		System.out.println("DAO : 상품코드에 맞는 상품명을 반환 ");
		return ses.selectOne(ns+".convertProdNoToProdName", prodCode);
	}

}
