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
		System.out.println("다오 : 게시판 전체목록 요청");
		return ses.selectList(ns + ".viewAllBoard");
	}

}
