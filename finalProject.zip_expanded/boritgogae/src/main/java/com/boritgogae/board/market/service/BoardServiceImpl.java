package com.boritgogae.board.market.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.market.dao.BoardDAO;
import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.etc.UploadFile;
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject 
	private BoardDAO dao;
	
	@Override
	public boolean write(BoardVO board) throws Exception {
		boolean result = true; //임시
		
		int row = dao.write(board);
		
		return result;
	
	}

	@Override
	public Map<String, Object> getAllBoard() throws Exception { //페이징, 검색어 아직 안함
		List<BoardVO>List = dao.getAllBoard();
		
		Map<String, Object> ListMap = new HashMap<String, Object>();
		ListMap.put("boardList", List);
		
		return ListMap;
	}
	
	//이미지파일, 조회수 안함
	@Override
	public Map<String, Object> viewContent(int no) throws Exception {
		BoardVO board = dao.getContent(no);
		
		Map<String, Object> ListMap = new HashMap<String, Object>();
		ListMap.put("board", board);
		
		return ListMap;
	}

}
