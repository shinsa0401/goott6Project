package com.boritgogae.board.prodReply.controller;

import java.io.File;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.board.prodReply.domain.ReplyDTO;
import com.boritgogae.board.prodReply.domain.ReviewDTO;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.board.prodReply.service.ReviewService;
import com.boritgogae.domain.OrderDetailVo;
import com.boritgogae.domain.OrdersVo;


@Controller
@RequestMapping(value = "/prodReply/*")
public class prodReplyController {
	
	@Inject
	private ReviewService service; 
	
	@Inject
	private ProdRestController rest;
	

	
//	@RequestMapping(value = "/getOrdersVo")
//	public String getOrderDetail(String userId, @RequestParam("prodNo") String prodNo, Model model) {
//		List<OrdersVo> vo = service.getOrder(userId, prodNo);
//		model.addAttribute("orders", vo);
//		return "";
//	}

	@RequestMapping(value = "/writeReview")
	public String writeReview(@RequestParam("prodNo") String prodNo) throws Exception{
		//리뷰쓰기 페이지로 이동
		return "boardProdReply/writeReview";
	}
	
	//리뷰 저장하기
	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public String addReview(ReviewDTO dto, RedirectAttributes rttr, @RequestParam("prodNo") String prodNo) throws Exception{
		//리뷰 저장하기 서비스 호출
		boolean result = service.addReview(dto);
		int reviewNo = service.getLastReviewNo();

		boolean imgResult = rest.saveReviewImg(reviewNo);
		if(result&&imgResult) {
			rttr.addFlashAttribute("status", "success");
		}else {
			rttr.addFlashAttribute("status", "fail");
		}
		
		return "redirect:/product/category/detail?prodNo=" + prodNo;
	}
	
	//리뷰 삭제
	@RequestMapping(value = "/deleteReview")
	public @ResponseBody String deleteReview(@RequestParam("reviewNo") String reviewNo, HttpServletRequest req) throws Exception {
		String upPath = req.getSession().getServletContext().getRealPath("resources/uploads/reviewImg");
		int rno = Integer.parseInt(reviewNo);
		
		if(service.deleteReview(rno) && service.deleteReviewImg(rno)) {
			rest.deleteImg(rno, req);
			return "delSuccess";
		}else {
			return "delFail";
		}
	}
	
	//리뷰 댓글 작성하기
	@RequestMapping(value = "/writeReply", method = RequestMethod.POST)
	public String writeReply(ReplyDTO reply) throws Exception {
		service.writeReply(reply);
		
		return "redirect:/product/category/detail?prodNo=" + reply.getProdNo();
	}
	
	//댓글 삭제하기
	@RequestMapping(value = "/deleteReply")
	public @ResponseBody String deleteReply(@RequestParam("rno") int rno) throws Exception{
		
		if(service.deleteReply(rno)) {
			return "replyDelSuccess";
		}else {
			return "replyDelFail";
		}
	}
	
	//리뷰 대댓글 작성하기
	@RequestMapping(value = "writeReReply", method = RequestMethod.POST)
	public String writeReReply(ReplyDTO reply, @RequestParam("prodNo") String prodNo) throws Exception {
		service.writeReReply(reply);
		
		return "redirect:/product/category/detail?prodNo=" + prodNo;
	}
}
