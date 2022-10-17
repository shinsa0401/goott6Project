package com.boritgogae.board.ask.persistence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadFile;
import com.boritgogae.board.ask.domain.UploadFileVo;

@Repository
public interface AskBoardDAO {
	// 게시판 전체 목록 가져오기
	public List<AskBoardVo> selectAllBoard(PagingInfo pi) throws Exception;
	
	// 검색어가 있을 때 페이징을 하며 검색 결과를 가져오는 메서드
	public List<AskBoardVo> getSearchResult(SearchCriteria sc, PagingInfo pi) throws Exception;

	// 검색된 글의 갯수를 반환하는 메서드
	public int getSearchResultCnt(SearchCriteria sc) throws Exception;
	
	// 전체 글의 갯수 반환하는 메서드
	public int getTotalPostCnt() throws Exception;

	// 문의코드와 코드내용을 반환하는 메서드
	public List<AskCodeVo> loadAskCode() throws Exception;

	// 게시판에 글 등록 하는 메서드
	public int insertAskBoard(AskBoardVo board) throws Exception;

	// 게시판 글 업데이트 메서드
	public int updateAskBoard(AskBoardVo board) throws Exception;

	// 최근 등록된 글의 글번호 가져오는 메서드
	public int getLastNo() throws Exception;

	// 최근 등록된 글의 ref 업데이트
	public int updateRef(int lastNo) throws Exception;

	// 업로드된 파일이 이미지인 경우
	public void imageInsert(int lastNo, String savedOriginImageFileName, String thumbnailFileName) throws Exception;

	// 업로드된 파일이 이미지가 아닌 경우
	public void fileInsert(int lastNo, String savedOriginImageFileName) throws Exception;

	// 게시글 조회하기
	public AskBoardVo getBoard(int bno)throws Exception;

	// 게시글 번호에 맞는 첨부파일 조회하기
	public List<UploadFileVo> getAttachFile(int bno)throws Exception;

	// 문의코드를 보내고 그에 맞는 문의옵션을 가져오는 메서드 
	public String readAskOptionByAskCode(String askCode)throws Exception;

	// 조회된 글의 조회수 증가 (컬럼 만들기)
	public int createReadCount(int bno, String clientIp) throws Exception;

	// 특정 IP와 글번호에 대해 가장 최근 읽은 시간을 불러오는 메서드
	public String checkRecentlyRead(int bno, String clientIp)throws Exception;

	// 글번호에 따른 조회수를 가져오는 메서드
	public int getReadCountByBno(int askBno) throws Exception;

	// 같은 ref를 가진 보드들 업데이트
	public int updateBoardsRef(AskBoardVo board) throws Exception;

	// 게시판에 답글 등록하는 메서드
	public int answerCreate(AskBoardVo board) throws Exception;

	// 타겟 보드의 답변상태를 Y로 만드는 메서드
	public int answerStatusOk(int askBno) throws Exception;

	// 특정 글번호를 가진 글을 삭제(isDelete를 Y로 바꿔주는것뿐임)
	public int removeBoard(int no) throws Exception;

	// 특정 글의 정보를 가져오는 메서드(기본 : 수정용)
	public AskBoardVo getboardVo(String bno) throws Exception;
	
	// 글에 첨부되어있는 파일리스트를 가져오는 메서드
	public List<UploadFile> showFileList(String bno) throws Exception;
	
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
	public  List<AskBoardVo> readFAQBoard() throws Exception;

	// 특정 보드의 조회수를 1 증가시킨다.
	public int readCountUp(int bno) throws Exception;

	

}
