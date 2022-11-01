package com.boritgogae.board.free.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.free.domain.FreeReplyVo;




@Repository
public class FreeReplyDaoImpl implements FreeReplyDao {
	
	@Inject
	SqlSession ses;
	
	String ns = "com.boritgogae.boardFreeMapper";

	@Override
	public int deleteAllReply(int bno) throws Exception {
		
		return ses.delete(ns+".deleteAllReply", bno);
	}

	@Override
	public int countReply(int bno) throws Exception {
		
		return ses.selectOne(ns+".countReply", bno);
	}

	@Override
	public int deleteReply(int rno, String replyer) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("rno", rno);
		map.put("replyer", replyer);
		return ses.delete(ns+".deleteReply", map);
	}

	@Override
	public List<FreeReplyVo> selectAllReply(int bno) throws Exception {
		
		return ses.selectList(ns+".selectAllReply", bno);
	}

	@Override
	public FreeReplyVo selectReply(int rno) throws Exception {
		
		return ses.selectOne(ns+".selectReply", rno);
	}

	@Override
	public int updateReply(FreeReplyVo rv) throws Exception {
		
		return ses.update(ns+".updateReply", rv);
	}

	@Override
	public int insertReply(FreeReplyVo rv) throws Exception {
		
		return ses.insert(ns+".insertReply", rv);
	}
}
