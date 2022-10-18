package com.boritgogae.service;

import java.util.List;

import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVO;

public interface AdminService {
	
	// 회원 전체 목록 가져오는 메서드
	public List<MemberVo> getMembers() throws Exception;
	
	// 최근 30일간 가입한 회원 목록을 가져오는 메서드
	public List<MemberVo> getNewMembers() throws Exception;
	
	   // 재고가 적은 순
    public List<ProductVO> getLowestProduct() throws Exception;
}
