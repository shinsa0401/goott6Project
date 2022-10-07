package com.boritgogae.board.question.persistence;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> sth

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.board.question.domain.BoardVo;

@Repository // DAO단임을 명시
public class BoardDAOImpl implements BoardDAO {
	
	// Mapper NameSpace 변수처리
	private static String ns = "com.boritgogae.boardQuestionMapper";
	
	// SqlSession 객체 주입
	@Inject
	private SqlSession ses;
	
	
<<<<<<< HEAD
	// 게시판 전체목록 보기 
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		System.out.println("다오 : 게시판 전체목록 요청");
		return ses.selectList(ns + ".viewAllBoard");
=======
	
	// 게시판 전체목록 보기 
	@Override
	public List<BoardVo> selectAllBoard() throws Exception {
		
		return ses.selectList(ns + ".selectAllBoard");
	}

	// 게시판 글 작성하기
	@Override
	public int insertBoard(BoardVo board) throws Exception {
		
		return ses.insert(ns + ".insertBoard", board);
	}

	// 게시글 번호 얻어오기
	@Override
	public BoardVo getBoard(int no) throws Exception {
		
		return ses.selectOne(ns + ".getBoardByNo", no);
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
	
	// 조회수 증가
	@Override
	public int updateReadCount(int no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
>>>>>>> sth
	}

}
