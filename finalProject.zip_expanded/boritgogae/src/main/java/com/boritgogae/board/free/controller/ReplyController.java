package com.boritgogae.board.free.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boritgogae.board.free.domain.BoardVo;
import com.boritgogae.board.free.domain.ReplyVo;
import com.boritgogae.board.free.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	ReplyService service;
	
	
	// test페이지
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
		System.out.println("rowcnt"+rowCnt);
		if(rowCnt!=1)
            throw new Exception("Delete Failed");

        return new ResponseEntity<>("DEL_OK", HttpStatus.OK);

		  
		
		
		
		
	}
	
	
	// 댓글 작성
	@RequestMapping(value = "/writerreply",method = RequestMethod.POST )// 회원가입 로그인 처리되면 세션으로 아이디 받기
	public String writer(@RequestBody ReplyVo rv,Integer bno, String replyer,HttpSession session) throws Exception{
		
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
          
          return "boardFree/detail";
	
		
	}
	
	
	// 댓글 수정
	 @RequestMapping(value = "/modify/{rno}")  
	    public ResponseEntity<String> modify(@PathVariable Integer rno, @RequestBody ReplyVo rv) throws Exception {
//	        String commenter = (String)session.getAttribute("id"); // 회원가입 로그인 처리 됬을때 아이디 세션으로 받기
	        String replyer = "samsung";
	        rv.setReplyer(replyer);
	        rv.setRno(rno);
	        System.out.println("rv = " + rv);

	       
	            if(service.modify(rv)!=1) {
	            	 throw new Exception("Write failed.");
	            }
	               
	            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
	       
	    }
	
	

}
