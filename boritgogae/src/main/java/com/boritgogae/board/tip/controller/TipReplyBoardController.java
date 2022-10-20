package com.boritgogae.board.tip.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boritgogae.board.tip.domain.TipReplyVo;
import com.boritgogae.board.tip.service.BoardReplyService;

@RestController
@RequestMapping(value="/reply")
public class TipReplyBoardController {
	
	@Inject
	private BoardReplyService service;
	
	// 댓글조회 메서드
	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	private ResponseEntity<List<TipReplyVo>> getAllReply(@PathVariable("bno") int bno){
		
		ResponseEntity<List<TipReplyVo>> result = null;
		
		try {
			List<TipReplyVo> lst = service.allReply(bno);
//			System.out.println(lst.toString());
			
			if(lst.size() < 1) {
				result = null;
			} else {
				result =  new ResponseEntity<List<TipReplyVo>>(lst,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 댓글 등록메서드
	@RequestMapping(value = "/{bno}", method = RequestMethod.POST)
	private ResponseEntity<List<TipReplyVo>> plusReply(@PathVariable("bno") int bno, @RequestBody TipReplyVo vo){
		
		ResponseEntity<List<TipReplyVo>> result = null;
		System.out.println(vo+"댓글등록할 내용");
		try {
			boolean judge = service.plusReply(bno,vo);
			
			if(judge) {
				List<TipReplyVo> lst = service.allReply(bno);
				result = new ResponseEntity<List<TipReplyVo>>(lst,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	// 댓글 삭제 메서드
	@RequestMapping(value ="/{bno}/{rno}", method = RequestMethod.GET)
	private ResponseEntity<List<TipReplyVo>> delReply(@PathVariable("rno") int rno,@PathVariable("bno") int bno){
		System.out.println(rno+"삭제할 댓글 번호");
		ResponseEntity<List<TipReplyVo>> result = null;
		
		try {
			Boolean judge = service.delReply(rno);
			
			if(judge) {
				List<TipReplyVo> lst = service.allReply(bno);
				result = new ResponseEntity<List<TipReplyVo>>(lst,HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;		
	}
	
	
}
