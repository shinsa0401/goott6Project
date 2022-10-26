package com.boritgogae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.board.notice.etc.NoticePagingInfo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;
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
    public List<ProductVo> getLowestProduct() throws Exception {
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
	public List<ProductVo> getTopLikeCount() throws Exception {
		return dao.getTopLikeCount();
	}

	@Override
	public List<OrdersVo> getNewOrder() throws Exception {
		return dao.getNewOrder();
	}

	@Override
	public List<ProductVo> getTopReadCount() throws Exception {
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

	@Override
	public int getProdCnt() throws Exception {
		return dao.getProdCnt();
	}

	@Override
	public Map<String, Object> getProdList(int pageNo) throws Exception {
		
		NoticePagingInfo pi = pagingProcess(pageNo);
		
		List<ProductVo> lst = null;
		
		lst = dao.getProdList(pi);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prodList", lst);
		resultMap.put("pagingInfo", pi);
		
		return resultMap;
	}

	private NoticePagingInfo pagingProcess(int pageNo) throws Exception {
		NoticePagingInfo result = new NoticePagingInfo();

		result.setTotalPostCnt(dao.getProdCnt()); // 전체 글의 갯수 setting

		// ------------------------------ 페이징 구현 ------------------------------

		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지 수 setting
		result.setStartNum(pageNo); // 현재 페이지에서 출력을 시작할 글 번호(index)

		// ------------------------------ 페이징 블럭을 위한 부분 ------------------------------
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이징 블럭 수 setting
		result.setCurrentPagingBlock(pageNo); // 현재 페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting

		System.out.println(result.toString());

		return result;
	}

	@Override
	public List<ProdImgVo> getProdImg() throws Exception {
		return dao.getProdImg();
	}

	@Override
	public List<ProductContentVo> getProductContent() throws Exception {
		return dao.getProdContent();
	}

}
