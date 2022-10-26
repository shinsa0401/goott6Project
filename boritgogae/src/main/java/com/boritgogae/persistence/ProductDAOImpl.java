package com.boritgogae.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.domain.ProductVo;


@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession ses;
	
	private String ns = "com.boritgogae.productMapper";
	

	@Override
	public ProductDTO getPopular(String prodNo) throws Exception {
		
		return ses.selectOne(ns+".popularProduct",prodNo);
	}


	@Override
	public List<ProductDTO> LastProduct() throws Exception {
		
		return ses.selectList(ns+".LastProduct");
	}
	

	@Override
	public int getProdCnt(int pageNo, String category) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("pageNo", pageNo+"");
		int s = ses.selectOne(ns+".prodCnt",map);
		return ses.selectOne(ns+".prodCnt",map);
	}


	@Override
	public List<ProductDTO> getProductAll(String category, TipPagingInfo pi) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String startNum = pi.getStartNum()+"";
		String PostPerPage = pi.getPostPerPage()+"";
		map.put("category", category);
		map.put("StartNum", pi.getStartNum());
		map.put("PostPerPage", pi.getPostPerPage());
		
		return ses.selectList(ns+".ProductAll", map);
	}


	@Override
	public int getSearchProdCnt(int pageNo, String category) throws Exception {
		
		System.out.println(ses.selectOne(ns+".productSearchCnt", category)+"@@@검색 결과상품갯수");
		
		return ses.selectOne(ns+".productSearchCnt", category);
	}


	@Override
	public List<ProductDTO> getSearchProductAll(String category, TipPagingInfo pi) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("StartNum", pi.getStartNum());
		map.put("PostPerPage", pi.getPostPerPage());
		
		System.out.println(pi.getStartNum()+"이게 문제인가");
		System.out.println(category+"검색키워드는 잘들어오는가");
		System.out.println(pi.getPostPerPage()+"끝 페이지 이거는 들어오는가");
		
		return ses.selectList(ns+".productSearchList", map);
	}





}
