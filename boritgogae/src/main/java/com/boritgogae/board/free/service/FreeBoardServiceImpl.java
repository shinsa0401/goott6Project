package com.boritgogae.board.free.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.boritgogae.board.free.domain.FreeBoardVo;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.free.domain.FreeUploadFileVo;
import com.boritgogae.board.free.etc.FreeUploadFile;
import com.boritgogae.board.free.persistence.FreeBoardDao;



@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	
	
	@Inject
	FreeBoardDao dao;
	
	

	@Override
	public boolean insertBoard(FreeBoardVo vo,List<FreeUploadFile> uploadFileLst) throws Exception {
		boolean result = false;
		int row =dao.insertWriter(vo);
		
		int row2 = 0;
		if(row==1) {
			
			
			int lastNo = dao.getLastNo();
			row2= dao.updateRef(lastNo);
			if(row2 == 1) {
				
				if(uploadFileLst.size()>0) {
					for(FreeUploadFile up : uploadFileLst) {
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
		
		FreeBoardVo board = dao.detail(bno);
		List<FreeUploadFileVo> fileList = dao.Fileview(bno);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("fileList", fileList);
		System.out.println(board);
		return map;
	}

	@Override
	public void boardUpdate(FreeBoardVo vo) throws Exception {
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
	public List<FreeBoardVo> listAll(Map map) throws Exception {
		
		
		
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
    public int getSearchResultCnt(FreeSearchCondition sc) throws Exception {
        return dao.searchResultCnt(sc);
    }

    @Override
    public List<FreeBoardVo> getSearchResultPage(FreeSearchCondition sc) throws Exception {
        return dao.searchSelectPage(sc);
    }
	
	
    
	
	
	
	
	
	
	
	
	
}
