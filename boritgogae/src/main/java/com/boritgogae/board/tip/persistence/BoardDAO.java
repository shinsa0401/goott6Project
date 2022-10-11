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

	public int maxBno() throws Exception;

	public int updateRef(int bno) throws Exception;

	public int selectRef(int bno) throws Exception;

	public int updateReplyRef(int maxBno, int ref) throws Exception;

	public int selectRefOrder(int maxNo) throws Exception;

	public int updateReplyRefOrder(int ref, int refOrder) throws Exception;


}
