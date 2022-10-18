package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

public class ReadCountQuestionVo {
	private int no;
	private String ipAddr;
	private int bno;
	private Timestamp readDate;
	
	public ReadCountQuestionVo() {
		super();
	}
	
	public ReadCountQuestionVo(int no, String ipAddr, int bno, Timestamp readDate) {
		super();
		this.no = no;
		this.ipAddr = ipAddr;
		this.bno = bno;
		this.readDate = readDate;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public Timestamp getReadDate() {
		return readDate;
	}
	public void setReadDate(Timestamp readDate) {
		this.readDate = readDate;
	}
	
	@Override
	public String toString() {
		return "ReadCountVo [no=" + no + ", ipAddr=" + ipAddr + ", bno=" + bno + ", readDate=" + readDate + "]";
	}
	
	
	
}
