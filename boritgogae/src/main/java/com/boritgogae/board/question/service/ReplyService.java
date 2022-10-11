package com.boritgogae.board.question.service;

<<<<<<< HEAD
import java.util.List;

import com.boritgogae.board.question.domain.ReplyVo;

public interface ReplyService {
	// 댓글을 등록 하는 메서드
	public boolean addReply(ReplyVo reply) throws Exception;
	
	// 현재 (bno번) 글의 모든 댓글 얻어오는 메서드
	public List<ReplyVo> getAllReply(int bno) throws Exception;
	
	// 댓글을 수정 하는 메서드
	public boolean modifyReply(ReplyVo reply) throws Exception;

	// 댓글을 삭제 하는 메서드
	public boolean removeReply(ReplyVo reply) throws Exception;

	// 댓글의 댓글 등록
	public boolean reReply(ReplyVo reply) throws Exception;
=======
public interface ReplyService {

>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
}
