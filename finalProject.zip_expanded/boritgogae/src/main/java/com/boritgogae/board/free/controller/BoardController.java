package com.boritgogae.board.free.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.service.BoardService;

@Controller
@RequestMapping("/boardFree/*")
public class BoardController {
	
	@Inject
	private BoardService service;
	// 실행할 메서드
	
	@RequestMapping(value = "/list")
	public ModelAndView boardview(Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map =service.boardlist();
		mav.setViewName("boardFree/list");
		List<BoardVo> list = (List<BoardVo>)map.get("boardLst");
		System.out.println("컨트"+list);
		model.addAttribute("boardList", list);
		
		
		
		
		
		return mav;
		
		
	}

}
