package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

public class BoardVo {
	private int no;
	private String writer;
	private String pwd;
	private Timestamp writtenDate;
	private int readCount;
	private int likeCount;
	private String title;
	private String content;
	private String uploadFile;
	private int ref;
	private int step;
	private int refOrder;
	private String isDelete;
<<<<<<< HEAD
	private String category;
=======
>>>>>>> sth
	
	public BoardVo() {
		super();
	}
<<<<<<< HEAD
	
	public BoardVo(int no, String writer, String pwd, Timestamp writtenDate, int readCount, int likeCount, String title,
			String content, String uploadFile, int ref, int step, int refOrder, String isDelete, String category) {
=======

	public BoardVo(int no, String writer, String pwd, Timestamp writtenDate, int readCount, int likeCount, String title,
			String content, String uploadFile, int ref, int step, int refOrder, String isDelete) {
>>>>>>> sth
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
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
<<<<<<< HEAD
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
=======
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

>>>>>>> sth
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", writer=" + writer + ", pwd=" + pwd + ", writtenDate=" + writtenDate
				+ ", readCount=" + readCount + ", likeCount=" + likeCount + ", title=" + title + ", content=" + content
				+ ", uploadFile=" + uploadFile + ", ref=" + ref + ", step=" + step + ", refOrder=" + refOrder
<<<<<<< HEAD
				+ ", isDelete=" + isDelete + ", category=" + category + "]";
	}
	
=======
				+ ", isDelete=" + isDelete + "]";
	}
	
	
	
>>>>>>> sth
}
