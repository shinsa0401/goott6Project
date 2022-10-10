package com.boritgogae.board.ask.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.boritgogae.board.ask.domain.AskReplyVo;


@Service
public interface AskReplyService {
	// 댓글 등록하는 메서드
	public boolean addReply(AskReplyVo reply) throws Exception;
	
	// 현재 글(bno)의 모든 댓글을 가져오는 메서드
	public List<AskReplyVo> getEntireReplies(int bno) throws Exception;

	// 댓글 수정하는 메서드
	public boolean modifyReply(AskReplyVo reply) throws Exception;
	
	// 대댓글 메서드
	public boolean nestedReply(AskReplyVo reply) throws Exception;

	// 댓글 삭제 메서드
	public boolean deleteReply(AskReplyVo reply) throws Exception;
	
}
