package com.boritgogae.board.question.service;

import java.util.List;

import com.boritgogae.board.question.domain.QuestionReplyVo;

public interface ReplyService {
	// 댓글을 등록 하는 메서드
	public boolean addReply(QuestionReplyVo reply) throws Exception;
	
	// 현재 (bno번) 글의 모든 댓글 얻어오는 메서드
	public List<QuestionReplyVo> getAllReply(int bno) throws Exception;
	
	// 댓글을 수정 하는 메서드
	public boolean modifyReply(QuestionReplyVo reply) throws Exception;

	// 댓글을 삭제 하는 메서드
	public boolean removeReply(QuestionReplyVo reply) throws Exception;

	// 댓글의 댓글 등록
	public boolean reReply(QuestionReplyVo reply) throws Exception;
}
