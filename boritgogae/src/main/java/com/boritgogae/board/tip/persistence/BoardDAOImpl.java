package com.boritgogae.board.tip.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.tip.domain.BoardVo;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.tipBoardMapper";
	
	@Override
	public List<BoardVo> selectAllBoard() throws Exception {
		System.out.println("DAOImpl" + ses.toString());
		return ses.selectList(ns+".listAll");
	}

	@Override
	public BoardVo selectDetail(int bno) throws Exception {
		return ses.selectOne(ns+".detail",bno);
	}

}
