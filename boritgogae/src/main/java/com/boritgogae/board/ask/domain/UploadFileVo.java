package com.boritgogae.board.ask.domain;

public class UploadFileVo {
	private int no;
	private int bno;
	private String originalFile;
	private String thumbnailFile;
	
	public UploadFileVo() {
		super();
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

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}

	public String getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(String thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}

	@Override
	public String toString() {
		return "UploadFileVo [no=" + no + ", bno=" + bno + ", originalFile=" + originalFile + ", thumbnailFile="
				+ thumbnailFile + "]";
	}

	public UploadFileVo(int no, int bno, String originalFile, String thumbnailFile) {
		super();
		this.no = no;
		this.bno = bno;
		this.originalFile = originalFile;
		this.thumbnailFile = thumbnailFile;
	}
	
	
	
}
