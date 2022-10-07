package com.boritgogae.board.tip.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.ReplyVo;
import com.boritgogae.board.tip.persistence.BoardReplyDAO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

	@Inject
	private BoardReplyDAO dao;
	
	@Override
	public List<ReplyVo> allReply(int bno) throws Exception {
		
		List<ReplyVo> lst = dao.getAllReply(bno);
		return lst;
	}

	@Override
	public boolean plusReply(int bno, ReplyVo vo) throws Exception {
		boolean result = false;
		int row = dao.insertReply(bno,vo);
		System.out.println(vo+"서비스vo");
		System.out.println(row+"인서트 성공했나?");
		if(row == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public Boolean delReply(int rno) throws Exception {
		boolean result = false;
		int row = dao.deleteReply(rno);
		if (row == 1) {
			result = true;
		}
		return result;
	}

}
