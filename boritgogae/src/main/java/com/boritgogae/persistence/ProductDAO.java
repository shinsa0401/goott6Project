package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;
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
	public ProductVO getProd(String prodNo);
	
	/**
	 * @methodName : getProdImg
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 상품번호
	 * @returnType : List<ProdImgVO>
	 **/
	public List<ProdImgVO> getProdImg(String prodNo);
	
	/**
	 * @methodName : updateProdReview
	 * @author : kjy
	 * @date : 2022. 10. 18.
	 * @입력 param : 상품번호
	 * @returnType : int
	 **/
	public int updateProdReview(String prodNo);





	public ProductDTO getPopular(String prodNo) throws Exception;

	public List<ProductDTO> LastProduct() throws Exception;
	
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) throws Exception;

	public int getProdCnt() throws Exception;

}
