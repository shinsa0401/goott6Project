package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

public class ReplyVo {
	private int rno;
	private int bno;
	private String replyWriter;
	private String replyPwd;
	private String replyContent;
	private Timestamp replyWrittenDate;
	private Timestamp replyUpdateDate;
	
	public ReplyVo() {
		super();
	}
	
	public ReplyVo(int rno, int bno, String replyWriter, String replyPwd, String replyContent,
			Timestamp replyWrittenDate, Timestamp replyUpdateDate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyWriter = replyWriter;
		this.replyPwd = replyPwd;
		this.replyContent = replyContent;
		this.replyWrittenDate = replyWrittenDate;
		this.replyUpdateDate = replyUpdateDate;
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
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public String getReplyPwd() {
		return replyPwd;
	}
	public void setReplyPwd(String replyPwd) {
		this.replyPwd = replyPwd;
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
	
	@Override
	public String toString() {
		return "ReplyVo [rno=" + rno + ", bno=" + bno + ", replyWriter=" + replyWriter + ", replyPwd=" + replyPwd
				+ ", replyContent=" + replyContent + ", replyWrittenDate=" + replyWrittenDate + ", replyUpdateDate="
				+ replyUpdateDate + "]";
	}
	
}
