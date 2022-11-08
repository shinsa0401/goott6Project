package com.boritgogae.board.prodReply.domain;

public class ReplyDTO {
	private int reviewNo;
	private int parentRno;
	private String replyWriter;
	private String replyContent;
	private String prodNo;
	





	public ReplyDTO(int reviewNo, int parentRno, String replyWriter, String replyContent, String prodNo) {
		super();
		this.reviewNo = reviewNo;
		this.parentRno = parentRno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.prodNo = prodNo;
	}

	public ReplyDTO() {
		super();
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

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public int getParentRno() {
		return parentRno;
	}

	public void setParentRno(int parentRno) {
		this.parentRno = parentRno;
	}

	@Override
	public String toString() {
		return "ReplyDTO [reviewNo=" + reviewNo + ", parentRno=" + parentRno + ", replyWriter=" + replyWriter
				+ ", replyContent=" + replyContent + ", prodNo=" + prodNo + "]";
	}
}
