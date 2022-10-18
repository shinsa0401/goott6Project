package com.boritgogae.board.question.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.question.domain.BoardQuestionVo;
import com.boritgogae.board.question.etc.SearchCriteriaQuestion;
import com.boritgogae.board.question.etc.UploadFileQuestion;

public interface BoardQuestionService {
	// 게시판 전체목록
	public Map<String, Object> viewAllBoard(int pageNo, SearchCriteriaQuestion sc) throws Exception;
	
	// 게시판 글 작성
	public Map<String, Object> writeBoard(BoardQuestionVo board, List<UploadFileQuestion> uploadFileLst) throws Exception;
	
	// 게시글 보기
	public Map<String, Object> viewBoard(int no) throws Exception;

	// 게시글 수정
	public boolean modifyBoard(BoardQuestionVo board, List<UploadFileQuestion> uploadFileLst) throws Exception;
	
	// 게시글 삭제(삭제여부 업데이트)
	public boolean removeBoard(int no, String pwd) throws Exception;
}
