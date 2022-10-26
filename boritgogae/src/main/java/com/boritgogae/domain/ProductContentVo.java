package com.boritgogae.domain;

import java.sql.Timestamp;

public class ProductContentVo {
	private int no;
	private String prodNo;
	private String prodContent;
	private Timestamp uploadDate;
	private String isDelete;
	
	public ProductContentVo(int no, String prodNo, String prodContent, Timestamp uploadDate, String isDelete) {
		super();
		this.no = no;
		this.prodNo = prodNo;
		this.prodContent = prodContent;
		this.uploadDate = uploadDate;
		this.isDelete = isDelete;
	}
	public ProductContentVo() {
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
	public String getProdContent() {
		return prodContent;
	}
	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
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
		return "ProductContentVo [no=" + no + ", prodNo=" + prodNo + ", prodContent=" + prodContent + ", uploadDate="
				+ uploadDate + ", isDelete=" + isDelete + "]";
	}
}
