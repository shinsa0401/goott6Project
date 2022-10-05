package com.boritgogae.board.market.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.etc.UploadFile;

public interface BoardService {
	//게시판 글 등록 메서드
	public boolean write(BoardVO board)throws Exception; 
	//게시판 전체 글
	public Map<String, Object> getAllBoard()throws Exception; //페이징 아직 안함
	//게시판 글 상세 조회
	public Map<String, Object> viewContent(int no)throws Exception;
}
