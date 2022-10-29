package com.boritgogae.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.domain.OrdersVo;
import com.boritgogae.domain.ProdImgVo;
import com.boritgogae.domain.ProductContentVo;
import com.boritgogae.domain.ProductDTO;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.etc.NoticePagingInfo;
import com.boritgogae.board.notice.etc.NoticeUploadFile;
import com.boritgogae.board.notice.etc.NoticeUploadFileProcess;
import com.boritgogae.domain.CouponUsedVo;
import com.boritgogae.domain.CouponVo;
import com.boritgogae.domain.DeleteAccountVo;
import com.boritgogae.domain.DeleteReasonVo;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.service.AdminService;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController {

	@Inject
	private AdminService service;

	@RequestMapping(value = "/main")
	public String adminMainPage(Model model) throws Exception {
		System.out.println("관리자 페이지로 이동");
		List<MemberVo> members = service.getMembers();
		List<MemberVo> newMembers = service.getNewMembers();
		List<ProductVo> lowestProduct = service.getLowestProduct();
		List<ProductVo> topLikeCountList = service.getTopLikeCount();
		List<OrdersVo> getNewOrders = service.getNewOrder();
		List<ProductVo> topReadCountList = service.getTopReadCount();
		int getLogInMemberCount = service.getLogInMemberCount();

		model.addAttribute("logInMemberCount", getLogInMemberCount);
		model.addAttribute("topReadCountList", topReadCountList);
		model.addAttribute("getNewOrders", getNewOrders);
		model.addAttribute("topLikeCountList", topLikeCountList);
		model.addAttribute("lowestProduct", lowestProduct);
		model.addAttribute("newMembers", newMembers);
		model.addAttribute("members", members);

		return "/admin/main";
	}

	@RequestMapping(value = "/member")
	public String memberManagememt(Model model) throws Exception {
		System.out.println("회원 관리 페이지로 이동");
		List<MemberVo> members = service.getMembers();

		model.addAttribute("members", members);

		return "/admin/member";
	}

	@RequestMapping(value = "/product")
	public String productList(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, RedirectAttributes rttr)
			throws Exception {

		if (pageNo < 1) {
			pageNo = 1;
		}

		Map<String, Object> map = service.getProdList(pageNo);

		List<ProductVo> prodList = (List<ProductVo>) map.get("prodList");
		List<ProdImgVo> prodImgList = service.getProdImg();
		NoticePagingInfo pi = (NoticePagingInfo) map.get("pagingInfo");
		int prodCnt = service.getProdCnt();
		List<ProductContentVo> prodContentList = service.getProductContent();

		model.addAttribute("prodContentList", prodContentList);
		model.addAttribute("prodImgList", prodImgList);
		model.addAttribute("prodList", prodList);
		model.addAttribute("pagingInfo", pi);
		model.addAttribute("prodCnt", prodCnt);
		rttr.addFlashAttribute("pageNo", pageNo);

		return "/admin/product";
	}

	@RequestMapping(value = "/member/new")
	public String newMemberManagememt(Model model) throws Exception {
		System.out.println("회원 관리 페이지로 이동");
		List<MemberVo> members = service.getNewMembers();

		model.addAttribute("members", members);

		return "/admin/newMember";
	}

	@RequestMapping(value = "/member/searchMember", method = RequestMethod.POST)
	public ResponseEntity<List<MemberVo>> searchMember(@RequestParam("inputString") String inputString, Model model)
			throws Exception {
		System.out.println(inputString + "회원 검색");
		ResponseEntity<List<MemberVo>> result = null;

		List<MemberVo> searchMember = service.searchMember(inputString);
		System.out.println(searchMember);

		result = new ResponseEntity<>(searchMember, HttpStatus.OK);

		return result;
	}

	@RequestMapping(value = "/member/delMembers")
	public String getDelMember(Model model) throws Exception {
		List<DeleteAccountVo> deleteMember = service.getDelMembers();
		List<DeleteReasonVo> deleteReasons = service.getDeleteReason();

		model.addAttribute("deleteReasons", deleteReasons);
		model.addAttribute("deleteMember", deleteMember);
		return "/admin/delMember";
	}

	@RequestMapping(value = "/member/detail")
	public String viewMemberProfile(@RequestParam("memberId") String memberId, Model model) throws Exception {

		MemberVo member = service.getMemberProfile(memberId);
		List<CouponUsedVo> memberCoupon = service.getCouponFromMember(memberId);
		List<DeliveryInfoVo> memberAddressList = service.getMemberAddress(memberId);

		model.addAttribute("memberAddressList", memberAddressList);
		model.addAttribute("memberCoupon", memberCoupon);
		model.addAttribute("member", member);

		return "/admin/memberProfile";
	}

	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public @ResponseBody String modifyMember(@RequestBody MemberVo member) throws Exception {
		String result = "";

		if (service.modifyMemberForAdmin(member)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteMember(@RequestParam("memberId") String memberId) throws Exception {
		String result = "";

		if (service.deleteMember(memberId)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "/coupon")
	public String getCoupon(Model model) throws Exception {
		List<CouponVo> couponList = service.getCoupon();
		List<MemberVo> members = service.getMembers();

		model.addAttribute("members", members);
		model.addAttribute("couponList", couponList);

		return "/admin/coupon";
	}

	@RequestMapping(value = "/coupon/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCoupon(@RequestBody CouponVo coupon) {
		coupon.setCouponDiscount(coupon.getCouponDiscount() * 0.01);

		ResponseEntity<String> result = null;

		try {
			if (service.createCoupon(coupon)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}

		return result;
	}

	@RequestMapping(value = "/coupon/modify", method = RequestMethod.POST)
	public ResponseEntity<String> moodifyCoupon(@RequestParam("couponName") String couponName,
			@RequestParam("couponDiscount") String couponDiscount, @RequestParam("couponUseDate") String couponUseDate,
			@RequestParam("modiCouponName") String modiCouponName) throws Exception {

		CouponVo coupon = new CouponVo(couponName, Double.parseDouble(couponDiscount), Integer.parseInt(couponUseDate));
		coupon.setCouponDiscount(coupon.getCouponDiscount() * 0.01);

		System.out.println(coupon);
		System.out.println(modiCouponName);
		ResponseEntity<String> result = null;

		if (service.modifyCoupon(coupon, modiCouponName)) {
			System.out.println("수정 완료");
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return result;
	}

	@RequestMapping(value = "/coupon/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteCoupon(@RequestParam("couponName") String couponName) throws Exception {

		String result = null;

		if (service.delCoupon(couponName)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "coupon/sendCoupon", method = RequestMethod.POST)
	public @ResponseBody String deleteCoupon(@RequestBody CouponUsedVo sendCoupon) throws Exception {

		String result = null;

		if (sendCoupon.getMemberId().equals("all")) {
			List<MemberVo> members = service.getMembers();
			for (MemberVo member : members) {
				sendCoupon.setMemberId(member.getMemberId());
				if (!member.getMemberId().equals("admin")) {
					if (service.sendCoupon(sendCoupon)) {
						result = "success";
					} else {
						result = "fail";
					}
				}
			}

		} else {
			if (service.sendCoupon(sendCoupon)) {
				result = "success";
			} else {
				result = "fail";
			}
		}

		return result;
	}

	@RequestMapping(value = "product/modify", method = RequestMethod.POST)
	public @ResponseBody String modifyProd(@RequestBody ProductVo product) throws Exception {
		String result = "";
		System.out.println(product);
		if (service.updateProd(product)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "product/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteProd(@RequestParam("prodNo") String prodNo) throws Exception {
		String result = "";
		if (service.deleteProd(prodNo)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "/product/register")
	public String registerProd(Model model) throws Exception {

		return "/admin/registProduct";
	}

	@RequestMapping(value = "/product/lowest")
	public String productLowestList(Model model) throws Exception {

		List<ProductVo> lowestProduct = service.getLowestProduct();
		List<ProdImgVo> prodImgList = service.getProdImg();
		List<ProductContentVo> prodContentList = service.getProductContent();

		model.addAttribute("prodContentList", prodContentList);
		model.addAttribute("prodImgList", prodImgList);
		model.addAttribute("lowestProduct", lowestProduct);

		return "/admin/lowestProduct";
	}

	@RequestMapping(value = "/product/register/insert", method = RequestMethod.POST)
	public @ResponseBody String registerProduct(@RequestBody ProductDTO product) throws Exception {
		System.out.println(product);

		String result = "";
		if (service.registerProduct(product)) {
			result = "success";
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(value = "/product/register/prodImg", method = RequestMethod.POST)
	public @ResponseBody String registerProdImg(@RequestParam("prodImg") MultipartFile prodImg,
			@RequestParam("prodNo") String prodNo, @RequestParam("savedProdCont") String savedProdCont, HttpServletRequest request) {
		System.out.println(prodImg);
		System.out.println(prodNo);
		System.out.println(savedProdCont);
		String result = "";
		String upPath = request.getSession().getServletContext().getRealPath("/resources/uploads");

		UUID uuid = UUID.randomUUID();
		String saveProdImgFileName = uuid.toString() + "_" + prodImg.getOriginalFilename(); // 중복되지 않는 파일 이름
		String savePath = calcSavePath(upPath); // 파일이 저장될 경로 계산하여 얻어옴 (최종 저장될 경로 = upPath + savePath)
		File originProdImgTarget = new File(upPath + savePath, saveProdImgFileName);
		
		try {
			System.out.println("업로드 성공");
			FileCopyUtils.copy(prodImg.getBytes(), originProdImgTarget);
			System.out.println("이미지 저장값 =  " + savePath + saveProdImgFileName);
			service.registerProdImg(savePath + File.separator + saveProdImgFileName, prodNo);
			service.registerProdContent(savedProdCont, prodNo);
			return "success";
		} catch (Exception e) {
			System.out.println("업로드 실패");
			return "fail";
		}
	}

	@RequestMapping(value = "/product/register/prodContent", method = RequestMethod.POST)
	public ResponseEntity<NoticeUploadFile> registerProdContent(@RequestParam("prodNo") String prodNo,
			@RequestParam("prodContent") MultipartFile prodContent, HttpServletRequest request) {

		String upPath = request.getSession().getServletContext().getRealPath("/resources/uploads");
		System.out.println(upPath);
		ResponseEntity<NoticeUploadFile> result = null;
		
		if (prodContent.getSize() > 0 && prodNo.length() == 22) {

			NoticeUploadFile upFile;
			try {
				
				upFile = NoticeUploadFileProcess.uploadFileProcess(upPath, prodContent.getOriginalFilename(), prodContent.getBytes(),
						prodContent.getContentType());
				System.out.println(upFile);
				
				
				result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"
			} catch (Exception e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
			}

		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
		}
		
		return result;
	}

	public static String calcSavePath(String upPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + File.separator + cal.get(Calendar.YEAR) + ""; // \2022
		String monthPath = yearPath + File.separator + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // \2022\09
		String datePath = monthPath + File.separator + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2022\09\13

		System.out.println(upPath + datePath);

		makeDir(upPath, yearPath, monthPath, datePath); // 실제 저장될 폴더를 만듬

		return datePath;
	}

	/**
	 * @methodName : makeDir
	 * @author : shh
	 * @date : 2022. 9. 13.
	 * @입력 param : upPath 경로 밑에 yearPath, monthPath, datePath의 폴더를 각각 만든다
	 * @returnType : void
	 */
	private static void makeDir(String upPath, String... paths) {
		// String... : 가변인자(배열로 처리됨), String 타입의 매개변수로 가변인자 배열로 받을것임을 컴파일러에게 알려줌.
		// yearPath, monthPath, datePath 값을 paths라는 이름의 배열로 넘겨주게 된다.

		if (new File(upPath + paths[paths.length - 1]).exists()) {
			// 해당 경로가 존재한다... 폴더를 생성할 필요가 없다.
			return;
		}

		// 해당 경로의 폴더가 없으므로 폴더를 생성함
		for (String path : paths) {
			File dirPath = new File(upPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir(); // 실제 디렉토리(폴더) 생성
			}
		}

	}

}
