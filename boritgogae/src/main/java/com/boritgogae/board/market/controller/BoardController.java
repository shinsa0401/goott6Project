package com.boritgogae.board.market.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.domain.SearchCriteria;
import com.boritgogae.board.market.service.BoardService;

@Controller
@RequestMapping("/boardMarket/*")
public class BoardController {
	
	@Inject 
	private BoardService service;
	
	//게시글 insert
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String wirteBoard(BoardVO board) throws Exception {
		System.out.println("이거 안되니");
		service.write(board);
		return "redirect:/boardMarket/listAll";
	}
	
	//글 쓰러가는 페이지
	@RequestMapping(value = "/write", method = RequestMethod.GET) //임시
	public String goWriteBoard() {
		System.out.println("컨트롤러단 글 쓰러가기");
		return "boardMarket/writeBoardMarket";
	}
	
	//게시글 상세보기
	@RequestMapping(value = "/viewContent")
	public ModelAndView viewContent(@RequestParam("no") String no, Model model) throws Exception {
		int bno = Integer.parseInt(no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardMarket/viewContent");
		
		Map<String, Object>ListMap = service.viewContent(bno);
		
		BoardVO board = (BoardVO)ListMap.get("board");
		
		System.out.println(ListMap);
		model.addAttribute("board",board);	
		//조회수
		service.readCnt(bno);
		
		return mav;
	}
	
	// 게시판 목록 
	@RequestMapping(value = "/listAll")
	public ModelAndView getAllBoard(Model model, SearchCriteria sc, int pageNo ) throws Exception { //페이징 안함
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardMarket/listAll");
		
		Map<String, Object>ListMap = this.service.getAllBoard(sc, pageNo);
		
		List<BoardVO>List = (List<BoardVO>)ListMap.get("boardList");
		System.out.println("컨트롤러"+List);
		model.addAttribute("boardList",List);
		
		return mav;
	}
	
	// 게시글 작성 취소
	@RequestMapping(value = "/writeCancle")
	public @ResponseBody String writeCancle(HttpServletRequest request) {
		//파일 지우기도 필요함
		return "success";
	}
	
	//게시글 수정 페이지로 이동
	@RequestMapping(value = "/modifyContent", method = RequestMethod.GET)
	public String goModifyContent(@RequestParam("no") String no, Model model) throws Exception {
		int bno = Integer.parseInt(no);
		
		Map<String, Object> map = service.viewContent(bno);
		
		BoardVO board = (BoardVO)map.get("board");
		
		model.addAttribute("board", board);
		return "boardMarket/modifyContent";
	}
	
	//게시글 수정
	@RequestMapping(value = "/modifyContent", method = RequestMethod.POST)
	public String modifyContent(BoardVO board) throws Exception{
		
		service.modify(board);
		
		return "redirect:/boardMarket/listAll";
		
	}
	//게시글 삭제
	@RequestMapping(value = "/delContent")
	public String delContent(int no) throws Exception {
		
		service.delete(no);
		
		return "redirect:/boardMarket/listAll";
	}
	
	
}
