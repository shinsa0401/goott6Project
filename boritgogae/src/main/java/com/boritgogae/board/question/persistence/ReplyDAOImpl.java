package com.boritgogae.board.question.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.question.domain.ReplyVo;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardQuestionMapper";

	// 댓글 등록
	@Override
	public int insertReply(ReplyVo reply) throws Exception {
		
		return ses.insert(ns + ".insertReply", reply);
	}

	// 현재 글(bno번)의 모든 댓글 검색
	@Override
	public List<ReplyVo> selectAllReply(int bno) {
		
		return ses.selectList(ns + ".selectAllReply", bno);
	}

	// 댓글 수정
	@Override
	public int updateReply(ReplyVo reply) throws Exception {
		
		return ses.update(ns + ".updateReply", reply);
	}

	// 댓글 삭제
	@Override
	public int deleteReply(ReplyVo reply) throws Exception {
		
		return ses.delete(ns + ".deleteReply", reply);
	}

	
}
