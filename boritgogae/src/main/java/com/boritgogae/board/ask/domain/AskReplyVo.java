package com.boritgogae.board.ask.domain;

import java.sql.Timestamp;

public class AskReplyVo {
	private int askRno;
	private int askBno;
	private String contents;
	private String memberId;
	private Timestamp writtenTime;
	private int ref;
	private int step;
	private int refOrder;
	private String isDelete;
	
	public AskReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAskRno() {
		return askRno;
	}

	public void setAskRno(int askRno) {
		this.askRno = askRno;
	}

	public int getAskBno() {
		return askBno;
	}

	public void setAskBno(int askBno) {
		this.askBno = askBno;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getRefOrder() {
		return refOrder;
	}

	public void setRefOrder(int refOrder) {
		this.refOrder = refOrder;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "AskReplyVo [askRno=" + askRno + ", askBno=" + askBno + ", contents=" + contents + ", memberId="
				+ memberId + ", writtenTime=" + writtenTime + ", ref=" + ref + ", step=" + step + ", refOrder="
				+ refOrder + ", isDelete=" + isDelete + "]";
	}

	public AskReplyVo(int askRno, int askBno, String contents, String memberId, Timestamp writtenTime, int ref,
			int step, int refOrder, String isDelete) {
		super();
		this.askRno = askRno;
		this.askBno = askBno;
		this.contents = contents;
		this.memberId = memberId;
		this.writtenTime = writtenTime;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
	}
	
	
	
	
}
