package com.boritgogae.board.tip.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.domain.PagingInfo;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.tipBoardMapper";
	
	@Override
	public List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception {
//		System.out.println("DAOImpl" + ses.toString());
		return ses.selectList(ns+".listAll",pi);
	}

	@Override
	public BoardVo selectDetail(int bno) throws Exception {
		return ses.selectOne(ns+".detail",bno);
	}

	@Override
	public int insertBoard(BoardVo board) throws Exception {
		
		
		return ses.insert(ns+".addBoard", board);
	}

	@Override
	public int deleteBoard(int bno) throws Exception {
		
		return ses.delete(ns+".deleteBoard", bno);
	}

	@Override
	public int updateBoard(int bno, BoardVo vo) throws Exception {
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
	public int updateReplyRefOrder(int ref, int refOrder) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("refOrder", refOrder+"");
		map.put("ref", ref+"");
		return ses.update(ns+".updateRefOrder", map);
	}

	


}
