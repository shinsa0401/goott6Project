package com.boritgogae.domain;

public class DetailOrderVo {
	private int orderDetailNo;
	private int orderNo;
	private String prodNo;
	private int qty;
	private int prodSubTotalPrice;
	private String cancelStatus;
	private String returnOrExchange;
	private int initialOrderDetailNo;
	private String purchaseConfirm;
	private String returnOrExchangeConfirm;
	private String reviewStatus;
	private String memberId;
	@Override
	public String toString() {
		return "DetailOrderVo [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", prodNo=" + prodNo
				+ ", qty=" + qty + ", prodSubTotalPrice=" + prodSubTotalPrice + ", cancelStatus=" + cancelStatus
				+ ", returnOrExchange=" + returnOrExchange + ", initialOrderDetailNo=" + initialOrderDetailNo
				+ ", purchaseConfirm=" + purchaseConfirm + ", returnOrExchangeConfirm=" + returnOrExchangeConfirm
				+ ", reviewStatus=" + reviewStatus + ", memberId=" + memberId + "]";
	}
	public DetailOrderVo() {
		super();
	}
	public DetailOrderVo(int orderDetailNo, int orderNo, String prodNo, int qty, int prodSubTotalPrice,
			String cancelStatus, String returnOrExchange, int initialOrderDetailNo, String purchaseConfirm,
			String returnOrExchangeConfirm, String reviewStatus, String memberId) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.prodNo = prodNo;
		this.qty = qty;
		this.prodSubTotalPrice = prodSubTotalPrice;
		this.cancelStatus = cancelStatus;
		this.returnOrExchange = returnOrExchange;
		this.initialOrderDetailNo = initialOrderDetailNo;
		this.purchaseConfirm = purchaseConfirm;
		this.returnOrExchangeConfirm = returnOrExchangeConfirm;
		this.reviewStatus = reviewStatus;
		this.memberId = memberId;
	}
	public int getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
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
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getReturnOrExchange() {
		return returnOrExchange;
	}
	public void setReturnOrExchange(String returnOrExchange) {
		this.returnOrExchange = returnOrExchange;
	}
	public int getInitialOrderDetailNo() {
		return initialOrderDetailNo;
	}
	public void setInitialOrderDetailNo(int initialOrderDetailNo) {
		this.initialOrderDetailNo = initialOrderDetailNo;
	}
	public String getPurchaseConfirm() {
		return purchaseConfirm;
	}
	public void setPurchaseConfirm(String purchaseConfirm) {
		this.purchaseConfirm = purchaseConfirm;
	}
	public String getReturnOrExchangeConfirm() {
		return returnOrExchangeConfirm;
	}
	public void setReturnOrExchangeConfirm(String returnOrExchangeConfirm) {
		this.returnOrExchangeConfirm = returnOrExchangeConfirm;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
