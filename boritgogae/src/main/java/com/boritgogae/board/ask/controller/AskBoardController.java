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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.ask.domain.AskBoardVo;
import com.boritgogae.board.ask.domain.AskCodeVo;
import com.boritgogae.board.ask.domain.AskReplyVo;
import com.boritgogae.board.ask.domain.AskPagingInfo;
import com.boritgogae.board.ask.domain.AskSearchCriteria;
import com.boritgogae.board.ask.domain.UploadAskFile;
import com.boritgogae.board.ask.domain.UploadAskFileVo;
import com.boritgogae.board.ask.etc.AskUploadFileProcess;
import com.boritgogae.board.ask.service.AskBoardService;

@Controller // 컨트롤러단
@RequestMapping("/board/ask/") // board/ 인 요청들 매핑
public class AskBoardController {
	
	@Inject
	private AskBoardService service; // 서비스 객체

	private List<UploadAskFile> UploadFileLst = new ArrayList();
	private List<UploadAskFile> addTempFileLst = new ArrayList();
	private List<UploadAskFile> subTempFileLst = new ArrayList();
	
	@RequestMapping(value = "/list")
	public String listAll(Model model, @RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo,
			RedirectAttributes rttr, AskSearchCriteria sc, HttpServletRequest request) throws Exception {
		System.out.println("컨트롤러 : 게시판 전체 목록 요청 페이지 번호 : " + pageNo);
		if(pageNo < 1) {
			pageNo = 1;
		}		
		Map<String,Object> map = this.service.readAllBoard(pageNo, sc);
		List<AskBoardVo> lst = (List<AskBoardVo>)map.get("askBoardList");
		AskPagingInfo pi = (AskPagingInfo)map.get("pagingInfo");
		List<AskCodeVo> askCodeList = service.loadAskCode();
		
		// 화면에 보여줄 좋아요와 조회수 2가지를 BoardVo에 넣어줌.(전달할 객체에만 넣어주는 것, DB값이 변하진 않는다)
		List<String> askBnoList = new ArrayList<String>();
		for (AskBoardVo askBoard : lst) {			
			askBoard.setLikeCount(service.getLikeCount(askBoard.getAskBno()));
			askBoard.setReadCount(service.getReadCount(askBoard.getAskBno()));
		}
		
		// 게시판글 상단에 항상 띄워줄 FAQ 조회수 TOP3 글을 불러오는 메서드
		List<AskBoardVo> FAQList = this.service.readFAQBoard();
		
		
		String test = getClientIP(request);
		model.addAttribute("askBoardList", lst); // 바인딩
		model.addAttribute("pagingInfo", pi); // 바인딩
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		model.addAttribute("ipTest", test); 
		model.addAttribute("FAQList", FAQList); // 바인딩
		
		rttr.addFlashAttribute("pageNo", pageNo);
		return "boardAsk/viewAskAll";
	}
	
	// 글 등록
	@RequestMapping(value = "/register")
	public String registerBoard(Model model) throws Exception {
		System.out.println("컨트롤러 : 게시판 글쓰기 요청");
		System.out.println("컨트롤러 : 문의코드 가져오기");
		List<AskCodeVo> askCodeList = service.loadAskCode();
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		System.out.println(askCodeList);
		return "boardAsk/writeAskBoard";
	}
	
	// 답글 페이지
	@RequestMapping(value = "/answer")
	public String answerBoard(Model model, @RequestParam("no") String no) throws Exception {
		System.out.println("컨트롤러 : 문의 답변");
		System.out.println("컨트롤러 : 문의코드 가져오기");
		List<AskCodeVo> askCodeList = service.loadAskCode();
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		model.addAttribute("askBno", no); // 바인딩
		System.out.println(askCodeList);
		return "boardAsk/writeAskBoardAnswer";
	}
	
	// 글 수정 페이지 불러오는 메서드
	@RequestMapping(value = "/modify")
	public String modifyBoard(Model model, @RequestParam("no") String no) throws Exception {
		List<AskCodeVo> askCodeList = service.loadAskCode();
		AskBoardVo board = service.getBoardVo(no);
		this.UploadFileLst = service.showFileList(no);
		
		model.addAttribute("askCodeList", askCodeList); // 바인딩
		model.addAttribute("askBno", no); // 바인딩
		model.addAttribute("board", board); // 바인딩	
		
		System.out.println("UploadFileLst : " + this.UploadFileLst);		
		return "boardAsk/modifyAskBoard";
	}

