package com.boritgogae.board.free.persistence;

import java.util.List;

import com.boritgogae.board.free.domain.FreeReplyVo;


public interface FreeReplyDao {
	
	public int deleteAllReply(int bno)throws Exception;
	
	public int countReply(int bno)throws Exception;
	
	public int deleteReply(int rno, String replyer)throws Exception;
	
	public List<FreeReplyVo> selectAllReply(int bno)throws Exception;
	
	public FreeReplyVo selectReply(int rno)throws Exception;
	
	public int updateReply(FreeReplyVo rv)throws Exception;
	
	public int insertReply(FreeReplyVo rv)throws Exception;

}
