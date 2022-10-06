package com.boritgogae.board.free.service;



import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.BoardVo;





@Service
public interface BoardService {
	
	public Map<String,Object> boardlist() throws Exception;
	
	public boolean insertBoard(BoardVo vo)throws Exception;
	
	public Map<String,Object> detailBoard(int bno)throws Exception;

	public void boardUpdate(BoardVo vo)throws Exception;
	
}
