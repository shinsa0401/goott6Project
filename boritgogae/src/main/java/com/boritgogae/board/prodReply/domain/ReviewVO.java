package com.boritgogae.board.prodReply.domain;

import java.sql.Timestamp;

public class ReviewVO {
	private int reviewNo;
	private String writer;
	private String prodNo;
	private Timestamp writtenDate;
	private String title;
	private String content;
	private int assess;
	private int orderDetailNo;
	private int likeCnt;
	
	
	public ReviewVO(int reviewNo, String writer, String prodNo, Timestamp writtenDate, String title, String content,
			int assess, int orderDetailNo, int likeCnt) {
		super();
		this.reviewNo = reviewNo;
		this.writer = writer;
		this.prodNo = prodNo;
		this.writtenDate = writtenDate;
		this.title = title;
		this.content = content;
		this.assess = assess;
		this.orderDetailNo = orderDetailNo;
		this.likeCnt = likeCnt;
	}


	public ReviewVO() {
		super();
	}


	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getProdNo() {
		return prodNo;
	}


	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
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


	public int getAssess() {
		return assess;
	}


	public void setAssess(int assess) {
		this.assess = assess;
	}


	public int getOrderDetailNo() {
		return orderDetailNo;
	}


	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}


	public int getLikeCnt() {
		return likeCnt;
	}


	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}


	@Override
	public String toString() {
		return "ReviewVO [reviewNo=" + reviewNo + ", writer=" + writer + ", prodNo=" + prodNo + ", writtenDate="
				+ writtenDate + ", title=" + title + ", content=" + content + ", assess=" + assess + ", orderDetailNo="
				+ orderDetailNo + ", likeCnt=" + likeCnt + "]";
	}
}
