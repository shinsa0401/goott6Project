package com.boritgogae.board.market.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.market.dao.MarketBoardDAO;
import com.boritgogae.board.market.domain.MarketBoardVO;
import com.boritgogae.board.market.domain.MarketPaging;
import com.boritgogae.board.market.domain.MarketSearchCriteria;
import com.boritgogae.board.market.domain.MarketUploadFileVo;
import com.boritgogae.board.market.etc.MarketUploadFile;

@Service
public class MarketBoardServiceImpl implements MarketBoardService {

	@Inject
	private MarketBoardDAO dao;

	@Override
	public boolean write(MarketBoardVO board, List<MarketUploadFile> uploadFileLst) throws Exception {
		boolean result = false;

		int row = dao.write(board);

		int row2 = 0;

		if (row == 1) {
			// 2) 방금 insert된 글 번호를 가져와서 해당 글의 ref에 update
			// 2-1) 방금 insert된 글의 no 가져오기

			int lastNo = dao.getLastNo();
			// 2-2) ref update

			row2 = dao.updateRef(lastNo);
			if (row2 == 1) {
				// 3) 업로드 된 파일이 있다면 업로드 된 파일의 갯수만큼 반복하여 uploadfile 테이블에 insert
				if (uploadFileLst.size() > 0) {

					for (MarketUploadFile up : uploadFileLst) {

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
	public Map<String, Object> getAllBoard(int pageNo, MarketSearchCriteria sc) throws Exception { 
		MarketPaging pi = pagingPro(pageNo, sc);

		List<MarketBoardVO> List = null;

		if (sc.getSearchWord() != null && !sc.getSearchWord().equals("")) {
			List = dao.getSearchResult(pi, sc);
			
		} else {
			List = dao.getAllBoard(pi);
		}

		Map<String, Object> ListMap = new HashMap<String, Object>();
		ListMap.put("boardList", List);
		ListMap.put("paging", pi);

		return ListMap;
	}

	// 페이징 구현
	private MarketPaging pagingPro(int pageNo, MarketSearchCriteria sc) throws Exception {

		MarketPaging result = new MarketPaging();

		// 검색 결과에 따른 페이징
		if (sc.getSearchWord() != null && !sc.getSearchType().equals("")) {
			// 검색어가 있다
			System.out.println("검색어가 있다 : " + sc.toString());
			result.setTotalPostCnt(dao.getSearchResultCnt(sc)); // 검색된 글의 갯수를 가져와 setting

		} else {
			System.out.println("검색어가 없다 : " + sc.toString());
			result.setTotalPostCnt(dao.getTotalCnt()); // 전체 글의 갯수 setting
		}

		// -------------------------페이징 구현----------------------------------

		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지 수 setting
		result.setStartNum(pageNo); // 현재 페이지에서 출력을 시작할 글 번호(index)

		// --------------------페이징 블럭을 위한 부분--------------------------------
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이징 블럭 수 setting
		result.setCurrentPagingBlock(pageNo); // 현재 페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting

		System.out.println(result.toString());

		return result;
	}

	
	@Override
	public Map<String, Object> viewContent(int no) throws Exception {
		MarketBoardVO board = dao.getContent(no);

		List<MarketUploadFileVo> fileList = dao.getAttachFile(no);

		Map<String, Object> ListMap = new HashMap<>();
		ListMap.put("board", board);
		ListMap.put("fileList", fileList);

		return ListMap;
	}

	// 글 수정하기
	@Override
	public void modify(MarketBoardVO board) throws Exception {

		dao.modify(board);

	}

	// 글 삭제하기
	@Override
	public void delete(int no) throws Exception {

		dao.delete(no);

	}

	// 조회수
	@Override
	public int readCnt(int no) throws Exception {

		return dao.readCnt(no);
	}

}
