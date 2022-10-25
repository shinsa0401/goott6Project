package com.boritgogae.board.prodReply.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.board.prodReply.domain.ReplyDTO;
import com.boritgogae.board.prodReply.domain.ProdReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewDTO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.Paging;
import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.board.prodReply.persistence.ReviewDAO;
import com.boritgogae.persistence.ProductDAO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Inject
	private ReviewDAO dao;
	
	@Inject
	private ProductDAO prodDao;

	//리뷰쓰는 메서드
	@Override
	public boolean addReview(ReviewDTO dto) throws Exception{
		
		dto.setReviewContent(dto.getReviewContent().replace("\r\n", "<br />"));
		
		int row =dao.insertReview(dto);
		boolean result = false;
		if (row == 1) {
			//포인트 부여
			//등급으로 구매적립포인트 가져오기
			//주문테이블에서 구매액수 가져오기
			//계산하기
			//insert하기
			//회원의 총 포인트 업데이트 해주기
			
			//상품의 reviewCount 업데이트
			System.out.println(dto.getProdNo());
			prodDao.updateProdReview(dto.getProdNo());
			result = true;
		}
		return result;
	}

	//리뷰이미지 데이터베이스에 저장
	@Override
	public boolean saveReviewImg(int reviewNo, List<UploadImg> imgLst) throws Exception {
		boolean result = false;
		int row = 0;
		System.out.println("서비스단"+reviewNo+ imgLst.toString());
		for(UploadImg img : imgLst) {
			img.setReviewNo(reviewNo);
			row += dao.saveReviewImg(img);
		}
		if(row == imgLst.size()) {
			result = true;
		}

		return result;
	}

	//마지막으로 쓴 리뷰 번호 얻어오는 메서드
	@Override
	public int getLastReviewNo() throws Exception {
		
		return dao.getLastReviewNo();
	}

	//상품번호로 리뷰 가져오는 메서드
	@Override
	public Map<String, Object> getReviews(String prodNo, int pageNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int reviewCnt = dao.getReviewCntByProdNo(prodNo);
		
		Paging page = new Paging(4, 3, reviewCnt, pageNo);
		
		List<ReviewVO> reviews = dao.getReviews(prodNo, page);
		
		map.put("page", page);
		map.put("reviews", reviews);
		
		return map;
	}


	//리뷰이미지 가져오는 메서드
	@Override
	public List<UploadImg> getReviewImgs(int reviewNo) throws Exception {
		List<UploadImg> reviewImgs = dao.getReviewImgs(reviewNo);
		for(UploadImg img : reviewImgs) {
			img.setReviewNo(reviewNo);
		}
		
		return reviewImgs;
	}

	//리뷰 삭제하는 메서드
	@Override
	public boolean deleteReview(int reviewNo) throws Exception {
		boolean result = false;
		
		int row = dao.deleteReview(reviewNo);
		
		if (row == 1) {
			result = true;
		}
		
		return result;
	}
	
	
	

	@Override
	public Map<String, Object> getReviewByRno(int reviewNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("review", dao.getReviewByRno(reviewNo));
		map.put("reviewImgs", dao.getReviewImgByRno(reviewNo));
		
		return map;
	}



	@Override
	public boolean modifyReview(ReviewVO vo) throws Exception {
		int row = dao.modifyReview(vo);
		if(row == 1) {
			return true;
		}else {
			return false;
		}
	}

	//댓글 저장하는 메서드
	@Override
	public boolean writeReply(ReplyDTO dto) throws Exception {
		boolean result = false;
		
		//댓글 저장하기
		int row = 0;
		if(dao.writeReply(dto) == 1) {
			//댓글 ref업데이트하기
			int lastNo = dao.getLstRno();
			
			row = dao.updateRef(lastNo);
		}
		if(row == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public List<ProdReplyVo> getReplies(String prodNo) throws Exception {
		
		return dao.getReplies(prodNo);
	}

	@Override
	public boolean deleteReply(int rno) throws Exception {
		boolean result = false;
		
		int row = dao.deleteReply(rno);
		
		if (row == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public void deleteReviewImg(int reviewNo) throws Exception {
		int row = dao.deleteReviewImg(reviewNo);
	}

	@Override
	public boolean writeReReply(ReplyDTO reply) throws Exception {
		//댓글 저장
		int write = dao.writeReply(reply);
		int update = 0;
		if(write == 1) {
			//ref업데이트 : 기존 댓글의 ref가져와서
			ProdReplyVo parentReply = dao.getReply(reply.getParentRno());
			dao.updateReRef(dao.getLstRno(), parentReply.getRef());
			
			//step, refOrder업데이트
			//step은 기존 댓글의 step + 1
			//refOrder도 기존 댓글의 refOrder + 1
			update = dao.updateStepRO(parentReply, dao.getLstRno());
		}
		if(update == 1) {
			return true;
		}else {
			return false;
		}
	}

//	@Override
//	public List<OrdersVo> getOrder(String userId, String prodNo) {
//		
//		return dao.getOrder(userId, prodNo);
//	}


}
