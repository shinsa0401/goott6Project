package com.boritgogae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.DeliveryVo;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.ExchangeVo;
import com.boritgogae.domain.TotalOrderListVo;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.GradesVo;
import com.boritgogae.domain.PointHistoryVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.UserBoardVo;
import com.boritgogae.domain.UserReplyVo;
import com.boritgogae.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject 
	private MemberDAO dao;

	@Inject
	private MemberDAO memDao;


	// 로그인 처리하는 메서드
	@Override
	public MemberVo logIn(LogInDTO dto) throws Exception {
		
		MemberVo logInMember = dao.logIn(dto);
		
		if (logInMember != null) {
			dao.updateLogInDate(logInMember.getMemberId());
			System.out.println("로그인 성공");
			
		} else {
			
			System.out.println("일치하는 정보가 없다");
		}
		
		return logInMember;
	}

	// 자동로그인을 체크했을 경우 로그인 유지를 위한 세션정보 업데이트하는 메서드
	@Override
	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit) throws Exception {
		
		return dao.updateMemberSession(memberId, sessionId, sessionLimit);
	}
	
	// 자동로그인 회원 체크하는 메서드
	@Override
	public MemberVo checkAutoLogIn(String sessionId) throws Exception {
		
		return dao.selectAutoLogIn(sessionId);
	}
	
	// 기존 회원 로그아웃 시간 업데이트하는 메서드
	@Override
	public int updateLogOutDate(String memberId) throws Exception {
		
		return dao.updateLogOutDate(memberId);
	}
	
	// 이메일로 회원 아이디 검색하는 메서드
	@Override
	public MemberVo selectMemberId(String memberEmail) throws Exception {
		System.out.println("서비스 : 이메일로 회원 아이디 검색");
		return dao.selectMemberId(memberEmail);
		
	}
	
	// 비밀번호 재설정 전 회원 아이디 확인하는 메서드
	@Override
	public int checkMemberId(String memberId) throws Exception {
		
		return dao.checkMemberId(memberId);
	}
	
	// 회원 비밀번호 업데이트하는 메서드
	@Override
	public int updatePwd(String memberId, String memberPwd) throws Exception {
		int result = 0;
		
		// 비밀번호 업데이트하고나서 lastPwdUpdate 컬럼 현재시간으로 업데이트
		if (dao.updatePwd(memberId, memberPwd) == 1) {
			result = dao.updateLastPwdUpdate(memberId);
		}
		
		return result;
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
	
	// 회원정보의 탈퇴여부를 Y로 바꾼다.
	@Override
	public int membershipWithdrawalUpdate(String memberId) throws Exception {
		System.out.println("서비스단 : 회원 탈퇴여부 변경");
		return dao.membershipWithdrawalUpdate(memberId);
	}
	
	// 회원탈퇴 테이블에 정보를 기입한다.
	@Override
	public int membershipWithdrawalInsert(String memberId, String code, String contents) throws Exception {
		System.out.println("서비스단 : 회원탈퇴 테이블 인서트");
		return dao.membershipWithdrawalInsert(memberId, code, contents);
	}	

	// 회원의 주문내역을 가져오는 메서드.
	@Override
	public List<Integer> getMemberOrders(String memberId) throws Exception {
		System.out.println("서비스단 : 회원의 주문내역을 가져오기");
		return dao.getMemberOrders(memberId);
	}

	// 주문번호에 따른 주문테이블의 내용을 가져오는 메서드
	@Override
	public OrdersVo getOrdersContents(Integer orderNo) throws Exception {
		System.out.println("서비스단 : 회원 주문번호에 따른 주문테이블의 내용을 가져오기");
		return dao.getOrdersContents(orderNo);
	}

	// 주문번호에 따른 상세주문+배송을 가져오는 메서드
	@Override
	public List<TotalOrderListVo> getTotalOrderListVo(Integer orderNo) throws Exception {
		System.out.println("서비스단 : 주문번호에 따른 상세주문+배송을 가져오기");
		return dao.getTotalOrderListVo(orderNo);
	}


	// 주문취소시 주문상세내역 업데이트
	@Override
	public int orderCancleDetailOrder(String orderCancleOrderNo, String status) throws Exception {
		System.out.println("서비스단 : 주문취소시 주문상세내역 업데이트");
		return dao.orderCancleDetailOrder(orderCancleOrderNo, status);
	}

	// 주문취소시 배송 업데이트
	@Override
	public int orderCancleDelivery(String orderCancleOrderNo, String status) throws Exception {
		System.out.println("서비스단 : 주문취소시 배송 업데이트");
		return dao.orderCancleDelivery(orderCancleOrderNo, status);
	}

	// 주문취소시 쿠폰 업데이트
	@Override
	public int orderCancleCoupon(String orderCancleOrderNo) throws Exception {
		System.out.println("서비스단 : 주문취소시 쿠폰 업데이트");
		return dao.orderCancleCoupon(orderCancleOrderNo);
	}

	
	
	
	
	// 주문취소시 사용했던 포인트 가져오기
	@Override
	public int orderCancleUsedPoint(String orderCancleOrderNo) throws Exception {
		System.out.println("서비스단 : 주문취소시 사용했던 포인트 가져오기");
		return dao.orderCancleUsedPoint(orderCancleOrderNo);
	}

	// 주문취소시 사용했던 포인트 재적립
	@Override
	public int orderCancleUsedPointReset(String memberId, String orderNo, int usedPoint) throws Exception {
		System.out.println("서비스단 : 주문취소시 쿠폰 업데이트");
		return dao.orderCancleUsedPointReset(memberId, orderNo, usedPoint);
	}

	// 주문취소시 적립되었던 포인트 가져오기
	@Override
	public int orderCancleSavedPoint(String orderCancleOrderNo) throws Exception {
		System.out.println("서비스단 : 주문취소시 쿠폰 업데이트");
		return dao.orderCancleSavedPoint(orderCancleOrderNo);
	}

	// 주문취소시 적립되었던 포인트 차감
	@Override
	public int orderCancleSavedPointReset(String memberId, String orderNo, int savedPoint) throws Exception {
		System.out.println("서비스단 : 주문취소시 쿠폰 업데이트");
		return dao.orderCancleSavedPointReset(memberId, orderNo, savedPoint);
	}

	// 주문취소시 재적립된 포인트 업데이트
	@Override
	public int orderCanclePointUpdate(String memberId, int pointUpdate) throws Exception {
		System.out.println("서비스단 : 주문취소시 쿠폰 업데이트");
		return dao.orderCanclePointUpdate(memberId, pointUpdate);
	}


	// 구매확정시 주문상세내역 업데이트
	@Override
	public int orderPurchaseConfirmDetailOrder(String orderNo) throws Exception {
		System.out.println("서비스단 : 구매확정시 주문상세내역 업데이트");
		return dao.orderPurchaseConfirmDetailOrder(orderNo);
	}

	// 구매확정시 배송 업데이트
	@Override
	public int orderPurchaseConfirmDelivery(String orderNo) throws Exception {
		System.out.println("서비스단 : 구매확정시 배송 업데이트");
		return dao.orderPurchaseConfirmDelivery(orderNo);
	}
	
	// 특정 주문번호에 사용한 쿠폰 내역을 가져온다.
	@Override
	public CouponUsedVo getUsedCouponByOrderNo(Integer orderNo) throws Exception {
		System.out.println("서비스단 : 특정 주문번호에 사용한 쿠폰 내역을 가져온다");
		return dao.getUsedCouponByOrderNo(orderNo);
	}

	//교환반품 테이블 insert
	@Override
	public int insertExchangeTable(ExchangeVo exchangeVo) throws Exception {
		System.out.println("서비스단 : 교환반품 테이블 insert");
		return dao.insertExchangeTable(exchangeVo);
	}

	// 주문상세 - 반품/교환 update
	@Override
	public int exchangeStatusUpdateDetailOrder(int orderDetailNo, String exchangeType) throws Exception {
		System.out.println("서비스단 : 반품/교환 update");
		return dao.exchangeStatusUpdateDetailOrder(orderDetailNo, exchangeType);
	}

	// 배송 - 배송상태(반품요청) update
	@Override
	public int exchangeStatusUpdateDelivery(int orderDetailNo, String exchangeType) throws Exception {
		System.out.println("서비스단 : 배송상태(반품요청) update");
		return dao.exchangeStatusUpdateDelivery(orderDetailNo, exchangeType);
	}

	// 주문 중 교환요청과 반품요청 상태인 상세주문번호를 찾는다.
	@Override
	public List<Integer> findRtnOrExForOrder(String orderNo) throws Exception {
		System.out.println("서비스단 : 교환요청과 반품요청 상태인 상세주문번호를 찾기");
		return dao.findRtnOrExForOrder(orderNo);
	}

	// 주문 중 배송완료 상태인 주문의 상품합을 구한다.
	@Override
	public int orderCompletedAmount(String orderNo) throws Exception {
		System.out.println("서비스단 : 주문 중 배송완료 상태인 주문의 상품합을 구한다.");
		// 1. 딜리버리 테이블에서 배송완료 상태인 디테일오더주문번호 리스트를 찾는다.
			List<Integer> orderCompletedList = dao.getOrderCompletedList(orderNo);
		// 2. 리스트를 통해 총액을 구하고 리턴해준다.
			int orderCompletedAmount = 0;
			for(Integer detailOrderNo : orderCompletedList) {
				orderCompletedAmount += dao.getDetailOrderInfo(detailOrderNo).getProdSubTotalPrice();
			}
			return orderCompletedAmount;
	}

	
	
	
	// 배송테이블에서 현재 배송상태를 가져온다.
	@Override
	public String deliveryStatusCheck(Integer detailOrderNo) throws Exception {
		System.out.println("서비스단 : 배송테이블에서 현재 배송상태를 가져온다.");
		return dao.deliveryStatusCheck(detailOrderNo);
	}

	

	// 주문상세 정보 가져오기	
	@Override
	public DetailOrderVo getDetailOrderInfo(Integer detailOrderNo) throws Exception {
		System.out.println("서비스단 : 주문상세 정보 가져오기");
		return dao.getDetailOrderInfo(detailOrderNo);
	}

	// 주문상세테이블의 마지막 번호를 받아오기
	@Override
	public int getDetailOrdersLastNo() throws Exception {
		System.out.println("서비스단 : 주문상세테이블의 마지막 번호를 받아오기");
		return dao.getDetailOrdersLastNo();
	}

	// 주문상세테이블 컬럼을 새로 넣어준다.
	@Override
	public int insertDetailOrder(DetailOrderVo detailOrder) throws Exception {
		System.out.println("서비스단 : 배송테이블에서 현재 배송상태를 가져온다.");
		return dao.insertDetailOrder(detailOrder);
		
	}

	// 배송지 정보를 가져온다
	@Override
	public DeliveryVo getDeliveryInfo(Integer detailOrderNo) throws Exception {
		System.out.println("서비스단 : 배송지 정보를 가져온다");
		return dao.getDeliveryInfo(detailOrderNo);
	}

	// 수정된 정보가 포함된 배송테이블 컬럼을 새로 넣어준다.
	@Override
	public int insertDelivery(DeliveryVo delivery) throws Exception {
		System.out.println("서비스단 : 수정된 정보가 포함된 배송테이블 컬럼을 새로 넣어준다.");
		return dao.insertDelivery(delivery);
		
	}

	// 주문상세 returnOrExchangeConfirm 정보를 변경
	@Override
	public int changeReturnOrExchangeConfirm(Integer detailOrderNo, String status) throws Exception {
		System.out.println("서비스단 : 주문상세 returnOrExchangeConfirm 정보를 변경");
		return dao.changeReturnOrExchangeConfirm(detailOrderNo, status);
	}

	// 쿠폰 이름에 맞는 할인율을 가져오는 메서드
	@Override
	public float getCouponDiscount(String couponName) throws Exception {
		System.out.println("서비스단 : 쿠폰 이름에 맞는 할인율을 가져오는 메서드");
		return dao.getCouponDiscount(couponName);
	}
	
	
	// 주문테이블 업데이트 
	@Override
	public int updateOrdersTable(OrdersVo newOrder, String orderNo) throws Exception {
		System.out.println("서비스단 : 주문테이블 업데이트");
		return dao.updateOrdersTable(newOrder, orderNo);
	}





	@Override
	public MemberVo getMemberInfo(String memberId) {
		
		return memDao.getMemInfo(memberId);
	}

	@Override
	public List<DeliveryInfoVo> getMemAddrs(String memberId) {
		
		return memDao.getMemAddrs(memberId);
	}

	@Override
	public GradesVo getGrade(String memberId) {
		
		return memDao.getGrade(memberId);
	}


	@Override
	public int memberjoin(MemberVo vo,HttpServletResponse response,DeliveryInfoVo dv) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		dv.setMemberId(vo.getMemberId());
		dv.setRecipient(vo.getMemberName());
		dv.setRecipientPhoneNumber(vo.getPhoneNumber());
		
		
		
		if (dao.checkid(vo.getMemberId()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 아이디가 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else {
			dao.memberjoin(vo);
			dao.joindelivery(dv);
			return 1;
		}
	
		
	}

	@Override
	public void checkid(String memberId, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkid(memberId));
		out.close();
		
	}

	@Override
	public void checkname(String memberName,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkname(memberName));
		out.close();
		
	}

	@Override
	public void checkemail(String memberEmail,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkemail(memberEmail));
		out.close();
		
	}

	@Override
	public List<ProductVo> selectLike(String memberId) throws Exception {
	 
		
		return dao.selectLike(memberId);
	}



	@Override
	public int likeProduct(String prodNo) throws Exception {
		
		return dao.likeProduct(prodNo);
	}

	@Override
    public int getSearchResultCnt(FreeSearchCondition sc) throws Exception {
        return dao.searchResultCnt(sc);
    }

    @Override
    public List<DM> getSearchResultPage(FreeSearchCondition sc) throws Exception {
        return dao.searchSelectPage(sc);
    }
	
    @Override
    public int sendDel(String no)throws Exception{
    	
    	
    	return dao.sendDel(no);
    }



	@Override
	public Map<String, Object> detaildm(int no) throws Exception {
		
		DM dm = dao.detaildm(no);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dm", dm);

		return map;
	}



	@Override
	public int insertWriter(DM dm) throws Exception {
		
		return dao.insertWriter(dm);
	}

	



}
