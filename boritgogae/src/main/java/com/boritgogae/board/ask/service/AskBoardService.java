package com.boritgogae.board.ask.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadAskFile;

@Service
public interface AskBoardService {
	// 게시판 전체 목록을 가져오는 메서드
	public Map<String, Object> readAllBoard(int pageNo, SearchCriteria sc) throws Exception;

	// 질문코드와 질문코드에 따른 분류를 가져오는 메서드
	public List<AskCodeVo> loadAskCode() throws Exception;

	// 게시판 글 등록 + (업로드된 파일 등록) 하는 메서드
	public boolean create(AskBoardVo board, List<UploadAskFile> uploadFileLst) throws Exception;
	
	// 게시판 수정에서 수정완료하는 메서드
	public boolean update(AskBoardVo board, List<UploadAskFile> addTempFileLst) throws Exception;
	
	// 게시판 답글 등록 + (업로드된 파일 등록) 하는 메서드
	public boolean answerCreate(AskBoardVo board, List<UploadAskFile> uploadFileLst) throws Exception;

	// 게시판 조회
	public Map<String, Object> viewBoard(int bno, String clientIp) throws Exception;

	// 문의코드를 보내고 그에 맞는 문의옵션을 가져오는 메서드 
	public String readAskOptionByAskCode(String askCode) throws Exception;

	// 글번호에 따른 조회수를 가져오는 메서드
	public int getReadCountByBno(int askBno) throws Exception;

	// 특정 글을 삭제하는 메서드
	public int removeBoard(int no) throws Exception;

	// 특정 글의 정보를 가져오는 메서드(기본 : 수정용)
	public AskBoardVo getBoardVo(String no) throws Exception;

	// 글에 첨부되어있는 파일리스트를 가져오는 메서드
	public List<UploadAskFile> showFileList(String no) throws Exception;

	// DB에서 파일명을 가지고 있는 컬럼 삭제
	public int deleteFileDB(String savedOriginImageFileName) throws Exception;

	// 특정 글의 좋아요 갯수를 파악한다.
	public int getLikeCount(int askBno) throws Exception;

	// 특정 글의 조회수 갯수를 파악한다.
	public int getReadCount(int askBno) throws Exception;
	
	// 특정 아이피가 특정 글을 추천을 했는지 확인하기
	public int likeCheck(int no, String clientIp) throws Exception;

	// 특정 아이피의 특정 글 추천을 삭제
	public int deleteLike(int no, String clientIp) throws Exception;

	// 특정 아이피의 특정 글 추천을 등록
	public int insertLike(int no, String clientIp) throws Exception;

	// FAQ중 조회수 상위 3개의 정보를 가져온다.
	public List<AskBoardVo> readFAQBoard() throws Exception;




	
	
}
