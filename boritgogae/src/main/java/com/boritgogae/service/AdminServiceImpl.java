package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO dao;
	
	@Override
	public List<MemberVo> getMembers() throws Exception {
		return dao.getMembers();
	}

	@Override
	public List<MemberVo> getNewMembers() throws Exception {
		return dao.getNewMembers();
	}

    @Override
    public List<ProductVO> getLowestProduct() throws Exception {
        return dao.getLowestProduct();
    }

}
