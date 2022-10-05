package com.boritgogae.board.question.persistence;

import java.util.List;

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
	
	
	// 게시판 전체목록 보기 
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		return ses.selectList(ns + ".viewAllBoard");
	}

	// 게시판 글 작성하기
	@Override
	public int writeBoard(BoardVo board) throws Exception {
		return ses.insert(ns + ".writeBoard", board);
	}

	// 게시글 번호 얻어오기
	@Override
	public BoardVo getBoard(int no) throws Exception {
		return ses.selectOne(ns + ".getBoardByNo", no);
	}

	// 게시글 수정
	@Override
	public int modifyBoard(BoardVo board) throws Exception {
		System.out.println("다오단 글 수정");
		System.out.println(board.toString());
		return ses.update(ns + ".modifyBoard", board);
		
	}

}
