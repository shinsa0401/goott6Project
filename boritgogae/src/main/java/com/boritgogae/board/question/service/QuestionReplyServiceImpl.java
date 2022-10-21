package com.boritgogae.board.question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.QuestionReplyVo;
import com.boritgogae.board.question.persistence.QuestionBoardDAO;
import com.boritgogae.board.question.persistence.QuestionReplyDAO;

@Service
public class QuestionReplyServiceImpl implements QuestionReplyService {

	@Inject
	private QuestionReplyDAO dao;
	
	@Inject QuestionBoardDAO bdao;
	
	// 댓글 등록
	@Override
	public boolean addReply(QuestionReplyVo reply) throws Exception {
		boolean result = false;
		int lastRno = 0;
		
		if (dao.insertReply(reply) == 1) {
			System.out.println("서비스 댓글 등록");
			// 방금 등록된 댓글번호 얻어오기
			lastRno = dao.getLastRno();
			
			// 댓글번호(rno)로 ref 업데이트
			if (dao.updateReplyRef(lastRno) == 1) {
				System.out.println("ref업데이트 성공");
				
				// 댓글 등록시 게시글 댓글 수 1증가
				if (bdao.addReplyCount(reply.getBno()) == 1) {
					System.out.println("서비스 게시글 댓글수 증가");
					result = true;
				}
			}
			
		}
		
		return result;
	}

	// 모든 댓글 얻어오기
	@Override
	public List<QuestionReplyVo> getAllReply(int bno) throws Exception {
		
		return dao.selectAllReply(bno);
	}

	// 댓글 수정
	@Override
	public boolean modifyReply(QuestionReplyVo reply) throws Exception {
		boolean result = false;
		
		if (dao.updateReply(reply) == 1) {
			System.out.println("서비스단 댓글 수정 성공");
			result = true;
		}
		
		return result;
	}

	// 댓글 삭제
	@Override
	public boolean removeReply(QuestionReplyVo reply) throws Exception {
		boolean result = false;
		
		if (dao.deleteReply(reply) == 1) {
			System.out.println("서비스단 댓글 삭제");
			
			// 댓글 삭제시 하위댓글 모두 삭제-----------------미완
			
			// 댓글 삭제시 게시글 댓글 수 1감소
			if (bdao.subReplyCount(reply.getBno()) == 1) {
				System.out.println("서비스단 게시글 댓글수 감소");
				result = true;
			}
		}
		
		return result;
	}

	// 댓글의 댓글
	@Override
	public boolean reReply(QuestionReplyVo reply) throws Exception {
		boolean result = false;
		// rno로 부모댓글 정보 얻어오기
		QuestionReplyVo parentReply = dao.getParentReply(reply.getRno());
		
		// ref 업데이트
		dao.updateRefOrder(parentReply);
		
		int step = parentReply.getStep();
		int refOrder = parentReply.getRefOrder();
		
		Map<String, Object> map = new HashMap<>();
		map.put("ref", parentReply.getRef());
		map.put("step", step);
		map.put("refOrder", refOrder);
		map.put("bno", reply.getBno());
		map.put("replyWriter", reply.getReplyWriter());
		map.put("replyContent", reply.getReplyContent());
		
		// 댓글의 댓글 작성
		if (dao.insertReReply(map) == 1) {
			System.out.println("서비스 댓글의 댓글 등록");
			// 댓글 등록시 게시글 댓글 수 1증가
			if (bdao.addReplyCount(reply.getBno()) == 1) {
				System.out.println("서비스 게시글 댓글수 증가");
				result = true;
			}
			
		}
		
		
		return result;
	}

	
	
	
	

}
