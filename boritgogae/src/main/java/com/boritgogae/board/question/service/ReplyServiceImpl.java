package com.boritgogae.board.question.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.ReplyVo;
import com.boritgogae.board.question.persistence.BoardDAO;
import com.boritgogae.board.question.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Inject BoardDAO bdao;
	
	// 댓글 등록
	@Override
	public boolean addReply(ReplyVo reply) throws Exception {
		boolean result = false;
		
		if (dao.insertReply(reply) == 1) {
			System.out.println("서비스 댓글 등록");
			
			// 댓글 등록시 게시글 댓글 수 1증가
			if (bdao.addReplyCount(reply.getBno()) == 1) {
				System.out.println("서비스 게시글 댓글수 증가");
				result = true;
			}
		}
		
		return result;
	}

	// 모든 댓글 얻어오기
	@Override
	public List<ReplyVo> getAllReply(int bno) throws Exception {
		
		return dao.selectAllReply(bno);
	}

	// 댓글 수정
	@Override
	public boolean modifyReply(ReplyVo reply) throws Exception {
		boolean result = false;
		
		if (dao.updateReply(reply) == 1) {
			System.out.println("서비스단 댓글 수정 성공");
			result = true;
		}
		
		return result;
	}

	// 댓글 삭제
	@Override
	public boolean removeReply(ReplyVo reply) throws Exception {
		boolean result = false;
		
		if (dao.deleteReply(reply) == 1) {
			System.out.println("서비스단 댓글 삭제");
			
			// 댓글 삭제시 게시글 댓글 수 1감소
			if (bdao.subReplyCount(reply.getBno()) == 1) {
				System.out.println("서비스단 게시글 댓글수 감소");
				result = true;
			}
		}
		
		return result;
	}

	
	
	

}
