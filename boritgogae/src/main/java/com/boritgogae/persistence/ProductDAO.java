package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;

public interface ProductDAO {

	public ProductDTO getPopular(String prodNo) throws Exception;

	public List<ProductDTO> LastProduct() throws Exception;
	
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) throws Exception;

	public int getProdCnt() throws Exception;
}
