package com.boritgogae.domain;

public class OrderDTO {
	private String memberId;
	private int prodTotalPrice;
	private String deliveryOption;
	private int totalPrice;
	private String isMember;
	private String phoneNumber;
	private String guestEmail;
	private String guestPwd;
	private String name;
	private String memo;
	private int usedPoint;
	private String address;
	private String detailAddress;
	private int postCode;
	private int accumPoint;
	
	public OrderDTO(String memberId, int prodTotalPrice, String deliveryOption, int totalPrice, String isMember,
			String phoneNumber, String guestEmail, String guestPwd, String name, String memo, int usedPoint,
			String address, String detailAddress, int postCode, int accumPoint) {
		super();
		this.memberId = memberId;
		this.prodTotalPrice = prodTotalPrice;
		this.deliveryOption = deliveryOption;
		this.totalPrice = totalPrice;
		this.isMember = isMember;
		this.phoneNumber = phoneNumber;
		this.guestEmail = guestEmail;
		this.guestPwd = guestPwd;
		this.name = name;
		this.memo = memo;
		this.usedPoint = usedPoint;
		this.address = address;
		this.detailAddress = detailAddress;
		this.postCode = postCode;
		this.accumPoint = accumPoint;
	}

	public OrderDTO() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getProdTotalPrice() {
		return prodTotalPrice;
	}

	public void setProdTotalPrice(int prodTotalPrice) {
		this.prodTotalPrice = prodTotalPrice;
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestPwd() {
		return guestPwd;
	}

	public void setGuestPwd(String guestPwd) {
		this.guestPwd = guestPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
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

	public int getAccumPoint() {
		return accumPoint;
	}

	public void setAccumPoint(int accumPoint) {
		this.accumPoint = accumPoint;
	}

	@Override
	public String toString() {
		return "OrderDTO [memberId=" + memberId + ", prodTotalPrice=" + prodTotalPrice + ", deliveryOption="
				+ deliveryOption + ", totalPrice=" + totalPrice + ", isMember=" + isMember + ", phoneNumber="
				+ phoneNumber + ", guestEmail=" + guestEmail + ", guestPwd=" + guestPwd + ", name=" + name + ", memo="
				+ memo + ", usedPoint=" + usedPoint + ", address=" + address + ", detailAddress=" + detailAddress
				+ ", postCode=" + postCode + ", accumPoint=" + accumPoint + "]";
	}
}
