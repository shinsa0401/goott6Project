package com.boritgogae.board.tip.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.tip.domain.TipBoardVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.board.tip.domain.TipUploadFile;
import com.boritgogae.board.tip.domain.TipUploadFileProcess;
import com.boritgogae.board.tip.service.BoardService;

@RestController
@RequestMapping(value = "/boardTip")
public class TipBoardController {

	@Inject
	private BoardService service;
	
	private List<TipUploadFile> UploadFileLst = new ArrayList<>();

	// 팁게시판 전체조회
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll(@RequestParam(value = "pageNo",required = false, defaultValue = "1") int pageNo)
			throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = service.getListBoard(pageNo);
		List<TipBoardVo> lst = (List<TipBoardVo>) map.get("lst");
		TipPagingInfo pi = (TipPagingInfo) map.get("pi");

		if(pageNo < 1) {
			pageNo = 1;
		}
		else if (pageNo > pi.getTotalPage()) {
			pageNo = pi.getTotalPage();
		}

		mav.setViewName("boardTip/listAll");
		mav.addObject("BoardLst", lst);
		mav.addObject("pi", pi);
		mav.addObject("pageNo", pageNo);
//		System.out.println(service.toString());
		return mav;
	}

	// 게시판 글조회 , 조회수 증가
	@RequestMapping(value = "/{bno}")
	public ModelAndView detail(@PathVariable("bno") int bno) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println(bno+"왔냐???"); //  왔네
		boolean cnt = service.addReadCnt(bno); // 조회수 증가 메서드

		if (cnt) {
			mav.setViewName("boardTip/detail");
			TipBoardVo detail = service.getDetail(bno);
			mav.addObject("board", detail);
		}

		return mav;
	}

	// 게시판 글삭제
	@RequestMapping(value = "/{bno}", method = RequestMethod.POST)
	public ModelAndView delDetail(@PathVariable("bno") int bno,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println("이제는 제대로 작동하나?");
		boolean result = false;
		result = service.delBoard(bno);

		if (result) {
			Map<String, Object> map = service.getListBoard(pageNo);
			List<TipBoardVo> lst = (List<TipBoardVo>) map.get("lst");
			TipPagingInfo pi = (TipPagingInfo) map.get("pi");

			if (pageNo < 1) {
				pageNo = 1;
			} else if (pageNo > pi.getTotalPage()) {
				pageNo = pi.getTotalPage();
			}

			mav.setViewName("boardTip/listAll");
			mav.addObject("BoardLst", lst);
			mav.addObject("pi", pi);
		}

		return mav;
	}

	// 게시판 글 수정페이지로 이동
	@RequestMapping(value = "/modifyBoard/{bno}")
	public ModelAndView modiBoard(@PathVariable("bno") int bno) throws Exception {
		ModelAndView mav = new ModelAndView();
		TipBoardVo detail = service.getDetail(bno);

		mav.setViewName("boardTip/modifyBoard");
		mav.addObject("board", detail);

		return mav;

	}

	// 게시판 글 수정완료했을시
	@RequestMapping(value = "/modifyBoard/{bno}", method = RequestMethod.POST)
	public ModelAndView updateBoard(@PathVariable("bno") int bno, TipBoardVo vo) throws Exception {
		ModelAndView mav = new ModelAndView();

		TipBoardVo detail = service.modiBoard(bno, vo);
		mav.setViewName("boardTip/detail");
		mav.addObject("board", detail);

		return mav;

	}

	// 글등록 버튼클릭시
	@RequestMapping(value = "/writeBoard")
	public ModelAndView writeBoard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTip/writeBoard");

		return mav;

	}

	// 게시판 글작성후등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView insertBoard(TipBoardVo board,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println("값이 넘어왔나?값이 넘어왔나?값이 넘어왔나?값이 넘어왔나?"+board.toString());
		boolean result = service.addBoard(board);
		if (result) {
			Map<String, Object> map = service.getListBoard(pageNo);
			List<TipBoardVo> lst = (List<TipBoardVo>) map.get("lst");
			TipPagingInfo pi = (TipPagingInfo) map.get("pi");

			if (pageNo < 1) {
				pageNo = 1;
			} else if (pageNo > pi.getTotalPage()) {
				pageNo = pi.getTotalPage();
			}

			mav.setViewName("boardTip/listAll");
			mav.addObject("BoardLst", lst);
			mav.addObject("pi", pi);
		}

		return mav;

	}
	
	// 답글 페이지
	@RequestMapping(value = "/replyBoard/{bno}", method = RequestMethod.GET)
	public ModelAndView ReplyBoard(TipBoardVo vo,@PathVariable("bno") int bno) throws Exception{
		ModelAndView mav = new ModelAndView();
		TipBoardVo detail = service.getDetail(bno);

		mav.setViewName("boardTip/replyBoard");
		mav.addObject("board", detail);
		return mav;
	}
	
	// 답글달기
	@RequestMapping(value = "/replyBoard/{bno}", method = RequestMethod.POST)
	public ModelAndView ReplyBoard(TipBoardVo vo,@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,@PathVariable("bno") int bno) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		boolean result = service.addReplyBoard(vo,bno);
		System.out.println(result+"@@@@@@@@@@@@@@@@@@@@@@@");
		if (result) {
			Map<String, Object> map = service.getListBoard(pageNo);
			List<TipBoardVo> lst = (List<TipBoardVo>) map.get("lst");
			TipPagingInfo pi = (TipPagingInfo) map.get("pi");

			if (pageNo < 1) {
				pageNo = 1;
			} else if (pageNo > pi.getTotalPage()) {
				pageNo = pi.getTotalPage();
			}

			mav.setViewName("boardTip/listAll");
			mav.addObject("BoardLst", lst);
			mav.addObject("pi", pi);
		}
		
		
		return mav;
		
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<TipUploadFile> uploadFile(@RequestParam("file") MultipartFile upfile, HttpServletRequest request) {

		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");

		ResponseEntity<TipUploadFile> result = null;

		if (upfile.getSize() > 0) {

			TipUploadFile upFile;
			try {
				upFile = TipUploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
						upfile.getContentType());
				this.UploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠
				result = new ResponseEntity<>(upFile, HttpStatus.OK); 
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
			}

		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}

		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.UploadFileLst.toArray()));
		
		return result;
	}

}
