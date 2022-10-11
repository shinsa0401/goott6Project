package com.boritgogae.board.question.service;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 댓글의 댓글
	@Override
	public boolean reReply(ReplyVo reply) throws Exception {
		boolean result = false;
		// rno로 부모댓글 정보 얻어오기
		ReplyVo parentReply = dao.getParentReply(reply.getRno());
		// 해당 글의 댓글의 맥스 refOrder 얻어오기
		int maxRefOrder = dao.getMaxRefOrder(reply.getBno()) + 1;
		System.out.println("서비스 맥스refOrder : " + maxRefOrder);
		int pStep = parentReply.getStep() + 1;
		System.out.println("서비스 부모 스텝 : " + pStep);
		Map<String, Object> map = new HashMap<>();
		map.put("ref", parentReply.getRef());
		map.put("step", pStep);
		map.put("refOrder", maxRefOrder);
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

	
	
	

=======
public class ReplyServiceImpl implements ReplyService {

>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
}
