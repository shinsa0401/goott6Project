package com.boritgogae.service;

import java.util.List;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;
import com.boritgogae.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject 
	private MemberDAO dao;

	// 로그인 처리하는 메서드
	@Override
	public MemberVo logIn(LogInDTO dto, HttpServletRequest request) throws Exception {
		
		MemberVo logInMember = dao.logIn(dto);
		
		if (logInMember != null) {
			dao.updateLogInDate(logInMember.getMemberId());
			System.out.println("로그인 성공");
			
		} else {
			
			System.out.println("일치하는 정보가 없다");
		}
		
		return logInMember;
	}

	// 자동로그인을 체크했을 경우 로그인 유지를 위한 sessionId, sessionLimit 업데이트 
	@Override
	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit) throws Exception {
		
		return dao.updateMemberSession(memberId, sessionId, sessionLimit);
	}
	
	// 자동로그인 회원 체크하는 메서드
	@Override
	public MemberVo checkAutoLogIn(String sessionId) throws Exception {
		
		return dao.selectAutoLogIn(sessionId);
	}
	
	// 기존 회원 로그아웃 시간 업데이트
	@Override
	public int updateLogOutDate(String memberId) throws Exception {
		
		return dao.updateLogOutDate(memberId);
	}
	
	
	
	// 등급혜택을 가져오는 메서드
	@Override
	public List<GradesVo> showGradeBenefit() throws Exception {
		System.out.println("서비스단 : 등급혜택불러오기");
		return dao.showGradeBenefit();
	}
	
	// 쿠폰혜택을 가져오는 메서드
	@Override
	public List<CouponVo> showCouponBenefit() throws Exception {
		System.out.println("서비스단 : 쿠폰혜택불러오기");
		return dao.showCouponBenefit();
	}

	// 유저가 보유한 포인트를 가져오는 메서드
	@Override
	public int pointNow(String memberId) throws Exception {
		System.out.println("서비스단 : 포인트 가져오기");
		return dao.pointNow(memberId);
	}

	// 유저의 포인트 내역을 가져오는 메서드
	@Override
	public List<PointHistoryVo> showPointHistory(String memberId) throws Exception {
		System.out.println("서비스단 : 포인트 내역 가져오기");
		return dao.showPointHistory(memberId);
	}
	
	// 유저의 쿠폰 보유 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponHaveList(String memberId) throws Exception {
		System.out.println("서비스단 : 쿠폰 보유 내역 가져오기");
		return dao.showCouponHaveList(memberId);
	}
	
	// 유저의 쿠폰 사용 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponUsedList(String memberId) throws Exception {
		System.out.println("서비스단 : 쿠폰 사용 내역 가져오기");
		return dao.showCouponUsedList(memberId);
	}

	// 유저의 작성글을 가져오는 메서드
	@Override
	public List<UserBoardVo> showUserBoardList(String memberId) throws Exception {
		System.out.println("서비스단 : 쿠폰 사용 내역 가져오기");
		return dao.showUserBoardList(memberId);
	}
	
	// 유저의 작성 댓글을 가져오는 메서드
	@Override
	public List<UserReplyVo> showUserReplyList(String memberId) throws Exception {
		System.out.println("서비스단 : 쿠폰 사용 내역 가져오기");
		return dao.showUserReplyList(memberId);
	}

	// 유저가 쓴 리뷰 리스트를 가져오는 메서드
	@Override
	public List<ReviewVO> showUserReviewList(String memberId) throws Exception {
		System.out.println("서비스단 : 작성된 리뷰 내역 가져오기");
		return dao.showUserReviewList(memberId);
	}

	// 유저가 리뷰를 쓰지 않은 구매확정 리스트를 가져오는 메서드
	@Override
	public List<OrderDetailVo> userAbleReviewList(String memberId) throws Exception {
		System.out.println("서비스단 : 리뷰를 쓰지 않은 구매확정 내역 가져오기");
		return dao.userAbleReviewList(memberId);
	}
	
	// 상품코드에 맞는 상품명을 반환해준다.
	@Override
	public String convertProdNoToProdName(String prodCode) throws Exception {
		System.out.println("서비스단 : 상품코드에 맞는 상품명 반환");
		return dao.convertProdNoToProdName(prodCode);
	}

	// 회원정보를 가져오는 메서드
	@Override
	public MemberVo showUserInfo(String memberId) throws Exception {
		System.out.println("서비스단 : 상품코드에 맞는 상품명 반환");
		return dao.showUserInfo(memberId);
	}

	// 회원 아이디에 맞는 비번인지 확인하는 메서드
	@Override
	public int checkPwd(String memberId, String pwd) throws Exception {
		System.out.println("서비스단 : 아이디에 맞는 비번인지 확인");
		return dao.checkPwd(memberId, pwd);
	}

	// 회원이 정보를 변경할 때의 메서드
	@Override
	public int changeInfo(String memberId, String target, String value) throws Exception {
		System.out.println("서비스단 : 정보 업데이트");
		return dao.changeInfo(memberId, target, value);
	}
	
	// 회원의 주소지 정보들을 불러오는 메서드
	@Override
	public List<DeliveryInfoVo> showDeliveryInfo(String memberId) throws Exception {
		System.out.println("서비스단 : 회원 주소지 목록");
		return dao.showDeliveryInfo(memberId);
	}
	
	// 회원이 주소지를 삭제할 때의 메서드
	@Override
	public int deleteAddr(String memberId, String deliveryInfo) throws Exception {
		System.out.println("서비스단 : 회원 주소지 삭제");
		return dao.deleteAddr(memberId, deliveryInfo);
	}

	// 회원이 주소지를 추가할 때의 메서드
	@Override
	public int addAddr(String memberId, String address, String detailAddress, String postCode, String recipient,
			String recipientPhoneNumber) throws Exception {
		System.out.println("서비스단 : 회원 주소지 추가");
		return dao.addAddr(memberId, address, detailAddress, postCode, recipient, recipientPhoneNumber);
	}

	// 회원이미지를 추가하는 메서드
	@Override
	public int addMemberImg(String memberId, String memberImg) throws Exception {
		System.out.println("서비스단 : 멤버 이미지 변경");
		return dao.addMemberImg(memberId, memberImg);
	}

	// 회원 이메일 변경 메서드
	@Override
	public int changeMemberEmail(String memberId, String memberEmail) throws Exception {
		System.out.println("서비스단 : 회원 이메일 변경");
		return dao.changeMemberEmail(memberId, memberEmail);
	}

}
