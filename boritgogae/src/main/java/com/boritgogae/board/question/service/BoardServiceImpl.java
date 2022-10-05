package com.boritgogae.board.question.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.BoardVo;
import com.boritgogae.board.question.persistence.BoardDAO;

@Service // Service단임을 명시
public class BoardServiceImpl implements BoardService {

	// dao객체 주입
	@Inject
	private BoardDAO dao;
	
	// 게시판 글 전체 목록
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		System.out.println("서비스 : 게시판 전체목록 요청");
		List<BoardVo> lst = dao.viewAllBoard();
		return lst;
	}

	// 게시판 글 작성
	@Override
	public boolean writeBoard(BoardVo board) throws Exception {
		boolean result = false;
		int row = dao.writeBoard(board);
		
		if (row == 1) {
			result = true;
			System.out.println("글저장성공");
		}
		return result;
	}

	// 게시글 보기
	@Override
	public BoardVo viewBoard(int no) throws Exception {
		
		BoardVo board = dao.getBoard(no);
		
		return board;
	}

	// 게시글 수정
	@Override
	public boolean modifyBoard(BoardVo board) throws Exception {
		boolean result = false;
		System.out.println("서비스단 글 수정");
		int row = dao.modifyBoard(board);
		if (row == 1) {
			result = true;
			System.out.println("글수정완료");
		}
		System.out.println(board.toString());
		return result;
	}

}
