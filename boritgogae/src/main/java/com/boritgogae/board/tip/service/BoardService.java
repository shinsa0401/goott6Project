package com.boritgogae.board.tip.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.BoardVo;


public interface BoardService {
	public Map<String, Object> getListBoard(int pageNo) throws Exception;

	public BoardVo getDetail(int bno) throws Exception;

	public boolean addBoard(BoardVo board) throws Exception;

	public boolean delBoard(int bno) throws Exception;

	public BoardVo modiBoard(int bno, BoardVo vo) throws Exception;

	public boolean addReadCnt(int bno) throws Exception;
}
