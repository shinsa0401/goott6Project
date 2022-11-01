package com.boritgogae.domain;

import java.sql.Timestamp;

public class DM {
	private int no;
	private String receiverId;
	private String senderId;
	private String noteContent;
	private Timestamp sendDate;
	private Timestamp readDate;
	public DM() {
		super();
	}
	public DM(int no, String receiverId, String senderId, String noteContent, Timestamp sendDate, Timestamp readDate) {
		super();
		this.no = no;
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.noteContent = noteContent;
		this.sendDate = sendDate;
		this.readDate = readDate;
	}
	@Override
	public String toString() {
		return "DM [no=" + no + ", receiverId=" + receiverId + ", senderId=" + senderId + ", noteContent=" + noteContent
				+ ", sendDate=" + sendDate + ", readDate=" + readDate + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public Timestamp getSendDate() {
		return sendDate;
	}
	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}
	public Timestamp getReadDate() {
		return readDate;
	}
	public void setReadDate(Timestamp readDate) {
		this.readDate = readDate;
	}
	
	
	

}
