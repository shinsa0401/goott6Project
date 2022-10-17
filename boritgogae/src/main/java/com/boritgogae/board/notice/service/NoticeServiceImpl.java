package com.boritgogae.board.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	private NoticeDAO dao;
	
	@Override
	public List<NoticeVo> getNoticeBoard() throws Exception {
		System.out.println("noticeService");
		return dao.getNoticeBoard();
		
	}

	@Override
	public boolean registerBoard(NoticeVo board) throws Exception {
		boolean result = false;
		
		
		if(dao.registerBoard(board) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public NoticeVo viewBoard(int bno) throws Exception {
		NoticeVo board = dao.viewBoard(bno);
		
		return board;
	}

	@Override
	public boolean deleteBoard(int bno) throws Exception {
		boolean result = false;
		
		if (dao.deleteBoard(bno) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modifyBoard(NoticeVo board) throws Exception {
		boolean result = false;
		if(dao.modifyBoard(board) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean registerReply(NoticeReplyVo replyBoard) throws Exception {
		boolean result = false;
		if(dao.registerReply(replyBoard) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception {
		return dao.getReplyList(bno);
	}

	@Override
	public boolean deleteReplyBoard(int rno) throws Exception {
boolean result = false;
		
		if (dao.deleteReplyBoard(rno) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public String getNickName(String memberId) throws Exception {
		return dao.getNickName(memberId);
	}

	@Override
	public boolean modifyReplyBoard(NoticeReplyVo board) throws Exception {
		boolean result = false;
		
		if (dao.modifyReplyBoard(board) == 1) {
			result = true;
		}
		
		return result;
	}
	
}
