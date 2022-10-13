package com.boritgogae.board.market.dao;

import java.util.List;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.domain.Criteria;
import com.boritgogae.board.market.domain.SearchCriteria;

public interface BoardDAO {
	//글 등록 메서드
	public int write(BoardVO board) throws Exception;
	//게시판 리스트 가져오는 메서드
	public List<BoardVO> getAllBoard()throws Exception;
	//페이징을 위한 게시판 리스트
	public List<BoardVO> getAllBoardWithPaging(Criteria cri)throws Exception;
	//게시판 상세 글 가져오기
	public BoardVO getContent(int no)throws Exception;
	//게시글 수정하기
	public void modify(BoardVO board)throws Exception;
	//게시글 삭제하기
	public void delete(int no)throws Exception;
	//게시글 조회수 올리기
	public int readCnt(int no)throws Exception;
	//검색된 글 (갯수반환,페이징 아직 안함)
	public List<BoardVO> getSearchResult(SearchCriteria sc)throws Exception;
	//전체 글 갯수 구하기
	public int getTotalCnt()throws Exception;
}
