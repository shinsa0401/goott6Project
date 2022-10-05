package com.boritgogae.board.notice.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.service.NoticeServiceImpl;

@Controller
@RequestMapping(value= "/board/notice/*")
public class NoticeController {
	
	@Inject
	private NoticeServiceImpl service;
	
	
	@RequestMapping(value="/list")
	public String getNoticeBoard(Model model) throws Exception {
		System.out.println("noticeController");
		// 공지사항 가져오기
		
		List<NoticeVo> list = service.getNoticeBoard();
		
		System.out.println(list);

		model.addAttribute("list", list);
		return "boardNotice/noticeBoardList";
		
	}
	
	@RequestMapping(value="/writeBoard")
	public String writeBoard() throws Exception {
		System.out.println("공지 글 작성");
		
		return "boardNotice/writeNoticeBoard";
	}
	
	@RequestMapping(value = "/register")
	public String registerBoard(NoticeVo board) throws Exception {
		System.out.println(board + "글 등록");
		
		if(service.registerBoard(board)) {
			System.out.println("등록 완료");
			
		}
		
		return "redirect:/board/notice/list";
	}
	
	@RequestMapping(value = "/view")
	public String viewBoard(@RequestParam("no") String bno, Model model) throws Exception {
		int no = Integer.parseInt(bno);
		
		Map<String, Object> map;
		NoticeVo board = service.viewBoard(no);
		
		model.addAttribute("board", board);

		return "boardNotice/noticeView";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteBoard(String bno) throws Exception {
		System.out.println(bno);
		int no = Integer.parseInt(bno);
		
		ResponseEntity<String> result = null;
		System.out.println(no);
		if(service.deleteBoard(no)) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return result;
	}
	
	//@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	
		
}
