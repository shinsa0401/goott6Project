package com.boritgogae.board.tip.domain;

import java.sql.Timestamp;

public class TipBoardVo {
	private int bno;
	private String memberId;
	private Timestamp createDate;
	private String title;
	private String content;
	private String imgFile;
	private String pwd;
	private int readCount;
	private String isDelete;
	private String categories;
	private int ref;
	private int refOrder;
	private int step;
	public TipBoardVo() {
		super();
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRefOrder() {
		return refOrder;
	}
	public void setRefOrder(int refOrder) {
		this.refOrder = refOrder;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", memberId=" + memberId + ", createDate=" + createDate + ", title=" + title
				+ ", content=" + content + ", imgFile=" + imgFile + ", pwd=" + pwd + ", readCount=" + readCount
				+ ", isDelete=" + isDelete + ", categories=" + categories + ", ref=" + ref + ", refOrder=" + refOrder
				+ ", step=" + step + "]";
	}
	public TipBoardVo(int bno, String memberId, Timestamp createDate, String title, String content, String imgFile,
			String pwd, int readCount, String isDelete, String categories, int ref, int refOrder, int step) {
		super();
		this.bno = bno;
		this.memberId = memberId;
		this.createDate = createDate;
		this.title = title;
		this.content = content;
		this.imgFile = imgFile;
		this.pwd = pwd;
		this.readCount = readCount;
		this.isDelete = isDelete;
		this.categories = categories;
		this.ref = ref;
		this.refOrder = refOrder;
		this.step = step;
	}
	
}
