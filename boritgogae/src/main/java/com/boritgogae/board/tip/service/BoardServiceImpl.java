package com.boritgogae.board.tip.service;

import java.util.List;
import java.util.Map;

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
//		System.out.println("ServiceImpl : "+lst.toString());
		return lst;
	}

	@Override
	public BoardVo getDetail(int bno) throws Exception {
		BoardVo detail = dao.selectDetail(bno);
		return detail;
	}

	@Override
	public boolean addBoard(BoardVo board) throws Exception {
		boolean result = false;
		int row = dao.insertBoard(board);
		if (row == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delBoard(int bno) throws Exception {
		boolean result = false;
//		System.out.println(bno + "제대로 왔나?");
		int row = dao.deleteBoard(bno);
//		System.out.println(row+"여기까지는 오나?");
		if (row == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public BoardVo modiBoard(int bno, BoardVo vo) throws Exception {
		int row = dao.updateBoard(bno,vo);
//		System.out.println("업데이트 성공했냐? : "+row);

		if (row==1){
			return dao.selectDetail(bno);
		}
		return dao.selectDetail(bno);
	}

	@Override
	public boolean addReadCnt(int bno) throws Exception {
		boolean result = false;
		int row = dao.plusReadCnt(bno);
		if (row == 1) {
			result = true;
		}
		return result; 
	}


}
