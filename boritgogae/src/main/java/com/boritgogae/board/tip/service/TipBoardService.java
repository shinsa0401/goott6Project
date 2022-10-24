package com.boritgogae.board.tip.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.TipBoardVo;
import com.boritgogae.board.tip.domain.TipReplyVo;


public interface TipBoardService {
	public Map<String, Object> getListBoard(int pageNo) throws Exception;

	public TipBoardVo getDetail(int bno) throws Exception;

	public boolean addBoard(TipBoardVo board) throws Exception;

	public boolean delBoard(int bno) throws Exception;

	public TipBoardVo modiBoard(int bno, TipBoardVo vo) throws Exception;

	public boolean addReadCnt(int bno) throws Exception;

	public boolean addReplyBoard(TipBoardVo vo, int bno) throws Exception;

	public List<TipBoardVo> miniBoard() throws Exception;
}
