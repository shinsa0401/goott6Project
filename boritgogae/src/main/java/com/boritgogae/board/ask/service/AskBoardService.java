package com.boritgogae.board.ask.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.etc.UploadFile;

@Service
public interface AskBoardService {
	// 게시판 전체 목록을 가져오는 메서드
	public Map<String, Object> readAllBoard(int pageNo, SearchCriteria sc) throws Exception;

	// 질문코드와 질문코드에 따른 분류를 가져오는 메서드
	public List<AskCodeVo> loadAskCode() throws Exception;

	// 게시판 글 등록 + (업로드된 파일 등록) 하는 메서드
	public boolean create(AskBoardVo board, List<UploadFile> uploadFileLst) throws Exception;

	// 게시판 조회
	public Map<String, Object> viewBoard(int bno, String clientIp) throws Exception;

	// 문의코드를 보내고 그에 맞는 문의옵션을 가져오는 메서드 
	public String readAskOptionByAskCode(String askCode) throws Exception;

	// 글번호에 따른 조회수를 가져오는 메서드
	public int getReadCountByBno(int askBno) throws Exception;
	
	
}
