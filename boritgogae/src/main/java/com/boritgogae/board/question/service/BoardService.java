package com.boritgogae.board.question.service;

import java.util.List;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardService {
	// 게시판 전체목록
	public List<BoardVo> viewAllBoard() throws Exception;
	
	// 게시판 글 작성
	public boolean writeBoard(BoardVo board) throws Exception;
	
	// 게시글 보기
	public BoardVo viewBoard(int no) throws Exception;

	// 게시글 수정
	public boolean modifyBoard(BoardVo board) throws Exception;
}
