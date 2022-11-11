package com.boritgogae.domain;

import java.sql.Timestamp;

public class TotalOrderListVo {
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
	private int deliveryNo;
	private String recipient;
	private String phoneNumber;
	private String deliveryCompany;
	private String address;
	private String detailAddress;
	private int postCode;
	private String deliveryRequest;
	private String deliveryStatus;
	private int returnDeliveryNo;
	private Timestamp deliveryCmplDate;
	private String prodName;
	private int prodPrice;
	private String originalFile;
	
	public TotalOrderListVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TotalOrderListVo(int orderDetailNo, int orderNo, String prodNo, int qty, int prodSubTotalPrice,
			String cancelStatus, String returnOrExchange, int initialOrderDetailNo, String purchaseConfirm,
			String returnOrExchangeConfirm, String reviewStatus, String memberId, int deliveryNo, String recipient,
			String phoneNumber, String deliveryCompany, String address, String detailAddress, int postCode,
			String deliveryRequest, String deliveryStatus, int returnDeliveryNo, Timestamp deliveryCmplDate,
			String prodName, int prodPrice, String originalFile) {
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
		this.deliveryNo = deliveryNo;
		this.recipient = recipient;
		this.phoneNumber = phoneNumber;
		this.deliveryCompany = deliveryCompany;
		this.address = address;
		this.detailAddress = detailAddress;
		this.postCode = postCode;
		this.deliveryRequest = deliveryRequest;
		this.deliveryStatus = deliveryStatus;
		this.returnDeliveryNo = returnDeliveryNo;
		this.deliveryCmplDate = deliveryCmplDate;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.originalFile = originalFile;
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
	public int getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getDeliveryRequest() {
		return deliveryRequest;
	}
	public void setDeliveryRequest(String deliveryRequest) {
		this.deliveryRequest = deliveryRequest;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public int getReturnDeliveryNo() {
		return returnDeliveryNo;
	}
	public void setReturnDeliveryNo(int returnDeliveryNo) {
		this.returnDeliveryNo = returnDeliveryNo;
	}
	public Timestamp getDeliveryCmplDate() {
		return deliveryCmplDate;
	}
	public void setDeliveryCmplDate(Timestamp deliveryCmplDate) {
		this.deliveryCmplDate = deliveryCmplDate;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	@Override
	public String toString() {
		return "TotalOrderListVo [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", prodNo=" + prodNo
				+ ", qty=" + qty + ", prodSubTotalPrice=" + prodSubTotalPrice + ", cancelStatus=" + cancelStatus
				+ ", returnOrExchange=" + returnOrExchange + ", initialOrderDetailNo=" + initialOrderDetailNo
				+ ", purchaseConfirm=" + purchaseConfirm + ", returnOrExchangeConfirm=" + returnOrExchangeConfirm
				+ ", reviewStatus=" + reviewStatus + ", memberId=" + memberId + ", deliveryNo=" + deliveryNo
				+ ", recipient=" + recipient + ", phoneNumber=" + phoneNumber + ", deliveryCompany=" + deliveryCompany
				+ ", address=" + address + ", detailAddress=" + detailAddress + ", postCode=" + postCode
				+ ", deliveryRequest=" + deliveryRequest + ", deliveryStatus=" + deliveryStatus + ", returnDeliveryNo="
				+ returnDeliveryNo + ", deliveryCmplDate=" + deliveryCmplDate + ", prodName=" + prodName
				+ ", prodPrice=" + prodPrice + ", originalFile=" + originalFile + "]";
	}
	
	
	
	
	
}
