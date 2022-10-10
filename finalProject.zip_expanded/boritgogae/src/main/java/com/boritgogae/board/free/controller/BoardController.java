package com.boritgogae.board.free.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.PageHandler;
import com.boritgogae.board.free.domain.SearchCriterria;
import com.boritgogae.board.free.service.BoardService;
import com.mysql.cj.Session;

@Controller
@RequestMapping("/boardFree/*")

public class BoardController {
	
	@Inject
	private BoardService service;
	// 실행할 메서드
	
	// /boardFree/list
	// 게시판 페이지select
	@RequestMapping(value = "/list")
	public ModelAndView boardview(Model model, Integer page,Integer pageSize) throws Exception {
		
		if(page ==null) {
			page=1;
		}
		if(pageSize==null) {
			pageSize=10;
		}
		
		
		int totalCnt = service.getCount();
		PageHandler pageHandler = new PageHandler(totalCnt, page,pageSize);
		
		
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("boardFree/list");
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("offset", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		
		List<BoardVo> list =service.listAll(map);
		model.addAttribute("boardList", list);
		model.addAttribute("page", pageHandler);
		System.out.println(pageHandler);

		
		
		return mav;
			
	}

	
	// 글작성 페이지이동	
	@RequestMapping(value = "/writer")
	public String boardWriter()throws Exception{
		
	
		return "boardFree/writer";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String boardinsert(BoardVo vo)throws Exception{
		
		service.insertBoard(vo);
		return "redirect: /boardFree/list";
		
	}
	
	@RequestMapping(value = "/modify")
	public String modify(@RequestParam("bno") String no , Model model)throws Exception{
		int bno = Integer.parseInt(no);
		
		
		Map<String,Object> map = service.detailBoard(bno);
		
		BoardVo board = (BoardVo)map.get("board");
		model.addAttribute("board", board);
		
		return "boardFree/modify";
	}
	
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.POST)
	public String boardUpdate(BoardVo vo)throws Exception{
		
		
		 service.boardUpdate(vo);
		return "redirect: /boardFree/list";
		
	}
	
	@RequestMapping(value = "/delBoard")
	public String delBoard(int bno)throws Exception{
		
		service.delBoard(bno);
		return "redirect: /boardFree/list";
	}
	
	
	
	
	// 상세 페이지로 이동
	@RequestMapping(value = "/detail")
	public ModelAndView boardDetail( Model model, @RequestParam("bno") String no)throws Exception{
		int bno = Integer.parseInt(no);
		
	
		System.out.println(bno +"askdbha");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardFree/detail");
		
		Map<String,Object> map = service.detailBoard(bno);
		
		BoardVo board = (BoardVo)map.get("board");
		model.addAttribute("board", board);
		System.err.println(map+"sdjfn");
		service.readCountUp( bno);
		
		return mav;
	}
	
	
}
