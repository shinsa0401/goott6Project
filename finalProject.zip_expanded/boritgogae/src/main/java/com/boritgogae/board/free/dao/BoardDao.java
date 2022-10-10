package com.boritgogae.board.free.dao;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.ReplyVo;
import com.boritgogae.board.free.domain.SearchCriterria;

public interface BoardDao {
	
	// 게시판 글 작성
	public int insertWriter(BoardVo vo)throws Exception;
	// 게시판 상세페이지
	public BoardVo detail(int bno)throws Exception;
	
	public void boardUpdate(BoardVo vo)throws Exception;
	
	public int delBoard(int bno)throws Exception; 
	
	public int readCountUp(int bno)throws Exception;
	
	public List<BoardVo> listAllSearch(SearchCriterria sc)throws Exception;
	
	public int listAllSearchCnt(SearchCriterria sc)throws Exception;
	
	public List<BoardVo> listAll(Map map)throws Exception;
	
	public int count() throws Exception;
	
	public int updateReplyCnt(int bno, int cnt)throws Exception;
	
	
	
	
}
