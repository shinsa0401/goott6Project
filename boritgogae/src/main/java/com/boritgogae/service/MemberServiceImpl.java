package com.boritgogae.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	
	
	@Inject
	MemberDAO dao;

	@Override
	public int memberjoin(MemberVo vo,HttpServletResponse response,DeliveryInfoVo dv) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		dv.setMemberId(vo.getMemberId());
		dv.setRecipient(vo.getMemberName());
		dv.setRecipientPhoneNumber(vo.getPhoneNumber());
		
		
		
		if (dao.checkid(vo.getMemberId()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 아이디가 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else {
			dao.memberjoin(vo);
			dao.joindelivery(dv);
			return 1;
		}
	
		
	}



	@Override
	public void checkid(String memberId, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkid(memberId));
		out.close();
		
	}

	@Override
	public void checkname(String memberName,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkname(memberName));
		out.close();
		
	}

	@Override
	public void checkemail(String memberEmail,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkemail(memberEmail));
		out.close();
		
	}

	@Override
	public List<ProductVo> selectLike(String memberId) throws Exception {
	 
		
		return dao.selectLike(memberId);
	}



	@Override
	public int likeProduct(String prodNo) throws Exception {
		
		return dao.likeProduct(prodNo);
	}



	
	
	
	@Override
    public int getSearchResultCnt(FreeSearchCondition sc) throws Exception {
        return dao.searchResultCnt(sc);
    }

    @Override
    public List<DM> getSearchResultPage(FreeSearchCondition sc) throws Exception {
        return dao.searchSelectPage(sc);
    }
	
    @Override
    public int sendDel(String no)throws Exception{
    	
    	
    	return dao.sendDel(no);
    }



	@Override
	public Map<String, Object> detaildm(int no) throws Exception {
		
		DM dm = dao.detaildm(no);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dm", dm);

		return map;
	}



	@Override
	public int insertWriter(DM dm) throws Exception {
		
		return dao.insertWriter(dm);
	}

	
	
	
}
