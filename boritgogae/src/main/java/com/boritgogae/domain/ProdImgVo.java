package com.boritgogae.domain;

import java.sql.Timestamp;

public class ProdImgVo {
	private int no;
	private String prodNo;
	private String originalFile;
	private Timestamp uploadDate;
	private String isDelete;
	@Override
	public String toString() {
		return "prodImgVo [no=" + no + ", prodNo=" + prodNo + ", originalFile=" + originalFile + ", uploadDate="
				+ uploadDate + ", isDelete=" + isDelete + "]";
	}
	public ProdImgVo(int no, String prodNo, String originalFile, Timestamp uploadDate, String isDelete) {
		super();
		this.no = no;
		this.prodNo = prodNo;
		this.originalFile = originalFile;
		this.uploadDate = uploadDate;
		this.isDelete = isDelete;
	}
	public ProdImgVo() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
