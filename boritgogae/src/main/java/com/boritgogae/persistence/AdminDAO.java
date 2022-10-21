package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.MemberVo;

public interface AdminDAO {

   // 회원 전체 목록 가져오는 메서드
   public List<MemberVo> getMembers() throws Exception;
   
}