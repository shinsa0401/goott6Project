package com.boritgogae.domain;

import java.sql.Timestamp;

public class ProdImgVO {
	private int no;
	private String prodNo;
	private String originalFile;
	private String thumbnailFile;
	private Timestamp uploadDate;
	private String isDelete;
	
	
	public ProdImgVO(int no, String prodNo, String originalFile, String thumbnailFile, Timestamp uploadDate,
			String isDelete) {
		super();
		this.no = no;
		this.prodNo = prodNo;
		this.originalFile = originalFile;
		this.thumbnailFile = thumbnailFile;
		this.uploadDate = uploadDate;
		this.isDelete = isDelete;
	}

	public ProdImgVO() {
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

	public String getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(String thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
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

	@Override
	public String toString() {
		return "ProdImgVO [no=" + no + ", prodNo=" + prodNo + ", originalFile=" + originalFile + ", thumbnailFile="
				+ thumbnailFile + ", uploadDate=" + uploadDate + ", isDelete=" + isDelete + "]";
	}
}
