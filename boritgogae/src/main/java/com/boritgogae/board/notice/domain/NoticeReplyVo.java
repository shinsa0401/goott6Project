package com.boritgogae.board.notice.domain;

import java.sql.Timestamp;

public class NoticeReplyVo {
	private int rno;
	private int bno;
	private String memberId;
	private Timestamp writtenDate;
	private String content;
	private String deleteYN;
	private int ref;
	private int step;
	private int refOrder;
	private String img;
	private String nickName;
	
	public NoticeReplyVo() {
		super();
	}

	public NoticeReplyVo(int rno, int bno, String memberId, Timestamp writtenDate, String content, String deleteYN,
			int ref, int step, int refOrder, String img, String nickName) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.memberId = memberId;
		this.writtenDate = writtenDate;
		this.content = content;
		this.deleteYN = deleteYN;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.img = img;
		this.nickName = nickName;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "NoticeReplyVo [rno=" + rno + ", bno=" + bno + ", memberId=" + memberId + ", writtenDate=" + writtenDate
				+ ", content=" + content + ", deleteYN=" + deleteYN + ", ref=" + ref + ", step=" + step + ", refOrder="
				+ refOrder + ", img=" + img + ", nickName=" + nickName + "]";
	}

	
	
}
