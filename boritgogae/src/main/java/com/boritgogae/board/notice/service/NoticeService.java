package com.boritgogae.board.notice.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;

public interface NoticeService {
	// 공지사항 전체 글 가져오는 메서드
	public Map<String, Object> getNoticeBoard(int pageNo) throws Exception;
	
	// 공지사항 글 등록 하는 메서드
	public boolean registerBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 상세 보기 메서드
	public NoticeVo viewBoard(int bno) throws Exception;
	
	// 공지사항 글 수정 하는 메서드
	public boolean modifyBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 삭제 하는 메서드
	public boolean deleteBoard(int bno) throws Exception;
	
	//작성자의 닉네임 가져오는 메서드
	public String getNickName(String memberId) throws Exception;
	
	// 댓글 등록 메서드
	public boolean registerReply(NoticeReplyVo replyBoard) throws Exception;
	
	// 해당 번호의 댓글 가져오는 메서드
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception;
	
	// 댓글 삭제 하는 메서드
	public boolean deleteReplyBoard(int rno) throws Exception;
	
	// 댓글 수정 하는 메서드
	public boolean modifyReplyBoard(NoticeReplyVo board) throws Exception;
}
