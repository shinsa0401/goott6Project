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
	
		
		List<BoardVo> lst = ses.selectList(ns+".selectAll");
		System.out.println(lst);
		return lst;
		
		 
	}

	@Override
	public int insertWriter(BoardVo vo) throws Exception {
	
		
		
		return ses.insert(ns+".insertWriter", vo);
		
	}

	@Override
	public BoardVo detail(int bno) throws Exception {
		
		return ses.selectOne(ns+".detail", bno);
	}

	@Override
	public void boardUpdate(BoardVo vo) throws Exception {
		
		ses.update(ns+".boardUpdate", vo);
	}

}
