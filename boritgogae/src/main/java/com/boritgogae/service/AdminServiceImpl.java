package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
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
    public List<DeleteAccountVo> getDelMembers() throws Exception {
        return dao.getDelMembers();
    }

    @Override
    public List<ProductVO> getLowestProduct() throws Exception {
        return dao.getLowestProduct();
    }

    @Override
    public boolean createCoupon(CouponVo coupon) throws Exception {
        boolean result = false;
        
        if(dao.createCoupon(coupon) == 1) {
            result = true;
        }
        
        return result;
    }

    @Override
    public List<CouponVo> getCoupon() throws Exception {
        return dao.getCoupon();
    }

	@Override
	public boolean delCoupon(String couponName) throws Exception {
		boolean result = false;
		
		if(dao.delCoupon(couponName) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean modifyCoupon(CouponVo coupon, String modiCouponName) throws Exception {
		boolean result = false;
		
		if(dao.modifyCoupon(coupon, modiCouponName) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean sendCoupon(CouponUsedVo sendCoupon) throws Exception {
		boolean result = false;
		
		if(dao.sendCoupon(sendCoupon) == 1) {
			result = true;
		}
		
		return result;
	}



}
