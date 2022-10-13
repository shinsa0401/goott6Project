package com.boritgogae.board.market.dao;

import java.util.List;

import com.boritgogae.board.market.domain.ReplyVO;

public interface ReplyDAO {

	//댓글 달기
	public int replyWrite( ReplyVO reply) throws Exception;
	//모든 댓글 가져오기
	public List<ReplyVO> getAllReply(int bno) throws Exception;
	//댓글 수정
	public int modiReply(ReplyVO reply)throws Exception;
	//댓글 삭제
	public int delReply(ReplyVO reply)throws Exception;
	
}
