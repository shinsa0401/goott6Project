
package com.boritgogae.board.market.controller;


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
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.market.domain.MarketBoardVO;
import com.boritgogae.board.market.domain.MarketPaging;
import com.boritgogae.board.market.domain.MarketSearchCriteria;
import com.boritgogae.board.market.domain.MarketUploadFileVo;
import com.boritgogae.board.market.etc.MarketUploadFile;
import com.boritgogae.board.market.etc.MarketUploadFileProcess;
import com.boritgogae.board.market.service.MarketBoardService;

@Controller
@RequestMapping("/boardMarket/*")
public class MarketBoardController {
	
	@Inject 
	private MarketBoardService service;
	
	private List<MarketUploadFile> uploadFileLst  = new ArrayList<>();
	
	
	/**
	 * @methodName : wirteBoard
	 * @author : hsy
	 * @Date : 2022. 10.  :
	 * @입력 : param :
	 * @returnType : String
	 * 게시글 작성
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String wirteBoard(MarketBoardVO board) throws Exception {
		System.out.println("컨트롤러 글 썼엉");
		service.write(board, this.uploadFileLst);
		this.uploadFileLst.clear();
		return "redirect:/boardMarket/listAll";
	}
	
	
	/**
	 * @methodName : goWriteBoard
	 * @author : hsy
	 * @Date : 2022. 10.  :
	 * @입력 : param :
	 * @returnType : String
	 * 글 쓰러가는 페이지
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET) 
	public String goWriteBoard() {
		System.out.println("컨트롤러단 글 쓰러가기");
		return "boardMarket/writeBoard";
	}
	
	
	/**
	 * @methodName : viewContent
	 * @author : hsy
	 * @Date : 2022. 10.  :
	 * @입력 : param :
	 * @returnType : ModelAndView
	 * 게시글 상세보기
	 */
	@RequestMapping(value = "/viewContent")
	public ModelAndView viewContent(@RequestParam("no") String no) throws Exception {
		int bno = Integer.parseInt(no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardMarket/viewContent");
		
		Map<String, Object>ListMap = service.viewContent(bno);
		
		MarketBoardVO board = (MarketBoardVO)ListMap.get("board");
		List<MarketUploadFileVo> fileList = (List<MarketUploadFileVo>)ListMap.get("fileList");
		
		System.out.println(ListMap);
		mav.addObject("board",board);
		mav.addObject("fileList", fileList);
		//조회수
		service.readCnt(bno);
		System.out.println("~~~~~~~~"+fileList);
		return mav;
	}
	
	
	/**
	 * @methodName : getAllBoard
	 * @author : hsy
	 * @Date : 2022. 10. . :
	 * @입력 : param :
	 * @returnType : ModelAndView
	 *  게시판 목록 
	 */
	@RequestMapping(value = "/listAll")
	public ModelAndView getAllBoard( MarketSearchCriteria sc, @RequestParam(value="pageNo", required=false, defaultValue = "1")int pageNo) throws Exception { 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardMarket/listAll"); //뷰의 경로
		mav.addObject("pageNo", pageNo); //뷰의 데이터 보내
		
		
		if(pageNo < 1 ) {
			pageNo = 1;
		}
		
		Map<String, Object>ListMap = this.service.getAllBoard(pageNo,sc );
		List<MarketBoardVO>List = (List<MarketBoardVO>)ListMap.get("boardList");
		
		MarketPaging pi = (MarketPaging)ListMap.get("paging");
		
		System.out.println("컨트롤러 페이징"+pi);
		System.out.println("컨트롤러  리스트맵"+ListMap);
		//뷰단 출력을 위해 바인딩
		mav.addObject("boardList", List);
		
		mav.addObject("paging", pi);
		
		System.out.println("컨트롤러"+mav);
		return mav;
	}
	
	
	/**
	 * @methodName : writeCancle
	 * @author : hsy
	 * @Date : 2022. 10.  :
	 * @입력 : param :
	 * @returnType : String
	 * 게시글 작성 취소
	 * 업로드 된 파일도 디렉토리에서 지워진다
	 */
	@RequestMapping(value = "/writeCancle")
	public @ResponseBody String writeCancle(HttpServletRequest request) {
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		
		for(MarketUploadFile uf : this.uploadFileLst) {
		      new File(upPath + uf.getSavedOriginImageFileName()).delete();  //원본파일 지움
		      
			boolean  thumb = false;
			if (uf.isImage()) { // 이미지인지 확인(이미지면 썸네일도 지워야 함)
				thumb = new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 파일 지움
				
			}
		}
		
		this.uploadFileLst.clear();
		
		return "success";
	}
	
	
	/**
	 * @methodName : goModifyContent
	 * @author : hsy
	 * @Date : 2022. 10. . :
	 * @입력 : param :
	 * @returnType : String
	 * 게시글 수정 페이지로 이동
	 */
	@RequestMapping(value = "/modifyContent")
	public String goModifyContent(@RequestParam("no") String no, Model model) throws Exception {
		int bno = Integer.parseInt(no);
		
		Map<String, Object> map = service.viewContent(bno);
		
		MarketBoardVO board = (MarketBoardVO)map.get("board");
		
		model.addAttribute("board", board);
		return "boardMarket/modifyContent";
	}
	

	/**
	 * @methodName : modifyContent
	 * @author : hsy
	 * @Date : 2022. 10.  :
	 * @입력 : param :
	 * @returnType : String
	 * 	게시글 수정
	 */
	@RequestMapping(value = "/modifyContent", method = RequestMethod.POST)
	public String modifyContent(MarketBoardVO board) throws Exception{
		
		service.modify(board);
		
		return "redirect:/boardMarket/listAll";
		
	}
	
	/**
	 * @methodName : delContent
	 * @author : hsy
	 * @Date : 2022. 10. :
	 * @입력 : param :
	 * @returnType : String
	 * 게시글 삭제
	 */
	@RequestMapping(value = "/delContent")
	public String delContent(int no) throws Exception {
		
		service.delete(no);
		
		return "redirect:/boardMarket/listAll";
	}
	
	/**
	 * @methodName : uploadFile
	 * @author : hsy
	 * @Date : 2022. 10. 13. :
	 * @입력 : param :
	 * @returnType : ResponseEntity<UploadFile>
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<MarketUploadFile> uploadFile(MultipartFile upfile, HttpServletRequest request) {
		System.out.println("파일이 업로드 됨");

		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("업로드된 파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일 타입 : " + upfile.getContentType());

		// 실제 파일 저장 될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		System.out.println("!!!!!!!!!"+upPath);

		ResponseEntity<MarketUploadFile> result = null;

		if (upfile.getSize() > 0) {
			MarketUploadFile upFile;
			try {
				upFile = MarketUploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
						upfile.getContentType());
				this.uploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠

				result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"을 json으로 보냄
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"를 json으로 보냄
			
			
			}

		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"를 json으로 보냄
		}
		
		System.out.println("컨트롤러단 이거 되니?"+result.toString());

		return result;

	}
	
	/**
	 * @methodName : delFile
	 * @author : hsy
	 * @Date : 2022. 10. 13. :
	 * @입력 : param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/delFile", method = RequestMethod.POST)
	public @ResponseBody String delFile(@RequestParam("deleteFileName") String deleteFileName,
			HttpServletRequest request) {
		
		
		// 실제 파일 저장 될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");

		File deleteFile = new File(upPath + deleteFileName);

		boolean origin = false, thumb = false;
		for (MarketUploadFile uf : this.uploadFileLst) {
			if (uf.getSavedOriginImageFileName().equals(deleteFileName)) { // 지워져야 할 파일이 있다
				origin = new File(upPath + uf.getSavedOriginImageFileName()).delete(); // 원본파일 지움

				if (uf.isImage()) { // 이미지인지 확인(이미지면 썸네일도 지워야 함)
					thumb = new File(upPath + uf.getThumbnailFileName()).delete(); // 썸네일 파일 지움
					
				}
				break;
			}
			this.uploadFileLst.remove(uf); // 리스트에서 삭제

		}
		
		

		String result = null;
		if (origin) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
		
	}
	
}
