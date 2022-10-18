package com.boritgogae.board.free.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class ReplyVo {
	
	private int rno;
	private int bno;
	private int prno;
	private String replyContent;
	private String replyer;
	private Timestamp replyWittenDate;
	private Timestamp replyUpdateDate;
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ReplyVo that = (ReplyVo) o;
	        return Objects.equals(rno, that.rno) && Objects.equals(bno, that.bno) && Objects.equals(prno, that.prno) && Objects.equals(replyContent, that.replyContent) && Objects.equals(replyer, that.replyer);
	    }
	
	
	
	public ReplyVo() {
		super();
	}
	

	public ReplyVo(int rno, int bno, int prno, String replyContent, String replyer, Timestamp replyWittenDate,
			Timestamp replyUpdateDate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.prno = prno;
		this.replyContent = replyContent;
		this.replyer = replyer;
		this.replyWittenDate = replyWittenDate;
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

	public int getPrno() {
		return prno;
	}

	public void setPrno(int prno) {
		this.prno = prno;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public Timestamp getReplyWittenDate() {
		return replyWittenDate;
	}

	public void setReplyWittenDate(Timestamp replyWittenDate) {
		this.replyWittenDate = replyWittenDate;
	}

	public Timestamp getReplyUpdateDate() {
		return replyUpdateDate;
	}

	public void setReplyUpdateDate(Timestamp replyUpdateDate) {
		this.replyUpdateDate = replyUpdateDate;
	}

	@Override
	public String toString() {
		return "replyVo [rno=" + rno + ", bno=" + bno + ", prno=" + prno + ", replyContent=" + replyContent
				+ ", replyer=" + replyer + ", replyWittenDate=" + replyWittenDate + ", replyUpdateDate="
				+ replyUpdateDate + "]";
	}


	
	

}
