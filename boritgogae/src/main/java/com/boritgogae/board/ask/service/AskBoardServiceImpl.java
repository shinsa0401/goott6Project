package com.boritgogae.board.ask.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.persistence.AskBoardDAO;

public class AskBoardServiceImpl implements AskBoardService {

	@Inject
	public AskBoardDAO dao;
		
	@Override
	public Map<String, Object> readAllBoard(int pageNo, SearchCriteria sc) throws Exception {
		System.out.println("서비스단 : 게시판 전체 목록 요청");
		
		PagingInfo pi = pagingProcess(pageNo, sc);
		
		List<AskBoardVo> lst = null;
		if(sc.getSearchWord() != null && sc.getSearchType().equals("")) { // 검색어가 있다면
			lst = dao.getSearchResult(sc, pi);
		} else {
			lst = dao.selectAllBoard(pi);
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardList", lst);
		resultMap.put("pagingInfo", pi);
		
		
		return resultMap;
		
	}
	
	// pagingProgress 해야함
	// DB 업뎃 해야함

}
