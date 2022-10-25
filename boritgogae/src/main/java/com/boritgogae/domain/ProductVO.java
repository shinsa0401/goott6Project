package com.boritgogae.domain;

import java.sql.Timestamp;

public class ProductVO {
	private String prodNo;
	private String prodName;
	private String prodContent;
	private int prodQuantity;
	private int prodPrice;
	private int readCount;
	private int likeCount;
	private Timestamp prodPutDate;
	private int reviewCount;
	
	
	public ProductVO(String prodNo, String prodName, String prodContent, int prodQuantity, int prodPrice, int readCount,
			int likeCount, Timestamp prodPutDate, int reviewCount) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodContent = prodContent;
		this.prodQuantity = prodQuantity;
		this.prodPrice = prodPrice;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.prodPutDate = prodPutDate;
		this.reviewCount = reviewCount;
	}


	public ProductVO() {
		super();
	}


	public String getProdNo() {
		return prodNo;
	}


	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getProdContent() {
		return prodContent;
	}


	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}


	public int getProdQuantity() {
		return prodQuantity;
	}


	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}


	public int getProdPrice() {
		return prodPrice;
	}


	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
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


	public Timestamp getProdPutDate() {
		return prodPutDate;
	}


	public void setProdPutDate(Timestamp prodPutDate) {
		this.prodPutDate = prodPutDate;
	}


	public int getReviewCount() {
		return reviewCount;
	}


	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}


	@Override
	public String toString() {
		return "ProductVO [prodNo=" + prodNo + ", prodName=" + prodName + ", prodContent=" + prodContent
				+ ", prodQuantity=" + prodQuantity + ", prodPrice=" + prodPrice + ", readCount=" + readCount
				+ ", likeCount=" + likeCount + ", prodPutDate=" + prodPutDate + ", reviewCount=" + reviewCount + "]";
	}
}
