package com.boritgogae.board.prodReply.domain;

import java.sql.Timestamp;

public class ProdReplyVo {
	private int rno;
	private int reviewNo;
	private String replyWriter;
	private String replyContent;
	private Timestamp replyWrittenDate;
	private Timestamp replyUpdateDate;
	private int ref;
	private int step;
	private int refOrder;
	
	



	public ProdReplyVo(int rno, int reviewNo, String replyWriter, String replyContent, Timestamp replyWrittenDate,
			Timestamp replyUpdateDate, int ref, int step, int refOrder) {
		super();
		this.rno = rno;
		this.reviewNo = reviewNo;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.replyWrittenDate = replyWrittenDate;
		this.replyUpdateDate = replyUpdateDate;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
	}


	public ProdReplyVo() {
		super();
	}


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public String getReplyWriter() {
		return replyWriter;
	}


	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public Timestamp getReplyWrittenDate() {
		return replyWrittenDate;
	}


	public void setReplyWrittenDate(Timestamp replyWrittenDate) {
		this.replyWrittenDate = replyWrittenDate;
	}


	public Timestamp getReplyUpdateDate() {
		return replyUpdateDate;
	}


	public void setReplyUpdateDate(Timestamp replyUpdateDate) {
		this.replyUpdateDate = replyUpdateDate;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getRefOrder() {
		return refOrder;
	}


	public void setRefOrder(int refOrder) {
		this.refOrder = refOrder;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	@Override
	public String toString() {
		return "QuestionReplyVo [rno=" + rno + ", reviewNo=" + reviewNo + ", replyWriter=" + replyWriter + ", replyContent="
				+ replyContent + ", replyWrittenDate=" + replyWrittenDate + ", replyUpdateDate=" + replyUpdateDate
				+ ", ref=" + ref + ", step=" + step + ", refOrder=" + refOrder + "]";
	}
}
