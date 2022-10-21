package com.boritgogae.service;

import java.util.List;

import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;

public interface ProductService {
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
	 * @입력 param : 상품번호 prodNo
	 * @returnType : List<ProdImgVO>
	 **/
	public List<ProdImgVO> getProdImg(String prodNo);
	
	/**
	 * @methodName : getProducts
	 * @author : kjy
	 * @date : 2022. 10. 20.
	 * @입력 param : orderSheetDTO(orderProductDTO의 리스트)
	 * @returnType : List<ProductVO>
	 **/
	public List<ProductVO> getProducts(OrderSheetDTO orderSheet);
}
