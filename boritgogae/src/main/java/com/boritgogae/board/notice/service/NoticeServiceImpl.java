package com.boritgogae.board.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
		board.setContent(board.getContent().replace("\r\n", "<br />"));
		
		
		if(dao.registerBoard(board) == 1) {
			result = true;
		}
		
		return result;
	}
	
}
