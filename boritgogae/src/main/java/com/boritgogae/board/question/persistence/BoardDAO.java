package com.boritgogae.board.question.persistence;

import java.util.List;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardDAO {
	// 게시판 전체목록 보기
	public List<BoardVo> viewAllBoard() throws Exception;
	
	// 게시판 글 작성
	public int writeBoard(BoardVo board) throws Exception;
	
	// 게시판 글 상세 보기
	public BoardVo getBoard(int no) throws Exception;

	// 게시판 글 수정
	public int modifyBoard(BoardVo board) throws Exception;
	
}
