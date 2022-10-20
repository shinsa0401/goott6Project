package com.boritgogae.board.tip.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.tip.domain.TipBoardVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.tipBoardMapper";
	
	@Override
	public List<TipBoardVo> selectAllBoard(TipPagingInfo pi) throws Exception {
//		System.out.println("DAOImpl" + ses.toString());
		return ses.selectList(ns+".listAll",pi);
	}

	@Override
	public TipBoardVo selectDetail(int bno) throws Exception {
		return ses.selectOne(ns+".detail",bno);
	}

	@Override
	public int insertBoard(TipBoardVo board) throws Exception {
		
		System.out.println(board.toString());
		return ses.insert(ns+".addBoard", board);
	}

	@Override
	public int deleteBoard(int bno) throws Exception {
		
		return ses.delete(ns+".deleteBoard", bno);
	}

	@Override
	public int updateBoard(int bno, TipBoardVo vo) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		map.put("no", bno+"");
//		System.out.println(map.get("board"));
//		System.out.println(map.get("bno"));
		return ses.update(ns+".updateBoard", map);
		
	}

	@Override
	public int plusReadCnt(int bno) throws Exception {
		
		return ses.update(ns+".updateReadCnt", bno);
	}

	@Override
	public int getTotalPostCnt() throws Exception {
		
		return ses.selectOne(ns+".totalCnt");
	}

	@Override
	public int maxBno() throws Exception {

		return ses.selectOne(ns+".maxBno");
	}

	@Override
	public int updateRef(int bno) throws Exception {
		
		return ses.update(ns+".updateRef", bno);
	}

	@Override
	public int selectRef(int bno) throws Exception {
		
		return ses.selectOne(ns+".selectRef", bno);
	}

	@Override
	public int updateReplyRef(int maxBno, int ref) throws Exception {
//		System.out.println(maxBno+","+ref+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Map<String, String> map = new HashMap<>();
		map.put("maxBno", maxBno+"");
		map.put("ref", ref+"");
		return ses.update(ns+".updateReplyRef", map);
	}

	@Override
	public int selectRefOrder(int maxNo) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectOne(ns+".getRefOrder", maxNo);
	}

	@Override
	public int updateReplyRefOrder(int cntRef,  int maxNo) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("refOrder", cntRef+"");
		map.put("bno", maxNo+"");
		return ses.update(ns+".updateRefOrder", map);
	}

	@Override
	public int countRef(int ref) throws Exception {

		return ses.selectOne(ns+".countRef", ref);
	}

	@Override
	public int selectMin(int ref) throws Exception {
		
		return ses.selectOne(ns+".minRef", ref);
	}

	@Override
	public int stepNum(int bno) throws Exception {

		return ses.selectOne(ns+".minNum", bno);
	}

	@Override
	public int updateReplyRefOrder(int cntRef, int maxNo, int step) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("refOrder", cntRef+"");
		map.put("bno", maxNo+"");
		map.put("step", step+"");
		return ses.update(ns+".updateStep", map);
	}

	


}
