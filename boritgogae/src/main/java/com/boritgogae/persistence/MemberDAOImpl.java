package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession ses;
	
	String ns = "com.boritgogae.memberMapper";

	@Override
	public MemberVo getMemInfo(String memberId) {
		
		return ses.selectOne(ns+".getMemberInfo", memberId);
	}

	@Override
	public List<DeliveryInfoVo> getMemAddrs(String memberId) {
		// TODO Auto-generated method stub
		return ses.selectList(ns+".getMemAddrs", memberId);
	}

}
