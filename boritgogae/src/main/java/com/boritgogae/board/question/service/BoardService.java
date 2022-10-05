package com.boritgogae.board.question.service;

import java.util.List;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardService {
	// 게시판 전체목록 보기
	public List<BoardVo> viewAllBoard() throws Exception;
	
}
