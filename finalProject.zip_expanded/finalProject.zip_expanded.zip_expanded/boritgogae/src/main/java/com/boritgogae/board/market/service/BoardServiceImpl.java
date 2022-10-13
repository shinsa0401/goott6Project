package com.boritgogae.board.market.service;

import java.util.List;

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

}
