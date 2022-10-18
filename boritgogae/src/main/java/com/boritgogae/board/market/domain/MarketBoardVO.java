package com.boritgogae.board.market.domain;

import java.sql.Timestamp;

public class MarketBoardVO {
	private int no;
	private String writer;
	private Timestamp writtenDate;
	private String title;
	private String content;
	
	private int readCount;
	
	private int ref;
	private int refOrder;
	private int step;
	
	public MarketBoardVO() {
		super();
	}

	public MarketBoardVO(int no, String writer, Timestamp writtenDate, String title, String content, int readCount, int ref,
			int refOrder, int step) {
		super();
		this.no = no;
		this.writer = writer;
		this.writtenDate = writtenDate;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
		return "BoardVO [no=" + no + ", writer=" + writer + ", writtenDate=" + writtenDate + ", title=" + title
				+ ", content=" + content + ", readCount=" + readCount + ", ref=" + ref + ", refOrder=" + refOrder
				+ ", step=" + step + "]";
	}

	
	
	
	
	
	
}
