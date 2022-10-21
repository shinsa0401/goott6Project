package com.boritgogae.board.prodReply.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.prodReply.domain.OrderDetailVO;
import com.boritgogae.board.prodReply.domain.OrdersVO;
import com.boritgogae.board.prodReply.domain.ReplyDTO;
import com.boritgogae.board.prodReply.domain.ProdReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewDTO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.PageAndProdNo;
import com.boritgogae.board.prodReply.etc.Paging;
import com.boritgogae.board.prodReply.etc.UploadImg;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Inject
	private SqlSession ses;
	
	String ns = "com.boritgogae.boardProdReplyMapper";

	@Override
	public int insertReview(ReviewDTO dto) throws Exception{
		
		return ses.insert(ns+".insertReview", dto);
	}

	@Override
	public List<OrdersVO> getOrder(String userId, String prodNo) throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("prodNo", prodNo);

		List<OrdersVO> orderLst = ses.selectList(ns + ".getOrders", map);
		
		System.out.println(orderLst.toString());
		
		return orderLst;
	}

	@Override
	public int getLastReviewNo() throws Exception {
		return ses.selectOne(ns+".getLastReviewNo");
	}

	@Override
	public int saveReviewImg(UploadImg img) throws Exception {
		return ses.insert(ns+".insertReviewImg", img);
	}

	@Override
	public List<ReviewVO> getReviews(String prodNo, Paging page) throws Exception {
		PageAndProdNo pageAndProdNo = new PageAndProdNo(prodNo, page.getStartNum(), page.getPostPerPage());
		
		List<ReviewVO> reviews = ses.selectList(ns+".selectReviews", pageAndProdNo);

		return reviews;
	}

	@Override
	public List<UploadImg> getReviewImgs(int reviewNo) throws Exception {
		List<UploadImg> reviewImgs = ses.selectList(ns+".selectReviewImgs", reviewNo);
		return reviewImgs;
	}

	@Override
	public int deleteReview(int reviewNo) throws Exception {
		
		return ses.delete(ns+".deleteReview", reviewNo);
	}

	@Override
	public ReviewVO getReviewByRno(int reviewNo) throws Exception {
		
		return ses.selectOne(ns+".getReviewByRno", reviewNo);
	}

	@Override
	public List<UploadImg> getReviewImgByRno(int reviewNo) throws Exception {
		
		return ses.selectList(ns+".getReviewImgByRno", reviewNo);
	}

	@Override
	public int modifyReview(ReviewVO vo) throws Exception {
		return ses.update(ns+".modifyReview", vo);
	}

	@Override
	public int getReviewCntByProdNo(String prodNo) throws Exception {
		
		return ses.selectOne(ns+".getReviewCntByProdNo", prodNo);
	}

	@Override
	public int writeReply(ReplyDTO dto) throws Exception {
		return ses.insert(ns+".insertReply", dto);
	}

	@Override
	public List<ProdReplyVo> getReplies(String prodNo) throws Exception {
		
		return ses.selectList(ns+".gerReplies", prodNo);
	}

	@Override
	public int deleteReply(int rno) throws Exception {
		
		return ses.delete(ns+".deleteReply", rno);
	}

	@Override
	public int deleteReviewImg(int reviewNo) throws Exception {
		
		return ses.delete(ns+".deleteReviewImg", reviewNo);
	}

	@Override
	public int updateRef(int rno) throws Exception {
		
		return ses.update(ns+".updateRef", rno);
	}

	@Override
	public int getLstRno() throws Exception {
		
		return ses.selectOne(ns+".getLastRno");
	}

	@Override
	public ProdReplyVo getReply(int rno) throws Exception {
		
		return ses.selectOne(ns+".gerReply", rno);
	}

	@Override
	public int updateStepRO(ProdReplyVo parentvo, int rno) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("step", parentvo.getStep());
		map.put("refOrder", parentvo.getRefOrder());
		map.put("rno", rno);
		
		return ses.update(ns+".updateStepRO", map);
	}

	@Override
	public int updateReRef(int rno, int parentRef) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("parentRef", parentRef);
		map.put("rno", rno);
		return ses.update(ns+".updateReRef", map);
	}
	
	
	
}
