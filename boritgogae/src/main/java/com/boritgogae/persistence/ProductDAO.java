package com.boritgogae.persistence;

import java.util.List;


import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;

public interface ProductDAO {
	
	//상품번호로 상품 가져옴
	public ProductVo getProd(String prodNo);
	
	//상품번호로 상품이미지 가져옴
	public List<ProdImgVo> getProdImg(String prodNo);
	






	public ProductDTO getPopular(String prodNo) throws Exception;

	public List<ProductDTO> LastProduct() throws Exception;
	
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) throws Exception;

	public int getProdCnt() throws Exception;
	
	
	//상품상세 가져옴
	public ProductContentVo getProdContent(String prodNo) throws Exception;
	
	//리뷰 수 업데이트
	public int updateProdReviewCnt(String prodNo) throws Exception;

	public List<ProductDTO> getSearchProductAll(String searchWord, TipPagingInfo pi);
	


}
