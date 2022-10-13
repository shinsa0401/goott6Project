package com.boritgogae.board.market.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.domain.Criteria;
import com.boritgogae.board.market.domain.Paging;
import com.boritgogae.board.market.domain.SearchCriteria;
import com.boritgogae.board.market.etc.UploadFile;

public interface BoardService {
	//게시판 글 등록 메서드
	public boolean write(BoardVO board)throws Exception; 
	//게시판 전체 글
	public Map<String, Object> getAllBoard(SearchCriteria sc, Criteria cri)throws Exception; //페이징 아직 안함
	//게시판 글 상세 조회
	public Map<String, Object> viewContent(int no)throws Exception;
	//게시글 수정하기
	public void modify(BoardVO board)throws Exception;
	//게시글 삭제하기
	public void delete(int no)throws Exception;
	//조회수 올리기
	public int readCnt(int no)throws Exception;
}
