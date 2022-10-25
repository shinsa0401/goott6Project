package com.boritgogae.persistence;

import java.util.List;

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
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) {
		
		return ses.selectList(ns+".prodList",pi);
	}

	@Override
	public int getProdCnt() throws Exception {
		return ses.selectOne(ns+".prodCnt");
	}

}
