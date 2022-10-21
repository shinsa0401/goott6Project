package com.boritgogae.service;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	// 로그인 처리하는 메서드
	@Override
	public MemberVo logIn(LogInDTO dto, HttpServletRequest request) throws Exception {
		
		MemberVo logInMember = dao.logIn(dto);
		
		if (logInMember != null) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("일치하는 정보가 없다");
		}
		
		return logInMember;
	}

//	// 자동로그인을 체크했을 경우 로그인 유지를 위한 sessionId, sessionLimit 업데이트 
//	@Override
//	public int keepLogIn(String memberId, String sessionId, Timestamp sessionLimit) throws Exception {
//		dao.updateMemberSession(memberId, sessionId, sessionLimit);
//		return 0;
//	}

}
