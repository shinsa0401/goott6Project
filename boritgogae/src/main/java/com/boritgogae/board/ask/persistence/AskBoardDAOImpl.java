package com.boritgogae.board.ask.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadFileVo;

@Repository // 현재 클래스가 DAO임
public class AskBoardDAOImpl implements AskBoardDAO {

	// Mapper NameSpace 처리하기
	private static String ns = "com.boritgogae.boardAskMapper";
	
	// sqlSession 객체 주입
	@Inject
	private SqlSession ses;
	
	// 게시글 전체 보기
	@Override
	public List<AskBoardVo> selectAllBoard(PagingInfo pi) throws Exception {
		System.out.println("DAO : 게시판 전체 목록 요청");
		List<AskBoardVo> lst = ses.selectList(ns + ".listAll", pi);
		System.out.println("DAO : 게시판 전체 목록 요청" + lst);
		return lst;
	}

	@Override
	public List<AskBoardVo> getSearchResult(SearchCriteria sc, PagingInfo pi) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", sc.getSearchType());
		map.put("searchWord", sc.getSearchWord());
		map.put("startNum", pi.getStartNum());
		map.put("postPerPage", pi.getPostPerPage());
		
		return ses.selectList(ns + ".listAllWithSearch", map);
	}

	@Override
	public int getSearchResultCnt(SearchCriteria sc) throws Exception {
		return ses.selectOne(ns + ".getSearchResultCnt", sc);
	}

	@Override
	public int getTotalPostCnt() throws Exception {
		return ses.selectOne(ns + ".getTotalPostCnt");
	}

	// 문의코드와 문의코드에 따른 내용을 가져온다.
	@Override
	public List<AskCodeVo> loadAskCode() throws Exception {
		return ses.selectList(ns+ ".loadAskCode");
	}

	@Override
	public int insertAskBoard(AskBoardVo board) throws Exception {
		return ses.insert(ns + ".create", board);
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
	public void imageInsert(int lastNo, String savedOriginImageFileName, String thumbnailFileName) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("lastNo", lastNo + "");
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		map.put("thumbnailFileName", thumbnailFileName);
		
		ses.insert(ns + ".insertImageFile", map);
		
	}

	@Override
	public void fileInsert(int lastNo, String savedOriginImageFileName) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("lastNo", lastNo + "");
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		
		ses.insert(ns + ".insertFile", map);
		
	}

	@Override
	public AskBoardVo getBoard(int bno) {
		return ses.selectOne(ns + ".getAskBoardByNo", bno);
	}

	@Override
	public List<UploadFileVo> getAttachFile(int bno) {
		return ses.selectList(ns + ".getAttrachFiles", bno);
	}

	
	// 일단 보류
	@Override
	public int updateReadCount(int bno) throws Exception {
		return ses.update(ns + ".updateReadCount", bno);
	}

	// 문의코드에 맞는 문의카테고리(한글) 가져오기
	@Override
	public String readAskOptionByAskCode(String askCode) {
		return ses.selectOne(ns + ".readAskOptionByAskCode",askCode);
	}
	
}
