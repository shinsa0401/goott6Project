package com.boritgogae.board.market.dao;


import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.boritgogae.board.market.domain.MarketReplyVO;

@Repository
public class MarketReplyDAOImpl implements MarketReplyDAO {
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardMarketMapper";
	
	
	
	//댓글 출력
	@Override
	public List<MarketReplyVO> getAllReply(int bno) throws Exception {
		
		return ses.selectList(ns+".getAllReply", bno);
	}

	//댓글 작성
	@Override
	public int replyWrite(MarketReplyVO reply) throws Exception {
		
		return ses.insert(ns+".replyWrite", reply);
	}

	//댓글 수정
	@Override
	public int modiReply(MarketReplyVO reply) throws Exception {
		System.out.println("다오단 댓글 수정 걸리니?: "+reply.toString());
		return ses.update(ns+".modiReply",reply);
		
	}
	//댓글 삭제
	@Override
	public int delReply(MarketReplyVO reply) throws Exception {
		
		return ses.delete(ns+".delReply",reply);
	}

}
