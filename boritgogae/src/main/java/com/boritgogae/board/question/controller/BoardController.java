package com.boritgogae.board.question.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	 * @methodName : newWriteBoard
	 * @author : 
	 * @date : 2022. 10. 4.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/newWrite")
	public String newWriteBoard() throws Exception {
		System.out.println("컨트롤러 : 게시판 글쓰기 페이지 요청");
		return "boardQuestion/writeBoard";
	}
	
	
	/**
	 * @methodName : writeBoard
	 * @author : 
	 * @date : 2022. 10. 5.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/write", method = RequestMethod.POST)
	public String writeBoard(BoardVo board) throws Exception {
		System.out.println("컨트롤러 : 글쓰기 요청");
		
		String result = "";
		
		Map<String, Object> map = new HashMap<>();
		map = service.writeBoard(board);
		
		if ((boolean) map.get("result")) {
			result = "redirect:/board/question/view?no="+ map.get("lastNo");
		} else {
			result = "redirect:/board/question";
		}
		
		return result;
	}
	
	
	/**
	 * @methodName : viewBoard
	 * @author : 
	 * @date : 2022. 10. 5.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/view")
	public String viewBoard(@RequestParam("no") String no, Model model) throws Exception {
		System.out.println("컨트롤러 : 게시판 글 상세보기 요청");
		int bno = Integer.parseInt(no);
		System.out.println(bno + " 번 글을 조회");
		
		BoardVo board = service.viewBoard(bno);
		
		model.addAttribute("board", board);
		
		return "/boardQuestion/viewBoard";
	}
	
	
	/**
	 * @methodName : modifyBoard
	 * @author : 
	 * @date : 2022. 10. 5.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/modify")
	public String modifyBoard(@RequestParam("no") String no, Model model) throws Exception {
		System.out.println("컨트롤러 : 게시판 글 수정 요청");
		// 수정하기위해 수정할 글의 정보를 얻어 와서 뷰단에 출력
		int bno = Integer.parseInt(no);
		BoardVo board = service.viewBoard(bno);
		
		model.addAttribute("board", board);
		
		return "/boardQuestion/modifyBoard";
	}
	
	
	/**
	 * @methodName : modifySave
	 * @author : 
	 * @date : 2022. 10. 5.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/modifySave", method = RequestMethod.POST)
	public String modifySave(BoardVo board) throws Exception {
		
		service.modifyBoard(board);
		System.out.println(board.toString());
		return "/boardQuestion/viewBoard";
	}
	
	
	
	/**
	 * @methodName : removeBoard
	 * @author : 
	 * @date : 2022. 10. 6.
	 * @입력 param :
	 * @returnType : String
	 */
	@RequestMapping(value = "/question/remove", method = RequestMethod.POST)
	public @ResponseBody String removeBoard(@RequestParam("no")int no, @RequestParam("pwd")String pwd) throws Exception {
		String result = null;
		System.out.println("삭제 요청");
		if (service.removeBoard(no, pwd)) {
			result = "success";
			System.out.println("삭제 성공");
		} else {
			result = "fail";
			System.out.println("삭제 실패");
		}
		
		return result;
	}
	
	
	
	
	
}