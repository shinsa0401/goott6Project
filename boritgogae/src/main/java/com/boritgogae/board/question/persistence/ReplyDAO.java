package com.boritgogae.board.question.persistence;

import java.util.List;
import java.util.Map;

import com.boritgogae.board.question.domain.ReplyVo;

public interface ReplyDAO {
	// 댓글 등록 하는 메서드
	public int insertReply(ReplyVo reply) throws Exception;
	
	// 최근 등록된 댓글 번호 얻어오는 메서드 
	public int getLastRno() throws Exception;
	
	// 최근 등록된 댓글의 ref 업데이트 하는 메서드
	public int updateReplyRef(int lastRno) throws Exception;
	
	// bno번의 모든 댓글을 얻어오는 메서드
	public List<ReplyVo> selectAllReply(int bno) throws Exception;

	// 댓글 수정 하는 메서드
	public int updateReply(ReplyVo reply) throws Exception;

	// 댓글 삭제 하는 메서드
	public int deleteReply(ReplyVo reply) throws Exception;

	// rno로 부모댓글의 정보 얻어오기
	public ReplyVo getParentReply(int rno) throws Exception;

	// 댓글의 maxRefOrder 얻어오기
	public int getMaxRefOrder(int bno) throws Exception;

	// 댓글의 댓글 등록
	public int insertReReply(Map<String, Object> map) throws Exception;

	

}
