package com.boritgogae.board.tip.persistence;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.tip.domain.TipBoardVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;

public interface BoardDAO {
	public List<TipBoardVo> selectAllBoard(TipPagingInfo pi) throws Exception;

	public TipBoardVo selectDetail(int bno) throws Exception;

	public int insertBoard(TipBoardVo board) throws Exception;

	public int deleteBoard(int bno) throws Exception;

	public int updateBoard(int bno, TipBoardVo vo) throws Exception;

	public int plusReadCnt(int bno) throws Exception;

	public int getTotalPostCnt() throws Exception;

	public int maxBno() throws Exception;

	public int updateRef(int bno) throws Exception;

	public int selectRef(int bno) throws Exception;

	public int updateReplyRef(int maxBno, int ref) throws Exception;

	public int selectRefOrder(int maxNo) throws Exception;

	public int updateReplyRefOrder(int cntRef,  int maxNo) throws Exception;

	public int countRef(int ref) throws Exception;

	public int selectMin(int ref) throws Exception;

	public int stepNum(int bno) throws Exception;

	public int updateReplyRefOrder(int cntRef, int maxNo, int step) throws Exception;


}
