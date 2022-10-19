package com.boritgogae.board.question.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.question.domain.QuestionBoardVo;
import com.boritgogae.board.question.domain.QuestionUploadFileVo;
import com.boritgogae.board.question.etc.QuestionPagingInfo;
import com.boritgogae.board.question.etc.QuestionSearchCriteria;
import com.boritgogae.board.question.etc.QuestionUploadFile;
import com.boritgogae.board.question.etc.QuestionUploadFileProcess;
import com.boritgogae.board.question.service.QuestionBoardService;

@Controller // 컨트롤러 단임을 명시
@RequestMapping("/board/*")
public class QuestionBoardController {
	
	@Inject
	private QuestionBoardService service;
	
	private List<QuestionUploadFile> uploadFileLst = new ArrayList<>();
	
	/**
	 * @methodName : viewAllBoard
	 * @author : 신태호
	 * @date : 2022. 10. 4.
	 * @입력 param : int pageNo, SearchCriteria sc
	 * @returnType : String
	 * 게시판의 전체 게시글을 얻어온다
	 */
	@RequestMapping(value = "/question")
	public String viewAllBoard(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, RedirectAttributes rttr, QuestionSearchCriteria sc) throws Exception {
		System.out.println("컨트롤러 : 게시판 전체 목록 요청! 페이지 번호 : " + pageNo);
		System.out.println(sc.toString());
		
		if (pageNo < 1) {
			pageNo = 1;
		}
		
		Map<String, Object> map = this.service.viewAllBoard(pageNo, sc);
		List<QuestionBoardVo> lst = (List<QuestionBoardVo>) map.get("boardLst");
		QuestionPagingInfo pi = (QuestionPagingInfo) map.get("pagingInfo");
		
		model.addAttribute("boardLst", lst); // 바인딩
		model.addAttribute("pagingInfo", pi); // 바인딩
		
		rttr.addFlashAttribute("pageNo", pageNo);
		
		return "boardQuestion/viewAllBoard";
	}
	
	
	
