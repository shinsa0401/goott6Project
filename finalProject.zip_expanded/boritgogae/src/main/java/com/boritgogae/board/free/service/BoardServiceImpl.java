package com.boritgogae.board.free.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.boritgogae.board.free.dao.BoardDao;
import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.SearchCriterria;

@Service
public class BoardServiceImpl implements BoardService {

	
	
	@Inject
	BoardDao dao;
	
	

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

	@Override
	public void delBoard(int bno) throws Exception {
		dao.delBoard(bno);
		
	}

	@Override
	public void readCountUp( int bno) throws Exception {
		dao.readCountUp(bno);
	}

	@Override
	public List<BoardVo> listAll(Map map) throws Exception {
		
		
		return dao.listAll(map);
	}

	@Override
	public int getCount() throws Exception {
		
		return dao.count();
	}

	@Override
	public List<BoardVo> listAllSearch(SearchCriterria sc) throws Exception {
		
		return dao.listAllSearch(sc);
	}

	@Override
	public int listAllSearchCnt(SearchCriterria sc) throws Exception {
		// TODO Auto-generated method stub
		return dao.listAllSearchCnt(sc);
	}
	
	
	
	
}
