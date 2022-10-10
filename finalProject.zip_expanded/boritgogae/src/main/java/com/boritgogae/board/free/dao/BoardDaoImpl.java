package com.boritgogae.board.free.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.ReplyVo;
import com.boritgogae.board.free.domain.SearchCriterria;


@Repository
public class BoardDaoImpl implements BoardDao {
	
	
	@Inject
	SqlSession ses;
	
	String ns = "com.boritgogae.boardFreeMapper";

	

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

	@Override
	public int delBoard(int bno) throws Exception {
		
		return ses.delete(ns+".deleteBoard", bno);
	}

	@Override
	public int readCountUp(int bno) throws Exception {
		
		return ses.update(ns+".readCountUp",bno);
		
	}

	@Override
	public List<BoardVo> listAll(Map map) throws Exception {
		
		return ses.selectList(ns+".listAll", map);
	}

	@Override
	public int count() throws Exception {
		
		return ses.selectOne(ns+".count");
	}
	
	@Override
	public List<BoardVo> listAllSearch(SearchCriterria sc) throws Exception {
		
		return ses.selectList(ns+".listAllSearch", sc);
	}

	@Override
	public int listAllSearchCnt(SearchCriterria sc) throws Exception {
		
		return ses.selectOne(ns+".listAllSearchCnt",sc);
	}

	@Override
	public int updateReplyCnt(int bno, int cnt) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("cnt", cnt);
		
		return ses.update(ns+".updateReplyCnt", map);
	}

	
	
	

}
