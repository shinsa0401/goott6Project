package com.boritgogae.board.ask.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boritgogae.board.ask.domain.AskReplyVo;
import com.boritgogae.board.ask.service.AskReplyService;

// 댓글 등록(C) POST /reply/ask/+json데이터
// 댓글 조회(R) GET  /reply/ask/bno
// 댓글 수정(U) 
// 댓글 삭제(D)

@RestController // 현재 클래스가 REST 방식으로 동작하는 컨트롤러임을 명시
@RequestMapping("/reply/ask")
public class AskReplyController {
	@Inject
	private AskReplyService service;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> addReply(@RequestBody AskReplyVo reply, HttpSession ses) {
		System.out.println("댓글 등록 시작 : " + reply.toString());

		ResponseEntity<String> result = null;

		try {
			if (service.addReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return result;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modifyReply(@RequestBody AskReplyVo reply) {
		System.out.println("댓글 수정 시작 : " + reply.toString());
		ResponseEntity<String> result = null;

		try {
			if (service.modifyReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return result;
	}
	

	@RequestMapping(value = "/nested", method = RequestMethod.POST)
	public ResponseEntity<String> nestedReply(@RequestBody AskReplyVo reply) {
		System.out.println("대댓글 시작 : " + reply.toString());

		ResponseEntity<String> result = null;
		
		try {
			if (service.nestedReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteReply(@RequestBody AskReplyVo reply) {
		System.out.println("댓글 삭제 시작 : " + reply.toString());

		ResponseEntity<String> result = null;
		try {
			if (service.deleteReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	// @PathVariable : REST Controller에서 URI 마지막에 붙어있는 변수의 값을 얻어오는 어노테이션
	// @RequestParam : REST Controller가 아닐 때 쿼리스트링의 변수 값을 얻어오는 어노테이션
	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<AskReplyVo>> getEntireReplies(@PathVariable("bno") int bno) {
		System.out.println(bno + "번 글의 모든 댓글을 얻어오자");
		
		ResponseEntity<List<AskReplyVo>> result = null;
		
		try {
			List<AskReplyVo> lst = service.getEntireReplies(bno);
			System.out.println(lst);
			if(lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<AskReplyVo>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}

}
