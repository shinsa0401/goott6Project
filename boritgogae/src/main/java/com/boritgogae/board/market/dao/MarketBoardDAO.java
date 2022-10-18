package com.boritgogae.board.market.dao;

import java.util.List;

import com.boritgogae.board.market.domain.MarketBoardVO;
import com.boritgogae.board.market.domain.MarketPaging;
import com.boritgogae.board.market.domain.MarketSearchCriteria;
import com.boritgogae.board.market.domain.MarketUploadFileVo;

public interface MarketBoardDAO {
	//글 등록 메서드
	public int write(MarketBoardVO board) throws Exception;
	
	//최근 등록된 글 번호 
	public int getLastNo() throws Exception;
	//ref업데이트
	public int updateRef(int lastNo)throws Exception;
	//업로드된 파일이 이미지인 경우
	public void imageInsert(int lastNo,String savedOriginImageFileName, String thumbnailFileName)throws Exception;
	//업로드된 파일이 이미지가 아닌 경우
	public void fileInsert(int lastNo,String savedOriginImageFileName)throws Exception;
	// 게시글 첨부파일 조회
	public List<MarketUploadFileVo> getAttachFile(int no) throws Exception;
	
	//게시판 리스트 가져오는 메서드
	public List<MarketBoardVO> getAllBoard(MarketPaging pi)throws Exception;
	//게시판 상세 글 가져오기
	public MarketBoardVO getContent(int no)throws Exception;
	
	//게시글 수정하기
	public void modify(MarketBoardVO board)throws Exception;
	//게시글 삭제하기
	public void delete(int no)throws Exception;
	//게시글 조회수 올리기
	public int readCnt(int no)throws Exception;
	
	//검색된 글 페이징하며 검색 결과
	public List<MarketBoardVO> getSearchResult(MarketPaging pi, MarketSearchCriteria sc)throws Exception;
	//검색된 글 갯수
	public int getSearchResultCnt(MarketSearchCriteria sc)throws Exception;
	//전체 글 갯수 구하기
	public int getTotalCnt()throws Exception;
}
