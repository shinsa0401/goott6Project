package com.boritgogae.board.tip.domain;

import java.sql.Timestamp;

public class TipReplyVo {
	private int rno;
	private int bno;
	private Timestamp createDate;
	private String memberId;
	private String content;
	private int likeCount;
	public TipReplyVo(int rno, int bno, Timestamp createDate, String memberId, String content, int likeCount) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.createDate = createDate;
		this.memberId = memberId;
		this.content = content;
		this.likeCount = likeCount;
	}
	public TipReplyVo() {
		super();
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	@Override
	public String toString() {
		return "ReplyVo [rno=" + rno + ", bno=" + bno + ", createDate=" + createDate + ", memberId=" + memberId
				+ ", content=" + content + ", likeCount=" + likeCount + "]";
	}
	
	
}
