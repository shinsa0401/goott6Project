package com.boritgogae.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boritgogae.board.free.domain.FreePageHandler;
import com.boritgogae.board.free.domain.FreeSearchCondition;
import com.boritgogae.board.free.etc.FreeUploadFile;

import com.boritgogae.board.free.service.FreeBoardService;
import com.boritgogae.domain.DM;
import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;
import com.boritgogae.domain.ProductVo;
import com.boritgogae.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private MemberService memService;

	@Autowired
	private JavaMailSender mailSender;

	
	@Inject
	MemberService service;
	

	
	
	@RequestMapping(value = "/joinOk", method = RequestMethod.POST)
	public String joinOk(DeliveryInfoVo dv, MemberVo vo, HttpServletResponse response)  {
		
	
		
		try {
			service.memberjoin(vo, response,dv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	 System.out.println("확인!!!!!!!!!!!!!!!!!!!!!!!!"+vo);
	 System.out.println("확인!!!!!!!!!!!!!!!!!!!!!!!!"+dv);
		return"/member/join";
	}
	 @RequestMapping(value = "/ex")
		public String test() throws Exception {
			
			
			
			 return "/member/ex";
			
		}
	 
	 
	 @RequestMapping(value = "/send")

		public void sendtest(DM dm, @RequestParam("noteContent")String noteContent,@RequestParam("receiverId")String receiverId) throws Exception {
			
				service.insertWriter(dm);
			
			
		}
	
	
	 @RequestMapping(value = "/join")
	public String join( ) {
		 
		 return "/member/join";
		
	}
	
	@RequestMapping(value = "/jusoPopup")
	public String juso() {
		return "/member/jusoPopup";
	}
	
	@RequestMapping (value="/mailCheck")
	@ResponseBody
	public String mailCheck(String email) throws Exception{
		System.out.println("이메일 데이터 전송확인");
		System.out.println("인증 메일 : "+email);
		
		Random random = new Random();
		int checkNum = random.nextInt(888888)+111111; // 111111 - 999999
		System.out.println("인증번호 : "+checkNum);
		
		//이메일 보내기
		String setFrom = "anwls6650@naver.com";
		String toEmail = email;
		String title = "test";
		String content = "가입해주셔서 감사합니다."+ "<br/><br/>"+"인증 번호는 "+checkNum+" 입니다.<br/>"+
							"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toEmail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        String num = Integer.toString(checkNum);
        return num;
	}
	
	
	
	@RequestMapping(value = "/like")
	public ModelAndView like(Model m, ProductVo pv ) throws Exception {
		
		 ModelAndView mav = new ModelAndView();
		 
		 mav.setViewName("member/like");
		 
		
		String memberId = "haha";
		List<ProductVo> list = service.selectLike(memberId);
	
        m.addAttribute("list", list);
        
        

   
       
        
		
		System.out.println("확인!!!!!!!");
		
		return mav;
	}
	
	
	
	 @RequestMapping(value = "/DM")
	    public ModelAndView list(Model m, FreeSearchCondition sc, HttpServletRequest request) throws Exception {
		 ModelAndView mav = new ModelAndView();
			
				mav.setViewName("member/DM");

	            int totalCnt = service.getSearchResultCnt(sc);
	            m.addAttribute("totalCnt", totalCnt);

	            FreePageHandler pageHandler = new FreePageHandler(totalCnt, sc);

	            List<DM> list = service.getSearchResultPage(sc);
	            m.addAttribute("list", list);
	            m.addAttribute("ph", pageHandler);
	            System.out.println("list"+list);
	           

	        return mav; // 로그인을 한 상태이면, 게시판 화면으로 이동
	           

			
	    }
	
	 //@RequestParam("tdArr")List[] tdArr
	 @RequestMapping(value = "/delDM")
		public ModelAndView delBoard(@RequestParam("tdArr[]")List<String> tdArr)throws Exception{
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("member/DM");
		System.out.println(tdArr);
		
	
		
		 for(String no : tdArr) {
			 service.sendDel(no);
		 }
		 
		
			return mav;
		}
		
	
	 
	 @RequestMapping(value = "/dmdetail")
		public ModelAndView boardDetail( Model model,@RequestParam("no") String no)throws Exception{
		 int bno = Integer.parseInt(no);
		 
		 ModelAndView mav = new ModelAndView();
			mav.setViewName("member/dmdetail");
		 
		 Map<String,Object> map = service.detaildm(bno);
		 
		 DM dm = (DM)map.get("dm");
		 
		 model.addAttribute("dm", dm);
		 
		 return mav;
		 
		 
	 }
	
	

}
