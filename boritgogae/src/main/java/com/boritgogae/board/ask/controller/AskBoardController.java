package com.boritgogae.board.ask.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.etc.UploadFile;
import com.boritgogae.board.ask.etc.UploadFileProcess;
import com.boritgogae.board.ask.service.AskBoardService;
import com.boritgogae.board.question.domain.BoardVo;

@Controller // 컨트롤러단
@RequestMapping("/board/ask/*") // board/ask 인 요청들 매핑
public class AskBoardController {
	
	@Inject
	private AskBoardService service; // 서비스 객체
	
	private List<UploadFile> UploadFileLst = new ArrayList();
	
	@RequestMapping(value = "/listAll")
	public void listAll(Model model, @RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo,
			RedirectAttributes rttr, SearchCriteria sc) throws Exception {
		System.out.println("컨트롤러 : 게시판 전체 목록 요청 페이지 번호 : " + pageNo);
		System.out.println(sc.toString());
		
		if(pageNo < 1) {
			pageNo = 1;
		}
		
		Map<String,Object> map = this.service.readAllBoard(pageNo, sc);
		List<AskBoardVo> lst = (List<AskBoardVo>)map.get("askBoardList");
		PagingInfo pi = (PagingInfo)map.get("pagingInfo");
		
		model.addAttribute("pagingInfo", pi); // 바인딩
		model.addAttribute("boardLst", lst); // 바인딩
		
		rttr.addFlashAttribute("pageNo", pageNo);
	}
	
	@RequestMapping(value = "/register")
	public String registerBoard() {
		System.out.println("컨트롤러 : 게시판 글쓰기 요청");
		return "board/writeBoard";
	}
	
	@RequestMapping(value="/imgFileUpload", method = RequestMethod.POST)
	public ResponseEntity<UploadFile> uploadFile(MultipartFile upfile, HttpServletRequest request){
		System.out.println("컨트롤러 : 파일 업로드요청");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());
		
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);
		
		ResponseEntity<UploadFile> result = null;
		
		if(upfile.getSize() > 0) {			
			UploadFile upFile;
			
			try {
				upFile = UploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(), upfile.getContentType());
				this.UploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠
				result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"
				
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
			}
		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
		}

		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.UploadFileLst.toArray()));
				
		return result;
	}
	
	
	
	
}
