package com.boritgogae.board.market.persistence;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.market.domain.MarketBoardVO;
import com.boritgogae.board.market.domain.MarketPaging;
import com.boritgogae.board.market.domain.MarketSearchCriteria;
import com.boritgogae.board.market.domain.MarketUploadFileVo;

@Repository
public class MarketBoardDAOImpl implements MarketBoardDAO {
	
	private static String ns = "com.boritgogae.boardMarketMapper";
	
	@Inject
	private SqlSession ses;

	@Override
	public int write(MarketBoardVO board) throws Exception {
		
		return ses.insert(ns+".write", board);
	}

	@Override
	public List<MarketBoardVO> getAllBoard(MarketPaging pi) throws Exception { //페이징 아직 안함
		
		return ses.selectList(ns+".getAllBoard",pi);
	}

	@Override
	public MarketBoardVO getContent(int no) throws Exception {
		
		return ses.selectOne(ns+".getContent",no);
	}

	@Override
	public void modify(MarketBoardVO board) throws Exception {
	
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
	public List<MarketBoardVO> getSearchResult(MarketPaging pi,MarketSearchCriteria sc) throws Exception {
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("searchType", sc.getSearchType());
		map.put("searchWord", sc.getSearchWord());
		map.put("startNum", pi.getStartNum()); //페이징을 위한 시작번호
		map.put("postperPage", pi.getPostPerPage()); //페이징을 위한 한페이징당 보여줄 페이지 수 
		
		return ses.selectList(ns+".search",map);
	}
	
	@Override
	public int getSearchResultCnt(MarketSearchCriteria sc) throws Exception {
		
		return ses.selectOne(ns+".getSearchResultCnt", sc);
	}
	
	
	@Override
	public int getTotalCnt() throws Exception {
		
		return ses.selectOne(ns+".getTotalCnt");
	}

	@Override
	public void imageInsert(int lastNo,String savedOriginImageFileName, String thumbnailFileName) throws Exception {
		
		Map<String, String>map = new HashMap<>();
		map.put("lastNo", lastNo+"");
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		map.put("thumbnailFileName", thumbnailFileName);
		
		ses.insert(ns+".addImgFile",map);
		
	}

	@Override
	public void fileInsert(int lastNo,String savedOriginImageFileName) throws Exception {
		
		Map<String, String>map = new HashMap<>();
		map.put("lastNo", lastNo+""); //int type변환을 위해 공백 추가
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		
		ses.insert(ns+".addNotImgFile", map);
		
	}

	@Override
	public List<MarketUploadFileVo> getAttachFile(int no) throws Exception {
		
		return ses.selectList(ns+".getAttachFiles", no);
	}

	@Override
	public int updateRef(int lastNo) throws Exception {
		
		return ses.update(ns+".updateRef", lastNo);
	}

	@Override
	public int getLastNo() throws Exception {
		
		return ses.selectOne(ns+".getLastNo");
	}

	
	
	
}
