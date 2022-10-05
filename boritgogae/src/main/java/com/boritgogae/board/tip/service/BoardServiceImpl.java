package com.boritgogae.board.tip.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVo> getListBoard() throws Exception {
		List<BoardVo> lst = dao.selectAllBoard();
		System.out.println("ServiceImpl : "+lst.toString());
		return lst;
	}

	@Override
	public BoardVo getDetail(int bno) throws Exception {
		BoardVo detail = dao.selectDetail(bno);
		return detail;
	}

}
