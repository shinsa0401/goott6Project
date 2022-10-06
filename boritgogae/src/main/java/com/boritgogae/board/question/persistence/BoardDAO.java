package com.boritgogae.board.question.persistence;

import java.util.List;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardDAO {
	// 게시판 전체목록을 가져오는 메서드
	public List<BoardVo> selectAllBoard() throws Exception;
	
	// 게시판 글 작성하는 메서드
	public int insertBoard(BoardVo board) throws Exception;
	
	// 최근 등록된 글의 글번호 가져오는 메서드
	public int getLastNo() throws Exception;
	
	// 최근 등록된 글의 ref 업데이트하는 메서드
	public int updateRef(int lastNo) throws Exception;
	
	// 게시판 글 상세 조회 하는 메서드
	public BoardVo getBoard(int no) throws Exception;
	
	// 조회된 글의 조회수 증가하는 메서드
	public int updateReadCount(int no) throws Exception;
	
	// 좋아요수 증가하는 메서드

	// 게시판 글 수정하는 메서드
	public int updateBoard(BoardVo board) throws Exception;
	
	// 게시판 글 삭제(삭제여부 업데이트)하는 메서드
	public int deleteBoard(int no, String pwd) throws Exception;
	
	// 게시글 비밀번호 확인하는 메서드
	public int boardPwdCheck(int no, String pwd) throws Exception;
	
	
}
