package com.boritgogae.persistence;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;

public interface ProductDAO {

	public ProductDTO getPopular(String prodNo) throws Exception;

	public List<ProductDTO> LastProduct() throws Exception;
	
	public int getProdCnt(int pageNo, String category) throws Exception;

	public List<ProductDTO> getProductAll(String category, TipPagingInfo pi) throws Exception;

	public int getSearchProdCnt(int pageNo, String category) throws Exception;

	public List<ProductDTO> getSearchProductAll(String category, TipPagingInfo pi) throws Exception;


}
