package com.boritgogae.board.market.dao;

import com.boritgogae.board.market.domain.BoardVO;

public interface BoardDAO {
	//글 등록 메서드
	public int write(BoardVO board) throws Exception;
}
