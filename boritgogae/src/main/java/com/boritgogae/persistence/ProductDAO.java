package com.boritgogae.persistence;

import java.util.List;


import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;

public interface ProductDAO {
	
	/**
	 * @methodName : getProd
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 상품번호 prodNo
	 * @returnType : ProductVO
	 **/
	public ProductVo getProd(String prodNo);
	
	/**
	 * @methodName : getProdImg
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 상품번호
	 * @returnType : List<ProdImgVO>
	 **/
	public List<ProdImgVo> getProdImg(String prodNo);
	
	/**
	 * @methodName : updateProdReview
	 * @author : kjy
	 * @date : 2022. 10. 18.
	 * @입력 param : 상품번호
	 * @returnType : int
	 **/
	public int updateProdReview(String prodNo);
	
	/**
	 * @methodName : getProdContent
	 * @author : kjy
	 * @date : 2022. 10. 25.
	 * @입력 param : prodNo
	 * @returnType : ProductContentVo
	 */
	public ProductContentVo getProdContent(String prodNo) throws Exception;





	public ProductDTO getPopular(String prodNo) throws Exception;
	
	public List<ProductDTO> LastProduct() throws Exception;
	
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) throws Exception;

	public int getProdCnt(int pageNo, String category) throws Exception;

	public List<ProductDTO> getProductAll(String category, TipPagingInfo pi) throws Exception;
	
	public int getSearchProdCnt(int pageNo, String category) throws Exception;

	public List<ProductDTO> getSearchProductAll(String category, TipPagingInfo pi) throws Exception;


}