	/**
	 * @methodName : newWriteBoard
	 * @author : 신태호
	 * @date : 2022. 10. 4.
	 * @입력 param :
	 * @returnType : String
	 * 전체목록에서 글작성 버튼을 누르면 작성페이지로 이동
	 */
	@RequestMapping(value = "/question/write")
	public String newWriteBoard() throws Exception {
		System.out.println("컨트롤러 : 게시판 글쓰기 페이지 요청");
		return "boardQuestion/writeBoard";
	}
	
	
	/**
	 * @methodName : writeBoard
	 * @author : 신태호
	 * @date : 2022. 10. 5.
	 * @입력 param : BoardVo board
	 * @returnType : String
	 * 게시글을 저장, 저장한 후 저장된글을 보여줌
	 */
	@RequestMapping(value = "/question/writeSave", method = RequestMethod.POST)
	public String writeBoard(QuestionBoardVo board) throws Exception {
		System.out.println("컨트롤러 : 글쓰기 요청");
		
		String result = "";
		
		Map<String, Object> map = new HashMap<>();
		map = service.writeBoard(board, this.uploadFileLst);
		
		if ((boolean) map.get("result")) {
			result = "redirect:/board/question/view?no="+ map.get("lastNo");
		} else {
			result = "redirect:/board/question";
		}
		
		this.uploadFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 왜? 이전 글 등록시 첨부했던 파일을 지우기 위해
		
		return result;
	}
	
	
	/**
	 * @methodName : viewBoard
	 * @author : 신태호
	 * @date : 2022. 10. 5.
	 * @입력 param : String no
	 * @returnType : String
	 * 게시글의 정보를 얻어와 출력
	 */
	@RequestMapping(value = "/question/view")
	public String viewBoard(@RequestParam("no") String no, Model model) {
		System.out.println("컨트롤러 : 게시판 글 상세보기 요청");
		int bno = Integer.parseInt(no);
		System.out.println(bno + " 번 글을 조회");
		
		Map<String, Object> map = new HashMap<>();
		QuestionBoardVo board = null;
		List<QuestionUploadFileVo> fileList = null;
		
		try {
			map = service.viewBoard(bno);
			
			board = (QuestionBoardVo) map.get("board");
			fileList = (List<QuestionUploadFileVo>) map.get("fileList");
			System.out.println(fileList.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		
		return "/boardQuestion/viewBoard";
	}
	
	
	/**
	 * @methodName : modifyBoard
	 * @author : 신태호
	 * @date : 2022. 10. 5.
	 * @입력 param : String no
	 * @returnType : String
	 * 게시글 수정을 눌렀을 때 기존 글의 정보를 불러옴
	 */
	@RequestMapping(value = "/question/modify")
	public String modifyBoard(@RequestParam("no") String no, Model model) {
		System.out.println("컨트롤러 : 게시판 글 수정 요청");
		// 수정하기위해 수정할 글의 정보를 얻어 와서 뷰단에 출력
		int bno = Integer.parseInt(no);
		
		Map<String, Object> map = new HashMap<>();
		QuestionBoardVo board = null;
		List<QuestionUploadFileVo> fileList = null;
		
		
		try {
			map = service.viewBoard(bno);
			
			board = (QuestionBoardVo) map.get("board");
			fileList = (List<QuestionUploadFileVo>) map.get("fileList");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(fileList.toString());
		
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		
		return "/boardQuestion/modifyBoard";
	}
	
	
	/**
	 * @methodName : modifySave
	 * @author : 신태호
	 * @date : 2022. 10. 5.
	 * @입력 param : BoardVo board
	 * @returnType : String
	 * 게시글 수정하고 수정된 글을 보여줌
	 */
	@RequestMapping(value = "/question/modifySave", method = RequestMethod.POST)
	public String modifySave(QuestionBoardVo board) throws Exception {
		String result = "";
		if (service.modifyBoard(board, this.uploadFileLst)) {
			result = "redirect:/board/question/view/?no="+ board.getNo();
		}
		
		// 리스트의 모든 아이템 삭제 
		this.uploadFileLst.clear();
		
		System.out.println(board.toString());
		return result;
	}
	
	
	
	/**
	 * @methodName : removeBoard
	 * @author : 신태호
	 * @date : 2022. 10. 6.
	 * @입력 param : int no, String pwd
	 * @returnType : String
	 * 게시글 삭제의 삭제여부를 N -> Y로 변경, 뷰단에는 출력되지않음
	 */
	@RequestMapping(value = "/question/remove", method = RequestMethod.POST)
	public @ResponseBody String removeBoard(@RequestParam("no")int no, @RequestParam("pwd")String pwd) throws Exception {
		String result = null;
		System.out.println("삭제 요청");
		if (service.removeBoard(no, pwd)) {
			result = "success";
			System.out.println("삭제 성공");
		} else {
			result = "fail";
			System.out.println("삭제 실패");
		}
		
		return result;
	}
	
	
	/**
	 * @methodName : uploadFile
	 * @author : 신태호
	 * @throws IOException 
	 * @date : 2022. 9. 13.
	 * @입력 param :
	 * @returnType : void
	 * ajax로 업로드된 파일을 저장하고, 업로드 된 파일 리스트에 추가
	 */
	@RequestMapping(value = "/question/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<QuestionUploadFile> uploadFile(MultipartFile upFiles, HttpServletRequest request) {
		System.out.println("파일이 업로드 됨");
		
		System.out.println("업로드된 파일 이름 : " + upFiles.getOriginalFilename());
		System.out.println("파일의 사이즈 : " + upFiles.getSize());
		System.out.println("업로드된 파일의 타입 : " + upFiles.getContentType());
		
		// 파일이 실제 저장 될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upPath);
		
		ResponseEntity<QuestionUploadFile> result = null;
		
		if (upFiles.getSize() > 0) {
			QuestionUploadFile upFile;
			try {
				upFile = QuestionUploadFileProcess.uploadFileProcess(upPath, upFiles.getOriginalFilename(), upFiles.getBytes(), upFiles.getContentType());
				this.uploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠
				
				result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"을 json으로 보냄
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신 "실패"를 json으로 보냄
			}
			
		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신 "실패"를 json으로 보냄
		}
		
		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.uploadFileLst.toArray()));
		
		return result;
		
	}
	
	
	
	/**
	 * @methodName : deleteFile
	 * @author : 신태호
	 * @date : 2022. 10. 11.
	 * @입력 param :
	 * @returnType : String
	 * 업로드 공간에 올라간 실제 저장된 파일과 썸네일 삭제
	 */
	@RequestMapping(value = "/question/deleteFile", method = RequestMethod.POST)
	public @ResponseBody String deleteFile(@RequestParam("deleteFileName") String deleteFileName, HttpServletRequest request) {
		System.out.println(deleteFileName + "을 지웁니다");
		// 파일이 실제 저장 될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		File deleteFile = new File(upPath + deleteFileName);
		
		boolean origin = false, thumb = false;
		for (QuestionUploadFile uf : this.uploadFileLst) {
			if (uf.getSavedOriginImageFileName().equals(deleteFileName)) { // 지워져야 할 파일이 있다
				origin = new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제
				
				if (uf.isImage()) {
					thumb = new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
					break;
				} else {
					break;
				}
			}
			this.uploadFileLst.remove(uf); // 리스트에서 삭제
		}
		
		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.uploadFileLst.toArray()));
		
		String result = null;
		if (origin) { // 삭제되었다면
			result = "success";
		} else {
			result = "fail";
		}
		
		return result;
		
	}
	
	
	/**
	 * @methodName : writeCancel
	 * @author : 신태호
	 * @date : 2022. 10. 11.
	 * @입력 param :
	 * @returnType : String
	 * 글을 저장하지않고 취소했을 때 업로드된 파일을 저장하지않고 초기화
	 */
	@RequestMapping(value = "/question/writeCancel", method = RequestMethod.POST)
	public @ResponseBody String writeCancel(HttpServletRequest request) {
		// 파일이 실제 저장 될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		
		for (QuestionUploadFile uf : this.uploadFileLst) {
			new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제
				
			if (uf.isImage()) {
				new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
			}
		}
		this.uploadFileLst.clear(); // 리스트의 모든 아이템 삭제
		
		return "success";
		
	}
	
	
	
}
