package com.boritgogae.domain;

import java.sql.Timestamp;

public class CouponUsedVo {
	private int no;
	private String memberId;
	private String couponName;
	private Timestamp useDate;
	private Timestamp issueDate;
	private String couponWhy;
	private int orderNo;
	
	public CouponUsedVo(int no, String memberId, String couponName, Timestamp useDate, Timestamp issueDate,
			String couponWhy, int orderNo) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.couponName = couponName;
		this.useDate = useDate;
		this.issueDate = issueDate;
		this.couponWhy = couponWhy;
		this.orderNo = orderNo;
	}

	public CouponUsedVo() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Timestamp getUseDate() {
		return useDate;
	}

	public void setUseDate(Timestamp useDate) {
		this.useDate = useDate;
	}

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public String getCouponWhy() {
		return couponWhy;
	}

	public void setCouponWhy(String couponWhy) {
		this.couponWhy = couponWhy;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "CouponUsedVo [no=" + no + ", memberId=" + memberId + ", couponName=" + couponName + ", useDate="
				+ useDate + ", issueDate=" + issueDate + ", couponWhy=" + couponWhy + ", orderNo=" + orderNo + "]";
	}
}
