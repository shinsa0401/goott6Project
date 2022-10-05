package com.boritgogae.board.question.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boritgogae.board.question.domain.BoardVo;
import com.boritgogae.board.question.service.BoardService;

@Controller // 컨트롤러 단임을 명시
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService service;
	
	
	/**
	 * @methodName : viewAllBoard
	 * @author : 
	 * @date : 2022. 10. 4.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question")
	public String viewAllBoard(Model model) throws Exception {
		System.out.println("컨트롤러 : 게시판 전체 목록 요청");
		List<BoardVo> lst = service.viewAllBoard();
		
		model.addAttribute("boardLst", lst);
		
		return "boardQuestion/viewAllBoard";
	}
	
	
	
	/**
	 * @methodName : writeBoard
	 * @author : 
	 * @date : 2022. 10. 4.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/write")
	public String writeBoard() throws Exception {
		System.out.println("컨트롤러 : 게시판 글쓰기 페이지 요청");
		
		return "board/writeBoard";
		
	}
}
