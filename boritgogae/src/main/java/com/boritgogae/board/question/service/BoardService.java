package com.boritgogae.board.question.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardService {
	// 게시판 전체목록
	public List<BoardVo> viewAllBoard() throws Exception;
	
	// 게시판 글 작성
	public Map<String, Object> writeBoard(BoardVo board) throws Exception;
	
	// 게시글 보기
	public BoardVo viewBoard(int no) throws Exception;

	// 게시글 수정
	public boolean modifyBoard(BoardVo board) throws Exception;
	
	// 게시글 삭제(삭제여부 업데이트)
	public boolean removeBoard(int no, String pwd) throws Exception;
}
