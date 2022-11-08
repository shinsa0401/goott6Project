package com.boritgogae.domain;

import java.sql.Timestamp;

public class UserBoardVo {
	private String writer;
	private Timestamp writtenDate;
	private String title;
	private int readCount;
	private String board;
	public UserBoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = writtenDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	@Override
	public String toString() {
		return "UserBoardVo [writer=" + writer + ", writtenDate=" + writtenDate + ", title=" + title + ", readCount="
				+ readCount + ", board=" + board + "]";
	}
	public UserBoardVo(String writer, Timestamp writtenDate, String title, int readCount, String board) {
		super();
		this.writer = writer;
		this.writtenDate = writtenDate;
		this.title = title;
		this.readCount = readCount;
		this.board = board;
	}
	
}
