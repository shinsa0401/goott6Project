package com.boritgogae.board.question.service;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> sth

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.BoardVo;
import com.boritgogae.board.question.persistence.BoardDAO;

@Service // Service단임을 명시
public class BoardServiceImpl implements BoardService {

	// dao객체 주입
	@Inject
	private BoardDAO dao;
	
<<<<<<< HEAD
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		System.out.println("서비스 : 게시판 전체목록 요청");
		List<BoardVo> lst = dao.viewAllBoard();
		return lst;
	}

=======
	// 게시판 글 전체 목록
	@Override
	public List<BoardVo> viewAllBoard() throws Exception {
		System.out.println("서비스 : 게시판 전체목록 요청");
		List<BoardVo> lst = dao.selectAllBoard();
		return lst;
	}

	// 게시판 글 작성
	@Override
	public Map<String, Object> writeBoard(BoardVo board) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		int row = dao.insertBoard(board);
		int lastNo = 0;
		
		if (row == 1) {
			System.out.println("글 저장성공");
			lastNo = dao.getLastNo();
			System.out.println("lastNo 검색 : " + lastNo);
			if (dao.updateRef(lastNo) == 1) {
				System.out.println("ref업데이트 성공");
				
				map.put("result", true);
				map.put("lastNo", lastNo);
			}
		}
		System.out.println(map.toString());
		return map;
	}

	// 게시글 보기
	@Override
	public BoardVo viewBoard(int no) throws Exception {
		
		BoardVo board = dao.getBoard(no);
		
		// no번 글 조회수 update
		dao.updateReadCount(no);
		
		
		return board;
	}

	// 게시글 수정
	@Override
	public boolean modifyBoard(BoardVo board) throws Exception {
		boolean result = false;
		System.out.println("서비스단 글 수정");
		int row = dao.updateBoard(board);
		if (row == 1) {
			result = true;
			System.out.println("글수정완료");
		}
		System.out.println(board.toString());
		return result;
	}

	@Override
	public boolean removeBoard(int no, String pwd) throws Exception {
		boolean result = false;
		
		if (dao.boardPwdCheck(no, pwd) == 1) {
			System.out.println("서비스단 비밀번호 확인 체크");
			
			if (dao.deleteBoard(no, pwd) == 1) {
				System.out.println("서비스단 삭제여부 업데이트");
				result = true;
			}
		}
		
		return result;
	}

>>>>>>> sth
}
