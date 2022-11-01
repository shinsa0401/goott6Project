package com.boritgogae.persistence;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.board.notice.etc.NoticePagingInfo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

public interface AdminDAO {
    
    // ----------------- 회원 관련 메서드 -----------------

	// 회원 전체 목록 가져오는 메서드
	public List<MemberVo> getMembers() throws Exception;
	
	// 최근 30일간 가입한 회원 목록을 가져오는 메서드
	public List<MemberVo> getNewMembers() throws Exception;
	
	// 탈퇴한 회원 목록을 가져오는 메서드
	public List<DeleteAccountVo> getDelMembers() throws Exception;
	
	// 탈퇴 사유를 가져오는 메서드
	public List<DeleteReasonVo> getDeleteReason() throws Exception;
	
	// 입력한 값으로 회원을 검색
	public List<MemberVo> searchMember(String inputString) throws Exception;
	
	// 회원의 상세 목록을 가져오는 메서드
	public MemberVo getMemberProfile(String memberId) throws Exception;
	
	// 회원의 주소를 가져오는 메서드
	public List<DeliveryInfoVo> getMemberAddress(String memberId) throws Exception;
	
	// 회원의 정보를 수정하는 메서드
	public int modifyMemberForAdmin(MemberVo member) throws Exception;

	// 회원을 삭제하는 메서드
	public int deleteMember(String memberId) throws Exception;
	
	// 일일 접속자 수를 구하는 메서드
	public int getLogInMemberCount() throws Exception;
	
	// ----------------- 쿠폰 관련 메서드 -----------------
	
	// 쿠폰 신규 등록 하는 메서드
	public int createCoupon(CouponVo coupon) throws Exception;
	
	// 등록된 모든 쿠폰을 가져오는 메서드
	public List<CouponVo> getCoupon() throws Exception;
	
	// 등록된 쿠폰을 삭제하는 메서드
	public int delCoupon(String couponName) throws Exception;
	
	// 등록된 쿠폰을 수정하는 메서드
	public int modifyCoupon(CouponVo coupon, String modiCouponName) throws Exception;
	
	// 쿠폰을 회원 or 회원 전체 에게 전송하는 메서드
	public int sendCoupon(CouponUsedVo sendCoupon) throws Exception;
	
	// 회원이 보유한 쿠폰을 가져오는 메서드
	public List<CouponUsedVo> getCouponFromMember(String memberId) throws Exception;
	
	// ----------------- 상품 관련 메서드 -----------------
	
	// 재고가 적은 순
	public List<ProductVo> getLowestProduct() throws Exception;

    // 찜수가 많은 순
    public List<ProductVo> getTopLikeCount() throws Exception;
    
    // 조회수 많은 순
    public List<ProductVo> getTopReadCount() throws Exception;
    
	// 새로 들어온 주문 
    public List<OrdersVo> getNewOrder() throws Exception;

    // 상품의 갯수를 가져오는 메서드
    public int getProdCnt() throws Exception;
	
    // 상품 전체 목록을 가져오는 메서드
    public List<ProductVo> getProdList(NoticePagingInfo pi) throws Exception;
    
    // 상품의 이미지를 가져오는 메서드
    public List<ProdImgVo> getProdImg() throws Exception;
    
    // 상품의 컨텐츠를 가져오는 메서드
    public List<ProductContentVo> getProdContent() throws Exception;
    
    // 상품의 정보를 수정 하는 메서드
    public int updateProd(ProductVo product) throws Exception;
    
    // 상품을 삭제하는 메서드
    public int deleteProd(String prodName) throws Exception;
    
    // 상품을 등록하는 메서드
    public int registerProduct(ProductDTO product) throws Exception;
    
    // 상품의 이미지를 등록하는 메서드
    public int registerProdImg(String prodImg, String prodNo) throws Exception;
    
    // 상품의 상세설명을 등록하는 메서드
    public int registerProdContent(String prodContent, String prodNo) throws Exception;
    
    // 구매 확정된 상품들의 누적 금액을 가져오는 메서드
    public int totalSales() throws Exception;
}
