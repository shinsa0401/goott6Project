package com.boritgogae.board.free.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.ReplyVo;

@Service
public interface ReplyService {
	
		public int getCount(int bno) throws Exception;  
	

	  
	    public int remove(int rno, int bno, String replyer) throws Exception ;


	    public int write(ReplyVo rv) throws Exception ;

	 
	    public List<ReplyVo> getList(int bno) throws Exception ;
	
	    public ReplyVo read(int rno) throws Exception;

	
	    public int modify(ReplyVo rv) throws Exception ;

}
