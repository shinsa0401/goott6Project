package com.boritgogae.board.notice.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boritgogae.board.notice.domain.NoticeReplyVo;
import com.boritgogae.board.notice.domain.NoticeVo;
import com.boritgogae.board.notice.etc.PagingInfo;
import com.boritgogae.board.notice.etc.UploadFile;
import com.boritgogae.board.notice.etc.UploadFileProcess;
import com.boritgogae.board.notice.service.NoticeServiceImpl;

@Controller
@RequestMapping(value= "/board/notice/*")
public class NoticeController {
	
	@Inject
	private NoticeServiceImpl service;
	
	private List<UploadFile> UploadFileLst = new ArrayList<>();
	
	// 공지사항 가져오기
	@RequestMapping(value="/list")
	public String getNoticeBoard(Model model, @RequestParam(value="pageNo", required = false, defaultValue = "1") int pageNo, RedirectAttributes rttr) throws Exception {
		System.out.println("noticeController");
		
		if(pageNo < 1) {
			pageNo = 1;
		}
		
		Map<String, Object> map = this.service.getNoticeBoard(pageNo);
		List<NoticeVo> list = (List<NoticeVo>)map.get("boardList");
		PagingInfo pi = (PagingInfo)map.get("pagingInfo");
		
		System.out.println(list);
		
		model.addAttribute("pagingInfo", pi);
		model.addAttribute("list", list);
		rttr.addFlashAttribute("pageNo", pageNo);
		
		return "boardNotice/noticeBoardList";
		
	}
	
	// 공지 글 작성시작
	@RequestMapping(value="/writeBoard")
	public String writeBoard() throws Exception {
		System.out.println("공지 글 작성");
		
		return "boardNotice/writeNoticeBoard";
	}
	
	// 공지 글 등록
	@RequestMapping(value = "/register")
	public String registerBoard(NoticeVo board) throws Exception {
		System.out.println(board + "글 등록");
		board.setContent(board.getContent().replace("\r\n", ""));
		if(service.registerBoard(board)) {
			System.out.println("등록 완료");
			
		}
		
		return "redirect:/board/notice/list";
	}
	
	// 공지 글 상세 보기
	@RequestMapping(value = "/view")
	public String viewBoard(@RequestParam("no") String bno, Model model) throws Exception {
		int no = Integer.parseInt(bno);
		
		Map<String, Object> map;
		NoticeVo board = service.viewBoard(no);
		
		model.addAttribute("board", board);

		return "boardNotice/noticeView";
	}
	
	// 공지 글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteBoard(String bno) throws Exception {
		int no = Integer.parseInt(bno);
		
		ResponseEntity<String> result = null;
		System.out.println(no);
		if(service.deleteBoard(no)) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return result;
	}
	
	// 수정할 공지 글 번호 가져오기
	@RequestMapping(value="/bnoToModify")
	public String bnoToModify(@RequestParam("bno") String bno, Model model) throws Exception {
		int no = Integer.parseInt(bno);
		NoticeVo board = service.viewBoard(no);
		
		model.addAttribute("board", board);
		
		return "boardNotice/modifyNoticeBoard";
	}
	
	// 공지 글 수정
	@RequestMapping(value="/modify")
	public String modifyBoard(NoticeVo board) throws Exception {
		if(service.modifyBoard(board)) {
			System.out.println("수정 완료");
		}
		
		return "redirect:/board/notice/list";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<UploadFile> uploadFile(@RequestParam("file") MultipartFile upfile, HttpServletRequest request) {
		System.out.println("컨트롤러 : 파일 업로드요청");
		System.out.println("업로드된 파일 이름 : " + upfile.getOriginalFilename());
		System.out.println("파일 사이즈 : " + upfile.getSize());
		System.out.println("업로드된 파일의 타입 : " + upfile.getContentType());

		// 파일이 실제 저장될 경로
		String upPath = request.getSession().getServletContext().getRealPath("resources/uploads");
		System.out.println("파일이 실제 저장 될 경로 : " + upfile);

		ResponseEntity<UploadFile> result = null;

		if (upfile.getSize() > 0) {

			UploadFile upFile;
			try {
				upFile = UploadFileProcess.uploadFileProcess(upPath, upfile.getOriginalFilename(), upfile.getBytes(),
						upfile.getContentType());
				this.UploadFileLst.add(upFile); // 업로드 될 파일이 여러개일 경우를 대비해 리스트에 넣어둠
				result = new ResponseEntity<>(upFile, HttpStatus.OK); // 업로드된 파일의 정보와 통신상태 "성공"
			} catch (IOException e) {
				result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
			}

		} else {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 통신상태 "실패"
		}

		System.out.println("현재 업로드 파일 리스트 : " + Arrays.toString(this.UploadFileLst.toArray()));
		
		return result;
	}
	
		
	// 댓글 관련
	
	// 댓글 등록
	@RequestMapping(value="/replyRegister", method = RequestMethod.POST)
	public ResponseEntity<String> registerReplyBoard(@RequestBody NoticeReplyVo replyBoard) throws Exception {
		replyBoard.setNickName(service.getNickName(replyBoard.getMemberId()));
		System.out.println(replyBoard + "글 등록");
		
		ResponseEntity<String> result = null;
		
		if(service.registerReply(replyBoard)) {
			System.out.println("등록 완료");
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
			
		return result;
	}
	
	@RequestMapping(value = "/replyList/{bno}")
	public ResponseEntity<List<NoticeReplyVo>> getEntireReplies(@PathVariable("bno") int bno) {
		
		ResponseEntity<List<NoticeReplyVo>> result = null;
		
		try {
			List<NoticeReplyVo> lst = service.getReplyList(bno);
			
			if(lst.size() < 1) {
				result = null;
			} else {
				result = new ResponseEntity<List<NoticeReplyVo>>(lst, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	
	// 공지 댓글 삭제
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteReplyBoard(String rno) throws Exception {
		System.out.println(rno);
		
		int no = Integer.parseInt(rno);
			
		ResponseEntity<String> result = null;
			
		if(service.deleteReplyBoard(no)) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
			
		return result;
	}
		
	// 공지 댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(@RequestBody NoticeReplyVo board) throws Exception{
		
		ResponseEntity<String> result = null;
		
		if(service.modifyReplyBoard(board)) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
			
		return result;
	}
	
}
