package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.ProductVo;

public interface ProductDAO {

	List<ProductVo> getProdInfo(int pageNo);

}
