package com.boritgogae.board.free.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.boritgogae.board.free.dao.BoardDao;
import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.SearchCondition;
import com.boritgogae.board.free.domain.SearchCriterria;
import com.boritgogae.board.free.domain.UploadFileVo;
import com.boritgogae.board.free.etc.UploadFile;

@Service
public class BoardServiceImpl implements BoardService {

	
	
	@Inject
	BoardDao dao;
	
	

	@Override
	public boolean insertBoard(BoardVo vo,List<UploadFile> uploadFileLst) throws Exception {
		boolean result = false;
		int row =dao.insertWriter(vo);
		
		int row2 = 0;
		if(row==1) {
			
			
			int lastNo = dao.getLastNo();
			row2= dao.updateRef(lastNo);
			if(row2 == 1) {
				
				if(uploadFileLst.size()>0) {
					for(UploadFile up : uploadFileLst) {
						if(up.isImage()) {
							dao.imageInsert(lastNo,up.getSavedOriginImageFileName());
						}else {
							dao.fileInsert(lastNo, up.getSavedOriginImageFileName());
						}
					}
				}
			}
			if(row==1&&row2==1) {
				result=true;
			}
			
		}
		return result;
		
	}

	@Override
	public Map<String, Object> detailBoard(int bno) throws Exception {
		
		BoardVo board = dao.detail(bno);
		List<UploadFileVo> fileList = dao.Fileview(bno);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("fileList", fileList);
		System.out.println(board);
		return map;
	}

	@Override
	public void boardUpdate(BoardVo vo) throws Exception {
		dao.boardUpdate(vo);
		
	}

	@Override
	public void delBoard(int bno) throws Exception {
		dao.delBoard(bno);
		
	}

	@Override
	public void readCountUp( int bno) throws Exception {
		dao.readCountUp(bno);
	}

	@Override
	public List<BoardVo> listAll(Map map) throws Exception {
		
		
		
		return dao.listAll(map);
	}

	@Override
	public int getCount() throws Exception {
		
		return dao.count();
	}


	@Override
	public int deleteImg(int bno)throws Exception{
		
		return dao.deleteImg(bno);
	}

	@Override
	public List<BoardVo> listAllSearch(SearchCriterria sc) throws Exception {
		
		return dao.listAllSearch(sc);
	}

	@Override
	public int listAllSearchCnt(SearchCriterria sc) throws Exception {
		
		return dao.listAllSearchCnt(sc);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return dao.searchResultCnt(sc);
    }

    @Override
    public List<BoardVo> getSearchResultPage(SearchCondition sc) throws Exception {
        return dao.searchSelectPage(sc);
    }
	
	
    
	
	
	
	
	
	
	
	
	
}
