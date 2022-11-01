package com.boritgogae.board.free.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeReplyVo;



@Service
public interface FreeReplyService {
	
		public int getCount(int bno) throws Exception;  
	

	  
	    public int remove(int rno, int bno, String replyer) throws Exception ;


	    public int write(FreeReplyVo rv) throws Exception ;

	 
	    public List<FreeReplyVo> getList(int bno) throws Exception ;
	
	    public FreeReplyVo read(int rno) throws Exception;

	
	    public int modify(FreeReplyVo rv) throws Exception ;

}
