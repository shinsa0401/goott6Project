package com.boritgogae.persistence;

import java.sql.Timestamp;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;

public interface MemberDAO {

	// 로그인하기 위해 회원정보를 얻어오는 메서드
	public MemberVo logIn(LogInDTO dto) throws Exception;

//	// 자동로그인을 체크한 회원의 세션의 정보를 업데이트
//	public int updateMemberSession(String memberId, String sessionId, Timestamp sessionLimit) throws Exception;

}
