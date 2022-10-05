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
	
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		System.out.println("서비스 : 게시판 전체목록 요청");
		List<BoardVo> lst = dao.viewAllBoard();
		return lst;
	}

}
