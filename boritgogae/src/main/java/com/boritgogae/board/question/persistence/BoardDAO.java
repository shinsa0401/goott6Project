package com.boritgogae.board.question.persistence;

import java.util.List;

import com.boritgogae.board.question.domain.BoardVo;

public interface BoardDAO {
	// 게시판 전체목록 보기
	public List<BoardVo> viewAllBoard() throws Exception;
	
	
}
