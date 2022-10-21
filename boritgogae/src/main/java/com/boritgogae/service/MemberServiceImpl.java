package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO memDao;

	@Override
	public MemberVo getMemberInfo(String memberId) {
		
		return memDao.getMemInfo(memberId);
	}

	@Override
	public List<DeliveryInfoVo> getMemAddrs(String memberId) {
		
		return memDao.getMemAddrs(memberId);
	}

}
