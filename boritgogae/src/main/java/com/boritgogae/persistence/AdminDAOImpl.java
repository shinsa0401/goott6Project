package com.boritgogae.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Inject
    private SqlSession ses;

    private static String ns = "com.boritgogae.AdminMapper";

    @Override
    public List<MemberVo> getMembers() throws Exception {
        return ses.selectList(ns + ".getMembers");
    }

    @Override
    public List<MemberVo> getNewMembers() throws Exception {
        return ses.selectList(ns + ".getNewMembers");
    }
    
    @Override
    public List<DeleteAccountVo> getDelMembers() throws Exception {
        return ses.selectList(ns + ".getDelMembers");
    }

    @Override
    public List<ProductVO> getLowestProduct() throws Exception {
        return ses.selectList(ns + ".getLowestProductQTY");
    }

    @Override
    public int createCoupon(CouponVo coupon) throws Exception {
        return ses.insert(ns + ".createCoupon", coupon);
    }

    @Override
    public List<CouponVo> getCoupon() throws Exception {
        return ses.selectList(ns + ".getCoupon");
    }

	@Override
	public int delCoupon(String couponName) throws Exception {
		return ses.delete(ns + ".delCoupon", couponName);
	}

	@Override
	public int modifyCoupon(CouponVo coupon, String modiCouponName) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("modiCouponName", modiCouponName);
		map.put("coupon", coupon);
		
		return ses.update(ns + ".modifyCoupon", map);
	}

	@Override
	public int sendCoupon(CouponUsedVo sendCoupon) throws Exception {
		return ses.insert(ns + ".sendCoupon", sendCoupon);
	}


    

}
