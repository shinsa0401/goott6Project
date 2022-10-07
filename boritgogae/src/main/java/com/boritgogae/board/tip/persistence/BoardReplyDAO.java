package com.boritgogae.board.tip.persistence;

import java.util.List;

import com.boritgogae.board.tip.domain.ReplyVo;

public interface BoardReplyDAO {

	List<ReplyVo> getAllReply(int bno) throws Exception;

	int insertReply(int bno, ReplyVo vo) throws Exception;

	int deleteReply(int rno) throws Exception;

}
