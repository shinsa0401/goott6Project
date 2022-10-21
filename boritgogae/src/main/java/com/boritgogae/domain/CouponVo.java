package com.boritgogae.domain;

import java.sql.Timestamp;

public class CouponVo {
	private String couponName;
	private float couponDiscount;
	private int couponUseDate;
	private Timestamp dueDate;


	public CouponVo(String couponName, float couponDiscount, int couponUseDate, Timestamp dueDate) {
		super();
		this.couponName = couponName;
		this.couponDiscount = couponDiscount;
		this.couponUseDate = couponUseDate;
		this.dueDate = dueDate;
	}

	public CouponVo() {
		super();
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public float getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(float couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public int getCouponUseDate() {
		return couponUseDate;
	}

	public void setCouponUseDate(int couponUseDate) {
		this.couponUseDate = couponUseDate;
	}

	
	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = new Timestamp(dueDate);
	}

	@Override
	public String toString() {
		return "CouponVo [couponName=" + couponName + ", couponDiscount=" + couponDiscount + ", couponUseDate="
				+ couponUseDate + ", dueDate=" + dueDate + "]";
	}
}
