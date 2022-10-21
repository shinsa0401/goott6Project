package com.boritgogae.persistence;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private static String ns = "com.boritgogae.memberMapper";
	
	@Inject
	private SqlSession session;
	
	// 로그인하기위해 회원정보를 얻어오는 메서드
	@Override
	public MemberVo logIn(LogInDTO dto) throws Exception {
		
		return session.selectOne(ns + ".logIn", dto);
	}

//	// 자동로그인 체크한 회원 세션의 정보를 업데이트
//	@Override
//	public int updateMemberSession(String memberId, String sessionId, Timestamp sessionLimit) throws Exception {
//		System.out.println("다오단 : 세션 업데이트");
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("memberId", memberId);
//		map.put("sessionId", sessionId);
//		map.put("sessionLimit", sessionLimit);
//		
//		return session.update(ns + ".updateMemberSession", map);
//	}

}
