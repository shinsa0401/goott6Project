package com.boritgogae.board.question.service;

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

	
//	// 댓글의 댓글
//	@Override
//	public boolean reReply(ReplyVo reply) throws Exception {
//		boolean result = false;
//		
//		// rno로 부모댓글 정보 얻어오기
//		ReplyVo parentReply = dao.getParentReply(reply.getRno());
//		System.out.println("부모댓글의 정보 : " + parentReply.toString());
//		
//		// 저장할 대댓글의 ref, step, refOrder
//		int ref = parentReply.getRef();
//		int step = parentReply.getStep() + 1; // 부모댓글의 step에 + 1
//		int refOrder = parentReply.getRefOrder();
//		
//		// refOrder 업데이트
//		
//		// 부모 댓글의 자식 댓글 개수
//		int reReplyNumCnt = dao.getReReplyNumCnt(ref, step);
//		
//		// 부모 댓글 그룹의 자식 댓글 개수의 합
//		int reReplyNumCntSum = dao.getReReplyNumCntSum(ref);
//        
//        //부모 댓글의 최댓값 step
//        int maxStep = dao.getReplyMaxStep(ref);
//        //SELECT MAX(step) FROM BOARD_COMMENTS WHERE ref = ?1
//        
//        System.out.println("자식댓글수 : " + reReplyNumCnt);
//        System.out.println("maxStep : " + maxStep);
//        System.out.println("그룹내 자식댓글수의 합 : " + reReplyNumCntSum);
//        
//        // 저장할 대댓글 step과 최댓값 step 비교
//        if (step < maxStep) {
//            refOrder = reReplyNumCntSum + 1;
//        } else if (step == maxStep) {
//            dao.updateRefOrder(parentReply);
//            refOrder = refOrder + reReplyNumCnt + 1;
//        } else if (step > maxStep) {
//            dao.updateRefOrder(parentReply);
//            refOrder = refOrder + 1;
//        }
//		
//		// 등록할 댓글의 댓글의 정보를 Map에 담기
//		Map<String, Object> map = new HashMap<>();
//		map.put("ref", ref);
//		map.put("step", step);
//		map.put("refOrder", refOrder);
//		map.put("bno", reply.getBno());
//		map.put("replyWriter", reply.getReplyWriter());
//		map.put("replyContent", reply.getReplyContent());
//		
//		// 댓글의 댓글 작성
//		if (dao.insertReReply(map) == 1) {
//			System.out.println("서비스 댓글의 댓글 등록");
//			// 댓글 등록시 게시글 댓글 수 1증가
//			if (bdao.addReplyCount(reply.getBno()) == 1) {
//				System.out.println("서비스 게시글 댓글수 증가");
//				result = true;
//			}
//			
//		}
//		
//		
//		return result;
//	}
	
	
	

}
