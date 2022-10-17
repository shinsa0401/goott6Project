package com.boritgogae.board.ask.persistence;

import java.sql.Timestamp;
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
import com.boritgogae.board.ask.domain.UploadFile;
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
		return ses.selectList(ns + ".loadAskCode");
	}

	// 게시판 글 등록 메서드
	@Override
	public int insertAskBoard(AskBoardVo board) throws Exception {
		return ses.insert(ns + ".create", board);
	}

	// 게시판 글 업데이트 메서드
	@Override
	public int updateAskBoard(AskBoardVo board) throws Exception {
		System.out.println("DAO : updateAskBoard : AskBoardVo = " + board + "");
		return ses.update(ns + ".updateAskBoard", board);
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
	public AskBoardVo getBoard(int bno) throws Exception {
		return ses.selectOne(ns + ".getAskBoardByNo", bno);
	}

	@Override
	public List<UploadFileVo> getAttachFile(int bno) throws Exception {
		return ses.selectList(ns + ".getAttrachFiles", bno);
	}

	// 문의코드에 맞는 문의카테고리(한글) 가져오기
	@Override
	public String readAskOptionByAskCode(String askCode) {
		return ses.selectOne(ns + ".readAskOptionByAskCode", askCode);
	}

	// 읽은시간 업데이트
	@Override
	public int createReadCount(int bno, String clientIp) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("bno", bno + "");
		map.put("clientIp", clientIp + "");
		return ses.insert(ns + ".createReadCount", map);
	}

	// 글조회 가장 최근 읽은 시간값을 불러옴
	@Override
	public String checkRecentlyRead(int bno, String clientIp) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("bno", bno + "");
		map.put("clientIp", clientIp + "");
		return ses.selectOne(ns + ".checkRecentlyRead", map);
	}

	// 글번호에 맞는 조회수 가져옴
	@Override
	public int getReadCountByBno(int askBno) throws Exception {
		return ses.selectOne(ns + ".getReadCountByBno", askBno);
	}

	// 같은 ref를 가진 보드들 update
	@Override
	public int updateBoardsRef(AskBoardVo board) throws Exception {
		System.out.println("DAO단 : " + board.toString());
		return ses.update(ns + ".updateBoardsRef", board);
	}

	// 답글 생성
	@Override
	public int answerCreate(AskBoardVo board) throws Exception {
		return ses.insert(ns + ".answerCreate", board);
	}

	@Override
	public int answerStatusOk(int askBno) throws Exception {
		return ses.update(ns + ".answerStatusOk", askBno);
	}
	
	// 글삭제
	@Override
	public int removeBoard(int no) throws Exception {
		System.out.println("DAO단 : " + no);
		return ses.update(ns + ".removeBoard", no);
	}

	// 글 수정을 위한 글 정보 조회
	@Override
	public AskBoardVo getboardVo(String bno) throws Exception {
		System.out.println("DAO단 : " + bno);
		return ses.selectOne(ns+".getboardVo", bno);
	}

	// 글에 첨부되어있는 파일리스트를 가져오는 메서드
	@Override
	public List<UploadFile> showFileList(String bno) throws Exception {
		System.out.println("DAO : showFileList : no = " + bno + "번");
		return ses.selectList(ns + ".showFileList", bno);
	}

	// DB에서 파일명을 가지고 있는 컬럼 삭제
	@Override
	public int deleteFileDB(String savedOriginImageFileName) throws Exception {
		System.out.println("DAO : deleteFileDB : 파일명 = " + savedOriginImageFileName + "");
		return ses.delete(ns + ".deleteFileDB", savedOriginImageFileName);
		
	}
	
	// 특정 글의 좋아요 갯수를 파악한다.
	@Override
	public int getLikeCount(int askBno) throws Exception {
		System.out.println("DAO : getLikeCount : no = " + askBno + "번");
		return ses.selectOne(ns + ".getLikeCount", askBno);
	}
	
	// 특정 글의 조회수 갯수를 파악한다.
	@Override
	public int getReadCount(int askBno) throws Exception {
		System.out.println("DAO : getReadCount : no = " + askBno + "번");
		return ses.selectOne(ns + ".getReadCount", askBno);
	}

	// 특정 아이피가 특정 글을 추천을 했는지 확인하기
	@Override
	public int likeCheck(int no, String clientIp) throws Exception {
		System.out.println("DAO : likeCheck : no = " + no + "번, clientIp = " + clientIp);
		
		Map<String, String> map = new HashMap<>();
		map.put("bno", no + "");
		map.put("clientIp", clientIp + "");
		return ses.selectOne(ns + ".likeCheck", map);
	}

	// 특정 아이피의 특정 글 추천을 삭제
	@Override
	public int deleteLike(int no, String clientIp) throws Exception {
		System.out.println("DAO : deleteLike : no = " + no + "번, clientIp = " + clientIp);
		
		Map<String, String> map = new HashMap<>();
		map.put("bno", no + "");
		map.put("clientIp", clientIp + "");
		return ses.delete(ns + ".deleteLike", map);
	}

	// 특정 아이피의 특정 글 추천을 등록
	@Override
	public int insertLike(int no, String clientIp) throws Exception {
		System.out.println("DAO : insertLike : no = " + no + "번, clientIp = " + clientIp);
		
		Map<String, String> map = new HashMap<>();
		map.put("bno", no + "");
		map.put("clientIp", clientIp + "");
		
		return ses.insert(ns + ".insertLike", map);
	}


	// FAQ중 조회수 상위 3개의 정보를 가져온다.
	@Override
	public  List<AskBoardVo> readFAQBoard() throws Exception {
		return ses.selectList(ns + ".readFAQBoard");
	}

	// 특정 보드의 조회수를 1 증가시킨다.
	@Override
	public int readCountUp(int bno) throws Exception {
		return ses.update(ns + ".readFAQBoard", bno);
	}
}
