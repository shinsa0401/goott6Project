package com.boritgogae.domain;

public class OrderDetailVo {
	private int orderDetailNo;
	private int orderNo;
	private String prodNo;
	private int qty;
	private int prodSubTotalPrice;
	private char cancelStatus;
	private char returnOrExchange;
	private int initialOrderDetailNo;
	private char purchaseConfirm;
	private char returnOrExchangeConfirm;
	private char reviewStatus;
	private String memberId;
	
	public OrderDetailVo() {
		super();
		// TODO Auto-generated constructor stub
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

	public char getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(char cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public char getReturnOrExchange() {
		return returnOrExchange;
	}

	public void setReturnOrExchange(char returnOrExchange) {
		this.returnOrExchange = returnOrExchange;
	}

	public int getInitialOrderDetailNo() {
		return initialOrderDetailNo;
	}

	public void setInitialOrderDetailNo(int initialOrderDetailNo) {
		this.initialOrderDetailNo = initialOrderDetailNo;
	}

	public char getPurchaseConfirm() {
		return purchaseConfirm;
	}

	public void setPurchaseConfirm(char purchaseConfirm) {
		this.purchaseConfirm = purchaseConfirm;
	}

	public char getReturnOrExchangeConfirm() {
		return returnOrExchangeConfirm;
	}

	public void setReturnOrExchangeConfirm(char returnOrExchangeConfirm) {
		this.returnOrExchangeConfirm = returnOrExchangeConfirm;
	}

	public char getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(char reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "OrderDetailVo [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", prodNo=" + prodNo
				+ ", qty=" + qty + ", prodSubTotalPrice=" + prodSubTotalPrice + ", cancelStatus=" + cancelStatus
				+ ", returnOrExchange=" + returnOrExchange + ", initialOrderDetailNo=" + initialOrderDetailNo
				+ ", purchaseConfirm=" + purchaseConfirm + ", returnOrExchangeConfirm=" + returnOrExchangeConfirm
				+ ", reviewStatus=" + reviewStatus + ", memberId=" + memberId + "]";
	}

	public OrderDetailVo(int orderDetailNo, int orderNo, String prodNo, int qty, int prodSubTotalPrice,
			char cancelStatus, char returnOrExchange, int initialOrderDetailNo, char purchaseConfirm,
			char returnOrExchangeConfirm, char reviewStatus, String memberId) {
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
	
	
	
}
