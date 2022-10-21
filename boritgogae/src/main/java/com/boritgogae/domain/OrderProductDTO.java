package com.boritgogae.domain;

public class OrderProductDTO {
	private String prodNo;
	private int qty;
	
	
	public OrderProductDTO(String prodNo, int qty) {
		super();
		this.prodNo = prodNo;
		this.qty = qty;
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

	@Override
	public String toString() {
		return "OrderProduct [prodNo=" + prodNo + ", qty=" + qty + "]";
	}
}
