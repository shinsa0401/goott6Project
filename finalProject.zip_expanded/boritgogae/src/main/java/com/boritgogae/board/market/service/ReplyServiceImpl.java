package com.boritgogae.board.market.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.boritgogae.board.market.dao.ReplyDAO;
import com.boritgogae.board.market.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

		@Inject
		private ReplyDAO dao;
		
	//	@Override
	//	public boolean replyWrite(int bno, ReplyVO reply) throws Exception {
	//		boolean result = false;
	//		
	//		int result1 = dao.replyWrite(bno, reply);
	//		System.out.println("서비스 vo"+reply);
	//		System.out.println("댓글 성공?"+result1);
	//		if (result1 ==1){
	//			result = true;
	//		}
	//		return result;
	//	}
	
		//댓글 목록
		@Override
		public List<ReplyVO> getAllReply(int bno) throws Exception {
			List<ReplyVO> lst = dao.getAllReply(bno);
			
			return lst;
			 
		}
		//댓글 등록
		@Override
		public boolean replyWrite(ReplyVO reply) throws Exception {
			boolean result = false;
			
			//reply객체를 dao단으로 보내고(insert)
			if(dao.replyWrite(reply)==1) {
				System.out.println("댓글 등록 완료");
				result = true;
			}
			return result;
			}

		
		//댓글 수정 
		@Override
		public boolean modiReply(ReplyVO reply) throws Exception {
			boolean result = false;
			
			
			if(dao.modiReply(reply)==1) {
				result = true;
			}
			return result;
		}
		
		//댓글 삭제
		@Override
		public boolean delReply(ReplyVO reply) throws Exception {
			boolean result = false;
			
			if(dao.delReply(reply)==1) {
				result = true;
			}
			return result;
		}
	
	
	

}
