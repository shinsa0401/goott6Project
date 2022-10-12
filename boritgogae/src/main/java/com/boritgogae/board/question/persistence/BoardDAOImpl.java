package com.boritgogae.board.question.persistence;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.question.domain.BoardVo;
import com.boritgogae.board.question.domain.ReadCountVo;
import com.boritgogae.board.question.domain.UploadFileVo;
import com.boritgogae.board.question.etc.PagingInfo;
import com.boritgogae.board.question.etc.SearchCriteria;

@Repository // DAO단임을 명시
public class BoardDAOImpl implements BoardDAO {
	
	// Mapper NameSpace 변수처리
	private static String ns = "com.boritgogae.boardQuestionMapper";
	
	// SqlSession 객체 주입
	@Inject
	private SqlSession ses;
	
	
	// 게시판 전체목록 보기 
	@Override
	public List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception {
		
		return ses.selectList(ns + ".selectAllBoard", pi);
	}

	// 게시판 글 작성하기
	@Override
	public int insertBoard(BoardVo board) throws Exception {
		
		return ses.insert(ns + ".insertBoard", board);
	}
	
	// 최근 등록된 글의 번호 (ref 업데이트하기 위해)
	@Override
	public int getLastNo() throws Exception {
		
		return ses.selectOne(ns + ".getLastNo");
	}

	// ref 업데이트
	@Override
	public int updateRef(int lastNo) throws Exception {
		
		return ses.update(ns + ".updateRef", lastNo);
	}
	
	// 이미지 파일 저장
	@Override
	public void insertImg(int lastNo, String savedOriginImageFileName, String thumbnailFileName) {
		System.out.println("이미지 저장 성공");
		
		Map<String, String> map = new HashMap<>();
		map.put("lastNo", lastNo + "");
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		map.put("thumbnailFileName", thumbnailFileName);
		ses.insert(ns + ".insertImg", map);
	}

	// 이미지가 아닌 파일 저장
	@Override
	public void insertFile(int lastNo, String savedOriginImageFileName) {
		System.out.println("파일 저장 성공");
		
		Map<String, String> map = new HashMap<>();
		map.put("lastNo", lastNo + "");
		map.put("savedOriginImageFileName", savedOriginImageFileName);
		ses.insert(ns + ".insertFile", map);
	}
	
	// 글 번호로 게시글 얻어오기
	@Override
	public BoardVo getBoard(int no) throws Exception {
		
		return ses.selectOne(ns + ".getBoardByNo", no);
	}
	
	// 글 번호로 첨부파일 얻어오기
	@Override
	public List<UploadFileVo> getAttachFiles(int no) throws Exception {
		
		return ses.selectList(ns + ".getAttachFiles", no);
	}
		
	// 게시글 비밀번호 확인(수정, 삭제시 확인 + 추가로 아이디까지 확인해야함 미완)
	@Override
	public int boardPwdCheck(int no, String pwd) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("pwd", pwd);
		
		return ses.selectOne(ns + ".boardPwdCheck", map);
	}
		
	// 게시글 수정
	@Override
	public int updateBoard(BoardVo board) throws Exception {
		
		return ses.update(ns + ".updateBoard", board);
	}

	// 게시글 삭제(삭제여부 업데이트)
	@Override
	public int deleteBoard(int no, String pwd) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("pwd", pwd);
		
		return ses.update(ns + ".deleteBoard", map);
	}

	// 게시글 조회시간 검색하기
	@Override
	public ReadCountVo getLastReadDate(Map<String, Object> readCount) throws Exception {
		
		return ses.selectOne(ns + ".selectReadCountCheck", readCount);
	}
	
	// 조회수 처리를 위해 글번호, ip주소, 조회시간 insert
	@Override
	public int insertReadCount(Map<String, Object> readCount) throws Exception {
		
		return ses.insert(ns + ".insertReadCount", readCount);
		
	}
	
	// 조회수 증가
	@Override
	public int updateReadCountBoard(int no) throws Exception {
		
		return ses.update(ns + ".updateReadCountBoard", no);
	}

	// 조회수 처리를 위해 글번호, ip주소, 조회시간 update
	@Override
	public int updateReadCount(Map<String, Object> readCount) throws Exception {
		
		return ses.update(ns + ".updateReadCount", readCount);
	}

	// 댓글 등록시 replyCount 증가
	@Override
	public int addReplyCount(int bno) throws Exception {
		
		return ses.update(ns + ".addReplyCount", bno);
	}
	
	// 댓글 삭제시 replyCoount 감소
	@Override
	public int subReplyCount(int bno) throws Exception {
		
		return ses.update(ns + ".subReplyCount", bno);
	}

	// 전체 글의 개수 반환하는 메서드
	@Override
	public int getTotalPostCnt() throws Exception {
		
		return ses.selectOne(ns + ".getTotalPostCnt");
	}

	// 검색된 글의 개수 반환하는 메서드
	@Override
	public int getSearchResultCnt(SearchCriteria sc) throws Exception {
		
		return ses.selectOne(ns + ".getSearchResultCnt", sc);
	}

	// 검색어가 있을 때 페이징 하며 검색 결과를 가져 오는 메서드
	@Override
	public List<BoardVo> getSearchResult(PagingInfo pi, SearchCriteria sc) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", sc.getSearchType());
		map.put("searchWord", sc.getSearchWord());
		map.put("startNum", pi.getStartNum());
		map.put("postPerPage", pi.getPostPerPage());
		
		return ses.selectList(ns + ".listAllWithSearch", map);
	}


}
