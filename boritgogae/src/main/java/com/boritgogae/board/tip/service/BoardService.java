package com.boritgogae.board.tip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.BoardVo;


public interface BoardService {
	public List<BoardVo> getListBoard() throws Exception;

	public BoardVo getDetail(int bno) throws Exception;

	public boolean addBoard(BoardVo board) throws Exception;

	public boolean delBoard(int bno)  throws Exception;
}
