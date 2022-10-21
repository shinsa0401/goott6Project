package com.boritgogae.board.question.service;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.question.domain.QuestionBoardVo;
import com.boritgogae.board.question.etc.SearchCriteria;
import com.boritgogae.board.question.etc.UploadFile;

public interface BoardService {
	// 게시판 전체목록
	public Map<String, Object> viewAllBoard(int pageNo, SearchCriteria sc) throws Exception;
	
	// 게시판 글 작성
	public Map<String, Object> writeBoard(QuestionBoardVo board, List<UploadFile> uploadFileLst) throws Exception;
	
	// 게시글 보기
	public Map<String, Object> viewBoard(int no) throws Exception;

	// 게시글 수정
	public boolean modifyBoard(QuestionBoardVo board, List<UploadFile> uploadFileLst) throws Exception;
	
	// 게시글 삭제(삭제여부 업데이트)
	public boolean removeBoard(int no, String pwd) throws Exception;
}
