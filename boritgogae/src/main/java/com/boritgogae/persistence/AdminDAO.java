package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVO;

public interface AdminDAO {
    
    // ----------------- 회원 관련 메서드 -----------------

	// 회원 전체 목록 가져오는 메서드
	public List<MemberVo> getMembers() throws Exception;
	
	// 최근 30일간 가입한 회원 목록을 가져오는 메서드
	public List<MemberVo> getNewMembers() throws Exception;
	
	// 탈퇴한 회원 목록을 가져오는 메서드
	public List<DeleteAccountVo> getDelMembers() throws Exception;
	
	
	// ----------------- 쿠폰 관련 메서드 -----------------
	
	// 쿠폰 신규 등록 하는 메서드
	public int createCoupon(CouponVo coupon) throws Exception;
	
	// 등록된 모든 쿠폰을 가져오는 메서드
	public List<CouponVo> getCoupon() throws Exception;
	
	// 등록된 쿠폰을 삭제하는 메서드
	public int delCoupon(String couponName) throws Exception;
	
	// 등록된 쿠폰을 수정하는 메서드
	public int modifyCoupon(CouponVo coupon, String modiCouponName) throws Exception;
	
	// ----------------- 상품 관련 메서드 -----------------
	
	// 재고가 적은 순
	public List<ProductVO> getLowestProduct() throws Exception;

	
	
	
}
