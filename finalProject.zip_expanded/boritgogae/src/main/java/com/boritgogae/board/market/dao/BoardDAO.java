package com.boritgogae.board.market.dao;

import java.util.List;

import com.boritgogae.board.market.domain.BoardVO;

public interface BoardDAO {
	//글 등록 메서드
	public int write(BoardVO board) throws Exception;
	//게시판 리스트 가져오는 메서드
	public List<BoardVO> getAllBoard()throws Exception;
	//게시판 상세 글 가져오기
	public BoardVO getContent(int no)throws Exception;
}
