package com.boritgogae.board.notice.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.etc.PagingInfo;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Inject
	private SqlSession ses;
	
	private static String ns = "com.boritgogae.boardNoticeMapper";
	
	@Override
	public List<NoticeVo> getNoticeBoard(PagingInfo pi) throws Exception {
		return ses.selectList(ns + ".getNoticeList", pi);
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

	@Override
	public int modifyBoard(NoticeVo board) throws Exception {
		return ses.update(ns + ".modifyBoard", board);
	}

	@Override
	public int registerReply(NoticeReplyVo replyBoard) throws Exception {
		return ses.insert(ns + ".registerReply", replyBoard);
	}
	
	@Override
	public int registerReplyRepl(NoticeReplyVo replyBoard) throws Exception {
		return ses.insert(ns + ".registerReplyRepl", replyBoard);
	}

	@Override
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception {
		return ses.selectList(ns + ".getReplyList", bno);
	}

	@Override
	public int deleteReplyBoard(int rno) throws Exception {
		return ses.delete(ns + ".deleteReply", rno);
	}

	@Override
	public String getNickName(String memberId) throws Exception {
		return ses.selectOne(ns + ".getNickName", memberId);
	}

	@Override
	public int modifyReplyBoard(NoticeReplyVo board) throws Exception {
		return ses.update(ns + ".modifyReply", board);
	}

	@Override
	public int updateReadCount(int bno) throws Exception {
		return ses.update(ns + ".updateReadCount", bno);
	}

	@Override
	public int getNoticeBoardCnt() throws Exception {
		return ses.selectOne(ns + ".getNoticeBoardCnt");
	}

	@Override
	public int getLastNo() throws Exception {
		return ses.selectOne(ns + ".getLastNo");
	}

	@Override
	public int updateRef(int lastNo) throws Exception {
		return ses.update(ns + ".updateRef", lastNo);
	}

	@Override
	public int updateRefOrder(NoticeReplyVo replyBoard) throws Exception {
		return ses.update(ns + ".updateRefOrder", replyBoard);
	}

	@Override
	public int updateRefFromRno(int rno, int lastNo) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("lastNo", lastNo + "");
		map.put("rno", rno + "");
		return ses.update(ns + ".updateRefFromRno", map);
	}

	@Override
	public int getRefOrder(int lastNo) throws Exception {
		return ses.selectOne(ns + ".getRefOrder", lastNo);
	}

	
	

}
