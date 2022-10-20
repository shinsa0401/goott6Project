package com.boritgogae.domain;

public class OrderProductDTO {
	private String prodNo;
	private int qty;
	private int price;
	private String prodImg;
	
	
	public OrderProductDTO(String prodNo, int qty, int price, String prodImg) {
		super();
		this.prodNo = prodNo;
		this.qty = qty;
		this.price = price;
		this.prodImg = prodImg;
	}
	
	
	public OrderProductDTO() {
		super();
	}


	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	@Override
	public String toString() {
		return "OrderProduct [prodNo=" + prodNo + ", qty=" + qty + ", price=" + price + ", prodImg=" + prodImg + "]";
	}
}
