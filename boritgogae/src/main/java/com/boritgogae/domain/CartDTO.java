package com.boritgogae.domain;

import java.sql.Timestamp;

public class CartDTO {
	
	private int cartNo;
	private String prodNo;
	private String memberId;
	private int qty;
	private String sessionId;
	private Timestamp sessionLimit;
	
	public CartDTO() {
		super();
	}

	public CartDTO(int cartNo, String prodNo, String memberId, int qty, String sessionId, Timestamp sessionLimit) {
		super();
		this.cartNo = cartNo;
		this.prodNo = prodNo;
		this.memberId = memberId;
		this.qty = qty;
		this.sessionId = sessionId;
		this.sessionLimit = sessionLimit;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Timestamp getSessionLimit() {
		return sessionLimit;
	}

	public void setSessionLimit(Timestamp sessionLimit) {
		this.sessionLimit = sessionLimit;
	}

	@Override
	public String toString() {
		return "CartVO [cartNo=" + cartNo + ", prodNo=" + prodNo + ", memberId=" + memberId + ", qty=" + qty
				+ ", sessionId=" + sessionId + ", sessionLimit=" + sessionLimit + "]";
	}
	
	

}
