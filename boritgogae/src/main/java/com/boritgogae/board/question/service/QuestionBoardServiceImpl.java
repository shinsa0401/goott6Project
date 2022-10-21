package com.boritgogae.board.question.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.boritgogae.board.question.domain.QuestionBoardVo;
import com.boritgogae.board.question.domain.QuestionReadCountVo;
import com.boritgogae.board.question.domain.QuestionUploadFileVo;

import com.boritgogae.board.question.etc.QuestionIPCheck;
import com.boritgogae.board.question.etc.QuestionPagingInfo;
import com.boritgogae.board.question.etc.QuestionSearchCriteria;
import com.boritgogae.board.question.etc.QuestionUploadFile;
import com.boritgogae.board.question.persistence.QuestionBoardDAO;

@Service // Service단임을 명시
public class QuestionBoardServiceImpl implements QuestionBoardService {

	// dao객체 주입
	@Inject
	private QuestionBoardDAO dao;
	
	// 게시판 글 전체 목록
	@Override
	public Map<String, Object> viewAllBoard(int pageNo, QuestionSearchCriteria sc) throws Exception {
		System.out.println("서비스 : 게시판 전체목록 요청");
		
		QuestionPagingInfo pi = pagingProcess(pageNo, sc);
		
		List<QuestionBoardVo> lst = null;
		
		if (sc.getSearchWord() != null && !sc.getSearchType().equals("")) { // 검색어가 있다
			lst = dao.getSearchResult(pi, sc);
		} else { // 검색어가 없을 때
			lst = dao.selectAllBoard(pi);
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardLst", lst);
		resultMap.put("pagingInfo", pi);
		
		
		return resultMap;
	}

	// 게시판 글 작성
	@Override
	public Map<String, Object> writeBoard(QuestionBoardVo board, List<QuestionUploadFile> uploadFileLst) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		// 글 저장
		int row = dao.insertBoard(board);
		int lastNo = 0;
		
		if (row == 1) {
			System.out.println("글 저장성공");
			// 방금 저장된 글번호 얻어오기
			lastNo = dao.getLastNo();
			System.out.println("lastNo 검색 : " + lastNo);
			// 얻어온 글번호 ref 업데이트 (계층형 답글 구현시 필요)
			if (dao.updateRef(lastNo) == 1) {
				System.out.println("ref업데이트 성공");
				
				// 업로드된 파일이 있다면 업로드된 파일의 개수 만큼 반복하여 uploadFile 테이블에 insert
				if (uploadFileLst.size() > 0) {
					for (QuestionUploadFile up : uploadFileLst) {
						if (up.isImage()) {
							dao.insertImg(lastNo, up.getSavedOriginImageFileName(), up.getThumbnailFileName());
						} else {
							dao.insertFile(lastNo, up.getSavedOriginImageFileName());
						}
					}
				}
				
				map.put("result", true);
				map.put("lastNo", lastNo);
			}
		}
		System.out.println(map.toString());
		return map;
	}

	// 게시글 보기
	@Override
	public Map<String, Object> viewBoard(int no) throws Exception {
		
		// 게시글 no번글 조회수증가(읽은 뒤 24시간후 증가)
		
		// 글을 조회하려는 클라이언트 ip주소를 얻어옴
		String ipAddr = QuestionIPCheck.getIpAddr();
		System.out.println(ipAddr);
		
		// Map에 글번호, ip주소 넣음
		Map<String, Object> readCount = new HashMap<>();
		readCount.put("bno", no); // no = 글번호
		readCount.put("ipAddr", ipAddr);
		
		System.out.println(readCount.toString());
		
		// 글을 조회했던 기록을 검색
		QuestionReadCountVo rc = dao.getLastReadDate(readCount);
					
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
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 게시글 정보 얻어오기
		QuestionBoardVo board = dao.getBoard(no);
		// 첨부파일 얻어오기
		List<QuestionUploadFileVo> fileList = dao.getAttachFiles(no);
		
		result.put("board", board);
		result.put("fileList", fileList);
		
		return result;
	}

	// 게시글 수정
	@Override
	public boolean modifyBoard(QuestionBoardVo board, List<QuestionUploadFile> uploadFileLst) throws Exception {
		boolean result = false;
		System.out.println("서비스단 글 수정");
		
		int no = board.getNo();
		
		int row = dao.updateBoard(board);
		if (row == 1) {
			System.out.println("글수정완료");
			
			// 업로드된 파일이 있다면 업로드된 파일의 개수 만큼 반복하여 uploadFile 테이블에 insert
			if (uploadFileLst.size() > 0) {
				for (QuestionUploadFile up : uploadFileLst) {
					if (up.isImage()) {
						dao.insertImg(no, up.getSavedOriginImageFileName(), up.getThumbnailFileName());
					} else {
						dao.insertFile(no, up.getSavedOriginImageFileName());
					}
				}
			}
			
			result = true;
		}
		
		System.out.println(board.toString());
		return result;
	}

	// 게시글 삭제 (삭제여부)
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
	
	
	
	// 페이징
	private QuestionPagingInfo pagingProcess(int pageNo, QuestionSearchCriteria sc) throws Exception {
		QuestionPagingInfo result = new QuestionPagingInfo();
		
		if (sc.getSearchWord() != null && !sc.getSearchType().equals("")) {
			// 검색어가 있다
			System.out.println("검색어가 있다 : " + sc.toString());
			result.setTotalPostCnt(dao.getSearchResultCnt(sc)); // 검색된 글의 개수를 가져와 setting
		} else {
			System.out.println("검색어가 없다");
			result.setTotalPostCnt(dao.getTotalPostCnt()); // 전체 글의 개수 setting
		}
		
		// --------------------- 페이징 구현 --------------------------------------------------------
		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지 수 setting
		result.setStartNum(pageNo); // 현재 페이지에서 출력을 시작할 글 번호(index)
		
		// --------------------- 페이징 블럭을 위한 부분 ---------------------------------------------
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이징 블럭 수 setting
		result.setCurrentPagingBlock(pageNo); // 현재 페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		
		System.out.println(result.toString());
		
		return result;
	}


}
