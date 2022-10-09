package com.boritgogae.board.tip.service;

import java.util.List;

import com.boritgogae.board.tip.domain.ReplyVo;

public interface BoardReplyService {

	List<ReplyVo> allReply(int bno) throws Exception;

	boolean plusReply(int bno, ReplyVo vo) throws Exception;

	Boolean delReply(int rno) throws Exception;

	Boolean updateReply(int rno, ReplyVo vo) throws Exception;

}
