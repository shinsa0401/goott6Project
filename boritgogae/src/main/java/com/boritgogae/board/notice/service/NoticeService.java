package com.boritgogae.board.notice.service;

import java.util.List;

import com.boritgogae.board.notice.domain.NoticeVo;

public interface NoticeService {
	// 공지사항 전체 글 가져오는 메서드
	public List<NoticeVo> getNoticeBoard() throws Exception;
	
	// 공지사항 글 등록 하는 메서드
	public boolean registerBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 수정 하는 메서드
	
	
	// 공지사항 글 삭제 하는 메서드
}
