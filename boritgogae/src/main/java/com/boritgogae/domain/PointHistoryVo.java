package com.boritgogae.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class PointHistoryVo {
	private int pointHisNo;
	private String memberId;
	private String pointNo;
	private Timestamp pointSaveDate;
	private int pointHistory;
	private int orderNo;
	public PointHistoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPointHisNo() {
		return pointHisNo;
	}
	public void setPointHisNo(int pointHisNo) {
		this.pointHisNo = pointHisNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPointNo() {
		return pointNo;
	}
	public void setPointNo(String pointNo) {
		this.pointNo = pointNo;
	}
	public Timestamp getPointSaveDate() {
		return pointSaveDate;
	}
	public void setPointSaveDate(Timestamp pointSaveDate) {
		this.pointSaveDate = pointSaveDate;
	}
	public int getPointHistory() {
		return pointHistory;
	}
	public void setPointHistory(int pointHistory) {
		this.pointHistory = pointHistory;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "PointHistoryVo [pointHisNo=" + pointHisNo + ", memberId=" + memberId + ", pointNo=" + pointNo
				+ ", pointSaveDate=" + pointSaveDate + ", pointHistory=" + pointHistory + ", orderNo=" + orderNo + "]";
	}
	public PointHistoryVo(int pointHisNo, String memberId, String pointNo, Timestamp pointSaveDate, int pointHistory,
			int orderNo) {
		super();
		this.pointHisNo = pointHisNo;
		this.memberId = memberId;
		this.pointNo = pointNo;
		this.pointSaveDate = pointSaveDate;
		this.pointHistory = pointHistory;
		this.orderNo = orderNo;
	}
	
	
	
}
