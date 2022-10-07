package com.boritgogae.board.question.persistence;

<<<<<<< HEAD
public interface ReplyDAO {

=======
import java.util.List;

import com.boritgogae.board.question.domain.ReplyVo;

public interface ReplyDAO {
	// 댓글 등록 하는 메서드
	public int insertReply() throws Exception;
	
	// 댓글 수정 하는 메서드
	
	// 댓글 삭제 하는 메서드

	// bno번의 모든 댓글을 가져오는 메서드
	public List<ReplyVo> selectRepliesByBno(int bno) throws Exception;
>>>>>>> sth
}
