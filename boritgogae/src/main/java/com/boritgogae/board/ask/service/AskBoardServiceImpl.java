package com.boritgogae.board.ask.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.AskPagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadAskFile;
import com.boritgogae.board.ask.domain.UploadAskFileVo;
import com.boritgogae.board.ask.persistence.AskBoardDAO;

@Service
public class AskBoardServiceImpl implements AskBoardService {

	@Inject
	public AskBoardDAO dao;

	@Override
	public Map<String, Object> readAllBoard(int pageNo, SearchCriteria sc) throws Exception {
		System.out.println("서비스단 : 게시판 전체 목록 요청");
		System.out.println("서비스단 : sc " + sc.toString());
		AskPagingInfo pi = pagingProcess(pageNo, sc);
		List<AskBoardVo> lst = null;
		if (sc.getSearchWord() != null && !sc.getSearchType().equals("")) { // 검색어가 있다면
			lst = dao.getSearchResult(sc, pi);
		} else {
			lst = dao.selectAllBoard(pi);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("askBoardList", lst);
		resultMap.put("pagingInfo", pi);

		return resultMap;
	}

	@Override
	public List<AskCodeVo> loadAskCode() throws Exception {
		System.out.println("서비스단 : 문의VO 요청");
		List<AskCodeVo> lst = null;
		lst = dao.loadAskCode();
		System.out.println(lst);

		return lst;
	}

	private AskPagingInfo pagingProcess(int pageNo, SearchCriteria sc) throws Exception {
		AskPagingInfo result = new AskPagingInfo();
		System.out.println("pagingProcess : sc " + sc.toString());
		if (sc.getSearchWord() != null && !sc.getSearchType().equals("")) {
			// 검색어가 있을 때
			System.out.println("검색어가 있다! : " + sc.toString());
			result.setTotalPostCnt(dao.getSearchResultCnt(sc)); // 검색된 글의 갯수를 가져와 세팅
		} else {
			System.out.println("검색어가 없다!");
			result.setTotalPostCnt(dao.getTotalPostCnt()); // 전체 글 갯수 세팅
		}

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

	@Override
	public boolean create(AskBoardVo board, List<UploadAskFile> uploadFileLst) throws Exception {
		boolean result = false;
		System.out.println("글 등록중");

		board.setContents(board.getContents().replace("\r\n", "<br />")); // 게시글 내용.. 줄바꿈 처리

		// 1) 넘어온 board를 insert
		int row = dao.insertAskBoard(board);
		int row2 = 0;

		if (row == 1) {
			// 2) 방금 insert된 글번호를 가져와서 해당 글의 ref에 update
			// 2-1) 방금 insert된 글의 no 가져오기
			int lastNo = dao.getLastNo();
			// 2-2) ref update
			row2 = dao.updateRef(lastNo);

			if (row2 == 1) {
				// 3) 업로드된 파일이 있다면 업로드된 파일의 갯수만큼 반복하여 uploadfile 테이블에 insert
				if (uploadFileLst.size() > 0) {
					for (UploadAskFile up : uploadFileLst) {
						if (up.isImage()) {
							dao.imageInsert(lastNo, up.getSavedOriginImageFileName(), up.getThumbnailFileName());
						} else {
							dao.fileInsert(lastNo, up.getSavedOriginImageFileName());
						}
					}
				}

			}

		}

		if (row == 1 && row2 == 1) {
			result = true;
		}

		return result;
	}

	@Override
	public boolean answerCreate(AskBoardVo board, List<UploadAskFile> uploadFileLst) throws Exception {
		boolean result = false;
		System.out.println("답글 등록중 서비스단" + board);
		board.setContents(board.getContents().replace("\r\n", "<br />")); // 게시글 내용.. 줄바꿈 처리

		System.out.println("서비스단 Board값 1" + board);
		// 현재 ref에 들어가있는 값은 참고해야할 참조해야할 bno값이다.
		// 이 값을 통해 board의 ref, refOrder, step 값을 찾아오자
		AskBoardVo targetBoard = dao.getBoard(board.getRef());
		System.out.println("서비스단 targetBoard값 " + targetBoard);

		// 기존에 같은 ref를 가지고 있던 댓글들의 ref를 update함.
		int result1 = dao.updateBoardsRef(targetBoard);

		// 답글 등록하기
		// 타겟보드의 ref, refOrder, step값을 넣어준다.
		board.setRef(targetBoard.getRef());
		board.setRefOrder(targetBoard.getRefOrder());
		board.setStep(targetBoard.getStep());

		// board를 insert
		int result2 = dao.answerCreate(board);

		// 만약 AnswerStatus에 A를 주어서 답변완료 체크를 했다면,
		if(board.getAnswerStatus() == "A") {
			// 타겟보드의 answerStatus를 Y로 만들어준다.
			// 아닐경우엔 그냥 현상유지 하면 된다.
			dao.answerStatusOk(targetBoard.getAskBno());
		}

		if (result2 == 1) {
			int lastNo = dao.getLastNo();
			// 3) 업로드된 파일이 있다면 업로드된 파일의 갯수만큼 반복하여 uploadfile 테이블에 insert
			if (uploadFileLst.size() > 0) {
				for (UploadAskFile up : uploadFileLst) {
					if (up.isImage()) {
						dao.imageInsert(lastNo, up.getSavedOriginImageFileName(), up.getThumbnailFileName());
					} else {
						dao.fileInsert(lastNo, up.getSavedOriginImageFileName());
					}
				}
			}

		}

		if (result2 == 1) {
			result = true;
		}

		return result;
	}

	@Override
	public Map<String, Object> viewBoard(int bno, String clientIp) throws Exception {
		// no번 글을 DB에서 셀렉트함
		AskBoardVo board = dao.getBoard(bno);
		// no번 글에 첨부되어 있는 파일 DB에서 select
		List<UploadAskFileVo> fileList = dao.getAttachFile(bno);
		// bno번 글 조회수 증가시키는 메서드
		// 동일 아이피와 글번호를 가진 컬럼이 있는지 확인하기
		String recentlyRead = dao.checkRecentlyRead(bno, clientIp);
		// 읽은 시간이 없다면?
		if (recentlyRead == null) {
			// 컬럼에 새로 데이터를 만들어준다.
			dao.createReadCount(bno, clientIp);
			// askBoard에도 ReadCount를 추가해준다.
			dao.readCountUp(bno);
		} else { // 읽은 시간이 있다면 가장 최근의 읽은시간을 가져온다.
			Timestamp recentlyReadTs = Timestamp.valueOf(recentlyRead);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			System.out.println("읽은시간 : " + recentlyReadTs);
			System.out.println("현재시간 : " + currentTime);

			// 읽었던 시간에 +1일 해준 후 현재시간과 비교함.
			Calendar cal = Calendar.getInstance();
			cal.setTime(recentlyReadTs);
			cal.add(Calendar.DATE, +1);
			recentlyReadTs.setTime(cal.getTime().getTime());
			System.out.println("읽었던시간 + 1일 : " + recentlyReadTs);

			// 비교함. true일 때만 업데이트 해줌.
			System.out.println("1일이 지났나? : " + currentTime.after(recentlyReadTs));
			// 지났다면
			if (currentTime.after(recentlyReadTs)) {
				// 컬럼에 새로 데이터를 만들어준다.
				dao.createReadCount(bno, clientIp);
				// askBoard에도 ReadCount를 추가해준다.
				dao.readCountUp(bno);
			}
			// 지나지 않았다면 아무것도 하지 않는다.
			// 1일이 지나거나 처음일때만 컬럼을 생성한다.
		}

		// Map을 통해 묶어서 반환해준다.
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("board", board);
		returnMap.put("fileList", fileList);

		return returnMap;
	}

	@Override
	public String readAskOptionByAskCode(String askCode) throws Exception {
		return dao.readAskOptionByAskCode(askCode);
	}

	@Override
	public int getReadCountByBno(int askBno) throws Exception {
		return dao.getReadCountByBno(askBno);
	}

	@Override
	public int removeBoard(int no) throws Exception {
		System.out.println("service : removeBoard : no = " + no);
		return dao.removeBoard(no);
	}

	@Override
	public AskBoardVo getBoardVo(String no) throws Exception {
		System.out.println("service : getBoardVo : no = " + no + "번");
		return dao.getboardVo(no);

	}

	// 글에 첨부되어있는 파일리스트를 가져오는 메서드
	@Override
	public List<UploadAskFile> showFileList(String no) throws Exception {
		System.out.println("service : showFileList : no = " + no + "번");
		return dao.showFileList(no);
	}

	// 게시판 글 등록 + (업로드된 파일 등록) 하는 메서드
	@Override
	public boolean update(AskBoardVo board, List<UploadAskFile> addTempFileLst) throws Exception {
		boolean result = false;
		System.out.println("글 수정등록중");

		board.setContents(board.getContents().replace("\r\n", "<br />")); // 게시글 내용.. 줄바꿈 처리
		
		
		// 1) 넘어온 board를 insert
		int row = dao.updateAskBoard(board);
		int row2 = 0;

		if (row == 1) {
				// 3) 업로드된 파일이 있다면 업로드된 파일의 갯수만큼 반복하여 uploadfile 테이블에 insert
				if (addTempFileLst.size() > 0) {
					for (UploadAskFile up : addTempFileLst) {
						if (up.isImage()) {
							dao.imageInsert(board.getAskBno(), up.getSavedOriginImageFileName(), up.getThumbnailFileName());
						} else {
							dao.fileInsert(board.getAskBno(), up.getSavedOriginImageFileName());
						}
					}
				}
		}

		if (row == 1 && row2 == 1) {
			result = true;
		}

		return result;
	}
	
	// DB에서 파일명을 가지고 있는 컬럼 삭제
	@Override
	public int deleteFileDB(String savedOriginImageFileName) throws Exception {
		return dao.deleteFileDB(savedOriginImageFileName);
	}

	// 특정 글의 좋아요 갯수를 파악한다.
	@Override
	public int getLikeCount(int askBno) throws Exception {
		return dao.getLikeCount(askBno);
	}
	
	// 특정 글의 조회수 갯수를 파악한다.
	@Override
	public int getReadCount(int askBno) throws Exception {
		return dao.getReadCount(askBno);
	}

	// 특정 아이피가 특정 글을 추천을 했는지 확인하기
	@Override
	public int likeCheck(int no, String clientIp) throws Exception {
		return dao.likeCheck(no, clientIp);
	}
	
	// 특정 아이피의 특정 글 추천을 삭제
	@Override
	public int deleteLike(int no, String clientIp) throws Exception {
		return dao.deleteLike(no, clientIp);
	}
	
	// 특정 아이피의 특정 글 추천을 등록
	@Override
	public int insertLike(int no, String clientIp) throws Exception {
		return dao.insertLike(no, clientIp);
	}

	// FAQ중 조회수 상위 3개의 정보를 가져온다.
	@Override
	public List<AskBoardVo> readFAQBoard() throws Exception {
		return dao.readFAQBoard();
	}

}