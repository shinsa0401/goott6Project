package com.boritgogae.board.market.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.market.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static String ns = "com.boritgogae.boardMarketMapper";
	
	@Inject
	private SqlSession ses;

	@Override
	public int write(BoardVO board) throws Exception {
		
		return ses.insert(ns+".write", board);
	}
	
	
}
