package com.boritgogae.board.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boritgogae.board.free.dao.BoardDao;
import com.boritgogae.board.free.dao.ReplyDao;
import com.boritgogae.board.free.domain.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	
	@Inject
	ReplyDao dao;
	
	@Inject
	BoardDao bdao;

	@Override
	public int getCount(int bno) throws Exception {
		System.out.println("bno"+bno);
		return dao.countReply(bno);
	}

	@Override
	//@Transactional(rollbackFor = Exception.class)
	public int remove(int rno, int bno, String replyer) throws Exception {
		  
	       // int rowCnt = bdao.updateReplyCnt(bno, -1);
	        
	        //rowCnt =dao.deleteReply(rno, replyer);
        
	        return dao.deleteReply(rno, replyer);
	        }

	@Override
	public int write(ReplyVo rv) throws Exception {
		return dao.insertReply(rv);
	}

	@Override
	public List<ReplyVo> getList(int bno) throws Exception {
		
		return dao.selectAllReply(bno);
	}

	@Override
	public ReplyVo read(int rno) throws Exception {
	
		return dao.selectReply(rno);
	}

	@Override
	public int modify(ReplyVo rv) throws Exception {
		
		return dao.updateReply(rv);
	}
	
	

}
