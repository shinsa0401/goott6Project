package com.boritgogae.board.ask.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.boritgogae.board.ask.domain.AskReplyVo;

@Repository
public interface AskReplyDAO {

	// 댓글 등록 메서드
	public int insertReply(AskReplyVo reply) throws Exception ;

	// 특정 글의 마지막 ref를 찾음
	public String getLastReplyRef(int askBno) throws Exception ;

	// 특정 글의 마지막 no를 찾음
	public String getLastReplyNo(int askBno) throws Exception ;
	
	// bno번의 모든 댓글을 가져오는 메서드
	public List<AskReplyVo> selectRepliesByBno(int bno) throws Exception ;
	
	// 댓글 수정 메서드
	public int modifyReply(AskReplyVo reply) throws Exception ;

	// 대댓글 등록 메서드
	public int nestedReply(AskReplyVo reply) throws Exception ;

	// 같은 ref를 가진 댓글들 업데이트
	public int updateNestedReply(AskReplyVo reply) throws Exception ;

	// sql_safe_updates 해제
	public int safeUpdatesDisable() throws Exception ;

	// 댓글 삭제(실제로는 수정임)
	public int deleteReply(AskReplyVo reply) throws Exception ;



}
