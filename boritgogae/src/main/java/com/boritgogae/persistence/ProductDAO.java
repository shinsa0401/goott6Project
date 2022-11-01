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
	






	public ProductDTO getPopular(String prodNo) throws Exception;

	public List<ProductDTO> LastProduct() throws Exception;
	
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) throws Exception;

	public int getProdCnt() throws Exception;
	
	
	/**
	 * @methodName : getProdContent
	 * @author : kjy
	 * @date : 2022. 10. 25.
	 * @입력 param : prodNo
	 * @returnType : ProductContentVo
	 */
	public ProductContentVo getProdContent(String prodNo) throws Exception;
	
	/**
	 * @methodName : updateProdReviewCnt
	 * @author : kjy
	 * @date : 2022. 10. 30.
	 * @입력 param : prodNo
	 * @returnType : int
	 */
	public int updateProdReviewCnt(String prodNo) throws Exception;

}
