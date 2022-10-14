package com.boritgogae.board.question.persistence;

import java.util.List;

import com.boritgogae.board.question.domain.ReplyVo;

public interface ReplyDAO {
	// 댓글 등록 하는 메서드
	public int insertReply(ReplyVo reply) throws Exception;
	
	// bno번의 모든 댓글을 얻어오는 메서드
	public List<ReplyVo> selectAllReply(int bno) throws Exception;

	// 댓글 수정 하는 메서드
	public int updateReply(ReplyVo reply) throws Exception;

	// 댓글 삭제 하는 메서드
	public int deleteReply(ReplyVo reply) throws Exception;
	
	

}
