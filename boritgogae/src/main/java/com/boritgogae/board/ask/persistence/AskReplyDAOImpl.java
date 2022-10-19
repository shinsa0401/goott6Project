package com.boritgogae.board.ask.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.ask.domain.AskReplyVo;

@Repository
public class AskReplyDAOImpl implements AskReplyDAO {

	@Inject
	private SqlSession ses;

	private static String ns = "com.boritgogae.boardAskMapper";
	
	// 댓글 등록 메서드
	@Override
	public int insertReply(AskReplyVo reply) throws Exception {
		System.out.println("DAO단 : " + reply.toString());
		return ses.insert(ns + ".addAskReply", reply);
	}

	@Override
	public String getLastReplyRef(int askBno) throws Exception {
		return ses.selectOne(ns + ".getLastReplyRef", askBno);
	}
	@Override
	public String getLastReplyNo(int askBno) throws Exception {
		return ses.selectOne(ns + ".getLastReplyNo", askBno);
	}
	
	@Override
	public List<AskReplyVo> selectRepliesByBno(int bno) {
		return ses.selectList(ns + ".selectAllRepliesByBno", bno);
	}

	@Override
	public int modifyReply(AskReplyVo reply) {
		return ses.update(ns + ".modifyReply", reply);
	}

	// 대댓글 등록 메서드
	@Override
	public int nestedReply(AskReplyVo reply) throws Exception {
		System.out.println("DAO단 : " + reply.toString());
		return ses.insert(ns + ".addNestedReply", reply);
	}

	// 같은 ref의 댓글들 refOrder update
	@Override
	public int updateNestedReply(AskReplyVo reply) throws Exception  {
		System.out.println("DAO단 : " + reply.toString());
		return ses.update(ns + ".updateNestedReply", reply);
	}

	// sql_safe_updates 해제
	public int safeUpdatesDisable() throws Exception {
		System.out.println("sql_safe_updates 해제");
		return ses.selectOne(ns + ".safeUpdatesDisable");
	}

	@Override
	public int deleteReply(AskReplyVo reply) throws Exception {
		System.out.println("DAO단 : " + reply.toString());
		return ses.update(ns + ".deleteReply", reply);
	}
	
	

}
