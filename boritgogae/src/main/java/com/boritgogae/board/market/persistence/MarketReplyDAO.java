package com.boritgogae.board.market.persistence;

import java.util.List;

import com.boritgogae.board.market.domain.MarketReplyVO;

public interface MarketReplyDAO {

	//댓글 달기
	public int replyWrite( MarketReplyVO reply) throws Exception;
	//모든 댓글 가져오기
	public List<MarketReplyVO> getAllReply(int bno) throws Exception;
	//댓글 수정
	public int modiReply(MarketReplyVO reply)throws Exception;
	//댓글 삭제
	public int delReply(MarketReplyVO reply)throws Exception;
	
}
