package com.boritgogae.service;

import java.util.ArrayList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.OrderProductDTO;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO prodDao;

	@Override
	public ProductVO getProd(String prodNo) {
		
		return prodDao.getProd(prodNo);
	}

	@Override
	public List<ProdImgVO> getProdImg(String prodNo) {
		
		return prodDao.getProdImg(prodNo);
	}

	@Override
	public List<ProductVO> getProducts(OrderSheetDTO orderSheet) {
		List<ProductVO> products = new ArrayList<>();
		List<OrderProductDTO> orders = orderSheet.getOrderProducts();
		
		
		
		for(OrderProductDTO order : orders) {
			products.add(prodDao.getProd(order.getProdNo()));
		}
		return products;
	}
}
