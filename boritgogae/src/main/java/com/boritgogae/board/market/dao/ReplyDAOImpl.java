package com.boritgogae.board.market.dao;


import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.boritgogae.board.market.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardMarketMapper";
	
	
	
	//댓글 출력
	@Override
	public List<ReplyVO> getAllReply(int bno) throws Exception {
		
		return ses.selectList(ns+".getAllReply", bno);
	}

	//댓글 작성
	@Override
	public int replyWrite(ReplyVO reply) throws Exception {
		
		return ses.insert(ns+".replyWrite", reply);
	}

	//댓글 수정
	@Override
	public int modiReply(ReplyVO reply) throws Exception {
		System.out.println("다오단 댓글 수정 걸리니?: "+reply.toString());
		return ses.update(ns+".modiReply",reply);
		
	}
	//댓글 삭제
	@Override
	public int delReply(ReplyVO reply) throws Exception {
		
		return ses.delete(ns+".delReply",reply);
	}

}
