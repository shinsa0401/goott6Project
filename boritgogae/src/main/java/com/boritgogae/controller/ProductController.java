package com.boritgogae.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.prodReply.domain.ProdReplyVo;
import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.Paging;
import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.board.prodReply.service.ReviewService;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.OptionVo;
import com.boritgogae.domain.OrderSheetDTO;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.board.tip.domain.TipPagingInfo;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.service.OrderService;
import com.boritgogae.service.ProductService;

@RequestMapping(value = "/product")
@RestController
public class ProductController {

   private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

   @Inject
   private ReviewService reviewService;
		
	//상세페이지
	/**
	 * @methodName : prodDetail
	 * @author : kjy
	 * @date : 2022. 10. 17.
	 * @입력 param : 쿼리스트링의 prodNo, pageNo
	 * @returnType : String
	 **/
	@RequestMapping(value = "/category/detail")
	public String prodDetail(@RequestParam(value="prodNo", required=true) String prodNo, @RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo, Model model, HttpServletRequest request) throws Exception {
		
		ProductVo prod = prodService.getProd(prodNo);
		List<ProdImgVo> prodImgLst = prodService.getProdImg(prodNo);
		
		Map<String, Object> reviewMap = reviewService.getReviews(prodNo, pageNo);
		List<UploadImg> imgLst = new ArrayList<>();

		List<ReviewVO> reviews = (List<ReviewVO>) reviewMap.get("reviews");
		Paging page = (Paging) reviewMap.get("page");
		ProductContentVo prodContent = prodService.getProdContent(prodNo);
		
		
		for (ReviewVO vo : reviews) {
			List<UploadImg> imgs = reviewService.getReviewImgs(vo.getReviewNo());
			for (UploadImg img : imgs) {
				imgLst.add(img);
			}
		}
		
		List<ProdReplyVo> replies = reviewService.getReplies(prodNo);
		
		MemberVo member = (MemberVo) request.getSession().getAttribute("logInMember");
		
		if(member != null) {
			String canReview = reviewService.canReview(member.getMemberId(), prodNo);
			System.out.println(canReview);
			model.addAttribute("canReview", canReview);
		}
		
		model.addAttribute("reviews",reviews);
		model.addAttribute("reviewImg", imgLst);
		model.addAttribute("page", page);
		model.addAttribute("replies", replies);
		model.addAttribute("product", prod);
		model.addAttribute("prodImg", prodImgLst);
		model.addAttribute("prodContent", prodContent);
		
		return "/product/prodDetail";
	}
	
	
   @Inject
   private ProductService prodService;

   @Inject
   private OrderService orderService;

   //검색
   @RequestMapping(value = "/{searchWord}", method = RequestMethod.GET)
   public ModelAndView SearchProduct(@RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo,
         @PathVariable(value="searchWord") String searchWord) throws Exception {
      
      ModelAndView mav = new ModelAndView();
      
      if (pageNo < 1) {
         pageNo = 1;
      }
      
      Map<String, Object> map = prodService.getSearchProduct(pageNo,searchWord);
      List<ProductDTO> prodLst = (List<ProductDTO>) map.get("prodLst");
      TipPagingInfo pi = (TipPagingInfo) map.get("pi");
      
      if (pageNo > pi.getTotalPage()) {
         pageNo = pi.getTotalPage();
      }
      
      List<ProductDTO> productLst = new ArrayList<ProductDTO>();
      List<DetailOrderVo> PopularProdLst = orderService.popularProd();
      
      for (DetailOrderVo i : PopularProdLst) {
         String prodNo = i.getProdNo();

         if (prodNo == null) {
            break;
         } else {
            ProductDTO product = prodService.getPopularProd(prodNo);
            productLst.add(product);
         }

      }

      List<ProductDTO> lastProduct = prodService.getLastProduct();
      
      
      mav.setViewName("/product/prodList");
      mav.addObject("pi", pi);
      mav.addObject("prodLst", prodLst);
      mav.addObject("popular",productLst);
      mav.addObject("lastProd",lastProduct);
      mav.addObject("category", searchWord);
      mav.addObject("pageNo", pageNo);
      
      
      return mav;
      
   }
   
   // 상품리스트페이지
   @RequestMapping(value = "/productCategory/{category}")
   public ModelAndView prodList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
         @PathVariable(value = "category") String category) throws Exception {


      if (pageNo < 1) {
         pageNo = 1;
      }
      
      Map<String, Object> map = prodService.getProductAll(category, pageNo);
      List<ProductDTO> prodLst = null;

      ModelAndView mav = new ModelAndView();

      prodLst = (List<ProductDTO>) map.get("prodLst");
      TipPagingInfo pi = (TipPagingInfo) map.get("pi");

      

      if (pageNo > pi.getTotalPage()) {
         pageNo = pi.getTotalPage();
      }


      List<ProductDTO> productLst = new ArrayList<ProductDTO>();
      List<DetailOrderVo> PopularProdLst = orderService.popularProd();
      
      for (DetailOrderVo i : PopularProdLst) {
         String prodNo = i.getProdNo();

         if (prodNo == null) {
            break;
         } else {
            ProductDTO product = prodService.getPopularProd(prodNo);
            productLst.add(product);
         }

      }

      List<ProductDTO> lastProduct = prodService.getLastProduct();
      
      
      mav.setViewName("/product/prodList");
      mav.addObject("prodLst", prodLst);
      mav.addObject("pi", pi);
      mav.addObject("popular",productLst);
      mav.addObject("lastProd",lastProduct);
      mav.addObject("category",category);
      mav.addObject("pageNo", pageNo);
      
      return mav;

   }



}

