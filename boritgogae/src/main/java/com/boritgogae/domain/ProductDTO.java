package com.boritgogae.domain;

import java.sql.Timestamp;

public class ProductDTO {
	private String prodNo;
	private String prodName;
	private int prodQuantity;
	private int prodPrice;
	private int readCount;
	private int likeCount;
	private Timestamp prodPutDate;
	private int reviewCount;
	private String originalFile;
	private String brand;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [prodNo=" + prodNo + ", prodName=" + prodName + ", prodQuantity=" + prodQuantity
				+ ", prodPrice=" + prodPrice + ", readCount=" + readCount + ", likeCount=" + likeCount
				+ ", prodPutDate=" + prodPutDate + ", reviewCount=" + reviewCount + ", originalFile=" + originalFile
				+ ", brand=" + brand + "]";
	}
	public ProductDTO(String prodNo, String prodName, int prodQuantity, int prodPrice, int readCount, int likeCount,
			Timestamp prodPutDate, int reviewCount, String originalFile) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodQuantity = prodQuantity;
		this.prodPrice = prodPrice;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.prodPutDate = prodPutDate;
		this.reviewCount = reviewCount;
		this.originalFile = originalFile;
		this.brand = brand;
	}
	public ProductDTO() {
		super();
	}
	
}
