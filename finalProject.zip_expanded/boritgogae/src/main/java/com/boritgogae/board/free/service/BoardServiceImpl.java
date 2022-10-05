package com.boritgogae.board.free.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.boritgogae.board.free.dao.BoardDao;
import com.boritgogae.board.free.domain.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	
	
	@Inject
	BoardDao dao;
	
	public Map<String,Object> boardlist() throws Exception{
		
		
		List<BoardVo> lst = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boardLst", lst);
		System.out.println("서비스"+lst);
		System.out.println(lst);
		return map;
	}
	
	
}
