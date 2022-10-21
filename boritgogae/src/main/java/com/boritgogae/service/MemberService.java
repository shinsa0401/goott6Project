package com.boritgogae.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;

public interface MemberService {

	// 로그인을 처리하는 메서드
	public MemberVo logIn(LogInDTO dto, HttpServletRequest request) throws Exception;

//	// 자동로그인을 체크했을 경우 로그인 유지를 위한 sessionId, sessionLimit 업데이트
//	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit);

}
