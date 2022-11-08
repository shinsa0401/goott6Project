package com.boritgogae.board.ask.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.ask.domain.AskReplyVo;
import com.boritgogae.board.ask.persistence.AskBoardDAO;
import com.boritgogae.board.ask.persistence.AskReplyDAO;

@Service
public class AskReplyServiceImpl implements AskReplyService{

	@Inject
	private AskReplyDAO dao;
	
	@Inject
	private AskBoardDAO boardDao;
	
	// @Transactional
	@Override
	public boolean addReply(AskReplyVo reply) throws Exception {
		System.out.println("서비스단 : " + reply.toString());
		
		boolean result = false;
		
		
//		// ref를 주어야 함 어떤 글번호인지 알아야해서 글번호를 보낸다.		
//		String lastRef = dao.getLastReplyRef(reply.getAskBno());
//		System.out.println(lastRef);
//		if(lastRef==null) {
//			lastRef = "0";
//		}
		
		String lastNo = dao.getLastReplyNo(reply.getAskBno());
		System.out.println(lastNo);
		if(lastNo==null) {
			lastNo = "0";
		}
		reply.setRef(Integer.parseInt(lastNo)+1);
		int result1 = dao.insertReply(reply);
		System.out.println("서비스단2 : " + reply.toString());
		
		if(result1 == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public List<AskReplyVo> getEntireReplies(int bno) throws Exception {
		return dao.selectRepliesByBno(bno);
	}

	@Override
	public boolean modifyReply(AskReplyVo reply) throws Exception {
		System.out.println("서비스단 : " + reply.toString());
		boolean result = false;
		int result1 = dao.modifyReply(reply);
		
		if(result1 == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean nestedReply(AskReplyVo reply) throws Exception {
		System.out.println("서비스단 대댓글: " + reply.toString());
		boolean result = false;
		

		// 기존에 같은 ref를 가지고 있던 댓글들의 ref update
		int result2 = dao.updateNestedReply(reply);
		System.out.println("서비스단 대댓글 업뎃결과 : " + result2);
		
		// 대댓글 등록하기
		int result1 = dao.nestedReply(reply);
		System.out.println("서비스단 대댓글 돌아왔나 : " + result1);
		
		// sql_safe_updates 해제
		// dao.safeUpdatesDisable();

		if(result1 == 1) {
			result = true;
		}
		return result;
	}


	@Override
	public boolean deleteReply(AskReplyVo reply) throws Exception {
		System.out.println("서비스단 댓글 삭제 1: " + reply.toString());
		boolean result = false;
		
		
		// 내용을 삭제된 댓글입니다. 로 변경
		reply.setContents("삭제된 댓글입니다.");
		reply.setIsDelete("Y");

		// 댓글 삭제하기
		int result1 = dao.deleteReply(reply);
		System.out.println("서비스단 댓글 삭제 2 : " + result1);
		
		// sql_safe_updates 해제
		// dao.safeUpdatesDisable();

		if(result1 == 1) {
			result = true;
		}
		return result;
	}
}
