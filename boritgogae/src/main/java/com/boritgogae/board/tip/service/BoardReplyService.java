package com.boritgogae.board.tip.service;

import java.util.List;

import com.boritgogae.board.tip.domain.TipReplyVo;

public interface BoardReplyService {

	List<TipReplyVo> allReply(int bno) throws Exception;

	boolean plusReply(int bno, TipReplyVo vo) throws Exception;

	Boolean delReply(int rno) throws Exception;

	Boolean updateReply(int rno, TipReplyVo vo) throws Exception;

}
