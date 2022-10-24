package com.boritgogae.domain;

public class GuestOrderDTO {
	private String name;
	private String phoneNumber;
	private String guestPwd;
	
	public GuestOrderDTO() {
		super();
	}
	
	public GuestOrderDTO(String name, String phoneNumber, String guestPwd) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.guestPwd = guestPwd;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGuestPwd() {
		return guestPwd;
	}
	public void setGuestPwd(String guestPwd) {
		this.guestPwd = guestPwd;
	}
	
	@Override
	public String toString() {
		return "GuestOrderDTO [name=" + name + ", phoneNumber=" + phoneNumber + ", guestPwd=" + guestPwd + "]";
	}
	
	
	
	
}
