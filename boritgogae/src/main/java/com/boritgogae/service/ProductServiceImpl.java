package com.boritgogae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.ProductVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.persistence.ProductDAO;


@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	
	@Override
	public ProductDTO getPopularProd(String prodNo) throws Exception {
		ProductDTO product = dao.getPopular(prodNo);
		return product;
	}


	@Override
	public List<ProductDTO> getLastProduct() throws Exception {
		
		return dao.LastProduct();
	}
	

	private TipPagingInfo pagingProcess(int pageNo, String category) throws Exception {
		TipPagingInfo result = new TipPagingInfo();
		
		result.setTotalPostCnt(dao.getProdCnt(pageNo,category)); // 전체 글의 갯수 setting
		
		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지수 setting

		result.setStartNum(pageNo); // 현재페이지에서 출력을 시작할 글 번호(index)

		// --페이징블럭을 위한부분
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이지 블럭수 setting
		result.setCurrentPagingBlock(pageNo);// 현재페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		System.out.println(result.toString());

		return result;
	}
	
	private TipPagingInfo pagingSearchProcess(int pageNo, String category) throws Exception {
		TipPagingInfo result = new TipPagingInfo();
		
		result.setTotalPostCnt(dao.getSearchProdCnt(pageNo,category)); // 전체 글의 갯수 setting
		
		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지수 setting

		result.setStartNum(pageNo); // 현재페이지에서 출력을 시작할 글 번호(index)

		// --페이징블럭을 위한부분
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이지 블럭수 setting
		result.setCurrentPagingBlock(pageNo);// 현재페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		System.out.println(result.toString());

		return result;
	}


	@Override
	public Map<String,Object> getProductAll(String category,int pageNo) throws Exception {
		TipPagingInfo pi = pagingProcess(pageNo,category);
		List<ProductDTO> prodLst = dao.getProductAll(category,pi); 
		
		Map<String,Object> map = new HashMap<>();
		map.put("pi", pi);
		map.put("prodLst", prodLst);
		
		return map;
	}


	@Override
	public Map<String, Object> getSearchProduct(int pageNo, String searchWord) throws Exception {
		Map<String, Object> map = new HashMap<>();
		TipPagingInfo pi = pagingSearchProcess(pageNo, searchWord);
		List<ProductDTO> prodLst = dao.getSearchProductAll(searchWord,pi);
		
		map.put("pi", pi);
		map.put("prodLst", prodLst);
		System.out.println(searchWord+"검색상품");
		System.out.println(prodLst+"결과상품");
		return map;
		
	}



}
