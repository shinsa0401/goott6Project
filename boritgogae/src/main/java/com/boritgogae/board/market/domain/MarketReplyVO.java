package com.boritgogae.board.market.domain;

import java.sql.Timestamp;

public class MarketReplyVO {
	private int rno;
	private int bno;
	private String replyer;
	private Timestamp replyWrittenDate;
	private String replyContent;
	
	public MarketReplyVO() {
		super();
	}

	public MarketReplyVO(int rno, int bno, String replyer, Timestamp replyWrittenDate, String replyContent) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyer = replyer;
		this.replyWrittenDate = replyWrittenDate;
		this.replyContent = replyContent;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public Timestamp getReplyWrittenDate() {
		return replyWrittenDate;
	}

	public void setReplyWrittenDate(Timestamp replyWrittenDate) {
		this.replyWrittenDate = replyWrittenDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replyer=" + replyer + ", replyWrittenDate="
				+ replyWrittenDate + ", replyContent=" + replyContent + "]";
	}
	
	
	
}
