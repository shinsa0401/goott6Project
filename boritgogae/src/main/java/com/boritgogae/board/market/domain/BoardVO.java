package com.boritgogae.board.market.domain;

import java.sql.Timestamp;

public class BoardVO {
	private int no;
	private String writer;
	private Timestamp writtenDate;
	private String title;
	private String content;
	private String imgFile;
	private int readCount;
	private String isDelete;
	private int ref;
	private int refOrder;
	private int step;
	
	public BoardVO() {
		super();
	}

	public BoardVO(int no, String writer, Timestamp writtenDate, String title, String content, String imgFile,
			int readCount, String isDelete, int ref, int refOrder, int step) {
		super();
		this.no = no;
		this.writer = writer;
		this.writtenDate = writtenDate;
		this.title = title;
		this.content = content;
		this.imgFile = imgFile;
		this.readCount = readCount;
		this.isDelete = isDelete;
		this.ref = ref;
		this.refOrder = refOrder;
		this.step = step;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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
		return "BoardVo [no=" + no + ", writer=" + writer + ", writtenDate=" + writtenDate + ", title=" + title
				+ ", content=" + content + ", imgFile=" + imgFile + ", readCount=" + readCount + ", isDelete="
				+ isDelete + ", ref=" + ref + ", refOrder=" + refOrder + ", step=" + step + "]";
	}
	
	
	
	
	
}
