package com.boritgogae.service;

import java.util.List;


import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductVo;
import java.util.Map;

import com.boritgogae.domain.ProductDTO;


public interface ProductService {
	/**
	 * @methodName : getProd
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 상품번호 prodNo
	 * @returnType : ProductVO
	 **/
	public ProductVo getProd(String prodNo) throws Exception;
	

	/**
	 * @methodName : getProdImg
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 상품번호 prodNo
	 * @returnType : List<ProdImgVO>
	 **/
	public List<ProdImgVo> getProdImg(String prodNo) throws Exception;
	
	/**
	 * @methodName : getProducts
	 * @author : kjy
	 * @date : 2022. 10. 20.
	 * @입력 param : orderSheetDTO(orderProductDTO의 리스트)
	 * @returnType : List<ProductVO>
	 **/
	public List<ProductVo> getProducts(OrderSheetDTO orderSheet) throws Exception;

	public ProductDTO getPopularProd(String prodNo) throws Exception;

	public List<ProductDTO> getLastProduct() throws Exception;
	

	


	
	public ProductContentVo getProdContent(String prodNo) throws Exception;

	public Map<String,Object> getProductAll(String category, int pageNo) throws Exception;

	public Map<String, Object> getSearchProduct(int pageNo, String searchWord) throws Exception;


}
