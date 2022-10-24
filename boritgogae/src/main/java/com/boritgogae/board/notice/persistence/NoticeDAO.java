package com.boritgogae.board.notice.persistence;

import java.sql.Timestamp;
import java.util.List;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.etc.PagingInfo;

public interface NoticeDAO {
	// 공지사항 전체 글 가져오는 메서드
	public List<NoticeVo> getNoticeBoard(PagingInfo pi) throws Exception;
	
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
	
	// 공지사항 전체 글 갯수 가져오기
	public int getNoticeBoardCnt()throws Exception;
	
	// 댓글 등록 메서드
	public int registerReply(NoticeReplyVo replyBoard) throws Exception;
	
	public int registerReplyRepl(NoticeReplyVo replyBoard) throws Exception;
	
	// 해당 글의 댓글 가져오는 메서드
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception;
	
	// 댓글 삭제 하는 메서드
	public int deleteReplyBoard(int rno) throws Exception;
	
	// 댓글 수정하는 메서드
	public int modifyReplyBoard(NoticeReplyVo board) throws Exception;
	
	// 최근 등록된 글의 글번호 가져오는 메서드
	public int getLastNo() throws Exception;

	// 최근 등록된 글의 ref 수정
	public int updateRef(int lastNo) throws Exception;
	
	// 대댓글 ref
	public int updateRefFromRno(int ref, int lastNo) throws Exception;
	
	// 최근 등록된 글의 refOrder 수정
	public int updateRefOrder(NoticeReplyVo replyBoard) throws Exception;
	
	// 조회된 글의 조회수 증가
	public int updateReadCount(int bno) throws Exception;

	// 대댓글의 부모글의 refOrder를 가져오는 메서드
	public int getRefOrder(int lastNo) throws Exception;
	
	// 조회수 중복 방지를 위한 조회 체크
	public Timestamp getLastReadTime(String readMemberIp, int bno) throws Exception;
	
	// 조회 테이블에 insert
	public int insertNoticeBoardRead(String readMemberIp, int bno) throws Exception;
	
	// 조회 테이블 update
	public int updateNoticeBoardRead(String readMemberIp, int bno,Timestamp nowTime) throws Exception;
	
	public List<NoticeVo> getNoticeBoarMain() throws Exception;
}
