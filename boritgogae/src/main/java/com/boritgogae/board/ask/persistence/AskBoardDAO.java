package com.boritgogae.board.ask.persistence;

import java.util.List;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.PagingInfo;

public interface AskBoardDAO {
	// 게시판 전체 목록 가져오기
	public List<AskBoardVo> selectAllBoard(PagingInfo pi) throws Exception;

}
