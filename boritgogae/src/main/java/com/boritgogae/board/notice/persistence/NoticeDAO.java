package com.boritgogae.board.notice.persistence;

import java.util.List;

import com.boritgogae.board.notice.domain.NoticeVo;

public interface NoticeDAO {
	// 공지사항 전체 글 가져오는 메서드
	public List<NoticeVo> getNoticeBoard() throws Exception;
	
	// 공지사항 글 작성하는 메서드
	public int registerBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 상세 보기 메서드
	public NoticeVo viewBoard(int bno) throws Exception;
	
	// 공지사항 글 수정 하는 메서드
	
	
	// 공지사항 글 삭제 하는 메서드
	public int deleteBoard(int bno) throws Exception;
}
