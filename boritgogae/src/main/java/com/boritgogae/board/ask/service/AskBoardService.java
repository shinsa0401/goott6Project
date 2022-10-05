package com.boritgogae.board.ask.service;

import java.util.Map;

import com.boritgogae.board.ask.domain.SearchCriteria;

public interface AskBoardService {
	// 게시판 전체 목록을 가져오는 메서드
	public Map<String, Object> readAllBoard(int pageNo, SearchCriteria sc) throws Exception;
	
}
