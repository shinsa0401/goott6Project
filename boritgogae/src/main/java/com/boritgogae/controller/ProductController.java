package com.boritgogae.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.boritgogae.board.prodReply.domain.ReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.Paging;
import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.board.prodReply.service.ReviewService;
import com.boritgogae.domain.OptionVo;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProdImgVO;
import com.boritgogae.domain.ProductVO;
import com.boritgogae.service.ProductService;



@RequestMapping(value="/product/*")
@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	@Inject
	private ReviewService reviewService;
	
	@Inject
	private ProductService prodService;
	
	//상세페이지
	/**
	 * @methodName : prodDetail
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 쿼리스트링의 prodNo, pageNo
	 * @returnType : String
	 **/
	@RequestMapping(value = "/category/detail")
	public String prodDetail(@RequestParam(value="prodNo", required=true) String prodNo,@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo, Model model) throws Exception {
		
		ProductVO prod = prodService.getProd(prodNo);
		List<ProdImgVO> prodImgLst = prodService.getProdImg(prodNo);
		
		Map<String, Object> reviewMap = reviewService.getReviews(prodNo, pageNo);
		List<UploadImg> imgLst = new ArrayList<>();

		List<ReviewVO> reviews = (List<ReviewVO>) reviewMap.get("reviews");
		Paging page = (Paging) reviewMap.get("page");
		
		
		for (ReviewVO vo : reviews) {
			List<UploadImg> imgs = reviewService.getReviewImgs(vo.getReviewNo());
			for (UploadImg img : imgs) {
				imgLst.add(img);
			}
		}
		
		List<ReplyVo> replies = reviewService.getReplies(prodNo);
		OptionVo options = new OptionVo(prodNo);
		
		model.addAttribute("reviews",reviews);
		model.addAttribute("reviewImg", imgLst);
		model.addAttribute("page", page);
		model.addAttribute("replies", replies);
		model.addAttribute("product", prod);
		model.addAttribute("prodImg", prodImgLst);
		model.addAttribute("options", options);
		
		return "/product/prodDetail";
	}
	
	
}
