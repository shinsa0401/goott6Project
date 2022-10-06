package com.boritgogae.board.free.dao;

import java.util.List;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;

public interface BoardDao {
	// 게시판 리스트 가져오기
	public List<BoardVo> selectList()throws Exception;
	// 게시판 글 작성
	public int insertWriter(BoardVo vo)throws Exception;
	// 게시판 상세페이지
	public BoardVo detail(int bno)throws Exception;
	
	public void boardUpdate(BoardVo vo)throws Exception;
	
}
