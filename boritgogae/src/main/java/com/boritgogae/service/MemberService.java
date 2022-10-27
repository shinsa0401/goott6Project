package com.boritgogae.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

@Service
public interface MemberService {
	
	
	
	public int likeProduct(String prodNo) throws Exception;
	
	public List<ProductVo> selectLike(String memberId) throws Exception;
	
	public int memberjoin(MemberVo vo,HttpServletResponse response,DeliveryInfoVo dv) throws Exception;
	
	
	
	public void checkid(String memberId,HttpServletResponse response) throws Exception;
	
	public void checkname(String memberName,HttpServletResponse response)throws Exception;
	
	public void checkemail(String memberEmail,HttpServletResponse response)throws Exception;

	int getSearchResultCnt(FreeSearchCondition sc) throws Exception;

	List<DM> getSearchResultPage(FreeSearchCondition sc) throws Exception;

	int sendDel(String no) throws Exception;
	
	public Map<String, Object> detaildm(int no)throws Exception;
	
	
	public int insertWriter(DM dm)throws Exception;

}
