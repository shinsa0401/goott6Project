package com.boritgogae.domain;

import java.sql.Timestamp;

public class UserReplyVo {
	private String memberId;
	private Timestamp writtenTime;
	private String contents;
	private String board;
	public UserReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Timestamp getWrittenTime() {
		return writtenTime;
	}
	public void setWrittenTime(Timestamp writtenTime) {
		this.writtenTime = writtenTime;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	@Override
	public String toString() {
		return "UserReplyVo [memberId=" + memberId + ", writtenTime=" + writtenTime + ", contents=" + contents
				+ ", board=" + board + "]";
	}
	public UserReplyVo(String memberId, Timestamp writtenTime, String contents, String board) {
		super();
		this.memberId = memberId;
		this.writtenTime = writtenTime;
		this.contents = contents;
		this.board = board;
	}
	
	
}
