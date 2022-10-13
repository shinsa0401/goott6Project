package com.boritgogae.board.market.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.board.market.dao.BoardDAO;
import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.domain.Criteria;
import com.boritgogae.board.market.domain.Paging;
import com.boritgogae.board.market.domain.SearchCriteria;
import com.boritgogae.board.market.etc.UploadFile;
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject 
	private BoardDAO dao;
	
	@Override
	public boolean write(BoardVO board) throws Exception {
		boolean result = true; //임시
		
		int row = dao.write(board);
		
		return result;
	
	}

	@Override
	public Map<String, Object> getAllBoard(SearchCriteria sc, Criteria cri) throws Exception { //페이징, 검색어 아직 안함
		List<BoardVO>List = null;
		
//		Paging pi = pagingPro(sc, pageNo);
		
		if(sc.getSearchWord() != null && !sc.getSearchWord().equals("")) {
			List = dao.getSearchResult(sc);
		}else {
			List = dao.getAllBoard();
		}
		
		Map<String, Object> ListMap = new HashMap<String, Object>();
		ListMap.put("boardList", List);
		
		return ListMap;
	}
	
	
//	@Override
//	public Map<String, Object> getAllBoard(SearchCriteria sc, int pageNo) throws Exception { //페이징, 검색어 아직 안함
//		List<BoardVO>List = null;
//		
////		Paging pi = pagingPro(sc, pageNo);
//		
//		if(sc.getSearchWord() != null && !sc.getSearchWord().equals("")) {
//			List = dao.getSearchResult(sc);
//		}else {
//			List = dao.getAllBoard();
//		}
//		
//		Map<String, Object> ListMap = new HashMap<String, Object>();
//		ListMap.put("boardList", List);
//		
//		return ListMap;
//	}
	
	//페이징 구현
//	private Paging pagingPro(SearchCriteria sc, int pageNo)throws Exception{
//		Paging result = new Paging();
//		
//		//검색결과 페이징 처리
//		if(sc.getSearchWord    ()!=null && !sc.getSearchWord().equals("")) {
//			result.setTotalPostCnt(dao.getSearchResult(sc));//검색된 글
//			
//		}
//		return result;
//	}
	
	//이미지파일, 조회수 안함
	@Override
	public Map<String, Object> viewContent(int no) throws Exception {
		BoardVO board = dao.getContent(no);
		
		Map<String, Object> ListMap = new HashMap<String, Object>();
		ListMap.put("board", board);
		
		return ListMap;
	}

	//파일 수정 안함
	@Override
	public void modify(BoardVO board) throws Exception {
		 
		dao.modify(board);
		
	}

	@Override
	public void delete(int no) throws Exception {
		
		dao.delete(no);
		
	}

	@Override
	public int readCnt(int no) throws Exception {
		
		return dao.readCnt(no);
	}

	

}
