package com.boritgogae.board.free.domain;

public class UploadFileFreeVo {
	

	@Override
	public String toString() {
		return "UploadFileFreeVo [ino=" + ino + ", bno=" + bno + ", attachFile=" + attachFile + "]";
	}
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	private int ino;
	private int bno;
	private String attachFile;
	public UploadFileFreeVo(int ino, int bno, String attachFile) {
		super();
		this.ino = ino;
		this.bno = bno;
		this.attachFile = attachFile;
	}
	public UploadFileFreeVo() {
		super();
	}
	
	
	

}
