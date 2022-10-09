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
	public List<AskReplyVo> selectRepliesByBno(int bno) {
		return ses.selectList(ns + ".selectAllRepliesByBno", bno);
	}

	@Override
	public int modifyReply(AskReplyVo reply) {
		return ses.update(ns + ".modifyReply", reply);
	}
	
	

}
