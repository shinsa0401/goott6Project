package com.boritgogae.board.question.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.BoardVo;
import com.boritgogae.board.question.domain.ReadCountVo;
import com.boritgogae.board.question.etc.IPCheck;
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
		
		// 게시글 no번글 조회수증가(읽은 뒤 24시간후 증가)
		
		// 글을 조회하려는 클라이언트 ip주소를 얻어옴
		String ipAddr = IPCheck.getIpAddr();
		System.out.println(ipAddr);
		
		// Map에 글번호, ip주소 넣음
		Map<String, Object> readCount = new HashMap<>();
		readCount.put("bno", no); // no = 글번호
		readCount.put("ipAddr", ipAddr);
		
		System.out.println(readCount.toString());
		
		// 글을 조회했던 기록을 검색
		ReadCountVo rc = dao.getLastReadDate(readCount);
					
		if (rc == null) { // 처음 조회하는 글이라면
			// ip주소, 글번호로 조회시간을 현재시간으로 insert
			if (dao.insertReadCount(readCount) == 1) {
				// 조회수 증가
				if (dao.updateReadCountBoard(no) == 1) {
					System.out.println("조회수 증가 완료");
				}
			}
			
		} else { // 기존에 조회했었던 글이라면
			// 전 조회 시간으로 부터 24시간 이상 지났으면 조회수 증가
			long now = System.currentTimeMillis(); // 현재시간
			long readDate = rc.getReadDate().getTime();
			long timeDiff = now - readDate;
			
			if (timeDiff >= 24 * 60 * 60 * 1000) { // 24시간 지남
				// ip주소, 글번호로 조회시간을 현재시간을 update
				if (dao.updateReadCount(readCount) == 1) {
					// 조회수 증가
					if (dao.updateReadCountBoard(no) == 1) {
						System.out.println("조회수 증가 완료");
					}
				}
			} else {
				System.out.println("24시간 지나지 않음");
			}
		}
		
		// 게시글 얻어오기
		BoardVo board = dao.getBoard(no);
		
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


}
