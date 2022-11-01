package com.boritgogae.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.domain.DM;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradeVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.domain.LogInDTO;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	
	@Inject
	SqlSession ses;
	
	String ns = "com.boritgogae.memberMapper";
	
	@Override
	public int memberjoin(MemberVo vo) throws Exception{
		
		return ses.insert(ns+".memberjoin", vo);
	}

	@Override
	public void joindelivery( DeliveryInfoVo dv ) throws Exception {
		
		
		ses.insert(ns+".joindelivery", dv);
	}

	@Override
	public int checkid(String memberId) throws Exception {
		
		return ses.selectOne(ns+".checkid", memberId);
	}

	@Override
	public int checkname(String memberName) throws Exception {
		
		return ses.selectOne(ns+".checkname", memberName);
	}

	@Override
	public int checkemail(String memberEmail) throws Exception {
		
		return ses.selectOne(ns+".checkemail", memberEmail);
	}

	@Override
	public List<ProductVo> selectLike(String memberId) throws Exception {
		
		return ses.selectList(ns+".selectLike", memberId);
	}

	@Override
	public int likeProduct(String prodNo) throws Exception {
		
		return ses.selectOne(ns+".likeProduct", prodNo);
	}


	
	
	
	
	@Override
	public int searchResultCnt(FreeSearchCondition sc) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(ns+".searchResultCnt", sc);
	}

	@Override
	public List<DM> searchSelectPage(FreeSearchCondition sc) throws Exception {
		 return ses.selectList(ns+".searchSelectPage", sc);
		
	}
	
	@Override
	public int sendDel(String no)throws Exception{
		
		
		return ses.update(ns+".sendDel", no);
	}

	@Override
	public DM detaildm(int no) throws Exception {
		
		return ses.selectOne(ns+".detaildm", no);
	}

	@Override
	public int insertWriter(DM dm) throws Exception {
		// TODO Auto-generated method stub
		return ses.insert(ns+".insertWriter", dm);
	}
	

}
