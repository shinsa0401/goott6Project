package com.boritgogae.board.market.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.market.domain.MarketBoardVO;
import com.boritgogae.board.market.domain.MarketPaging;
import com.boritgogae.board.market.domain.MarketSearchCriteria;
import com.boritgogae.board.market.etc.MarketUploadFile;

public interface MarketBoardService {
	//게시판 글 등록 메서드
	public boolean write(MarketBoardVO board, List<MarketUploadFile> uploadFileLst)throws Exception; 
	
	//게시판 전체 글
	public Map<String, Object> getAllBoard(int pageNo,MarketSearchCriteria sc )throws Exception; 
	//게시판 글 상세 조회
	public Map<String, Object> viewContent(int no)throws Exception;
	//게시글 수정하기
	public void modify(MarketBoardVO board)throws Exception;
	//게시글 삭제하기
	public void delete(int no)throws Exception;
	//조회수 올리기
	public int readCnt(int no)throws Exception;
}
