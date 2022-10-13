package com.boritgogae.board.notice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value="writeBoard")
	public String writeBoard() throws Exception {
		System.out.println("공지 글 작성");
		
		return "boardNotice/writeNoticeBoard";
	}
	
	@RequestMapping(value = "register")
	public String registerBoard(NoticeVo board) throws Exception {
		System.out.println(board + "글 등록");
		
		if(service.registerBoard(board)) {
			System.out.println("등록 완료");
			
		}
		
		return "redirect:/board/notice/list";
	}
}
