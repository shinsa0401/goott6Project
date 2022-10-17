package com.boritgogae.board.free.service;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.ReplyVo;
import com.boritgogae.board.free.domain.SearchCondition;
import com.boritgogae.board.free.domain.SearchCriterria;
import com.boritgogae.board.free.etc.UploadFile;





@Service
public interface BoardService {
	
	
	
	public boolean insertBoard(BoardVo vo, List<UploadFile> uploadFileLst)throws Exception;
	
	public Map<String,Object> detailBoard(int bno)throws Exception;

	public void boardUpdate(BoardVo vo)throws Exception;
	
	public void delBoard(int bno)throws Exception;
	
	public void readCountUp(int bno)throws Exception;
	
	public List<BoardVo> listAllSearch(SearchCriterria sc)throws Exception;
	
	public List<BoardVo> listAll(Map map)throws Exception;
	
	public int getCount() throws Exception;

	public int deleteImg(int bno) throws Exception;
	

	public int listAllSearchCnt(SearchCriterria sc) throws Exception;
	
	
	
	
	
	
	
	  int getSearchResultCnt(SearchCondition sc) throws Exception;
	    List<BoardVo> getSearchResultPage(SearchCondition sc) throws Exception;
	
	
}
