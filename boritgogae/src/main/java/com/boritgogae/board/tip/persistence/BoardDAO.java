package com.boritgogae.board.tip.persistence;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.domain.PagingInfo;

public interface BoardDAO {
	public List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception;

	public BoardVo selectDetail(int bno) throws Exception;

	public int insertBoard(BoardVo board) throws Exception;

	public int deleteBoard(int bno) throws Exception;

	public int updateBoard(int bno, BoardVo vo) throws Exception;

	public int plusReadCnt(int bno) throws Exception;

	public int getTotalPostCnt() throws Exception;

}
