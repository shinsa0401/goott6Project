package com.boritgogae.board.prodReply.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.prodReply.domain.ReviewVO;
import com.boritgogae.board.prodReply.etc.UploadImg;
import com.boritgogae.board.prodReply.etc.UploadImgProcess;
import com.boritgogae.board.prodReply.service.ReviewService;


@RequestMapping(value="/prodReply2/*")
@RestController
public class ProdRestController {
	
	@Inject
	private ReviewService service; 
	
	List<UploadImg> uploadFileLst = new ArrayList<>();
	
	List<UploadImg> deleteFileLst = new ArrayList<>();
	
	//사진 서버에 저장, 리스트에 저장하는 메서드
	@RequestMapping(value = "/addReviewImg/{prodNo}", method = RequestMethod.POST)
	public ResponseEntity<UploadImg> addReviewImg(MultipartFile file, HttpServletRequest req, @PathVariable("prodNo") String prodNo){
		
		String upPath = req.getSession().getServletContext().getRealPath("resources/uploads/reviewImg");
		
		ResponseEntity<UploadImg> result = null;
		
		if(file.getSize()>0) {
			UploadImg upImg;
			try {
				upImg = UploadImgProcess.uploadImgProcess(upPath, file.getOriginalFilename(), file.getBytes(), prodNo);
				this.uploadFileLst.add(upImg);
				System.out.println(upImg.toString());
				result = new ResponseEntity<>(upImg,HttpStatus.OK);
				
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				System.out.println("오류발생?!");
			}
		}
		return result;
	}
	
	//리뷰 작성에서 사진 서버에서 삭제, 리스트에서 삭제하는 메서드
	@RequestMapping(value = "/delImg", method = RequestMethod.POST)
	public ResponseEntity<String> delImg(@RequestParam("deleteImgName") String deleteImgName, HttpServletRequest req) throws Exception{
		
		String upPath = req.getSession().getServletContext().getRealPath("resources/uploads/reviewImg");
		
		boolean originDel = false;
		boolean thumbDel = false;
		
		System.out.println(uploadFileLst.toString());
		
		for(UploadImg img : this.uploadFileLst) {
			System.out.println(img.toString());
			if(img.getImgName().equals(deleteImgName)) {
				originDel = new File(upPath+img.getImgName()).delete();
				thumbDel = new File(upPath+img.getThumbnailName()).delete();
				
				this.uploadFileLst.remove(img);
			}
		}

		if(originDel&&thumbDel) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//작성 취소 했을 때
	public boolean reviewCancel() {
		boolean result = false;
		
		
		this.uploadFileLst.clear();
		this.deleteFileLst.clear();
		
		if(this.uploadFileLst.size()==0 && this.deleteFileLst.size()==0) {
			result = true;
		}
		return result;
	}
	
	//저장버튼 눌렀을 때
	public boolean saveReviewImg(int reviewNo) throws Exception {
		boolean imgResult = false;
		boolean lstResult = false;
		
		imgResult = service.saveReviewImg(reviewNo, this.uploadFileLst);
		
		this.uploadFileLst.clear();
		this.deleteFileLst.clear();
		
		if(uploadFileLst.size()==0 && deleteFileLst.size()==0) {
			lstResult = true;
		}
		if(imgResult&&lstResult) {
			return true;
		}else {
			return false;
		}
	}
	
	//수정 페이지에서 x버튼 눌렀을 때 삭제할 파일 리스트에 추가, 업로드할 파일 리스트에서 삭제하는 메서드
	@RequestMapping(value = "/deleteImgFromLst")
	public void deleteImgFromLst(@RequestParam("deleteImgName") String deleteImgName) throws Exception{
		for(UploadImg img : uploadFileLst) {
			if(img.getImgName().equals(deleteImgName)) {
				deleteFileLst.add(img);
				uploadFileLst.remove(img);
			}
		}
		System.out.println(uploadFileLst.toString());
		System.out.println(deleteFileLst.toString());
	}
	
	// 리뷰이미지를 서버와 파일리스트에서 지우는 메서드
	public void deleteReviewImg(HttpServletRequest req) {
		String upPath = req.getSession().getServletContext().getRealPath("resources/uploads/reviewImg");
		
		for(UploadImg img : deleteFileLst) {
			new File(upPath+img.getImgName()).delete();
			new File(upPath+img.getThumbnailName()).delete();
		}
		
		this.uploadFileLst.clear();
		this.deleteFileLst.clear();
	}
	
	//서버에서 이미지 삭제하는 메서드
	public void deleteImg(int reviewNo, HttpServletRequest req) throws Exception {
		String upPath = req.getSession().getServletContext().getRealPath("resources/uploads/reviewImg");
		
		List<UploadImg> imgs = service.getReviewImgs(reviewNo);
		for(UploadImg img : imgs) {
			new File(upPath + img.getImgName()).delete();
			new File(upPath+img.getThumbnailName()).delete();
		}
	}
	
	//리뷰 수정하는 페이지로 가기
	@RequestMapping(value = "/editeReview/{reviewNo}/{prodNo}")
	public ModelAndView editReview(@PathVariable("reviewNo") String reviewNo, @PathVariable("prodNo") String prodNo, HttpSession ses) throws Exception {
		int rno = Integer.parseInt(reviewNo);
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = service.getReviewByRno(rno);
		
		mav.setViewName("boardProdReply/editReview");
		
		ReviewVO review = (ReviewVO)map.get("review");
		List<UploadImg> imgs = (List<UploadImg>)map.get("reviewImgs");
		
		mav.addObject("review", review);
		
		if(imgs.size()>0) {
			for(UploadImg img : imgs) {
				this.uploadFileLst.add(img);
			}
			mav.addObject("imgs", imgs);
		}
		
		return mav;
	}
	
	//리뷰 수정하기
	@RequestMapping(value = "/modifyReview", method = RequestMethod.POST)
	public void modifyReview(ReviewVO vo, HttpServletResponse resp) throws Exception {
		
		//사진vo받아와서 rest의 리스트에 저장 : 수정페이지로 넘어갈때 했음
		
		//리뷰 수정
		service.modifyReview(vo);
		
		//사진 지우고 다시 넣기
		service.deleteReviewImg(vo.getReviewNo());
		this.saveReviewImg(vo.getReviewNo()); //리스트에 있는 사진 db에 넣음
		
		resp.sendRedirect("/product/category/detail?prodNo=" + vo.getProdNo());
	}
	
}
