package com.boritgogae.persistence;

import java.util.List;



import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	private SqlSession ses;
	
	String ns = "com.boritgogae.productMapper";



	@Override
	public ProductVo getProd(String prodNo) {
		
		return ses.selectOne(ns+".getProd", prodNo);
	}



	@Override
	public List<ProdImgVo> getProdImg(String prodNo) {
		return ses.selectList(ns+".getProdImg", prodNo);
	}



	@Override
	public int updateProdReview(String prodNo) {
		
		return ses.update(ns+".updateProdReview", prodNo);


	}
	

	@Override
	public ProductDTO getPopular(String prodNo) throws Exception {
		
		return ses.selectOne(ns+".popularProduct",prodNo);
	}


	@Override
	public List<ProductDTO> LastProduct() throws Exception {

		return ses.selectList(ns+".LastProduct");
	}
	
	@Override
	public List<ProductDTO> getProdInfo(TipPagingInfo pi) {
		
		return ses.selectList(ns+".prodList",pi);
	}



	 /**
	 * @methodName : getProdContent
	 * @author : kjy
	 * @date : 2022. 10. 25.
	 * @입력 param : prodNo
	 * @returnType : ProductContentVo
	 */
	@Override
	public ProductContentVo getProdContent(String prodNo) throws Exception {
		
		return ses.selectOne(ns+".getProdContent", prodNo);
	}


	@Override
	public int getProdCnt() throws Exception {
		return ses.selectOne(ns+".prodCnt");
	}



	@Override
	public List<ProductDTO> getProductAll(String category, TipPagingInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ProductDTO> getSearchProductAll(String searchWord, TipPagingInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
