package com.boritgogae.service;

import java.util.List;

import javax.inject.Inject;

import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.persistence.ProductDAO;

public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO prodDao;

	@Override
	public ProductVO getProd(String prodNo) {
		
		return prodDao.getProd(prodNo);
	}

	@Override
	public List<ProdImgVO> getProdImg(String prodNo) {
		
		return prodDao.getProdImg(prodNo);
	}
}
