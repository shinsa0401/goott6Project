package com.boritgogae.board.tip.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.TipReplyVo;
import com.boritgogae.board.tip.persistence.TipBoardReplyDAO;

@Service
public class TipBoardReplyServiceImpl implements TipBoardReplyService {

	@Inject
	private TipBoardReplyDAO dao;
	
	@Override
	public List<TipReplyVo> allReply(int bno) throws Exception {
		
		List<TipReplyVo> lst = dao.getAllReply(bno);
		return lst;
	}

	@Override
	public boolean plusReply(int bno, TipReplyVo vo) throws Exception {
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

	@Override
	public Boolean updateReply(int rno, TipReplyVo vo) throws Exception {
		boolean result = false;
		int row = dao.modiReply(rno,vo);
		if(row == 1) {
			result = true;
		}
		
		return result;
	}

}
