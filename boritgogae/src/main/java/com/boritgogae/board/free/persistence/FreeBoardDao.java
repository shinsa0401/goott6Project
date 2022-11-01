package com.boritgogae.board.free.persistence;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.free.domain.FreeBoardVo;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.free.domain.FreeUploadFileVo;


public interface FreeBoardDao {
	
	// 게시판 글 작성
	public int insertWriter(FreeBoardVo vo)throws Exception;
	// 게시판 상세페이지
	public FreeBoardVo detail(int bno)throws Exception;
	
	public void boardUpdate(FreeBoardVo vo)throws Exception;
	
	public int delBoard(int bno)throws Exception; 
	
	public int readCountUp(int bno)throws Exception;
	

	
	public List<FreeBoardVo> listAll(Map map)throws Exception;
	
	public int count() throws Exception;
	
	public int updateReplyCnt(int bno, int cnt)throws Exception;
	
	
	public void imageInsert(int lastNo, String savedOriginImageFileName) throws Exception;
	
	public void fileInsert(int lastNo, String savedOriginImageFileName) throws Exception;
	
	public int getLastNo() throws Exception;
	
	public int updateRef(int lastNo) throws Exception;
	
	public List<FreeUploadFileVo> Fileview(int bno)throws Exception;
	
	public int deleteImg(int bno)throws Exception;
	
	
	
	
	
	
	 int searchResultCnt(FreeSearchCondition sc) throws Exception;
	    List<FreeBoardVo> searchSelectPage(FreeSearchCondition sc) throws Exception;
	
	
	
	
}