	// 파일 업로드 메서드
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<UploadAskFile> uploadFile(MultipartFile upfile, HttpServletRequest request) {
		System.out.println("컨트롤러 : 파일 업로드요청");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());

		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);

		ResponseEntity<UploadAskFile> result = null;

		if (upfile.getSize() > 0) {
			UploadAskFile upFile;
			try {
				// 실제로 파일이 넘어가는 구문임
				upFile = AskUploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
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
	
	

	// 글 수정시 파일 업로드 메서드 
	@RequestMapping(value = "/uploadFileModify", method = RequestMethod.POST)
	public ResponseEntity<UploadAskFile> uploadFileModify(MultipartFile upfile, HttpServletRequest request) {
		System.out.println("컨트롤러 : 파일 업로드요청");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());

		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);

		ResponseEntity<UploadAskFile> result = null;

		if (upfile.getSize() > 0) {
			UploadAskFile upFile;
			try {
				upFile = AskUploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
						upfile.getContentType());
				this.UploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠
				this.addTempFileLst.add(upFile); // 수정 취소시 삭제시켜야 할 파일들의 목록임
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
	
	// 첨부파일 삭제시 메서드
	@RequestMapping(value = "/delFile", method = RequestMethod.POST)
	public @ResponseBody String delFile(@RequestParam("deleteFileName") String deleteFileName,
			HttpServletRequest request) {
		System.out.println(deleteFileName + "지우기");
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		File deleteFile = new File(upPath + deleteFileName);
		
		boolean origin = false, thumb = false;
		for (UploadAskFile uf : this.UploadFileLst) {
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
	
	// 글 수정 - 첨부파일 삭제시 메서드
	@RequestMapping(value = "/delFileModify", method = RequestMethod.POST)
	public @ResponseBody String delFileModify(@RequestParam("deleteFileName") String deleteFileName,
			HttpServletRequest request) {
		System.out.println(deleteFileName + "지우기");
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		File deleteFile = new File(upPath + deleteFileName);
		
		boolean origin = false, thumb = false;
		for (UploadAskFile uf : this.UploadFileLst) {
			if (uf.getSavedOriginImageFileName().equals(deleteFileName)) { // 지워져야 할 파일이 있다
				// origin = new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제
				
				if (uf.isImage()) {
					// thumb = new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제					
				}
				this.subTempFileLst.add(uf); // 수정 취소시 삭제시켜야 할 파일들의 목록임

				this.UploadFileLst.remove(uf); // 리스트에서 삭제

				this.addTempFileLst.remove(uf); // 리스트에서 삭제
				break;
			}
		}

		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.UploadFileLst.toArray()));

		// ResponseEntity<String> result = null;
//		String result = "";
//		if (origin) {
//			// result = new ResponseEntity<>("success", HttpStatus.OK);
//			result = "success";
//
//		} else {
//			// result = new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
//			result = "fail";
//		}
		String result = "success";
		System.out.println(result);
		return result;
	}
	
	// 글 수정중 취소하는 메서드
	@RequestMapping(value = "/ModifyCancel", method = RequestMethod.POST)
	public @ResponseBody String ModifyCancel(HttpServletRequest request) {
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");

		for (UploadAskFile uf : this.addTempFileLst) {
			new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제

			if (uf.isImage()) {
				new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
			}
		}

		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제
		this.addTempFileLst.clear(); // 리스트의 모든 아이템 삭제
		this.subTempFileLst.clear(); // 리스트의 모든 아이템 삭제
		
		return "success";
	}
	
	
	// 글 작성중 취소 하는 메서드
	@RequestMapping(value = "/writeCancel", method = RequestMethod.POST)
	public @ResponseBody String writeCancel(HttpServletRequest request) {
		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");

		for (UploadAskFile uf : this.UploadFileLst) {
			new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제

			if (uf.isImage()) {
				new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
			}
		}

		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제
		
		return "success";
	}
	
	// 글 등록시 메서드
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createBoard(AskBoardVo board, RedirectAttributes rttr) throws Exception {
		System.out.println(board.toString());
		
		if(service.create(board, this.UploadFileLst)) {
			rttr.addFlashAttribute("status", "success");
		} else {
			rttr.addFlashAttribute("status", "error");
		}
		
		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해

		System.out.println("테스트중");
		return "redirect:/board/ask/list"; // /board/ask 로 Redirect
	}
	
	// 글 수정에서 수정 등록시 메서드
	@RequestMapping(value = "/modifyComplete", method = RequestMethod.POST)
	public String modifyComplete(AskBoardVo board, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		System.out.println("modifyComplete : " + board.toString());
		
		String upPath = request.getSession().getServletContext().getRealPath("resources/askBoard/uploads");
		
		// strTempFileLst 목록에 있는 파일들을 실제로 삭제시킨다.
		for (UploadAskFile uf : this.subTempFileLst) {
			new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 삭제

			if (uf.isImage()) {
				new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 삭제
			}
		}
		
		// strTempFileLst 목록에 있는 파일들을 DB에서 삭제시킨다.
		for (UploadAskFile uf : this.subTempFileLst) {
			service.deleteFileDB(uf.getSavedOriginImageFileName());
		}

		if(service.update(board, this.addTempFileLst)) { // 추가된 사진은 DB에 올려준다. 
			rttr.addFlashAttribute("status", "success");
		} else {
			rttr.addFlashAttribute("status", "error");
		}

		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해
		this.addTempFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해
		this.subTempFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해

		System.out.println("테스트중");
		return "redirect:/board/ask/list"; // /board/ask 로 Redirect
	}
	
	
		
	@RequestMapping(value = "/answerCreate", method = RequestMethod.POST)
	public String answerCreateBoard(AskBoardVo board, RedirectAttributes rttr) throws Exception {
		if(board.getAnswerStatus().equals("A,-")) {
			board.setAnswerStatus("A");
		}
		
		// 일단 ref값에 글번호 넣어놓은 상태
		System.out.println("Controller : answerCreate : " + board.toString());		
		
		if(service.answerCreate(board, this.UploadFileLst)) {
			rttr.addFlashAttribute("status", "success");
		} else {
			rttr.addFlashAttribute("status", "error");
		}
		
		this.UploadFileLst.clear(); // 리스트의 모든 아이템 삭제 -> 이전 글 등록시 첨부했던 파일을 지우기 위해

		return "redirect:/board/ask/list"; // /board/ask 로 Redirect
	}	
	
	// 글 삭제하는 메서드.
	@RequestMapping(value = "/remove")
	public String removeBoard(Model model, @RequestParam("no") String no, RedirectAttributes rttr) throws Exception {
		System.out.println("컨트롤러 : " + no + "번 글 삭제");
		
		if(service.removeBoard(Integer.parseInt(no))==1) {
			rttr.addFlashAttribute("status", "success");
		} else {
			rttr.addFlashAttribute("status", "error");
		}		
		
		return "redirect:/board/ask/list"; // /board/ask 로 Redirect
	}
	
	@RequestMapping(value="/view")
	public String viewAskBoard(@RequestParam("no") String no, Model model, HttpServletRequest request) {
		int bno = Integer.parseInt(no);
		System.out.println(no + "번 글 조회하기");
		
		Map<String, Object> map;
		AskBoardVo board = null;
		List<UploadAskFileVo> fileList = null;
		String askOption = "";
		String clientIp = getClientIP(request);
		int readCount = 0;
		int likeCheck = 0;
		try {
			map = service.viewBoard(bno, clientIp);
			board = (AskBoardVo)map.get("board");
			fileList = (List<UploadAskFileVo>)map.get("fileList");
			// ask코드를 통해 옵션 알아오기
			askOption = service.readAskOptionByAskCode(board.getAskCode());
			// 글번호에 따른 조회수 가져오기
			readCount = service.getReadCountByBno(board.getAskBno());
			// 특정 아이피가 추천을 했는지 확인하기
			likeCheck = service.likeCheck(bno, clientIp);
			System.out.println("likeCheck : " + likeCheck);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		model.addAttribute("askOption", askOption);
		model.addAttribute("readCount", readCount);
		model.addAttribute("likeCheck", likeCheck);
		
				
		System.out.println(board);
		System.out.println(fileList);
		System.out.println(askOption);
		System.out.println("조회수 : " + readCount);
		
		return "boardAsk/viewAskBoard";
	}
	

	@RequestMapping(value = "/loadFileNames", method = RequestMethod.GET)
	public ResponseEntity<List<UploadAskFile>> loadFileNames(@RequestParam("bno") int bno) {
		System.out.println(bno + "번 글의 파일목록을 얻어오자");
		
		ResponseEntity<List<UploadAskFile>> result = null;
		
		try {
			List<UploadAskFile> lst = service.showFileList(bno+"");
			System.out.println(lst);
			if(lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<UploadAskFile>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	// 좋아요 버튼 클릭했을때 오는 메서드입니다.
	@RequestMapping(value = "/likeStatusChange", method = RequestMethod.POST)
	public ResponseEntity<String> likeStatusChange(@RequestBody AskBoardVo board , HttpServletRequest request) {

		String clientIp = getClientIP(request);
		

		System.out.println("글번호테스트 : " + board.toString());
		System.out.println("글번호 : " + board.getAskBno() + " , IP : " + clientIp);
		ResponseEntity<String> result = null;
		int bno = board.getAskBno() ;

		int likeCheck = 0;
		// 특정 아이피가 추천을 했는지 확인하기
		try {
			likeCheck = service.likeCheck(bno, clientIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("likeCheck : " + likeCheck);
		
		if(likeCheck == 1) { // 이미 좋아요가 눌려있었을 시
			try {
				if (service.deleteLike(bno, clientIp) == 1) {
					result = new ResponseEntity<String>("cancelSuccess", HttpStatus.OK);
				} else {
					result = new ResponseEntity<String>("cancelFail", HttpStatus.OK);
				}
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else { // 좋아요가 눌려있지 않았을 시
			try {
				if (service.insertLike(bno, clientIp)==1) {
					result = new ResponseEntity<String>("registerSuccess", HttpStatus.OK);
				} else {
					result = new ResponseEntity<String>("registerFail", HttpStatus.OK);
				}
			} catch (Exception e) {
				result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		
		return result;
	}
	
	
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");;
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
	    
	    return ip;
	}
	
}
