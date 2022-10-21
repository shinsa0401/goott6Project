package com.boritgogae.board.tip.persistence;

import java.util.List;

import com.boritgogae.board.tip.domain.TipReplyVo;

public interface BoardReplyDAO {

	List<TipReplyVo> getAllReply(int bno) throws Exception;

	int insertReply(int bno, TipReplyVo vo) throws Exception;

	int deleteReply(int rno) throws Exception;

	int modiReply(int rno, TipReplyVo vo) throws Exception;

}
