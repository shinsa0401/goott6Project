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
		
		
		// ref를 주어야 함 어떤 글번호인지 알아야해서 글번호를 보낸다.		
		String lastRef = dao.getLastReplyRef(reply.getAskBno());
		System.out.println(lastRef);
		if(lastRef==null) {
			lastRef = "0";
		}
		reply.setRef(Integer.parseInt(lastRef)+1);
		int result1 = dao.insertReply(reply);
		System.out.println("서비스단2 : " + reply.toString());
		
		if(result1 == 1) {
			result = true;
		}
		return result;
		
	}

	@Override
	public List<AskReplyVo> getEntireReplies(int bno) {
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


}
