package com.boritgogae.board.market.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boritgogae.board.market.domain.ReplyVO;
import com.boritgogae.board.market.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	/**
	 * @methodName : replyWrite
	 * @author : hsy
	 * @Date : 2022. 10. 9. :
	 * @입력 : param :
	 * @returnType : ResponseEntity<String>
	 * 댓글 등록
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> replyWrite(@RequestBody ReplyVO reply) {
		System.out.println("댓글 등록 시작 : " + reply.toString());
		
		ResponseEntity<String> result = null;
		
		try {
			if(service.replyWrite(reply) ) {
				result = new ResponseEntity<String>("success",HttpStatus.OK);
			}else {
				result = new ResponseEntity<String>("fail",HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		};
	
		return result;
	} 
	

	
	/**
	 * @methodName : getEntireReplies
	 * @author : hsy
	 * @Date : 2022. 10. 10. :
	 * @입력 : param :
	 * @returnType : ResponseEntity<List<ReplyVO>>
	 * 댓글 목록
	 */
	@RequestMapping(value="/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> getEntireReplies(@PathVariable("bno") int bno) {
		System.out.println(bno + "번 글의 모든 댓글을 얻어오자");
		
		ResponseEntity<List<ReplyVO>> result = null;
		
		try {
			List<ReplyVO> lst = service.getAllReply(bno);
			if (lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<ReplyVO>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	
	/**
	 * @methodName : modiReply
	 * @author : hsy
	 * @Date : 2022. 10. 11. :
	 * @입력 : param :
	 * @returnType : ResponseEntity<String>
	 * 댓글 수정
	 */
	@RequestMapping(value = "/modiReply/{rno}", method = RequestMethod.POST)
	public ResponseEntity<String> modiReply(@RequestBody ReplyVO reply, @PathVariable("rno") int rno ) {
		ResponseEntity<String> result = null;
		reply.setRno(rno);
		
		try {
			if(service.modiReply(reply)) {
				result = new ResponseEntity<String>("success",HttpStatus.OK);
			}else {
				result = new ResponseEntity<String>("fail",HttpStatus.OK);
			}
			System.out.println("여기에 뭐가 걸릴려나"+result);
			System.out.println("여기에 뭐가 걸릴려나"+reply.getReplyContent());
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			System.out.println("댓글 수정 에러 : "+e);
		}
	
		
		return result;
	
}
	/**
	 * @methodName : delReply
	 * @author : hsy
	 * @Date : 2022. 10. 11. :
	 * @입력 : param :
	 * @returnType : ResponseEntity<String>
	 * 댓글 삭제 
	 */
	@RequestMapping(value = "/delReply" )
	public ResponseEntity<String> delReply(@RequestBody ReplyVO reply)  {
		ResponseEntity<String> result = null;
		
		try {
			if(service.delReply(reply)) {
				result = new ResponseEntity<String>("success",HttpStatus.OK);
			}else {
				result = new ResponseEntity<String>("fail",HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}
		
}
