package com.boritgogae.board.market.service;

import java.util.List;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.etc.UploadFile;

public interface BoardService {
	
	public boolean write(BoardVO board)throws Exception; 
}
