package com.boritgogae.board.free.dao;

import java.util.List;

import com.boritgogae.board.free.domain.ReplyVo;

public interface ReplyDao {
	
	public int deleteAllReply(int bno)throws Exception;
	
	public int countReply(int bno)throws Exception;
	
	public int deleteReply(int rno, String replyer)throws Exception;
	
	public List<ReplyVo> selectAllReply(int bno)throws Exception;
	
	public ReplyVo selectReply(int rno)throws Exception;
	
	public int updateReply(ReplyVo rv)throws Exception;
	
	public int insertReply(ReplyVo rv)throws Exception;

}
