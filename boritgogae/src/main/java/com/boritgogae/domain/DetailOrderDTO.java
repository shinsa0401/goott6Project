package com.boritgogae.domain;

public class DetailOrderDTO {
	private int orderNo;
	private String prodNo;
	private int qty;
	private int prodSubTotalPrice;
	private String memberId;
	
	public DetailOrderDTO(int orderNo, String prodNo, int qty, int prodSubTotalPrice, String memberId) {
		super();
		this.orderNo = orderNo;
		this.prodNo = prodNo;
		this.qty = qty;
		this.prodSubTotalPrice = prodSubTotalPrice;
		this.memberId = memberId;
	}
	public DetailOrderDTO() {
		super();
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	public int getProdSubTotalPrice() {
		return prodSubTotalPrice;
	}
	public void setProdSubTotalPrice(int prodSubTotalPrice) {
		this.prodSubTotalPrice = prodSubTotalPrice;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "DetailOrderDTO [orderNo=" + orderNo + ", prodNo=" + prodNo + ", qty=" + qty + ", subTotalPrice="
				+ prodSubTotalPrice + ", memberId=" + memberId + "]";
	}
}
