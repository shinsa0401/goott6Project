package com.boritgogae.board.free.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.System.Logger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.SearchCondition;
import com.boritgogae.board.free.domain.SearchCriterria;
import com.boritgogae.board.free.domain.UploadFileFreeVo;
import com.boritgogae.board.free.etc.UploadFile;
import com.boritgogae.board.free.etc.UploadFileProcess;
import com.boritgogae.board.free.service.BoardService;


@Controller
@RequestMapping("/boardFree/*")

public class BoardController {
	
	
	private List<UploadFile> uploadFileLst = new ArrayList<>();

	
	
	@Inject
	private BoardService service;
	
	
	
	
	
	// 실행할 메서드
	
	// /boardFree/list
	// 게시판 페이지select
//	@RequestMapping(value = "/list")
//	public ModelAndView boardview(Model model, Integer page,Integer pageSize) throws Exception {
//		
//		if(page ==null) {
//			page=1;
//		}
//		if(pageSize==null) {
//			pageSize=10;
//		}
//		
//		
//		int totalCnt = service.getCount();
//		PageHandler ph = new PageHandler(totalCnt, page,pageSize);
//		
//		
//		ModelAndView mav = new ModelAndView();
//	
//		mav.setViewName("boardFree/list");
//		Map<String,Object> map = new HashMap<String, Object>();
//		
//		map.put("offset", (page-1)*pageSize);
//		map.put("pageSize", pageSize);
//	
//		
//		List<BoardVo> list =service.listAll(map);
//		model.addAttribute("boardList", list);
//		model.addAttribute("page", ph);
//		
//		
//
//		
//		
//		return mav;
//			
//	}
	
	
	
	 @RequestMapping(value = "/list")
	    public ModelAndView list(Model m, SearchCondition sc, HttpServletRequest request) throws Exception {
		 ModelAndView mav = new ModelAndView();
			
				mav.setViewName("boardFree/list");

//	        try {
	            int totalCnt = service.getSearchResultCnt(sc);
	            m.addAttribute("totalCnt", totalCnt);

	            PageHandler pageHandler = new PageHandler(totalCnt, sc);

	            List<BoardVo> list = service.getSearchResultPage(sc);
	            m.addAttribute("list", list);
	            m.addAttribute("ph", pageHandler);
	            System.out.println("list"+list);
	           
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            m.addAttribute("msg", "LIST_ERR");
//	            m.addAttribute("totalCnt", 0);
//	        }

	        return mav; // 로그인을 한 상태이면, 게시판 화면으로 이동
	    }
	
	
	
	
	
	

	
	// 글작성 페이지이동	
	@RequestMapping(value = "/writer")
	public String boardWriter()throws Exception{
		
	
		return "boardFree/writer";
	}
	
	
	// 게시판 글작성
	@RequestMapping(value = "/create", method = RequestMethod.POST)

	public String boardinsert(BoardVo vo)throws Exception{
		
		service.insertBoard(vo,this.uploadFileLst);
		System.out.println(this.uploadFileLst);
		this.uploadFileLst.clear();
		return "redirect: /boardFree/list";
		
	}
	
	
	
	//게시판 상세페이지 내용과 함께 수정페이지로 이동
	@RequestMapping(value = "/modify")
	public String modify(@RequestParam("bno") String no , Model model)throws Exception{
		int bno = Integer.parseInt(no);
		
		
		Map<String,Object> map = service.detailBoard(bno);
		
		BoardVo board = (BoardVo)map.get("board");
		model.addAttribute("board", board);
		
		return "boardFree/modify";
	}
	
	
	// 게시판 수정
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.POST)
	public String boardUpdate(BoardVo vo)throws Exception{
		
		
		 service.boardUpdate(vo);
		return "redirect: /boardFree/list";
		
	}
	
	
	// 게시판 삭제
	@RequestMapping(value = "/delBoard")
	public String delBoard(int bno)throws Exception{
		
		service.delBoard(bno);
		return "redirect: /boardFree/list";
	}
	
	
	
	
	// 상세 페이지로 이동
	@RequestMapping(value = "/detail")
	public ModelAndView boardDetail( Model model, @RequestParam("bno") String no)throws Exception{
		int bno = Integer.parseInt(no);
		
	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardFree/detail");
		
		Map<String,Object> map = service.detailBoard(bno);
		
		BoardVo board = (BoardVo)map.get("board");
		List<UploadFileFreeVo> fileList = (List<UploadFileFreeVo>)map.get("fileList");
		System.out.println("확인"+fileList);
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		
		
		service.readCountUp( bno);
		
		return mav;
	}
	   @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	   public ResponseEntity<UploadFile> uploadFile(MultipartFile upfile, HttpServletRequest request) {
	      System.out.println("파일이 업로드 됨!");

	      System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
	      System.out.println("파일 사이즈 : " + upfile.getSize());
	      System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());

	      // 파일이 실제 저장 될 경로
	      String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
	      System.out.println("파일이 실제 저장 될 경로 : " + upPath);

	      ResponseEntity<UploadFile> result = null;

	      if (upfile.getSize() > 0) {
	         UploadFile upFile;
	         try {
	            upFile = UploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
	                  upfile.getContentType());
	            this.uploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠

	            result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"을 json으로 보냄
	         } catch (IOException e) {
	            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"를 json으로 보냄
	         }

	      } else {
	         result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"를 json으로 보냄
	      }

	      System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.uploadFileLst.toArray()));

	      return result;

	   }
	
@RequestMapping(value = "/delFile", method = RequestMethod.POST)
public @ResponseBody String delFile(@RequestParam("deleteFileName") String deleteFileName,
	         HttpServletRequest request) {
	      System.out.println(deleteFileName + "을 지우자!!!!");
	      // 파일이 실제 저장 될 경로
	      String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
	      File deleteFile = new File(upPath + deleteFileName);

	      boolean origin = false;
	      if(deleteFile.exists()) { // 파일이 존재하면
	    	  origin=deleteFile.delete(); // 원본 파일 삭제
	          
	      }
//	      for (UploadFile uf : this.uploadFileLst) {
//	         if (uf.getSavedOriginImageFileName().equals(deleteFileName)) { // 지워져야 할 파일이 있다
//	            origin = new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본 파일 삭제
//
//	         this.uploadFileLst.remove(uf); // 리스트에서 삭제
//
//	      }
//	      }
//	      System.out.println("현재 업로드 파일 리스트 : " + origin);
//


	      String result = null;
	      if (origin) {
	         result = "success";
	      } else {
	         result = "fail";
	      }

	      return result;

	   }

@RequestMapping(value = "/writeCancel", method = RequestMethod.POST)
public @ResponseBody String writeCancel(HttpServletRequest request) {
   String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
   
   for(UploadFile uf : this.uploadFileLst) {
      new File(upPath + uf.getSavedOriginImageFileName()).delete();  //원본파일 지움
         
    
      
   }
   this.uploadFileLst.clear(); //리스트의 모든 아이템 삭제
   
return"success";
}

	

	
}
