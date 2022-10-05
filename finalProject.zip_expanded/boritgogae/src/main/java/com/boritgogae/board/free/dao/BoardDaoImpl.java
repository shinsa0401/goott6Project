package com.boritgogae.board.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.free.domain.BoardVo;


@Repository
public class BoardDaoImpl implements BoardDao {
	
	
	@Inject
	SqlSession ses;
	
	String ns = "com.boritgogae.boardFreeMapper";

	@Override
	public List<BoardVo> selectList() throws Exception {
		
		List<BoardVo> lst = ses.selectOne(ns+".selectAll");
		System.out.println("다오"+lst);
		return lst;
		
		 
	}

}
