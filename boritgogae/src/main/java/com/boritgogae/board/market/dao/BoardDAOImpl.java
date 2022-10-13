package com.boritgogae.board.market.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.domain.Criteria;
import com.boritgogae.board.market.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static String ns = "com.boritgogae.boardMarketMapper";
	
	@Inject
	private SqlSession ses;

	@Override
	public int write(BoardVO board) throws Exception {
		
		return ses.insert(ns+".write", board);
	}

	@Override
	public List<BoardVO> getAllBoard() throws Exception { //페이징 아직 안함
		
		return ses.selectList(ns+".getAllBoard");
	}

	@Override
	public BoardVO getContent(int no) throws Exception {
		
		return ses.selectOne(ns+".getContent",no);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
	
		 ses.update(ns+".modify", board);
	}

	@Override
	public void delete(int no) throws Exception {
		
		ses.delete(ns+".delContent",no);
		
	}

	@Override
	public int readCnt(int no) throws Exception {
		
		return ses.update(ns+".readCount", no);
	}

	@Override
	public List<BoardVO> getSearchResult(SearchCriteria sc) throws Exception {
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("searchType", sc.getSearchType());
		map.put("searchWord", sc.getSearchWord());
		
		return ses.selectList(ns+".search",map);
	}
	
	@Override
	public int getTotalCnt() throws Exception {
		
		return ses.selectOne(ns+".getTotalCnt");
	}

	@Override
	public List<BoardVO> getAllBoardWithPaging(Criteria cri) throws Exception {
		
		return ses.selectList(ns+".getAllBoard");
	}
	
	
}
