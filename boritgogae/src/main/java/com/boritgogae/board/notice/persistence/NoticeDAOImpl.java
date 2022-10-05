package com.boritgogae.board.notice.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.notice.domain.NoticeVo;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardNoticeMapper";
	
	@Override
	public List<NoticeVo> getNoticeBoard() throws Exception {
		System.out.println("NoticeDAO");
		return ses.selectList(ns + ".getNoticeList");
		
	}
	
	@Override
	public int registerBoard(NoticeVo board) throws Exception {	
		return ses.insert(ns + ".register", board);
	}


	@Override
	public NoticeVo viewBoard(int bno) throws Exception {
		return ses.selectOne(ns + ".getBoardByNo", bno);
	}


	@Override
	public int deleteBoard(int bno) throws Exception {
		return ses.delete(ns + ".deleteBoard", bno);
	}

	

}
