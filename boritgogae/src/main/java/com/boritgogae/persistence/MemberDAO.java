package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.DeliveryVo;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.ExchangeVo;
import com.boritgogae.domain.TotalOrderListVo;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.PointHistoryVo;

import java.sql.Timestamp;

import java.util.Map;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;

public interface MemberDAO {
	
	//멤버정보 가져오는 메서드
	public MemberVo getMemInfo(String memberId);
	   
	//멤버 주소지 가져오는메서드
	public List<DeliveryInfoVo> getMemAddrs(String memberId);
	   
	//멤버 등급 가져오는 메서드
	public GradesVo getGrade(String memberId);
	
	public int updateMemberPoint(String memberId);




	// 로그인하기 위해 회원정보를 얻어오는 메서드
	public MemberVo logIn(LogInDTO dto) throws Exception;

	// 자동로그인을 체크한 회원의 세션의 정보를 업데이트하는 메서드
	public int updateMemberSession(String memberId, String sessionId, Timestamp sessionLimit) throws Exception;

	// 자동로그인 체크한 회원인지 검색하는 메서드
	public MemberVo selectAutoLogIn(String sessionId) throws Exception;

	// 로그인시 로그인시간 업데이트하는 메서드
	public int updateLogInDate(String memberId) throws Exception;
	
	// 로그아웃시 로그아웃시간 업데이트하는 메서드
	public int updateLogOutDate(String memberId) throws Exception;
	
	// 이메일로 회원 아이디 검색하는 메서드
	public MemberVo selectMemberId(String memberEmail) throws Exception;
	
	// 회원 아이디가 맞는지 체크하는 메서드
	public int checkMemberId(String memberId) throws Exception;
	
	// 회원 비밀번호 업데이트하는 메서드
	public int updatePwd(String memberId, String memberPwd) throws Exception;
	
	// 회원 비밀번호 업데이트 이후 lastPwdUpdate 컬럼 업데이트
	public int updateLastPwdUpdate(String memberId) throws Exception;
	
	
	// 등급혜택을 가져오는 메서드
	public List<GradesVo> showGradeBenefit() throws Exception;

	// 쿠폰혜택을 가져오는 메서드
	public List<CouponVo> showCouponBenefit() throws Exception;

	// 유저가 보유한 포인트를 가져오는 메서드
	public int pointNow(String memberId) throws Exception;

	// 유저의 포인트 내역을 가져오는 메서드
	public List<PointHistoryVo> showPointHistory(String memberId) throws Exception;

	// 유저의 쿠폰 보유 내역을 가져오는 메서드
	public List<CouponUsedVo> showCouponHaveList(String memberId) throws Exception;

	// 유저의 쿠폰 사용 내역을 가져오는 메서드
	public List<CouponUsedVo> showCouponUsedList(String memberId) throws Exception;
	
	// 유저의 작성글을 가져오는 메서드
	public List<UserBoardVo> showUserBoardList(String memberId) throws Exception;

	// 유저의 작성 댓글을 가져오는 메서드
	public List<UserReplyVo> showUserReplyList(String memberId) throws Exception;

	// 유저가 쓴 리뷰 리스트를 가져오는 메서드
	public List<ReviewVO> showUserReviewList(String memberId) throws Exception;

	// 유저가 리뷰를 쓰지 않은 구매확정 리스트를 가져오는 메서드
	public List<OrderDetailVo> userAbleReviewList(String memberId) throws Exception;

	// 상품코드에 맞는 상품명을 반환해준다.
	public String convertProdNoToProdName(String prodCode) throws Exception;
	
	// 회원정보를 가져오는 메서드
	public MemberVo showUserInfo(String memberId) throws Exception;

	// 회원 아이디에 맞는 비번인지 확인하는 메서드
	public int checkPwd(String memberId, String pwd) throws Exception;

	// 회원이 정보를 변경할 때의 메서드
	public int changeInfo(String memberId, String target, String value) throws Exception;

	// 회원의 주소지 정보들을 불러오는 메서드
	public List<DeliveryInfoVo> showDeliveryInfo(String memberId) throws Exception;

	// 회원이 주소지를 삭제할 때의 메서드
	public int deleteAddr(String memberId, String deliveryInfo) throws Exception;

	// 회원이 주소지를 추가할 때의 메서드
	public int addAddr(String memberId, String address, String detailAddress, String postCode, String recipient,
			String recipientPhoneNumber) throws Exception;

	// 회원이미지를 추가하는 메서드
	public int addMemberImg(String memberId, String memberImg) throws Exception;
	
	// 회원 이메일 변경 메서드
	public int changeMemberEmail(String memberId, String memberEmail) throws Exception;

	// 회원정보의 탈퇴여부를 Y로 바꾼다.
	public int membershipWithdrawalUpdate(String memberId) throws Exception;

	// 회원탈퇴 테이블에 정보를 기입한다.
	public int membershipWithdrawalInsert(String memberId, String code, String contents) throws Exception;

