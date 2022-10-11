package com.boritgogae.board.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.etc.PagingInfo;
import com.boritgogae.board.notice.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	private NoticeDAO dao;
	
	@Override
	public Map<String, Object> getNoticeBoard(int pageNo) throws Exception {
		
		PagingInfo pi = pagingProcess(pageNo);

		List<NoticeVo> lst = null;

		lst = dao.getNoticeBoard(pi);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardList", lst);
		resultMap.put("pagingInfo", pi);
		
		return resultMap;
	}

	@Override
	public boolean registerBoard(NoticeVo board) throws Exception {
		boolean result = false;
		
		
		if(dao.registerBoard(board) == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public NoticeVo viewBoard(int bno) throws Exception {
		dao.updateReadCount(bno);
		
		NoticeVo board = dao.viewBoard(bno);
		return board;
	}

	@Override
	public boolean deleteBoard(int bno) throws Exception {
		boolean result = false;
		
		if (dao.deleteBoard(bno) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modifyBoard(NoticeVo board) throws Exception {
		boolean result = false;
		if(dao.modifyBoard(board) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean registerReply(NoticeReplyVo replyBoard) throws Exception {
		System.out.println(replyBoard.getRno());
		boolean result = false;
		int ref = replyBoard.getRef();
		
		int row = 0;
		int row2 = 0;
		int lastNo = 0;
		if(replyBoard.getRno() != 0) { // 대댓글일 경우
			dao.updateRefOrder(replyBoard);
			row = dao.registerReplyRepl(replyBoard);
			lastNo = dao.getLastNo();
			row2 = dao.updateRefFromRno(ref, lastNo);
			
			System.out.println(dao.getRefOrder(lastNo));
			
			
		} else { // 댓글일 경우
			row = dao.registerReply(replyBoard);
			lastNo = dao.getLastNo();
			row2 = dao.updateRef(lastNo);
			
		}
		if(row == 1 && row2 == 1) {
			result = true;
		}
		
		return result;
	}

	@Override
	public List<NoticeReplyVo> getReplyList(int bno) throws Exception {
		return dao.getReplyList(bno);
	}

	@Override
	public boolean deleteReplyBoard(int rno) throws Exception {
		boolean result = false;
		
		if (dao.deleteReplyBoard(rno) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public String getNickName(String memberId) throws Exception {
		return dao.getNickName(memberId);
	}

	@Override
	public boolean modifyReplyBoard(NoticeReplyVo board) throws Exception {
		boolean result = false;
		
		if (dao.modifyReplyBoard(board) == 1) {
			result = true;
		}
		
		return result;
	}
	
	private PagingInfo pagingProcess(int pageNo) throws Exception {
		PagingInfo result = new PagingInfo();

		result.setTotalPostCnt(dao.getNoticeBoardCnt()); // 전체 글의 갯수 setting
		
		// ------------------------------ 페이징 구현 ------------------------------
		
		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지 수 setting
		result.setStartNum(pageNo); // 현재 페이지에서 출력을 시작할 글 번호(index)
		
		// ------------------------------ 페이징 블럭을 위한 부분 ------------------------------
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이징 블럭 수 setting
		result.setCurrentPagingBlock(pageNo); // 현재 페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		
		System.out.println(result.toString());
		
		return result;
	}
	
	
}
