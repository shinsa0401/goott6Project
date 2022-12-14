package com.boritgogae.domain;

public class DeliveryInfoVo {
	private int deliveryInfo;
	private String memberId;
	private String address;
	private String detailAddress;
	private String recipient;
	private String recipientPhoneNumber;
	private String postCode;
	public DeliveryInfoVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliveryInfoVo(int deliveryInfo, String memberId, String address, String detailAddress, String recipient,
			String recipientPhoneNumber, String postCode) {
		super();
		this.deliveryInfo = deliveryInfo;
		this.memberId = memberId;
		this.address = address;
		this.detailAddress = detailAddress;
		this.recipient = recipient;
		this.recipientPhoneNumber = recipientPhoneNumber;
		this.postCode = postCode;
	}
	public int getDeliveryInfo() {
		return deliveryInfo;
	}
	public void setDeliveryInfo(int deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientPhoneNumber() {
		return recipientPhoneNumber;
	}
	public void setRecipientPhoneNumber(String recipientPhoneNumber) {
		this.recipientPhoneNumber = recipientPhoneNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Override
	public String toString() {
		return "DeliveryInfo [deliveryInfo=" + deliveryInfo + ", memberId=" + memberId + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", recipient=" + recipient + ", recipientPhoneNumber="
				+ recipientPhoneNumber + ", postCode=" + postCode + "]";
	}
}