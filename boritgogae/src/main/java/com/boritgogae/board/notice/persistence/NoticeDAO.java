package com.boritgogae.board.notice.persistence;

import java.util.List;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;

public interface NoticeDAO {
	// 공지사항 전체 글 가져오는 메서드
	public List<NoticeVo> getNoticeBoard() throws Exception;
	
	// 공지사항 글 작성하는 메서드
	public int registerBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 상세 보기 메서드
	public NoticeVo viewBoard(int bno) throws Exception;
	
	// 공지사항 글 수정 하는 메서드
	public int modifyBoard(NoticeVo board) throws Exception;
	
	// 공지사항 글 삭제 하는 메서드
	public int deleteBoard(int bno) throws Exception;
	
	//작성자의 닉네임 가져오는 메서드
	public String getNickName(String memberId) throws Exception;
	
	// 댓글 등록 메서드
	public int registerReply(NoticeReplyVo replyBoard) throws Exception;
	
	// 해당 글의 댓글 가져오는 메서드
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception;
	
	// 댓글 삭제 하는 메서드
	public int deleteReplyBoard(int rno) throws Exception;
	
	// 댓글 수정하는 메서드
	public int modifyReplyBoard(NoticeReplyVo board) throws Exception;
}
