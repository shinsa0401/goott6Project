package com.boritgogae.board.tip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.domain.PagingInfo;
import com.boritgogae.board.tip.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;

	@Override
	public Map<String, Object> getListBoard(int pageNo) throws Exception {
		System.out.println("받아온 pageNo = " + pageNo);
		PagingInfo pi = pagingProcess(pageNo);
		List<BoardVo> lst = dao.selectAllBoard(pi);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lst", lst);
		map.put("pi", pi);
//		System.out.println("ServiceImpl : "+lst.toString());
		return map;
	}

	private PagingInfo pagingProcess(int pageNo) throws Exception {
		PagingInfo result = new PagingInfo();

		result.setTotalPostCnt(dao.getTotalPostCnt()); // 전체 글의 갯수 setting

		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지수 setting

		result.setStartNum(pageNo); // 현재페이지에서 출력을 시작할 글 번호(index)

		// --페이징블럭을 위한부분
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이지 블럭수 setting
		result.setCurrentPagingBlock(pageNo);// 현재페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		System.out.println(result.toString());

		return result;
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
			int bno = dao.maxBno();
			int ref = dao.updateRef(bno);
			if (ref == 1) {
				result = true;
			}
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
		int row = dao.updateBoard(bno, vo);
//		System.out.println("업데이트 성공했냐? : "+row);

		if (row == 1) {
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

	@Override
	public boolean addReplyBoard(BoardVo vo, int bno) throws Exception {
		boolean result = false;
		int row = dao.insertBoard(vo);
		int ref = dao.selectRef(bno);
		int parent = dao.selectMin(ref);
		int maxNo = dao.maxBno();
		if (row == 1 && ref == bno) {

			int row2 = dao.updateReplyRef(maxNo, ref);

			if (row2 == 1) {
				int cntRef = dao.countRef(ref);

				if (cntRef >= 2) {
					int row3 = dao.updateReplyRefOrder(cntRef, maxNo);

					if (row3 == 1) {
						result = true;
					}
				}

			}

		} else if (row == 1 && ref != bno) {
			int row2 = dao.updateReplyRef(maxNo, ref);

			if (row2 == 1) {
				int cntRef = dao.countRef(ref);

				if (cntRef >= 2) {
					int step = dao.stepNum(bno);
					int row3 = dao.updateReplyRefOrder(cntRef, maxNo,step);

					if (row3 == 1) {
						result = true;
					}
				}

			}

		}

		return result;
	}

}
