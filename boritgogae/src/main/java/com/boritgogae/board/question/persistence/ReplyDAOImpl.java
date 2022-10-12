package com.boritgogae.board.question.persistence;

import java.util.List;
import java.util.Map;

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
	
	// 최근 등록된 댓글 번호 검색
	@Override
	public int getLastRno() throws Exception {
		
		return ses.selectOne(ns + ".getLastRno");
	}
	
	// 최근 등록된 댓글의 ref 업데이트
	@Override
	public int updateReplyRef(int lastRno) throws Exception {
		
		return ses.update(ns + ".updateReplyRef", lastRno);
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

	// rno로 부모댓글의 정보 얻어오기
	@Override
	public ReplyVo getParentReply(int rno) throws Exception {
		
		return ses.selectOne(ns + ".getParentReply", rno);
	}

	// 댓글의 maxRefOrder 얻어오기
	@Override
	public int getMaxRefOrder(int bno) throws Exception {
		
		return ses.selectOne(ns + ".getMaxRefOrder", bno);
	}

	// 댓글의 댓글 등록
	@Override
	public int insertReReply(Map<String, Object> map) throws Exception {
		
		return ses.insert(ns + ".insertReReply", map);
	}

	
}
