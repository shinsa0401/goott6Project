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
		
		
		List<BoardVo> lst = dao.selectList();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boardLst", lst);
		System.out.println("서비스"+lst);
		
		return map;
	}

	@Override
	public boolean insertBoard(BoardVo vo) throws Exception {
		boolean result = false;
		int row =dao.insertWriter(vo);
		
		if(row==1) {
			result = true;
		}
		return result;
		
	}

	@Override
	public Map<String, Object> detailBoard(int bno) throws Exception {
		
		BoardVo board = dao.detail(bno);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("board", board);
		System.out.println(board);
		return map;
	}

	@Override
	public void boardUpdate(BoardVo vo) throws Exception {
		dao.boardUpdate(vo);
		
	}
	
	
}
