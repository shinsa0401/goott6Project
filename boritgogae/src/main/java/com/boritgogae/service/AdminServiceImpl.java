package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
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

	@Override
	public List<ProductVO> getTopLikeCount() throws Exception {
		return dao.getTopLikeCount();
	}

	@Override
	public List<OrdersVO> getNewOrder() throws Exception {
		return dao.getNewOrder();
	}

	@Override
	public List<ProductVO> getTopReadCount() throws Exception {
		return dao.getTopReadCount();
	}

	@Override
	public List<MemberVo> searchMember(String inputString) throws Exception {
		return dao.searchMember(inputString);
	}

	@Override
	public List<DeleteReasonVo> getDeleteReason() throws Exception {
		return dao.getDeleteReason();
	}

	@Override
	public MemberVo getMemberProfile(String memberId) throws Exception {
		return dao.getMemberProfile(memberId);
	}

	@Override
	public List<CouponUsedVo> getCouponFromMember(String memberId) throws Exception {
		return dao.getCouponFromMember(memberId);
	}

	@Override
	public List<DeliveryInfoVo> getMemberAddress(String memberId) throws Exception {
		return dao.getMemberAddress(memberId);
	}

	@Override
	public boolean modifyMemberForAdmin(MemberVo member) throws Exception {
		boolean result = false;
		if(dao.modifyMemberForAdmin(member) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteMember(String memberId) throws Exception {
		boolean result = false;
		
		
		if(dao.deleteMember(memberId) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public int getLogInMemberCount() throws Exception {
		return dao.getLogInMemberCount();
	}
	


}
