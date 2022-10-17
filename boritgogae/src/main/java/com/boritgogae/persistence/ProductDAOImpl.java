package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;

public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	private SqlSession ses;
	
	String ns = "com.boritgogae.productMapper";



	@Override
	public ProductVO getProd(String prodNo) {
		
		return ses.selectOne(ns+".getProd", prodNo);
	}



	@Override
	public List<ProdImgVO> getProdImg(String prodNo) {
		return ses.selectList(ns+".getProdImg", prodNo);
	}

}
