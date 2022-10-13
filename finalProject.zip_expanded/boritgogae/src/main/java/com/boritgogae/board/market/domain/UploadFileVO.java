package com.boritgogae.board.market.domain;


public class UploadFileVo {
	private int no;
	private int bno;
	private String originalFileName;
	private String thumbnailFileName;
	
	public UploadFileVo() {
		super();
	}
	
	public UploadFileVo(int no, int bno, String originalFileName, String thumbnailFileName) {
		super();
		this.no = no;
		this.bno = bno;
		this.originalFileName = originalFileName;
		this.thumbnailFileName = thumbnailFileName;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getThumbnailFileName() {
		return thumbnailFileName;
	}
	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}
	
	@Override
	public String toString() {
		return "UploadFileVo [no=" + no + ", bno=" + bno + ", originalFileName=" + originalFileName
				+ ", thumbnailFileName=" + thumbnailFileName + "]";
	}
	
	
}

