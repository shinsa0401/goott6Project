package com.boritgogae.board.question.persistence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.boritgogae.board.question.domain.QuestionBoardVo;
import com.boritgogae.board.question.domain.QuestionReadCountVo;
import com.boritgogae.board.question.domain.QuestionUploadFileVo;
import com.boritgogae.board.question.etc.PagingInfo;
import com.boritgogae.board.question.etc.SearchCriteria;

public interface BoardDAO {
	// 게시판 전체목록을 가져오는 메서드
	public List<QuestionBoardVo> selectAllBoard(PagingInfo pi) throws Exception;
	
	// 게시판 글 작성하는 메서드
	public int insertBoard(QuestionBoardVo board) throws Exception;
	
	// 최근 등록된 글의 글번호 가져오는 메서드
	public int getLastNo() throws Exception;
	
	// 최근 등록된 글의 ref 업데이트하는 메서드
	public int updateRef(int lastNo) throws Exception;
	
	// 업로드된 이미지 파일 저장
	public void insertImg(int lastNo, String savedOriginImageFileName, String thumbnailFileName);

	// 업로드된 이미지가 아닌 파일 저장
	public void insertFile(int lastNo, String savedOriginImageFileName);
	
	// 글번호로 상세 조회 하는 메서드
	public QuestionBoardVo getBoard(int no) throws Exception;
	
	// 글번호로 첨부파일 조회하는 메서드
	public List<QuestionUploadFileVo> getAttachFiles(int no) throws Exception;
	
	// 게시글 조회 시간 검색하는 메서드
	public QuestionReadCountVo getLastReadDate(Map<String, Object> readCount) throws Exception;
	
	// 조회수 처리를 위한 ip주소, 글번호, 현재시간을 insert 하는 메서드
	public int insertReadCount(Map<String, Object> readCount) throws Exception;
	
	// 조회수 처리를 위한 ip주소, 글번호, 현재시간으로 update 하는 메서드
	public int updateReadCount(Map<String, Object> readCount) throws Exception;
	
	// 조회된 글의 조회수 증가하는 메서드
	public int updateReadCountBoard(int no) throws Exception;

	// 게시판 글 수정하는 메서드
	public int updateBoard(QuestionBoardVo board) throws Exception;
	
	// 게시판 글 삭제(삭제여부 업데이트)하는 메서드
	public int deleteBoard(int no, String pwd) throws Exception;
	
	// 게시글 비밀번호 확인하는 메서드
	public int boardPwdCheck(int no, String pwd) throws Exception;
	
	// 댓글 등록시 replyCount 증가하는 메서드
	public int addReplyCount(int bno) throws Exception;

	// 댓글 삭제시 replyCount 감소하는 메서드
	public int subReplyCount(int bno) throws Exception;

	// 전체 글의 개수 반환하는 메서드
	public int getTotalPostCnt() throws Exception;

	// 검색된 글의 개수 반환하는 메서드
	public int getSearchResultCnt(SearchCriteria sc) throws Exception;
		
	// 검색어가 있을 때 페이징 하며 검색 결과를 가져 오는 메서드
	public List<QuestionBoardVo> getSearchResult(PagingInfo pi, SearchCriteria sc) throws Exception;
	
}
