package com.boritgogae.board.free.dao;

import java.util.List;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;

public interface BoardDao {
	public List<BoardVo> selectList()throws Exception;

}
