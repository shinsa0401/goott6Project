package com.boritgogae.board.free.domain;

import java.sql.Timestamp;

public class BoardVo {

	
	private int bno; // 글번호
	private String writer; // 글쓴이
	private Timestamp createDate; //등록일
	private int readCount; // 조회수
	private String content; // 글 내용
	private int likeCount; // 좋아요 수
	private String title; // 글 제목
	private String isDelete; // 삭제여부
	private int refOrde; // 원글참조정렬
	private int ref; // 원본글 참조
	private int step; // 계층형 깊이
	
	
	public BoardVo() {
		super();
	}


	public BoardVo(int bno, String writer, Timestamp createDate, int readCount, String content, int likeCount,
			String title, String isDelete, int refOrde, int ref, int step) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.createDate = createDate;
		this.readCount = readCount;
		this.content = content;
		this.likeCount = likeCount;
		this.title = title;
		this.isDelete = isDelete;
		this.refOrde = refOrde;
		this.ref = ref;
		this.step = step;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Timestamp getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public int getRefOrde() {
		return refOrde;
	}


	public void setRefOrde(int refOrde) {
		this.refOrde = refOrde;
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


	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", writer=" + writer + ", createDate=" + createDate + ", readCount=" + readCount
				+ ", content=" + content + ", likeCount=" + likeCount + ", title=" + title + ", isDelete=" + isDelete
				+ ", refOrde=" + refOrde + ", ref=" + ref + ", step=" + step + "]";
	}
	
	
	
	
	
}
	
