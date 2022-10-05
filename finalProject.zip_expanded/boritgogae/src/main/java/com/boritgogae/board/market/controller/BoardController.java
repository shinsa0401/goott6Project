package com.boritgogae.board.market.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boritgogae.board.market.domain.BoardVO;
import com.boritgogae.board.market.service.BoardService;

@Controller
@RequestMapping("/boardMarket/*")
public class BoardController {
	
	@Inject 
	private BoardService service;
	
	
	
	@RequestMapping(value = "/writeBoardMarket") //임시
	public String createBoard() {
		System.out.println("컨트롤러단 글 쓰기");
		return "boardMarket/writeBoardMarket";
	}
	
}
