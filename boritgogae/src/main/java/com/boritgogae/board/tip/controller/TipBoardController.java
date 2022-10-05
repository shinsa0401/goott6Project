package com.boritgogae.board.tip.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.service.BoardService;

@RestController
@RequestMapping(value="/boardTip") 
public class TipBoardController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="")
	public ModelAndView listAll() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTip/listAll");
//		service.getListBoard();
		List<BoardVo> lst = service.getListBoard();
		mav.addObject("BoardLst", lst);
		
		System.out.println(service.toString());
		return mav;
	}
	
	@RequestMapping(value="/{bno}")
	public ModelAndView detail(@PathVariable("bno") int bno) throws Exception{
		ModelAndView mav = new ModelAndView();
//		System.out.println(bno+"왔냐???"); //  왔네
		mav.setViewName("boardTip/detail");
		BoardVo detail = service.getDetail(bno);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+ detail);
		mav.addObject("board", detail);
		return mav;
	}
	
}
