package com.boritgogae.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradeVo;
import com.boritgogae.domain.MemberVo;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

@Service
public interface MemberService {
	
	/**
	 * @methodName : getMemberInfo
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : memberId
	 * @returnType : MemberVo
	 **/
	public MemberVo getMemberInfo(String memberId);
	
	/**
	 * @methodName : getMemAddrs
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : memberId
	 * @returnType : List<DeliveryInfoVo>
	 **/
	public List<DeliveryInfoVo> getMemAddrs(String memberId);
	
	/**
	 * @methodName : getGrade
	 * @author : kjy
	 * @date : 2022. 10. 23.
	 * @입력 param : memberId
	 * @returnType : GradeVo
	 **/
	public GradeVo getGrade(String memberId);



	// 로그인을 처리하는 메서드
	public MemberVo logIn(LogInDTO dto, HttpServletRequest request) throws Exception;

	// 자동로그인을 체크했을 경우 로그인 유지를 위한 세션정보 업데이트하는 메서드
	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit) throws Exception;

	// 회원 로그아웃시 로그아웃시간 업데이트하는 메서드
	public int updateLogOutDate(String memberId) throws Exception;
	
	// 이메일로 회원 아이디 검색하는 메서드
	public MemberVo selectMemberId(String memberEmail) throws Exception;
	
	// 비밀번호 재설정 전 회원 아이디 확인하는 메서드
	public int checkMemberId(String memberId) throws Exception;
	
	// 회원 비밀번호 업데이트하는 메서드
	public int updatePwd(String memberId, String memberPwd) throws Exception;

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
	public int addAddr(String memberId, String address, String detailAddress, String postCode, String recipient, String recipientPhoneNumber) throws Exception;

	// 회원이미지를 추가하는 메서드
	public int addMemberImg(String memberId, String memberImg) throws Exception;

	// 회원 이메일 변경 메서드
	public int changeMemberEmail(String memberId, String memberEmail) throws Exception;



	public int likeProduct(String prodNo) throws Exception;
	
	public List<ProductVo> selectLike(String memberId) throws Exception;
	
	public int memberjoin(MemberVo vo,HttpServletResponse response,DeliveryInfoVo dv) throws Exception;
	
	public void checkid(String memberId,HttpServletResponse response) throws Exception;
	
	public void checkname(String memberName,HttpServletResponse response)throws Exception;
	
	public void checkemail(String memberEmail,HttpServletResponse response)throws Exception;

	int getSearchResultCnt(FreeSearchCondition sc) throws Exception;

	List<DM> getSearchResultPage(FreeSearchCondition sc) throws Exception;

	int sendDel(String no) throws Exception;
	
	public Map<String, Object> detaildm(int no)throws Exception;
	
	public int insertWriter(DM dm)throws Exception;
}
