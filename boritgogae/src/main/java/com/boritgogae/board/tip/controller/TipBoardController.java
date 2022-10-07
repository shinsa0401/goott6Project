package com.boritgogae.board.tip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.tip.domain.BoardVo;
import com.boritgogae.board.tip.service.BoardService;

@RestController
@RequestMapping(value = "/boardTip")
public class TipBoardController {

	@Inject
	private BoardService service;

	// 팁게시판 전체조회
	@RequestMapping(value = "")
	public ModelAndView listAll() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTip/listAll");
//		service.getListBoard();
		List<BoardVo> lst = service.getListBoard();
		mav.addObject("BoardLst", lst);

		System.out.println(service.toString());
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
			BoardVo detail = service.getDetail(bno);
			mav.addObject("board", detail);
		}
		
		return mav;
	}

	// 게시판 글삭제
	@RequestMapping(value = "/{bno}", method = RequestMethod.POST)
	public ModelAndView delDetail(@PathVariable("bno") int bno) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println("이제는 제대로 작동하나?");
		boolean result = false;
		result = service.delBoard(bno);
		
			List<BoardVo> lst = service.getListBoard();
//			System.out.println(lst.toString());
			mav.setViewName("boardTip/listAll");
			mav.addObject("BoardLst", lst);
		

		return mav;
	}
	
	// 게시판 글 수정페이지로 이동
	@RequestMapping(value = "/modifyBoard/{bno}")
	public ModelAndView modiBoard(@PathVariable("bno") int bno) throws Exception {
		ModelAndView mav = new ModelAndView();
		BoardVo detail = service.getDetail(bno);
		
		mav.setViewName("boardTip/modifyBoard");
		mav.addObject("board", detail);
		
		return mav;
		
	}
	
	// 게시판 글 수정완료했을시
		@RequestMapping(value = "/modifyBoard/{bno}", method = RequestMethod.POST)
		public ModelAndView updateBoard(@PathVariable("bno") int bno,BoardVo vo) throws Exception {
			ModelAndView mav = new ModelAndView();
			
			BoardVo detail = service.modiBoard(bno,vo);
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
	public ModelAndView insertBoard(BoardVo board) throws Exception {
		ModelAndView mav = new ModelAndView();
//		System.out.println("값이 넘어왔나?값이 넘어왔나?값이 넘어왔나?값이 넘어왔나?"+board.toString());
		boolean result = service.addBoard(board);
		List<BoardVo> lst = service.getListBoard();
		mav.setViewName("boardTip/listAll");
		mav.addObject("BoardLst", lst);
		return mav;

	}

}
