package com.boritgogae.persistence;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private static String ns = "com.boritgogae.memberMapper";

	@Inject
	private SqlSession ses;
	
	// 로그인하기위해 회원정보를 얻어오는 메서드
	@Override
	public MemberVo logIn(LogInDTO dto) throws Exception {
		System.out.println("DAO : 로그인하려는 회원 정보 검색");
		return ses.selectOne(ns + ".logIn", dto);
	}

	// 자동로그인 체크한 회원 세션의 정보를 업데이트하는 메서드
	@Override
	public int updateMemberSession(String memberId, String sessionId, Timestamp sessionLimit) throws Exception {
		System.out.println("DAO : 자동로그인 체크 회원 세션 업데이트");
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("sessionId", sessionId);
		map.put("sessionLimit", sessionLimit);
		
		return ses.update(ns + ".updateMemberSession", map);
	}
	
	// 자동로그인 체크한 회원인지 검색
	@Override
	public MemberVo selectAutoLogIn(String sessionId) throws Exception {
		System.out.println("DAO : 자동로그인 체크한 회원 검색");
		return ses.selectOne(ns + ".selectAutoLogIn", sessionId);
	}
	
	// 기존 로그인시 로그인시간 업데이트하는 메서드
	@Override
	public int updateLogInDate(String memberId) throws Exception {
		System.out.println("DAO : 로그인 시간 업데이트");
		return ses.update(ns + ".updateLogInDate", memberId);
	}
	
	// 기존 회원 로그아웃시 로그아웃시간 업데이트하는 메서드
	@Override
	public int updateLogOutDate(String memberId) throws Exception {
		System.out.println("DAO : 로그아웃시간 업데이트");
		return ses.update(ns + ".updateLogOutDate", memberId);
	}
	
	// 이메일로 회원 아이디 검색하는 메서드
	@Override
	public MemberVo selectMemberId(String memberEmail) throws Exception {
		System.out.println("DAO : 이메일로 회원 아이디 검색");
		return ses.selectOne(ns + ".selectMemberId", memberEmail);
	}
	
	// 회원 아이디가 맞는지 체크하는 메서드
	@Override
	public int checkMemberId(String memberId) throws Exception {
		System.out.println("DAO : 회원의 아이디가 맞는지 체크");
		return ses.selectOne(ns + ".checkMemberId", memberId);
	}
	
	// 회원 비밀번호 업데이트하는 메서드
	@Override
	public int updatePwd(String memberId, String memberPwd) throws Exception {
		System.out.println("DAO : 회원 비밀번호 재설정");
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberPwd", memberPwd);
		return ses.update(ns + ".updatePwd", map);
	}

	// 등급혜택을 가져오는 메서드
	@Override
	public List<GradesVo> showGradeBenefit() throws Exception {
		System.out.println("DAO : 등급혜택불러오기 ");
		return ses.selectList(ns + ".showGradeBenefit");
	}

	// 쿠폰혜택을 가져오는 메서드
	@Override
	public List<CouponVo> showCouponBenefit() throws Exception {
		System.out.println("DAO : 쿠폰혜택불러오기 ");
		return ses.selectList(ns + ".showCouponBenefit");
	}

	// 유저가 보유한 포인트를 가져오는 메서드
	@Override
	public int pointNow(String memberId) throws Exception {
		System.out.println("DAO : 포인트 불러오기 ");
		return ses.selectOne(ns + ".pointNow", memberId);
	}

	// 유저의 포인트 내역을 가져오는 메서드
	@Override
	public List<PointHistoryVo> showPointHistory(String memberId) throws Exception {
		System.out.println("DAO : 포인트 내역 불러오기 ");
		return ses.selectList(ns + ".showPointHistory", memberId);
	}

	// 유저의 쿠폰 보유 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponHaveList(String memberId) throws Exception {
		System.out.println("DAO : 쿠폰 보유 내역 불러오기 ");
		return ses.selectList(ns + ".showCouponHaveList", memberId);
	}

	// 유저의 쿠폰 사용 내역을 가져오는 메서드
	@Override
	public List<CouponUsedVo> showCouponUsedList(String memberId) throws Exception {
		System.out.println("DAO : 쿠폰 사용 내역 불러오기 ");
		return ses.selectList(ns + ".showCouponUsedList", memberId);
	}

	// 유저의 작성글을 가져오는 메서드
	@Override
	public List<UserBoardVo> showUserBoardList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성글 내역 불러오기 ");
		return ses.selectList(ns + ".showUserBoardList", memberId);
	}

	// 유저의 작성 댓글을 가져오는 메서드
	@Override
	public List<UserReplyVo> showUserReplyList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성 댓글 내역 불러오기 ");
		return ses.selectList(ns + ".showUserReplyList", memberId);
	}

	// 유저가 쓴 리뷰 리스트를 가져오는 메서드
	@Override
	public List<ReviewVO> showUserReviewList(String memberId) throws Exception {
		System.out.println("DAO : 유저 작성 리뷰 리스트 불러오기 ");
		return ses.selectList(ns + ".showUserReviewList", memberId);
	}

	// 유저가 리뷰를 쓰지 않은 구매확정 리스트를 가져오는 메서드
	@Override
	public List<OrderDetailVo> userAbleReviewList(String memberId) throws Exception {
		System.out.println("DAO : 유저 리뷰를 쓰지 않은 구매확정 리스트 불러오기 ");
		return ses.selectList(ns + ".userAbleReviewList", memberId);
	}

	// 상품코드에 맞는 상품명을 반환해준다.
	@Override
	public String convertProdNoToProdName(String prodCode) throws Exception {
		System.out.println("DAO : 상품코드에 맞는 상품명을 반환 ");
		return ses.selectOne(ns + ".convertProdNoToProdName", prodCode);
	}

	// 회원정보를 가져오는 메서드
	@Override
	public MemberVo showUserInfo(String memberId) throws Exception {
		System.out.println("DAO : 회원정보를 가져옴 ");
		return ses.selectOne(ns + ".showUserInfo", memberId);
	}

	// 회원 아이디에 맞는 비번인지 확인하는 메서드
	@Override
	public int checkPwd(String memberId, String pwd) throws Exception {
		System.out.println("DAO : 회원정보와 비번이 맞는지 확인 ");
		System.out.println("pwd : " + pwd);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("pwd", pwd);

		return ses.selectOne(ns + ".checkPwd", map);
	}

	// 회원이 정보를 변경할 때의 메서드
	@Override
	public int changeInfo(String memberId, String target, String value) throws Exception {
		System.out.println("DAO : 회원정보 변경");
		System.out.println("memberId : " + memberId + "target : " + target + "value : " + value);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("target", target);
		map.put("value", value);

		return ses.update(ns + ".changeInfo", map);
	}

	// 회원의 주소지 정보들을 불러오는 메서드
	@Override
	public List<DeliveryInfoVo> showDeliveryInfo(String memberId) throws Exception {
		return ses.selectList(ns + ".showDeliveryInfo", memberId);
	}
	
	// 회원이 주소지를 삭제할 때의 메서드
	@Override
	public int deleteAddr(String memberId, String deliveryInfo) throws Exception {
		System.out.println("DAO : 주소지 삭제");
		System.out.println("memberId : " + memberId + " deliveryInfo : " + deliveryInfo);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("deliveryInfo", deliveryInfo);

		return ses.delete(ns + ".deleteAddr", map);
	}

	// 회원이 주소지를 추가할 때의 메서드
	@Override
	public int addAddr(String memberId, String address, String detailAddress, String postCode, String recipient,
			String recipientPhoneNumber) {
		System.out.println("DAO : 주소지 추가");
		System.out.println("address : " + address + " detailAddress : " + detailAddress + " postCode : " + postCode
				 + " recipient : " + recipient + " recipientPhoneNumber : " + recipientPhoneNumber);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("address", address);
		map.put("detailAddress", detailAddress);
		map.put("postCode", postCode);
		map.put("recipient", recipient);
		map.put("recipientPhoneNumber", recipientPhoneNumber);

		return ses.insert(ns + ".addAddr", map);
	}


	// 회원이미지를 추가하는 메서드
	@Override
	public int addMemberImg(String memberId, String memberImg) throws Exception {
		System.out.println("DAO : 멤버이미지 변경");
		System.out.println("memberId : " + memberId + " memberImg : " + memberImg);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberImg", memberImg);

		return ses.update(ns + ".addMemberImg", map);
	}

	// 회원 이메일 변경 메서드
	@Override
	public int changeMemberEmail(String memberId, String memberEmail) throws Exception {
		System.out.println("DAO : 멤버 이메일 변경");
		System.out.println("memberId : " + memberId + " memberEmail : " + memberEmail);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberEmail", memberEmail);

		return ses.update(ns + ".changeMemberEmail", map);
	}


	
}
