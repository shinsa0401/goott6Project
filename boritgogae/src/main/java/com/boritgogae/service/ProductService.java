package com.boritgogae.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.domain.ProductDTO;

public interface ProductService {
	
	public ProductDTO getPopularProd(String prodNo) throws Exception;

	public List<ProductDTO> getLastProduct() throws Exception;
	
	public Map<String,Object> getProductAll(String category, int pageNo) throws Exception;

	public Map<String, Object> getSearchProduct(int pageNo, String searchWord) throws Exception;

}
