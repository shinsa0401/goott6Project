package com.boritgogae.board.ask.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.PagingInfo;
import com.boritgogae.board.ask.domain.SearchCriteria;
import com.boritgogae.board.ask.domain.UploadFileVo;
import com.boritgogae.board.ask.etc.UploadFile;
import com.boritgogae.board.ask.etc.UploadFileProcess;
import com.boritgogae.board.ask.service.AskBoardService;

@Controller // 컨트롤러단
@RequestMapping("/board/*") // board/ 인 요청들 매핑
public class AskBoardController {
	
	@Inject
	private AskBoardService service; // 서비스 객체
	
	private List<UploadFile> UploadFileLst = new ArrayList();
	
	@RequestMapping(value = "/ask")
	public String listAll(Model model, @RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo,
			RedirectAttributes rttr, SearchCriteria sc) throws Exception {
		System.out.println("컨트롤러 : 게시판 전체 목록 요청 페이지 번호 : " + pageNo);
		if(pageNo < 1) {
			pageNo = 1;
		}		
		Map<String,Object> map = this.service.readAllBoard(pageNo, sc);
		List<AskBoardVo> lst = (List<AskBoardVo>)map.get("askBoardList");
		PagingInfo pi = (PagingInfo)map.get("pagingInfo");
		List<AskCodeVo> askCodeList = service.loadAskCode();
		
		
		model.addAttribute("askBoardList", lst); // 바인딩
		model.addAttribute("pagingInfo", pi); // 바인딩
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		
		rttr.addFlashAttribute("pageNo", pageNo);
		return "boardAsk/viewAskAll";
	}
	
	@RequestMapping(value = "/ask/register")
	public String registerBoard(Model model) throws Exception {
		System.out.println("컨트롤러 : 게시판 글쓰기 요청");
		System.out.println("컨트롤러 : 문의코드 가져오기");
		List<AskCodeVo> askCodeList = service.loadAskCode();
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		System.out.println(askCodeList);
		return "boardAsk/writeAskBoard";
	}
	

	
	
	@RequestMapping(value = "/ask/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<UploadFile> uploadFile(MultipartFile upfile, HttpServletRequest request) {
		System.out.println("컨트롤러 : 파일 업로드요청");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());

		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);

		ResponseEntity<UploadFile> result = null;

		if (upfile.getSize() > 0) {
			UploadFile upFile;
			try {
				upFile = UploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
						upfile.getContentType());
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
	
	@RequestMapping(value = "/ask/delFile", method = RequestMethod.POST)
	public @ResponseBody String delFile(@RequestParam("deleteFileName") String deleteFileName,
			HttpServletRequest request) {
		System.out.println(deleteFileName + "지우기");
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		File deleteFile = new File(upPath + deleteFileName);
		
		boolean origin = false, thumb = false;
		for (UploadFile uf : this.UploadFileLst) {
			if (uf.getSavedOriginImageFileName().equals(deleteFileName)) { // 지워져야 할 파일이 있다
				origin = new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제

				if (uf.isImage()) {
					thumb = new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
					
				}
				this.UploadFileLst.remove(uf); // 리스트에서 삭제
				break;
			}
		}

		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.UploadFileLst.toArray()));

		// ResponseEntity<String> result = null;
		String result = "";
		if (origin) {
			// result = new ResponseEntity<>("success", HttpStatus.OK);
			result = "success";

		} else {
			// result = new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
			result = "fail";
		}
		
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/ask/writeCancel", method = RequestMethod.POST)
	public @ResponseBody String writeCancel(HttpServletRequest request) {
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");

		for (UploadFile uf : this.UploadFileLst) {
			new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제

			if (uf.isImage()) {
				new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
			}
		}

		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제
		
		return "success";
	}
	
	
	@RequestMapping(value = "/ask/create", method = RequestMethod.POST)
	public String createBoard(AskBoardVo board, RedirectAttributes rttr) throws Exception {
		System.out.println(board.toString());
		
		if(service.create(board, this.UploadFileLst)) {
			rttr.addFlashAttribute("status", "success");
		} else {
			rttr.addFlashAttribute("status", "error");
		}
		
		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해
		
		return "redirect:/board/ask"; // /board/ask 로 Redirect
	}
	
	
	@RequestMapping(value="/ask/view")
	public String viewAskBoard(@RequestParam("no") String no, Model model) {
		int bno = Integer.parseInt(no);
		System.out.println(no + "번 글 조회하기");
		
		Map<String, Object> map;
		AskBoardVo board = null;
		List<UploadFileVo> fileList = null;
		String askOption = "";
		
		try {
			map = service.viewBoard(bno);
			board = (AskBoardVo)map.get("board");
			fileList = (List<UploadFileVo>)map.get("fileList");
			askOption = service.readAskOptionByAskCode(board.getAskCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		model.addAttribute("askOption", askOption);
		
		System.out.println(board);
		System.out.println(fileList);
		System.out.println(askOption);
		
		return "boardAsk/viewAskBoard";
	}
	
	
	
}
