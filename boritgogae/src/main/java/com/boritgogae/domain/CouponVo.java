package com.boritgogae.domain;

public class CouponVo {

	private String couponName;
	private float couponDiscount;
	private int couponUseDate;
	public CouponVo() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "CouponVo [couponName=" + couponName + ", couponDiscount=" + couponDiscount + ", couponUseDate="
				+ couponUseDate + "]";
	}
	public CouponVo(String couponName, float couponDiscount, int couponUseDate) {
		super();
		this.couponName = couponName;
		this.couponDiscount = couponDiscount;
		this.couponUseDate = couponUseDate;
	}
	
	
}
