package com.boritgogae.board.tip.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.tip.domain.ReplyVo;

@Repository
public class BoardReplyDAOImpl implements BoardReplyDAO {
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardTipMapper";
	
	@Override
	public List<ReplyVo> getAllReply(int bno) throws Exception {
		
		return ses.selectList(ns+".selectReply", bno);
	}

	@Override
	public int insertReply(int bno, ReplyVo vo) throws Exception {
		System.out.println(vo+"dao vo");
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("vo", vo);
		return ses.insert(ns+".insertReply", map);
		
	}

	@Override
	public int deleteReply(int rno) throws Exception {
		
		return ses.delete(ns+".deleteReply", rno);
	}

}
