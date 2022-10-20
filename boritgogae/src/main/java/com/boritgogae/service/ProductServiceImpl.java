package com.boritgogae.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.persistence.ProductDAO;


@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	
	

}
