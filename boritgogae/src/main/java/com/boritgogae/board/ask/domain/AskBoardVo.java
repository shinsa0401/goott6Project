package com.boritgogae.board.ask.domain;

import java.sql.Timestamp;

public class AskBoardVo {
	private int askBno;
	private String writer;
	private String askCode;
	private Timestamp writtenDate;
	private int readCount;
	private int likeCount;
	private String title;
	private String contents;
	private String attachFile;
	private int ref;
	private int step;
	private int refOrder;
	private String isDelete;
	private String isSecret;
	
	public AskBoardVo() {
		super();

	}
	
	public int getAskBno() {
		return askBno;
	}
	public void setAskBno(int askBno) {
		this.askBno = askBno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getAskCode() {
		return askCode;
	}
	public void setAskCode(String askCode) {
		this.askCode = askCode;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
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
	public String getIsSecret() {
		return isSecret;
	}
	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}
	@Override
	public String toString() {
		return "AskBoardVo [askBno=" + askBno + ", writer=" + writer + ", askCode=" + askCode + ", writtenDate="
				+ writtenDate + ", readCount=" + readCount + ", likeCount=" + likeCount + ", title=" + title
				+ ", contents=" + contents + ", attachFile=" + attachFile + ", ref=" + ref + ", step=" + step
				+ ", refOrder=" + refOrder + ", isDelete=" + isDelete + ", isSecret=" + isSecret + "]";
	}
	public AskBoardVo(int askBno, String writer, String askCode, Timestamp writtenDate, int readCount, int likeCount,
			String title, String contents, String attachFile, int ref, int step, int refOrder, String isDelete,
			String isSecret) {
		super();
		this.askBno = askBno;
		this.writer = writer;
		this.askCode = askCode;
		this.writtenDate = writtenDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.title = title;
		this.contents = contents;
		this.attachFile = attachFile;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
		this.isSecret = isSecret;
	}
	
	
	
}
