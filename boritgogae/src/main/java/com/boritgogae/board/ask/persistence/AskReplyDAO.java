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

	// bno번의 모든 댓글을 가져오는 메서드
	public List<AskReplyVo> selectRepliesByBno(int bno);
	
	// 댓글 수정 메서드
	public int modifyReply(AskReplyVo reply);


}
