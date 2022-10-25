package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuestionBoardVo {
	private int no;
	private String writer;
	private String pwd;	
	private Timestamp writtenDate;
	private int readCount;
	private int likeCount;
	private String title;
	private String content;
	private String uploadFile;
	private String isDelete;
	private int replyCount;
	
	public QuestionBoardVo() {
		super();
	}
	
	public QuestionBoardVo(int no, String writer, String pwd, Timestamp writtenDate, int readCount, int likeCount,
			String title, String content, String uploadFile, String isDelete, int replyCount) {
		super();
		this.no = no;
		this.writer = writer;
		this.pwd = pwd;
		this.writtenDate = writtenDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.title = title;
		this.content = content;
		this.uploadFile = uploadFile;
		this.isDelete = isDelete;
		this.replyCount = replyCount;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	@Override
	public String toString() {
		return "QuestionBoardVo [no=" + no + ", writer=" + writer + ", pwd=" + pwd + ", writtenDate=" + writtenDate
				+ ", readCount=" + readCount + ", likeCount=" + likeCount + ", title=" + title + ", content=" + content
				+ ", uploadFile=" + uploadFile + ", isDelete=" + isDelete + ", replyCount=" + replyCount + "]";
	}
	
	
	
	
	
	
	
}
