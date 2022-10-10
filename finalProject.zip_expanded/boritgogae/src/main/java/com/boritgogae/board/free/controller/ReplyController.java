package com.boritgogae.board.free.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.board.free.domain.ReplyVo;
import com.boritgogae.board.free.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	ReplyService service;
	
	@RequestMapping(value = "/comment")
	public String test ()throws Exception{
		
		
		return"boardFree/comment";
	}
	
	// 댓글 리스트 가져오기
	@RequestMapping(value = "/list" )
	@ResponseBody
	public List<ReplyVo> list (int bno) throws Exception{
		List<ReplyVo> list = service.getList(bno);
		
		return list;
	}
	
	//지정된 댓글 삭제
    
	@RequestMapping(value = "/remove/{rno}")
	public ResponseEntity<String> remove(@PathVariable Integer rno, int bno, String replyer) throws Exception{
		
		String replyers = "samsung";
		
		int rowCnt =service.remove(rno, bno, replyers);
		
		 if(rowCnt!=1)
             throw new Exception("Delete Failed");

         return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
		
		
		
		
	}
	
	@RequestMapping(value = "/writerreply",method = RequestMethod.POST )
	public void writer(@RequestBody ReplyVo rv,Integer bno, String replyer,HttpSession session) throws Exception{
		
		String commenter ="samsung";
		rv.setReplyer(commenter);
//		rv.setBno(bno);
//		service.write(rv);
		System.out.println("rv"+rv);
//		
//		
//		  if(service.write(rv)!=1)
//              throw new Exception("Write failed.");
//
//          return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
		
		 service.write(rv);
          
          
	
		
	}
	
	
	

}
