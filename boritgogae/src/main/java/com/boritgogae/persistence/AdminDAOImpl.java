package com.boritgogae.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.board.notice.etc.NoticePagingInfo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Inject
    private SqlSession ses;

    private static String ns = "com.boritgogae.AdminMapper";
    private static String prodNs = "com.boritgogae.productMapper";

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
    public List<ProductVo> getLowestProduct() throws Exception {
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

	@Override
	public List<ProductVo> getTopLikeCount() throws Exception {
		return ses.selectList(ns + ".getTopLikeCount");
	}

	@Override
	public List<OrdersVo> getNewOrder() throws Exception {
		return ses.selectList(ns + ".getNewOrder");
	}

	@Override
	public List<ProductVo> getTopReadCount() throws Exception {
		return ses.selectList(ns + ".getTopReadCount");
	}

	@Override
	public List<MemberVo> searchMember(String inputString) throws Exception {
		return ses.selectList(ns + ".searchMember", inputString);
	}

	@Override
	public List<DeleteReasonVo> getDeleteReason() throws Exception {
		return ses.selectList(ns + ".getDeleteReason");
	}

	@Override
	public MemberVo getMemberProfile(String memberId) throws Exception {
		return ses.selectOne(ns + ".getMemberProfile", memberId);
	}

	@Override
	public List<CouponUsedVo> getCouponFromMember(String memberId) throws Exception {
		return ses.selectList(ns + ".getCouponFromMember", memberId);
	}

	@Override
	public List<DeliveryInfoVo> getMemberAddress(String memberId) throws Exception {
		return ses.selectList(ns + ".getMemberAddress", memberId);
	}

	@Override
	public int modifyMemberForAdmin(MemberVo member) throws Exception {
		return ses.update(ns + ".modifyMemberForAdmin", member);
	}

	@Override
	public int deleteMember(String memberId) throws Exception {
		return ses.delete(ns + ".deleteMember", memberId);
	}

	@Override
	public int getLogInMemberCount() throws Exception {
		return ses.selectOne(ns + ".getLogInMemberCount");
	}

	@Override
	public int getProdCnt() throws Exception {
		return ses.selectOne(prodNs + ".prodCnt");
	}

	@Override
	public List<ProductVo> getProdList(NoticePagingInfo pi) throws Exception {
		return ses.selectList(ns + ".getProductList", pi);
	}

	@Override
	public List<ProdImgVo> getProdImg() throws Exception {
		return ses.selectList(ns + ".getProductImg");
	}

	@Override
	public List<ProductContentVo> getProdContent() throws Exception {
		return ses.selectList(ns + ".getProductContent");
	}

	@Override
	public int updateProd(ProductVo product) throws Exception {
		return ses.update(ns + ".updateProduct", product);
	}

	@Override
	public int deleteProd(String prodName) throws Exception {
		return ses.delete(ns + ".deleteProduct", prodName);
	}

	@Override
	public int registerProduct(ProductDTO product) throws Exception {
		return ses.insert(ns + ".registerProduct", product);
	}

	@Override
	public int registerProdImg(String prodImg, String prodNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);
		map.put("originalFile", prodImg);
		return ses.insert(ns + ".registerProdImg", map);
	}
	
	@Override
	public int registerProdContent(String prodContent, String prodNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);
		map.put("prodContent", prodContent);
		System.out.println(map);
		return ses.insert(ns + ".registerProdContent", map);
	}

	
    

}
