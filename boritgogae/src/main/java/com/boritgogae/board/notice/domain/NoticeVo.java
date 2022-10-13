package com.boritgogae.board.notice.domain;

import java.sql.Timestamp;

public class NoticeVo {
	private int bno;
	private String memberId;
	private Timestamp writtenDate;
	private int readCount;
	private int likeCount;
	private String title;
	private String content;
	
	public NoticeVo() {
		super();
	}

	public NoticeVo(int bno, String memberId, Timestamp writtenDate, int readCount, int likeCount, String title,
			String content) {
		super();
		this.bno = bno;
		this.memberId = memberId;
		this.writtenDate = writtenDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.title = title;
		this.content = content;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Timestamp getWrittenDate() {
		return writtenDate;
	}

	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = writtenDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "NoticeVo [bno=" + bno + ", memberId=" + memberId + ", writtenDate=" + writtenDate + ", readCount="
				+ readCount + ", likeCount=" + likeCount + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
