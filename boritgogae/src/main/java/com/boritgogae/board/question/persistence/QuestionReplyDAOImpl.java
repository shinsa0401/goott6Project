package com.boritgogae.board.question.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.question.domain.QuestionReplyVo;

@Repository
public class QuestionReplyDAOImpl implements QuestionReplyDAO{
	
	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardQuestionMapper";

	// 댓글 등록
	@Override
	public int insertReply(QuestionReplyVo reply) throws Exception {
		
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
	public List<QuestionReplyVo> selectAllReply(int bno) {
		
		return ses.selectList(ns + ".selectAllReply", bno);
	}

	// 댓글 수정
	@Override
	public int updateReply(QuestionReplyVo reply) throws Exception {
		
		return ses.update(ns + ".updateReply", reply);
	}

	// 댓글 삭제
	@Override
	public int deleteReply(QuestionReplyVo reply) throws Exception {
		
		return ses.delete(ns + ".deleteReply", reply);
	}

	// rno로 부모댓글의 정보 얻어오기
	@Override
	public QuestionReplyVo getParentReply(int rno) throws Exception {
		
		return ses.selectOne(ns + ".getParentReply", rno);
	}

	// 댓글의 maxRefOrder 얻어오기
	@Override
	public int getMaxRefOrder(int bno) throws Exception {
		
		return ses.selectOne(ns + ".getMaxRefOrder", bno);
	}
	
	// 댓글의 댓글 작성전 refOrder 업데이트
	@Override
	public int updateRefOrder(QuestionReplyVo parentReply) throws Exception {
		System.out.println("다오단 refOrder 업데이트");
		return ses.update(ns + ".updateRefOrder", parentReply);
	}

	// 댓글의 댓글 등록
	@Override
	public int insertReReply(Map<String, Object> map) throws Exception {
		
		return ses.insert(ns + ".insertReReply", map);
	}

	// 부모댓글의 자식댓글수 검색
	@Override
	public int getReReplyNumCnt(int ref, int step) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ref", ref);
		map.put("step", step);
		return ses.selectOne(ns + ".getReReplyNumCnt", map);
	}

	//부모 댓글의 최댓 step값 검색
	@Override
	public int getReplyMaxStep(int ref) {
		
		return ses.selectOne(ns + ".getReplyMaxStep", ref);
	}
	
	//부모 댓글그룹의 자식 댓글수의 합 검색
	@Override
	public int getReReplyNumCntSum(int ref) {
		
		return ses.selectOne(ns + ".getReReplyNumCntSum", ref);
	}


}
