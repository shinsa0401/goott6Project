package com.boritgogae.domain;

public class CouponVo {
    private String couponName;
    private double couponDiscount;
    private int couponUseDate;

    public CouponVo() {
        super();
    }

    public CouponVo(String couponName, double couponDiscount, int couponUseDate) {
        super();
        this.couponName = couponName;
        this.couponDiscount = couponDiscount;
        this.couponUseDate = couponUseDate;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
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

}
