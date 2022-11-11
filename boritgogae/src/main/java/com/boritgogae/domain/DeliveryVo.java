package com.boritgogae.domain;

import java.sql.Timestamp;

public class DeliveryVo {
	private int orderDetailNo;
	private int orderNo;
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
	
	public DeliveryVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryVo(int orderDetailNo, int orderNo, int deliveryNo, String recipient, String phoneNumber,
			String deliveryCompany, String address, String detailAddress, int postCode, String deliveryRequest,
			String deliveryStatus, int returnDeliveryNo, Timestamp deliveryCmplDate) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
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

	@Override
	public String toString() {
		return "DeliveryVo [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", deliveryNo=" + deliveryNo
				+ ", recipient=" + recipient + ", phoneNumber=" + phoneNumber + ", deliveryCompany=" + deliveryCompany
				+ ", address=" + address + ", detailAddress=" + detailAddress + ", postCode=" + postCode
				+ ", deliveryRequest=" + deliveryRequest + ", deliveryStatus=" + deliveryStatus + ", returnDeliveryNo="
				+ returnDeliveryNo + ", deliveryCmplDate=" + deliveryCmplDate + "]";
	}
	
	
	
}
