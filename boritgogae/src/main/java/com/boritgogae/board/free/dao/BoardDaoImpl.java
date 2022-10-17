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
import com.boritgogae.board.free.domain.SearchCondition;
import com.boritgogae.board.free.domain.SearchCriterria;
import com.boritgogae.board.free.domain.UploadFileFreeVo;



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
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("SearchType", sc.getSearchType());
		map.put("searchWord", sc.getSearchWord());
//		map.put("page", ph.getPage());
//		map.put("naviSize", ph.getNaviSize());
		return ses.selectList(ns+".listAllSearch", map);
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
	
	@Override
	   public void imageInsert(int lastNo, String savedOriginImageFileName) throws Exception {
	      Map<String, String> map = new HashMap<>();
	      map.put("lastNo", lastNo + "");
	      map.put("savedOriginImageFileName", savedOriginImageFileName);
	    
	      System.out.println("dwdwdwdww"+map.toString());
	      ses.insert(ns + ".insertImageFile", map);
	      
	   }

	   @Override
	   public void fileInsert(int lastNo, String savedOriginImageFileName) throws Exception {
	      Map<String, String> map = new HashMap<>();
	      map.put("lastNo", lastNo + "");
	      map.put("savedOriginImageFileName", savedOriginImageFileName);
	      
	      ses.insert(ns + ".insertFile", map);
	      
	   }
	@Override
	public int getLastNo()throws Exception{
		return ses.selectOne(ns+".getLastNo");
	}
	
	@Override
	public int updateRef(int lastNo)throws Exception{
		return ses.update(ns+".updateRef", lastNo);
	}

	@Override
	public List<UploadFileFreeVo> Fileview(int bno) throws Exception {
		
		return ses.selectList(ns+".Fileview", bno);
	}

	@Override
	public int deleteImg(int bno) throws Exception {
		
		return ses.delete(ns+".deleteImg", bno);
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(ns+".searchResultCnt", sc);
	}

	@Override
	public List<BoardVo> searchSelectPage(SearchCondition sc) throws Exception {
		 return ses.selectList(ns+".searchSelectPage", sc);
		
	}
	
	
	

	

}
