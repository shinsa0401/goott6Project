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
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.DeliveryVo;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.ExchangeVo;
import com.boritgogae.domain.TotalOrderListVo;
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

	// 자동로그인 체크한 회원 세션의 정보를 업데이트
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

	// 기존 로그인시 로그인시간 업데이트
	@Override
	public int updateLogInDate(String memberId) throws Exception {
		System.out.println("DAO : 로그인 시간 업데이트");
		return ses.update(ns + ".updateLogInDate", memberId);
	}

	// 기존 회원 로그아웃시 로그아웃시간 업데이트
	@Override
	public int updateLogOutDate(String memberId) throws Exception {
		System.out.println("DAO : 로그아웃시간 업데이트");
		return ses.update(ns + ".updateLogOutDate", memberId);
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
		System.out.println("memberId : " + memberId + ", target : " + target + ", value : " + value);
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

	// 회원정보의 탈퇴여부를 Y로 바꾼다.
	@Override
	public int membershipWithdrawalUpdate(String memberId) throws Exception {
		System.out.println("DAO : 회원정보의 탈퇴여부를 Y로 바꾼다 ");
		return ses.update(ns + ".membershipWithdrawalUpdate", memberId);
	}

	// 회원탈퇴 테이블에 정보를 기입한다.
	@Override
	public int membershipWithdrawalInsert(String memberId, String code, String contents) throws Exception {
		System.out.println("DAO : 회원탈퇴 테이블에 정보를 기입");
		System.out.println("memberId : " + memberId + ", code : " + code + ", contents : " + contents);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("code", code);
		map.put("contents", contents);

		return ses.insert(ns + ".membershipWithdrawalInsert", map);
	}

	// 회원의 주문내역을 가져오는 메서드.
	@Override
	public List<Integer> getMemberOrders(String memberId) throws Exception {
		System.out.println("DAO : 주문내역을 가져옴 ");
		return ses.selectList(ns + ".getMemberOrders", memberId);
	}

	// 주문번호에 따른 주문테이블의 내용을 가져오는 메서드
	@Override
	public OrdersVo getOrdersContents(Integer orderNo) throws Exception {
		System.out.println("DAO : 주문번호에 따른 주문테이블의 내용을 가져오기, 주문번호 : " + orderNo);
		return ses.selectOne(ns + ".getOrdersContents", orderNo);
	}

	// 주문번호에 따른 상세주문+배송을 가져오는 메서드
	@Override
	public List<TotalOrderListVo> getTotalOrderListVo(Integer orderNo) throws Exception {
		System.out.println("DAO : 주문번호에 따른 상세주문+배송을 가져오기");
		return ses.selectList(ns + ".getTotalOrderListVo", orderNo);
	}

	// 주문취소시 주문상세내역 업데이트
	@Override
	public int orderCancleDetailOrder(String orderCancleOrderNo, String status) throws Exception {
		System.out.println("DAO : 주문취소시 주문상세내역 업데이트");
		Map<String, String> map = new HashMap<>();
		map.put("orderCancleOrderNo", orderCancleOrderNo);
		map.put("status", status);

		return ses.update(ns + ".orderCancleDetailOrder", map);
	}

	// 주문취소시 배송 업데이트
	@Override
	public int orderCancleDelivery(String orderCancleOrderNo, String status) throws Exception {
		System.out.println("DAO : 주문취소시 배송 업데이트");
		Map<String, String> map = new HashMap<>();
		map.put("orderCancleOrderNo", orderCancleOrderNo);
		map.put("status", status);

		return ses.update(ns + ".orderCancleDelivery", map);
	}

	// 주문취소시 쿠폰 업데이트
	@Override
	public int orderCancleCoupon(String orderCancleOrderNo) throws Exception {
		System.out.println("DAO : 주문취소시 쿠폰 업데이트");
		return ses.update(ns + ".orderCancleCoupon", orderCancleOrderNo);
	}

	// 주문취소시 사용했던 포인트 가져오기
	@Override
	public int orderCancleUsedPoint(String orderCancleOrderNo) throws Exception {
		System.out.println("DAO : 주문취소시 사용했던 포인트 가져오기 : " + orderCancleOrderNo);
		return ses.selectOne(ns + ".orderCancleUsedPoint", orderCancleOrderNo);
	}

	// 주문취소시 사용했던 포인트 재적립
	@Override
	public int orderCancleUsedPointReset(String memberId, String orderNo, int usedPoint) throws Exception {
		System.out.println("DAO : 주문취소시 사용했던 포인트 재적립 : " + usedPoint);
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("orderNo", orderNo);
		map.put("usedPoint", usedPoint + "");

		return ses.insert(ns + ".orderCancleUsedPointReset", map);
	}

	// 주문취소시 적립되었던 포인트 가져오기
	@Override
	public int orderCancleSavedPoint(String orderCancleOrderNo) throws Exception {
		System.out.println("DAO : 주문취소시 적립되었던 포인트 가져오기 : " + orderCancleOrderNo);
		return ses.selectOne(ns + ".orderCancleSavedPoint", orderCancleOrderNo);
	}

	// 주문취소시 적립되었던 포인트 차감
	@Override
	public int orderCancleSavedPointReset(String memberId, String orderNo, int savedPoint) throws Exception {
		System.out.println("DAO : 주문취소시 적립되었던 포인트 차감 : " + savedPoint);
		if (savedPoint == 0) {
			return 1;
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("orderNo", orderNo);
			map.put("usedPoint", savedPoint + "");

			return ses.insert(ns + ".orderCancleSavedPointReset", map);
		}
	}

	// 주문취소시 재적립된 포인트 업데이트
	@Override
	public int orderCanclePointUpdate(String memberId, int pointUpdate) throws Exception {
		System.out.println("DAO : 주문취소시 재적립된 포인트 업데이트 : " + pointUpdate);
		if (pointUpdate == 0) {
			return 1;
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("pointUpdate", pointUpdate + "");

			return ses.insert(ns + ".orderCanclePointUpdate", map);
		}
	}

	// 구매확정시 주문상세내역 업데이트
	@Override
	public int orderPurchaseConfirmDetailOrder(String orderNo) throws Exception {
		System.out.println("DAO : 구매확정시 주문상세내역 업데이트");
		return ses.update(ns + ".orderPurchaseConfirmDetailOrder", orderNo);
	}

	// 구매확정시 배송 업데이트
	@Override
	public int orderPurchaseConfirmDelivery(String orderNo) throws Exception {
		System.out.println("DAO : 구매확정시 배송 업데이트");
		return ses.update(ns + ".orderPurchaseConfirmDelivery", orderNo);
	}

	// 특정 주문번호에 사용한 쿠폰 내역을 가져온다.
	@Override
	public CouponUsedVo getUsedCouponByOrderNo(Integer orderNo) throws Exception {
		System.out.println("DAO : 특정 주문번호에 사용한 쿠폰 내역을 가져온다." + orderNo);
		return ses.selectOne(ns + ".getUsedCouponByOrderNo", orderNo);
	}

	// 교환반품 테이블 insert
	@Override
	public int insertExchangeTable(ExchangeVo exchangeVo) throws Exception {
		System.out.println("DAO : 교환반품 테이블 insert");
		Map<String, String> map = new HashMap<>();
		map.put("orderDetailNo", exchangeVo.getOrderDetailNo() + "");
		if (exchangeVo.getExchangeType().equals("E")) {
			map.put("exchangeType", "교환");
		} else if (exchangeVo.getExchangeType().equals("R")) {
			map.put("exchangeType", "반품");
		}
		map.put("reasonNo", exchangeVo.getReasonNo() + "");
		if (exchangeVo.getReasonContent() == "") {
			exchangeVo.setReasonContent("없음");
		}
		map.put("reasonContent", exchangeVo.getReasonContent());
		System.out.println(map);
		return ses.insert(ns + ".insertExchangeTable", map);
	}

	// 주문상세 - 반품/교환 update
	@Override
	public int exchangeStatusUpdateDetailOrder(int orderDetailNo, String exchangeType) throws Exception {
		System.out.println("DAO : 주문상세 - 반품/교환 update");
		Map<String, String> map = new HashMap<>();
		map.put("orderDetailNo", orderDetailNo + "");
		map.put("exchangeType", exchangeType);

		return ses.update(ns + ".exchangeStatusUpdateDetailOrder", map);
	}

	// 배송 - 배송상태(반품요청) update
	@Override
	public int exchangeStatusUpdateDelivery(int orderDetailNo, String exchangeType) throws Exception {
		System.out.println("DAO : 배송상태(반품요청) update");

		Map<String, String> map = new HashMap<>();
		map.put("orderDetailNo", orderDetailNo + "");
		if (exchangeType.equals("E")) {
			map.put("exchangeType", "교환요청");
		} else if (exchangeType.equals("R")) {
			map.put("exchangeType", "반품요청");
		} else {
			map.put("exchangeType", exchangeType);
		}
		return ses.update(ns + ".exchangeStatusUpdateDelivery", map);
	}

	// 주문 중 교환요청과 반품요청 상태인 상세주문번호를 찾는다.
	@Override
	public List<Integer> findRtnOrExForOrder(String orderNo) throws Exception {
		System.out.println("DAO : 주문 중 교환요청과 반품요청 상태인 상세주문번호를 찾는다");
		return ses.selectList(ns + ".findRtnOrExForOrder", orderNo);
	}

	// 주문 중 배송완료 상태인 상세주문번호를 찾는다
	@Override
	public List<Integer> getOrderCompletedList(String orderNo) {
		System.out.println("DAO : 주문 중 배송완료 상태인 상세주문번호를 찾는다.");
		return ses.selectList(ns + ".getOrderCompletedList", orderNo);
	}
	
	// 배송테이블에서 현재 배송상태를 가져온다.
	@Override
	public String deliveryStatusCheck(Integer detailOrderNo) throws Exception {
		System.out.println("DAO : 배송테이블에서 현재 배송상태를 가져온다.");
		return ses.selectOne(ns + ".deliveryStatusCheck", detailOrderNo);
	}

	// 주문상세 정보 가져오기
	@Override
	public DetailOrderVo getDetailOrderInfo(Integer detailOrderNo) throws Exception {
		System.out.println("DAO : 주문상세 정보 가져오기");
		return ses.selectOne(ns + ".getDetailOrderInfo", detailOrderNo);
	}

	// 주문상세테이블의 마지막 번호를 받아오기
	@Override
	public int getDetailOrdersLastNo() throws Exception {
		System.out.println("DAO : 주문상세테이블의 마지막 번호를 받아오기");
		return ses.selectOne(ns + ".getDetailOrdersLastNo");
	}

	// 주문상세테이블 컬럼을 새로 넣어준다.
	@Override
	public int insertDetailOrder(DetailOrderVo detailOrder) throws Exception {
		System.out.println("DAO : 주문상세테이블 컬럼을 새로 넣어준다.");
		return ses.insert(ns + ".insertDetailOrder", detailOrder);
	}

	// 배송지 정보를 가져온다
	@Override
	public DeliveryVo getDeliveryInfo(Integer detailOrderNo) throws Exception {
		System.out.println("DAO : 배송지 정보를 가져온다");
		return ses.selectOne(ns + ".getDeliveryInfo", detailOrderNo);
	}

	// 배송테이블 컬럼을 새로 넣어준다.
	@Override
	public int insertDelivery(DeliveryVo delivery) throws Exception {
		System.out.println("DAO : 배송테이블 컬럼을 새로 넣어준다.");
		return ses.insert(ns + ".insertDelivery", delivery);
	}

	// 주문상세 returnOrExchangeConfirm 정보를 변경
	@Override
	public int changeReturnOrExchangeConfirm(Integer detailOrderNo, String status) throws Exception {
		System.out.println("DAO : 주문상세 returnOrExchangeConfirm 정보를 변경");

		Map<String, String> map = new HashMap<>();
		map.put("orderDetailNo", detailOrderNo + "");
		map.put("status", status);
		return ses.update(ns + ".changeReturnOrExchangeConfirm", map);
	}
	
	// 쿠폰 이름에 맞는 할인율을 가져오는 메서드
	@Override
	public float getCouponDiscount(String couponName) throws Exception {
		System.out.println("DAO : 쿠폰 이름에 맞는 할인율을 가져오는 메서드");
		return ses.selectOne(ns + ".getCouponDiscount", couponName);
	}

	// 주문테이블 업데이트 
	@Override
	public int updateOrdersTable(OrdersVo newOrder, String orderNo) throws Exception {
		System.out.println("DAO : 주문테이블 업데이트");

		Map<String, Object> map = new HashMap<>();
		map.put("prodTotalPrice", newOrder.getProdTotalPrice());
		map.put("totalPrice", newOrder.getTotalPrice());
		map.put("orderNo", orderNo);
		return ses.update(ns + ".updateOrdersTable", map);
	}


}
