package com.boritgogae.persistence;


import java.util.List;
import java.util.Map;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

public interface MemberDAO {
	

	public List<ProductVo> selectLike (String memberId)throws Exception;

	public int likeProduct (String prodNo)throws Exception;
	
	
	
	
	
	
	public int memberjoin(MemberVo vo) throws Exception;
	
	public void joindelivery(DeliveryInfoVo dv)throws Exception;
	
	public int checkid(String memberId)throws Exception;
	
	public int checkname(String memberName)throws Exception;


	public int checkemail(String memberEmail)throws Exception;

	public int searchResultCnt(FreeSearchCondition sc) throws Exception;

	public List<DM> searchSelectPage(FreeSearchCondition sc) throws Exception;

	public int sendDel(String no) throws Exception;
	public DM detaildm(int no)throws Exception;
	
	public int insertWriter(DM dm)throws Exception;
	

}
