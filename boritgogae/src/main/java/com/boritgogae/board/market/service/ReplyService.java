package com.boritgogae.board.market.service;

import java.util.List;

import com.boritgogae.board.market.domain.ReplyVO;

public interface ReplyService {
//	// 댓글 달기
//	public boolean replyWrite(int bno, ReplyVO reply) throws Exception;
	//댓글 달기
	public boolean replyWrite( ReplyVO reply) throws Exception;
	//현재글의 모든 댓글
	public List<ReplyVO> getAllReply(int bno) throws Exception;
	//댓글 수정
	public boolean modiReply (ReplyVO reply) throws Exception;
	//댓글 삭제
	public boolean delReply(ReplyVO reply)throws Exception;

}
