package com.boritgogae.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.service.NoticeService;
import com.boritgogae.board.tip.domain.TipBoardVo;
import com.boritgogae.board.tip.domain.TipReplyVo;
import com.boritgogae.board.tip.service.TipBoardService;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.OrderVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.service.OrderService;
import com.boritgogae.service.ProductService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {
	
	@Inject
	private ProductService prodService;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private TipBoardService tipService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, @RequestParam(value="orderNo", required = false, defaultValue = "0") int  orderNo) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProductDTO> productLst = new ArrayList<ProductDTO>();
		
		List<DetailOrderVo> prodLst = orderService.popularProd();
		
		for (DetailOrderVo i : prodLst ) {
			String prodNo = i.getProdNo();
//			System.out.println("인기상품 코드"+prodLst.get(i).getProdNo());
		
			if (prodNo == null) {
				break;
//				continue; //break 테스트후 continue로 바꿀것
			}
			else {
				ProductDTO product = prodService.getPopularProd(prodNo);
				productLst.add(product);
			}
			
		}
//		System.out.println("이놈아 나와라"+productLst);
		List<NoticeVo> noticeLst = noticeService.getNoticeBoardMain();
		
		List<ProductDTO> lastProduct = prodService.getLastProduct();
		mav.setViewName("index");
		mav.addObject("orderNo", orderNo);
		mav.addObject("prodLst",productLst);
		mav.addObject("noticeLst",noticeLst);
		mav.addObject("lastProd",lastProduct);
		
		return mav;
	}
	
	@RequestMapping(value = "view/tip")
	private ResponseEntity<List<TipBoardVo>> miniTip() throws Exception{
		ResponseEntity<List<TipBoardVo>> result = null;
		List<TipBoardVo> list = tipService.miniBoard();
		
		if(list.size() < 1) {
			result = null;
		} else {
			result =  new ResponseEntity<List<TipBoardVo>>(list,HttpStatus.OK);
		}
		
		return result;
	}
	
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ModelAndView LatestProdList() throws Exception{
//		
//	}
	
	
	
}
