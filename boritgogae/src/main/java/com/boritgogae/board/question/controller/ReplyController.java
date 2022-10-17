package com.boritgogae.board.question.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boritgogae.board.question.domain.ReplyVo;
import com.boritgogae.board.question.service.ReplyService;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	/**
	 * @methodName : addReply
	 * @author : 신태호
	 * @date : 2022. 10. 9.
	 * @입력 param : ReplyVo reply
	 * 새로운 댓글 등록
	 * @returnType : ResponseEntity<String>
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ResponseEntity<String> addReply(@RequestBody ReplyVo reply) {
		ResponseEntity<String> result = null;
		System.out.println(reply.toString());
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
	
	/**
	 * @methodName : getAllReply
	 * @author : 신태호
	 * @date : 2022. 10. 9.
	 * @입력 param : int bno
	 * 현재글의 모든 댓글을 얻어온다
	 * @returnType : ResponseEntity<List<ReplyVo>>
	 */
	@RequestMapping(value = "/{bno}")
	public ResponseEntity<List<ReplyVo>> getAllReply(@PathVariable("bno") int bno) {
		ResponseEntity<List<ReplyVo>> result = null;
		
		System.out.println("컨트롤러 " + bno + " 번의 모든 댓글을 얻어오자");
		
		try {
			List<ReplyVo> lst = service.getAllReply(bno);
			
			if (lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<ReplyVo>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
		
	}
	
	
	/**
	 * @methodName : modifyReply
	 * @author : 신태호
	 * @date : 2022. 10. 10.
	 * @입력 param : ReplyVo reply
	 * @returnType : ResponseEntity<String>
	 * 댓글 수정
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modifyReply(@RequestBody ReplyVo reply) {
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
	
	/**
	 * @methodName : modifyReply
	 * @author : 신태호
	 * @date : 2022. 10. 10.
	 * @입력 param : int rno
	 * 댓글 삭제
	 * @returnType : ResponseEntity<String>
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<String> removeReply(@RequestBody ReplyVo reply) {
		ResponseEntity<String> result = null;
		System.out.println("컨트롤러 댓글 삭제");
		
		try {
			if (service.removeReply(reply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	
	/**
	 * @methodName : reReply
	 * @author : 신태호
	 * @throws Exception 
	 * @date : 2022. 10. 11.
	 * @입력 param :ReplyVo reply
	 * @returnType : ResponseEntity<String>
	 * 댓글의 댓글 등록
	 */
	@RequestMapping(value = "/reReply", method = RequestMethod.POST)
	public ResponseEntity<String> reReply(@RequestBody ReplyVo reply) throws Exception {
		ResponseEntity<String> result = null;
		
		System.out.println(reply.toString());
		try {
			if (service.reReply(reply)) {
				
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				
				result = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return result;
		
	}
	
	
}
