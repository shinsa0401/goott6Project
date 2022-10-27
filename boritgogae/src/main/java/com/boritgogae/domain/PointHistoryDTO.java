package com.boritgogae.domain;

public class PointHistoryDTO {
	private String memberId;
	private int pointNo;
	private int pointHistory;
	private int orderNo;
	
	public PointHistoryDTO(String memberId, int pointNo, int pointHistory, int orderNo) {
		super();
		this.memberId = memberId;
		this.pointNo = pointNo;
		this.pointHistory = pointHistory;
		this.orderNo = orderNo;
	}

	public PointHistoryDTO() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPointNo() {
		return pointNo;
	}

	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
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
		return "PointHistoryDTO [memberId=" + memberId + ", pointNo=" + pointNo + ", pointHistory=" + pointHistory
				+ ", orderNo=" + orderNo + "]";
	}
	
	
}
