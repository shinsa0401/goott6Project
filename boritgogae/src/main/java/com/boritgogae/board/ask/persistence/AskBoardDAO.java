package com.boritgogae.board.ask.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadFileVo;

@Repository
public interface AskBoardDAO {
	// 게시판 전체 목록 가져오기
	public List<AskBoardVo> selectAllBoard(PagingInfo pi) throws Exception;
	
	// 검색어가 있을 때 페이징을 하며 검색 결과를 가져오는 메서드
	public List<AskBoardVo> getSearchResult(SearchCriteria sc, PagingInfo pi) throws Exception;

	// 검색된 글의 갯수를 반환하는 메서드
	public int getSearchResultCnt(SearchCriteria sc) throws Exception;
	
	// 전체 글의 갯수 반환하는 메서드
	public int getTotalPostCnt() throws Exception;

	// 문의코드와 코드내용을 반환하는 메서드
	public List<AskCodeVo> loadAskCode() throws Exception;

	// 게시판에 글 등록 하는 메서드
	public int insertAskBoard(AskBoardVo board) throws Exception;

	// 최근 등록된 글의 글번호 가져오는 메서드
	public int getLastNo() throws Exception;

	// 최근 등록된 글의 ref 업데이트
	public int updateRef(int lastNo) throws Exception;

	// 업로드된 파일이 이미지인 경우
	public void imageInsert(int lastNo, String savedOriginImageFileName, String thumbnailFileName) throws Exception;

	// 업로드된 파일이 이미지가 아닌 경우
	public void fileInsert(int lastNo, String savedOriginImageFileName) throws Exception;

	// 게시글 조회하기
	public AskBoardVo getBoard(int bno);

	// 게시글 번호에 맞는 첨부파일 조회하기
	public List<UploadFileVo> getAttachFile(int bno);

	// 조회된 글의 조회수 증가
	public int updateReadCount(int bno) throws Exception;


	// 문의코드를 보내고 그에 맞는 문의옵션을 가져오는 메서드 
	public String readAskOptionByAskCode(String askCode);
	
}