	// 회원의 주문내역을 가져오는 메서드.
	public List<Integer> getMemberOrders(String memberId) throws Exception;

	// 주문번호에 따른 주문테이블의 내용을 가져오는 메서드
	public OrdersVo getOrdersContents(Integer orderNo) throws Exception;
	
	// 주문번호에 따른 상세주문+배송을 가져오는 메서드
	public List<TotalOrderListVo> getTotalOrderListVo(Integer orderNo) throws Exception;

	// 주문취소시 주문상세내역 업데이트
	public int orderCancleDetailOrder(String orderCancleOrderNo, String status) throws Exception;
	
	// 주문취소시 배송 업데이트
	public int orderCancleDelivery(String orderCancleOrderNo, String status) throws Exception;

	// 주문취소시 쿠폰 업데이트
	public int orderCancleCoupon(String orderCancleOrderNo) throws Exception;
	
	// 주문취소시 사용했던 포인트 가져오기
	public int orderCancleUsedPoint(String orderCancleOrderNo) throws Exception;

	// 주문취소시 사용했던 포인트 재적립
	public int orderCancleUsedPointReset(String memberId, String orderNo, int usedPoint) throws Exception;

	// 주문취소시 적립되었던 포인트 가져오기
	public int orderCancleSavedPoint(String orderCancleOrderNo) throws Exception;

	// 주문취소시 적립되었던 포인트 차감
	public int orderCancleSavedPointReset(String memberId, String orderNo, int savedPoint) throws Exception;

	// 주문취소시 재적립된 포인트 업데이트
	public int orderCanclePointUpdate(String memberId, int pointUpdate) throws Exception;

	// 구매확정시 주문상세내역 업데이트
	public int orderPurchaseConfirmDetailOrder(String orderNo) throws Exception;

	// 구매확정시 배송 업데이트
	public int orderPurchaseConfirmDelivery(String orderNo) throws Exception;

	// 특정 주문번호에 사용한 쿠폰 내역을 가져온다.
	public CouponUsedVo getUsedCouponByOrderNo(Integer orderNo) throws Exception;

	//교환반품 테이블 insert
	public int insertExchangeTable(ExchangeVo exchangeVo) throws Exception;

	//주문상세 - 반품/교환 update
	public int exchangeStatusUpdateDetailOrder(int orderDetailNo, String exchangeType) throws Exception;

	//배송 - 배송상태(반품요청) update
	public int exchangeStatusUpdateDelivery(int orderDetailNo, String exchangeType) throws Exception;

	// 주문 중 교환요청과 반품요청 상태인 상세주문번호를 찾는다.
	public List<Integer> findRtnOrExForOrder(String orderNo) throws Exception;

	// 배송테이블에서 현재 배송상태를 가져온다.
	public String deliveryStatusCheck(Integer detailOrderNo) throws Exception;

	// 주문상세 정보 가져오기	
	public DetailOrderVo getDetailOrderInfo(Integer detailOrderNo) throws Exception;

	// 주문상세테이블의 마지막 번호를 받아오기
	public int getDetailOrdersLastNo() throws Exception;

	// 주문상세테이블 컬럼을 새로 넣어준다.
	public int insertDetailOrder(DetailOrderVo detailOrder) throws Exception;


	// 배송지 정보를 가져온다
	public DeliveryVo getDeliveryInfo(Integer detailOrderNo) throws Exception;

	// 배송테이블 컬럼을 새로 넣어준다.
	public int insertDelivery(DeliveryVo delivery) throws Exception;

	// 주문상세 returnOrExchangeConfirm 정보를 변경
	public int changeReturnOrExchangeConfirm(Integer detailOrderNo, String status) throws Exception;

	// 쿠폰 이름에 맞는 할인율을 가져오는 메서드
	public float getCouponDiscount(String couponName) throws Exception;

	// 주문테이블 업데이트 
	public int updateOrdersTable(OrdersVo newOrder, String orderNo) throws Exception;

	// 주문 중 배송완료 상태인 상세주문번호를 찾는다
	public List<Integer> getOrderCompletedList(String orderNo);




	public List<ProductVo> selectLike (String memberId)throws Exception;

	public int likeProduct (String prodNo)throws Exception;
	
	
	
	
	
	
	public int memberjoin(MemberVo vo) throws Exception;
	
	public void joindelivery(DeliveryInfoVo dv)throws Exception;
	
	public int checkid(String memberId)throws Exception;
	
	public int checkname(String memberName)throws Exception;


	public int checkemail(String memberEmail)throws Exception;

	public int searchResultCnt(FreeSearchCondition sc) throws Exception;

	public List<DM> searchSelectPage(FreeSearchCondition sc) throws Exception;

	public int sendDel(String no) throws Exception;
	public DM detaildm(int no)throws Exception;
	
	public int insertWriter(DM dm)throws Exception;

	


}
