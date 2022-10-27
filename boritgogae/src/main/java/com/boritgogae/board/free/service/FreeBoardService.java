package com.boritgogae.board.free.service;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeBoardVo;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.free.etc.FreeUploadFile;






@Service
public interface FreeBoardService {
	
	
	
	public boolean insertBoard(FreeBoardVo vo, List<FreeUploadFile> uploadFileLst)throws Exception;
	
	public Map<String,Object> detailBoard(int bno)throws Exception;

	public void boardUpdate(FreeBoardVo vo)throws Exception;
	
	public void delBoard(int bno)throws Exception;
	
	public void readCountUp(int bno)throws Exception;
	

	
	public List<FreeBoardVo> listAll(Map map)throws Exception;
	
	public int getCount() throws Exception;

	public int deleteImg(int bno) throws Exception;
	


	
	
	
	
	
	
	
	  int getSearchResultCnt(FreeSearchCondition sc) throws Exception;
	    List<FreeBoardVo> getSearchResultPage(FreeSearchCondition sc) throws Exception;
	
	
}
