package com.boritgogae.board.notice.controller;

import java.io.Console;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.service.NoticeServiceImpl;

@Controller
@RequestMapping(value= "/board/notice/*")
public class NoticeController {
	
	@Inject
	private NoticeServiceImpl service;
	
	// 공지사항 가져오기
	@RequestMapping(value="/list")
	public String getNoticeBoard(Model model) throws Exception {
		System.out.println("noticeController");
		
		List<NoticeVo> list = service.getNoticeBoard();
		
		System.out.println(list);

		model.addAttribute("list", list);
		return "boardNotice/noticeBoardList";
		
	}
	
	// 공지 글 작성시작
	@RequestMapping(value="/writeBoard")
	public String writeBoard() throws Exception {
		System.out.println("공지 글 작성");
		
		return "boardNotice/writeNoticeBoard";
	}
	
	// 공지 글 등록
	@RequestMapping(value = "/register")
	public String registerBoard(NoticeVo board) throws Exception {
		System.out.println(board + "글 등록");
		
		if(service.registerBoard(board)) {
			System.out.println("등록 완료");
			
		}
		
		return "redirect:/board/notice/list";
	}
	
	// 공지 글 상세 보기
	@RequestMapping(value = "/view")
	public String viewBoard(@RequestParam("no") String bno, Model model) throws Exception {
		int no = Integer.parseInt(bno);
		
		Map<String, Object> map;
		NoticeVo board = service.viewBoard(no);
		
		model.addAttribute("board", board);

		return "boardNotice/noticeView";
	}
	
	// 공지 글 삭제
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
	
	// 수정할 공지 글 번호 가져오기
	@RequestMapping(value="/bnoToModify")
	public String bnoToModify(@RequestParam("bno") String bno, Model model) throws Exception {
		int no = Integer.parseInt(bno);
		NoticeVo board = service.viewBoard(no);
		
		model.addAttribute("board", board);
		
		return "boardNotice/modifyNoticeBoard";
	}
	
	// 공지 글 수정
	@RequestMapping(value="/modify")
	public String modifyBoard(NoticeVo board) throws Exception {
		if(service.modifyBoard(board)) {
			System.out.println("수정 완료");
		}
		
		return "redirect:/board/notice/list";
	}
	
	//@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	
		
	// 댓글 관련
	
	// 댓글 등록
	@RequestMapping(value="/replyregister", method = RequestMethod.POST)
	public ResponseEntity<String> registerReplyBoard(@RequestBody NoticeReplyVo replyBoard) throws Exception {
		replyBoard.setNickName(service.getNickName(replyBoard.getMemberId()));
		System.out.println(replyBoard + "글 등록");
		
		ResponseEntity<String> result = null;
		
		if(service.registerReply(replyBoard)) {
			System.out.println("등록 완료");
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
			
		return result;
	}
	
	@RequestMapping(value = "/replylist/{bno}")
	public ResponseEntity<List<NoticeReplyVo>> getEntireReplies(@PathVariable("bno") int bno) {
		System.out.println(bno);
		ResponseEntity<List<NoticeReplyVo>> result = null;
		
		try {
			List<NoticeReplyVo> lst = service.getReplyList(bno);
			System.out.println(lst);
			if(lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<NoticeReplyVo>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	// 공지 글 삭제
		@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
		public ResponseEntity<String> deleteReplyBoard(String rno) throws Exception {
			int no = Integer.parseInt(rno);
			
			ResponseEntity<String> result = null;
			
			if(service.deleteReplyBoard(no)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
			
			return result;
		}
}
